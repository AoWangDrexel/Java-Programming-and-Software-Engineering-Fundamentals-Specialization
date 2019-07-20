import edu.duke.*;
public class CaesarBreaker {
    
    // returns int array of the number of occurences of each letter
    public int [] countLetters(String s){
        s = s.toUpperCase();
        int [] countOccurence = new int[26];
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
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

    public void testCountLetters(){
        String s = "AAABBBBZZEE"; // 3 A, 4 B, 2 Z, 2 E
        int [] letters = countLetters(s);
        for(int i = 0; i < letters.length; i++){
            System.out.println((char)(65+i) + ": " + letters[i]);
        }
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

    public void testMaxIndex(){
        String s = "AAABBBBZZEE";
        int [] letters = countLetters(s);
        System.out.println(maxIndex(letters)); // should print out 1 because b has 4 occurences

    }
    public String decrypt(String s, int key){
        CaesarCipher cc = new CaesarCipher();
        String msg = cc.encrypt(s, 26 - key);
        return msg;
    }

    public void testDecrypt(){
        String s = "Olssv aolyl, ovd hyl fvb kvpun? Upjl tllapun fvb, mypluk. ";
        int key = getKey(s);
        System.out.println(decrypt(s, key));
        
        // should return 
        // Hello there, how are you doing? Nice meeting you, friend. key : 7
    }

    // return a new String that is every other character from message starting with the start position
    public String halfOfString(String msg, int start){
        String newMsg = "";
        for(int i = start; i < msg.length(); i+=2){
            newMsg += msg.charAt(i);
        }
        return newMsg;
    }

    public void testHalfString(){
        String s = "Qbkm Zgis";
        int key = 0;
        System.out.println(halfOfString(s,key)); // should print out Qk gs
        s = "Qbkm Zgis";
        key = 1;
        System.out.println(halfOfString(s,key)); // bmZi
    }

    public int getKey(String s){
        int maxIndex = maxIndex(countLetters(s));
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int key = maxIndex - alphabet.indexOf('E');
        
        //System.out.println("Encrypted message: " + s);
        //System.out.println("The letter with the highest frequency: " + alphabet.charAt(maxIndex(countLetters(s))));
        
        if(key > 0){
            return key;
        }
        
        return 26 + key;
    }

    public void testGetKey(){
        String s = "Olssv aolyl, ovd hyl fvb kvpun? Upjl tllapun fvb, mypluk.";
        System.out.println(getKey(s)); // should print out 7
    }
    
    public String decryptTwoKeys(String encrypted){
        String half1 = halfOfString(encrypted,0);
        String half2 = halfOfString(encrypted,1);
        int key1 = getKey(half1);
        int key2 = getKey(half2);
        System.out.println("The first key: " + key1 + " The second key: " + key2);
        CaesarCipher cc = new CaesarCipher();
        //System.out.println(cc.encryptTwoKeys(encrypted,24,6));
        return cc.encryptTwoKeys(encrypted,26 - key1,26 - key2);
        // for Top ncmy qkff vi vguv vbg ycpx, make them 24, 6
    }
    

    public void testDecryptTwoKeys(){
         FileResource fr = new FileResource();
         System.out.println(decryptTwoKeys(fr.asString()));
         
         //String s = "Top ncmy qkff vi vguv vbg ycpx";
         //decryptTwoKeys(s); // Run like wild to beat the wind
         
         //s = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
         //System.out.println(decryptTwoKeys(s));
         //Eren and Emily have evil eerie green ears
    }

    
    
}
