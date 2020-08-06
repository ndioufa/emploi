package com.demo.fa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.fa.entities.Demandeur;



public interface IDemandeur extends JpaRepository<Demandeur, Integer> {

}
