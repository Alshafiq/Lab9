import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

@SuppressWarnings("serial")
public class AddressBook implements Serializable{

	private ArrayList<BuddyInfo> buddy;
	private File file; 
	
	public AddressBook() throws IOException
	{
		buddy = new ArrayList<>();
		
		//This is input output path!
		file = new File("/users/alshafiqhasbi/desktop/file.txt");
        if (!file.exists()) {
			file.createNewFile();
		}
	}
	
	public void addBuddy(BuddyInfo bud)
	{
		if(bud != null)
		{
			buddy.add(bud);
		}
	}
	
	public void removeBuddy(BuddyInfo bud)
	{
		if(bud != null)
		{
			buddy.remove(bud);
		}
	}
	
	public BuddyInfo getBuddy(int i)
	{
		return buddy.get(i);
	}
	
	public int getBookSize()
	{
		return buddy.size();
	}
	
	public void export() throws IOException
	{
        List<String> list = new ArrayList<String>();
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
        for(BuddyInfo b: buddy)
        {
            list.add(b.toString());
        }
        
        String[] stringArr = list.toArray(new String[0]);
      
		for(String s: stringArr)
		{
			bw.write(s);
			bw.newLine();
		}
		
		bw.close();
	}
	
	public AddressBook importA() throws IOException
	{
		AddressBook book = new AddressBook();
		BuddyInfo bud = null;
        @SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        
        while ((line = reader.readLine()) != null) {    		
            bud = BuddyInfo.importB(line);
			book.addBuddy(bud);
        }
        
        return book;
	}

	public void sExport() throws IOException
	{
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
		oos.close();
	}
	
	public AddressBook sImport() throws IOException, ClassNotFoundException 
	{
		AddressBook book = new AddressBook();
		@SuppressWarnings("resource")
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		book = (AddressBook) ois.readObject();
		
		return book;
	}
	
	public String toXML()
	{
		String open = "<AddressBook>";
		String close = "</AddressBook>";
		String xml = open + "\n\t";
		BuddyInfo b;
		for(int i = 0; i<buddy.size(); i++)
		{
			b = buddy.get(i);
			if(i == buddy.size()-1)
			{
				xml = xml + b.toXML() + "\n";
			}
			else
			{
				xml = xml + b.toXML() + "\n\t";
			}
		}
		xml = xml + close;
		return xml;
	}
	
	public void ExportToXmlFile() throws IOException
	{
		File xmlFile = new File("/users/alshafiqhasbi/desktop/file.xml");
		if (!xmlFile.exists()) {
			xmlFile.createNewFile();
		}
		FileWriter fw = new FileWriter(xmlFile.getAbsoluteFile());
		fw.write(this.toXML());
		fw.close();
	}
	
	public void  importFromXmlFileDOM() throws Exception
	{
		AddressBook book = new AddressBook();
		final BuddyInfo bud = null;
		
		File xmlFile = new File("/users/alshafiqhasbi/desktop/file.xml");
		
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
		s.parse(xmlFile, dh);
	}
	
	public static void main(String[] args) throws Exception {
		// Setup
		BuddyInfo bud1 = new BuddyInfo("Apik", "Alta Vista", 613854);
		BuddyInfo bud2 = new BuddyInfo("Ahmad", "Colonel By", 613855);
		BuddyInfo bud3 = new BuddyInfo("Ally", "Walkley", 613856);
		
		AddressBook book = new AddressBook();
		AddressBook book2 = new AddressBook();
		
		book.addBuddy(bud1);
		book.addBuddy(bud2);
		book.addBuddy(bud3);
		
		// Runner
		//book.sExport();				//export AddressBook object
		//book2 = book.sImport();		//import AddressBook object
		//book.export();			//export content of AddressBook
		//book2 = book.importA();	//import content of AddressBook
		book.ExportToXmlFile();
		System.out.println(book.toXML());
		System.out.println("\n");
		book.importFromXmlFileDOM();
		//System.out.println("See result in file.txt in the specified path(export).");
		//System.out.println("\nThis is book2(import from file) contents:");
		
		for(int i = 0; i < book2.getBookSize(); i++)
		{
			//System.out.println(book2.getBuddy(i).toString());
		}
	}

}