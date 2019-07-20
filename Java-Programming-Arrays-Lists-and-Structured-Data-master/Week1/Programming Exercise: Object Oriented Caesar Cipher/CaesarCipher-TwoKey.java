public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;
    
    // constructor that initializes the alphabet, shifted alphabets, and keys
    public CaesarCipherTwo(int key1, int key2){
        alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        this.key1 = key1;
        this.key2 = key2;
    }
    
    // encrypts the input
    public String encrypt(String input){
        // creates StringBuilder object sb
        StringBuilder sb = new StringBuilder(input);
        // goes though each letter of the input
        for(int i = 0; i < input.length(); i++){
            char currChar = input.charAt(i);
            int idx;
            // if char is uppercase, find the index of it in the alphabet
            // else make the char uppercase using the Character object
            if(Character.isUpperCase(currChar)){
                idx = alphabet.indexOf(currChar);
            }else{
                idx = alphabet.indexOf(Character.toUpperCase(currChar)); 
            } 
            // if index of the found char in alphabet is found
            // find letter in the location of shifted alphabet
            if(idx != -1){
                char newChar;
                // if i is even, get shifted from alphabet1
                // else get from alphabet2
                if(i % 2 == 0){
                    if(Character.isUpperCase(currChar)){
                        newChar = shiftedAlphabet1.charAt(idx);     
                    }else{
                        newChar = Character.toLowerCase(shiftedAlphabet1.charAt(idx)); 
                    }
                }else{
                    if(Character.isUpperCase(currChar)){
                        newChar = shiftedAlphabet2.charAt(idx);     
                    }else{
                        newChar = Character.toLowerCase(shiftedAlphabet2.charAt(idx)); 
                    }   
                }
                sb.setCharAt(i, newChar);
            }
        }
        return sb.toString();   
    }

    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26-key1, 26-key2);
        return cc.encrypt(input);
    }

}
