package ru.gootsite.magnit;

import java.io.File;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class FilesService {
	public boolean saveOriginFile(MarshObj collection) {
		try {
			File outFile = new File("1.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(MarshObj.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(collection, outFile);
		} catch (JAXBException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	boolean trnsformXmlFile() {
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			
			InputStream is = java.lang.ClassLoader.getSystemResourceAsStream("trans.xslt");
			Source xslt = new StreamSource(is);

	        Transformer transformer;
			transformer = factory.newTransformer(xslt);
			
	        Source text = new StreamSource(new File("1.xml"));
	        transformer.transform(text, new StreamResult(new File("output.xml")));
	        return true;
		} catch (TransformerException e) {
			System.out.println("Ошибка приобразования XML");
			e.printStackTrace();
			return false;
		}
	}
}
