package edu.uwpce.bzbookstore.test;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uwpce.bzbookstore.BZUserInfo;
import edu.uwpce.bzbookstore.BZUsersManager;
import edu.uwpce.bzbookstore.BZUsersManagerImpl;

public class BZUsersManagerImplTests {
	
	private static final Logger logger = LoggerFactory.getLogger(BZUsersManagerImplTests.class);


	private static int USER_ID = 200;

	private static String FIRST_NAME = "Bill";
	private static String LAST_NAME = "Zude";
	private static String USER_NAME = "buzz";
	private static String PASSWORD = "pwd";
	private static String PHONE_NUMBER_1 = "206-574-8090";
	private static String PHONE_NUMBER_2 = "206-555-1313";

	
/*	"addresses": {
	"primary": {
	"streetAddress": "12546 Fremont Ave N",
	"city": "Seattle",
	"state": "WA",
	"zipcode": "98133",
	"addressType": "primary"
	},
	"secondary": {
	"streetAddress": "13245 Aurora Ave",
	"city": "Portland",
	"state": "OH",
	"zipcode": "45132",
	"addressType": "secondary"
	}
	},
	"cards": {
	"primary": {
	"cardNumber": "1234567890987654",
	"cardExpirationDate": "May 2017",
	"cardType": "primary",
	"cardCode": "999",
	"cardOwnerName": "Bob Smith",
	"cardVendor": "VISA",
	"cardOwnerId": 100
*/
	
	private BZUsersManager usersManager;
	private Map<Integer, BZUserInfo> users;
	
	@Before
	public void setUp() throws Exception {
		usersManager = new BZUsersManagerImpl();
		users = new ConcurrentHashMap<Integer, BZUserInfo>();
	
		BZUserInfo user = new BZUserInfo();
		user.setUserId(USER_ID);
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		user.setUserName(USER_NAME);
		user.setPassword(PASSWORD);
		user.setPhoneNumber1(PHONE_NUMBER_1);
		user.setPhoneNumber2(PHONE_NUMBER_2);
		users.put(USER_ID, user);

		logger.info("Added user to users map with id: " + user.getUserId());
		usersManager.setUsers(users);
	}

	@Test
	public void testGetUsersWithNoUsers() {
		BZUsersManager localUserManager = new BZUsersManagerImpl();
		Map<Integer, BZUserInfo> localUsers = localUserManager.getUsers();
		assertNull(localUsers);
	}

	@Test
	public void testGetUsers() {
		Map<Integer, BZUserInfo> localUsers = usersManager.getUsers();
		assertNotNull(localUsers);
		assertEquals(users.size(), localUsers.size());
		
		// We'll duplicate some of the book and author tests here just
		// to make sure we got the SAME list back.
		BZUserInfo localUser = localUsers.get(USER_ID);
		assertEquals(FIRST_NAME, localUser.getFirstName());
		assertEquals(LAST_NAME, localUser.getLastName());
		assertEquals(USER_NAME, localUser.getUserName());
		assertEquals(PASSWORD, localUser.getPassword());
		assertEquals(PHONE_NUMBER_1, localUser.getPhoneNumber1());
		assertEquals(PHONE_NUMBER_2, localUser.getPhoneNumber2());

	}
}
