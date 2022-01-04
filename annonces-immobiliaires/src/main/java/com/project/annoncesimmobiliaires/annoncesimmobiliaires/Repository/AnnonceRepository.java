package com.project.annoncesimmobiliaires.annoncesimmobiliaires.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.annoncesimmobiliaires.annoncesimmobiliaires.Model.Annonce;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long>{

}
