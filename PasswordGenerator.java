package pgpkg;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class PasswordGenerator {
	static String password = "";
	static char[] characters = new char[62];		//char array of all possible pw characters
	static int length = 0;
	static Set<String> passwords = new HashSet<String>();
	
	public static void main(String[] args) throws IOException {
		characters = generateAlphaNumericArray();
		
		password = generatePw();
		
		if (!isValidPw(password)) {
			password = "";
			password = generatePw();
		}
		
		/*	Using message box:
		 * 	UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("SanSerif", Font.PLAIN, 20)));
		 * 	UIManager.put("OptionPane.messageForeground", Color.BLACK);
		 * 	JOptionPane.showMessageDialog(null,"The password is: " + password + "\nThe password length is: " + length);
		*/
		
		File input = new File("storedPasswords.txt");
		PrintWriter pw = new PrintWriter(new FileOutputStream(input, true));
		pw.println(password);
		pw.close();
		
		Scanner scan = new Scanner(input);
		while(scan.hasNextLine())
			passwords.add(new String(scan.nextLine()));
		scan.close();
	}
	
	public static String generatePw() {
		length = generatePwLength();
		password += getAlpha();
		
		for (int i = 0; i < length - 2; i++)
			password += getAlphaNumeric();
		
		String sc = getSpecialCase();
		int index = getSCIndex();
		password = password.substring(0, index) + sc + password.substring(index);
		
		return password;
	}
	
	public static char[] generateAlphaNumericArray() {
		for (char ch = 'A'; ch <= 'Z'; ch++)
			characters[ch - 'A'] = ch;
		for (char ch = 'a'; ch <= 'z'; ch++)
			characters[ch - 'a' + 26] = ch;
		for (char ch = '0'; ch <= '9'; ch++)
			characters[ch - '0' + 52] = ch;
		
		return characters;
	}
	
	public static int generatePwLength() {
		return (int) (Math.random() * 5 + 8);
	}
	
	public static char getAlpha() {
		return characters[(int) (Math.random() * 51)];
	}
	
	public static char getAlphaNumeric() {
		return characters[(int) (Math.random() * 61)];
	}
	
	public static boolean hasLower(String pw) {
		for (int i = 0; i < length - 1; i++)
			if (pw.charAt(i) >= 'a' && pw.charAt(i) <= 'z')
				return true;
		
		return false;
	}
	
	public static boolean hasUpper(String pw) {
		for (int i = 0; i < length - 1; i++)
			if (pw.charAt(i) >= 'A' && pw.charAt(i) <= 'Z')
				return true;
		
		return false;
	}
	
	public static boolean hasNumeric(String pw) {
		for (int i = 0; i < length - 1; i++)
			if (pw.charAt(i) >= '0' && pw.charAt(i) <= '9')
				return true;
		
		return false;
	}
	
	public static String getSpecialCase() {
		int random = (int) (Math.random() * 2);
		
		if (random == 0)
			return "_";
		else
			return "-";
	}
	
	public static int getSCIndex() {
		int index = (int) (Math.random() * length - 1);
		
		if (index == 0 || index == length)
			index = (int) (Math.random() * length - 1);
			
		return index;	//can't be first or last index;
	}
	
	public static boolean isValidPw(String pw) {
		if (hasLower(pw) && hasUpper(pw) && hasNumeric(pw))
			return true;
		
		return false;
	}
}