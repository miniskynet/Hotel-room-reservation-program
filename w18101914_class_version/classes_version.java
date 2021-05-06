import java.io.*;
import java.util.*;

public class classes_version {
	//create static variables for the queue, the size of the queue,
	//and the front and rear pointers
	static Room waiting_list[] = new Room[10];
	static int queue_size;
	static int front;
	static int rear;
	public static Scanner input = new Scanner(System.in);
	
	//method to check if hotel is full
		public static boolean isfull(Room rooms_[]) {
			//set return values default state to false
			boolean full = false;
			int ecount = 0;
			//iterate through each room object and count empty rooms
			for(int i=0;i<rooms_.length;i++) {
				if(rooms_[i].room_name.equals("e")) {
					ecount++;
				}			
			}
			//if empty room count is 0, set the boolean value to true
			if(ecount==0) {
				full = true;
			}
			return full;
		}
	
	//create a method to enQueue values into the queue
	public static void enQueue(Room waiting_list_[]) {
		//check whether there is vacant space in the queue,
		//and alert the user
		if(isFull()) {
			System.out.println("The waiting list is full!");
		}
		//if there is space in the waiting list take information of customer,
		//to be stored later
		else {		
			System.out.println("Enter name for room :" );
			waiting_list_[rear].room_name = input.next();
			//make sure user enters a number for the amount of guests with a try catch block
			do {
			    try {
			    	System.out.println("Enter number of guests for room :" );
			    	waiting_list_[rear].add_info.num_guests= input.nextInt();
			    } catch (InputMismatchException e) {
			        System.out.print("Please enter number\n");
			    }
			    input.nextLine();
			} while (waiting_list_[rear].add_info.num_guests <= 0);
			//take additional information
			System.out.println("\nAdditional Information\n");
			System.out.println("Enter first name of customer:");
			waiting_list_[rear].add_info.fname=input.next();
			System.out.println("Enter surname of customer:");
			waiting_list_[rear].add_info.lname=input.next();
			System.out.println("Enter credit card number of customer:");
			waiting_list_[rear].add_info.credit = input.next();	
			//move the rear pointer and increase queue size
			rear = (rear+1)%10;
			queue_size++;
		}		
	}
	
	//create a method to dequeue instances
	public static Room deQueue() {
		//take a Room object into a variable
		Room waiting_person = waiting_list[front];
		//move front pointer and decrease the size of queue
		front = (front+1)%10;
		queue_size--;
		//return the next in line Room object
		return waiting_person;
	}
	
	//create a method to check if queue is full
	public static boolean isFull() {
		return queue_size==10;
	}
	
	//create a method views all rooms
	public static void view(Room rooms_[]) {
		System.out.println();
		//iterate through the array and print all the room-names and additional information
		for (int i = 0; i < 8; i++ )
		{
			System.out.println("Room number "+ i + " is occupied by " + rooms_[i].room_name);
			System.out.println("Customers full name is: "+rooms_[i].add_info.fname+" "+rooms_[i].add_info.lname);
			System.out.println("Customers credit card number is: "+rooms_[i].add_info.credit);
			System.out.println("Number of guests in room: "+rooms_[i].add_info.num_guests);
			System.out.println();
		}
	}
	
