package com.demo.fa.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import javax.persistence.*;

@Entity
public class Entreprise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String nom;
	@Column
	private String adresse;
	@Column
	private String mail;
	 @Column
	    private int etat;
	@Column
	private String raison_social;
	@Column
	private String password;
	@OneToMany(mappedBy = "entreprise",fetch = FetchType.LAZY)
    List<Offre> offre = new ArrayList<Offre>();
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "entreprise_role",
            joinColumns = { @JoinColumn(name = "email", nullable = false, updatable = false)},
            inverseJoinColumns = { @JoinColumn(name = "nom", nullable = false, updatable = false)})
    private List<Role> role = new ArrayList<>();
	
	public Entreprise() {
		super();
	}
	
	public Entreprise(int id, String nom, String adresse, String mail, int etat, String raison_social, String password,
			List<Offre> offre, List<Role> role) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.mail = mail;
		this.etat = etat;
		this.raison_social = raison_social;
		this.password = password;
		this.offre = offre;
		this.role = role;
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
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public String getRaison_social() {
		return raison_social;
	}
	public void setRaison_social(String raison_social) {
		this.raison_social = raison_social;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Offre> getOffre() {
		return offre;
	}
	public void setOffre(List<Offre> offre) {
		this.offre = offre;
	}
	public List<Role> getRole() {
		return role;
	}
	public void setRole(List<Role> role) {
		this.role = role;
	}
	
}
