package com.demo.fa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String nom;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private List<Entreprise> entreprise = new ArrayList<>();
	public Role() {
		super();
	}
	public Role(int id, String nom, List<Entreprise> entreprise) {
		super();
		this.id = id;
		this.nom = nom;
		this.entreprise = entreprise;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Entreprise> getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(List<Entreprise> entreprise) {
		this.entreprise = entreprise;
	}

	
}
