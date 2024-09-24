package me.trihung.services;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class Utils {
	
	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
	    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = resizedImage.createGraphics();
	    graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
	    graphics2D.dispose();
	    return resizedImage;
	}
	
	public static String encodeImage(String path) {
		byte[] fileContent;
		try {
			fileContent = Files.readAllBytes(new File(path).toPath());
			return Base64.getEncoder().encodeToString(fileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
