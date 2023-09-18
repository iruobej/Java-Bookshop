import java.time.LocalDate;

public class AudioBook extends Book {

	private double length;
	private AudioFormat aformat; 
	public AudioBook(int barcode, BookType bookType, String title, BookLanguage language, String genre,
			LocalDate releaseDate, int stockquantity, double price, double length, AudioFormat aformat) {
		super(barcode, bookType, title, language, genre, releaseDate, stockquantity, price);
		this.length = length;
		this.aformat = aformat;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public AudioFormat getAformat() {
		return aformat;
	}

	public void setAformat(AudioFormat aformat) {
		this.aformat = aformat;
	}

	
}
