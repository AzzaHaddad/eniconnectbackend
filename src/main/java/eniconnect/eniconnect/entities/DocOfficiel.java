package eniconnect.eniconnect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="doc_officiel")
public class DocOfficiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="file_name")
    private String file_name;
    @Column(name="type")
    private String type;
    @Lob
    @Column(name="file")
    private byte[] file;
    @ManyToOne(targetEntity = Etudiant.class,fetch=FetchType.EAGER)
    @JoinColumn(name="etuddoc_fk",referencedColumnName = "cin",nullable = false)
    private Etudiant etudiant;

    public DocOfficiel(String name, String type, byte[] data,Etudiant etudiant) {
        this.file_name = name;
        this.type = type;
        this.file = data;
        this.etudiant=etudiant;
    }
}
