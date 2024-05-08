

public class Customer {
	    private int customerID;
	    private String name;
	    private String phoneNumber;
	    private HotelReservation reservation;
		// Constructor
	    public Customer(int customerID, String name, String phoneNumber,String checkInDate, String checkOutDate) {
	        this.customerID = customerID;
	        this.name = name;
	        this.phoneNumber = phoneNumber;
	    }
	    // Getters and setters
	    public int getCustomerID() {
	        return customerID;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }
	    public HotelReservation getReservation() {
			return reservation;
		}
		public void setReservation(HotelReservation reservation) {
			this.reservation = reservation;
		}
	    public void Invoice() {
	    	System.out.println();
	    	System.out.println("----------------------------------------------");
	    	System.out.println("Customer ID : " + customerID);
	    	System.out.println("Customer Name : " + name);
	    	System.out.println("Phone Number : " + phoneNumber);
	    	this.reservation.display();
	    	System.out.println("----------------------------------------------");
	    	System.out.println();

	    }

	}



