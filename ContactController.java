package com.example.EasyBytes.controller;

import com.example.EasyBytes.model.Contact;
import com.example.EasyBytes.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        return contactService.updateContact(id, contact);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }
}
