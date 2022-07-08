package kz.halykacademy.bookstore;

import java.time.LocalDate;
import java.util.List;

public class Author {
    int id;
    String lastName;
    String firstName;
    String patronymic;
    LocalDate dateOfBirth;
    List<Book>books;

    public Author(String surname, String name, String patronymic, LocalDate dateOfBirth, List<Book> books) {
        this.lastName = surname;
        this.firstName = name;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.books = books;
    }

    public Author(int id, String surname, String name, String patronymic, LocalDate dateOfBirth, List<Book> books) {
        this.id = id;
        this.lastName = surname;
        this.firstName = name;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return lastName;
    }

    public void setSurname(String surname) {
        this.lastName = surname;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
