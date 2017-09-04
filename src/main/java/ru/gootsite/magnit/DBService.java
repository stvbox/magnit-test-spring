package ru.gootsite.magnit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService {
	public DBService() {
		super();
	}

	String connectionString = "";
	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	private Connection connection;

	public DBService(String connectionString) {
		this.connectionString = connectionString;
	}

	public boolean prepareDB() {
		Statement stmt;
		
		if(!initConnection()) return false;
		
		try {
			stmt = this.connection.createStatement();
			String sqldr = "DROP TABLE IF EXISTS TEST";
			stmt.execute(sqldr);
			String sqlcr = "CREATE TABLE TEST (FIELD INT NOT NULL)"; 
			stmt.executeUpdate(sqlcr);
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println("Ошибка проверки/подготовки БД");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	private boolean initConnection() {
		try {
			this.connection = DriverManager.getConnection(this.connectionString);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean fillingDB(int iterations) {
		
		String insertSQL = "INSERT INTO TEST (FIELD) VALUES (?);";
		
		try {
			this.connection.setAutoCommit(false);
			PreparedStatement stmt = this.connection.prepareStatement(insertSQL);
			
			for(int i=1; i <= iterations; i++) {
				stmt.setInt(1, i);
				stmt.execute();
			}

			this.connection.commit();
			this.connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	MarshObj queryDB() {
		MarshObj marshaled = new MarshObj();
		Statement stmt;
		try {
			stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT FIELD FROM TEST;" );
			while ( rs.next() ) {
				//System.out.println("read: " + rs.getInt("FIELD"));
				marshaled.entry.add(new ObjEntry(rs.getInt("FIELD")));
			}
			rs.close();
			stmt.close();
			return marshaled;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
