package shop_management;

import java.io.IOException;
import java.util.Scanner;

import product_management.ProductManagement;
import user_management.AddUser;
import user_management.User;

public class ApplicationMain {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		boolean keepProgramrunning = true;

//		Login Section 
		System.out.println("Login Here");

		// User name
		System.out.print("Enter Loginname: ");
		String LoginName = sc.nextLine();


		// Password
		System.out.print("Enter Password: ");
		String Password = sc.nextLine();

		if (!AddUser.validateUser(LoginName,Password)) {
			System.out.println("Enter valid Username and Password");
			return;
			
		}
		
		while (keepProgramrunning == true) {
			System.out.println("************************Welcome to Shop Management Section**********************");

			/*
			 * Assignment 1- make Product Section Assignment 2- instead of ArrayList
			 * Directly write data into file Assignment 3- Integrate the Product and user
			 * Management Section
			 */
			System.out.println("Select option below for further steps:");

			System.out.println("1.User Managemet");
			System.out.println("2.Product Managemnet");
			System.out.println("5.Quit");

			System.out.print("Choice: ");

			int option = sc.nextInt();
			if (option == 1) {
				AddUser.AddUser();

			} else if (option == 2) {
				ProductManagement.ProductManagement();

			} else if (option == 5) {
				break;
			}
		}
	}

}
