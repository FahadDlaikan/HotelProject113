

public class Regular extends Room {
	    private boolean hasBalcony;
	    private double price;
	    // Constructor
	    public Regular(double nights,boolean hasBalcony) {
	        super("Regular", nights);
	        this.hasBalcony = hasBalcony;
	    }

	    // Method to book the regular room
	    //@Override
	    public void bookRoom() {
	        if (isAvailable()) {
	            setAvailability(false); // Room is now booked
	            System.out.println("Regular room " + roomNumber + " has been booked.");
	        } else {
	            System.out.println("Sorry, regular room " + roomNumber + " is not available.");
	        }
	    }

	    // Method to check availability of the regular room
	    //@Override
	    public void checkAvailability() {
	        if (isAvailable()) {
	            System.out.println("Regular room " + roomNumber + " is available.");
	        } else {
	            System.out.println("Regular room " + roomNumber + " is not available.");
	        }
	    }
	    public  double calculatePrice() {
	    	price = nights * 500;
	    	if(hasBalcony)
	    		price += (50 * nights);
	    	return price;
	    }

	    public void displayInfo() {
	    	System.out.println();
	    	System.out.println("----------------------------------------------");
	    	System.out.println("Hotel: IMF");
	    	System.out.println("Room Number: " + roomNumber);
	        System.out.println("Room Type: Regular");
	        if(hasBalcony)
		        System.out.println("This room has a Balcony");
	    	System.out.println("----------------------------------------------");
	    	System.out.println();

	    }

		public String getType() {
			return "Regular";
		}
	}


