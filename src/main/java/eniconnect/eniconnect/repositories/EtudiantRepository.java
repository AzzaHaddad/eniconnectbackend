package eniconnect.eniconnect.repositories;

import eniconnect.eniconnect.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> //extends Crudrepository
{


    @Query("SELECT e FROM Etudiant e WHERE e.email_enicar = ?1 AND e.cin = ?2")
    public Etudiant signIn(String email,long password);
}
