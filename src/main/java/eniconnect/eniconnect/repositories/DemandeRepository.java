package eniconnect.eniconnect.repositories;

import eniconnect.eniconnect.entities.Demande;
import eniconnect.eniconnect.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Integer> {

    List<Demande> findByEtudiantCin(long cin);

}

