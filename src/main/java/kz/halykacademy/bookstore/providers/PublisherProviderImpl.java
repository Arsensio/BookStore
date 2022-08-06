package kz.halykacademy.bookstore.providers;


import kz.halykacademy.bookstore.models.PublisherEntity;
import kz.halykacademy.bookstore.providers.interfaces.PublisherProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class PublisherProviderImpl implements PublisherProvider {

    List<PublisherEntity> publishers;

    public PublisherProviderImpl(){
//        publishers = new ArrayList<>();
//        List<Author> list = new ArrayList<>();
//        list.add(new Author(1L,"Ulykbekov","Arsen","Kadylbekuly", LocalDate.now(),List.of(1L)));        list.add(new Author(1L,"Ulykbekov","Arsen","Kadylbekuly", LocalDate.now(),List.of(1L)));
//        list.add(new Author(2L,"Kamalbekov","Erasyl","Talgatovich", LocalDate.now(),List.of(1L)));
//
//
//
//        List<Long>authors = list.stream().
//                map(author -> author.getId()).
//                collect(Collectors.toList());
//
//
//
//        List<Book>listOfBook = new ArrayList<>();
//        listOfBook.add(new Book(1L,1000,authors,1L,"Opyt Duraka",250, LocalDate.of(2002,05,05)));
//        listOfBook.add(new Book(2L,1000,authors,1L,"Opyt Duraka",250, LocalDate.of(2002,05,05)));
//
//
//        List<Long>books = listOfBook.stream().
//                map(book -> book.getId()).
//                collect(Collectors.toList());
//
//
//        List<Book>listOfBook1 = new ArrayList<>();
//        listOfBook1.add(new Book(3L,1000,authors,2L,"Opyt Duraka",250, LocalDate.of(2002,05,05)));
//        listOfBook1.add(new Book(4L,1000,authors,2L,"Opyt Duraka",250, LocalDate.of(2002,05,05)));
//
//
//        List<Long>books1 = listOfBook1.stream().
//                map(book -> book.getId()).
//                collect(Collectors.toList());
//
//        publishers.add(new Publisher(1L,"ATAMEKEN",books));
//        publishers.add(new Publisher(2L,"NEW BOOKS",books1));
//
//
//        System.out.println("ARRAY LIST ARE CREATED");
    }

    @Override
    public List<PublisherEntity> getAll() {
        return publishers.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public PublisherEntity get(Long id) {
        return publishers.get(Math.toIntExact(id));
    }

    @Override
    public List<PublisherEntity> get(String publisherName) {
        List<PublisherEntity> authorsWithName = publishers
                .stream()
                .filter(name -> name.getName().contains(publisherName))
                .collect(Collectors.toList());

        return authorsWithName;
    }

    @Override
    public Long save(PublisherEntity publisher) {
        publishers.add(publisher);
        Long index = Long.valueOf(publishers.size() - 1);
        publisher.setId(index);
        return index;
    }

    @Override
    public void update(PublisherEntity publisher) {
        publishers.set(Math.toIntExact(publisher.getId()), publisher);
    }

    @Override
    public void delete(PublisherEntity publisher) {
        publishers.set(Math.toIntExact(publisher.getId()), null);

    }
}
