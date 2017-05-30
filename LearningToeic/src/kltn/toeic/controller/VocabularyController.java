package kltn.toeic.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kltn.toeic.dao.ActivityDAO;
import kltn.toeic.dao.UserDAO;
import kltn.toeic.dao.VocabularyDAO;
import kltn.toeic.model.Activity;
import kltn.toeic.model.User;
import kltn.toeic.model.Vocabulary;
import kltn.toeic.util.ConstantValues;

@Controller
public class VocabularyController {
	@Autowired
	UserDAO userDAO;
	@Autowired
	VocabularyDAO vocabularyDAO;
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
	
	@RequestMapping(value="admin/manageVocabulary", method=RequestMethod.GET)
	public String manageVacabulary(Model model, @RequestParam(value="numPage", required=false) String numPage){
		if (numPage == null) numPage = "1";
		List<Vocabulary> listVocabulary = vocabularyDAO.getVocabularyPaging(Integer.parseInt(numPage));
		long numVocab = vocabularyDAO.getVocabulary();
		long pageVocab = numVocab/ConstantValues.NUMBER_ROW_8 + (numVocab%ConstantValues.NUMBER_ROW_8 == 0 ? 0 : 1);
		model.addAttribute("listVocabulary", listVocabulary);
		model.addAttribute("pageVocab", pageVocab);
		model.addAttribute("numPage", numPage);
		return "manageVocabulary";
	}
	
	@RequestMapping(value="admin/searchVocabulary", method=RequestMethod.GET)
	public String searchVacabularyAd(Model model, @RequestParam(value="numPage", required=false) String numPage,
			@RequestParam("searchKey") String searchKey){
		if (numPage == null) numPage = "1";
		List<Vocabulary> listVocabulary = vocabularyDAO.searchVocabulary(searchKey, Integer.parseInt(numPage));
		long numVocab = vocabularyDAO.getSearchVocabulary(searchKey);
		long pageVocab = numVocab/ConstantValues.NUMBER_ROW_8 + (numVocab%ConstantValues.NUMBER_ROW_8 == 0 ? 0 : 1);
		model.addAttribute("listVocabulary", listVocabulary);
		model.addAttribute("pageVocab", pageVocab);
		model.addAttribute("numPage", numPage);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("numVocab", numVocab);
		return "searchVocabularyAd";
	}
	
	@RequestMapping(value="searchVocabulary",method=RequestMethod.GET)
	public String searchVacabulary(Model model,@RequestParam(value="numPage", required=false) String numPage,
			@RequestParam("searchKey") String searchKey){
		if (numPage == null) numPage = "1";
		List<Vocabulary> listVocabulary = vocabularyDAO.searchVocabulary(searchKey, Integer.parseInt(numPage));
		long numVocab = vocabularyDAO.getSearchVocabulary(searchKey);
		long pageVocab = numVocab/ConstantValues.NUMBER_ROW_8 + (numVocab%ConstantValues.NUMBER_ROW_8 == 0 ? 0 : 1);
		model.addAttribute("listVocabulary", listVocabulary);
		model.addAttribute("pageVocab", pageVocab);
		model.addAttribute("numPage", numPage);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("numVocab", numVocab);
		return "searchVocabulary";
	}
	
	@RequestMapping(value="admin/updateVocabulary")
	public String update(Model model, @RequestParam("vocabularyid") int vocabularyid){
		model.addAttribute("vocabulary", vocabularyDAO.select(vocabularyid));
		return "updateVocabulary";
	}
	
