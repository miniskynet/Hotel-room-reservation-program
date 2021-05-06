//import java utility and java input output
import java.util.*;
import java.io.*;

public class array_only {
	//create scanner named input 
	public static Scanner input = new Scanner(System.in);
	
	//method to view all customer information 
	public static void view(String[] hotel_ ,int[] num_guests_,String[] fname_,
			String[] sname_, String[] credit_) {
		System.out.println();
		//iterate through each array and print parallel elements
		for (int i = 0; i < 8; i++ )
		{
			System.out.println("room " + i + " occupied by " + hotel_[i]);
			System.out.println("Customers full name is: "+fname_[i]+" "+sname_[i]);
			System.out.println("Customers credit card number is: "+credit_[i]);
			System.out.println("Number of guests in room: "+num_guests_[i]);
			System.out.println();
		}System.out.println("\n");
	}
	
	//method to add new customers
	public static int add (int roomNum_,String roomName_, String[] hotel_,int[] num_guests_,String[] fname_,
			String[] sname_, String[] credit_) {
		int num_of_guests = 0,roomNum_temp=9;	
		//create a try catch block inside a do while to make sure only integers are entered, 
		//and exit the loop once a value not larger than 8 is entered 
		do {
		    try {
		    	System.out.println("Enter room number (0-8) or 8 to stop:" );
		    	roomNum_temp= input.nextInt();
		    } catch (InputMismatchException e) {
		        System.out.print("Invalid\n");
		    }
		    input.nextLine();
		} while (roomNum_temp > 8);
		roomNum_ = roomNum_temp;
		//if user enters 8 return the value to exit the program
			if (roomNum_==8) {
				return roomNum_;
			//if user enters any other integer proceed to taking information regarding the customer
			}else {
				System.out.println("Enter name for room " + roomNum_ +" :" );
				roomName_ = input.next();
				//make sure user enters a number for the amount of guests with a try catch block
				do {
				    try {
				    	System.out.println("Enter number of guests for room " + roomNum_ +" :" );
				    	num_of_guests= input.nextInt();
				    } catch (InputMismatchException e) {
				        System.out.print("Please enter number\n");
				    }
				    input.nextLine();
				} while (num_of_guests <= 0);
				hotel_[roomNum_] = roomName_ ;
				num_guests_[roomNum_] = num_of_guests;
				//take additional information
				System.out.println("\nAdditional Information\n");
				System.out.println("Enter first name of customer:");
				fname_[roomNum_]=input.next();
				System.out.println("Enter surname of customer:");
				sname_[roomNum_]=input.next();
				System.out.println("Enter credit card number of customer:");
				credit_[roomNum_] = input.next();				
			}
			return roomNum_;
				
	}
	//method to view empty rooms
	public static void empty(String[] hotel_) {
		System.out.println();
		//iterate through the array and print elements that are equal to initialized values
		for (int i = 0; i < 8; i++ )
		{	
			if (hotel_[i].equals("e")) {
				System.out.println("room " + i + " is empty");
			}
		}System.out.println();
	}
	
	//method to delete user selected customer information
	public static void delete(String[] hotel_,int[] num_guests_,String[] fname_,
			String[] sname_, String[] credit_) {
		int roomNum_,roomNum_temp=9;
		//make sure a valid integer is entered with a try and catch block
		do {
		    try {
		    	System.out.print("Enter room number to delete customer: ");
				roomNum_temp = input.nextInt();
		    } catch (InputMismatchException e) {
		        System.out.print("Invalid\n");
		        
		    }
		    input.nextLine();
		} while (roomNum_temp>8);
		//once user selects the desired room number, 
		//set all the parallel elements to the initial values
		roomNum_ = roomNum_temp;
		hotel_[roomNum_] = "e";
		num_guests_[roomNum_] = 0;
		fname_[roomNum_] = "e";
		sname_[roomNum_] = "e";
		credit_[roomNum_] = "e";
	}
	
	//method to find a room based on customers name
	public static void find(String[] hotel_) {
		System.out.println("\nEnter name of customer: ");
		int in_room = 9;
		String name = input.next();
		//iterate through the array and print the room number,
		//if the desired customer name is present and it does not equal,
		//to the initial value print the room name
		for(int i=0;i<8;i++) {
			if (hotel_[i].equals(name)&&(!hotel_[i].equals("e"))) {
				in_room = i;
				System.out.println("Selected customer is in room "+i);
				System.out.println();
		
		}
	//if the user name is not present in the array, alert the user
	}if(in_room==9) {
		System.out.println("The name you are looking for cannot be found.\n");
	}
	}
	
