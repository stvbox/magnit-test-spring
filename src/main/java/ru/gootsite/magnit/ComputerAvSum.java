package ru.gootsite.magnit;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ComputerAvSum extends Computer {

	@Override
	float calc() {
		try {
			JAXBContext jaxbContext;
			jaxbContext = JAXBContext.newInstance(UnMarshObj.class);
	        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	        UnMarshObj umobjs = (UnMarshObj) jaxbUnmarshaller.unmarshal( new File("output.xml") );
	        return umobjs.calcAverage();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
