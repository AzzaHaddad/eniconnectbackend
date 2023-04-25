package eniconnect.eniconnect.controllers;

import eniconnect.eniconnect.entities.DocOfficiel;
import eniconnect.eniconnect.repositories.DocOfficielRepository;
import eniconnect.eniconnect.services.DocOfficielService;
import eniconnect.eniconnect.services.EmailService;
import eniconnect.eniconnect.services.EtudiantService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.print.Doc;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/documents")
@CrossOrigin("http://localhost:8081")
public class DocOfficielController {

   @Autowired
    private DocOfficielService documentService;
   @Autowired
   private DocOfficielRepository docRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EtudiantService etudiantService;
////////////////////////////////////////********upload/update document*********///////////////////////////////////////////////////
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file, @RequestParam(name = "type") String type, @RequestParam(name = "cin") long cin) throws IOException, MessagingException {

        try{
            String subject = "New Document Uploaded";
            String text = "Cher etudiant, \n\nUn document a ete ajoute a votre compte eniconnect: " + file.getOriginalFilename() + "\n\nCordialement!";
            emailService.sendEmail(etudiantService.getEtudiantById(cin).getEmail(), subject, text);
            //documentService.writeToFile(documentService.store(file,type,cin).getId()); // call writeToFile method to ensure file is saved correctly
            return ResponseEntity.ok().body("Document uploaded or updated successfully with ID "+documentService.store(file,type,cin).getId());}
     catch (IOException e) {
        e.printStackTrace();
        // Return an error response if the upload fails
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload document");
    }
    }



    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<String> updateDocument(@PathVariable("id") int id,@RequestParam(value = "file",required = false) MultipartFile file,@RequestParam(value = "type",defaultValue = "") String type) throws IOException, MessagingException {
                DocOfficiel document = documentService.getDocument(id);
                if (document == null) {
                    return ResponseEntity.ok().body("Document inexistant");
                }
                else
                {
                    //&&((document.getType()!=type)||(Arrays.equals(document.getFile(),file.getBytes())))
                    if((file!=null||(!type.isEmpty()))) {
                        if ((!type.isEmpty()))
                            document.setType(type);
                        if (file != null)  {
                            document.setFile(file.getBytes());
                            document.setFile_name(StringUtils.cleanPath(file.getOriginalFilename()));
                            System.out.println(file.getOriginalFilename());
                        }
                        String subject = "UPDATE DOCUMENT";
                        String text = "Cher etudiant, \n\nle document " + document.getFile_name() + " a ete modifie \n\nCordialement!";
                        emailService.sendEmail(etudiantService.getEtudiantById(document.getEtudiant().getCin()).getEmail(), subject, text);

                        return ResponseEntity.ok().body("Document updated successfully with ID " + docRepository.save(document).getId());
                    }
                    else
                        return ResponseEntity.ok().body("Pas de mis à jour car pas de changement des attributs");

                }
    }

            ////view the document without retreiving binary data from databse, we give the path of the directory
    //where the files are stored
    @RequestMapping(value = "/consult/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> viewDocument(@PathVariable("id") int id) throws IOException {
        DocOfficiel document = documentService.getDocument(id);
        if (document == null) {
            return ResponseEntity.notFound().build();
        }
        String fileName = document.getFile_name();
        String filePath = "file:///C:/Users/azzah/OneDrive/Desktop/ZOUZA/files/" + fileName;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + fileName);

        Resource resource = new UrlResource(filePath);
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        InputStreamResource inputStreamResource = new InputStreamResource(resource.getInputStream());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_PDF)
                .body(inputStreamResource);
    }

    //////////////// DOWNLOAD THE FILE WITHOUT DISPLAYING THE DOCUMENT USING FILE ID
    @GetMapping("/downloadById/{id}")
    public ResponseEntity<InputStreamResource> downloadDocumentById(@PathVariable int id) throws IOException {
        DocOfficiel document = documentService.getDocument(id);
        if (document == null) {
            return ResponseEntity.notFound().build();
        }
        String directoryPath = "C:\\\\Users\\\\azzah\\\\OneDrive\\\\Desktop\\\\ZOUZA\\\\files\\\\"; // the directory path where your files are stored
        File file = new File(directoryPath + document.getFile_name());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Disposition", "attachment; filename=" + document.getFile_name()); // Add this line


        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }



    //////////////// DOWNLOAD THE FILE WITHOUT DISPLAYING THE DOCUMENT USING FILE NAME
    @GetMapping("/downloadByName/{fileName}")
    public ResponseEntity<InputStreamResource> downloadDocumentByName(@PathVariable String fileName) throws IOException {
        String directoryPath = "C:\\\\Users\\\\azzah\\\\OneDrive\\\\Desktop\\\\ZOUZA\\\\files\\\\"; // the directory path where your files are stored
        File file = new File(directoryPath + fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Disposition", "attachment; filename=" + fileName); // Add this line


        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
    //////////////// DELETE DOCUMENT CONTROLLER
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteDocumentById(@PathVariable int id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }




    /// ************* THE FOLLOWING CONTROLLERS works only in the case that the conversion from binary to pdf works correctly
//////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/download/{id}",method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadDocument(@PathVariable int id) {
        Optional<DocOfficiel> documentOptional = Optional.ofNullable(documentService.getDocument(id));
        if (documentOptional.isPresent()) {
            DocOfficiel document = documentOptional.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("inline").filename("document.pdf").build());
            return ResponseEntity.ok().headers(headers).body(document.getFile());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

/////////////////////////////////////////////////////////
    @GetMapping("/{id}/consult")
    public ResponseEntity<byte[]> consultDocument(@PathVariable int id, HttpServletResponse response) throws IOException {
        //the reponse parameter is only used for the second consultation logic
        Optional<DocOfficiel> documentOptional = Optional.ofNullable(documentService.getDocument(id));
        if (documentOptional.isPresent()) {

            // consultation logic 1 : Display the PDF document in an iframe on the web page
            DocOfficiel document = documentOptional.get();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("inline").filename("document.pdf").build());
            byte[] pdfBytes = document.getFile();
            ByteArrayResource resource = new ByteArrayResource(pdfBytes);
            return ResponseEntity.ok().headers(headers).body(resource.getByteArray());


            // consultation logic 2:Allow the user to download the PDF document as a file:
            /* DocOfficiel document1 = documentOptional.get();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=document.pdf");
            response.getOutputStream().write(document.getFile());
            return null;*/
        } else {
            return ResponseEntity.notFound().build();
        }
    }



} //closing DocOfficielController class
