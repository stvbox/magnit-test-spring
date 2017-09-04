package ru.gootsite.magnit;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="entries")
public class UnMarshObj extends ArrayList<UMObjEntry> {
	private List<UMObjEntry> entry = new ArrayList<UMObjEntry>();
	
	@XmlElement(name="entry")
	public List<UMObjEntry> getEntry(){
		return entry;
	}
	
	public float calcAverage(){
		float counter = 0;
		float summ = 0;
		float result = 0;
		
        for(UMObjEntry ent : entry) {
        	summ += ent.getField();
        	counter++;
        }
        
        result = (counter>0)?summ/counter:0;
        
        //System.out.println("Сумма: " + summ);
        //System.out.println("Счетчик: " + counter);
        //System.out.println("Среднее: " + result);
        
		return result;
	}
	
}

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
class UMObjEntry {
	public UMObjEntry() {
		super();
	}

	@XmlAttribute(name="field")
	private int FIELD;
	
	public UMObjEntry(int FIELD) {
		this.FIELD = FIELD;
	}
	
	public int getField(){
		return FIELD;
	}
}