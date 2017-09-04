package ru.gootsite.magnit;

import java.io.Serializable;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Starter implements Serializable {

	private int iterCount = 0;

	private DBService dbService;

	private Computer computer;
	private FilesService fileService;

	public static void main(String[] args) {
		printSeparator();

		ApplicationContext ctx = null;
		
		try {
			ctx = new FileSystemXmlApplicationContext("app-context-xml.xml");
		} catch (NoClassDefFoundError e) {
			System.out.println("Dependencies not found. Make build package 'mvn clean package' ");
		} catch (Exception e) {
			ctx = new GenericXmlApplicationContext();
			((GenericXmlApplicationContext) ctx).load("classpath:app-context-xml.xml");
			((GenericXmlApplicationContext) ctx).refresh();
		}

		printSeparator();

		Starter mainObj = (Starter) ctx.getBean("mainObject");

		while (!(mainObj.iterCount > 0)) {
			try {
				System.out.print("Specify the number of iterations: ");
				Scanner input = new Scanner(System.in);

				int num = input.nextInt();
				if (num > 20000000)
					throw new Exception("Maximum number of iterations exceeded!");
				if (num < 1)
					throw new Exception("No number of iterations!");

				mainObj.setIterCount(num);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				continue;
			}
		}

		mainObj.dbService.prepareDB();
		mainObj.dbService.fillingDB(mainObj.iterCount);
		mainObj.fileService.saveOriginFile(mainObj.dbService.queryDB());
		mainObj.fileService.trnsformXmlFile();
		System.out.printf("Result of the calculations: %.2f", mainObj.computer.calc());
		System.out.println();
		printSeparator();

	}

	public void setIterCount(int iterCount) {
		this.iterCount = iterCount;
	}
	
	public void setFileService(FilesService fileService) {
		this.fileService = fileService;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	public void setDbService(DBService dbService) {
		this.dbService = dbService;
	}
	
	static void printSeparator() {
		System.out.println("-----------------------------------------------------");
	}

}