	//method to store data
	public static void store(String[] hotel_,int[] num_guests_,String[] fname_,
			String[] sname_, String[] credit_) {
		//create a try catch block, to print an error message if there is a failure
		//at creating the text file
		try {
			//create new text file
			File create = new File("Hotelinfo.txt");
			//if new file is created alert the user, else alert the user 
			//that the file already exists
			if(create.createNewFile()) {
				System.out.println("File created: " + create.getName());
			}else {
				System.out.println("File already exists. ");
			}
		}catch(IOException e) {
			System.out.println("An error occurred. ");
			e.printStackTrace();
			//create a try catch block, to print an error message if there is a failure
			//at writing to the file
		}try {
			//create a FileWriter object and store the customer details(parallel array elements included)
			FileWriter store = new FileWriter("Hotelinfo.txt");
			for(int i=0;i<hotel_.length;i++) {
				store.write(i+" "+hotel_[i]+" "+num_guests_[i]+" "
						+fname_[i]+" "+sname_[i]+" "
						+credit_[i]+"\n");
			}
			//close the FileWriter object and print if the process was successful or not
			store.close();
			System.out.println("Data stored");			
		}catch (IOException e) {
			System.out.println("An error occurred. ");
			e.printStackTrace();
		}
		
	}
	
	//method to load data from text file
	public static void load(String[] hotel_,int[] num_guests_,String[] fname_,
			String[] sname_, String[] credit_) {
		//read the stored data line by line, using File object 
		//and let the user know if an error occurs
		try {
			File read_object = new File("Hotelinfo.txt");
			Scanner sc = new Scanner(read_object);
			while(sc.hasNextLine()) {
				String data = sc.nextLine();
				String[] data_arr = data.split(" ");
				hotel_[Integer.parseInt(data_arr[0])] = data_arr[1];
				num_guests_[Integer.parseInt(data_arr[0])] = Integer.parseInt(data_arr[2]);
				fname_[Integer.parseInt(data_arr[0])] = data_arr[3];
				sname_[Integer.parseInt(data_arr[0])] = data_arr[4];
				credit_[Integer.parseInt(data_arr[0])]= data_arr[5];
			}//alert the user once the loading process is complete
			System.out.println("\nSuccessfully loaded!\n");
			sc.close();
		}catch(FileNotFoundException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	
	//method to order the customer names in alphabetical order
	public static void order(String[] hotel_) {
		//create  a new array list
		ArrayList<String> new_arr = new ArrayList<String>();
		//iterate through the array and add all elements
		//to the array-list which are not equal to the initial value
		for(int i=0;i<8;i++) {
			if(hotel_[i].equals("e")) {
				continue;
			}else {
				new_arr.add(hotel_[i]);
			}
		}
		//sort the array-list
		Collections.sort(new_arr);
		//print the sorted elements
		System.out.println("\nCustomer names in alphabetical order: ");
		for(String item:new_arr) {
			System.out.println(item);
		}System.out.println();
	}
	
	
	public static void main(String[] args) {
		//create an array of Strings and cast them into a list
		String[] options = {"A","a","V","v","E","e","D","d","F","f","S","s","L","l","O","o"};
		List<String> options_ = Arrays.asList(options);
		String roomName="",user_select="";
		int roomNum = 0;
		//create arrays to store room names and additional information
		String[] hotel = new String[8];
		int[] num_guests = new int[8];
		String[] fname = new String[8];
		String[] sname = new String[8];
		String[] credit = new String[8];
		//call the initialization method
		initialize(hotel,num_guests,fname,sname,credit);
		//while the value of roomNum is smaller than 8, loop the process of taking input
		while ( roomNum < 8 )
		{			
			do {
				System.out.print("Enter A to add customer to room\nEnter V to view all rooms\nEnter E to view empty rooms\nEnter D to delete customer from room"
						+ "\nEnter F to find customer from name\nEnter S to store data into file\nEnter L to load program data from file\nEnter O to view guests,"
						+ "ordered alphabetially\n: ");
				user_select = input.next();
			//while the user input is empty or the user inputed value is not in the list continue to take user input
			}while(user_select.equals("")||!(options_.contains(user_select)));
			
			//if user selects an option out of the menu, call the corresponding method
			if(user_select.equals("A")||user_select.equals("a")) {				
				roomNum = add(roomNum,roomName,hotel,num_guests,fname,sname,credit);
			}else if(user_select.equals("V")||user_select.equals("v")) {
				view(hotel,num_guests,fname,sname,credit);
			}else if (user_select.equals("E")||user_select.equals("e")) {
				empty(hotel);
			}else if (user_select.equals("D")||user_select.equals("d")) {				
				delete(hotel,num_guests,fname,sname,credit);
			}else if (user_select.equals("F")||user_select.equals("f")) {				
				find(hotel);
			}else if (user_select.equals("S")||user_select.equals("s")) {				
				store(hotel,num_guests,fname,sname,credit);
			}else if (user_select.equals("L")||user_select.equals("l")) {				
				load(hotel,num_guests,fname,sname,credit);
			}else if (user_select.equals("O")||user_select.equals("o")) {				
				order(hotel);
			}						
		}		
	}
	//method to initialize all the arrays 
	private static void initialize( String hotelRef[],int[] num_guests_init,String[] fname_init,
			String[] sname_init, String[] credit_init  ) {
		for (int i = 0; i < 8; i++ ) {
			hotelRef[i] = "e";
			num_guests_init[i]=0;
			fname_init[i]="e";
			sname_init[i]="e";
			credit_init[i]="e";
			
		}
		System.out.println( "initialize\n ");
	}
}