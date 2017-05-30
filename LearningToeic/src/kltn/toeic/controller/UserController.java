package kltn.toeic.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kltn.toeic.dao.ActivityDAO;
import kltn.toeic.dao.UserDAO;
import kltn.toeic.model.Activity;
import kltn.toeic.model.User;
import kltn.toeic.util.ConstantValues;

@Controller
public class UserController {
	@Autowired
	UserDAO userDAO;
	@Autowired
	ActivityDAO activityDAO;
	@Autowired
	JavaMailSender mailSender;
	
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
	
	@RequestMapping(value="signin", method=RequestMethod.GET)	
	public String login(){
		return "signin";
	}
	
	@RequestMapping(value="register", method=RequestMethod.GET)	
	public String register(){	
		return "register";
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)	
	public String register(Model model, @ModelAttribute("user") User newUser) throws MessagingException, UnsupportedEncodingException {
		if (!userDAO.checkUser(newUser.getEmail())) {
			newUser.setPassword(getHashPassword(newUser.getPassword()));
			newUser.setConfirm(RandConfirm());
			newUser.setRole("ROLE_USER");
			newUser.setEnabled(0);
			newUser.setImage("user.png");
			userDAO.createUser(newUser);		
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("luyenthitoeic3110@gmail.com","LEARNING TOEIC");
			helper.setTo(newUser.getEmail());
			helper.setSubject("LEARNING TOEIC Confirm account");
			String url="<a href=\"http://localhost:8080/LearningToeic/register/"+newUser.getConfirm()+"\"> this link </a>";
			String body=newUser.getFullname()+"<br/><br/>Welcome!<br/><br/> Thanks for signing up. Please follow "+url+"to activate your account.<br/><br/><br/>---<br/>LEARNING TOEIC";
			helper.setText(body, true);
			mailSender.send(message);
			model.addAttribute("registerSuccess", "true");
			System.out.println(url);
		} else {
			model.addAttribute("userError", "true");
			model.addAttribute("mailError", newUser.getEmail());
			return null;
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/register/{confirmCode}")
	public String confirmRegister(Model model, @PathVariable("confirmCode") String confirmCode) {
		if (userDAO.checkConfirm(confirmCode)) {
			userDAO.enable(confirmCode);
			model.addAttribute("activateSuccess", "true");
		} else {
			model.addAttribute("confirmRegisterError", "true");
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="forgotPassword", method=RequestMethod.GET)
	public String forgotPassword() {
		return "forgotPassword";
	}

	@RequestMapping(value="forgotPassword", method=RequestMethod.POST)
	public String forgotPasswordPost(Model model, @RequestParam("email") String email) throws MessagingException, UnsupportedEncodingException {
		if (!userDAO.checkUser(email)) {
			model.addAttribute("userError", true);
		} else {
			model.addAttribute("userError", false);
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("luyenthitoeic3110@gmail.com","LEARNING TOEIC");
			User user = userDAO.select(email);
			user.setPassword(RandPassword());
			helper.setTo(user.getEmail());
			helper.setSubject("LEARNING TOEIC Account reset password");
			String body=user.getFullname()+",<br/><br/>We are sending you this email because we received a password reset request for your account "+user.getEmail()+".<br/>If you did not make such a request, please ignore this email.<br/><br/>New password: "+user.getPassword()+"<br/><br/>Please go back to http://localhost:8080/LearningToeic and sign in with your email address and your new password.<br/><br/>Thank you<br/><br/>--<br/>LEARING TOEIC";
			helper.setText(body, true);
			mailSender.send(message);
			user.setPassword(getHashPassword(user.getPassword()));
			userDAO.update(user);
		}
		model.addAttribute("mailError",email);
		return null;
	}
	
	@RequestMapping(value = "/profile/profile_info", method = {RequestMethod.GET})
	public String profileInfo(Model model, Principal principal) {
		User user = userDAO.select(principal.getName());
		model.addAttribute("user", user);
		return "profile_info";
	}
	
	@RequestMapping(value = "/profile/profile_edit", method = {RequestMethod.GET})
	public String profileEdit(Model model, Principal principal) {
		User user = userDAO.select(principal.getName());
		model.addAttribute("user", user);
		return "profile_edit";
	}

	@RequestMapping(value="/profile/profile_edit", method = {RequestMethod.POST})	
	public String profileEdit(Model model, @RequestParam("fullname") String fullname,
			@RequestParam("username") String username, RedirectAttributes redirectAttributes, Principal principal){		
		User user = userDAO.select(principal.getName());
		user.setFullname(fullname);
		user.setUsername(username);
		userDAO.update(user);
		Activity activity = new Activity();
		String activityContent = "Edit profile.";
		activity.setUser(user);
		activity.setContent(activityContent);
		activity.setCreateDate(new Date());
		activityDAO.createActivity(activity);
		model.addAttribute("updateAccountSuccess", true);
		model.addAttribute("fullname", userDAO.select(principal.getName()).getFullname());
		redirectAttributes.addFlashAttribute("email", userDAO.select(principal.getName()).getEmail());
		redirectAttributes.addFlashAttribute("updateAccountSuccess",true);
		return "redirect:/profile/profile_info";
	}
	
	@RequestMapping(value = "/profile/profile_change_password", method = {RequestMethod.GET})
	public String changePassword(Model model) {
		return "profile_change_password";
	}
	
	@RequestMapping(value="/profile/profile_change_password", method = {RequestMethod.POST})	
	public String changePassword(Model model, RedirectAttributes redirectAttributes, 
			@RequestParam("oldPassword") String oldPassword, @RequestParam("password") String password, 
			@RequestParam("confrimPassword") String confrimPassword, Principal principal){		
		User user = userDAO.select(principal.getName());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (passwordEncoder.matches(oldPassword, user.getPassword())) {
			if (password.equals(confrimPassword)) {
				user.setPassword(getHashPassword(password));
				userDAO.update(user);
				Activity activity = new Activity();
				String activityContent = "Change password.";
				activity.setUser(user);
				activity.setContent(activityContent);
				activity.setCreateDate(new Date());
				activityDAO.createActivity(activity);
				model.addAttribute("fullname", userDAO.select(principal.getName()).getFullname());
				model.addAttribute("email", userDAO.select(principal.getName()).getEmail());
				redirectAttributes.addFlashAttribute("updateAccountSuccess", true);
			} else {
				redirectAttributes.addFlashAttribute("errorPassword", true);
			}
		} else {
			redirectAttributes.addFlashAttribute("errorPass", "true");
		}
		return "redirect:/profile/profile_change_password";
	}
	
	@RequestMapping(value = "/profile/profile_change_image", method = {RequestMethod.GET})
	public String changeImage(Model model) {
		return "profile_change_image";
	}
	
	@RequestMapping(value = "/profile/profile_change_image", method = {RequestMethod.POST})
	public String changeImage(Model model, Principal principal, @RequestParam("image") MultipartFile image, HttpServletRequest request) {
		User user = userDAO.select(principal.getName());
		String uploadRootPath = request.getServletContext().getRealPath("")+"resources/upload/profile/";
		File uploadRootDir = new File(uploadRootPath);
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		if (!image.isEmpty()) {
			String filePath = request.getServletContext().getRealPath("")+"resources/upload/profile/"+user.getImage();
			if (!user.getImage().equals("user.png")) {
				File fileImage = new File(filePath);
				if (fileImage.exists()) {
					fileImage.delete();
				}
			}
			user.setImage(image.getOriginalFilename());
			try {
				byte[] bytes = image.getBytes();
				File serverFile = new File(uploadRootDir.getAbsolutePath()
					+ File.separator + image.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
			}
		}
		userDAO.update(user);
		Activity activity = new Activity();
		String activityContent = "Upload profile image.";
		activity.setUser(user);
		activity.setContent(activityContent);
		activity.setCreateDate(new Date());
		activityDAO.createActivity(activity);
		model.addAttribute("user", userDAO.select(principal.getName()));
		return "profile_change_image";
	}
	
	@RequestMapping(value="/profile/my_activities", method=RequestMethod.GET)
	public String getMyActivity(Model model, Principal principal, @RequestParam(value="numPage", required=false) String numPage){
		User user = userDAO.select(principal.getName());
		if(numPage == null) numPage = "1";
		List<Activity> activities = activityDAO.getActivityByUser(user.getEmail(), Integer.parseInt(numPage));
		long numActivity = activityDAO.getNumberActivityByUser(user.getEmail());
		long pageActivity = (numActivity/ConstantValues.NUMBER_ROW_9) + (numActivity%ConstantValues.NUMBER_ROW_9 == 0 ? 0 : 1);
		model.addAttribute("numPage", numPage);
		model.addAttribute("activities", activities);
		model.addAttribute("numactivity", activities.size());
		model.addAttribute("pageActivity", pageActivity);
		return "my_activities";
	}
	
	@RequestMapping(value="subadmin/manageUser", method=RequestMethod.GET)
	public String manageUser(Model model, @RequestParam(value="numPage", required=false) String numPage){
		if (numPage == null) numPage = "1";
		List<User> listUser = userDAO.getUserPaging(Integer.parseInt(numPage));
		long numUser = userDAO.getUser();
		long pageUser = numUser/ConstantValues.NUMBER_ROW_6 + (numUser%ConstantValues.NUMBER_ROW_6 == 0 ? 0 : 1);
		model.addAttribute("listUser", listUser);
		model.addAttribute("pageUser", pageUser);
		model.addAttribute("numPage", numPage);
		return "manageUser";
	}
	
	@RequestMapping(value="subadmin/list_user_active", method=RequestMethod.GET)
	public String listUserActive(Model model, @RequestParam(value="numPage", required=false) String numPage){
		if (numPage == null) numPage = "1";
		List<User> listUser = userDAO.getUserActivePaging(Integer.parseInt(numPage));
		long numUser = userDAO.getUserActive();
		long pageUser = numUser/ConstantValues.NUMBER_ROW_6 + (numUser%ConstantValues.NUMBER_ROW_6 == 0 ? 0 : 1);
		model.addAttribute("listUser", listUser);
		model.addAttribute("pageUser", pageUser);
		model.addAttribute("numPage", numPage);
		model.addAttribute("numUserActive", numUser);
		return "list_user_active";
	}
	
	@RequestMapping(value="subadmin/list_user_blocked",method=RequestMethod.GET)
	public String listUserBlocked(Model model, @RequestParam(value="numPage", required=false) String numPage){
		if (numPage == null) numPage = "1";
		List<User> listUser = userDAO.getUserBlockedPaging(Integer.parseInt(numPage));
		long numUser = userDAO.getUserBlocked();
		long pageUser = numUser/ConstantValues.NUMBER_ROW_6 + (numUser%ConstantValues.NUMBER_ROW_6 == 0 ? 0 : 1);
		model.addAttribute("listUser", listUser);
		model.addAttribute("pageUser", pageUser);
		model.addAttribute("numPage", numPage);
		model.addAttribute("numUserBlocked", numUser);
		return "list_user_blocked";
	}
	
	@RequestMapping(value="subadmin/searchUser", method=RequestMethod.GET)
	public String searchUser(Model model, @RequestParam(value="numPage", required=false) String numPage,
			@RequestParam("searchKey") String searchKey){
		if (numPage==null) numPage="1";
		List<User> listUser = userDAO.searchUser(searchKey, Integer.parseInt(numPage));
		long numUser = userDAO.getSearchUser(searchKey);
		long pageUser = numUser/ConstantValues.NUMBER_ROW_6 + (numUser%ConstantValues.NUMBER_ROW_6 == 0 ? 0 : 1);
		model.addAttribute("listUser", listUser);
		model.addAttribute("pageUser", pageUser);
		model.addAttribute("numPage", numPage);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("numUser", numUser);
		return "searchUser";
	}
	
	@RequestMapping(value="subadmin/confirmDeleteUser")
	public String delete(Model model, @RequestParam("email") String email){
		model.addAttribute("users", userDAO.select(email));
		return "confirmDeleteUser";
	}
	
	@RequestMapping(value="subadmin/deleteUser")
	public String deleteUser(Model model, RedirectAttributes redirectAttributes, @RequestParam("email") String email, 
			HttpServletRequest request) {
		User user = userDAO.select(email);
		String iProfile = request.getServletContext().getRealPath("")+"resources/upload/profile/"+user.getImage();
		if (!user.getImage().equals("user.png")) {
			File fileImage = new File(iProfile);
			if (fileImage.exists()) {
				fileImage.delete();
			}
		}
		userDAO.delete(user);
		List<User> listUser = userDAO.listUsers();
		redirectAttributes.addFlashAttribute("delete", true);
		model.addAttribute("listUser", listUser);
		return "redirect:/subadmin/manageUser";
	}
	
	@RequestMapping(value="subadmin/changeRole", method=RequestMethod.GET)
	public String changeRole(Model model, @RequestParam(value="email") String email) {
		model.addAttribute("users", userDAO.select(email));
		return "changeRole";
	}
	
	@RequestMapping(value="subadmin/changeRole", method=RequestMethod.POST)
	public String changeRole(Model model, RedirectAttributes redirectAttributes, 
			@RequestParam(value="role") String role, @RequestParam(value="email") String email) {
		User user = userDAO.select(email);
		System.out.println("email: " + user.getEmail());
		System.out.println("role: " + role);
		user.setRole(role);
		userDAO.update(user);
		redirectAttributes.addFlashAttribute("changeSuccess", true);
		return "redirect:/subadmin/manageUser";
	}
	
	@RequestMapping(value="subadmin/activeUser")
	public @ResponseBody String activeUser(@RequestParam("email") String email) {
		User user = userDAO.select(email);
		if (userDAO.activeUser(user)) {
			userDAO.update(user);
			return "done";
		} else {
			return "error";
		}
	}
	
	@RequestMapping(value="subadmin/blockUser")
	public @ResponseBody String blockUser(@RequestParam("email") String email) {
		User user = userDAO.select(email);
		if (userDAO.blockUser(user)) {
			userDAO.update(user);
			return "done";
		} else {
			return "error";
		}
	}
	
	@RequestMapping(value = "/subadmin")
	public String indexSubAdmin(Model model) {
		long numAllUser = userDAO.getUser();
		long numUserActive = userDAO.getUserActive();
		long numUserBlocked = userDAO.getUserBlocked();
		model.addAttribute("numAllUser", numAllUser);
		model.addAttribute("numUserActive", numUserActive);
		model.addAttribute("numUserBlocked", numUserBlocked);
		return "indexSubAdmin";
	}
	
	public String getHashPassword(String password) {  
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
		String hashedPassword = passwordEncoder.encode(password);  
		return hashedPassword;  
	}
	
	public String RandConfirm() {
		String rs = "";
		String temp = "qwertyuioplkjhgfdsazxcvbnmMNBVCXZASDFGHJKLPOIUYTREWQ12345678901234567890";
		Random r = new Random();
		for (int i = 0; i < 48; i++) {
			int t = r.nextInt(temp.length());
			rs = rs+temp.charAt(t);
		}
		return rs;
	}
	
	public String RandPassword() {
		String rs = "";
		String temp = "qwertyuioplkjhgfdsazxcvbnmMNBVCXZASDFGHJKLPOIUYTREWQ1234567890_!@#$%^&*()";
		Random r = new Random();
		for (int i = 0; i < 16;i++) {
			int t = r.nextInt(temp.length());
			rs = rs+temp.charAt(t);
		}
		return rs;
	}
}
