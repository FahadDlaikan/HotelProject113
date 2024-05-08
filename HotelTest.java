import java.util.Scanner;
public class HotelTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("\t \t Welcome to the IMF Hotel");
		System.out.println("Please enter your ID: ");
		int id = in.nextInt();
		in.nextLine();
		System.out.println("Please enter your name: ");
		String name = in.nextLine();
		System.out.println("Please enter your Phone Number: ");
		String phoneNumber = in.nextLine();
		System.out.println("Please enter your capacity: ");
		int capacity = in.nextInt();
		in.nextLine();
		if(capacity <= 0) System.out.println("Capacity must be greater than 0. Please try again");
		System.out.println("Please enter your Check in Date (DD/MM/YYYY): ");
		String checkInDate = in.nextLine();
		System.out.println("Please enter your Check Out Date (DD/MM/YYYY): ");
		String checkOutDate = in.nextLine();

		Customer Fahad = new Customer(id,name,phoneNumber,checkInDate,checkOutDate);
		HotelReservation reservation = new HotelReservation(checkInDate,checkOutDate,capacity);
		Fahad.setReservation(reservation);
		Room[] rooms = new Room[capacity];
		int counter = 0;
		boolean addMore = true;
		do{
			System.out.println(" \t Welcome to the IMF Hotel");
			System.out.println("Select which service you would like to do:");
			System.out.println("1)Add Room to the reservation");
			System.out.println("2)Delete Room from the reservation");
			System.out.println("3)Print your Invoice");
			System.out.println("4)EXIT");
			System.out.println("Enter the number of the service you want: ");
			int service = in.nextInt();
			in.nextLine();
			if(service == 1) {
				System.out.println("Would you like a Regular or a Suite Room (Enter 1 for suite or 2 for regular): ");
				int choice = in.nextInt();
				in.nextLine();
				System.out.println("How many nights would you like to stay: ");
				double nights = in.nextDouble();
				in.nextLine();
				
				boolean hasJaccuzzi = false;
				boolean hasBalcony = false;
				if(choice == 1) {
					System.out.println("Would you like a jacuzzi with your suite? (yes/no): ");
					String jaccuzzi = in.nextLine();
					if(jaccuzzi.equalsIgnoreCase("yes")) hasJaccuzzi = true;
					if(counter >= capacity) reservation.addRoom(rooms[counter-1]);
					else {
					rooms[counter] = new Suite(nights, hasJaccuzzi);	
					if(reservation.addRoom(rooms[counter]))
					counter++;
					}
				}
				else {
					System.out.println("Would you like a balcony with your regular? (yes/no): ");
					String balcony = in.nextLine();
					if(balcony.equalsIgnoreCase("yes")) hasBalcony = true;
					rooms[counter] = new Regular(nights, hasBalcony);
					if(reservation.addRoom(rooms[counter]))
					counter++;
			    }
				System.out.println();
				System.out.println();

			}
			else if(service == 2) {
				System.out.println("Please enter the room number you want to remove: ");
				int roomNumber = in.nextInt();
				in.nextLine();
				if(reservation.removeRoom(roomNumber)) {
					for (int i = 0; i < counter; i++) {
						if (rooms[i].getRoomNumber() == roomNumber) {
							rooms[i] = rooms[counter-1];
							rooms[--counter] = null;
						}
				      }	
				}
			}
			else if(service == 3) {
				Fahad.Invoice();
				System.out.println("Would you like to display a specific room? (yes/no): ");
				String display = in.nextLine();
				if(display.equalsIgnoreCase("yes")) {
					System.out.println("Please enter the room number you want to display: ");
					int roomNumber = in.nextInt();
					in.nextLine();
					reservation.searchRoom(roomNumber).displayInfo();
				}
			}
			else addMore = false;


			
		}while(addMore);
		System.out.println("Here is your final Invoice : ");
		Fahad.Invoice();
		System.out.println("\t Thank you for visting the IMF Hotel.");
		

	}

}

