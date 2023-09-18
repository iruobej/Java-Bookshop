import java.util.ArrayList;

public class User {

	private int userID;
	private String username;
	private String surname;
	private int houseNumber;
	private String postCode;
	private String city;
	private String role;
	private ArrayList<Book> bookList;

	public User(int userID, String username, String surname, int houseNumber, String postCode, String city,
			String role) {
		super();
		this.userID = userID;
		this.username = username;
		this.surname = surname;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.city = city;
		this.role = role;
	}
	
	
	public int getUserID() {
		return userID;
	}



	public String getUsername() {
		return username;
	}



	public String getSurname() {
		return surname;
	}



	public int getHouseNumber() {
		return houseNumber;
	}



	public String getPostCode() {
		return postCode;
	}



	public String getCity() {
		return city;
	}



	public String getRole() {
		return role;
	}

	
}
