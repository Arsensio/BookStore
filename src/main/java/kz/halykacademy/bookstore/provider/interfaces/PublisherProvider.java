package kz.halykacademy.bookstore.provider.interfaces;

import kz.halykacademy.bookstore.model.Book;
import kz.halykacademy.bookstore.model.Publisher;

import java.util.List;

public interface PublisherProvider {

    public List<Publisher> getAll();

    public Publisher get(Long id);

    public List<Publisher> get(String publisherName);

    public Long save(Publisher publisher);

    public void update(Publisher publisher);

    public void delete(Publisher publisher);
}
