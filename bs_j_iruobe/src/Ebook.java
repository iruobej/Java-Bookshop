import java.time.LocalDate;

public class Ebook extends Book {
	private int ePages;
	private EbookFormat eformat;
	public Ebook(int barcode, BookType bookType, String title, BookLanguage language, String genre, LocalDate releaseDate,
			int stockquantity, double price, int ePages, EbookFormat eformat) {
		super(barcode, bookType, title, language, genre, releaseDate, stockquantity, price);
		this.ePages = ePages;
		this.eformat = eformat;
	}
	public int getEpages() {
		return ePages;
	}
	public void setLength(int ePages) {
		this.ePages = ePages;
	}
	public EbookFormat getEformat() {
		return eformat;
	}
	public void setEformat(EbookFormat eformat) {
		this.eformat = eformat;
	}
	
	

}
