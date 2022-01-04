package com.project.annoncesimmobilières.annoncesimmobilières.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.annoncesimmobilières.annoncesimmobilières.Model.Annonce;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long>{

}
