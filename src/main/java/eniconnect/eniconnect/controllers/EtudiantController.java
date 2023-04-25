package eniconnect.eniconnect.controllers;

import eniconnect.eniconnect.entities.Etudiant;
import eniconnect.eniconnect.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}