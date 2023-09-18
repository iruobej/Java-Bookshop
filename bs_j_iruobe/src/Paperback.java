import java.time.LocalDate;

public class Paperback extends Book {

	private int pages;
	private Condition bookCondition; 
	public Paperback(int barcode, BookType bookType, String title, BookLanguage language, String genre,
			LocalDate releaseDate, int stockquantity, double price, int pages, Condition bookCondition){
		super(barcode, bookType, title, language, genre, releaseDate, stockquantity, price);
		this.pages = pages;
		this.bookCondition = bookCondition;
		
		
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public Condition getBookCondition() {
		return bookCondition;
	}
	public void setBookCondition(Condition bookCondition) {
		this.bookCondition = bookCondition;
	}

	
}
