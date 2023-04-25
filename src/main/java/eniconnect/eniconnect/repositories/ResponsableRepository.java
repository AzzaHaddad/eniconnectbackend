package eniconnect.eniconnect.repositories;

import eniconnect.eniconnect.entities.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableRepository  extends JpaRepository<Responsable, Long> {

    Responsable findByEmailAndPassword(String email, String password);
    void delete(Responsable responsable);
    Responsable findByEmail(String email);

}

