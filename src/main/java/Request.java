import java.io.IOException;

public class Request {

	private String clientId;
	private long requestId;
	private String name;
	private int quantity;
	private double price;

	public Request(String clientId, String requestId, String name, String quantity, String price) throws Exception {

		this.clientId = clientId;
		this.requestId = Long.valueOf(requestId);
		this.name = name;
		this.quantity = Integer.valueOf(quantity);
		this.price = Double.valueOf(price);

		if (clientId.length() > 6 || clientId.contains(" ") || name.length() > 255)
			throw new Exception();
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String toString() {
		return "Client id: " + clientId + ". Request id: " + requestId + ". Name: " + name + ". Quantity: " + quantity
				+ ". Price: " + price;

	}

}
