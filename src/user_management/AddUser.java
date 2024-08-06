package user_management;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class AddUser {

	static ArrayList<User> al = new ArrayList<User>(); // Creating user Array to store multiple data.

	static {
		try {
			fileLoadDataFromFileToArrayList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void AddUser() throws IOException {

//		fileLoadDataFromFileToArrayList();   -----> we have write this function separately,because it does not called until 
//		the AddUser() in invoked,and this function is not invoked until we login, hence declare this function outside the AddUser().
//		to search the login name and password in the file.
		Scanner sc = new Scanner(System.in);

		boolean keepProgramrunning = true;

		while (keepProgramrunning == true) {
			System.out.println("************************Welcome to User Management Section**********************");

			System.out.println("Select option below for further steps:");
			System.out.println("1.Add User");
			System.out.println("2.Edit User");
			System.out.println("3.Delete User");
			System.out.println("4.Search User");
			System.out.println("5.Quit");

			System.out.print("Choice: ");

			int option = sc.nextInt();

			// Here we have created separate userOption class for options value e.g
			// ADD_USER=1 .
			if (option == userOption.QUIT) {

				File file = new File("C:/Users/sanja/eclipse-workspace/OrangeHRM/src/user_management/User.csv");
				FileWriter fileWriter = new FileWriter(file, true);
				BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

				for (User user : al) {

					bufferWriter.write(user.LoginName + ","+user.passwd+","+user.userName+ ","+user.Cpass + "\n");
				}

				bufferWriter.close();
				fileWriter.close();
				file = null;

				System.out.println("Exiting from the Program");
				keepProgramrunning = false;

			} else if (option == userOption.ADD_USER) {
				addUser();
				System.out.println("\n");
			} else if (option == userOption.SEARCH_USER) {
				System.out.println("Enter the name that you waant to search: ");
				sc.nextLine();
				String sn = sc.nextLine();
				searchUser(sn);
			} else if (option == userOption.DELETE_USER) {
				System.out.println("Enter UserName that you want to delete: ");
				sc.nextLine();
				String del = sc.nextLine();
				deleteUser(del);
			} else if (option == userOption.EDIT_USER) {
				System.out.println("Enter the UserName that you want to edit...");
				sc.nextLine();
				String edit = sc.nextLine();
				editUser(edit);
			}

		}
	
	}

	// ***********************Add User Function**********************************************
	public static void addUser() {
		Scanner sc = new Scanner(System.in);
		User userObject = new User();

		System.out.print("Enter the UserName:");
		userObject.userName = sc.nextLine();

		System.out.print("Enter the LoginName:");
		userObject.LoginName = sc.nextLine();

		System.out.print("Enter the Password:");
		userObject.passwd = sc.nextLine();

		System.out.print("Confirm your Password:");
		userObject.Cpass = sc.nextLine();

//		System.out.print("Enter the User_role:");
//		userObject.userRole=sc.nextLine(); //not taking input for user roll

		System.out.println("User Name is : " + " " + userObject.userName);
		System.out.println("Login Name is : " + " " + userObject.LoginName);
		System.out.println("New Password is : " + " " + userObject.passwd);
		System.out.println("New Confirm Password is : " + " " + userObject.Cpass);
//		System.out.println("Your role is : "+" "+userObject.userRole);

		al.add(userObject);

	}

	// *********************************Search User Function******************************************************************************
	public static void searchUser(String userName) {
		for (User user : al) {
			if (user.userName.equalsIgnoreCase(userName)) {
				System.out.println("UserName: " + " " + user.userName);
				System.out.println("LoginName: " + " " + user.LoginName);
				System.out.println("Password: " + " " + user.passwd);
				System.out.println("Confirm Password: " + " " + user.Cpass);
				return;
			}
		}
		System.out.println("User Not Found...");
	}

	// **************************Function to display the stored data*****************************
	public static void fileLoadDataFromFileToArrayList() throws IOException {
		File f_obj = new File("C:/Users/sanja/eclipse-workspace/OrangeHRM/src/user_management/User.csv"); // f_obj stores path of file
			FileReader fr = new FileReader(f_obj); // fr Reads data of file from HDD
			BufferedReader br = new BufferedReader(fr); // store in RAM i.e buffered data
			String line = ""; // Read data line by line
			while ((line = br.readLine()) != null) {
				User user = new User();
				String[] userDataArray = line.split(",");

				if (userDataArray.length > 3) {
					user.LoginName = userDataArray[0];
					user.passwd = userDataArray[1];
					user.userName = userDataArray[2];
					user.Cpass = userDataArray[3];

					al.add(user); // Last line do not come as null bcauz of this.
				}
				
			}
			br.close();
			fr.close();
			f_obj = null;
		} 

	// ***********************************Validate User Function***********************************************

	public static boolean validateUser(String LoginName, String password) {
		Iterator<User> userIterator = al.iterator();
		while (userIterator.hasNext()) {
			User user = userIterator.next();
			if (user.LoginName.equalsIgnoreCase(LoginName) && user.passwd.equalsIgnoreCase(password)) {
				return true;
			}
		}
		return false;
	}

	// *********************************Edit User Function**************************************************
	public static void editUser(String userName) {
		for (User user : al) { // Stores every value of aL in user iteratively.
			// but actually user do not stores value of aL, it just points to the address of
			// values in aL.
			// hence when we take the new data, there is change in aL.
			// user has again has 4 variables.
			if (user.userName.equalsIgnoreCase(userName)) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Editing user " + user.userName);

				System.out.println("New UserName");
				user.userName = scanner.nextLine();

				System.out.print("New LoginName: ");

				user.LoginName = scanner.nextLine();
				System.out.print("New Password: ");
				user.passwd = scanner.nextLine();

				System.out.print("New Confirm Password: ");
				user.Cpass = scanner.nextLine();

			}
		}

	}

	// *********************************Delete UserFunction*******************************************************

	public static void deleteUser(String userName) {
		Iterator<User> userItr = al.iterator();

		while (userItr.hasNext()) {
			User user = userItr.next();
			if (user.userName.equalsIgnoreCase(userName)) {
				userItr.remove();
				System.out.println("User " + userName + " " + "has been deleted");
				break;
			}
		}

	}

}
