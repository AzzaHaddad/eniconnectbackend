package eniconnect.eniconnect.controllers;

import eniconnect.eniconnect.entities.DocOfficiel;
import eniconnect.eniconnect.entities.EmploiTemps;
import eniconnect.eniconnect.services.EmploiTempsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/emploiTemps")
@CrossOrigin("http://localhost:8081")
public class EmploiTempsController {

    @Autowired
    EmploiTempsService emptservice;

    @RequestMapping(value = "/consultById/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> viewEmploiById(@PathVariable("id") int id) throws IOException {
        EmploiTemps empt = emptservice.getEmploiById(id);
        if (empt == null) {
            return ResponseEntity.notFound().build();
        }
        String filePath = "file:///C:/Users/azzah/OneDrive/Desktop/ZOUZA/files/emploiTemps/" + empt.getNiveau()+empt.getGroupe()+empt.getSemestre()+empt.getAnnee()+".pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + empt.getNiveau()+empt.getGroupe()+empt.getSemestre()+empt.getAnnee()+".pdf");

        Resource resource = new UrlResource(filePath);
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        InputStreamResource inputStreamResource = new InputStreamResource(resource.getInputStream());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_PDF)
                .body(inputStreamResource);
    }
    @RequestMapping(value = "/consult", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> viewEmploi(@RequestParam("niveau") int niveau,@RequestParam("groupe") char groupe,@RequestParam("semestre") int semestre,@RequestParam("annee") int annee) throws IOException {
        EmploiTemps empt = emptservice.getEmploi(niveau, groupe, semestre, annee);
        if (empt == null) {
            return ResponseEntity.notFound().build();
        }
        String filePath = "file:///C:/Users/azzah/OneDrive/Desktop/ZOUZA/emploiTemps/" + niveau + groupe + semestre + annee+".pdf";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + empt.getNiveau()+empt.getGroupe()+empt.getSemestre()+empt.getAnnee()+".pdf");
        Resource resource = new UrlResource(filePath);
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        InputStreamResource inputStreamResource = new InputStreamResource(resource.getInputStream());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_PDF)
                .body(inputStreamResource);
    }
}
