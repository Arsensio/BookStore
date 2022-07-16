package kz.halykacademy.bookstore.model;

import kz.halykacademy.bookstore.provider.interfaces.PublisherProvider;

import java.util.ArrayList;
import java.util.List;

public class Publisher  {
    Long id;
    String name;
    List<Book> books;


    public Publisher(Long id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Publisher(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


}
