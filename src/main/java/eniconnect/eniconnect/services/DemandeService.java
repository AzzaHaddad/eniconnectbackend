package eniconnect.eniconnect.services;

import eniconnect.eniconnect.entities.Demande;
import eniconnect.eniconnect.entities.EtatDemande;

import java.time.LocalDateTime;
import java.util.List;

public interface DemandeService {
    public List<Demande> getAllDemandes();

    public Demande getDemandeById(int id) ;

    public Demande createDemande(String objet,String contenu, long cin);

    Demande updateDemande(int id, Demande demandeDetails) ;

    void deleteDemande(int id) ;

    List<Demande> getAllDemandesByEtudiant(long cin);


    //  List<Demande> getDemandesByEtudiant(long cin) ;
}
