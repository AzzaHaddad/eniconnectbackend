package eniconnect.eniconnect.controllers;

import eniconnect.eniconnect.entities.Demande;
import eniconnect.eniconnect.entities.Etudiant;
import eniconnect.eniconnect.services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demandes")
@CrossOrigin(maxAge = 3600)

public class DemandeController {
    @Autowired
    private DemandeService demandeService;

    @RequestMapping(value = "/alldemandes", method = RequestMethod.GET)
    public ResponseEntity<List<Demande>> getAllDemandes() {
        List<Demande> demandes = demandeService.getAllDemandes();
        if (demandes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(demandes, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/alldemandesByCin", method = RequestMethod.GET)
    public ResponseEntity<List<Demande>> getAllDemandesByEtudiant(long cin) {
        List<Demande> demandes = demandeService.getAllDemandesByEtudiant(cin);
        if (demandes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(demandes, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Demande> getDemandeById(@PathVariable(value = "id") int id) {
        Demande demande = demandeService.getDemandeById(id);
        return ResponseEntity.ok().body(demande);
    }


    @RequestMapping(value = "/createDemande1", method = RequestMethod.POST )
    public Demande createDemande1(@RequestParam String objet, @RequestParam String contenu , @RequestParam(name = "cin") long cin) {
        return demandeService.createDemande(objet, contenu , cin);
    }

    @RequestMapping(value = "/createDemande", method = RequestMethod.POST)
    public ResponseEntity<Integer> createDemande(@RequestParam String objet, @RequestParam String contenu, @RequestParam(name = "cin") long cin) {
        Demande dem = demandeService.createDemande(objet, contenu, cin);
        if (dem != null) {

            return new ResponseEntity<>(dem.getId(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/updateDemande/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Demande> updateDemande(@PathVariable(value = "id") int id, @RequestBody Demande demandeDetails) {
        Demande updatedDemande = demandeService.updateDemande(id, demandeDetails);
        return ResponseEntity.ok(updatedDemande);
    }

    @RequestMapping(value = "/deleteDemande/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteDemande(@PathVariable(value = "id") int id) {
        demandeService.deleteDemande(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}