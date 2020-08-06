package com.demo.fa.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.fa.dao.IEntreprise;
import com.demo.fa.entities.Entreprise;



@Controller

public class EntrepriseController {
	@Autowired
	IEntreprise iEnt;
	
	@RequestMapping(value="/Entreprise/add" )
	public String index(Entreprise entreprise)
	{
	 
//		try {
//			iEnt.save(en);
//			
//			
//			//model.put("message", "v");	
//		} catch (Exception e) {
//		e.printStackTrace();
//		}
				
		return "entreprise/add";
	}
	@PostMapping("/Entreprise/add")
	public String add(@ModelAttribute("entreprise") Entreprise e ) {
		
		Entreprise en = new Entreprise();
		en.setAdresse(e.getAdresse());
		en.setNom(e.getNom());
		en.setMail(e.getMail());
		en.setEtat(e.getEtat());
		en.setRaison_social(e.getRaison_social());
		en.setPassword(e.getPassword());
		iEnt.save(en);
		return "redirect:/Entreprise/liste";
		
	}
	@RequestMapping(value="/Entreprise/liste")
	public String liste(ModelMap modelmap)
	{
	 
		List<Entreprise> ent=iEnt.findAll();
		modelmap.put("liste_entr", ent);
		modelmap.put("iEnt", new Entreprise());
		return "entreprise/liste";
	}
 
	 @RequestMapping(value="/Entreprise/delete" ,method=RequestMethod.GET)
		public String delete(int id)
		{
		 try
			{
				iEnt.delete(iEnt.getOne(id));
				iEnt.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return "redirect:/Entreprise/liste";
		}
	 @RequestMapping(value="Entreprise/edit",method=RequestMethod.GET)
		public String edit(ModelMap modelmap,int id)
		{
			List<Entreprise> en=iEnt.findAll();
			modelmap.put("liste_entr", en);
			
		 modelmap.put("en", iEnt.getOne(id));
			
			return "redirect:/Entreprise/liste";
		}

	@RequestMapping(value="/Entreprise/acceuil")
	public ModelAndView acceuil()
	{
		
		return new ModelAndView("acceuil/acceuil");
	}
	/**
     * Spring security
     */
	@RequestMapping(value="/login")
	public String login(ModelMap modelmap)
	{
		
		return "login";
	}
	 @RequestMapping(value = "/")
	    public String index() {

	        return "redirect:/logon";
	    }
 
	 @RequestMapping(value="")
		public String home()
		{
			
		 return "redirect:/logon";
		}
	
	 @RequestMapping(value="/logon")
		public String logon( HttpServletRequest req,HttpServletResponse resp)
		{
		  String mail = req.getRemoteUser();
	        Entreprise entre = iEnt.getEntrepriseByEmail(mail);
	       // System.out.println(entre.getNom() + "  " + entre.getAdresse());

	        return "/login";
	 
		    }
		}
