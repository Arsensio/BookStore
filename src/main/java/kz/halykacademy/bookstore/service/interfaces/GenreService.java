package kz.halykacademy.bookstore.service.interfaces;

import kz.halykacademy.bookstore.model.GenreEntity;
import kz.halykacademy.bookstore.model.PublisherEntity;
import kz.halykacademy.bookstore.store.interfaces.BookGenreRepository;
import kz.halykacademy.bookstore.store.interfaces.GenreRepository;
import kz.halykacademy.bookstore.web.author.AuthorDTO;
import kz.halykacademy.bookstore.web.author.SaveAuthorDTO;
import kz.halykacademy.bookstore.web.genre.GenreDTO;
import kz.halykacademy.bookstore.web.genre.SaveGenreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GenreService {

    public List<GenreDTO> findAll();

    public GenreDTO findOne(Long id) throws Throwable;

    public List<GenreDTO> findAllByName(String name);

    public GenreEntity save(SaveGenreDTO saveGenre);

    public void delete(Long id);

    public GenreDTO update(Long id, SaveGenreDTO saveGenreDTO);
}
@Service
class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookGenreRepository bookGenreRepository;


    @Override
    public List<GenreDTO> findAll() {
        return genreRepository.findAll()
                .stream().
                map(GenreEntity::toDTO).
                toList();
    }

    @Override
    public GenreDTO findOne(Long id) throws Throwable {
        return genreRepository.findById(id)
                .get()
                .toDTO();
    }

    @Override
    public List<GenreDTO> findAllByName(String name) {
        return genreRepository.findAllByNameContaining(name)
                .stream().
                map(GenreEntity::toDTO).
                toList();
    }

    @Override
    public GenreEntity save(SaveGenreDTO saveGenre) {
        GenreEntity saved = genreRepository.save(
                new GenreEntity(
                        saveGenre.getId(),
                        saveGenre.getName(),
                        null
                )
        );
        return saved;
    }

    @Override
    public void delete(Long id) {
        bookGenreRepository.deleteAllByGenreId(id);
        genreRepository.deleteById(id);
    }

    @Override
    public GenreDTO update(Long id, SaveGenreDTO saveGenreDTO) {

        genreRepository.findById(id).ifPresentOrElse(it -> {
            it.setName(saveGenreDTO.getName());
            genreRepository.saveAndFlush(it);
        },()->{
            System.out.println("no such publisher");
        });

        return genreRepository.findById(id).get().toDTO();
    }


}
