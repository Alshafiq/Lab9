import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import org.xml.sax.helpers.DefaultHandler;

import org.xml.sax.Attributes;


public class Parser {

	public static void readSAX(File f) throws Exception
	{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser s = spf.newSAXParser();
		
		DefaultHandler dh = new DefaultHandler()
		{
			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes)
			{
				System.out.println("Start: " + qName);
			}
			
			public void endElement(String uri, String localName, String qName)
			{
				System.out.println("END: " + qName);
			}
			
			public void characters(char[] ch, int start, int length)
			{
				System.out.println("CHARS: " + new String(ch, start, length));
			}
		};
		s.parse(f, dh);
	}
	
	public static void readDOM(File f) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder d = factory.newDocumentBuilder();
		Document doc = d.parse(f);
		
		System.out.println("Root: " + doc.getDocumentElement().getNodeName());
		
		NodeList lst = doc.getDocumentElement().getChildNodes();
		for(int ii=0; ii<lst.getLength(); ii++)
		{
			Node n = lst.item(ii);
			System.out.println("Child: " + n.getNodeName() + "--> " + n.getTextContent());
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file = new File("/users/alshafiqhasbi/desktop/file.xml");
		readSAX(file);
		System.out.println("\n");
		readDOM(file);
	}
}
