package pgpkg;

public class PasswordGenerator {
/*
	(0) create empty string to store password
	(1) generate random number between 8 and 12 inclusive, to be used as the length of the password
	(2) create char array of 62 elements: A-Z, a-z, 0-9
	(3) create method getAlpha()
	(4) create method getAlphaNumeric()
	(5) call getAlpha() for firstCharacter
	(6) call getAlphaNumeric() 7-11 times, depending on the length of the password to be generated, call it password.length() - 1, so if its 8 characters long, call it 7 times
	(7) randomly select "_" or "-"
	(8) randomly place in random positions 2 - password.length(), replacing the character at that position
	(9) check requirements: 1 upper, 1 lower, 1 numeric; if not repeat process
	(10) return generated password
*/
	static String password = "";
	static int length;
	static char[] characters = new char[62];
	
	public static void main(String[] args) {
		characters = generateAlphaNumericArray();
		System.out.println(characters);
		password = generatePw();
		System.out.println(password);
	}
	
	public static String generatePw() {
		length = generatePwLength();
		password += getAlpha();
		
		for (int i = 0; i < length - 1; i++)
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
		return (int) Math.random() * 5 + 8;
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
		int random = (int) Math.random() * 2;
		
		if (random == 0)
			return "_";
		else
			return "-";
	}
	
	public static int getSCIndex() {
		return length;	//can't be first or last index;
	}
	/*
	public static boolean isValidPw(String pw) {
		
	}*/
}
