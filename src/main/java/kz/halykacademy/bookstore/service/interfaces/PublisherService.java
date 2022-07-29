package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.model.PublisherEntity;
import kz.halykacademy.bookstore.store.interfaces.PublisherRepository;
import kz.halykacademy.bookstore.web.author.SaveAuthorDTO;
import kz.halykacademy.bookstore.web.publishers.PublisherDTO;
import kz.halykacademy.bookstore.web.publishers.SavePublisherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


public interface PublisherService {

    public List<PublisherDTO> findAll();

    public PublisherDTO findOne(Long id) throws Throwable;

    public List<PublisherEntity> getByName(String name);

    public PublisherDTO save(SavePublisherDTO savePublisherDTO);
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
                .orElseThrow((Supplier<Throwable>)()->new Exception("Author with id not found"));
    }

    @Override
    public List<PublisherEntity> getByName(String name) {
        return repository.findAllByName(name);
    }

    @Override
    public PublisherDTO save(SavePublisherDTO savePublisherDTO) {
        PublisherEntity saved = repository.save(
                new PublisherEntity(
                        savePublisherDTO.getId(),
                        savePublisherDTO.getName(),
                        null
                )
        );
        return saved.toDTO();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}

