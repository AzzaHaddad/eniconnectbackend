package eniconnect.eniconnect.controllers;

import eniconnect.eniconnect.entities.DocOfficiel;
import eniconnect.eniconnect.entities.Etudiant;
import eniconnect.eniconnect.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/etudiants")
@CrossOrigin("http://localhost:8081")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @GetMapping("/{cin}")
    public Etudiant getEtudiantById(@PathVariable long cin) {
        return etudiantService.getEtudiantById(cin);
    }

    @GetMapping("/signin")
    public Etudiant signIn(@RequestParam String email, @RequestParam long password) {
        return etudiantService.signIn(email, password);
    }


   @GetMapping("/alletudiants")
    //@RequestMapping()
    public List<Etudiant> getAllEtudiants()
    {
        return etudiantService.getAllEtudiants();    }

    @RequestMapping(value = "/update/{cin}",method = RequestMethod.PUT)
    public ResponseEntity<String> updateEtudiant(@PathVariable("cin") long cin, @RequestParam(value = "email",defaultValue = "") String email,@RequestParam(value = "numtel",defaultValue = "0") long numtel)
    {
        Etudiant e = etudiantService.getEtudiantById(cin);
        if (e == null) {
            return ResponseEntity.ok().body("etudiant inexistant");
        }
        else
        {
            if(!email.isEmpty())
                e.setEmail(email);
            if(numtel!=0)
                e.setNumeroTelephone(numtel);
            return ResponseEntity.ok().body("etudiant updated successfully with cin "+etudiantService.updateEtudiant(e).getCin());
        }
    }







}