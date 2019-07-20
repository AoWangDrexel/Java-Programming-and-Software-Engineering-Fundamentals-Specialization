public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipher(int key){
        alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet= alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key; // could likely use the this.mainKey = mainKey
    } 

    public String encrypt(String input){
        // creates stringbuilder object to hold input
        StringBuilder encrypted = new StringBuilder(input);
        // goes through each letter in encrypted
        for(int i = 0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);  
            int idx;
            // find index of the current letter in the original alphabet
            if(Character.isUpperCase(currChar)){
                idx = alphabet.indexOf(currChar); 
            }else{
                idx = alphabet.indexOf(Character.toUpperCase(currChar)); 
            }
            // if index is found, use the index from the original alphabet
            // to get the letter from the encrypted alphabet
            if(idx != -1){
                char newChar;
                if(Character.isUpperCase(currChar)){
                    newChar = shiftedAlphabet.charAt(idx);     
                }else{
                    newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx)); 
                }
                // set the letters in the stringbuilder object with the
                // letters in the encrypted alphabet
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();    
    }

    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);   
    }
}
