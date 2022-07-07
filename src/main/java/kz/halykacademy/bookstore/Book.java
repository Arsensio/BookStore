package kz.halykacademy.bookstore;

import java.time.LocalDate;
import java.util.List;

public class Book {
    int id;
    double price;
    List<Author>authors;
    String publisher;
    String name;
    int numOfpage;
    LocalDate yearOfIssue;

    public Book(double price, List<Author> authors, String publisher, String name, int numOfpage, LocalDate yearOfIssue) {
        this.price = price;
        this.authors = authors;
        this.publisher = publisher;
        this.name = name;
        this.numOfpage = numOfpage;
        this.yearOfIssue = yearOfIssue;
    }

    public Book(int id, double price, List<Author> authors, String publisher, String name, int numOfpage, LocalDate yearOfIssue) {
        this.id = id;
        this.price = price;
        this.authors = authors;
        this.publisher = publisher;
        this.name = name;
        this.numOfpage = numOfpage;
        this.yearOfIssue = yearOfIssue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfpage() {
        return numOfpage;
    }

    public void setNumOfpage(int numOfpage) {
        this.numOfpage = numOfpage;
    }

    public LocalDate getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(LocalDate yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }
}
