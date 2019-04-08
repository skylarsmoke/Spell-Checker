package Lab4;

import java.io.File;
import java.util.Scanner;

/**
 * 
 * @author Skylar Smoker and Austin Hull
 *
 */

public class SpellCheck {

	Scanner words;
	Scanner check;
	
	public SpellCheck(String fileWords, String fileCheck) {
		
		File fileW = new File(fileWords);
		File fileC = new File(fileCheck);
		
		try {
			words = new Scanner(fileW);
			
		} catch (Exception e) {
			System.out.println("Could not find file with words!");
			System.exit(0);
		}
		
		try {
			check = new Scanner(fileC);
		} catch (Exception e) {
			System.out.println("Could not find file to spell check!");
			System.exit(0);
		}
		
		readFileCheck(fileC);
		
	}
	
	private void readFileCheck(File file) {
		
		while (check.hasNextLine()) {
			String wordToCheck = check.nextLine();
			System.out.println(wordToCheck);
		}
		
	}
	
	public static void main(String args[]) {
		
		SpellCheck spell = new SpellCheck(args[0], args[1]);
		
	}
	
}

