package Lab4;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Skylar Smoker and Austin Hull
 *
 */

public class SpellCheck {
	
	Hashtable<Integer, String> dict;
	BufferedWriter write;
	Scanner words;
	Scanner check;
	Scanner other;
	
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
		

		dict = new Hashtable<Integer, String>();
		int i = 0;
		while (words.hasNextLine()) {
			String word = words.nextLine();
			dict.put(i, word);
			i++;
		}
		
		words.close();
		
		try {
			write = new BufferedWriter(new FileWriter("mydoc-checked.txt"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		while (check.hasNextLine()) {
			String wordToCheck = check.nextLine();
			suggestions(wordToCheck);
		}
		
		try {
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		other.close();
	}
	
	private void suggestions(String wordToCheck) {
		
		if (!dict.contains(wordToCheck)) {
			System.out.println(wordToCheck + ", did you mean?");
			makeSuggestions(wordToCheck);
		} else {
			try {
				write.write(wordToCheck + " - Correct");
				write.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void makeSuggestions(String wordToCheck) {
		other = new Scanner(System.in);
		String beginning1 = wordToCheck.substring(0, 2);
		String beginning2 = wordToCheck.substring(0, 3);
		String end = getLast(wordToCheck, 1);
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		Random random = new Random();
		int i = 0;
		int j = 0;
		int x = 0;
		while(dict.get(i) != null) {
			if (dict.get(i).length() == wordToCheck.length() || dict.get(i).length() == (wordToCheck.length() - 1) || dict.get(i).length() == (wordToCheck.length() + 1)) {
				if (dict.get(i).startsWith(beginning1) && dict.get(i).endsWith(end)) {
					list1.add(i);
					
				}
			}
			
			if (dict.get(j).length() == wordToCheck.length() || dict.get(j).length() == (wordToCheck.length() - 1) || dict.get(j).length() == (wordToCheck.length() + 1)) {
				if (dict.get(j).startsWith(beginning2)) {
					list2.add(j);
				}
			}
			
			if (dict.get(x).length() == wordToCheck.length() || dict.get(x).length() == (wordToCheck.length() - 1) || dict.get(x).length() == (wordToCheck.length() + 1)) {
				if (dict.get(x).startsWith(beginning2)) {
					list3.add(x);
				}
			}
			i++;
			j++;
			x++;
		}
		String one = dict.get(list1.get(random.nextInt(list1.size())));
		String two = dict.get(list2.get(random.nextInt(list2.size())));
		String three = dict.get(list3.get(random.nextInt(list3.size())));
		System.out.println("1. " + one);
		System.out.println("2. " + two);
		System.out.println("3. " + three);
		System.out.println("4. something else?");

		int userChoice = 0;
		while (!other.hasNextInt()) {
			System.out.println("Please enter a valid menu option!");
			other.nextLine();
		}
		
		userChoice = other.nextInt();
		
		while (userChoice > 4 || userChoice < 1) {
			System.out.println("Please enter a valid menu option!");
			userChoice = other.nextInt();
		}
		
		switch(userChoice) {
		case 1:
			try {
				write.write(one + " - Fixed from " + wordToCheck);
				write.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println();
			break;
		case 2:
			try {
				write.write(two + " - Fixed from " + wordToCheck);
				write.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println();
			break;
		case 3:
			try {
				write.write(three + " - Fixed from " + wordToCheck);
				write.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println();
			break;
		case 4:
			System.out.println("Enter word: ");
			String userInput = other.next();
			System.out.println();
			try {
				write.write(userInput + " - Fixed from " + wordToCheck);
				write.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		
	}
	
	private String getLast(String inputString, int subStringLength) {
		int length = inputString.length();
		if (length <= subStringLength) {
			return inputString;
		}
		int startIndex = length-subStringLength;
		return inputString.substring(startIndex);
}


	
	public static void main(String args[]) {
		
		@SuppressWarnings("unused")
		SpellCheck spell = new SpellCheck(args[0], args[1]);
		
		
	}
	
}

