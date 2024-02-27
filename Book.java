/**
 *@author SeyedParsa Hejazi
 *@author Han Lee
 */
import java.io.*;

public class Book implements Serializable {
	private String title;
	private String author;
	private double price;
	private String ISBN;
	private String genre;
	private int year;

	/**
	 * Default Constructor for Book
	 */
	public Book() {
		title = "Empty";
		author = "Empty";
		price = 0.0;
		ISBN = "Empty";
		genre = "Empty";
		year = 0;
	}

	public Book(String title, String author, double price, String ISBN, String genre, int year) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.ISBN = ISBN;
		this.genre = genre;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Boolean equals(Book book) {
		if (book == null || book.getClass() != this.getClass()) {
			return false;
		} else {
			Book otherBook = (Book) book;
			return (this.year == otherBook.year && this.genre.equals(otherBook.genre)
					&& this.title.equals(otherBook.title) && this.price == otherBook.price
					&& this.ISBN.equals(otherBook.ISBN) && this.author.equals(otherBook.author));
		}
	}

	@Override
	public String toString() {
		return "The book with the title \"" + title + '\"' + " is written by " + author + ". " + " It is priced at "
                + price + "$ and has a ISBN number of " + ISBN + ", genre of " + genre + " written in the year " + year
                + ".";
	}

}
