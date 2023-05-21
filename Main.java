import java.io.*;
import java.util.*;
import java.util.Hashtable;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileReader;




public class Main {
public static void main(String[] args) {  
    List<Hashtable<String, String>> monsterList = convertCSV("data.txt");
    searchForMonster(monsterList);
   
}

//Converts CSV file to List of Hashtables which use Key Value pairs
public static List<Hashtable<String, String>> convertCSV(String args){
    List<Hashtable<String, String>> lines = new ArrayList<Hashtable<String,String>>();
    try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
    
        // CSV file delimiter
        String DELIMITER = ",";
    
        // read the file line by line
        String line;
        while ((line = br.readLine()) != null) {
            String[] columns = line.split(DELIMITER);

            //Create Key Value Pairs
            Hashtable<String, String> monsterDictionary = new Hashtable<>(); {
                monsterDictionary.put("Name", columns[0]);
                monsterDictionary.put("URL", columns[1]);
                monsterDictionary.put("CR", columns[2]);
                monsterDictionary.put("Type", columns[3]);
                monsterDictionary.put("Size", columns[4]);
                monsterDictionary.put("AC", columns[5]);
                monsterDictionary.put("HP", columns[6]);
                monsterDictionary.put("Speed", columns[7]);
                monsterDictionary.put("Align", columns[8]);
                monsterDictionary.put("Legendary?", columns[9]);
                monsterDictionary.put("Source", columns[10]);
            };
            lines.add(monsterDictionary);
        }
        return(lines);
        
    
    } catch (IOException ex) {
        ex.printStackTrace();
    }

    return(lines);

}

public static void searchForMonster(List<Hashtable<String, String>> monsterList){

    //Creat Key Value Pairs for user input to List of Hashtables
    Hashtable<String, String> categories = new Hashtable<String, String>();
    categories.put("1", "Name");
    categories.put("2", "CR");
    categories.put("3", "Type");
    categories.put("4", "Size");
    categories.put("5", "AC");
    categories.put("6", "HP");
    categories.put("7", "Align");

    try(Scanner myObj = new Scanner(System.in)){  // Create a Scanner object
        System.out.printf("Categories%n1. Name%n2. Challenge Rating%n3. Type%n4. Size%n5. Armor Class%n6. Health%n7. Alignment%n8. Exit%nWhat category would you like to search by?: %n");
        String categoryChoice = myObj.nextLine();  // Read user input
        
        //Check User input for validity
        if(categoryChoice.equals("8")){
            System.out.println("Thank You for Your Time!");
        }
        else if(!categoryChoice.matches("1|2|3|4|5|6|7")){
            System.out.println("Please Try Again!");
            searchForMonster(monsterList);
        }
        else{
            System.out.printf("Search in " + categories.get(categoryChoice) + ": ");
            String searchCriteria = myObj.nextLine();
            
            //Search for user input in List
            for(Hashtable<String,String> monster: monsterList){
                if((monster.get(categories.get(categoryChoice))).equals(searchCriteria)){
                    //Display Information
                    System.out.println(monster);
                }
            }
            //Recurr for continued prompting
            searchForMonster(monsterList);
        }
        
    }
}
}