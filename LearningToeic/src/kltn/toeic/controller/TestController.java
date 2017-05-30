package kltn.toeic.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import kltn.toeic.dao.MyScoreDAO;
import kltn.toeic.dao.ScoreDAO;
import kltn.toeic.dao.TestDAO;
import kltn.toeic.dao.UserDAO;
import kltn.toeic.model.Activity;
import kltn.toeic.model.IncompleteSentence;
import kltn.toeic.model.MyScore;
import kltn.toeic.model.Photo;
import kltn.toeic.model.QuestionResponse;
import kltn.toeic.model.ReadingComprehension;
import kltn.toeic.model.ReadingComprehensionDetail;
import kltn.toeic.model.ShortConversation;
import kltn.toeic.model.ShortConversationDetail;
import kltn.toeic.model.ShortTalk;
import kltn.toeic.model.ShortTalkDetail;
import kltn.toeic.model.Test;
import kltn.toeic.model.TextCompletion;
import kltn.toeic.model.TextCompletionDetail;
import kltn.toeic.model.User;
import kltn.toeic.util.ConstantValues;

@Controller
public class TestController {
	@Autowired
	TestDAO testDAO;
	@Autowired 
	UserDAO userDAO;
	@Autowired
	ScoreDAO scoreDAO;
	@Autowired
	ActivityDAO activityDAO;
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
			model.addAttribute("username",null);
		}	
	}
	
	@RequestMapping(value="admin/manageTest", method=RequestMethod.GET)
	public String manageTest(Model model, @RequestParam(value="numPage", required=false) String numPage) {
		if (numPage == null) numPage = "1";
		List<Test> testList = testDAO.getTestPaging(Integer.parseInt(numPage));
		long numTest = testDAO.getTest();
		long pageTest = numTest/ConstantValues.NUMBER_ROW_6 + (numTest%ConstantValues.NUMBER_ROW_6 == 0 ? 0 : 1);
		model.addAttribute("testList", testList);
		model.addAttribute("pageTest", pageTest);
		model.addAttribute("numPage", numPage);
		return "manageTest";
	}
	
	@RequestMapping(value="admin/searchTest", method=RequestMethod.GET)
	public String searchTest(Model model, @RequestParam(value="numPage", required=false) String numPage, @RequestParam(value="searchKey") String searchKey) {
		if (numPage == null) numPage = "1";
		List<Test> testList = testDAO.searchTest(searchKey, Integer.parseInt(numPage));
		long numTest = testDAO.getSearchTest(searchKey);
		long pageTest = numTest/ConstantValues.NUMBER_ROW_6 + (numTest%ConstantValues.NUMBER_ROW_6 == 0 ? 0 : 1);
		model.addAttribute("testList", testList);
		model.addAttribute("numTest", numTest);
		model.addAttribute("numPage", numPage);
		model.addAttribute("pageTest", pageTest);
		model.addAttribute("searchKey", searchKey);
		return "searchTest";
	}
	
	@RequestMapping(value="admin/createTest")
	public String createTest() {
		return "createTest";
	}
	
	@RequestMapping(value="admin/createTest", method=RequestMethod.POST)
	public String createTest(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request,
			@RequestParam("excelfile") MultipartFile excelfile,
			@RequestParam("part1")MultipartFile[] part1,
			@RequestParam("part2")MultipartFile[] part2,
			@RequestParam("part3")MultipartFile[] part3,
			@RequestParam("part4")MultipartFile[] part4,
			@RequestParam("part7")MultipartFile[] part7,
			@RequestParam("testName") String testName,
			@RequestParam("level") String level, Principal principal) throws IOException {
		Test test = new Test();
		if (!testDAO.checkExits(testName)) {
			test.setTestname(testName);
			test.setEnable(1);
			test.setLevel(level);
			// Part 1
			List<Photo> photos= new ArrayList<Photo>();
			int i = 0;
			@SuppressWarnings("resource")
			XSSFSheet worksheet1 = new XSSFWorkbook(excelfile.getInputStream()).getSheet("photo");
			Photo photo = new Photo();
			while (i <= worksheet1.getLastRowNum()) {
				photo = new Photo();
				XSSFRow row = worksheet1.getRow(i++);
				photo.setAnswer(row.getCell(0).getStringCellValue());
				photo.setCorrectanswer(row.getCell(1).getStringCellValue());
				photo.setTest(test);		
				photo.setPhotoid(i);
				photos.add(photo);
			}
			test.setPhotos(photos);
			// Part 2
			List<QuestionResponse> questionResponses= new ArrayList<QuestionResponse>();
			i = 0;
			@SuppressWarnings("resource")
			XSSFSheet worksheet2 = new XSSFWorkbook(excelfile.getInputStream()).getSheet("question_response");
			QuestionResponse questionResponse = new QuestionResponse();
			while (i <= worksheet2.getLastRowNum()) {
				questionResponse = new QuestionResponse();
				XSSFRow row = worksheet2.getRow(i++);
				questionResponse.setQuestion(row.getCell(0).getStringCellValue());
				questionResponse.setAnswer(row.getCell(1).getStringCellValue());
				questionResponse.setCorrectanswer(row.getCell(2).getStringCellValue());
				questionResponse.setTest(test);		
				questionResponse.setQuestionresponseid(i);
				questionResponses.add(questionResponse);
			}
			test.setQuestionResponses(questionResponses);
			// Part 3
			List<ShortConversation> shortConversations= new ArrayList<ShortConversation>();
			i = 0;
			int t = 0;
			@SuppressWarnings("resource")
			XSSFSheet worksheet3 = new XSSFWorkbook(excelfile.getInputStream()).getSheet("short_conversation");
			ShortConversation shortConversation = new ShortConversation();
			while (i <= worksheet3.getLastRowNum()) {
				shortConversation = new ShortConversation();
				XSSFRow row = worksheet3.getRow(i++);
				shortConversation.setScript(row.getCell(0).getStringCellValue());
				shortConversation.setTest(test);		
				shortConversation.setShortconversationid(i);
				int n = (int) row.getCell(1).getNumericCellValue();
				@SuppressWarnings("resource")
				XSSFSheet worksheet = new XSSFWorkbook(excelfile.getInputStream()).getSheet("short_conversation_detail");
				List<ShortConversationDetail> shortConversationDetails = new ArrayList<ShortConversationDetail>();
				ShortConversationDetail shortConversationDetail;
				for (int j = t; j < t+n; j++) {
					shortConversationDetail = new ShortConversationDetail();
					XSSFRow row1 = worksheet.getRow(j);
					shortConversationDetail.setQuestion(row1.getCell(0).getStringCellValue());
					shortConversationDetail.setAnswer(row1.getCell(1).getStringCellValue());
					shortConversationDetail.setCorrectanswer(row1.getCell(2).getStringCellValue());
					shortConversationDetail.setId(j-t+1);
					shortConversationDetail.setShortConversation(shortConversation);
					shortConversationDetails.add(shortConversationDetail);
				}
				t+=n;
				shortConversation.setShortConversationDetails(shortConversationDetails);
				shortConversations.add(shortConversation);
			}
			test.setShortConversations(shortConversations);
			// Part 4
			List<ShortTalk> shortTalks= new ArrayList<ShortTalk>();
			i = 0;
			t = 0;
			@SuppressWarnings("resource")
			XSSFSheet worksheet4 = new XSSFWorkbook(excelfile.getInputStream()).getSheet("short_talk");
			ShortTalk shortTalk = new ShortTalk();
			while (i <= worksheet4.getLastRowNum()) {
				shortTalk = new ShortTalk();
				XSSFRow row = worksheet4.getRow(i++);
				shortTalk.setScript(row.getCell(0).getStringCellValue());
				shortTalk.setTest(test);		
				shortTalk.setShorttalkid(i);;
				int n = (int) row.getCell(1).getNumericCellValue();
				@SuppressWarnings("resource")
				XSSFSheet worksheet = new XSSFWorkbook(excelfile.getInputStream()).getSheet("short_talk_detail");
				List<ShortTalkDetail> shortTalkDetails = new ArrayList<ShortTalkDetail>();
				ShortTalkDetail shortTalkDetail;
				for (int j = t; j < t+n; j++){
					shortTalkDetail = new ShortTalkDetail();
					XSSFRow row1 = worksheet.getRow(j);
					shortTalkDetail.setQuestion(row1.getCell(0).getStringCellValue());
					shortTalkDetail.setAnswer(row1.getCell(1).getStringCellValue());
					shortTalkDetail.setCorrectanswer(row1.getCell(2).getStringCellValue());
					shortTalkDetail.setId(j-t+1);
					shortTalkDetail.setShortTalk(shortTalk);
					shortTalkDetails.add(shortTalkDetail);
				}
				t+=n;
				shortTalk.setShortTalkDetails(shortTalkDetails);
				shortTalks.add(shortTalk);
			}
			test.setShortTalks(shortTalks);
			// Part 5
			List<IncompleteSentence> incompleteSentences = new ArrayList<IncompleteSentence>();
			i = 0;
			@SuppressWarnings("resource")
			XSSFSheet worksheet5 = new XSSFWorkbook(excelfile.getInputStream()).getSheet("incomplete_sentence");
			IncompleteSentence incompleteSentence = new IncompleteSentence();
			while (i <= worksheet5.getLastRowNum()) {
				incompleteSentence = new IncompleteSentence();
				XSSFRow row = worksheet5.getRow(i++);
				incompleteSentence.setQuestion(row.getCell(0).getStringCellValue());
				incompleteSentence.setAnswer(row.getCell(1).getStringCellValue());
				incompleteSentence.setCorrectanswer(row.getCell(2).getStringCellValue());
				incompleteSentence.setTest(test);		
				incompleteSentence.setIncompletesentenceid(i);
				incompleteSentences.add(incompleteSentence);
			}
			test.setIncompleteSentences(incompleteSentences);
			// Part 6
			List<TextCompletion> textCompletions= new ArrayList<TextCompletion>();
			i = 0;
			t = 0;
			@SuppressWarnings("resource")
			XSSFSheet worksheet6 = new XSSFWorkbook(excelfile.getInputStream()).getSheet("text_completion");
			TextCompletion textCompletion = new TextCompletion();
			while (i <= worksheet6.getLastRowNum()) {
				textCompletion = new TextCompletion();
				XSSFRow row = worksheet6.getRow(i++);
				textCompletion.setScript(row.getCell(0).getStringCellValue());
				textCompletion.setTest(test);		
				textCompletion.setTextcompletionid(i);
				int n = (int) row.getCell(1).getNumericCellValue();
				@SuppressWarnings("resource")
				XSSFSheet worksheet = new XSSFWorkbook(excelfile.getInputStream()).getSheet("text_completion_detail");
				List<TextCompletionDetail> textCompletionDetails = new ArrayList<TextCompletionDetail>();
				TextCompletionDetail textCompletionDetail;
				for (int j = t; j < t+n; j++){
					textCompletionDetail = new TextCompletionDetail();
					XSSFRow row1 = worksheet.getRow(j);
					textCompletionDetail.setAnswer(row1.getCell(0).getStringCellValue());
					textCompletionDetail.setCorrectanswer(row1.getCell(1).getStringCellValue());
					textCompletionDetail.setId(j-t+1);
					textCompletionDetail.setTextCompletion(textCompletion);
					textCompletionDetails.add(textCompletionDetail);
				}
				t+=n;
				textCompletion.setTextCompletionDetails(textCompletionDetails);
				textCompletions.add(textCompletion);
			}
			test.setTextCompletions(textCompletions);
			// Part 7
			List<ReadingComprehension> readingComprehensions= new ArrayList<ReadingComprehension>();
			i = 0;
			t = 0;
			@SuppressWarnings("resource")
			XSSFSheet worksheet7 = new XSSFWorkbook(excelfile.getInputStream()).getSheet("reading_comprehension");
			ReadingComprehension readingComprehension = new ReadingComprehension();
			while (i <= worksheet7.getLastRowNum()) {
				readingComprehension = new ReadingComprehension();
				XSSFRow row = worksheet7.getRow(i++);
				readingComprehension.setTest(test);		
				readingComprehension.setReadingcomprehensionid(i);
				int n = (int) row.getCell(0).getNumericCellValue();
				@SuppressWarnings("resource")
				XSSFSheet worksheet = new XSSFWorkbook(excelfile.getInputStream()).getSheet("reading_comprehension_detail");
				List<ReadingComprehensionDetail> readingComprehensionDetails = new ArrayList<ReadingComprehensionDetail>();
				ReadingComprehensionDetail readingComprehensionDetail;
				for (int j = t; j < t+n; j++){
					readingComprehensionDetail = new ReadingComprehensionDetail();
					XSSFRow row1 = worksheet.getRow(j);
					readingComprehensionDetail.setQuestion(row1.getCell(0).getStringCellValue());
					readingComprehensionDetail.setAnswer(row1.getCell(1).getStringCellValue());
					readingComprehensionDetail.setCorrectanswer(row1.getCell(2).getStringCellValue());
					readingComprehensionDetail.setId(j-t+1);
					readingComprehensionDetail.setReadingComprehension(readingComprehension);
					readingComprehensionDetails.add(readingComprehensionDetail);
				}
				t+=n;
				readingComprehension.setReadingComprehensionDetails(readingComprehensionDetails);
				readingComprehensions.add(readingComprehension);
			}
			test.setReadingComprehensions(readingComprehensions);
			testDAO.createTest(test);
			int testId = test.getTestid();
			String uploadRootPath = request.getServletContext().getRealPath("")+"resources/upload/Test_"+testId+"/";

			// Part 1
			File uploadRootDir = new File(uploadRootPath+"part1/");
			if (!uploadRootDir.exists()) {
				uploadRootDir.mkdirs();
			}
			List<File> uploadedFiles = new ArrayList<File>();
			for (i = 0; i < part1.length; i++) {
				MultipartFile file = part1[i];
				String name = file.getOriginalFilename();
				System.out.println("Client File Name = " + name);
				if (name != null && name.length() > 0) {
					try {
						byte[] bytes = file.getBytes();
						File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						uploadedFiles.add(serverFile);
						System.out.println("Write file: " + serverFile);
					} catch (Exception e) {
						System.out.println("Error Write file: " + name);
					}
				}
			}
			// Part 2
			uploadRootDir = new File(uploadRootPath+"part2/");
			if (!uploadRootDir.exists()) {
				uploadRootDir.mkdirs();
			}
			uploadedFiles = new ArrayList<File>();
			for (i = 0; i < part2.length; i++) {
				MultipartFile file = part2[i];
				String name = file.getOriginalFilename();
				System.out.println("Client File Name = " + name);
				if (name != null && name.length() > 0) {
					try {
						byte[] bytes = file.getBytes();
						File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						uploadedFiles.add(serverFile);
						System.out.println("Write file: " + serverFile);
					} catch (Exception e) {
						System.out.println("Error Write file: " + name);
					}
				}
			}
			// Part 3
			uploadRootDir = new File(uploadRootPath+"part3/");
			if (!uploadRootDir.exists()) {
				uploadRootDir.mkdirs();
			}
			uploadedFiles = new ArrayList<File>();
			for (i = 0; i < part3.length; i++) {
				MultipartFile file = part3[i];
				String name = file.getOriginalFilename();
				System.out.println("Client File Name = " + name);
				if (name != null && name.length() > 0) {
					try {
						byte[] bytes = file.getBytes();
						File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						uploadedFiles.add(serverFile);
						System.out.println("Write file: " + serverFile);
					} catch (Exception e) {
						System.out.println("Error Write file: " + name);
					}
				}
			}
			// Part 4
			uploadRootDir = new File(uploadRootPath+"part4/");
			if (!uploadRootDir.exists()) {
				uploadRootDir.mkdirs();
			}
			uploadedFiles = new ArrayList<File>();
			for (i = 0; i < part4.length; i++) {
				MultipartFile file = part4[i];
				String name = file.getOriginalFilename();
				System.out.println("Client File Name = " + name);
				if (name != null && name.length() > 0) {
					try {
						byte[] bytes = file.getBytes();
						File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						uploadedFiles.add(serverFile);
						System.out.println("Write file: " + serverFile);
					} catch (Exception e) {
						System.out.println("Error Write file: " + name);
					}
				}
			}
			// Part 7
			uploadRootDir = new File(uploadRootPath+"part7/");
			if (!uploadRootDir.exists()) {
				uploadRootDir.mkdirs();
			}
			uploadedFiles = new ArrayList<File>();
			for (i = 0; i < part7.length; i++) {
				MultipartFile file = part7[i];
				String name = file.getOriginalFilename();
				System.out.println("Client File Name = " + name);
				if (name != null && name.length() > 0) {
					try {
						byte[] bytes = file.getBytes();
						File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						uploadedFiles.add(serverFile);
						System.out.println("Write file: " + serverFile);
					} catch (Exception e) {
						System.out.println("Error Write file: " + name);
					}
				}
			}
			model.addAttribute("uploadedFiles", uploadedFiles);
			User user = userDAO.select(principal.getName());
			Activity activity = new Activity();
			String activityContent = "Create test: " + "<i>" + test.getTestname() + "</i>";
			activity.setUser(user);
			activity.setContent(activityContent);
			activity.setCreateDate(new Date());
			activityDAO.createActivity(activity);
			redirectAttributes.addFlashAttribute("createTestSuccess", true);
		} else {
			redirectAttributes.addFlashAttribute("errorName", true);
		}
		return "redirect:/admin/manageTest";	
	}
	
	@RequestMapping(value="admin/confirmDeleteTest")
	public String confirmDeleteTest(Model model, @RequestParam("testid") int testid) {
		Test test = testDAO.select(testid);
		model.addAttribute("test", test);
		return "confirmDeleteTest";
	}
	
	public static void delete(File file) throws IOException {
		if (file.isDirectory()) {
			if (file.list().length == 0) {
				file.delete();
			} else {
				String files[] = file.list();
				for (String temp : files) {
					File fileDelete = new File(file, temp);
					delete(fileDelete);
				}
				if (file.list().length == 0) {
					file.delete();
				}
			}
		} else {
			file.delete();
		}
	}
	
	@RequestMapping(value="admin/deleteTest")
	public String deleteTest(Model model, RedirectAttributes redirectAttributes, @RequestParam("testid") int testid, 
			Principal principal, HttpServletRequest request) throws IOException {
		String path = request.getServletContext().getRealPath("")+"resources/upload/Test_"+testid+"";
		File folder = new File(path);
		if (folder.exists()) {
			delete(folder);
		}
		User user = userDAO.select(principal.getName());
		Activity activity = new Activity();
		String activityContent = "Delete test: " + "<i>" + testDAO.select(testid).getTestname() + "</i>";
		activity.setUser(user);
		activity.setContent(activityContent);
		activity.setCreateDate(new Date());
		activityDAO.createActivity(activity);
		testDAO.delete(testDAO.select(testid));
		model.addAttribute("tests", testDAO.testList());
		redirectAttributes.addFlashAttribute("delete", true);
		return "redirect:/admin/manageTest";
	}
	
	@RequestMapping(value="admin/viewTest")
	public String viewTest(Model model, @RequestParam("testid") int testid) {
		Test test = testDAO.select(testid);
		int id = 1;
		for(TextCompletion textCompletion : test.getTextCompletions()) {
			String script = textCompletion.getScript();
			List<String> anpha = Arrays.asList("A^B^C^D".split("\\^"));
			for(TextCompletionDetail textCompletionDetail : textCompletion.getTextCompletionDetails()) {
				List<String> answers = Arrays.asList( textCompletionDetail.getAnswer().split("\\^"));
				String answer = "";
				answer = "<select name=\"textCompletion-"+id+ "\"  style=\"color: blue;\">";
				for(int i = 0; i < answers.size(); i++) {
					if (anpha.get(i).equals(textCompletionDetail.getCorrectanswer())) {
						answer = answer+"<option disabled selected value=\""+anpha.get(i)+"\">"+answers.get(i)+"</option>";
					} else {
						answer = answer+"<option disabled value=\""+anpha.get(i)+"\">"+answers.get(i)+"</option>";
					}
				}
				answer+="</select>";
				script = script.replace("___"+textCompletionDetail.getId()+"___", answer);
				id++;
			}
			textCompletion.setScript(script);
		}
		model.addAttribute("test", test);
		return "viewTest";
	}
	
	@RequestMapping(value="testList")
	public String selectTest(Model model,Principal principal) {	
		try {
			model.addAttribute("easyTest", testDAO.select_easy().getTestid());
		} catch (Exception e) {
			model.addAttribute("errorEasy", true);
		}
		try {
			model.addAttribute("mediumTest", testDAO.select_medium().getTestid());
		} catch (Exception e) {
			model.addAttribute("errorMedium", true);
		}
		try {
			model.addAttribute("hardTest", testDAO.select_hard().getTestid());
		} catch (Exception e) {
			model.addAttribute("errorHard", true);
		}
		return "selectTest";
	}
	
	@RequestMapping(value="practiceFullTest/{testid}", method=RequestMethod.GET)	
	public String practiceFullTest(Model model, @PathVariable("testid") int testid){
		Test test = testDAO.select(testid);
		int id = 1;
		for (TextCompletion textCompletion : test.getTextCompletions()) {
			String script = textCompletion.getScript();
			List<String> anpha = Arrays.asList("A^B^C^D".split("\\^"));
			for (TextCompletionDetail textCompletionDetail : textCompletion.getTextCompletionDetails()) {
				List<String> answers = Arrays.asList( textCompletionDetail.getAnswer().split("\\^"));
				String answer = "<select name=\"textCompletion-"+id+ "\"> <option value=''></option>";
				for(int i = 0; i < answers.size(); i++) {
					answer = answer+"<option  value=\""+anpha.get(i)+"\">"+answers.get(i)+"</option>";
				}
				answer+="</select>";
				System.out.println(answer);
				script = script.replace("___"+textCompletionDetail.getId()+"___", answer);
				System.out.println(script);
				id++;
			}
			textCompletion.setScript(script);
		}
		model.addAttribute("test", test);
		return "doTest";
	}
	
	@RequestMapping(value="practiceFullTest/{testid}/submit", method=RequestMethod.POST)
	public String submit(Model model, @PathVariable("testid") int testid, HttpServletRequest request, Principal principal) {
		Test test = testDAO.select(testid);
		int listening = 0;
		int reading = 0;
		int p1=0, p2=0, p3=0, p4=0, p5=0, p6=0, p7=0;
		// Part 1
		List<String> photos = new ArrayList<>();
		for (Photo photo : test.getPhotos()) {
			photos.add(request.getParameter("photo-"+photo.getPhotoid()));
			if (photo.getCorrectanswer().equals(request.getParameter("photo-"+photo.getPhotoid()))) {
				listening++;
				p1++;
			}
		}
		// Part 2
		List<String> questionResponses = new ArrayList<>();
		for (QuestionResponse questionResponse : test.getQuestionResponses()) {
			questionResponses.add(request.getParameter("questionResponse-"+questionResponse.getQuestionresponseid()));
			if (questionResponse.getCorrectanswer().equals(request.getParameter("questionResponse-"+questionResponse.getQuestionresponseid()))) {
				listening++;
				p2++;
			}
		}
		int id = 1;
		// Part 3
		List<String> shortConversations = new ArrayList<>();
		for(ShortConversation shortConversation : test.getShortConversations())
			for(ShortConversationDetail shortConversationDetail : shortConversation.getShortConversationDetails()) {
				shortConversations.add(request.getParameter("shortConversation-"+id));
				if (shortConversationDetail.getCorrectanswer().equals(request.getParameter("shortConversation-"+id))) {
					listening++;
					p3++;
				}
				id++;	
			}
		id = 1;
		// Part 4
		List<String> shortTalks = new ArrayList<>();
		for(ShortTalk shortTalk : test.getShortTalks())
			for(ShortTalkDetail shortTalkDetail : shortTalk.getShortTalkDetails()) {
				shortTalks.add(request.getParameter("shortTalk-"+id));
				if (shortTalkDetail.getCorrectanswer().equals(request.getParameter("shortTalk-"+id))) {
					listening++;
					p4++;
				}
				id++;
			}
		// Part 5
		List<String> incompleteSentences = new ArrayList<>();
		for (IncompleteSentence incompleteSentence : test.getIncompleteSentences()) {
			incompleteSentences.add(request.getParameter("incompleteSentence-"+incompleteSentence.getIncompletesentenceid()));
			if (incompleteSentence.getCorrectanswer().equals(request.getParameter("incompleteSentence-"+incompleteSentence.getIncompletesentenceid()))) {
				reading++;
				p5++;
			}

		}
		id = 1;
		// Part 6
		List<String> textCompletions = new ArrayList<>();
		for(TextCompletion textCompletion : test.getTextCompletions()) {
			String script = textCompletion.getScript();
			List<String> anpha = Arrays.asList("A^B^C^D".split("\\^"));
			for(TextCompletionDetail textCompletionDetail : textCompletion.getTextCompletionDetails()) {
				List<String> answers = Arrays.asList(textCompletionDetail.getAnswer().split("\\^"));
				textCompletions.add(request.getParameter("textCompletion-"+id));
				String answer = "";
				if (textCompletionDetail.getCorrectanswer().equals(request.getParameter("textCompletion-"+id))) {
					reading++;
					p6++;
					answer = "<select name=\"textCompletion-"+id+ "\"  style=\"color: blue;\">";
				} else {
					answer = "<select name=\"textCompletion-"+id+ "\"  style=\"color: red;\">";
				}
				for(int i = 0; i < answers.size(); i++) {
					if (anpha.get(i).equals(textCompletionDetail.getCorrectanswer())) {
						answer=answer+"<option disabled selected value=\""+anpha.get(i)+"\">"+answers.get(i)+"</option>";
					} else {
						if (anpha.get(i).equals(request.getParameter("textCompletion-"+id))) {
							answer = answer+"<option disabled style=\"font-style: italic;font-weight: bold;\" value=\""+anpha.get(i)+"\">"+answers.get(i)+"</option>";
						} else {
							answer = answer+"<option disabled value=\""+anpha.get(i)+"\">"+answers.get(i)+"</option>";
						}
					}
				}
				answer+="</select>";
				script = script.replace("___"+textCompletionDetail.getId()+"___", answer);
				id++;
			}
			textCompletion.setScript(script);
		}
		id = 1;
		// Part 7
		List<String> readingComprehensions = new ArrayList<>();
		for(ReadingComprehension readingComprehension : test.getReadingComprehensions()) {
			for(ReadingComprehensionDetail readingComprehensionDetail : readingComprehension.getReadingComprehensionDetails()) {
				readingComprehensions.add(request.getParameter("readingComprehension-"+id));
				if (readingComprehensionDetail.getCorrectanswer().equals(request.getParameter("readingComprehension-"+id))) {
					reading++;
					p7++;
				}
				id++;
			}
		}
		model.addAttribute("score",scoreDAO.Read(reading)+scoreDAO.List(listening));
		model.addAttribute("reading",reading);
		model.addAttribute("listening", listening);

		model.addAttribute("test", test);
		model.addAttribute("photos", photos);
		model.addAttribute("questionResponses", questionResponses );
		model.addAttribute("shortConversations", shortConversations);
		model.addAttribute("shortTalks", shortTalks);
		model.addAttribute("incompleteSentences", incompleteSentences);
		model.addAttribute("textCompletions", textCompletions);
		model.addAttribute("readingComprehensions", readingComprehensions);

		model.addAttribute("p1", p1);
		model.addAttribute("p2", p2);
		model.addAttribute("p3", p3);
		model.addAttribute("p4", p4);
		model.addAttribute("p5", p5);
		model.addAttribute("p6", p6);
		model.addAttribute("p7", p7);
		
		int score = scoreDAO.Read(reading) + scoreDAO.List(listening);
		User user = userDAO.select(principal.getName());
		MyScore myscore = new MyScore();
		myscore.setUser(user);
		myscore.setScore(score);
		myscore.setTestid(testid);
		myscore.setTimeTest(new Date());
		myscoreDAO.create(myscore);
		Activity activity = new Activity();
		String activityContent = "Do test: " + "<i>" + test.getTestname() + "</i>, Score: " 
				+ score + " with Reading: " + scoreDAO.Read(reading)
				+ ", Listening: " + scoreDAO.List(listening);
		activity.setUser(user);
		activity.setContent(activityContent);
		activity.setCreateDate(new Date());
		activityDAO.createActivity(activity);
		return "submitTest";
	}
}