/**********************************
 * Assignment 3
 * Name: Siyan Wang
 * Email: swang2@haverford.edu
 * Course: CS 206 
 * Submitted: 9/29/2015
 *
 * The source code for zip code and town name match.
 *
 **************************************/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class Search{
    //Input the data
    public static Place[] inputData(File file) throws FileNotFoundException{
	//Access to the file
	Scanner lines= new Scanner(file);
	//get the basic info of the file
	String[] info= lines.nextLine().split(",");
	int length = Integer.parseInt(info[0].split(" ")[1]);
	//Create an array of Place objects
	Place[] data= new Place[length];
	int counter = 0;
	//Put each place into the array
	while(lines.hasNext()){
	    String[] line=lines.nextLine().split("\t");
	    String  townState= line[3];
	    String[] townState_split=townState.split(", ");
	    data[counter]=new Place(line[0], townState_split[0], townState_split[1]);
	    counter++;
	}
	lines.close();
	return data;
    }// end of inputData
    
    //Check whether a value is a integer.
    public static boolean isInteger(String value) {
	try {
	    Integer.parseInt(value);
	    return true;
	} catch (NumberFormatException e) {
	    return false;
	}
    }//end of isInteger
    
    //Use linear search to get the location of a zip
    public static int searchData(String input, Place[] dataPiece){
	//Prepare for the search
	int length= dataPiece.length;
	//The input must be a 5-digit number
	if(input.length() !=5)
	    throw new NumberFormatException();
	//Check whether the input is an integer;
	if (isInteger(input)== false)
	    throw new NumberFormatException();
	//Test if the input is a US zip code(with linear search)
	long startTime = System.nanoTime();
	for (int i=0; i<length; i++) {
	    if (dataPiece[i].equals(input)){
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);//calculate the time;
		System.out.println("The time linear search needs is "+ duration);
		return i;
	    }
	}
	long endTime = System.nanoTime();
	long duration = (endTime - startTime);//calculate the time;
	System.out.println("The time linear search needs is "+ duration);
	return -1;
    }//end of searchData

    //use the binary search the index
    public static int binarySearch(String input2, Place[] data2){
	Arrays.sort(data2);
	//The input must be a 5-digit number;
	if(input2.length() !=5)
	    throw new NumberFormatException();
	//Check whether the input is an integer;
	if (isInteger(input2)== false)
	    throw new NumberFormatException();
	//Use binary search to find where the zip code is;
	Place dataPlace =new Place(input2, null, null);
    long startTime2 = System.nanoTime();
    int index=Arrays.binarySearch(data2, dataPlace);
    long endTime2 = System.nanoTime();
    long duration2 = (endTime2 - startTime2);//calculate the time;
    System.out.println("The time binary search needs is  "+ duration2);
    return index;
    }//end of binarySearch
	
    public static void main(String[] args){
	int response=0;
	do{
	    try{
		//import the data
		Place[] data = inputData(new File("data/zips.txt"));
		//Ask for input
		String input = JOptionPane.showInputDialog(null, "Please enter the zip code you want to search for: ","Zip Code Search", JOptionPane.QUESTION_MESSAGE);
		if (input !=null){//and click ok certainly..
                    //Eliminate the spaces before and after the input first.
		    input=input.trim();
		    System.out.println("You asked me to search for zip code "+ input);
		    //search the location
		    int linearIndex=searchData(input, data);
		    int binaryIndex=binarySearch(input, data);
		    if (binaryIndex>=0){//click OK of course...a valid zip code...
			Place locationInfo= data[binaryIndex];
			String outputTown= locationInfo.getTown();
			String outputState = locationInfo.getState();
			JOptionPane.showMessageDialog(null, "The Zip code "+ input+ " belongs to "+outputTown+", "+outputState);
			System.out.println("The Zip code "+ input+ " belongs to "+outputTown+", "+outputState);
		    //Continue or not
			String[] options={"Yes", "No"};
			response=JOptionPane.showOptionDialog(null, "Do you want to search again?","Zip Code Search", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response ==0){//continue searching
			    System.out.println("Do you want to search again? Yes\n");
			}else{//exit
			    System.out.println("Do you want to search again? No");
			}
		    }else{//the zip code does not exist
			System.out.println("The zip code "+input+" does not exist.");
			//Ask if continue or not
			String[] options={"Yes", "No"};
			response=JOptionPane.showOptionDialog(null, "Do you want to search again?", "Zip Code Search", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response==0){//continue searching
			    System.out.println("Do you want to search again? Yes\n");
			}else{//exit
			    System.out.println("Do you want to search again? No");
			}	
		    }
		}else{//click cancel
		    	System.out.println("You have cancelled the search.");
			response = 1;
		}
		}catch (FileNotFoundException e){
		    System.out.println("The path is not valid or cannot be found!");
		    System.exit(1);
		}catch(NumberFormatException e){
		    //Give info abou the incorrect input.
		    JOptionPane.showMessageDialog(null, "The input is not valid. Please enter a new one.", "Input error", JOptionPane.ERROR_MESSAGE);
		    System.out.println("The input is not valid. Please enter a new one.");
		    //Ask if continue or not
		    String[] options={"Yes", "No"};
		    response=JOptionPane.showOptionDialog(null, "Do you want to search again?", "Zip Code Search", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		    if (response==0){//continue searching
			System.out.println("Do you want to search again? Yes\n");
		    }else{//exit
			System.out.println("Do you want to search again? No");
		    }
		}  
	    }while(response==0);
	    System.out.println("Goodbye! Thanks:) \n");
	}//end of the main
    }//end of the class
