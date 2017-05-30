package kltn.toeic.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kltn.toeic.dao.ActivityDAO;
import kltn.toeic.dao.ExerciseDAO;
import kltn.toeic.dao.GrammarDAO;
import kltn.toeic.dao.UserDAO;
import kltn.toeic.model.Activity;
import kltn.toeic.model.Exercise;
import kltn.toeic.model.Grammar;
import kltn.toeic.model.User;
import kltn.toeic.util.ConstantValues;

@Controller
public class ExerciseController {
	@Autowired
	GrammarDAO grammarDAO;
	@Autowired
	ExerciseDAO exerciseDAO;
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
			model.addAttribute("username", null);
		}
	}
	
	@RequestMapping(value="grammar/{index}/exercise")
	public String doExercise(Model model, @PathVariable("index") String index, Principal principal) {
		List<List<String>> listAnswer;
		listAnswer=new ArrayList<List<String>>();
		List<Exercise> exercises = exerciseDAO.select(Integer.parseInt(index));
		for (Exercise exercise :  exercises) {
			listAnswer.add(Arrays.asList(exercise.getAnswer().split("\\^")));
		}
		model.addAttribute("lessonName", grammarDAO.select(Integer.parseInt(index)).getName());
		model.addAttribute("listExercise", exercises);
		model.addAttribute("numexercise", exercises.size());
		model.addAttribute("listAnswer", listAnswer);
		User user = userDAO.select(principal.getName());
		Activity activity = new Activity();
		String activityContent = "Do exercise of grammar: " + "<i>" + grammarDAO.select(Integer.parseInt(index)).getName() + "</i>";
		activity.setUser(user);
		activity.setContent(activityContent);
		activity.setCreateDate(new Date());
		activityDAO.createActivity(activity);
		return "doExercise";
	}

	@RequestMapping(value="grammar/{index}/exercise", method=RequestMethod.POST)
	public String submitExercise(Model model, @PathVariable("index") String index, HttpServletRequest request) {
		List<List<String>> listAnswer = new ArrayList<List<String>>();
		List<String> listCheck = new ArrayList<String>();
		List<Exercise> exercises = exerciseDAO.select(Integer.parseInt(index));
		for (Exercise exercise : exercises) {
			listAnswer.add(Arrays.asList(exercise.getAnswer().split("\\^")));
		}
		model.addAttribute("lessonName", grammarDAO.select(Integer.parseInt(index)).getName());
		model.addAttribute("listExercise", exercises);
		int dd = 0;
		for (int i = 0; i < exercises.size(); i++) {
			String a = listAnswer.get(i).get(0);
			String b = listAnswer.get(i).get(1);
			String c = listAnswer.get(i).get(2);
			String d = listAnswer.get(i).get(3);
			if (request.getParameter("question-"+i) == null) {
				listAnswer.get(i).set(0, "<em style=\"color: red;\"> " + a +"</em>");
				listAnswer.get(i).set(1, "<em style=\"color: red;\"> " + b +"</em>");
				listAnswer.get(i).set(2, "<em style=\"color: red;\"> " + c +"</em>");
				listAnswer.get(i).set(3, "<em style=\"color: red;\"> " + d +"</em>");
				listCheck.add("");
			} else {
				if (request.getParameter("question-"+i).equals("A")) {
					listAnswer.get(i).set(0, "<em style=\"color: red;\"> " + a +"</em>");
					listCheck.add("A");
				}
				if (request.getParameter("question-"+i).equals("B")) {
					listAnswer.get(i).set(1, "<em style=\"color: red;\"> " + b +"</em>");
					listCheck.add("B");
				}
				if (request.getParameter("question-"+i).equals("C")) {
					listAnswer.get(i).set(2, "<em style=\"color: red;\"> " + c +"</em>");
					listCheck.add("C");
				}
				if (request.getParameter("question-"+i).equals("D")) {
					listAnswer.get(i).set(3, "<em style=\"color: red;\"> " + d +"</em>");
					listCheck.add("D");
				}
			}
			System.out.println(exercises.get(i).getCorrectanswer());
			if (exercises.get(i).getCorrectanswer().equals("A")) {
				listAnswer.get(i).set(0, "<em style=\"color: blue;\"> " +a+"</em>");
			}
			if (exercises.get(i).getCorrectanswer().equals("B")) {
				listAnswer.get(i).set(1, "<em style=\"color: blue;\"> " +b+"</em>");
			}
			if (exercises.get(i).getCorrectanswer().equals("C")) {
				listAnswer.get(i).set(2, "<em style=\"color: blue;\"> " +c+"</em>");
			}
			if (exercises.get(i).getCorrectanswer().equals("D")) {
				listAnswer.get(i).set(3, "<em style=\"color: blue;\"> " +d+"</em>");
			}
			if (exercises.get(i).getCorrectanswer().equals(request.getParameter("question-"+i))) {
				dd++;
			}
		}
		if (listAnswer.size() == 0) {
			model.addAttribute("score", "<b>Your score: " + 0 +"</b");
		} else {
			model.addAttribute("score", "<b>Your score: " + dd*100/exercises.size()+"</b");
		}
		model.addAttribute("listAnswer",listAnswer);
		model.addAttribute("listCheck",listCheck);
		return "result";
	}
	
	@RequestMapping(value="admin/manageExercise/lessonid={lessonid}", method=RequestMethod.GET)
	public String manageExercise(Model model, @PathVariable("lessonid") int lessonid,
			@RequestParam(value="numPage", required=false) String numPage) {
		if (numPage == null) numPage="1";
		List<Exercise> exercises = exerciseDAO.getExercisePaging(lessonid, Integer.parseInt(numPage));
		long numExer = exerciseDAO.getExercise(lessonid);
		System.out.println("so exercise: " + numExer);
		long pageExer = numExer/ConstantValues.NUMBER_ROW_9 + (numExer%ConstantValues.NUMBER_ROW_9 == 0 ? 0 : 1);
		System.out.println("so page: " + pageExer);
		model.addAttribute("numexercise", exercises.size());
		model.addAttribute("exercises", exercises);
		model.addAttribute("numPage", numPage);
		model.addAttribute("pageExer", pageExer);
		model.addAttribute("grammarname", grammarDAO.select(lessonid).getName());
		model.addAttribute("grammarid", grammarDAO.select(lessonid).getGrammarid());
		return "manageExercise";
	}
	
	@RequestMapping(value="admin/updateExercise", method=RequestMethod.GET)
	public String update(Model model, @RequestParam("exerciseid") int exerciseid, @RequestParam("grammarid") int grammarid) {
		System.out.println("exerciseid : " + exerciseid);
		System.out.println("grammarid : " + grammarid);
		model.addAttribute("grammarid", grammarid);
		model.addAttribute("exercise", exerciseDAO.selected(exerciseid, grammarid));
		System.out.println("exercise name : " + exerciseDAO.selected(exerciseid, grammarid).getQuestion());
		return "updateExercise";
	}
	
	@RequestMapping(value="admin/updateExercise", method=RequestMethod.POST)
	public String updateExercise(Model model, RedirectAttributes redirectAttributes, @RequestParam("grammarid") int grammarid,
			@RequestParam("exerciseid") int exerciseid, @RequestParam("ans") String ans,
			@RequestParam("question") String question, @RequestParam("A") String A,
			@RequestParam("B") String B, @RequestParam("C") String C, @RequestParam("D") String D, Principal principal) {
		Grammar grammar = grammarDAO.select(grammarid);
		for (Exercise exercise : grammar.getExercises()) {
			if (exercise.getExerciseid() == exerciseid) {
				exercise.setAnswer(A+"^"+B+"^"+C+"^"+D);
				exercise.setCorrectanswer(ans);
				exercise.setQuestion(question);
				break;
			}
		}
		grammarDAO.update(grammar);
		User user = userDAO.select(principal.getName());
		Activity activity = new Activity();
		String activityContent = "Update exercise of grammar: " + "<i>" + grammar.getName() + "</i>";
		activity.setUser(user);
		activity.setContent(activityContent);
		activity.setCreateDate(new Date());
		activityDAO.createActivity(activity);
		model.addAttribute("exercises", grammarDAO.select(grammarid).getExercises());
		model.addAttribute("grammarname", grammarDAO.select(grammarid).getName());
		redirectAttributes.addAttribute("grammarid", grammarDAO.select(grammarid).getGrammarid());
		redirectAttributes.addFlashAttribute("updateExerciseSuccess","true");
		return "redirect:/admin/manageExercise/lessonid={grammarid}";
	}
	
	@RequestMapping(value="admin/confirmDeleteExercise")
	public String delete(Model model, @RequestParam("exerciseid") int exerciseid, @RequestParam("grammarid") int grammarid){
		model.addAttribute("grammarid", grammarid);
		model.addAttribute("exercise", exerciseDAO.selected(exerciseid, grammarid));
		return "confirmDeleteExercise";
	}
	
	@RequestMapping(value="admin/deleteExercise")
	public String deleteExercise(Model model, RedirectAttributes redirectAttributes, @RequestParam("grammarid") int grammarid, 
			Principal principal, @RequestParam("exerciseid") int exerciseid) {
		System.out.println("exercise id: " + exerciseid);
		System.out.println("grammar id: " + grammarid);
		Exercise exercise = exerciseDAO.selected(exerciseid, grammarid);
		exerciseDAO.delete(exercise);
		User user = userDAO.select(principal.getName());
		Activity activity = new Activity();
		String activityContent = "Delete exercise of grammar: " + "<i>" + grammarDAO.select(grammarid).getName() + "</i>";
		activity.setUser(user);
		activity.setContent(activityContent);
		activity.setCreateDate(new Date());
		activityDAO.createActivity(activity);
		Grammar grammar = grammarDAO.select(grammarid);
		model.addAttribute("exercises", grammar.getExercises());
		model.addAttribute("grammarname", grammarDAO.select(grammarid).getName());
		redirectAttributes.addAttribute("grammarid", grammarDAO.select(grammarid).getGrammarid());
		redirectAttributes.addFlashAttribute("deleteExerciseSuccess","true");
		return "redirect:/admin/manageExercise/lessonid={grammarid}";
	}
}
