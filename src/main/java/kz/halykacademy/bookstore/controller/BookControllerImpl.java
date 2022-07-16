package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.controller.interfaces.BookController;
import kz.halykacademy.bookstore.service.BookServiceImpl;
import kz.halykacademy.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;


import java.util.List;

public class BookControllerImpl implements BookController {

    @Autowired
    private BookServiceImpl service;


    public BookControllerImpl(BookServiceImpl service) {
        this.service = service;
    }

    @Override
    public String listAll(Model model) {
        return returnList(model);
    }

    private String returnList(Model model) {
        List<Book> books = service.listAll();
        System.out.println(books);

        model.addAttribute("books", books);
        return "book/list";
    }

    @Override
    public String getOne(Long id, Model model) {
        Book book = service.getOne(id);

        model.addAttribute("mode", "Update");
        model.addAttribute("modeTitle", "UpdateExisting");
        model.addAttribute("isUpdate", true);
        model.addAttribute("book", book);


        return "book/form";
    }
//
//    @Override
//    public String newBookForm(Model model) {
//        model.addAttribute("mode", "New");
//        model.addAttribute("modeTitle", "Add new");
//        model.addAttribute("isUpdate", false);
//        return "book/form";
//    }
//
//    @Override
//    public String save(MultipartFile image, Book book, Model model) {
//        Book saved = service.save(book);
//
//        if (!image.isEmpty())
//            write(image, saved.getId() + "");
//
//        return "redirect:book";
//    }
//
//    @Override
//    public String delete(Long id, Model model) {
//        service.delete(id);
//        return returnList(model);
//    }
//
//    @Override
//    public HttpEntity<FileSystemResource> image(Long id) {
//        FileSystemResource fileSystemResource = read(id + "");
//
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + id);
//
//        return new HttpEntity(fileSystemResource, header);
//    }
//

//
//    public String write(MultipartFile file, String fileName) {
//
//        String folderPath = System.getProperty("user.home");
//        String filePath = folderPath + "/Pictures/" + fileName;
//
//        try {
//            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return filePath;
//    }
//
//    private FileSystemResource read(String fileName) {
//        String folderPath = System.getProperty("user.home");
//        String filePath = folderPath + "/Pictures/" + fileName;
//
//        return new FileSystemResource(new File(filePath));
//    }


}
