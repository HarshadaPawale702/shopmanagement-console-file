package product_management;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductManagement {
	
	static ArrayList<Product> al=new ArrayList<Product>(); //Creating user Array to store multiple data.

	
	public static void ProductManagement() throws IOException {
		
		fileLoadDataFromFileToArrayList() ;
		
		Scanner sc=new Scanner(System.in);
		
	    boolean keepProgramrunning=true;
	    
	    while(keepProgramrunning==true) {
	    	System.out.println("************************Welcome to Product Management**********************");
	    	
	    	System.out.println("Select option below for further steps:");
	    	System.out.println("1.Add Product");
	    	System.out.println("2.Edit Product");
	    	System.out.println("3.Delete Product");
	    	System.out.println("4.Search Product");
	    	System.out.println("5.Quit");
	    	
	    	System.out.print("Choice: ");
	    	
	    	int option=sc.nextInt();
	    	
	    	//Here we have created separate userOption class for options value e.g ADD_USER=1 . 
	 	    if(option == ProductOption.QUIT) {
	 	    	
	 	    	
	 			File file = new File("C:/Users/sanja/eclipse-workspace/OrangeHRM/src/product_management/Product.csv");
	 			FileWriter fileWriter=new FileWriter(file,true);
	 			BufferedWriter bufferWriter=new BufferedWriter(fileWriter);
	 			
	 			
	 			for(Product product:al) {
//	 				
	 				bufferWriter.write(product.productName+","+product.brandName+","+product.price+"\n");	
	 			}
	 			
	 			bufferWriter.close(); 
	 			fileWriter.close();  
	 			file=null;   
	 	    	
	 	    	System.out.println("Exiting from the Program");
	 	    	keepProgramrunning=false;
	 	    	
	 	    }else if(option == ProductOption.ADD_PRODUCT) {
	 	    	addProduct();
	 	    	System.out.println("\n");
	 	    }else if(option == ProductOption.SEARCH_PRODUCT) {
	 	    	System.out.println("Enter the Product name that you waant to search: ");
	 	    	sc.nextLine();
	 	    	String sn=sc.nextLine();
	 	    	searchProduct(sn);
	 	    }else if(option == ProductOption.DELETE_PRODUCT) {
	 	    	System.out.println("Enter Product Name that you want to delete: ");
	 	    	sc.nextLine();
	 	    	String del=sc.nextLine();
	 	    	deleteProduct(del);
	 	    }else if(option == ProductOption.EDIT_PRODUCT) {
				System.out.println("Enter the Product Name that you want to edit...");
				sc.nextLine();
				String edit=sc.nextLine();
				editProductInfo(edit);
	 	    }
	 	    
	    }
	    System.out.println("\n");
	  
	}

	
	//***********************addUser() function*******************************************************************************
	public static void addProduct() {
		Scanner sc= new Scanner(System.in);
		Product userObject=new Product();
		
		System.out.print("Enter the Product Name:");
		userObject.productName=sc.nextLine();
		
		System.out.print("Enter the Brand Name:");
		userObject.brandName=sc.nextLine();
		
		
		System.out.print("Enter the Price:");
		userObject.price=sc.nextLine();
	
		System.out.println("Product Name: "+" "+userObject.productName);
		System.out.println("Brand Name: "+" "+userObject.brandName);
		System.out.println("Price : "+" "+userObject.price);

		al.add(userObject);
	
	}
	
	//*********************************Search User Function******************************************************************************
	public static void searchProduct(String ProductName) {
		for(Product product : al) {
			if(product.productName.equalsIgnoreCase(ProductName)) {
				System.out.println("Product Name: "+" "+product.productName);
				System.out.println("Brand Name"+" "+product.brandName);
				System.out.println("Price: "+" "+product.price);
				return;
			}
		}
		System.out.println("Product Not Found...");
	}
	
	//**************************Function to display the stored data*****************************
	public static void fileLoadDataFromFileToArrayList() throws IOException {
		File f_obj=new File("C:/Users/sanja/eclipse-workspace/OrangeHRM/src/product_management/Product.csv");  //f_obj stores path of file
		FileReader fr=new FileReader(f_obj);		//fr Reads data  of file from HDD 
		BufferedReader br=new BufferedReader(fr);	//store in RAM i.e buffered data
		String line="";		//Read data line by line 
		while((line=br.readLine()) != null) {
			Product user=new Product();
			String [] userDataArray=line.split(",");
			
			if(userDataArray.length>3) {
			user.productName=userDataArray[0];
			user.brandName=userDataArray[1];
			user.price=userDataArray[2];
			
			
			al.add(user); //Last line do not come as null bcauz of this.
			}
			
		}
		br.close();
		fr.close();
		f_obj=null;
	}
	
	//*********************************Edit User Function**********************************************
	public static void editProductInfo(String ProductName) {
		for(Product user:al) {  //Stores every value of aL in user iteratively.
			//but actually user do not stores value of aL, it just points to the address of values in aL.
			//hence when we take the new data, there is change in aL.
			//user has again has 4 variables.
			if(user.productName.equalsIgnoreCase(ProductName)) {
				Scanner scanner= new Scanner(System.in);
				System.out.println("Editing Product information"+user.productName);
				
				
				System.out.println("New Product Name:");
				user.productName=scanner.nextLine();
				
				System.out.print("New Brand Name: ");
				user.brandName=scanner.nextLine();
				
				System.out.print("New Price: ");
				user.price=scanner.nextLine();
			
			}
		}
		
	}
	
	//*********************************Delete User Function**********************************************
	
	public static void deleteProduct(String ProductName) {
		Iterator<Product>userItr=al.iterator();
		
		while(userItr.hasNext()) {
			Product user=userItr.next();
			if(user.productName.equalsIgnoreCase(ProductName)) {
				userItr.remove();
				System.out.println("Product"+ProductName+" "+ "has been deleted");
				break;
			}
		}
		
	}
	

	
}

