package com.demo.fa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.fa.entities.Offre;
@Repository
public interface IOffre extends JpaRepository<Offre, Integer>{
	

}
