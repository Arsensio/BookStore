package kz.halykacademy.bookstore.provider.interfaces;

import kz.halykacademy.bookstore.model.PublisherEntity;
import kz.halykacademy.bookstore.model.PublisherEntity;
import org.springframework.stereotype.Component;

import java.util.List;

public interface PublisherProvider {

    public List<PublisherEntity> getAll();

    public PublisherEntity get(Long id);

    public List<PublisherEntity> get(String publisherName);

    public Long save(PublisherEntity publisher);

    public void update(PublisherEntity publisher);

    public void delete(PublisherEntity publisher);
}
