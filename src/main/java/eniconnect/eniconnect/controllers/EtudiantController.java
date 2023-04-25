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

    @RequestMapping(value = "/{cin}",method = RequestMethod.GET)
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable long cin) {
        return ResponseEntity.ok().body(etudiantService.getEtudiantById(cin));
    }

    @RequestMapping(value = "/signin",method = RequestMethod.GET)
    public ResponseEntity<Etudiant> signIn(@RequestParam String email, @RequestParam long password) {
        Etudiant e=etudiantService.signIn(email, password);
        if(e==null)
            return ResponseEntity.ok().body(null);
        else
            return ResponseEntity.ok().body(e);
    }


    @GetMapping("/alletudiants")
    //@RequestMapping()
    public List<Etudiant> getAllEtudiants()
    {
        return etudiantService.getAllEtudiants();
    }

    @GetMapping("/alletudiantsByGroupe/{niveau}/{groupe}")
    public ResponseEntity<List<Etudiant>> getEtudiantsByGroupe(@PathVariable("niveau") int niveau,@PathVariable("groupe") char groupe)
    {
        List<Etudiant> etudiants = etudiantService.getEtudiantsByGroupe(niveau, groupe);

        if (etudiants.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(etudiants);
        }
    }
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