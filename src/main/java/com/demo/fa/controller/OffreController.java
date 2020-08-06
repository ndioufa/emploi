package com.demo.fa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.fa.dao.IEntreprise;
import com.demo.fa.dao.IOffre;
import com.demo.fa.entities.Entreprise;
import com.demo.fa.entities.Offre;

@Controller
public class OffreController {

	@Autowired
	IOffre idao;
	IEntreprise iEnt;
	@RequestMapping(value="/Offre/add" )
	public String index(Offre offre)
	{
	 
//		try {
//			iEnt.save(en);
//			
//			
//			//model.put("message", "v");	
//		} catch (Exception e) {
//		e.printStackTrace();
//		}
				
		return "offre/add";
	}
	@PostMapping("/Offre/add")
	public String add(@ModelAttribute("offre") Offre o ) {
		
		Offre of = new Offre();
		of.setNom(o.getNom());
		of.setSpecialite(o.getSpecialite());
		Entreprise entre = new Entreprise();
		entre = iEnt.getOne(of.getEntreprise().getId());
		of.setEntreprise(o.getEntreprise());
		idao.save(of);
		return "redirect:/Offre/liste";
		
	}
	@RequestMapping(value="/Offre/liste")
	public String liste(ModelMap modelmap)
	{
	 
		List<Offre> off=idao.findAll();
		modelmap.put("liste_offre", off);
		modelmap.put("idao", new Offre());
		return "offre/liste";
	}
 
	 @RequestMapping(value="/Offre/delete" ,method=RequestMethod.GET)
		public String delete(int id)
		{
		 try
			{
				idao.delete(idao.getOne(id));
				idao.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return "redirect:/Offre/liste";
		}
	 @RequestMapping(value="Offre/edit",method=RequestMethod.GET)
		public String edit(ModelMap modelmap,int id)
		{
			List<Offre> of=idao.findAll();
			modelmap.put("liste_offre", of);
			
		 modelmap.put("of", idao.getOne(id));
			
			return "redirect:/Offre/liste";
		}

	
}
