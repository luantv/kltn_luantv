package kltn.toeic.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kltn.toeic.dao.IntroductionDAO;
import kltn.toeic.dao.UserDAO;
import kltn.toeic.model.Introduction;

@Controller
public class MainController {
	@Autowired
	IntroductionDAO introDAO;
	@Autowired
	UserDAO userDAO;
	
	@ModelAttribute
	public void addObjAttribute(Model model, Principal principal){
		if (principal != null) {
			String email = principal.getName();
			model.addAttribute("user", userDAO.select(email));
			if (userDAO.checkAdmin(userDAO.select(email))) {
				System.out.println("admin");
				model.addAttribute("role", "admin");
			} else if (userDAO.checkSubAdmin(userDAO.select(email))) {
				System.out.println("subadmin");
				model.addAttribute("role", "subadmin");
			} else {
				model.addAttribute("role", "user");
				System.out.println("user");
			}
		} else {
			model.addAttribute("username",null);
		}
	}
	
	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
	public String indexUser(Model model) {
		model.addAttribute("intro", introDAO.select(1));
		return "index";
	}
	
	@RequestMapping(value = "/{name}")
	public String introTOEIC(Model model, @PathVariable("name") String name) {
		model.addAttribute("intro", introDAO.select(name));
		return "index";
	}
	
	@RequestMapping(value = "/admin", method = {RequestMethod.GET})
	public String indexAdmin(Model model) {
		model.addAttribute("intro", introDAO.select(1));
		return "indexAdmin";
	}
	
	@RequestMapping(value = "/admin", method = {RequestMethod.POST})
	public String indexAdminU(Model model, @RequestParam String content) {
		Introduction intro = introDAO.select(1);
		intro.setContent(content);
		introDAO.update(intro);
		model.addAttribute("updateSuccess", true);
		model.addAttribute("intro", intro);
		return "indexAdmin";
	}
	
	@RequestMapping(value = "/admin/{name}", method = {RequestMethod.GET})
	public String introTOEICAdmin(Model model, @PathVariable("name") String name) {
		model.addAttribute("intro", introDAO.select(name));
		return "indexAdmin";
	}
	
	@RequestMapping(value = "/admin/{name}", method = {RequestMethod.POST})
	public String introTOEICAdmin(Model model, @PathVariable String name, @RequestParam String content) {
		Introduction intro = introDAO.select(name);
		intro.setContent(content);
		introDAO.update(intro);
		model.addAttribute("updateSuccess", true);
		model.addAttribute("intro", intro);
		return "indexAdmin";
	}
	
}
