package eniconnect.eniconnect.repositories;


import eniconnect.eniconnect.entities.EmploiTemps;
import eniconnect.eniconnect.services.EmploiTempsServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploiTempsRepository extends JpaRepository<EmploiTemps, Integer> {

@Query("SELECT e from EmploiTemps e where e.niveau=?1 and e.groupe=?2 and e.semestre=?3 and e.annee=?4")
    public EmploiTemps getEmploi(int niveau, char groupe, int semestre, int annee);
}
