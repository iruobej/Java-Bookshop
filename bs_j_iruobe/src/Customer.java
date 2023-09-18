
public class Customer extends User {
	private double creditBalance;

	public Customer(int userID, String username, String surname, int houseNumber, String postCode, String city,
			double creditBalance, String role) {
		super(userID, username, surname, houseNumber, postCode, city, role);
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	public double getCreditBalance() {
		return creditBalance;
	}


	public void setCreditBalance(double creditBalance) {
		this.creditBalance = creditBalance;
	}
     
	
}
