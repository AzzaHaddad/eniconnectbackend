package eniconnect.eniconnect.services;

import eniconnect.eniconnect.entities.Responsable;
import eniconnect.eniconnect.repositories.ResponsableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface ResponsableService{
    public Responsable addResponsable(Responsable responsable);
    public List<Responsable> getAllResponsables();
    public Responsable signIn(String email, String password);
    Responsable responsableExistsByEmail(String email);
    void deleteResponsable(Responsable responsable);
    public Responsable updateResponsable(Responsable responsable);




}
