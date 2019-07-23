import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import edu.duke.*;

public class CharactersInPlay {
    private List<String> name;
    private List<Integer> count;
    private Map<String, Integer> countRoles;

    public CharactersInPlay(){
        name = new ArrayList<>();
        count = new ArrayList<>();
        // version with HashMap below
        countRoles = new HashMap<>();
    }

    /* This method should update the two ArrayLists, adding the character’s name
     * if it is not already there, and counting this line as one speaking part for this person.
     */
    public void update1(String person){

        if(!name.contains(person)){
            name.add(person);
            count.add(1);
        }else{
            int idx = name.indexOf(person);
            count.set(idx,count.get(idx) + 1);
        }
    }
    // version done with HashMap
    public void update2(String person){

        if(!countRoles.containsKey(person)){
            countRoles.put(person, 1);
        }else{
            countRoles.put(person, countRoles.get(person) + 1);
        }
    }

    /*
     * Method opens a file, and reads the file line-by-line. For each line, if there is 
     * a period on the line, extract the possible name of the speaking part, and call update
     * to count it as an occurrence for this person. Make sure you clear the appropriate
     * instance variables before each new file.
     */
    public void findAllCharacters(){
        FileResource fr = new FileResource();

        for(String line: fr.lines()){
            int idx = line.indexOf(".");
            if(idx != -1){
                String name = line.substring(0,idx);
                update1(name);
                //update2(name);
            }

        }
    }

    public void tester(){
        findAllCharacters();

        // use the small Macbeth txt file
        /*
        for(int i = 0; i < name.size(); i++){
        int line = count.get(i);
        if(line > 1){
        System.out.println(name.get(i) + " has " + line + " speaking parts");
        }
        }
         */

        /*
        System.out.println(countRoles);
        for(Map.Entry<String,Integer> entry: countRoles.entrySet()){
        if(entry.getValue() > 1){
        System.out.println(entry.getKey() +" has " + entry.getValue()+ " speaking parts");
        }
        }
         */

        // in either ways, it should print out
        // LADY MACBETH has 2 speaking parts
        // MACBETH has 3 speaking parts

        charactersWithNumParts(10,15);

        // if num1 = 2, num2 = 3
        //MACBETH has 3 speaking parts
        // LADY MACBETH has 2 speaking parts
    }

    /*
     * This method should print out the names of all those characters
     * that have exactly number speaking parts, where number is greater
     * than or equal to num1 and less than or equal to num2. 
     */
    public void charactersWithNumParts(int num1, int num2){
        System.out.println("Characters with between " + num1 + "-" +num2 + " lines");
        for(int i = 0; i < name.size(); i++){
            int line = count.get(i);
            if(line >= num1 && line <= num2){
                System.out.println(name.get(i) + " has " + line + " speaking parts");
            }
        }
    }

}
