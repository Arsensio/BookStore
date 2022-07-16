package kz.halykacademy.bookstore.provider;

import kz.halykacademy.bookstore.model.Author;
import kz.halykacademy.bookstore.model.Publisher;
import kz.halykacademy.bookstore.provider.interfaces.PublisherProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PublisherProviderImpl implements PublisherProvider {

    List<Publisher> publishers = new ArrayList<>();

    @Override
    public List<Publisher> getAll() {
        return publishers.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public Publisher get(Long id) {
        return publishers.get(Math.toIntExact(id));
    }

    @Override
    public List<Publisher> get(String publisherName) {
        List<Publisher> authorsWithName = publishers
                .stream()
                .filter(name -> name.getName().contains(publisherName))
                .collect(Collectors.toList());

        return authorsWithName;
    }

    @Override
    public Long save(Publisher publisher) {
        publishers.add(publisher);
        Long index = Long.valueOf(publishers.size() - 1);
        publisher.setId(index);
        return index;
    }

    @Override
    public void update(Publisher publisher) {
        publishers.set(Math.toIntExact(publisher.getId()), publisher);
    }

    @Override
    public void delete(Publisher publisher) {
        publishers.set(Math.toIntExact(publisher.getId()), null);

    }
}
