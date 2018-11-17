package org.spectres.ctf;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@Autowired
	private HomeService service;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RelicRepository relicRepository;

	@Autowired
	private SecretRepository secretRepository;

	@RequestMapping(value = "/")
	public String index(HttpServletResponse response, Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);
		response.addCookie(new Cookie("is_admin", "no"));
		return "index";
	}
	
	@RequestMapping(value = "/robots.txt")
	public void robots(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        response.getWriter().write("User-agent: *\nDisallow: /supersecretpage\n");
	        response.setHeader("Server", "flag{mathematical}");
	    } catch (IOException e) {
//	        CustomLogger.info(TAG, "robots", "robots(): "+e.getMessage());
	    }
	}

	@RequestMapping(value = "/users")
	public String users(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		Set<String> roles = auth.getAuthorities().stream()
//			     .map(r -> r.getAuthority()).collect(Collectors.toSet());
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);
		m.addAttribute("greeting", "Users");
		return "users";
	}
	
	@RequestMapping(value = "/addusers")
	public String addUsers(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);
		m.addAttribute("greeting", "Add Users");
		return "addusers";
	}
	
	@RequestMapping(value = "/contact")
	public String contact(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);
		m.addAttribute("greeting", "Leave us a comment");
		return "contact";
	}
	
	@RequestMapping(value = "/comment")
	public String comment(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);
		m.addAttribute("greeting", "Leave us a comment");
		return "contact";
	}
	
	@RequestMapping(value = "/forgotpassword")
	public String forgotpassword(Model m) {
		m.addAttribute("greeting", "Reset your password");
		return "forgotpassword";
	}
	
	@RequestMapping(value = "/superadmin")
	public String moderator(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);
		m.addAttribute("greeting", "Greetings Super Admin");
		m.addAttribute("allTypes", Arrays.asList(Table.ALL));
		m.addAttribute("items", userRepository.findAll());
		return "superadmin";
	}
	
	@RequestMapping(value = "/validate")
	public String validator(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);
		m.addAttribute("greeting", "Validate Flag");
		m.addAttribute("flagsFound", service.getFlagsFound() < 18 ? service.getFlagsFound() : "You found all the flags! Congratulation!");
		m.addAttribute("points", service.getPoints());
		return "validator";
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/validateFlag")
	public String validateFlag(@RequestParam String flag, Model m) {
		
		String message = "";
		if (service.checkFlag(flag)) {
			message = "Congratulation, you have found valid flag";
		} else  {
			message = "Nope, that is not valid flag or it has already been used.";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);
		m.addAttribute("greeting", "Validate Flag");
		m.addAttribute("message", message);
		m.addAttribute("flagsFound", service.getFlagsFound());
		m.addAttribute("points", service.getPoints());
		return "forward:/validate";
	}
	
	@RequestMapping(value = "/lucky7")
	public String lucky7(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);
		m.addAttribute("greeting", "Grumpy Cats or Lucky Sevens?");
		m.addAttribute("areyoulucky", false);
		

		return "lucky7";
	}
	
	@RequestMapping(value = "/supersecretpage")
	public String supersecretpage(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);
		m.addAttribute("greeting", "Everything Stays");

		return "supersecretpage";
	}

	@RequestMapping(value = "/docs")
	public String documents(Model m) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);
		m.addAttribute("greeting", "Helloooo..");
		m.addAttribute("documents", service.listDocuments());

		return "docs";
	}

	@RequestMapping(value = "/adm1n")
	public String admin(Model m) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);
		m.addAttribute("greeting", "Hey there admin!");
		
		return "admin";
	}
	

	@RequestMapping("/search")
	public String search(Model m, @RequestParam(value="name", required=false) String name) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);
		System.out.println("SEARCH table: " + name);
		m.addAttribute("greeting", "Found anything yet?");
		m.addAttribute("allTypes", Arrays.asList(Table.ALL));
		
		switch (name) {
		case "users":
			m.addAttribute("items", userRepository.findAll());
			break;
		case "relics":
			m.addAttribute("items", relicRepository.findAll());
			break;
		case "secrets":
			m.addAttribute("items", secretRepository.findAll());
			break;
		default:
			m.addAttribute("items", Arrays.asList("No", "Such", "Table"));
			break;
		}
		
		if(name.matches("([A-Za-z0-9])\\w+'")) {
			m.addAttribute("items", Arrays.asList("OMG", "DATABASE ERROR", "Don't hurt my precious database!", "Here is you flag{prototype}"));
		}
		
		return "search";
	}

	@GetMapping(path = "/allUsers")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@GetMapping(path = "/allRelics")
	public @ResponseBody Iterable<Relic> getAllRelics() {
		// This returns a JSON or XML with the users
		return relicRepository.findAll();
	}

	@GetMapping(path = "/allSecrets")
	public @ResponseBody Iterable<Secret> getAllSecrets() {
		// This returns a JSON or XML with the users
		return secretRepository.findAll();
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String documentDetails(@RequestParam("name") String name, Model m) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);
		m.addAttribute("name", name);
		m.addAttribute("content", service.getFileContent(name));

		return "details";
	}

	@RequestMapping(value = "/vegas")
	public String vegas(Model m) throws NoSuchAlgorithmException {
		int sevens = SecureRandom.getInstanceStrong().nextInt(8888);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		m.addAttribute("superadminRole", hasAdminRole);

		m.addAttribute("greeting", "Grumpy Cats or Lucky Sevens?");
		m.addAttribute("areyoulucky", sevens == 7777);
		m.addAttribute("sevens", (sevens == 7777 ? "flag{scriptingrocks}" : sevens));
		

		return "lucky7";
	}
}
