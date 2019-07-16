import edu.duke.*;
import org.apache.commons.csv.*;

public class CSV {
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // vvv all of them use the exports_small.csv
        // should print out --> Germany: motor vehicles, machinery, chemicals: $1,547,000,000,000
        //System.out.println(countryInfo(parser,"Germany")); 
        
        //listExportersTwoProducts(parser,"gold","diamonds"); // should print out Namibia and South Africa
        // do parser = fr.getCSVParser() to reset parser to do another method
        
        //System.out.println(numberOfExporters(parser, "gold")); // should record 3
        
        bigExporters(parser,"$999,999,999");
        
        /* should print -->
         * Germany $1,547,000,000,000
         * Macedonia $3,421,000,000
         * Malawi $1,332,000,000
         * Malaysia $231,300,000,000
         * Namibia $4,597,000,000
         * Peru $36,430,000,000
         * South Africa $97,900,000,000
         * United States $1,610,000,000,000
         */
       
    }
    
    public String countryInfo(CSVParser parser, String country){
        String exports = "";
        String value = "";
        // loops through all the country's names, if matches with the parameter, get the exports and the value
        for(CSVRecord record : parser){
            if(record.get("Country").equals(country)){
                exports = record.get("Exports");
                value = record.get("Value (dollars)");
                return country + ": " + exports + ": " + value;
            }
        }
        // if country in parameter is not found, return not found
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        // loops through to print countries that have the two exports
        for (CSVRecord record : parser){       
            if(record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)){
                System.out.println(record.get("Country"));
            }   
        }

    }
    // count the number of countries that export the specific export item in the parameter
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser){
            if(record.get("Exports").contains(exportItem)){
              count++;   
            }
        }
        return count;
    }
    
    // prints out all the countries, whose amount in export result in higher "or longer" than the string amount
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser){
            if(record.get("Value (dollars)").length() > amount.length()){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        } 
    }  
}
