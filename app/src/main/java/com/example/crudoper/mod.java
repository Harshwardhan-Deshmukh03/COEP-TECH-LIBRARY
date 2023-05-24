package com.example.crudoper;

public class mod {
    String bookName,bookNumber,bookPublication,bookstatus;

    public mod() {
    }

    public mod(String bookName, String bookNumber, String bookPublication, String bookstatus) {
        this.bookName = bookName;
        this.bookNumber = bookNumber;
        this.bookPublication = bookPublication;
        this.bookstatus = bookstatus;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getBookPublication() {
        return bookPublication;
    }

    public void setBookPublication(String bookPublication) {
        this.bookPublication = bookPublication;
    }

    public String getBookstatus() {
        return bookstatus;
    }

    public void setBookstatus(String bookstatus) {
        this.bookstatus = bookstatus;
    }
}
