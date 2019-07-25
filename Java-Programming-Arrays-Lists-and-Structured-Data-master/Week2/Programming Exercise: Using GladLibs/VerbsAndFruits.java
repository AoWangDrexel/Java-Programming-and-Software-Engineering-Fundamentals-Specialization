import edu.duke.*;
import java.util.*;

public class GladLib {
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private ArrayList<String> usedWords;
    private int wordAlreadyUsed;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    // emptry constructor defaults to the duke URL for data
    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLib(String source){
        initializeFromSource(source);
        usedWords = new ArrayList<>();
        wordAlreadyUsed = 0;
        myRandom = new Random();
    }

    // from the method readIt, all the arraylists are stored with 
    // arraylists stored with each line of specified files
    private void initializeFromSource(String source) {
        adjectiveList= readIt(source+"/adjective.txt"); 
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");      
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");
        verbList = readIt(source+ "/verb.txt");
        fruitList = readIt(source+ "/fruit.txt");
    }
    // chooses a random number from the size of the arraylist and returns it
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    // if label equals one of the titles of the file, get a random
    // word from it
    private String getSubstitute(String label) {
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (label.equals("verb")){
            return randomFrom(verbList);
        }
        if (label.equals("fruit")){
            return randomFrom(fruitList);
        }
        return "**UNKNOWN**";
    }

    // checks each word line by line in the arraylists if surrounded by
    // <>, places it with a random word in the file from the method, getSubstitute
    // if word is picked more than once, word is chosen again till a different one
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
            wordAlreadyUsed++;
        }
        usedWords.add(sub);
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
    // loops through the website's data or file data line by line
    // stores each line into a string ArrayList
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
        System.out.println();
        System.out.println("Words replaced: " + wordAlreadyUsed);
    }
}
