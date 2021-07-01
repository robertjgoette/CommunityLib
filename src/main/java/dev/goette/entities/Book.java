package dev.goette.entities;

// Entities are a 1 to 1 with tables in your database
//Use Java ...?
// Entities SHOULD be beens with private fields and public getters and setters. Java very big on encapsolation
// You can ensure fields are never set to a value you do not want
public class Book {

    int bookId;
    String title;
    String author;
    boolean isAvailable;
    int quality;
    long returnDate;

    // bean requires no arg constructor
    public Book(){

    }

    public Book(int bookId, String title, String author, boolean isAvailable, int quality, long returnDate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
        this.quality = quality;
        this.returnDate = returnDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public long getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(long returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isAvailable=" + isAvailable +
                ", quality=" + quality +
                ", returnDate=" + returnDate +
                '}';
    }
}
