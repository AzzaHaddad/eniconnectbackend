package eniconnect.eniconnect.services;

import eniconnect.eniconnect.entities.DocOfficiel;
import eniconnect.eniconnect.entities.EmploiTemps;

import java.util.stream.Stream;

public interface EmploiTempsService {

    EmploiTemps getEmploi(int niveau,char groupe,int semestre,int annee);
    EmploiTemps getEmploiById(int id);
    void deleteEmploi(int id);
    Stream<EmploiTemps> getAllEmplois();
}
