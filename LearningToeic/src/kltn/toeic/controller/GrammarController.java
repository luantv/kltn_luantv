package kltn.toeic.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kltn.toeic.dao.ActivityDAO;
import kltn.toeic.dao.GrammarDAO;
import kltn.toeic.dao.UserDAO;
import kltn.toeic.model.Activity;
import kltn.toeic.model.Exercise;
import kltn.toeic.model.Grammar;
import kltn.toeic.model.User;
import kltn.toeic.util.ConstantValues;

@Controller
public class GrammarController {
	@Autowired
	GrammarDAO grammarDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	ActivityDAO activityDAO;

	@ModelAttribute
	public void addObjAttribute(Model model, Principal principal ){
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

	@RequestMapping(value="admin/manageGrammar",method=RequestMethod.GET)
	public String manageGrammar(Model model, @RequestParam(value="numPage", required=false) String numPage){
		if (numPage == null) numPage="1";
		List<Grammar> lessons = grammarDAO.getGrammarPaging(Integer.parseInt(numPage));
		long numGram = grammarDAO.getGrammar();
		long pageGram = numGram/ConstantValues.NUMBER_ROW_6 + (numGram%ConstantValues.NUMBER_ROW_6 == 0 ? 0 : 1);
		model.addAttribute("numPage", numPage);
		model.addAttribute("pageGram", pageGram);
		model.addAttribute("lessons",lessons);
		return "manageGrammar";
	}

	@RequestMapping(value="admin/searchGrammar", method=RequestMethod.GET)
	public String searchGrammarAd(Model model, @RequestParam("searchKey") String searchKey,
			@RequestParam(value="numPage", required=false) String numPage){
		if (numPage == null) numPage="1";
		List<Grammar> listGrammar = grammarDAO.searchGrammar(searchKey, Integer.parseInt(numPage));
		long numGram = grammarDAO.getSearchGrammar(searchKey);
		long pageGram = numGram/ConstantValues.NUMBER_ROW_6 + (numGram%ConstantValues.NUMBER_ROW_6 == 0 ? 0 : 1);
		model.addAttribute("listGrammar", listGrammar);
		model.addAttribute("pageGram", pageGram);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("numPage", numPage);
		model.addAttribute("numGram", numGram);
		return "searchGrammarAd";
	}
	
	@RequestMapping(value="searchGrammar", method=RequestMethod.GET)
	public String searchGrammar(Model model, @RequestParam("searchKey") String searchKey,
			@RequestParam(value="numPage", required=false) String numPage){
		if (numPage == null) numPage="1";
		List<Grammar> listGrammar = grammarDAO.searchGrammar(searchKey, Integer.parseInt(numPage));
		long numGram = grammarDAO.getSearchGrammar(searchKey);
		long pageGram = numGram/ConstantValues.NUMBER_ROW_6 + (numGram%ConstantValues.NUMBER_ROW_6 == 0 ? 0 : 1);
		model.addAttribute("listGrammar", listGrammar);
		model.addAttribute("pageGram", pageGram);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("numPage", numPage);
		model.addAttribute("numGram", numGram);
		return "searchGrammar";
	}
	
	@RequestMapping(value="admin/postGrammar", method = RequestMethod.GET)
	public String postGrammar() {
		return "postGrammar";
	}

	@RequestMapping(value="admin/postGrammar", method = RequestMethod.POST)
	public String postGrammarPost(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("lession") Grammar newLesson, 
			@RequestParam("excelfile") MultipartFile excelfile, Principal principal) throws IOException {
		if (!grammarDAO.checkExits(newLesson.getName())) {
		List<Exercise> exercises = new ArrayList<Exercise>();
		int i = 0;
		try {
			@SuppressWarnings("resource")
			XSSFSheet worksheet = new XSSFWorkbook(excelfile.getInputStream()).getSheetAt(0);
			Exercise exercise = new Exercise();
			while (i <= worksheet.getLastRowNum()) {
				exercise = new Exercise();
				XSSFRow row = worksheet.getRow(i++);
				exercise.setQuestion(row.getCell(0).getStringCellValue());
				exercise.setAnswer(row.getCell(1).getStringCellValue());
				exercise.setCorrectanswer(row.getCell(2).getStringCellValue());
				exercise.setGrammar(newLesson);		
				exercise.setExerciseid(i);
				exercises.add(exercise);
			}
			newLesson.setExercises(exercises);
		}
		catch (Exception e) {
			newLesson.setExercises(null);
		}
		grammarDAO.create(newLesson);
		User user = userDAO.select(principal.getName());
		Activity activity = new Activity();
		String activityContent = "Create lesson grammar: " + "<i>" + newLesson.getName() + "</i>";
		activity.setUser(user);
		activity.setContent(activityContent);
		activity.setCreateDate(new Date());
		activityDAO.createActivity(activity);
		redirectAttributes.addFlashAttribute("postGrammarSuccess","true");
		} else {
			redirectAttributes.addFlashAttribute("errorName","true");
		}
		return "redirect:/admin/manageGrammar";
	}
	
	@RequestMapping(value="admin/confirmDeleteGrammar")
	public String delete(Model model, @RequestParam("lessonid") int lessonid){
		model.addAttribute("lesson", grammarDAO.select(lessonid));
		return "confirmDeleteGrammar";
	}
	
	@RequestMapping(value="admin/deleteGrammar")
	public String deleteGrammar(Model model, RedirectAttributes redirectAttributes, @RequestParam("lessonid") int lessonid, Principal principal) {
		Grammar grammar = grammarDAO.select(lessonid);
		grammarDAO.delete(grammar);
		List<Grammar> lessons = grammarDAO.listGrammar();
		model.addAttribute("lessons",lessons);
		User user = userDAO.select(principal.getName());
		Activity activity = new Activity();
		String activityContent = "Delete lesson grammar: " + "<i>" + grammar.getName() + "</i>";
		activity.setUser(user);
		activity.setContent(activityContent);
		activity.setCreateDate(new Date());
		activityDAO.createActivity(activity);
		redirectAttributes.addFlashAttribute("delete",true);
		return "redirect:/admin/manageGrammar";
	}
	
	@RequestMapping(value="admin/updateGrammar")
	public String update(Model model, @RequestParam("lessonid") int lessonid){
		model.addAttribute("lesson", grammarDAO.select(lessonid));
		return "updateGrammar";
	}
	
	@RequestMapping(value="admin/updateGrammar", method=RequestMethod.POST)
	public String updateGrammar(Model model, RedirectAttributes redirectAttributes, @RequestParam("lessonid") int lessonid,
			@RequestParam("name") String name, @RequestParam("content") String content,
			@RequestParam("description") String description, Principal principal){
		Grammar grammar = grammarDAO.select(lessonid);
		grammar.setName(name);
		grammar.setContent(content);
		grammar.setDescription(description);
		grammar.setEnable(1);
		grammarDAO.update(grammar);
		User user = userDAO.select(principal.getName());
		Activity activity = new Activity();
		String activityContent = "Update lesson grammar: " + "<i>" + grammar.getName() + "</i>";
		activity.setUser(user);
		activity.setContent(activityContent);
		activity.setCreateDate(new Date());
		activityDAO.createActivity(activity);
		redirectAttributes.addFlashAttribute("updateGrammarSuccess","true");
		return "redirect:/admin/manageGrammar";
	}
	
	@RequestMapping(value="grammar")
	public String grammar(Model model, @RequestParam(value="numPage", required=false) String numPage) {
		if (numPage == null) numPage="1";
		List<Grammar> listGrammar = grammarDAO.getGrammarPaging(Integer.parseInt(numPage));
		long numGram = grammarDAO.getGrammar();
		long pageGram = numGram/ConstantValues.NUMBER_ROW_6 + (numGram%ConstantValues.NUMBER_ROW_6 == 0 ? 0 : 1);
		model.addAttribute("listGrammar", listGrammar);
		model.addAttribute("pageGram", pageGram);
		model.addAttribute("numPage", numPage);
		return "grammar";
	}
	
	@RequestMapping(value="grammar/{index}")
	public String selectGrammar(Model model, @PathVariable("index") int index, Principal principal) {
		model.addAttribute("lesson", grammarDAO.select(index));
		User user = userDAO.select(principal.getName());
		Activity activity = new Activity();
		String activityContent = "Learn lesson grammar: " + "<i>" + grammarDAO.select(index).getName() + "</i>";
		activity.setUser(user);
		activity.setContent(activityContent);
		activity.setCreateDate(new Date());
		activityDAO.createActivity(activity);
		return "grammarDetail";
	}
}
