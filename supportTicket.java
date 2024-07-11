package com.example.EasyBytes.service;

import com.example.EasyBytes.model.SupportTicket;
import com.example.EasyBytes.repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class supportTicket {

    @Autowired
    private SupportTicketRepository supportTicketRepository;

    @Autowired
    private EmailService emailService;

    public SupportTicket createSupportTicket(SupportTicket supportTicket) {
        SupportTicket savedTicket = supportTicketRepository.save(supportTicket);
        // Send email notification
        emailService.sendEmail(
                supportTicket.getUserEmail(),
                "New Support Ticket Created",
                "Your support ticket has been created: " + supportTicket.getDescription()
        );
        return savedTicket;
    }

    public SupportTicket updateSupportTicket(SupportTicket supportTicket) {
        SupportTicket updatedTicket = supportTicketRepository.save(supportTicket);
        // Send email notification if the status is updated
        if (supportTicket.isStatusUpdated()) {
            emailService.sendEmail(
                    supportTicket.getUserEmail(),
                    "Support Ticket Status Updated",
                    "The status of your support ticket: " + supportTicket.getDescription() + " has been updated to: " + supportTicket.getStatus()
            );
        }
        return updatedTicket;
    }
}
