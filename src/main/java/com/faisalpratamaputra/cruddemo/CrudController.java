package com.faisalpratamaputra.cruddemo;

import com.faisalpratamaputra.cruddemo.entity.Publisher;
import com.faisalpratamaputra.cruddemo.entity.PublisherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CrudController {
    @Autowired
    private PublisherRepository publisherRepository;

    @GetMapping("/")
    public String greeting(Model model) {
        publisherRepository.save(new Publisher("Atari"));
        model.addAttribute("publishers", publisherRepository.findAll());
        return "index";
    }

    @GetMapping("/publisher/create")
    public String publisherCreate(Model model) {
        return "publisher";
    }

    @GetMapping("/publisher/update/{id}")
    public String publisherUpdate(@PathVariable("id") Long id, Model model) {
        return "publisher";
    }

    @GetMapping("/publisher/delete/{id}")
    public RedirectView publisherDelete(@PathVariable("id") Long id, Model model) {
        publisherRepository.findById(id).ifPresent(publisher -> publisherRepository.delete(publisher));
        return new RedirectView("/");
    }
}
