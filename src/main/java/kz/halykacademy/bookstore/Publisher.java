package kz.halykacademy.bookstore;

import java.util.ArrayList;
import java.util.List;

public class Publisher implements PublisherProvider {
    int id;
    String name;
    List<Book> books;

    List<Publisher> publishers = new ArrayList<>();

    public Publisher(int id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Publisher(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    @Override
    public List<Publisher> getAll() {
        return publishers;
    }

    public Publisher getBook(int id) {
        return publishers.get(id);
    }

    public void putBook(Publisher publisher) {
        publishers.add(publisher);
    }

    public void deletePublisher(int id) {
        publishers.remove(id);
    }

}
