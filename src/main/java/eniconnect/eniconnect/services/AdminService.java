package eniconnect.eniconnect.services;

import eniconnect.eniconnect.entities.Admin;

public interface AdminService {

    public Admin addAdmin(Admin admin);

    public Admin signIn(String username, String password);


}
