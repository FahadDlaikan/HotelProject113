import java.io.Serializable;

public abstract class Room implements Serializable {
	    protected int roomNumber;
	    protected String type;
	    protected boolean availability;
	    protected double nights;
		protected static int roomCounter = 101;

	    // Constructor
	    public Room(String type,double nights) {
	        this.roomNumber = roomCounter++;
	        this.type = type;
	        this.availability = true; // By default, room is available
	        this.nights = nights;
	    }
	    // Getters and setters
	    public int getRoomNumber() {
	        return roomNumber;
	    }

	    public boolean isAvailable() {
	        return availability;
	    }
	    public void setAvailability(boolean availability) {
	        this.availability = availability;
	    }
	    public abstract String getType();
	    
	    public abstract double calculatePrice();
	    
	    public abstract void displayInfo() ;
	    
	    // Abstract method to book the room
	    public abstract void bookRoom();

	    // Abstract method to check availability
	    public abstract void checkAvailability();
}


