package eniconnect.eniconnect.controllers;


import eniconnect.eniconnect.entities.Responsable;
import eniconnect.eniconnect.services.EmailService;
import eniconnect.eniconnect.services.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;


@RestController
@RequestMapping("/responsable")
@CrossOrigin(maxAge = 3600)

public class ResponsableController {

    @Autowired
    private ResponsableService responsableService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Long> addResponsable(@RequestBody Responsable requestResponsable) {
        String password =requestResponsable.getPassword();
        Responsable responsable = responsableService.responsableExistsByEmail(requestResponsable.getEmail());
        if (responsable == null) {
            Responsable savedResponsable = responsableService.addResponsable(requestResponsable);
            Long responsableId = savedResponsable.getId();
            String subject = "Account Responsable Created";
            String text = "Cher responsable, \n\nVotre compte eniconnect a été créé avec succès.\n\nEmail: " + savedResponsable.getEmail() + "\nMot de passe: " + password + "\n\nCordialement!";
            try {
                emailService.sendEmail(savedResponsable.getEmail(), subject, text);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            return new ResponseEntity<>(responsableId, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Responsable>> getAllResponsables() {
        List<Responsable> responsables = responsableService.getAllResponsables();
        if (responsables.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(responsables, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteResponsableByEmail(@RequestParam("email") String email) {
        Responsable responsable = responsableService.responsableExistsByEmail(email);
        if (responsable != null) {
            responsableService.deleteResponsable(responsable);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<Responsable> signIn(@RequestBody Responsable requestResponsable) {
        Responsable responsable = responsableService.signIn(requestResponsable.getEmail(), requestResponsable.getPassword());
        if (responsable != null) {
            return new ResponseEntity<>(responsable, HttpStatus.OK);
        } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<Responsable> updateResponsable(@PathVariable("email") String email, @RequestBody Responsable requestResponsable) {
        Responsable responsable = responsableService.responsableExistsByEmail (email);
        if (responsable != null) {
            responsable.setNom(requestResponsable.getNom());
            responsable.setPrenom(requestResponsable.getPrenom());
            responsable.setAdresse(requestResponsable.getAdresse());
            responsable.setTelephone(requestResponsable.getTelephone());
            responsable.setEmail(requestResponsable.getEmail());
            Responsable updatedResponsable = responsableService.updateResponsable(responsable);
            return new ResponseEntity<>(updatedResponsable, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
