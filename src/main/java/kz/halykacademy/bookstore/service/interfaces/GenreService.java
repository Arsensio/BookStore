package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.exceptions.ResourceNotFoundException;
import kz.halykacademy.bookstore.models.GenreEntity;
import kz.halykacademy.bookstore.store.interfaces.GenreRepository;
import kz.halykacademy.bookstore.web.genre.GenreDTO;
import kz.halykacademy.bookstore.web.genre.SaveGenreDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GenreService {

    List<GenreDTO> findAll();

    GenreEntity findOne(Long id) throws Throwable;

    List<GenreDTO> findAllByName(String name);

    GenreEntity save(SaveGenreDTO saveGenre);

    void delete(Long id);

    GenreDTO update(Long id,SaveGenreDTO saveGenreDTO);
}

@Service
@AllArgsConstructor
class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;

    @Override
    public List<GenreDTO> findAll() {
        return genreRepository.findAll()
                .stream().
                map(GenreEntity::toDTO).
                toList();
    }

    @Override
    public GenreEntity findOne(Long id) throws Throwable {
        return genreRepository.findById(id).get();
    }

    @Override
    public List<GenreDTO> findAllByName(String name) {
        return genreRepository.findAllByNameContainingIgnoreCase(name)
                .stream().
                map(GenreEntity::toDTO).
                toList();
    }

    @Override
    public GenreEntity save(SaveGenreDTO saveGenre) {
        GenreEntity saved = genreRepository.save(
                new GenreEntity(
                        null,
                        saveGenre.getName()
                )
        );
        return saved;
    }

    @Override
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public GenreDTO update(Long id,SaveGenreDTO saveGenreDTO) {
        genreRepository.findById(id).ifPresentOrElse(it -> {
            it.setName(saveGenreDTO.getName());
            genreRepository.saveAndFlush(it);
        }, () -> {
            throw new ResourceNotFoundException("No such genre present");
        });
        return genreRepository.findById(id).get().toDTO();
    }


}
