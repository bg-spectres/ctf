package org.spectres.ctf;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

	@RequestMapping(value = "/api/users")
	public String allUsers() {

		return "[{\"name\":\"Jake\",\"city\":\"Land of Ooo\",\"phone\":\"444 444\", \"password\":\"myPas$w0rD\"},{\"name\":\"Fin\",\"city\":\"Land of Ooo\",\"phone\":\"444 444\", \"password\":\"BuB8leGuM\"},{\"name\":\"BMO\",\"city\":\"Land of Ooo\",\"phone\":\"444 444\", \"password\":\"flag{bmo}\"}]";
	}
	
	@RequestMapping(value = "/api/comment")
	public String comment() {

		return "[{\"name\":\"Jake\",\"city\":\"Land of Ooo\",\"phone\":\"444 444\", \"password\":\"myPas$w0rD\"},{\"name\":\"Fin\",\"city\":\"Land of Ooo\",\"phone\":\"444 444\", \"password\":\"BuB8leGuM\"},{\"name\":\"BMO\",\"city\":\"Land of Ooo\",\"phone\":\"444 444\", \"password\":\"flag{bmo}\"}]";
	}
	
	@RequestMapping(value = "/api/comment", method = RequestMethod.POST) 
	public String process(@RequestBody Map<String, Object> payload) throws Exception {

		  System.out.println(payload);
		  System.out.println(payload.get("comment"));
		  String comment = payload.get("comment").toString().toLowerCase();
		  if (comment.contains("</script>")) {
			  comment = comment.split("</script>")[0];
		  }
		  if (comment.contains("<script>")) {
			  comment = comment.split("<script>")[1];
			  comment = comment.trim();
		  }
		  
		  Pattern p = null;
		  
		  if ( comment.startsWith("alert('") && comment.endsWith("')")){
			  comment = comment.split("alert\\('")[1];
			  System.out.println(comment);
			  comment = comment.substring(0, comment.length() - 2);
			  p = Pattern.compile("[a-zA-z0-9\\s]*", Pattern.CASE_INSENSITIVE);
			  
		  } else if ( comment.startsWith("alert('") && comment.endsWith("');")) {
			  comment = comment.split("alert\\('")[1];
			  System.out.println(comment);
			  comment = comment.substring(0, comment.length() - 3);
			  p = Pattern.compile("[a-zA-z0-9\\s]*", Pattern.CASE_INSENSITIVE);
			  
		  } else  if ( comment.startsWith("alert(") && comment.endsWith(");")) {
			  comment = comment.split("alert\\(")[1];
			  System.out.println(comment);
			  comment = comment.substring(0, comment.length() - 2);
			  p = Pattern.compile("\\d+");
			  
		  } else  if ( comment.startsWith("alert(") && comment.endsWith(")")) {
			  comment = comment.split("alert\\(")[1];
			  System.out.println(comment);
			  comment = comment.substring(0, comment.length() - 1);
			  p = Pattern.compile("\\d+");
			  
		  } 
		  
		  if (p != null) {
			  Matcher m = p.matcher(comment);
			  boolean b = m.find();
			  if(b) {
				  System.out.println(comment);
				  return "{\"flag\":\"pringles\"}";
			  }
		  }
		  
		  return "";
		}

}
