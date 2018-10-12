import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void raport(Database database, PrintWriter printwriter, Handler handler) {

		boolean end = false;

		System.out.print("\n\nGeneruj raport:\n" + "a. Ilosc zamowien lacznie,\n"
				+ "b. Ilosc zamowien do klienta o wskazanym identyfikatorze,\n" + "c. Laczna kwota zamowien,\n"
				+ "d. Laczna kwota zamowien do klienta o wskazanym identyfikatorze,\n"
				+ "e. Lista wszystkich zamowien,\n" + "f. Lista zamowien do klienta o wskazanym identyfikatorze,\n"
				+ "g. Srednia wartosc zamowienia,\n"
				+ "h. Srednia wartosc zamowienia do klienta o wskazanym identyfikatorze,\n"
				+ "i. Zakoncz program.\n\n");

		String choiceString;
		Scanner in = new Scanner(System.in);
		choiceString = in.nextLine();
		String report;

		if (choiceString.equals("a")) {
			report = "Ilosc wszystkich zamowien: " + database.numberOfAllRequests();
			handler.showRaport(in, report, printwriter);
		} else if (choiceString.equals("b")) {
			System.out.println("Podaj id klienta:");
			String id = in.nextLine();
			report = "Ilosc zamowien do klienta " + id + ": " + database.numberOfRequestsWhereId(id);
			handler.showRaport(in, report, printwriter);
		} else if (choiceString.equals("c")) {
			report = "Laczna kwota zamowien: " + database.amountOfAllRequests();
			handler.showRaport(in, report, printwriter);
		} else if (choiceString.equals("d")) {
			System.out.println("Podaj id klienta:");
			String id = in.nextLine();
			report = "Laczna kwota zamowien do klienta " + id + ": " + database.amountOfRequestsWhereId(id);
			handler.showRaport(in, report, printwriter);
		} else if (choiceString.equals("e")) {
			report = "Lista wszystkich zamowien:\n" + database.listOfAllRequests();
			handler.showRaport(in, report, printwriter);
		} else if (choiceString.equals("f")) {
			System.out.println("Podaj id klienta:");
			String id = in.nextLine();
			report = "Lista zamowien do klienta " + id + ":\n" + database.listOfRequestsWhereId(id);
			handler.showRaport(in, report, printwriter);
		} else if (choiceString.equals("g")) {
			report = "Srednia wartosc zamowienia: " + database.averageAmountOfAllRequests();
			handler.showRaport(in, report, printwriter);
		} else if (choiceString.equals("h")) {
			System.out.println("Podaj id klienta:");
			String id = in.nextLine();
			report = "Srednia wartosc zamowienia do klienta " + id + ": " + database.averageAmountOfRequestsWhereId(id);
			handler.showRaport(in, report, printwriter);
		} else {
			end = true;
		}

		if (!end)
			raport(database, printwriter, handler);

		in.close();
	}

	public static void main(String[] args) {

		Database database = new Database();
		Handler handler = new Handler();

		for (String x : args) {

			if (handler.ifXML(x)) {
				try {
					handler.addFromXMLFile(x, database);
				} catch (Exception e) {
					System.out.println("Blad, zly format/nazwa pliku XML");
				} finally {
					System.out.println("Liczba bledow w pliku " + x + " = " + handler.getNumberOfErrorsInXMLFile());
				}
			} else {
				try {
					handler.addFromCSVFile(x, "\n", ",", database);
				} catch (FileNotFoundException e) {
					System.out.println("Blad, zly format/nazwa pliku CSV");
				} finally {
					System.out.println("Liczba bledow w pliku " + x + " = " + handler.getNumberOfErrorsInCSVFile());
				}
			}
		}

		try {
			PrintWriter printWriter = new PrintWriter("raport.txt");
			raport(database, printWriter, handler);
			printWriter.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}
}
