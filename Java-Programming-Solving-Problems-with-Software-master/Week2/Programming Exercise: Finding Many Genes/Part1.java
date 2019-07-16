import edu.duke.*;

public class Part1 {
    public String findGene(String dna, int where){
        // find ATG
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return "";
        }
        // find the index of the three stop codons
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        // find the smallest index besides -1 to find shortest gene
        int minIndex = 0;
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }else{
            minIndex = taaIndex;
        }
        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        
        // if all stop codons are -1, return empty string
        if(minIndex == -1){
            return "";
        }

        return dna.substring(startIndex, minIndex + 3);   
    }
    
    // three stop codons, TAA, TGA, TAG
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        // finds the start of the stop codon
        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        
        // loops through the dna until a gene that's difference from the start codon is a multiple of three
        while(currIndex != -1){
            if((currIndex - startIndex) % 3 == 0){ 
                return currIndex;
            }
            // continues to search for a stop codon that has a different index
            currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
        }
        // if no stop codon is found
        return -1;
    }
    
    public void testFindStopCodon(){
        //            0123456789
        String dna = "ATGGGGGGGGGTTTTTTTTAA"; // 18 - 0 = 18 % 3 = 0
        System.out.println(findStopCodon(dna, 0, "TAA")); // should print out 18     
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        
        while(true){
            String gene = findGene(dna, startIndex);
            
            if(gene.isEmpty()){
                break;
            }
            
            System.out.println(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        
    }
    
    public void testFindGene(){
        //            v           v 12 - 0 = 12 % 3 = 0
        //            0123456789
        String dna = "ATGCCCGGGAAATAACCC";
        if(!findGene(dna, 0).equals("ATGCCCGGGAAATAA")){
            System.out.println("Error");
        }
        
        System.out.println("tests finished");
    }
    
    public void testPrintAllGenes(){
        //URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/dna/GRch38dnapart.fa");
        //for(String s: ur.words()){
        //    printAllGenes(s);
        //}
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        printAllGenes(dna);
        System.out.println();
        
        dna = "ATGATCATAAGAAGATAATAGAGGGCCATGTAA";
        printAllGenes(dna);
        
    }
}

