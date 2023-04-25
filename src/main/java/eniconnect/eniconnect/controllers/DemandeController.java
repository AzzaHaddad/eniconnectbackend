package eniconnect.eniconnect.controllers;

import eniconnect.eniconnect.entities.Demande;
import eniconnect.eniconnect.entities.Etudiant;
import eniconnect.eniconnect.services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demandes")
@CrossOrigin("http://localhost:8081")

public class DemandeController {
    @Autowired
    private DemandeService demandeService;

    @RequestMapping(value = "/alldemandes",method = RequestMethod.GET)
    public List<Demande> getAllDemandes() {
        return demandeService.getAllDemandes();
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Demande> getDemandeById(@PathVariable(value = "id") int id) {
        Demande demande = demandeService.getDemandeById(id);
        return ResponseEntity.ok().body(demande);
    }


    @RequestMapping(value = "/createDemande",method = RequestMethod.POST)
    public Demande createDemande(@RequestParam String objet,@RequestParam String contenu) {
        return demandeService.createDemande(objet,contenu);
    }

    @RequestMapping(value = "/updateDemande/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Demande> updateDemande(@PathVariable(value = "id") int id, @RequestBody Demande demandeDetails) {
        Demande updatedDemande = demandeService.updateDemande(id, demandeDetails);
        return ResponseEntity.ok(updatedDemande);
    }

    @RequestMapping(value = "/deleteDemande/{id}",method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteDemande(@PathVariable(value = "id") int id) {
        demandeService.deleteDemande(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

  /*  @RequestMapping(value = "/getDemandeByEtudiant/{cin}",method = RequestMethod.GET)
    public List<Demande> getDemandesByEtudiant(@PathVariable(value = "cin") long cin) {
        Etudiant etudiant = new Etudiant();
        etudiant.setCin(cin);
        return demandeService.getDemandesByEtudiant(cin);
    }*/
}

