package eniconnect.eniconnect.services;

import eniconnect.eniconnect.entities.Etudiant;
import eniconnect.eniconnect.repositories.EtudiantRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class EtudiantServiceImpl implements EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;


    @Override
    public Etudiant getEtudiantById(long cin) {
        return etudiantRepository.findById(cin).orElse(null);
    }
    @Override
    public Etudiant signIn(String email, long password)
            //password = CIN
    {

        Etudiant etudiant = etudiantRepository.signIn(email, password);
        if (etudiant != null) {
            System.out.print("User indentified");        }
        else
        {
            System.out.print("user no");        }
        return etudiant;
    }
    @Override
    public List<Etudiant> getAllEtudiants(){
        return etudiantRepository.findAll();
    }
    @Override
    public Etudiant updateEtudiant(Etudiant e)
    {
        return etudiantRepository.save(e);
    }
    @Override
    public List<Etudiant> getEtudiantsByGroupe(int niveau,char groupe)
    {
        return etudiantRepository.getEtudiantsByGroupe(niveau,groupe);
    }



}
