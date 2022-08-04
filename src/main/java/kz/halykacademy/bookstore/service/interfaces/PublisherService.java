package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.model.PublisherEntity;
import kz.halykacademy.bookstore.store.interfaces.PublisherRepository;
import kz.halykacademy.bookstore.web.publisher.PublisherDTO;
import kz.halykacademy.bookstore.web.publisher.SavePublisherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;


public interface PublisherService {

    public List<PublisherDTO> findAll();

    public PublisherDTO findOne(Long id) throws Throwable;

    public List<PublisherDTO> getByName(String name);

    public PublisherDTO save(SavePublisherDTO savePublisherDTO);

    public PublisherDTO update(SavePublisherDTO savePublisherDTO);

    public void delete(Long id);
}

@Service
class PublisherServiceImpl implements PublisherService {

    @Autowired
    PublisherRepository repository;


    @Override
    public List<PublisherDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(PublisherEntity::toDTO)
                .toList();
    }

    @Override
    public PublisherDTO findOne(Long id) throws Throwable {
        return repository.findById(id)
                .map(PublisherEntity::toDTO)
                .orElseThrow((Supplier<Throwable>) () -> new Exception("Author with id not found"));
    }

    @Override
    public List<PublisherDTO> getByName(String name) {
        return repository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(PublisherEntity::toDTO)
                .toList();
    }

    @Override
    public PublisherDTO save(SavePublisherDTO savePublisherDTO) {

        PublisherEntity saved = repository.save(
                new PublisherEntity(
                        savePublisherDTO.getId(),
                        savePublisherDTO.getName()
                )
        );
        return saved.toDTO();
    }

    @Override
    public PublisherDTO update(SavePublisherDTO savePublisherDTO) {

        repository.findById(savePublisherDTO.getId()).ifPresentOrElse(it -> {
            it.setName(savePublisherDTO.getName());
            repository.saveAndFlush(it);
        }, () -> {
            System.out.println("no such publisher");
        });

        return repository.findById(savePublisherDTO.getId()).get().toDTO();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

