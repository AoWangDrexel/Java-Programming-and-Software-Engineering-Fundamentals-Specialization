import edu.duke.*;
import java.util.regex.*;

public class CaesarCipher {
     public String encrypt(String input, int key){
        // creates stringbuilder object to hold input
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        // shifting the alphabet by key amount
        String shiftedAlphabetUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0, key);
        String shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0, key);
        // goes through each letter in encrypted
        for(int i = 0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);  
            int idx;
            // find index of the current letter in the original alphabet
            if(Character.isUpperCase(currChar)){
                idx = alphabetUpper.indexOf(currChar); 
            }else{
                idx = alphabetLower.indexOf(currChar); 
            }
            // if index is found, use the index from the original alphabet
            // to get the letter from the encrypted alphabet
            if(idx != -1){
                char newChar;
                if(Character.isUpperCase(currChar)){
                    newChar = shiftedAlphabetUpper.charAt(idx);     
                }else{
                    newChar = shiftedAlphabetLower.charAt(idx); 
                }
                // set the letters in the stringbuilder object with the
                // letters in the encrypted alphabet
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();    
    }
    // my own algorithm, which i think is more concise
    public static String caesarCipher(String text, int key) {
        String cipherText = "";
	for (int i = 0, n = text.length(); i < n; i++) {
	    // regexChecker("[A-Z]", text.charAt(i)) + "" or vv
	    if (Character.isAlphabetic(text.charAt(i))) {
	        if (Character.isUpperCase(text.charAt(i))) {
	            cipherText += (char) (((int) text.charAt(i) + key - 65) % 26 + 65);
	        } else {
	            cipherText += (char) (((int) text.charAt(i) + key - 97) % 26 + 97);
		}
            } else {
                cipherText += text.charAt(i);
	    }

	}
	return cipherText;
    }
    
    public static boolean regexChecker(String theRegex, String strCheck) {
	Pattern checkRegex = Pattern.compile(theRegex);
	Matcher regexMatcher = checkRegex.matcher(strCheck);
	return regexMatcher.matches();
    }
    
    public void testEncrypt(){
        String text = "FIRST LEGION ATTACK EAST FLANK!";
        System.out.println(encrypt(text, 23)); // “CFOPQ IBDFLK XQQXZH BXPQ CIXKH!”
        text = "First Legion";
        System.out.println(encrypt(text, 23)); // should print out Cfopq Ibdflk
        System.out.println(encrypt(text, 17)); // Wzijk Cvxzfe
 
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26 - key);
        System.out.println(decrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        String cipherText = "";
	for (int i = 0, n = input.length(); i < n; i++) {
	    // regexChecker("[A-Z]", text.charAt(i)) + "" or vv
	    if (Character.isAlphabetic(input.charAt(i))) {
	        // deciding to alternate the keys by asking whether i is even or odd
	        if (i % 2 == 0){
	            if (Character.isUpperCase(input.charAt(i))) {
	                cipherText += (char) (((int) input.charAt(i) + key1 - 65) % 26 + 65);
        	    } else {
        	        cipherText += (char) (((int) input.charAt(i) + key1 - 97) % 26 + 97);
        	    }
                }else{
                    if (Character.isUpperCase(input.charAt(i))) {
	                cipherText += (char) (((int) input.charAt(i) + key2 - 65) % 26 + 65);
        	    } else {
        	        cipherText += (char) (((int) input.charAt(i) + key2 - 97) % 26 + 97);
        	    }
                    
                }
            } else {
                cipherText += input.charAt(i);
	    }

	}
	return cipherText;
    }
    
    public void testEncryptTwoKeys(){
        String s = "First Legion";
        System.out.println(encryptTwoKeys(s, 23, 17)); // Czojq Ivdzle
        
        
    }
}
