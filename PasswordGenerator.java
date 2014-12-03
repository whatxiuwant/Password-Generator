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
	private String password;
	private int length;
	private char[] characters = new char[62];
	
	public PasswordGenerator(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void generatePwLength() {
		length = (int) Math.random() * 5 + 8;
	}
	
	public int getPwLength() {
		return length;
	}
	
	public void generateAlphaNumericArray() {
		for (char ch = 'a'; ch <= 'z'; ch++)
			characters[ch - 'a'] = ch;
		for (char ch = 'A'; ch <= 'Z'; ch++)
			characters[ch - 'A' + 26] = ch;
		for (char ch = '0'; ch <= '9'; ch++)
			characters[ch - '0' + 52] = ch;
	}
	
	public char getAlpha() {
		return characters[(int) Math.random()*51];
	}
	
	public char getAlphaNumeric(char[] array) {
		return characters[(int) Math.random()*61];
	}
}
