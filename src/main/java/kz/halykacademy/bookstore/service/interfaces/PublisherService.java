package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.exceptions.ResourceNotFoundException;
import kz.halykacademy.bookstore.models.PublisherEntity;
import kz.halykacademy.bookstore.store.interfaces.PublisherRepository;
import kz.halykacademy.bookstore.web.publisher.PublisherDTO;
import kz.halykacademy.bookstore.web.publisher.SavePublisherDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;


public interface PublisherService {

    List<PublisherDTO> findAll();

    PublisherDTO findOne(Long id) throws Throwable;

    List<PublisherDTO> getByName(String name);

    PublisherDTO save(SavePublisherDTO savePublisherDTO);

    PublisherDTO update(Long id, SavePublisherDTO savePublisherDTO);

    void delete(Long id);
}

@Service
@AllArgsConstructor
class PublisherServiceImpl implements PublisherService {

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
                .orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("Author with id not found"));
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
                        null,
                        savePublisherDTO.getName()
                )
        );
        return saved.toDTO();
    }

    @Override
    public PublisherDTO update(Long id, SavePublisherDTO savePublisherDTO) {
        repository.findById(id).ifPresentOrElse(it -> {
            it.setName(savePublisherDTO.getName());
            repository.saveAndFlush(it);
        }, () -> {
            throw new ResourceNotFoundException("no such publisher");
        });
        return repository.findById(id).get().toDTO();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

