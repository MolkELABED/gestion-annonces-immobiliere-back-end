package com.project.annoncesimmobilières.annoncesimmobilières.Controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.annoncesimmobilières.annoncesimmobilières.Exception.ResourceNotFoundException;
import com.project.annoncesimmobilières.annoncesimmobilières.Model.Annonce;
import com.project.annoncesimmobilières.annoncesimmobilières.Repository.AnnonceRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AnnonceController {
  @Autowired
  AnnonceRepository repository;

  @GetMapping("/annonces")
  public List<Annonce> getAllAnnonce() {
    List<Annonce> Annonces = new ArrayList<>();
    repository.findAll().forEach(Annonces::add);
    return Annonces;
  }

  @GetMapping("/annonces/{id}")
  public ResponseEntity<Annonce> getAnnonceById(@PathVariable(value = "id") Long AnnonceId)
    throws ResourceNotFoundException
  {
    Annonce Annonce = repository.findById(AnnonceId)
        .orElseThrow(() -> new ResourceNotFoundException("Annonce not found for this id ::" + AnnonceId));

    return ResponseEntity.ok().body(Annonce);
  }

  @PostMapping("/annonces")
  public Annonce createAnnonce(@RequestBody Annonce Annonce) {
    return repository.save(Annonce);
  }

  @PutMapping("/annonces/{id}")
  public ResponseEntity<Annonce> updateAnnonce(@PathVariable(value = "id") Long id, @RequestBody Annonce Annonce) {
    Optional<Annonce> AnnonceInfo = repository.findById(id);

    if (AnnonceInfo.isPresent()) {
      Annonce annonce = AnnonceInfo.get();
      annonce.setDescription(Annonce.getDescription());
      annonce.setTitre(Annonce.getTitre());
      annonce.setLieu(Annonce.getLieu());
      annonce.setPrix(Annonce.getPrix());
      annonce.setImage(Annonce.getImage());

      return new ResponseEntity<>(repository.save(annonce), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @DeleteMapping("/annonces/{id}")
  public void deleteAnnonce(@PathVariable(value = "id") Long id) {
    repository.deleteById(id);
  }
  
  @GetMapping(path="/photoAnnonce/{id}", produces=MediaType.IMAGE_JPEG_VALUE)
  public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
	Annonce a=repository.findById(id).get();
	return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/annonces/"+a.getImage()));
  }
  
  @PostMapping(path = "/uploadPhoto/{id}")
  public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception {
    Annonce annonce = repository.findById(id).get();

    annonce.setImage(id + ".jpg");
    Files.write(Paths.get(System.getProperty("user.home") + "/annonces/" + annonce.getImage()), file.getBytes());
    repository.save(annonce);
  }
}