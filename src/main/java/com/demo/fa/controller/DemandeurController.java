package com.demo.fa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.fa.dao.IDemandeur;
import com.demo.fa.entities.Demandeur;
import com.demo.fa.entities.Entreprise;

@Controller
public class DemandeurController {
	@Autowired
	IDemandeur idao;
	@RequestMapping(value="/Demandeur/add" )
	public String index(Demandeur demandeur)
	{
	 
//		try {
//			iEnt.save(en);
//			
//			
//			//model.put("message", "v");	
//		} catch (Exception e) {
//		e.printStackTrace();
//		}
				
		return "demande/add";
	}
	@PostMapping("/Demandeur/add")
	public String add(@ModelAttribute("demandeur") Demandeur d ) {
		
		Demandeur de = new Demandeur();
		de.setNom(d.getNom());;
		de.setPrenom(d.getPrenom());;
		de.setMail(d.getMail());
		de.setAdresse(d.getAdresse());
		de.setEtat(d.getEtat());
		de.setDomaine(d.getDomaine());
		de.setPassword(d.getPassword());
		idao.save(de);
		return "redirect:/Demandeur/liste";
		
	}
	@RequestMapping(value="/Demandeur/liste")
	public String liste(ModelMap modelmap)
	{
	 
		List<Demandeur> de=idao.findAll();
		modelmap.put("liste_de", de);
		modelmap.put("idao", new Entreprise());
		return "demande/liste";
	}
 
	 @RequestMapping(value="/Demandeur/delete" ,method=RequestMethod.GET)
		public String delete(int id)
		{
		 try
			{
				idao.delete(idao.getOne(id));
				idao.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return "redirect:/Demandeur/liste";
		}
	 @RequestMapping(value="Demandeur/edit",method=RequestMethod.GET)
		public String edit(ModelMap modelmap,int id)
		{
			List<Demandeur> en=idao.findAll();
			modelmap.put("liste_entr", en);
			
		 modelmap.put("en", idao.getOne(id));
			
			return "redirect:/Demandeur/liste";
		}

}
