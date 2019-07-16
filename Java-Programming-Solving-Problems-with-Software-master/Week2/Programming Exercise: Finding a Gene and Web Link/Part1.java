public class Part1 {
    public String findSimpleGene(String dna){
        String result = "";
        int startIndex = dna.indexOf("ATG");
        
        // no ATG --> return empty
        if (startIndex == -1){
            return result;
        }
        
        int endIndex = dna.indexOf("TAA", startIndex + 3);
        
        // no TAA --> return empty
        if (endIndex == -1){
            return result;
        }
        
        // the difference of the start of ATG and start of TAA must be divisible by 3
        if((endIndex - startIndex) % 3 == 0){
            result = dna.substring(startIndex, endIndex + 3);
        }
        
        return  result;
           
    }
    
    public void testFindSimpleGene(){
        //             v    v
        //            0123456789 6-1 = 5 not % 3 = 0
        String dna = "AATGCGTAATATGGT"; // will not print
        System.out.println("The DNA strand is: " + dna);
        System.out.println("Gene is: " + findSimpleGene(dna));
        //      v        v
        //     0123456789     10-1 = 9 % 3 = 0
        dna = "AATGCTAGGGTAATATGGT"; // will print
        System.out.println("The DNA strand is: " + dna);
        System.out.println("Gene is: " + findSimpleGene(dna));
        //          v              v
        //     0123456789 20-5 = 15 % 3 = 0
        dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT"; // will print
        System.out.println("The DNA strand is: " + dna);
        System.out.println("Gene is: " + findSimpleGene(dna));
        //       v
        //     0123456789
        dna = "CGATGGTTTG"; // does not have an ending codon --> won't print
        System.out.println("The DNA strand is: " + dna);
        System.out.println("Gene is: " + findSimpleGene(dna));
        //     v              v
        //     0123456789 15-0 = 15 % 3 will print
        dna = "ATGCTTCGGCTGCTCTAA";
        System.out.println("The DNA strand is: " + dna);
        System.out.println("Gene is: " + findSimpleGene(dna));
        
        // does not have star codon ATG
        dna = "TTATAA";
        System.out.println("The DNA strand is: " + dna);
        System.out.println("Gene is: " + findSimpleGene(dna));
        
    }
}

