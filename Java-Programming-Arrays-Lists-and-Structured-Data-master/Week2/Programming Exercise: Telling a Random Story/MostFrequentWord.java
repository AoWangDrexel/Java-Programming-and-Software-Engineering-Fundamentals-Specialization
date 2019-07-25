import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList <String> myWords;
    private ArrayList <Integer> myFreqs;

    public WordFrequencies(){
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>(); 
    }
    
    // finding unique words in the arraylist, myWords including puncuation
    public void findUniques1(){
        // clearing the lists
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        // looping through the list and setting all words to lowercase
        // this or This, would count the same
        for(String word : fr.words()){
            word = word.toLowerCase();
            // if word is not in list, then add it and add the frequency to 1
            if(!myWords.contains(word)){
                myWords.add(word);
                myFreqs.add(1);
            // then if it is in the list, get the frequency +1
            }else{
                int idx = myWords.indexOf(word);
                int value = myFreqs.get(idx);
                myFreqs.set(idx, value + 1);
            }
        }
        
        for(int i = 0; i < myWords.size();i++){
            System.out.println(myWords.get(i) +": " + myFreqs.get(i));    
        }
    }
    // more advanced/easy way of counting words using HashMaps
    // since they have built in keys and values, like dictionaies in Python
    public void findUniques2(){
        Map<String, Integer> countWords = new HashMap<>();
        FileResource fr = new FileResource();
        
        for(String word : fr.words()){
            word = word.toLowerCase();
            if (!countWords.containsKey(word)) {
                countWords.put(word, 1);
            } else {
                countWords.put(word, countWords.get(word) + 1);
            }
        }
        
        // printing the keys and values out like in the previous method
        for(Map.Entry<String,Integer> entry: countWords.entrySet()){
             System.out.println(entry.getKey() +": " + entry.getValue()); 
        }
        System.out.println(countWords);
    }
    
    public void tester(){
        //findUniques1();
        findUniques2();
    }
    
    // finds the index of the greatest frequency of words
    public int findIndexOfMax(){
        int max = Integer.MIN_VALUE;
        int idx = 0; 
        for(int i = 0; i < myFreqs.size(); i++){
            if(myFreqs.get(i) > max){
                max = myFreqs.get(i);
                idx = i;
            }
        }
        return idx;
    }
    
    public void testFindIndexOfMax(){
        // use testwordfreqs.txt
        
        //findUniques2();
        // if uses findUnique2, return 0
        // because HashMaps stores in different order, but a regardless is
        // most reoccuring
        
        findUniques1();
        // returns 2, a is most occuring
        System.out.println(findIndexOfMax());   
    }
}
