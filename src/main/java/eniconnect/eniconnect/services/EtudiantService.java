package eniconnect.eniconnect.services;

import eniconnect.eniconnect.entities.Etudiant;

import java.util.List;

public interface EtudiantService {


    Etudiant getEtudiantById(long cin);
    Etudiant signIn(String email, long password);
    List<Etudiant> getAllEtudiants();
    Etudiant updateEtudiant(Etudiant e);
    List<Etudiant> getEtudiantsByGroupe(int niveau,char groupe);


}
