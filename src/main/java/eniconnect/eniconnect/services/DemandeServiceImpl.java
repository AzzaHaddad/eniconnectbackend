package eniconnect.eniconnect.services;

import eniconnect.eniconnect.entities.Demande;
import eniconnect.eniconnect.entities.EtatDemande;
import eniconnect.eniconnect.entities.Etudiant;
import eniconnect.eniconnect.repositories.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DemandeServiceImpl implements DemandeService {
    @Autowired
    private DemandeRepository demandeRepository;
    @Autowired
    private EtudiantService etudiantService;

    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    public Demande getDemandeById(int id) {
        return demandeRepository.findById(id).orElse(null);
    }

    public Demande createDemande(String objet,String contenu, long cin) {
        Etudiant etd = etudiantService.getEtudiantById(cin);
        Demande demande=new Demande();
        demande.setObjet(objet);
        demande.setContenu(contenu);
        demande.setEtat(EtatDemande.EN_ATTENTE);
        demande.setDateDemande(LocalDateTime.now());
        demande.setEtudiant(etd);
        return demandeRepository.save(demande);
    }

    public Demande updateDemande(int id, Demande demandeDetails) {
        Demande demande = getDemandeById(id);
        demande.setObjet(demandeDetails.getObjet());
        demande.setContenu(demandeDetails.getContenu());
        return demandeRepository.save(demande);
    }

    public void deleteDemande(int id) {
        Demande demande = getDemandeById(id);
        demandeRepository.delete(demande);
    }

    /*public List<Demande> getDemandesByEtudiant(long cin) {
        return demandeRepository.findByEtudiant(cin);
    }*/

    public List<Demande> getAllDemandesByEtudiant(long cin)
    {
        return demandeRepository.findByEtudiantCin(cin);
    }
}

