import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GradeBookDriver {

	public static ArrayList<ArrayList<String>> mainArray = new ArrayList<>();
	public static ArrayList<String> layer1 = new ArrayList<>();
	public static ArrayList<String> layer2 = new ArrayList<>();
	public static ArrayList<String> layer3 = new ArrayList<>();
	
	public static void main(String[] args) {
		setUp("226-fall-1996.csv");
		setUp("326-fall-1996.csv");
		setUp("326-fall-1997.csv");
        System.out.println("Welcome to Grade Book Manager");
        Scanner kb = new Scanner(System.in);
        boolean flag = true;
        do {
	        displayMenu();
	        String choice = kb.nextLine();
	        switch (choice.substring(0,1).toLowerCase()){
	            case "a":
	                addData();
	                break;
	            case "s":
	                saveData();
	                break;
	            case "g":
	                studentsPerGrade();
	                break;
	            case "e":
	                System.exit(0);
	                flag = false;
	                break;
	            default:
	            	System.out.println("Invalid option.\n");
	        }
        } while(flag);
    }
	
	
	
    public static void addData(){
    	boolean more = true;
    	int iterate = 0;
    	Scanner kb = new Scanner(System.in);
    	do {
    		if(iterate != 0) 
    			kb.nextLine();
    		iterate++;
	    	System.out.print("Enter a file name to add to repository: ");
	    	String s = kb.nextLine();
	    	if(s.equals("226-fall-1996.csv")) {
	    		mainArray.add(layer1);
	    		System.out.print("Add another file? (y or n): ");
	    		String a = kb.next();
	    		if (a.equals("y") || a.equals("yes")) {
	    			more = true;
	    		}
	    		else if (a.equals("n") || a.equals("no"))
	    			more = false;
	    	}
	    	else if(s.equals("326-fall-1996.csv")) {
	    		mainArray.add(layer2);
	    		System.out.print("Add another file? (y or n): ");
	    		String a = kb.next();
	    		if (a.equals("y") || a.equals("yes")) {
	    			more = true;
	    		}
	    		else if (a.equals("n") || a.equals("no"))
	    			more = false;
	    	}
	    	else if(s.equals("326-fall-1997.csv")) {
	    		mainArray.add(layer3);
	    		System.out.print("Add another file? (y or n): ");
	    		String a = kb.next();
	    		if (a.equals("y") || a.equals("yes")) {
	    			more = true;
	    		}
	    		else if (a.equals("n") || a.equals("no"))
	    			more = false;
	    	}
	    	else {
	    		System.out.println("Not an option!");
	    		System.out.print("Add another file? (y or n): ");
	    		String a = kb.next();
	    		if (a.equals("y") || a.equals("yes")) {
	    			more = true;
	    		}
	    		else if (a.equals("n") || a.equals("no"))
	    			more = false;
	    	}
    	} while(more);
    }
    
                    // Doesnt write to new csv file!
    public static void saveData(){
    	boolean more = false;
    	//FileWriter fileWriter = null;
    	System.out.print("Enter student ID: ");
    	Scanner kb = new Scanner(System.in);
    	String studentID = kb.nextLine();
    	for (ArrayList<String> categories : mainArray) {
    		for (String element : categories) {
    			String[] s = element.split(",");
    			for (String a : s) {
    				if (a.equals(studentID)) {
    					try {
    						PrintWriter pw = new PrintWriter(new File("student.csv"));
    					    StringBuilder sb = new StringBuilder();
    						//fileWriter = new FileWriter((System.getProperty("user.home")+"/student.csv"), true);
    						sb.append(categories.get(0) + "\n" + element + "\n");
    						pw.write(sb.toString());
    					}
    					catch(Exception e) {
    						System.out.println("Error in CsvFileWriter !!!");
    						e.printStackTrace();
    					}
    					more = true;
    					System.out.println("\nCSV file was created successfully !!!");
    				}
    			}
    		}
    	}
    	if (!more) {
    		System.out.println("Not an option!");
    	}
    }
    
    
    public static void studentsPerGrade(){

    }
    
    
    public void exit(){
        System.exit(0);
    }
    
    
	public static void displayMenu(){

        System.out.println("\nPlease enter the following");
        System.out.println("A) Add a new file to repository");
        System.out.println("S) Save data for a user");
        System.out.println("G) Get grades breakdown");
        System.out.println("E) exit");
    }


	public static void setUp(String s){
        String csvFile = s;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile)); 
            while ((line = br.readLine()) != null) {

                String[] categories = line.split(cvsSplitBy);
                for(int i =  0; i < categories.length; i++){
                	if(s.equals("226-fall-1996.csv")) {
                		layer1.add(categories[i]);
                	}
                	if(s.equals("326-fall-1996.csv")) {
                		layer2.add(categories[i]);
                	}
                	if(s.equals("326-fall-1997.csv")) {
                		layer3.add(categories[i]);
                	}
              
               }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }

}