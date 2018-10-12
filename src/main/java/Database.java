import java.util.ArrayList;
import java.util.List;

public class Database {

	private List<Request> requests;

	public Database() {
		requests = new ArrayList<Request>();
	}

	public void addRequest(Request o) {
		requests.add(o);
	}

	public List<Request> getList() {
		return requests;
	}

	public String numberOfAllRequests() {
		return Integer.toString(requests.size());
	}

	public String numberOfRequestsWhereId(String clientId) {
		int number = 0;
		for (Request x : requests) {
			if (x.getClientId().equals(clientId))
				number++;
		}
		return Integer.toString(number);
	}

	public String amountOfAllRequests() {
		double priceSum = 0;
		for (Request x : requests) {
			priceSum += x.getPrice();
		}
		return Double.toString(priceSum);
	}

	public String amountOfRequestsWhereId(String clientId) {
		double priceSum = 0;
		for (Request x : requests) {
			if (x.getClientId().equals(clientId))
				priceSum += x.getPrice();
		}
		return Double.toString(priceSum);
	}

	public StringBuilder listOfAllRequests() {
		StringBuilder list = new StringBuilder();
		for (Request x : requests) {
			list.append(x.toString() + "\n");
		}
		return list;
	}

	public StringBuilder listOfRequestsWhereId(String clientId) {
		StringBuilder list = new StringBuilder();
		for (Request x : requests) {
			if (x.getClientId().equals(clientId))
				list.append(x.toString() + "\n");
		}
		return list;
	}

	public String averageAmountOfAllRequests() {
		double priceSum = 0;
		for (Request x : requests) {
			priceSum += x.getPrice();
		}
		double requestsNumber = (double) requests.size();
		double average = priceSum / requestsNumber;

		return Double.toString(average);
	}

	public String averageAmountOfRequestsWhereId(String clientId) {
		double priceSum = 0;
		for (Request x : requests) {
			if (x.getClientId().equals(clientId))
				priceSum += x.getPrice();
		}
		int number = 0;
		for (Request x : requests) {
			if (x.getClientId().equals(clientId))
				number++;
		}
		double requestsNumber = (double) number;
		double average = priceSum / requestsNumber;

		return Double.toString(average);
	}

}
