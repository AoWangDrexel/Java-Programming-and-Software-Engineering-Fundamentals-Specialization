import edu.duke.*;

public class TestCaesarCipher {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // returns int array of the number of occurences of each letter
    public int [] countLetters(String s){
        s = s.toUpperCase();
        int [] countOccurence = new int[26];

        // loops through each letter in s
        for(int i = 0; i < s.length(); i++){
            char letter = s.charAt(i);
            // determines if char is a letter, if it is find the index of it in the alphabet
            if(Character.isLetter(letter)){
                int index = alphabet.indexOf(letter);

                // count each occurence from 0 to 25
                countOccurence[index]++; 
            }
        }
        return countOccurence;
    }

    // returns the index of the largest value in values
    public int maxIndex(int [] values){
        int largest = 0;
        for(int i = 0; i < values.length ; i++){
            if(values[largest] < values[i]){
                largest = i;
            }
        }
        return largest;
    }
    
    public void simpleTests(){
        //FileResource fr = new FileResource();
        //String text = fr.asString();
        String text = "Hello there, how are you doing? Nice meeting you, friend.";
        CaesarCipher cc = new CaesarCipher(7);
        String cipherText = cc.encrypt(text);
        System.out.println("Encrypted: " + cipherText);
        // Olssv aolyl, ovd hyl fvb kvpun? Upjl tllapun fvb, mypluk.
        System.out.println("Decrypted: " + cc.decrypt(cipherText));
        // Hello there, how are you doing? Nice meeting you, friend.
        
        breakCaesarCipher(cipherText);
        // Hello there, how are you doing? Nice meeting you, friend.
    }
    
    // prints out the decoded msg by finding the most common letter and 
    // substracting it from the index of E in the alphabet
    public void breakCaesarCipher(String input){
        int maxIndex = maxIndex(countLetters(input));
        int key = maxIndex - alphabet.indexOf('E');
        if(key < 0){
            key += 26;
        }
        CaesarCipher cc = new CaesarCipher(key);
        System.out.println("The decrypted message: " + cc.decrypt(input));
    }
}
