package selenium_api;

import java.util.Random;

public class RandomEmail {
	static String getRandomEmail() {
		return "random" +  new Random().nextInt() + "@gmail.com";
	}
	

}
