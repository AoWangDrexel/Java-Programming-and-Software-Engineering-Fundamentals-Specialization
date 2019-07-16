public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        // return true if stringa appears at least twice in stringb, otherwise false
        // twoOccurrences(“by”, “A story by Abby Long”) --> true
        // twoOccurrences(“a”, “banana”) -- > true
        // twoOccurrences(“atg”, “ctgtatgta”)-- > false
        
        int findIndex = stringb.indexOf(stringa);
        if(findIndex == -1){
            return false;
        }else{
            findIndex = stringb.indexOf(stringa, findIndex + stringa.length());
        }
        if(findIndex == -1){
            return false;
        }else{
            return true;
        }
    }
    
    
    public String lastPart(String stringa, String stringb){
        int findIndex = stringb.indexOf(stringa);
        if(findIndex == -1){
            return stringb;
        }
        return stringb.substring(findIndex + stringa.length());
    }
    
    public void testingTwoOccurrences(){
        boolean occur = twoOccurrences("by", "A story by Abby Long"); //--> true
        System.out.println(occur);
        
        occur = twoOccurrences("a","banana"); // --> true 
        System.out.println(occur);
        
        occur = twoOccurrences("atg", "ctgtatgta"); // --> false;
        System.out.println(occur);
        
    }
    
    public void testingLastPart(){
        String s = lastPart("an","banana");
        System.out.println(s); // print out ana
        
        s = lastPart("zoo", "forest");
        System.out.println(s); // prints out forest
    }
}
