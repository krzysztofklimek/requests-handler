import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DatabaseTest {

	Database database = new Database();

	@Test
	public void testNumberOfAllRequests() throws Exception {

		Request request1 = new Request("1", "1", "Bu³ka", "1", "10");
		Request request2 = new Request("2", "2", "Bu³ka", "3", "20");

		database.addRequest(request1);
		database.addRequest(request2);

		assertEquals("2", database.numberOfAllRequests());
	}

	@Test
	public void testnumberOfRequestsWhereId() throws Exception {

		Request request1 = new Request("1", "1", "Bu³ka", "1", "10");
		Request request2 = new Request("2", "2", "Bu³ka", "3", "20");

		database.addRequest(request1);
		database.addRequest(request2);
		
		assertEquals("1", database.numberOfRequestsWhereId("1"));
		assertEquals("1", database.numberOfRequestsWhereId("2"));
	}
	
	
	@Test
	public void testAmountOfAllRequests() throws Exception{
		Request request1 = new Request("1", "1", "Bu³ka", "1", "10");
		Request request2 = new Request("2", "2", "Bu³ka", "3", "20");

		database.addRequest(request1);
		database.addRequest(request2);
		
		assertEquals("30.0", database.amountOfAllRequests());
	}
	
	@Test
	public void testAmountOfRequestsWhereId() throws Exception{
		Request request1 = new Request("1", "1", "Bu³ka", "1", "10");
		Request request2 = new Request("2", "2", "Bu³ka", "3", "20");

		database.addRequest(request1);
		database.addRequest(request2);
		
		assertEquals("10.0", database.amountOfRequestsWhereId("1"));
		assertEquals("20.0", database.amountOfRequestsWhereId("2"));
	}
	
	@Test
	public void testAverageAmouOfAllRequests() throws Exception{
		Request request1 = new Request("1", "1", "Bu³ka", "1", "10");
		Request request2 = new Request("2", "2", "Bu³ka", "3", "20");

		database.addRequest(request1);
		database.addRequest(request2);
		
		assertEquals("15.0", database.averageAmountOfAllRequests());
	}
	
	@Test
	public void testAverageAmountOfRequestsWhereId() throws Exception{
		Request request1 = new Request("1", "1", "Bu³ka", "1", "10");
		Request request2 = new Request("2", "2", "Bu³ka", "3", "20");

		database.addRequest(request1);
		database.addRequest(request2);
		
		assertEquals("10.0", database.averageAmountOfRequestsWhereId("1"));
		assertEquals("20.0", database.averageAmountOfRequestsWhereId("2"));
	}
}
















