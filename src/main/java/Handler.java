import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Handler {

	private int numberOfErrorsInCSVFile;
	private int numberOfErrorsInXMLFile;

	public Handler() {
		numberOfErrorsInCSVFile = 0;
		numberOfErrorsInXMLFile = 0;
	}

	public void increaseCSVErrors() {
		numberOfErrorsInCSVFile++;
	}

	public void increaseXMLErrors() {
		numberOfErrorsInXMLFile++;
	}

	/** the method checks if the given file is an xml file */
	public boolean ifXML(String fileName) {

		String[] dividedFilename = fileName.split("\\.");

		if (dividedFilename[1].equals("xml"))
			return true;
		else
			return false;

	}

	/** the method creates file and divides it into single lines */
	public void addFromCSVFile(String fileCSVName, String fileDelimiter, String lineDelimiter, Database database)
			throws FileNotFoundException {
		File file = new File(fileCSVName);
		Scanner fileScanner = new Scanner(file).useDelimiter(fileDelimiter);
		readCSVFile(fileScanner, lineDelimiter, database);
		fileScanner.close();
	}

	/** the method divides line into values */
	public void readCSVFile(Scanner fileScanner, String lineDelimiter, Database database) {

		fileScanner.next();
		while (fileScanner.hasNext()) {
			Scanner lineScanner = new Scanner(fileScanner.next()).useDelimiter(lineDelimiter);
			readCSVLine(lineScanner, database);
			lineScanner.close();
		}
	}

	/** the method creates Request object */
	public void readCSVLine(Scanner lineScanner, Database database) {
		Request requestObject = null;

		while (lineScanner.hasNext()) {
			try {
				requestObject = new Request(lineScanner.next(), // clientId
						lineScanner.next(), // requestId
						lineScanner.next(), // name
						lineScanner.next(), // quantity
						lineScanner.next()); // price

				database.addRequest(requestObject);
			} catch (Exception e) {
				increaseCSVErrors();
			}
		}
	}

	/** the method handles XML file format*/
	public void addFromXMLFile(String fileXMLName, Database database) throws Exception {
		InputStream inputStream = new FileInputStream(fileXMLName);
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		InputSource is = new InputSource(reader);
		is.setEncoding("UTF-8");

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(is);
		doc.getDocumentElement().normalize();
		NodeList requestsList = doc.getElementsByTagName("request");

		readXMLLine(requestsList, database);

	}

	/** the method creates object from values from XML file*/
	public void readXMLLine(NodeList requestsList, Database database) {
		Request requestObject = null;

		for (int i = 0; i < requestsList.getLength(); i++) {
			Node r = requestsList.item(i);

			if (r.getNodeType() == Node.ELEMENT_NODE) {
				Element request = (Element) r;

				try {
					requestObject = new Request(request.getElementsByTagName("clientId").item(0).getTextContent(),
							request.getElementsByTagName("requestId").item(0).getTextContent(),
							request.getElementsByTagName("name").item(0).getTextContent(),
							request.getElementsByTagName("quantity").item(0).getTextContent(),
							request.getElementsByTagName("price").item(0).getTextContent());

					database.addRequest(requestObject);
				} catch (Exception e) {
					increaseXMLErrors();
				}
			}
		}
	}

	/**the method allows to display a report in the console or write to a file*/
	public void showRaport(Scanner scanner, String output, PrintWriter printwriter) {
		System.out.println("Wybierz:\n1. wyswietlenie w konsoli.\n2. zapis w pliku");
		String choice = scanner.nextLine();
		if (choice.equals("1"))
			System.out.println(output);
		else if (choice.equals("2")) {
			Scanner sc = new Scanner(output).useDelimiter("\n");
			while (sc.hasNext()) {
				printwriter.println(" ");
				printwriter.print(sc.next());
			}
			sc.close();
			printwriter.print(",");
			printwriter.println(" ");
		}
	}

	public int getNumberOfErrorsInCSVFile() {
		return numberOfErrorsInCSVFile;
	}

	public int getNumberOfErrorsInXMLFile() {
		return numberOfErrorsInXMLFile;
	}

}
