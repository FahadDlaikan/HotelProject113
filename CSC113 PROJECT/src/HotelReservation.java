import java.io.*;

public class HotelReservation {
	
	private int reservationID;
	private String checkInDate;
	private String checkOutDate;
	private double totalCost;
	private Room[] rooms;
	private int nbRooms;
	private int capacity;
	private static int ReservationID = 101;

	// Constructor
	public HotelReservation(String checkInDate, String checkOutDate,int capacity) {
		this.reservationID =  ReservationID++;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		rooms = new Room[capacity];
		nbRooms = 0;
	}

	// Getters and setters
	public int getReservationID() {
		return reservationID;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}
	public double calculateTotalCost() {
		totalCost = 0;
		for(int i = 0; i < nbRooms; i++) {
			totalCost += rooms[i].calculatePrice();
		}
		return totalCost;
	}

	public boolean addRoom(Room room) {
		if (nbRooms >= rooms.length) {

			System.out.println("Cannot add more rooms. Capacity has been reached.");
			return false;
		}
			rooms[nbRooms] = room;
			
			if(room instanceof Suite) {
				System.out.println("Suite room added to the reservation.");
				System.out.println("Room Number : " + rooms[nbRooms++].getRoomNumber());
			}

			else {
				System.out.println("Regular room added to the reservation.");
				System.out.println("Room Number : " + rooms[nbRooms++].getRoomNumber());
			}
				
			
			return true;

	}
	// Method to delete a room from the reservation
	public boolean removeRoom(int roomNumber) {
		Room roomToDelete = searchRoom(roomNumber);
		if (roomToDelete == null){	
			System.out.println("Room not found in the reservation.");
			return false;
		}
		for (int i = 0; i < nbRooms; i++) {
				if (rooms[i] == roomToDelete) {
					rooms[i] = rooms[nbRooms-1];
					rooms[--nbRooms] = null;
					System.out.println("Room deleted from the reservation.");
		 			return true;		
		      }	
		}
		return false;
	}
	// Method to find a room in the reservation
	public Room searchRoom(int roomNumber) {
		for (int i = 0; i < nbRooms; i++) {
			if (rooms[i].getRoomNumber() == roomNumber) {
				return rooms[i];
			}
		}
		return null;
	}

	public void display() {
		System.out.println("Reservation ID : " + reservationID);
		System.out.println("Check in Date : " + checkInDate);
		System.out.println("Check out Date : " + checkOutDate);
		System.out.println("Total Cost : " + calculateTotalCost());
		System.out.println("You have booked a total of " + nbRooms + " rooms.");
	}
	
	// write 
	public void savetofile(String f) throws IOException{  
        File file=new File(f);  
        FileOutputStream fileOutput=new FileOutputStream(file);  
        ObjectOutputStream objectOutput= new ObjectOutputStream(fileOutput);  
        for(int i=0;i<nbRooms;i++)  
        	objectOutput.writeObject(rooms[i]);  
          
        fileOutput.close();  
        objectOutput.close();  
    }  
	
	//write
	
	public void savetofile(int roomNumber,String f) throws IOException{  
	    File file=new File(f);;  
	    FileOutputStream fileOutput=new FileOutputStream(file);  
	    ObjectOutputStream objectOutput= new ObjectOutputStream(fileOutput);  
	    for(int i=0;i<nbRooms;i++)  
	        if(rooms[i] instanceof Suite && rooms[i].getRoomNumber()==roomNumber)  
	        	objectOutput.writeObject(rooms[i]);  
	      
	    fileOutput.close();  
	    objectOutput.close();  
	  
	    }  
	
	// read
	
	public void LoadFromfile(String filename, Suite suite[]) throws IOException{  
	    int index=0;  
	    File file=new File(filename);  
	    FileInputStream fileInput= new FileInputStream(file);  
	    ObjectInputStream ObjectInput=new ObjectInputStream(fileInput);  
	    try {  
	    while(true) {  
	    try {  
	    Room room=(Room)ObjectInput.readObject();  
	    if(room instanceof Suite)  
	        suite[index++]=(Suite)room;  
	      
	    }catch(ClassNotFoundException e) {  
	        System.out.println(e);  
	      
	    }
	   }  
	      
	}catch(EOFException e) {  
	    System.out.println("Done reading.");  
	    fileInput.close();  
	    ObjectInput.close();  
	}  
	      
	}  
}

