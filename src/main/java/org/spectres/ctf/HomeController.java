package org.spectres.ctf;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String index(HttpServletResponse response) {
		response.addCookie(new Cookie("is_admin", "no"));
		return "index";
	}

	@RequestMapping(value = "/users")
	public String users(Model m) {
		m.addAttribute("greeting", "Grumpy Cats or Lucky Sevens?");
		m.addAttribute("areyoulucky", false);

		return "users";
	}

	@RequestMapping(value = "/docs")
	public String documents(Model m) throws IOException {
		m.addAttribute("greeting", "Helloooo..");
		m.addAttribute("documents", service.listDocuments());

		return "docs";
	}

	@RequestMapping(value = "/admin")
	public String admin(Model m) throws IOException {
		m.addAttribute("greeting", "Hey there admin!");
		m.addAttribute("allTypes", Arrays.asList(Table.ALL));
		m.addAttribute("items", userRepository.findAll());
		
		return "admin";
	}
	

	@RequestMapping("/search")
	public String search(Model m, @RequestParam(value="name", required=false) String name) {
		System.out.println("SEARCH table: " + name);
		m.addAttribute("greeting", "Hey there admin!");
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
		
		return "admin";
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
		m.addAttribute("name", name);
		m.addAttribute("content", service.getFileContent(name));

		return "details";
	}

	@RequestMapping(value = "/vegas")
	public String vegas(Model m) throws NoSuchAlgorithmException {
		int sevens = SecureRandom.getInstanceStrong().nextInt(888);

		m.addAttribute("greeting", "Grumpy Cats or Lucky Sevens?");
		m.addAttribute("areyoulucky", sevens == 777);
		m.addAttribute("sevens", (sevens == 777 ? "flag{sandra.plata!}" : sevens));

		return "users";
	}
}
