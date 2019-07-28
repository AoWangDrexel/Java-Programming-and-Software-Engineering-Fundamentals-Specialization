import edu.duke.*;
import java.util.*;

public class GladLib {
    // declaring ArrayLists for each of the brackets in the gladlib txt files
    private Map<String, ArrayList<String>> myMap;
    private List<String> usedWords;
    private List<String> categoriesUsed;
    private int repeatedWords;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLib(){
        myMap = new HashMap<>();
        usedWords = new ArrayList<>();
        categoriesUsed = new ArrayList<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        repeatedWords = 0;
    }

    public GladLib(String source){
        myMap = new HashMap<>();
        usedWords = new ArrayList<>();
        categoriesUsed = new ArrayList<>();
        initializeFromSource(source);
        myRandom = new Random();
        repeatedWords = 0;
    }

    private void initializeFromSource(String source) {
        String [] categories = {"adjective", "noun", "color", "country", "name",
                "animal", "timeframe", "verb", "fruit"};
        for(String category : categories){
            myMap.put(category, readIt(source + "/" + category + ".txt"));
        }
    }

    // returns a random string from the individual txt files 
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if(myMap.containsKey(label)){
            if(!categoriesUsed.contains(label)){
                categoriesUsed.add(label);
            }
            return randomFrom(myMap.get(label));
        }
        return "**UNKNOWN**";
    }

    // returns the replaced word from the gladlib file from the
    // specific lists
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(usedWords.contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
            repeatedWords++;
        }
        if(!usedWords.contains(sub)){
            usedWords.add(sub);
        }
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nTotal number of words replaced: " + repeatedWords);
        System.out.println("Total number of possible words: " + totalWordsInMap());
        System.out.println("Total numer of words considered from " + categoriesUsed.size()
            + " categories : " + totalWordsConsidered());
        System.out.println(categoriesUsed);
        usedWords.clear();
        categoriesUsed.clear();
        repeatedWords = 0;
    }
    
    // This method returns the total number of words in all the ArrayLists in the HashMap.
    private int totalWordsInMap(){
        int total = 0;
        for(String categories : myMap.keySet()){
            total += myMap.get(categories).size();
        } 
        return total;
    }
    
    /*
     * This method returns the total number of words in the ArrayLists 
     * of the categories that were used for a particular GladLib. 
     * If only noun, color, and adjective were the categories used
     * in a GladLib, then only calculate the sum of all the words 
     * in those three ArrayLists.
     */
    private int totalWordsConsidered(){
        int total  = 0;
        for(String category : categoriesUsed){
            total += myMap.get(category).size();
        }
        return total;
    }

}
