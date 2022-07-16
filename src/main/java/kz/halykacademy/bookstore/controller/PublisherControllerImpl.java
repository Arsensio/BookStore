package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.controller.interfaces.PublisherController;
import kz.halykacademy.bookstore.model.Publisher;
import kz.halykacademy.bookstore.service.interfaces.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.List;

public class PublisherControllerImpl implements PublisherController {
    @Autowired
    private PublisherService service;


    public PublisherControllerImpl(PublisherService service) {
        this.service = service;
    }

    @Override
    public String listAll(Model model) {
        return returnList(model);
    }

    private String returnList(Model model) {
        List<Publisher> publishers = service.listAll();
        System.out.println(publishers);

        model.addAttribute("publishers", publishers);
        return "publisher/list";
    }

    @Override
    public String getOne(Long id, Model model) {
        Publisher publisher = service.getOne(id);

        model.addAttribute("mode", "Update");
        model.addAttribute("modeTitle", "UpdateExisting");
        model.addAttribute("isUpdate", true);
        model.addAttribute("publisher", publisher);


        return "publisher/form";
    }

    @Override
    public String getByName(String name, Model model) {
        List<Publisher> publisher = service.getByName(name);

        model.addAttribute("mode", "Update");
        model.addAttribute("modeTitle", "UpdateExisting");
        model.addAttribute("isUpdate", true);
        model.addAttribute("publisher", publisher);


        return "publisher/formName";
    }


}
