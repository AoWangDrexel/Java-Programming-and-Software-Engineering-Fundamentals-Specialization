public class Part2 {
    public int howMany(String stringa, String stringb){
        int count = 0; // count number of stringa's in stringb
        int startIndex = 0; // start checking in index 0 for indexOf
        int index = 0;
        
        // as long as there are stringa's in stringb
        while(true){
            index = stringb.indexOf(stringa, startIndex);
            // if stringa exists in stringb, count + 1, else break
            if(index != -1){
                count++;
            }else{
                break;
            }
            // start looking for stringa after the latter stringa
            startIndex = index + stringa.length();
        }
        
        return count;
    }
    
    public void testHowMany(){
        int count = howMany("GAA","ATGAACGAATTGAATC"); 
        System.out.println(count); // should print 3
        
        count = howMany("AA", "ATAAAA"); 
        System.out.println(count); // should print 2
        
        
    }
}
