import edu.duke.*;
import java.util.regex.*;

public class WordPlay {
   
    public boolean isVowel1(char ch){
        // using regex, regular expressions to see if ch is a vowel
        return regexChecker("[/aeiouAEIOU/]", ch + "");
    }
    
    public boolean isVowel2(char ch){
        String vowel = "AEIOUaeiou"; // string of all upper and lower case vowels
        int index = vowel.indexOf(ch); // find index of letter in the vowel
        return index >= 0; // if greater than 0, or not -1, it's a vowel
    }
    
    public static boolean regexChecker(String theRegex, String strCheck) {
	Pattern checkRegex = Pattern.compile(theRegex); // creates a regex object
	Matcher regexMatcher = checkRegex.matcher(strCheck); // create match object
	return regexMatcher.matches(); // returns whether or not string is in the regex
    }
    
    public void testIsVowel(){
        char letter = 'F';
        System.out.println(letter + " vowel? " + isVowel1(letter)); // should return false
        System.out.println(letter + " vowel? " + isVowel2(letter));
        letter = 'a';
        System.out.println(letter + " vowel? " + isVowel1(letter)); // should return true
        System.out.println(letter + " vowel? " + isVowel2(letter));
    }
    
    public String replaceVowels(String phrase, char ch){
        // create stringbuilder object
        StringBuilder sb = new StringBuilder(phrase);
        //loop through each letter
        for(int i = 0; i < sb.length(); i++){
            // if letter is a vowel, replace letter with ch
            if(isVowel1(sb.charAt(i))){
                sb.setCharAt(i, ch);  
            }
        }
        return sb.toString();
    }
    
    public void testReplaceVowels(){
        String s = "Hello World";
        char ch = '*';
        System.out.println(replaceVowels(s, ch)); // should return H*ll* W*rld 
    }
    
    public String emphasize(String phrase, char ch){
        // create stringbuilder object
        StringBuilder sb = new StringBuilder(phrase);
        //loop through each letter
        for(int i = 0; i < sb.length(); i++){
            // if letter is equal to ch, replace with either + or *
            // if i is even, replace letter with +, odd, *
            // make the letters capitalize to enture lower and upper cases are covered 
            // for equality
            if(Character.toUpperCase(sb.charAt(i)) == Character.toUpperCase(ch)){
                if((i + 1) % 2 == 0){
                    sb.setCharAt(i, '+');
                }else{
                    sb.setCharAt(i, '*');
                }
            }
        }
        return sb.toString();
    }
    
    public void testEmphasize(){
        String s = "dna ctgaaactga";
        char ch = 'a';
        System.out.println(emphasize(s,ch)); // returns “dn* ctg+*+ctg+”
        s = "Mary Bella Abracadabra";
        System.out.println(emphasize(s,ch)); // “M+ry Bell+ +br*c*d*br+”
        
        
    }
   
  
}
