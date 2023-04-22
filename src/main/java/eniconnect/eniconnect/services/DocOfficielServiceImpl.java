package eniconnect.eniconnect.services;

import eniconnect.eniconnect.entities.DocOfficiel;
import eniconnect.eniconnect.entities.Etudiant;
import eniconnect.eniconnect.repositories.DocOfficielRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class DocOfficielServiceImpl implements DocOfficielService{

    @Autowired
    private DocOfficielRepository docRepository;
    @Autowired
    private EtudiantService etudService;

    public DocOfficiel store(MultipartFile file, String type, long cin) throws IOException {
        String file_name = StringUtils.cleanPath(file.getOriginalFilename());
        Etudiant etud = etudService.getEtudiantById(cin);
        DocOfficiel FileDB = new DocOfficiel(file_name, type, file.getBytes(),etud);
        return docRepository.save(FileDB);
    }


    public byte[] getFile(int id) {
        return docRepository.findById(id).orElse(null).getFile();
    }
    public DocOfficiel getDocument(int id)
    {
        return docRepository.findById(id).orElse(null);
    }
    public Stream<DocOfficiel> getAllFiles() {
        return docRepository.findAll().stream();
    }


    //// not used anymore: but this method retrieves the binary data from database and write to a pdf file
    public void writeToFile(int id) throws IOException {
        Optional<DocOfficiel> documentOptional = docRepository.findById(id);
        /*if (documentOptional.isPresent()) {
            System.out.println("heelo hello");
            DocOfficiel document = documentOptional.get();
            System.out.println("heelo hello");
            String filePath = "C:\\Users\\azzah\\OneDrive\\Desktop\\2INGINFOC C\\SEMESTER 2\\projet web\\eniconnect\\uploads";
            Files.write(Paths.get(filePath), document.getFile());
            System.out.println("heelo hello");

        } else {
            throw new FileNotFoundException("Document not found with ID: " + id);
        }*/
        DocOfficiel document = docRepository.findById(id).orElse(null);
        if(document == null) {
            throw new FileNotFoundException("Document with ID " + id + " not found");
        }
        String fileName = document.getFile_name() ;
        String filePath = "uploads/" + fileName;
        byte[] fileData = document.getFile();
        FileOutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(fileData);
        outputStream.close();
    }


    public void deleteDocument(int id)
    {
        DocOfficiel doc = docRepository.findById(id).orElse(null);
        if (doc != null) {
            docRepository.delete(doc);
        }

    }


}