	//create a method to add a new customer to room
	public static int add(int roomNum_,Room rooms_[],Room waiting_list_[]) {
		String[] exit_cont = {"E","e","C","c"};
		List<String> options_ = Arrays.asList(exit_cont);
		String exit = "";
		int roomNum_temp=9;
		//if hotel rooms are all occupied add customer to waiting list
		if(isfull(rooms_)) {			
			do {
				System.out.println("Hotel rooms are all occupied."
						+ "You will be added to a waiting list.\n"
						+ "Press E to exit or C to continue\n: ...");
				exit = input.next();
			}//while user input does not equal to the exit or continue option re-prompt
			while(exit.equals("")||!(options_.contains(exit)));
			//if user select continue proceed to the enqueue process
			if(exit.equals("C")||exit.equals("c")) {
				enQueue(waiting_list_);
			}//if user selects exit, return 9 to exit the program
			else if(exit.equals("E")||exit.equals("e")) {
				System.out.println("Exiting ...");
				roomNum_ = roomNum_temp;
				return roomNum_;
			}			
			
		}else {	
			
			//create  a try catch block inside a while loop,
			//to make sure that the user input is an integer,
			//and within range
			do {
			    try {
			    	System.out.println("Enter room number (0-8) or 8 to stop:" );
			    	roomNum_temp= input.nextInt();
			    } catch (InputMismatchException e) {
			        System.out.print("Invalid\n");
			    }
			    input.nextLine();
			} while (roomNum_temp >= 9);
			roomNum_ = roomNum_temp;
			//if user enters 8 return the roomNum to exit the program
			if (roomNum_==8) {
				System.out.println("Exiting ...");
				return roomNum_;
			//if user enters any other integer lesser than 8,
			//ask user for a name for the room, and assign it 
			}else {
				System.out.println("Enter name for room " + roomNum_ +" :" );
				rooms_[roomNum_].room_name = input.next();
				//make sure user enters a number for the amount of guests with a try catch block
				do {
				    try {
				    	System.out.println("Enter number of guests for room " + roomNum_ +" :" );
				    	rooms_[roomNum_].add_info.num_guests= input.nextInt();
				    } catch (InputMismatchException e) {
				        System.out.print("Please enter number\n");
				    }
				    input.nextLine();
				} while (rooms_[roomNum_].add_info.num_guests <= 0);
				//take additional information
				System.out.println("\nAdditional Information\n");
				System.out.println("Enter first name of customer:");
				rooms_[roomNum_].add_info.fname=input.next();
				System.out.println("Enter surname of customer:");
				rooms_[roomNum_].add_info.lname=input.next();
				System.out.println("Enter credit card number of customer:");
				rooms_[roomNum_].add_info.credit = input.next();				
			}
			
		}		
		return roomNum_;
	}
	
	//create a method to find customer room from name
	public static void find(Room rooms_[]) {
		int in_room = 9;
		//ask user for the name to find
		System.out.println("\nEnter name of customer: ");
		String name = input.next();
		//iterate through the array and,
		//if the user selected name is present, 
		//change the value of in_room to the number of the room,
		//and print out the room number
		for(int i=0;i<8;i++) {
			if (rooms_[i].room_name.equals(name)&&(!rooms_[i].room_name.equals("e"))) {
				in_room = i;
				System.out.println("Selected customer is in room "+i);
				System.out.println();
		
		}
	//if in_room value does not change(customer name is not present),
	//alert the user
	}if(in_room==9) {
		System.out.println("The name you are looking for cannot be found.\n");
	}
	}
	
	//create a method view empty rooms
	public static void empty(Room rooms_[]) {
		System.out.println();
		//iterate through the array and print out
		//the names that are equal to the initialized value
		for (int i = 0; i < 8; i++ )
		{	
			if (rooms_[i].room_name.equals("e")) {
				System.out.println("room " + i + " is empty");
			}
		}System.out.println();
	}
	
	//create a method delete customer from room
	public static void delete(Room rooms_[]) {
		int roomNum_,roomNum_temp=9;
		//create a try-catch block to validate if the user input,
		//is an integer and is in range
		do {
		    try {
		    	System.out.print("Enter room number to delete customer: ");
				roomNum_temp = input.nextInt();
		    } catch (InputMismatchException e) {
		        System.out.print("Invalid\n");
		        
		    }
		    input.nextLine();
		} while (roomNum_temp>8);
		roomNum_ = roomNum_temp;
		//set the selected room number to the initial value
		rooms_[roomNum_].room_name="e";
		rooms_[roomNum_].add_info.num_guests=0;
		rooms_[roomNum_].add_info.fname="e";
		rooms_[roomNum_].add_info.lname="e";
		rooms_[roomNum_].add_info.credit="e";
		//if queue size is larger than 0, proceed to dequeue elements from the queue,
		//and enter them into vacant hotel rooms
		if(queue_size>0) {
			rooms_[roomNum_] = deQueue();			
		}
	}
	
	//create a method store data
	public static void store(Room rooms_[]) {
		//create a try catch block to create a new text file to store data,
		//and print out if any errors occur
		try {
			File create = new File("Hotelinfo_classes.txt");
			if(create.createNewFile()) {
				System.out.println("File created: " + create.getName());
			}else {
				System.out.println("File already exists. ");
			}
		}catch(IOException e) {
			System.out.println("An error occurred. ");
			e.printStackTrace();
		}//create a try catch block to make sure the data is stored, 
		//and alert the user if errors occur
		try {
			FileWriter store = new FileWriter("Hotelinfo_classes.txt");
			for(int i=0;i<8;i++) {
				store.write(i+" "+rooms_[i].room_name+" "+rooms_[i].add_info.num_guests+" "
						+rooms_[i].add_info.fname+" "+rooms_[i].add_info.lname+" "
						+rooms_[i].add_info.credit+"\n");
			}//alert the user know once the data is stored
			store.close();
			System.out.println("Data stored");			
		}catch (IOException e) {
			System.out.println("An error occurred. ");
			e.printStackTrace();
		}
		
	}
	
