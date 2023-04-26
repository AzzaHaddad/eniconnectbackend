package eniconnect.eniconnect.controllers;


import eniconnect.eniconnect.entities.Admin;
import eniconnect.eniconnect.entities.Responsable;
import eniconnect.eniconnect.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/admin")
@CrossOrigin(maxAge = 3600)
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin requestAdmin) {
            Admin savedAdmin = adminService.addAdmin(requestAdmin);
            if (savedAdmin!=null){
            return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @PostMapping("/signin")
    public ResponseEntity<Admin> signIn(@RequestBody Admin requestAdmin) {
        Admin admin = adminService.signIn(requestAdmin.getUsername(), requestAdmin.getPassword());
        if (admin != null) {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
