package com.demo.fa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.fa.entities.Entreprise;
@Repository
public interface IEntreprise  extends JpaRepository<Entreprise, Integer> {
	
	@Query("select p from Entreprise p where p .mail = :mail" )
    public Entreprise chercher(@Param("mail") String e);
	 @Query("select p from Entreprise p where p.mail = :mail")
	    public Entreprise getEntrepriseByEmail(@Param("mail") String e);


}
