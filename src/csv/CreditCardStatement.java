package csv;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;


public class CreditCardStatement {

	public static void main(String[] args) {
		//Creating the array list
		List<String[]> data = new ArrayList<String[]>();
		
		//Declaring variables
		String dataRow;
		double balance=0;
		double credit=0;
		double debit=0;
		
		//Defining the file-path for the csv file
		String filename = "C:\\Users\\Parikshit\\Desktop\\SHALIK\\JAVA TRAINING\\Files\\statements.csv";
		 
		//Creating the file in Java
		File file = new File(filename);
		 
		 try {
			//Reading the file 
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while((dataRow=br.readLine())!=null) {
				//Parsing the file according to commas
				String[] line = dataRow.split(",");
				//Adding the file values to the array-list collection
				data.add(line);
			}
			//Closing the file
			br.close();
		
		//Exception handling	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("--CREDIT CARD STATEMENT--\n");
		 for(String[] row: data) {
						
				//Extracting fee amount from CSV file 
			 	System.out.print(row[1].substring(0)+": ");
				 if(row[1].substring(0).equals("FEE")) {
					 System.out.println(row[3]);
					 double amount = Double.parseDouble(row[3]);
					 balance=balance+amount;
				 }
				 //Extracting credit amount from CSV file
				 if(row[1].substring(0).equals("CREDIT")) {
					 System.out.println(row[3]);
					 double amount = Double.parseDouble(row[3]);
					 credit = balance;
					 balance=balance+amount;
				 }
				 //Extracting debit amount from CSV file
				 else if(row[1].substring(0).equals("DEBIT")) {
					 System.out.println(row[3]);
					 double amount = Double.parseDouble(row[3]);
					 debit=balance;
					 balance=balance-amount;
				 }
			 }
			 System.out.println("");
			 //Assign calculated running balance
			 balance =Double.parseDouble(new DecimalFormat("#.##").format(balance));
			 
			 //Displaying appropriate notices for balance payment
			 // 1. Balance overdue
			 if(balance>0) {
				 System.out.println("Balance amount: $"+balance+" overdue");
				 double fee = balance*0.1;
				 System.out.println("10% fee levied: $"+fee);
				 balance=balance+fee;
				 System.out.println("Final outstanding balnce due: $"+balance);
				 System.out.println("***Kindly pay the balance in time else necesarry action will be taken***");
				 
			 }
			 // 2. Balance cleared
			 else if(balance==0) {
				 System.out.println("No balance due. Thank you");
			 }
			 // 3. Over-payment of balance
			 else if(balance<0) {
				 System.out.println("Thank you for your payment");
				 double over=debit-credit; 
				 over = Double.parseDouble(new DecimalFormat("#.##").format(over));
				 System.out.println("You have overpaid an amount of: $"+over);
			 }
		 }
		 
	}

