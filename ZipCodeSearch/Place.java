/**********************************
 * Assignment 3
 * Name: Siyan Wang
 * Email: swang2@haverford.edu
 * Course: CS 206 
 * Submitted: 9/24/2015
 *
 * The source code for Place class to searve to Search.java.
 *
 **************************************/


public class Place implements Comparable <Place>{
    //Attributes
    private String zip;
    private String town;
    private String state;

    /**
     * the constructor for a place
     * @param zipcode
     * @param town
     * @param state
     */
	public Place(String zipCode, String townName, String stateName){
	zip=zipCode;
	town=townName;
	state=stateName;	     
    }
    //ancestor method: return zip code
    public String getZip(){
	return zip;
    }
    //ancestor method: return town name
    public String getTown(){
	return town;
    }
    //ancestor method: return state name
    public String getState(){
        return state;
    }
    public String toString(){
	return "The zip code "+ zip + " belongs to "+town+", "+state;
    }
    public Boolean equals(String seek){
	return zip.equals(seek);
    }
    public int compareTo(Place place){
	Integer placeInt=Integer.parseInt(place.getZip());
	Integer existingInt=Integer.parseInt(getZip());
	return existingInt.compareTo(placeInt);
    }
}

