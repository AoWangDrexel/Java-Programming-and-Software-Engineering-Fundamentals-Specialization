import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class BabyBirths {
    public void printNames(){
        FileResource fr = new FileResource();
        for( CSVRecord record : fr.getCSVParser(false)){
            int numBorn =Integer.parseInt(record.get(2));
            if(numBorn <= 100){
                System.out.println("Name " + record.get(0) + " Gender " + record.get(1) + " Num Born " + record.get(2));
            }
        }
    }
    
    public void totalBirths(FileResource fr){
        int totalName = 0;
        int maleName = 0;
        int femaleName = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals("M")){
                maleName++;
            }
            if(rec.get(1).equals("F")){
                femaleName++;
            }
            totalName++;
        }
        System.out.println("Total names: " + totalName);
        System.out.println("Total names of M: " + maleName);
        System.out.println("Total names of F: " + femaleName);
    }
    
    public int getRank(int year, String name, String gender){
        int rank = 0;
        String file = "yob"+year+".csv";
        FileResource fr = new FileResource(file);
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                rank++;
            }
            if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                return rank;
            }
        }

        return -1;
    }
    
    public void testGetRank(){
        //System.out.println("Mason's rank is: " + getRank(2012, "Mason", "M"));
        // should print out 2
        //System.out.println("Ava's rank is: " + getRank(2012, "Ava", "F"));
        // 5
        //System.out.println("Emily's rank is: " + getRank(1960, "Emily", "F"));
        System.out.println("Frank's rank is: " + getRank(1971, "Frank", "M"));
        
    }
    
    public String getName(int year, int rank, String gender){
        String file = "yob"+year+".csv";
        FileResource fr = new FileResource(file);
        int count = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                count++;
            }
            if(rec.get(1).equals(gender) && rank == count){
                return rec.get(0);
            }
            
        }
        return "NO NAME";
    }
    
    public void testGetName(){
        
        //System.out.println("2012 Rank 5 and Female: " + getName(2012,5,"F")); // should print out Ava
        //System.out.println("1980 Rank 350 and Female: " + getName(1980,350,"F"));
        System.out.println("1982 Rank 450 and Male: " + getName(1982,450,"M"));
        
        
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        System.out.print(name + " born in " + year + " would be ");
        String newName = getName(newYear, rank, gender);
        System.out.println(newName + " if she was born in " + newYear);
    }
    
    public void testWhatIsNameInYear(){
        //whatIsNameInYear("Isabella", 2012, 2014,"F");
        // should print out
        // Isabella born in 2012 would be Sophia if she was born in 2014.
        //whatIsNameInYear("Susan", 1972, 2014,"F");
        whatIsNameInYear("Owen", 1974, 2014,"M");
        
        
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestRank = Integer.MAX_VALUE;
        int year = 0;
        int maxYear = 0;
        
        for(File f : dr.selectedFiles()){
            int yearString1 = f.getName().indexOf("1");
            int yearString2 = f.getName().indexOf("2");
            
            if(yearString1 != -1 && yearString2 == -1){
                year = Integer.parseInt(f.getName().substring(yearString1, yearString1 + 4));
            }else if(yearString1 == -1 && yearString2 != -1){
                year = Integer.parseInt(f.getName().substring(yearString2, yearString2 + 4));
            }else if(yearString1 != -1 && yearString2 != -1 && yearString1 < yearString2){
                year = Integer.parseInt(f.getName().substring(yearString1, yearString1 + 4));
            }else{
                year = Integer.parseInt(f.getName().substring(yearString2, yearString2 + 4));
            }
            

            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            for(CSVRecord rec : parser){
                if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                    int rank  = getRank(year, name, gender);
                    if(rank < highestRank){
                        highestRank = rank;
                        maxYear = year;
                    }
                } 
            }
        }
        return maxYear;
    }

    public void testYearOfHighestRank(){
        //System.out.println("The maximum rank was year " + yearOfHighestRank("Mason","M") + " for Mason");
        // should print out 2012 because his rank was 2
        //System.out.println(yearOfHighestRank("Genevieve","F"));
        System.out.println(yearOfHighestRank("Mich","M"));
        
    }
    
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int rankSum = 0;
        int year = 0;
        int numOfYears = 0;
        
        for(File f : dr.selectedFiles()){
            int yearString1 = f.getName().indexOf("1");
            int yearString2 = f.getName().indexOf("2");
            
            if(yearString1 != -1 && yearString2 == -1){
                year = Integer.parseInt(f.getName().substring(yearString1, yearString1 + 4));
            }else if(yearString1 == -1 && yearString2 != -1){
                year = Integer.parseInt(f.getName().substring(yearString2, yearString2 + 4));
            }else if(yearString1 != -1 && yearString2 != -1 && yearString1 < yearString2){
                year = Integer.parseInt(f.getName().substring(yearString1, yearString1 + 4));
            }else{
                year = Integer.parseInt(f.getName().substring(yearString2, yearString2 + 4));
            }

            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            for(CSVRecord rec : parser){
                if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                    int rank  = getRank(year, name, gender);
                    rankSum += rank;
                } 
            }
            
            numOfYears++;
        }
        
        return (double)rankSum/numOfYears;
    }
    
    public void testGetAverageRank(){
        //System.out.println("The average of the ranks for Jacob: " + getAverageRank("Jacob", "M"));
        // should print out
        // The average of the ranks for Jacob: 2.6666666666666665
        
        //System.out.println("The average of the ranks for Robert: " + getAverageRank("Robert", "M"));
        System.out.println("The average of the ranks for Susan: " + getAverageRank("Susan", "F"));
        
    }
        
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        String file = "yob" + year + ".csv";
        FileResource fr = new FileResource(file);
        int sumBirths = 0;
        int theRank = getRank(year, name, gender);
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(!rec.get(0).equals(name) && rec.get(1).equals(gender)){
                int currRank = getRank(year, rec.get(0), gender);
                if(currRank < theRank){
                    sumBirths += Integer.parseInt(rec.get(2));
                }
            }
            
        }
        return sumBirths;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        
        //System.out.println("Ethan has " + getTotalBirthsRankedHigher(2012, "Ethan", "M") + " males who have a greater rank");
        // Ethan has 15 males who have a greater rank
        
        //System.out.println("Emily has " + getTotalBirthsRankedHigher(1990, "Emily", "F") + " females who have a greater rank");
        System.out.println("Drew has " + getTotalBirthsRankedHigher(1990, "Drew", "M") + " males who have a greater rank");
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
}
