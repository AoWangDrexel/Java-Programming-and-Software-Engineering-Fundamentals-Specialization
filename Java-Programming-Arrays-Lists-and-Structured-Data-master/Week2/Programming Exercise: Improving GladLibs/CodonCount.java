import java.util.Map;
import java.util.HashMap;
import edu.duke.*;

public class CodonCount {
    private Map<String, Integer> codons;

    public CodonCount(){
        codons = new HashMap<>();  

    }

    /*
     * The method will build a new map of codons mapped to their counts 
     * from the string dna with the reading frame with the position 
     * start (a value of 0, 1, or 2).
     */
    public void buildCodonMap(int start, String dna){
        // clears the map, so when used again it won't overlap with the
        // other start values 
        codons.clear();
        // loops through the dna strand starting from the parameter, start
        // gets substrings of the dna in bunches of three
        for(int i = start; i < dna.length() - 2; i += 3){
            String codon = dna.substring(i, i + 3);
            // if codon is not in the map, add it in and give it a value of 1
            if(!codons.containsKey(codon)){
                codons.put(codon, 1);
            }else{
                // if already in key, add value + 1
                codons.put(codon, codons.get(codon) + 1);
            }     
        }
    }

    public void testBuildCodonMap(){
        String dna = "CGTTCAAGTTCAA";
        buildCodonMap(0,dna);
        /*
         * when start is 0
         * CGT: 1
         * TCA: 2
         * AGT: 1 
         * 
         * when start is 1
         * CAA: 2
         * GTT: 2
         * 
         * when start is 2
         * TTC: 2
         * AAG: 1
         */
        for(Map.Entry<String,Integer> entry : codons.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());          
        }        
    }

    /*
     * This method returns a String, the codon in a reading frame 
     * that has the largest count. If there are several such codons,
     * return any one of them. This method assumes the HashMap of codons
     * to counts has already been built.
     */
    public String getMostCommonCodon(){
        int max = Integer.MIN_VALUE;
        String commonCodon = "";
        for(String key : codons.keySet()){
            if(codons.get(key) > max){
                max = codons.get(key);
                commonCodon = key;
            }
        }
        return commonCodon;
    }

    public void testGetMostCommonCodon(){
        testBuildCodonMap();
        System.out.println(getMostCommonCodon());
        /*
         * when start is 2
         * TTC: 2
         * AAG: 1
         * 
         * returns TTC
         * 
         * if start  = 0
         * returns TCA
         */
    }

    /*
     * This method prints all the codons in the HashMap along with
     * their counts if their count is between start and end, inclusive.
     */
    public void printCodonCounts(int start, int end){
        for(String codon : codons.keySet()){
            int value = codons.get(codon);
            if(value >= start && value <= end){
                System.out.println(codon + ": " + value);
            }
        }
    }
    // prompts user for file (use the smalldna.txt file)
    public void tester(){
        FileResource fr = new FileResource();
        // creates the data as a string, uppercase, and removes excess white space at the end
        String dna = fr.asString().toUpperCase().trim();
        for(int i = 0; i < 3; i++){
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with " + i + " results in "
                + codons.size() + " unique codons");
            System.out.println("and most common codon is " + getMostCommonCodon() + " with count " 
                + codons.get(getMostCommonCodon()));   
            System.out.println("Counts of codons between 1 and 5 inclusive are: ");
            printCodonCounts(1,5);
            System.out.println();

        }

    }
}

