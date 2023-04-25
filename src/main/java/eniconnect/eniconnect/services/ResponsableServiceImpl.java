package eniconnect.eniconnect.services;

import eniconnect.eniconnect.entities.Responsable;
import eniconnect.eniconnect.repositories.ResponsableRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class ResponsableServiceImpl implements ResponsableService{
    @Autowired
    private ResponsableRepository responsableRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Responsable addResponsable(Responsable responsable) {
        String encodedPassword = passwordEncoder.encode(responsable.getPassword());
        responsable.setPassword(encodedPassword);
        responsableRepository.save(responsable);
        return responsableRepository.save(responsable);
    }

    public List<Responsable> getAllResponsables() {
        return responsableRepository.findAll();
    }

    public Responsable signIn(String email, String password) {
        Responsable responsable = responsableRepository.findByEmail(email);
        if (responsable != null) {
            String encodedPassword = passwordEncoder.encode(password);
            if (passwordEncoder.matches(password, responsable.getPassword())) {
                return responsable;
            }
        }
        return null;
    }
    @Override
    public Responsable responsableExistsByEmail(String email) {
        return responsableRepository.findByEmail(email);
    }

    @Override
    public void deleteResponsable(Responsable responsable) {
        responsableRepository.delete(responsable);
    }
}

