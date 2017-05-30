package kltn.toeic.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kltn.toeic.dao.MyScoreDAO;
import kltn.toeic.dao.UserDAO;
import kltn.toeic.model.MyScore;
import kltn.toeic.model.ScoreChart;

@Controller
public class MyScoreController {
	@Autowired 
	UserDAO userDAO;
	@Autowired
	MyScoreDAO myscoreDAO;
	
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
	
	@RequestMapping(value="/profile/chart", method={RequestMethod.GET})
	public String chart(Model model, Principal principal, HttpServletRequest request){
		List<MyScore> myscores = myscoreDAO.listScore(principal.getName());
		if (myscores.isEmpty()) {
			System.out.println("error");
			model.addAttribute("errorScore", true);
		} else {
			System.out.println("myscores size: " + myscores.size());
			
			List<ScoreChart> m = new ArrayList<ScoreChart>();
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
			for (int i = 0; i<myscores.size(); i++) {
				m.add(new ScoreChart(myscores.get(i).getScore(), format.format(myscores.get(i).getTimeTest())));
			}
			System.out.println(m.size());
			for (int i = 0; i<m.size(); i++) {
				System.out.println(m.get(i).getScore() + " " + m.get(i).getTime());
			}
			String filePath = request.getServletContext().getRealPath("")+"resources/myscore.json";
			ObjectMapper o = new ObjectMapper();
			try {
				o.writeValue(new File(filePath), m);
				String j = o.writeValueAsString(m);
				System.out.println(j);
			} catch (JsonGenerationException e1) {
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return "chart";
	}
}