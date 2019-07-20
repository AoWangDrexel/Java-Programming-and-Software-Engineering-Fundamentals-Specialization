import edu.duke.*;

public class TestCaesarCipherTwo {
    // return a new String that is every other character from message starting with the start position
    public String halfOfString(String msg, int start){
        String newMsg = "";
        for(int i = start; i < msg.length(); i+=2){
            newMsg += msg.charAt(i);
        }
        return newMsg;
    }

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
        CaesarCipherTwo cct1 = new CaesarCipherTwo(23,17);
        CaesarCipherTwo cct2 = new CaesarCipherTwo(8,21);
        String text ="First Legion";
        System.out.println(cct1.encrypt(text));
        // should print Czojq Ivdzle
        text = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        System.out.println(cct2.encrypt(text));
        //Io iwjv jz dv bcm kjvammmikz mwju edbc twpz pvb wi awm v ncmxmqnm xvzog. TMGT TJCY!
        
        FileResource fr = new FileResource();
        String cipher = fr.asString();
        breakCaesarCipher(cipher); // use mysteryTwoKeysPractice.txt

    }

    public void breakCaesarCipher(String input){
        String half1 = halfOfString(input,0);
        String half2 = halfOfString(input,1);
        int index1 = maxIndex(countLetters(half1));
        int index2 = maxIndex(countLetters(half2));
        int key1 = index1 - 4;
        int key2 = index2 - 4;
        CaesarCipherTwo cct = new CaesarCipherTwo(key1,key2);
        System.out.println("Key 1: " + key1 + " Key 2: " + key2);
        System.out.println("Decrypted: " + cct.decrypt(input) );

    }
}
