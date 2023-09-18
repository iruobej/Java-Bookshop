import java.util.ArrayList;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {
	private int barcode;
	private	BookType bookType;
	private String title;
	private BookLanguage language;
	private String genre;
	private LocalDate releaseDate;
	private int stockquantity; 
	private double price;
	
	public Book(int barcode,BookType bookType, String title,BookLanguage language, String genre, LocalDate releaseDate,int stockquantity,double price) {
		this.barcode = barcode;
		this.bookType = bookType;
		this.title = title;
		this.language = language;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.stockquantity = stockquantity;
		this.price = price;
	}
	public int getBarcode() {
		return(barcode);
	}
	public BookType getBookType() {
		return(bookType);
	}
	public String getGenre() {
		return(genre);
	}
	public String getTitle() {
		return(title);
	}
	public BookLanguage getLanguage() {
		return(language);
	}
	public LocalDate getReleaseDate() {
		//DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		//String releaseDate = releaseDate.format(format);
		
		return this.releaseDate;
	}
	public int getStockQuantity() {
		return(stockquantity);
	}
	public double getPrice() {
		return(price);
	}
	public void setStockQuantity(int stockquantity) {
		this.stockquantity = stockquantity;
	}
}
