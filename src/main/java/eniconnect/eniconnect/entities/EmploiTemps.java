package eniconnect.eniconnect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "emploi_du_temps")
public class EmploiTemps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="niveau")
    private int niveau;
    @Column(name="groupe")
    private char groupe;
    @Column(name="semestre")
    private int semestre;
    @Column(name = "annee")
    private int annee= Year.now().getValue();
}
