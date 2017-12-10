package org.spectres.ctf;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@Autowired
	private HomeService service;
	
	@RequestMapping(value = "/")
	public String index() {
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
		m.addAttribute("greeting", "Heloooo..");
		m.addAttribute("documents", service.listDocuments());
		
		return "docs";
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String documentDetails(@RequestParam("name") String name, Model m) throws IOException {
		m.addAttribute("name", name);
		m.addAttribute("content", service.getFileContent(name));
		
		return "details";
	}
	
	@RequestMapping(value = "/vegas") 
	public String vegas(Model m) throws NoSuchAlgorithmException {
		int sevens = 777; // SecureRandom.getInstanceStrong().nextInt(888);

		m.addAttribute("greeting", "Grumpy Cats or Lucky Sevens?");
		m.addAttribute("areyoulucky", sevens == 777);
		m.addAttribute("sevens", (sevens == 777 ? "flag{--luckyYOU!--}" : sevens));
		
		return "users";
	}
}
