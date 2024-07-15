package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LerArquivoJsonPayload {
	
	 public static String readerJson(String pathFile) {
	        try (BufferedReader reader = new BufferedReader(new FileReader(pathFile))) {
	            StringBuilder stringBuilder = new StringBuilder();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                stringBuilder.append(line).append("\n");
	            }
	            return stringBuilder.toString();
	        } catch (IOException e) {
	            return null;
	        }
	    }

}
