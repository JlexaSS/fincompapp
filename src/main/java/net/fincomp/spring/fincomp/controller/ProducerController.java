package net.fincomp.spring.fincomp.controller;

import net.fincomp.spring.fincomp.model.Category;
import net.fincomp.spring.fincomp.model.Producer;
import net.fincomp.spring.fincomp.service.CategoryService;
import net.fincomp.spring.fincomp.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProducerController {
    @Autowired
    private ProducerService producerService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/producer")
    public String add(Producer producer, Model model){
        producerService.saveProducer(producer);
        model.addAttribute("producers", producerService.findAll());
        return "producerList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("admin/producer/edit")
    public String saveProducer(
            @RequestParam String name,
            @RequestParam String country,
            @RequestParam("producerId") Producer producer
    ){
        producerService.saveProducer(producer, name, country);

        return "redirect:/admin/producer";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/producer")
    public String findProducer(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Producer> producers = producerService.findAll();

        if (filter != null && !filter.isEmpty()) {
            producers = producerService.findByNameContaining(filter);
        } else {
            producers = producerService.findAll();
        }

        model.addAttribute("producers", producers);
        model.addAttribute("filter", filter);

        return "producerList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/producer/delete/{producer}")
    public String deleteUser(@PathVariable("producer") Long producer){
        producerService.deleteById(producer);

        return "redirect:/admin/producer";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/producer/edit/{producer}")
    public String userEditForm(@PathVariable Producer producer, Model model){
        model.addAttribute("producers", producer);
        return "producerEdit";
    }
}