	//load data from file
	public static void load(Room rooms_[]) {
		//create a try catch block to read the stored data line by line,
		//and alert the user if an error occurs
		try {
			File read_object = new File("Hotelinfo_classes.txt");
			Scanner sc = new Scanner(read_object);
			while(sc.hasNextLine()) {
				String data = sc.nextLine();
				String[] data_arr = data.split(" ");
				rooms_[Integer.parseInt(data_arr[0])].room_name = data_arr[1];
				rooms_[Integer.parseInt(data_arr[0])].add_info.num_guests = Integer.parseInt(data_arr[2]);
				rooms_[Integer.parseInt(data_arr[0])].add_info.fname = data_arr[3];
				rooms_[Integer.parseInt(data_arr[0])].add_info.lname = data_arr[4];
				rooms_[Integer.parseInt(data_arr[0])].add_info.credit = data_arr[5];
				
			}//if the process is successful, alert the user
			System.out.println("\nSuccessfully loaded!\n");
			sc.close();
		}catch(FileNotFoundException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	
	//order customer names in alphabetical order 
	public static void order(Room rooms_[]) {
		//create an array list to store room names,
		//which are not equal to the initial value
		ArrayList<String> new_arr = new ArrayList<String>();
		for(int i=0;i<8;i++) {
			if(rooms_[i].room_name.equals("e")) {
				continue;
			}else {
				new_arr.add(rooms_[i].room_name);
			}
		}
		//sort the array list alphabetically 
		Collections.sort(new_arr);
		System.out.println("\nCustomer names in alphabetical order: ");
		//print the sorted array
		for(String item:new_arr) {
			System.out.println(item);
		}System.out.println();
	}
		
	//initialize the array with "e" strings
	public static void initialize(Room rooms_[]) {
		System.out.println( "initialize\n ");
		//iterate through the array, and assign the initial values to it
		for(int i=0;i<8;i++) {
			rooms_[i] = new Room();

		}
		//iterate through the queue array, and assign the initial values to it
		for(int i=0;i<10;i++) {
			waiting_list[i] = new Room();

		}
		for(int i=0;i<8;i++) {
			//set room attributes to default values of all room instances
			rooms_[i].room_name = "e";
			rooms_[i].add_info.num_guests = 0;
			rooms_[i].add_info.fname = "e";
			rooms_[i].add_info.lname = "e";
			rooms_[i].add_info.credit = "e";
		}
		for(int i=0;i<10;i++) {
			//set room attributes to default values of all waiting list instances
			waiting_list[i].room_name = "e";
			waiting_list[i].add_info.num_guests = 0;
			waiting_list[i].add_info.lname = "e";
			waiting_list[i].add_info.fname = "e";
			waiting_list[i].add_info.credit = "e";
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		//create an array of Strings and cast them into a list
		String[] options = {"A","a","V","v","E","e","D","d","F","f","S","s","L","l","O","o"};
		List<String> options_ = Arrays.asList(options);
		int roomNum=0;
		String user_select="";
		//create array to store room names and additional information
		Room[] rooms = new Room[8];
		initialize(rooms);	
		//create queue for waiting list

		while ( roomNum < 8 )
		{			
			do {
				System.out.print("Enter A to add customer to room\nEnter V to view all rooms\nEnter E to view empty rooms\nEnter D to delete customer from room"
						+ "\nEnter F to find customer from name\nEnter S to store data into file\nEnter L to load program data from file\nEnter O to view guests,"
						+ "ordered alphabetially\n: ");
				user_select = input.next();
				//while the user input is empty or the user inputed value is not in the list continue to take user input	
			}while(user_select.equals("")||!(options_.contains(user_select)));
			//depending on user select, call the method
				if(user_select.equals("V")||user_select.equals("v")) {
					view(rooms);
				}else if(user_select.equals("A")||user_select.equals("a")) {				
					roomNum = add(roomNum,rooms,waiting_list);
				}else if (user_select.equals("E")||user_select.equals("e")) {
						empty(rooms);
				}else if (user_select.equals("D")||user_select.equals("d")) {				
					delete(rooms);
				}else if (user_select.equals("F")||user_select.equals("f")) {				
					find(rooms);
				}else if (user_select.equals("S")||user_select.equals("s")) {				
					store(rooms);
				}else if (user_select.equals("L")||user_select.equals("l")) {				
					load(rooms);
				}else if (user_select.equals("O")||user_select.equals("o")) {				
					order(rooms);
				}

}
		}}
