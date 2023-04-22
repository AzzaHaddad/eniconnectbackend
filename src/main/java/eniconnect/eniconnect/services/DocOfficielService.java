package eniconnect.eniconnect.services;

import eniconnect.eniconnect.entities.DocOfficiel;
import eniconnect.eniconnect.entities.Etudiant;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface DocOfficielService {

    DocOfficiel store(MultipartFile file, String type, long cin) throws IOException;
    byte[] getFile(int id);
    DocOfficiel getDocument(int id);
    Stream<DocOfficiel> getAllFiles();
    void writeToFile(int id) throws IOException;
    void deleteDocument(int id);
}
