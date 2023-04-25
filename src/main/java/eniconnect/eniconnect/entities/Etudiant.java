package eniconnect.eniconnect.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.grammars.hql.HqlParser;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "etudiant")
public class Etudiant {

    @Id
    @Column(name = "cin")
    private long cin;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "emailEnicar")
    private String email_enicar;

    @Column(name = "numeroTelephone")
    private long numeroTelephone;

    @Column(name = "filiere")
    private String filiere;

    @Column(name = "niveau")
    private int niveau;
    @Column(name="groupe")
    private char groupe;
    @OneToMany(targetEntity = DocOfficiel.class,mappedBy = "etudiant",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<DocOfficiel> docOfficiels;
    @OneToMany(targetEntity = Demande.class,mappedBy = "etudiant",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Demande> demandes;


    @JsonManagedReference
    public List<DocOfficiel> getDocOfficiels() {
        return docOfficiels;
    }
    @JsonManagedReference
    public List<Demande> getDemandes() {
        return demandes;
    }

    //we're not gonna manipulate students
   /* @PrePersist
    @PreUpdate
    private void validateFiliere() {
        if (filiere != null && !isValidFiliere(filiere)) {
            throw new IllegalArgumentException("Invalid value for filiere column");
        }
    }

    private boolean isValidFiliere(String filiere) {
        // Define your allowed values for filiere here
        String[] allowedFilieres = {"informatique", "industriel", "mecatronique", "electrique","infotronique"};
        for (String allowedFiliere : allowedFilieres) {
            if (allowedFiliere.equalsIgnoreCase(filiere)) {
                return true;
            }
        }
        return false;
    }*/

}




