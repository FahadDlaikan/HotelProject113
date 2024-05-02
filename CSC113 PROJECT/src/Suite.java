
public class Suite extends Room{

        private boolean hasJacuzzi;
        private double price;
	    // Constructor
	    public Suite(double nights,boolean hasJacuzzi) {
	        super("Suite", nights);
	        this.hasJacuzzi = hasJacuzzi;
	    }

	    // Method to book the suite
	   // @Override
	    public void bookRoom() {
	        if (isAvailable()) {
	            setAvailability(false); // Room is now booked
	            System.out.println("Suite " + roomNumber + " has been booked.");
	        } else {
	            System.out.println("Sorry, Suite " + roomNumber + " is not available.");
	        }
	    }

	    // Method to check availability of the suite
	   // @Override
	    public void checkAvailability() {
	        if (isAvailable()) {
	            System.out.println("Suite " +roomNumber + " is available.");
	        } else {
	            System.out.println("Suite " + roomNumber + " is not available.");
	        }
	    }
	    public  double calculatePrice() {
	    	price = nights * 1000;
	    	if(hasJacuzzi) price += (100 * nights);
	    	return price;
	    }

	    public void displayInfo() {
	    	System.out.println();
	    	System.out.println("----------------------------------------------");
	    	System.out.println("Hotel: IMF");
	    	System.out.println("Room Number: " + roomNumber);
	        System.out.println("Room Type: Suite");
	        if(hasJacuzzi)
	        System.out.println("This Suite has a Jacuzzi");
	    	System.out.println("----------------------------------------------");
	    	System.out.println();

	    }

		public String getType() {
			return "Suite";
		}
	}
	