	@RequestMapping(value="admin/updateVocabulary", method=RequestMethod.POST)
	public String updateVocabulary(Model model, RedirectAttributes redirectAttributes, @RequestParam("vocabularyid") int vocabularyid,
			@RequestParam("vocabularyname") String vocabularyname, @RequestParam("video") MultipartFile video,
			@RequestParam("document") MultipartFile document, @RequestParam("image") MultipartFile image,
			Principal principal, HttpServletRequest request) throws IOException {
		Vocabulary vocabulary = vocabularyDAO.select(vocabularyid);
		vocabulary.setVocabularyname(vocabularyname);
//		String oldDocument = vocabulary.getDocument();
//		System.out.println("document: " + oldDocument);
//		System.out.println("video.getSize(): " + video.getSize());
//		System.out.println("video.isEmpty():" + video.isEmpty());
//		System.out.println("document.getSize(): " + document.getSize());
//		System.out.println("document.isEmpty():" + document.isEmpty());
//		System.out.println("document name: " + document.getOriginalFilename());
//		System.out.println("video name: " + video.getOriginalFilename());
		String uploadRootPath = request.getServletContext().getRealPath("")+"resources/upload/vocabulary/";
		File uploadRootDir = new File(uploadRootPath);
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		if (!image.isEmpty()) {
			String filePath = request.getServletContext().getRealPath("")+"resources/upload/vocabulary/"+vocabulary.getImage();
			File fileImage = new File(filePath);
			if (fileImage.exists()) {
				fileImage.delete();
			}
			vocabulary.setImage(image.getOriginalFilename());
			try {
				byte[] bytes = image.getBytes();
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + image.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
			}
		}
		if (!video.isEmpty()) {
			String filePath = request.getServletContext().getRealPath("")+"resources/upload/vocabulary/"+vocabulary.getVideo();
			File fileVideo = new File(filePath);
			if (fileVideo.exists()) {
				fileVideo.delete();
			}
			vocabulary.setVideo(video.getOriginalFilename());
			try {
				byte[] bytes = video.getBytes();
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + video.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
			}
		}
			
		if (!document.isEmpty()) {
			String filePath = request.getServletContext().getRealPath("")+"resources/upload/vocabulary/"+vocabulary.getDocument();
			File fileDocument = new File(filePath);
			if (fileDocument.exists()) {
				fileDocument.delete();
			}
			vocabulary.setDocument(document.getOriginalFilename());
			try {
				byte[] bytes = document.getBytes();
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + document.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
			}
		}
		vocabularyDAO.update(vocabulary);
		User user = userDAO.select(principal.getName());
		Activity activity = new Activity();
		String activityContent = "Update lesson vocabulary: " + "<i>" + vocabularyname + "</i>";
		activity.setUser(user);
		activity.setContent(activityContent);
		activity.setCreateDate(new Date());
		activityDAO.createActivity(activity);
		model.addAttribute("vocabulary", vocabularyDAO.select(vocabularyid));
		redirectAttributes.addFlashAttribute("updateVocabularySuccess",true);
		return "redirect:/admin/manageVocabulary";
	}
	
	@RequestMapping(value="admin/confirmDeleteVocabulary")
	public String delete(Model model, @RequestParam("vocabularyid") int vocabularyid){
		model.addAttribute("vocabulary", vocabularyDAO.select(vocabularyid));
		return "confirmDeleteVocabulary";
	}
	
	@RequestMapping(value="admin/deleteVocabulary")
	public String deleteGrammar(Model model, RedirectAttributes redirectAttributes, @RequestParam("vocabularyid") int vocabularyid, 
			Principal principal, HttpServletRequest request){
		Vocabulary vocabulary = vocabularyDAO.select(vocabularyid);
		System.out.println("doc: " + vocabulary.getDocument());
		System.out.println("vid: " + vocabulary.getVideo());
		String fileDoc = request.getServletContext().getRealPath("")+"resources/upload/vocabulary/"+vocabulary.getDocument();
		File fileDocument = new File(fileDoc);
		if (fileDocument.exists()) {
			fileDocument.delete();
		}
		String fileVid = request.getServletContext().getRealPath("")+"resources/upload/vocabulary/"+vocabulary.getVideo();
		File fileVideo = new File(fileVid);
		if (fileVideo.exists()) {
			fileVideo.delete();
		}
		String fileImg = request.getServletContext().getRealPath("")+"resources/upload/vocabulary/"+vocabulary.getImage();
		if (!vocabulary.getImage().equals("uet.png")) {
			File fileImage = new File(fileImg);
			if (fileImage.exists()) {
				fileImage.delete();
			}
		}
		vocabularyDAO.delete(vocabulary);
		User user = userDAO.select(principal.getName());
		Activity activity = new Activity();
		String activityContent = "Delete lesson vocabulary: " + "<i>" + vocabulary.getVocabularyname() + "</i>";
		activity.setUser(user);
		activity.setContent(activityContent);
		activity.setCreateDate(new Date());
		activityDAO.createActivity(activity);
		List<Vocabulary> listVocabulary = vocabularyDAO.vocabularies();
		redirectAttributes.addFlashAttribute("delete","true");
		model.addAttribute("listVocabulary",listVocabulary);
		return "redirect:/admin/manageVocabulary";
	}
	
