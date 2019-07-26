import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import edu.duke.*;

public class WordsInFiles {
    private Map<String, ArrayList<String>> commonWord;

    public WordsInFiles(){
        commonWord = new HashMap<>();       
    }

    /*
     * This method should add all the words from f into the map.
     * If a word is not in the map, then you must create a new 
     * ArrayList of type String with this word, and have the word
     * map to this ArrayList. If a word is already in the map, then
     * add the current filename to its ArrayList, unless the filename
     * is already in the ArrayList. 
     */
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        ArrayList<String> al; 
        for(String word : fr.words()){
            if(commonWord.containsKey(word)){
                al = commonWord.get(word); 
                if(!al.contains(fileName)){
                    al.add(fileName);
                }
            }else{
                al = new ArrayList<>();
                al.add(fileName);
                commonWord.put(word, al);
            }
        }
    }

    /*
     * This method first clears the map, and then uses a DirectoryResource
     * to select a group of files. For each file, it puts all of its 
     * words into the map by calling the method addWordsFromFile. 
     * The remaining methods to write all assume that the HashMap 
     * has been built.
     */
    public void buildWordFileMap(){
        commonWord.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);        
        } 
    }

    /*
     *  This method returns the maximum number of files any word
     *  appears in, considering all words from a group of files. 
     */
    public int maxNumber(){
        int max = Integer.MIN_VALUE;
        for(String word : commonWord.keySet()){
            if(commonWord.get(word).size() > max){
                max = commonWord.get(word).size();
            }  
        } 
        return max;
    }

    /*
     * This method returns an ArrayList of words that appear
     * in exactly number files.
     */
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> wordsInFile = new ArrayList<>();
        for(String word : commonWord.keySet()){
            if(commonWord.get(word).size() == number){
                wordsInFile.add(word);
            }   
        }  
        return wordsInFile;      
    }

    /*
     * This method prints the names of the files this word
     * appears in, one filename per line.
     */
    public void printFilesIn(String word){
        for(String files : commonWord.get(word)){  
            System.out.print(files + " ");
        }
    }

    /*
     * This method should call buildWordFileMap to select 
     * a group of files and build a HashMap of words, with 
     * each word mapped to an ArrayList of the filenames this 
     * word appears in, determine the maximum number of files
     * any word is in, considering all words, and determine
     * all the words that are in the maximum number of files
     * and for each such word, print the filenames of the files it is in. 
     */
    public void tester(){
        buildWordFileMap();
        System.out.println("The maximum number where a number appears in files: " 
            + maxNumber());
        System.out.println("The files are: " + wordsInNumFiles(maxNumber()));

        for(Map.Entry<String, ArrayList<String>> entry : commonWord.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        /*
         * The maximum number where a number appears in files: 3
         * The files are: [cats, and]
         * love: [brief3.txt, brief4.txt]
         * cats: [brief1.txt, brief3.txt, brief4.txt]
         * are: [brief1.txt, brief2.txt]
         * and: [brief1.txt, brief3.txt, brief4.txt]
         * silly: [brief2.txt]
         * dogs: [brief2.txt, brief3.txt]
         * animals: [brief3.txt]
         * birds: [brief4.txt]
         * cute: [brief1.txt]
         * funny: [brief1.txt]
         */
    }

}

