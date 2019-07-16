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
    
    public int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        
        while(true){
            String gene = findGene(dna, startIndex);
            
            if(gene.isEmpty()){
                break;
            }
            
            count++;
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return count;
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource sr = new StorageResource(); // creating storage resource object
        int startIndex = 0;
        
        while(true){
            String gene = findGene(dna, startIndex);
            
            if(gene.isEmpty()){
                break;
            }
            // storing in each individual gene
            sr.add(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
       }        
        return sr;  
    }
    
    public double cgRatio(String dna){
        double cgCount = 0;
        dna = dna.toUpperCase(); // make sure if the dna input is either lower or mixed, the program could calculate the cg ratio regardless
        for(int i = 0; i < dna.length(); i++){ // for-loop is easier 
            // if dna character is either a c or g char, cgCount increases by 1
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G'){
                cgCount++;
            }
        }
        return cgCount/dna.length();
    }
    // version with while loop since course does not teach charAt or for loops
    public double cgRatio2(String dna){
        double cgCount = 0;
        int startIndexC = 0;
        int startIndexG = 0;
        int cIndex = 0;
        int gIndex = 0;
        dna = dna.toUpperCase();
        
        while(cIndex != -1 && gIndex != -1){
            cIndex = dna.indexOf("C", startIndexC);
            gIndex = dna.indexOf("G",startIndexG);
            
            if(cIndex != -1){
                cgCount++;
            }
            
            if(gIndex != -1){
                cgCount++;
            }
      
            startIndexC = dna.indexOf("C", startIndexC) + 1;
            startIndexG = dna.indexOf("G", startIndexG) + 1;
        }
        
        return cgCount/dna.length();
        
    }

    public void testFindStopCodon(){
        //            0123456789
        String dna = "ATGGGGGGGGGTTTTTTTTAA"; // 18 - 0 = 18 % 3 = 0
        System.out.println(findStopCodon(dna, 0, "TAA")); // should print out 18     
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
    
    public void testCountGenes(){
        //            0123456789
        //            v  v   v     v
        String dna = "ATGTAAGATGCCCTAGT"; 
        System.out.println(countGenes(dna)); // should print out 2 since it has the genes, ATGTAA and ATGCCCTAG
        
        
    }
    
    public void testGetAllGenes(){
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/dna/GRch38dnapart.fa");
        StorageResource sr = new StorageResource();
        for(String s: ur.words()){
            sr = getAllGenes(s);
        }
        
        for(String gene : sr.data()){
            System.out.println(gene);
        }
    }
    
    public void testCgRatio(){
        String dna = "ATGCCATAG";      
        System.out.println(cgRatio(dna)); // should print out 4/9 or 0.44444
        System.out.println(cgRatio2(dna)); // should also do that ^^
    }
}
