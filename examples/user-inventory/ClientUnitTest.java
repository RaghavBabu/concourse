import com.cinchapi.concourse.Concourse;

/**
 * class ClientUnitTest
 * This class contains test cases to check the functionality of each method
 * which manipulates the ConcourseDB.
 * @author raghavbabu
 * Date :05/27/2016
 */
public class ClientUnitTest {


	private static final Concourse concourse = Concourse.connect();

	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {

		ClientInterfaceImpl client = new ClientInterfaceImpl(concourse);

		try{

			//test to register a user.
			User user1 = new User(1,"admin","admin123","admin@gmail.com","India","1634537363");
			User user2 = new User(2,"Brad","pitt","pitt@gmail.com","US","443754848");
			User user3 = new User(3,"admin","admin123","admin@gmail.com","India","1634537363");

			//register user.
			if (client.register(user1) )
				System.out.println("*** User "+user1.getUsername()+" successfully registered!! ***");

			if (client.register(user2) )
				System.out.println("*** User "+user2.getUsername()+" successfully registered!! ***");
			
			//test for registered user.
			long recordId = client.duplicateUserName(user3.getUsername());

			//check for duplicate userName.
			if(recordId == -1){
				System.out.println("*** UserName already exists, choose a different name ***");
			}


			//test to login and invalid user.
			if(client.login("rahul", "dravid") ){
				System.out.println("*** User rahul successfully logged in!! ***");
			}

			//test to login and valid user.
			if(client.login("admin", "admin123") ){
				System.out.println("*** User admin successfully logged in!! ***");
			}else{
				System.out.println("*** Login Failure ***");
			}

			//removing admin from database.	
			if (client.unregister("admin") )
				System.out.println("*** User admin successfully unregistered!! ***");
			
			//test to list out all records.
			client.listAllRecords();
		}
		finally{
			
			//removes all records from environment.
			client.clearInventory();
		}

	}
}
