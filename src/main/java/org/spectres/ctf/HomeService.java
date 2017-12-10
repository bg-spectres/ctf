package org.spectres.ctf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);

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
}
