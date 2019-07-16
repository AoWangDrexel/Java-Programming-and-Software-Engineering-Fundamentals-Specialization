import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Cold {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestTemp = null;
        for(CSVRecord currTemp : parser){
             coldestTemp = findColdestFromTwo(coldestTemp, currTemp);
        }
        return coldestTemp;
    }
    
    public CSVRecord findColdestFromTwo(CSVRecord coldestTemp, CSVRecord currTemp){
        if(coldestTemp == null){
            coldestTemp = currTemp;
        }else{
            double coldest = Double.parseDouble(coldestTemp.get("TemperatureF"));
            double current =  Double.parseDouble(currTemp.get("TemperatureF"));
            if(current != -9999 && current < coldest){
                coldestTemp = currTemp;
            }
        }
        
        return coldestTemp;
    }
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestTemp = null;
        String coldestFileName = null;
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currTemp = coldestHourInFile(fr.getCSVParser());
            if(coldestTemp == null){
                coldestTemp = currTemp;
            }else{
                double coldest = Double.parseDouble(coldestTemp.get("TemperatureF"));
                double current =  Double.parseDouble(currTemp.get("TemperatureF"));
                if(current != -9999 && current < coldest){
                    coldestTemp = currTemp;
                    coldestFileName = f.getName();
                }
            }     
        }

        return coldestFileName;
    }
    
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumid = null;
        double currHumid = 0;
        double lowHumid = 0;
        for(CSVRecord record : parser){
            if(lowestHumid == null){
                lowestHumid = record;
            }else{
                if(!record.get("Humidity").equals("N/A")){
                    currHumid = Double.parseDouble(record.get("Humidity"));
                    lowHumid = Double.parseDouble(lowestHumid.get("Humidity"));
                }
                if(currHumid < lowHumid){
                    lowestHumid = record;
                }
            }
        }
        return lowestHumid;
   }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumid = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currHumid = lowestHumidityInFile(fr.getCSVParser());
            
            if(lowestHumid == null){
                lowestHumid = currHumid;
            }else{
                double currentHumid = Double.parseDouble(currHumid.get("Humidity"));
                double lowHumid = Double.parseDouble(lowestHumid.get("Humidity"));
                if(currHumid.get("Humidity") != "N/A" && currentHumid < lowHumid){
                    lowestHumid = currHumid;
                }
            }
        }
        
        return lowestHumid;
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        
        double totalTemp = 0;
        int numOfTemps = 0;
        
        for(CSVRecord record : parser){
            totalTemp += Double.parseDouble(record.get("TemperatureF"));
            numOfTemps++;   
        }
        return totalTemp/numOfTemps;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        
        double totalTemp = 0;
        int numOfTemps = 0;
        
        for(CSVRecord record : parser){
            if(Double.parseDouble(record.get("Humidity")) >= value){
                totalTemp += Double.parseDouble(record.get("TemperatureF"));
                numOfTemps++; 
            }
              
        }
        return totalTemp/numOfTemps;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
    }
    
    public void testFileWithColdestTemperature(){
        System.out.println("Coldest day was in file: " + fileWithColdestTemperature());
        FileResource fr = new FileResource();
        CSVRecord cold = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was: " + cold.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were: ");
        for(CSVRecord record : fr.getCSVParser()){
            System.out.print(record.get("DateUTC") + " " + record.get("TemperatureF") + "\n");
            
        }
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was: " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
        
        // Lowest Humidity was: 24 at 2014-01-20 19:51:00 for the output on weather-2014-01-20.csv
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was: " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
        // when comparing Jan 19, 2014 and Jan 20, 2014 -- > output should be 
        // Lowest Humidity was: 24 at 2014-01-20 19:51:00
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is: " + avg); // Average temperature in file is: 44.93333333333334
        // Jan 20, 2014
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser,80);
        if(avg == 0){
            System.out.println("No temperatures with that humidity");
        }else{
            System.out.println("Average temperature in file is: " + avg); // Mar 20th, 2014 should result in Average temperature in file is: 41.78666666666667
            // on Jan 20th 2014, no temp w/ that humidity
        }
        
    }
        
        
}
