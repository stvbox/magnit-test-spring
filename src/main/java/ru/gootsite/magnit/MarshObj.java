package ru.gootsite.magnit;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="entries")
public class MarshObj extends ArrayList<ObjEntry> {
	public ArrayList<ObjEntry> entry = new ArrayList<ObjEntry>();
}

class ObjEntry {
	@XmlElement(name="field")
	private int FIELD;
	
	public ObjEntry(int FIELD) {
		this.FIELD = FIELD;
	}
}