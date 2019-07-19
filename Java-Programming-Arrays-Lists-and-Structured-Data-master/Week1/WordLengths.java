import edu.duke.*;
public class WordLengths {
    public void countWordLengths(FileResource fr, int[] counts){
        // finds the longest string in the file
        // creates the length for the counts int array, **optional
        int findMaxLength = Integer.MIN_VALUE;
        for (String s : fr.words()){
            if(s.length() > findMaxLength){
                findMaxLength = s.length();
            }
        }

        System.out.println(findMaxLength);

        //int [] counts = new int[findMaxLength + 1];

        // goes through each word in file
        // nested loop that goes through each word and each letter in the word
        // if word has non alphabetical characters in the beginning 0, or in the end
        // length() - 1, it will be removed
        for(String word : fr.words()){
            for(int i = 0; i < word.length(); i++){
                if((i == 0 || i == word.length() - 1) && !Character.isLetter(word.charAt(i))){
                    if(i == 0){
                        word = word.substring(1);
                    }
                    if(i == word.length() - 1){
                        word = word.substring(0, word.length() - 1);
                    }
                }
            }
            System.out.println(word);
            counts[word.length()]++;        
        }

        for(int i = 0; i < findMaxLength + 1; i++){
            System.out.println("Length: " + i + ", Frequencies: " + counts[i]);
        }
    }

    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int [] counts = new int[31];
        countWordLengths(fr,counts);
        // should print 3 out
        System.out.println("The most common word length in the file is: " + indexOfMax(counts));
        /*
         * Length: 0, Frequencies: 0
         * Length: 1, Frequencies: 0
         * Length: 2, Frequencies: 2
         * Length: 3, Frequencies: 3
         * Length: 4, Frequencies: 2
         * Length: 5, Frequencies: 1
         * Length: 6, Frequencies: 1
         * Length: 7, Frequencies: 1
         * Length: 8, Frequencies: 2
         * Length: 9, Frequencies: 0
         * Length: 10, Frequencies: 0
         * Length: 11, Frequencies: 1
         * The most common word length in the file is: 3
         */
    }
    //finds the index of the most frequent word
    public int indexOfMax(int [] values){
        int largest = 0;
        for(int i = 0; i < values.length ; i++){
            if(values[largest] < values[i]){
                largest = i;
            }
        }
        return largest;
    }
}
