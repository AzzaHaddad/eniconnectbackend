package eniconnect.eniconnect.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="demande")
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="objet")
    private String objet;

    @Column(name="contenu")
    private String contenu;

    @Column(name="etat")
    @Enumerated(EnumType.STRING)
    private EtatDemande etat=EtatDemande.EN_ATTENTE;

    @Column(name="dateDemande")
    private LocalDateTime dateDemande = LocalDateTime.now();

    @Nullable
    @Column(name="reponse")
    private String reponse;

    @Column(name="dateReponse")
    @Nullable
    private LocalDateTime dateReponse;
    @ManyToOne(targetEntity = Etudiant.class,fetch=FetchType.EAGER)
    @JoinColumn(name="etudid",referencedColumnName = "cin",nullable = false)
    private Etudiant etudiant;

    @JsonBackReference
    public Etudiant getEtudiant() {
        return etudiant;
    }


}
