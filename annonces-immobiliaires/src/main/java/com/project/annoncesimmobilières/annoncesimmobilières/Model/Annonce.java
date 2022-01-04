package com.project.annoncesimmobilières.annoncesimmobilières.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "annonces")
public class Annonce {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String titre;
  private String description;
  private String lieu;
  private String prix;
  private String image;

  @Override
  public String toString() {
    return "Annonce [id=" + id + ", titre=" + titre + ", description=" + description + ", image=" + image + "]";
  }

  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getTitre() {
    return titre;
  }
  public void setTitre(String titre) {
    this.titre = titre;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }

public String getPrix() {
	return prix;
}

public void setPrix(String prix) {
	this.prix = prix;
}

public String getLieu() {
	return lieu;
}

public void setLieu(String lieu) {
	this.lieu = lieu;
}
}