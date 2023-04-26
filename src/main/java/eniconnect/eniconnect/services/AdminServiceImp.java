package eniconnect.eniconnect.services;


import eniconnect.eniconnect.entities.Admin;
import eniconnect.eniconnect.entities.Responsable;
import eniconnect.eniconnect.repositories.AdminRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class AdminServiceImp implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin signIn(String username, String password) {

        Admin admin = adminRepository.findByUsernameAndPassword(username,password );
        if (admin == null){
            return null;
        }
        else{return admin;}

    }


}
