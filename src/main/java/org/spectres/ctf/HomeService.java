package org.spectres.ctf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);
	private static int points = 0;
	private static int flagsFound = 0;
	public static boolean flagsInitialized = false;
	public static Map<String, Integer> flags = new HashMap<>();
	
	public List<String> listDocuments() throws IOException {
		logger.info("listDocuments START");
		List<String> result = new ArrayList<String>();
		File files = new ClassPathResource("static/documents").getFile();
		for (File file : files.listFiles()) {
			logger.info(file.getPath() + "/" + file.getName());
			if (file.getName().contains("6"))
				continue;
			else
				result.add(file.getName());
		}

		logger.info("listDocuments END");
		return result;
	}

	public String getFileContent(String filename) throws IOException {
		logger.info("getFileContent START");
		String result = "";
		File file = new ClassPathResource("static/documents/" + filename).getFile();
		if (file.exists() && file.isFile()) {
			result = String.join(" ", Files.readAllLines(Paths.get(file.getAbsolutePath())));
		}
		logger.info("getFileContent END");
		return result;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getFlagsFound() {
		return flagsFound;
	}
	
	public void initailizeFags() {
		
		flags.put("first", 1);
		flags.put("ezsecond", 1);
		flags.put("stylish", 1);
		flags.put("bmo", 1);
		flags.put("scriptingrocks", 2);
		flags.put("invisible", 2);
		flags.put("babylove", 2);
		flags.put("maninblack", 2);
		flags.put("yudothis", 2);
		flags.put("banana", 2);
		flags.put("pringles", 2);
		flags.put("thieves", 2);
		flags.put("prototype", 2);
		flags.put("otpyrc", 2);
		flags.put("mathematical", 3);
		flags.put("gohan", 3);
		flags.put("ihurtyoubecauseiloveyou", 3);
		flags.put("greataxe", 3);
		flags.put("perceive", 3);
		
		
	}
	
	public boolean checkFlag(String flag) {
		if (flagsInitialized == false) {
			initailizeFags();
			flagsInitialized = true;
		}
		
		if(flags.containsKey(flag)) {
			points += flags.get(flag);
			flagsFound++;
			flags.remove(flag);
			return true;
		}
		
		return false;
	}
}
