import edu.duke.*;
public class Part4 {
    public void readLinks(){
        int leftQuote = 0;
        int rightQuote = 0;
        
        // URLResource from the Duke library
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        
        // goes through every link word by word
        for(String s: ur.words()){
            
            // confirms that links include youtube.com
            if(s.toLowerCase().indexOf("youtube.com") != -1){
                leftQuote = s.indexOf("\"");
                rightQuote = s.indexOf("\"", leftQuote + 1);
                System.out.println(s.substring(leftQuote + 1, rightQuote));
            }
        }
    }
}
