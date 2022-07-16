package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.model.Publisher;
import kz.halykacademy.bookstore.provider.interfaces.PublisherProvider;
import kz.halykacademy.bookstore.service.interfaces.PublisherService;

import java.util.List;

public class PublisherServiceImpl implements PublisherService {

    PublisherProvider provider;


    public PublisherServiceImpl(PublisherProvider provider) {
        this.provider = provider;
    }

    @Override
    public List<Publisher> listAll() {
        return provider.getAll();
    }

    @Override
    public Publisher getOne(Long id) {
        return provider.get(id);
    }

    @Override
    public List<Publisher> getByName(String name) {
        return provider.get(name);
    }
}
