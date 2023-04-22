package eniconnect.eniconnect.repositories;

import eniconnect.eniconnect.entities.DocOfficiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocOfficielRepository extends JpaRepository<DocOfficiel,Integer> {

}
