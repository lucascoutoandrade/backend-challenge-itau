package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	public static Properties getProp() {
		Properties props = new Properties();
		FileInputStream file = null;
		try {
			file = new FileInputStream("./src/test/resources/application.test.properties");
			//file = new FileInputStream("./configs/application.test.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			props.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props;

	}

}
