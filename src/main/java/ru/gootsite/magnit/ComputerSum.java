package ru.gootsite.magnit;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ComputerSum extends Computer {

	@Override
	float calc() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true); // never forget this!
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse("output.xml");
			
			XPathFactory xpthFactory = XPathFactory.newInstance();
			XPath xpath = xpthFactory.newXPath();
			XPathExpression expr = xpath.compile("sum(//entries/entry/@field)");
			Double result = (Double) expr.evaluate(doc, XPathConstants.NUMBER);
			
			return result.floatValue();	
		} catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return 0;
		
	}

}