	@RequestMapping(value="admin/postVocabulary", method = RequestMethod.GET)
	public String postVocabulary() {
		return "postVocabulary";
	}
	
	@RequestMapping(value="admin/postVocabulary", method = RequestMethod.POST)
	public String postVocabularyPost(Model model, RedirectAttributes redirectAttributes,
			@RequestParam("vocabularyname") String vocabularyname, @RequestParam("video") MultipartFile video,
			@RequestParam("document")MultipartFile document, @RequestParam("image") MultipartFile image,
			Principal principal, HttpServletRequest request) throws IOException {
		System.out.println("image :" + image.getOriginalFilename());
		if (!vocabularyDAO.checkExits(vocabularyname)) {
			Vocabulary vocabulary = new Vocabulary();
			vocabulary.setVocabularyname(vocabularyname);
			vocabulary.setDocument(document.getOriginalFilename());
			vocabulary.setVideo(video.getOriginalFilename());
			String uploadRootPath = request.getServletContext().getRealPath("")+"resources/upload/vocabulary/";
			File uploadRootDir = new File(uploadRootPath);
			if (!uploadRootDir.exists()) {
				uploadRootDir.mkdirs();
			}
			try {
				byte[] bytes = video.getBytes();
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + video.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
			}
			try {
				byte[] bytes = document.getBytes();
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + document.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
			}
			if (image.isEmpty()) {
				vocabulary.setImage("uet.png");
			} else {
				vocabulary.setImage(image.getOriginalFilename());
				try {
					byte[] bytes = image.getBytes();
					File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + image.getOriginalFilename());
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
				}
			}
			vocabularyDAO.create(vocabulary);
			User user = userDAO.select(principal.getName());
			Activity activity = new Activity();
			String activityContent = "Create lesson vocabulary: " + "<i>" + vocabulary.getVocabularyname() + "</i>";
			activity.setUser(user);
			activity.setContent(activityContent);
			activity.setCreateDate(new Date());
			activityDAO.createActivity(activity);
			redirectAttributes.addFlashAttribute("postVocabularySuccess",true);
		} else {
			redirectAttributes.addFlashAttribute("errorName",true);
			redirectAttributes.addFlashAttribute("vocabularyname", vocabularyname);
		}
		return "redirect:/admin/manageVocabulary";
	}
	
	@RequestMapping(value="vocabulary")
	public String vocabulary(Model model, @RequestParam(value="numPage", required=false) String numPage) {
		if (numPage == null) numPage = "1";
		List<Vocabulary> listVocabulary = vocabularyDAO.getVocabularyPaging(Integer.parseInt(numPage));
		long numVocab = vocabularyDAO.getVocabulary();
		long pageVocab = numVocab/ConstantValues.NUMBER_ROW_8 + (numVocab%ConstantValues.NUMBER_ROW_8 == 0 ? 0 : 1);
		model.addAttribute("listVocabularys", listVocabulary);
		model.addAttribute("pageVocab", pageVocab);
		model.addAttribute("numPage", numPage);
		return "vocabulary";
	}
	
	@RequestMapping(value="vocabulary/{index}")
	public String selectVocabulary(Model model,  @PathVariable("index") int index, Principal principal) {
		model.addAttribute("vocabulary", vocabularyDAO.select(index));
		User user = userDAO.select(principal.getName());
		Activity activity = new Activity();
		String activityContent = "Learn lesson vocabulary: " + "<i>" + vocabularyDAO.select(index).getVocabularyname() + "</i>";
		activity.setUser(user);
		activity.setContent(activityContent);
		activity.setCreateDate(new Date());
		activityDAO.createActivity(activity);
		return "vocabularyDetail";
	}
}
