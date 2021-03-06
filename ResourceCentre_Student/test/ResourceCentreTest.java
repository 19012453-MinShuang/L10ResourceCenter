import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResourceCentreTest {
	private Camcorder cc1;
	private Camcorder cc2;
	private Chromebook cb1;
	private Chromebook cb2;
	
	private ArrayList<Camcorder> camcorderList;
	private ArrayList<Chromebook> chromebookList;
	
	public ResourceCentreTest() {
		super();
	}
	
	@Before
	public void setUp() throws Exception {
		// prepare test data
		cc1 = new Camcorder("CC0011", "Nikon HDSLR", 40);
		cc2 = new Camcorder("CC0012", "Sony DSC-RX100M7", 20);
		cb1 = new Chromebook("CB0011", "My Google Chromebook 1st", "Mac OS");
		cb2 = new Chromebook("CB0012", "SAMSUNG Chromebook 4+", "Win 10");

		camcorderList= new ArrayList<Camcorder>();
		chromebookList= new ArrayList<Chromebook>();
	}

	
	@Test
	public void addCamcorderTest() {
		// Item list is not null, so that can add a new item
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);
		
		//Given an empty list, after adding 1 item, the size of the list is 1
		ResourceCentre.addCamcorder(camcorderList, cc1);		
		assertEquals("Test if that Camcorder arraylist size is 1?", 1, camcorderList.size());
		
		///The item just added is as same as the first item of the list
		assertSame("Test that Camcorder is added same as 1st item of the list?", cc1, camcorderList.get(0));
		
		//Add another item. test The size of the list is 2?
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test that Camcorder arraylist size is 2?",2, camcorderList.size());
	}
	@Test
	public void addChromebookTest() {
		//fail("Not yet implemented");
		// write your code here
		//Adding a blank object into the list(Error)
		cb2=new Chromebook("","","");
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertSame("Test if the program add the blank object", cb2, chromebookList.get(0));
		
		///Adding 2 item into the null list so list become 2 item inside(Normal)
		cb2 = new Chromebook("CB0012", "SAMSUNG Chromebook 4+", "Win 10");
		chromebookList.clear();
		ResourceCentre.addChromebook(chromebookList, cb1);
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test to see if the total number of item in the list becomes 2", 2, chromebookList.size());
	}
	
	@Test
	public void retrieveAllCamcorderTest() {
		// Test if Item list is not null but empty, so that can add a new item
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);
		
		//test if the list of camcorders retrieved from the SourceCentre is empty
				String allCamcorder= ResourceCentre.retrieveAllCamcorder(camcorderList);
				String testOutput = "";
				assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);
				
		//Given an empty list, after adding 2 items, test if the size of the list is 2
		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test if that Camcorder arraylist size is 2?", 2, camcorderList.size());
		
		//test if the expected output string same as the list of camcorders retrieved from the SourceCentre
		allCamcorder= ResourceCentre.retrieveAllCamcorder(camcorderList);

		testOutput = String.format("%-10s %-30s %-10s %-10s %-20d\n","CC0011", "Nikon HDSLR", "Yes", "", 40);
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20d\n","CC0012", "Sony DSC-RX100M7", "Yes", "", 20);
	
		assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);
		
	}
	@Test
	public void retrieveAllChromebookTest() {
		//fail("Not yet implemented");
		// write your code here
		// Normal test
		//test if the expected output string same as the list of chromebook retrieved from the SourceCentre
		String allChromebook = ResourceCentre.retrieveAllChromebook(chromebookList);

		String testOutput = String.format("%-10s %-30s %-10s %-10s %-20d\n","CB0011", "Nikon HDSLR", "Yes", "", 40);
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20d\n","CB0012", "Sony DSC-RX100M7", "Yes", "", 20);

		assertEquals("Check that ViewAllChromebooklist", testOutput, allChromebook);

		// Error test
		// Error with different variable output
		String allChromebook1 = ResourceCentre.retrieveAllChromebook(chromebookList);

		String testOutput1 = String.format("%-10s %-30s %-10s %-10s %-20d\n","CB0011", "Nikon HDSLR", "Yes", "", 40);
		testOutput1 += String.format("%-10s %-30s %-10s %-10s %-20d\n","CB0012", "Sony DSC-RX100M7", "Yes", "", 20);
		
		assertEquals("Check that ViewAllChromebooklist", testOutput, allChromebook);
		
	}

	@Test
	public void doLoanCamcorderTest() {
		//fail("Not yet implemented");
		// write your code here
		//get due Date by adding 14days to today
		String twoweeksFromToday = LocalDate.now().plusDays(14).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		// To make sure camcorderlist contains nothing
		assertEquals(0,camcorderList.size());
		// add cc1 and cc2 into the camcorderlist , so that it has them now and they can be loaned
		camcorderList.add(cc1);
		camcorderList.add(cc2);
		assertEquals(2,camcorderList.size());
		// normal condition  to test can the staff loan cc1
		boolean result1 = ResourceCentre.doLoanCamcorder(camcorderList, cc1.getAssetTag(), twoweeksFromToday );
		assertTrue("Test staff can loan out cc1 again ", result1);
		
		//error condition to test will the unavailable asset be loan? Should be false
		boolean result2 =ResourceCentre.doLoanCamcorder(camcorderList," cc999999999999", twoweeksFromToday );
		assertFalse("Test staff can loan out unvailable  asset " , result2);
		
		
	}
	
	@Test
	public void doLoanChromebookTest() {
		//fail("Not yet implemented");
		// write your code here
		//get due Date by adding 14days to today
		String twoweeksFromToday = LocalDate.now().plusDays(14).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		// To make sure chromebooklist contains nothing
		assertEquals(0,camcorderList.size());
		// add cb1 and cb2 into the chromebookList , so that it has them now and they can be loaned
		chromebookList.add(cb1);
		chromebookList.add(cb2);
		assertEquals(2,chromebookList.size());
		// normal condition  to test can the staff loan cb1
		boolean result3 = ResourceCentre.doLoanChromebook(chromebookList, cb1.getAssetTag(), twoweeksFromToday );
		assertTrue("Test staff can loan out cb1 again ", result3);
		
		//error condition to test will the unavailable asset be loan? Should be false
		boolean result4 =ResourceCentre.doLoanChromebook(chromebookList," cb9999999999", twoweeksFromToday );
		assertFalse("Test staff can loan out unvailable  asset " , result4);
		
		
	}
	
	@Test
	public void doReturnCamcorderTest() {
		//fail("Not yet implemented");
		// write your code here
		
	}
	@Test
	public void doReturnChromebookTest() {
		//fail("Not yet implemented");
		// write your code here
		
		//norm
		ResourceCentre.addChromebook(chromebookList, cb1); 
		cb1.setIsAvailable(true);
		assertTrue("Test if return item changes availability status", cb1.getIsAvailable()); 
		
		//error
		boolean check = ResourceCentre.doReturnChromebook (chromebookList,"CB0013"); 
		assertFalse("Test  if  items in list are returned", check) ; 
		 
	}
	
	@After
	public void tearDown() throws Exception {
		cc1 = null;
		cc2 = null;
		cb1 = null;
		cb2 = null;
		camcorderList = null;
		chromebookList = null;

	}

}
