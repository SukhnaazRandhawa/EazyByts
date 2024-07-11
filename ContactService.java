package com.example.EasyBytes.service;

import com.example.EasyBytes.model.Contact;
import com.example.EasyBytes.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact updateContact(Long id, Contact contact) {
        Contact existingContact = contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        existingContact.setName(contact.getName());
        existingContact.setEmail(contact.getEmail());
        existingContact.setPhone(contact.getPhone());
        return contactRepository.save(existingContact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
