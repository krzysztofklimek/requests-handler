import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class HandlerTest {

	@Test
	public void testIfXML() {
		Handler handler = new Handler();
		String fileNameXML = "plikXML.xml";
		String fileNameCSV = "plikCSV.txt";
		
		assertTrue(handler.ifXML(fileNameXML));
		assertFalse(handler.ifXML(fileNameCSV));
	}

	@Test
	public void testReadCSVLine(){
		
		Handler handler = new Handler();
		Database database = new Database();
		String line = "client,2,name,4,5";
		Scanner lineScanner = new Scanner(line).useDelimiter(",");
		
		handler.readCSVLine(lineScanner, database);
		
		assertEquals(1, database.getList().size());
		assertEquals("client", database.getList().get(0).getClientId());
		assertEquals(2, database.getList().get(0).getRequestId());
		assertEquals("name", database.getList().get(0).getName());
		assertEquals(4, database.getList().get(0).getQuantity());
		assertEquals(5, 5, database.getList().get(0).getPrice());
	}
	
	@Test
	public void	testReadXMLLine() throws Exception{
		Handler handler = new Handler();
		Database database = new Database();
		
		InputStream inputStream = new FileInputStream("testXML.xml");
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		InputSource is = new InputSource(reader);
		is.setEncoding("UTF-8");

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(is);
		doc.getDocumentElement().normalize();
		NodeList requestsList = doc.getElementsByTagName("request");

		handler.readXMLLine(requestsList, database);
		
		assertEquals(1, database.getList().size());
		assertEquals("client", database.getList().get(0).getClientId());
		assertEquals(2, database.getList().get(0).getRequestId());
		assertEquals("name", database.getList().get(0).getName());
		assertEquals(4, database.getList().get(0).getQuantity());
		assertEquals(5, 5, database.getList().get(0).getPrice());
	}

}
















