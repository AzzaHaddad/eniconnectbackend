package eniconnect.eniconnect.services;

import eniconnect.eniconnect.entities.Demande;
import eniconnect.eniconnect.entities.DocOfficiel;
import eniconnect.eniconnect.entities.EmploiTemps;
import eniconnect.eniconnect.repositories.EmploiTempsRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class EmploiTempsServiceImpl implements EmploiTempsService {
    @Autowired
    private EmploiTempsRepository emptrepo;


    public EmploiTemps getEmploi(int niveau, char groupe, int semestre, int annee)
    {
        return emptrepo.getEmploi(niveau,groupe,semestre,annee);
    }
    public Stream<EmploiTemps> getAllEmplois() {
        return emptrepo.findAll().stream();
    }
   public  EmploiTemps getEmploiById(int id)
    {
        return emptrepo.findById(id).orElse(null);
    }
    public void deleteEmploi(int id)
    {
        EmploiTemps empt = emptrepo.findById(id).orElse(null);
        if (empt != null) {
            emptrepo.delete(empt);
        }
    }
}
