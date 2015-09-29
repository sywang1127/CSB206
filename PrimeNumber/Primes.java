/**********************************
 * Assignment 2
 * Name: Siyan Wang
 * Email: swang2@haverford.edu
 * Course: CS 206 
 * Submitted: 9/15/2015
 *
 * The source code for printing all prime numbers between 1 and 100.
 *
 **************************************/


 /*****************************************
 * Creat a class  called Prime to check whether a number is a prime number and print all prime numbers between 1 and 100
 * author Siyan Wang(swang2@haverford.edu)
 *
 ***********************************************/


public class Primes{

 /*****************************************
 * Creat a function called isPrime to check whether a number is a prime number
 * author Siyan Wang(swang2@haverford.edu)
 *
 ***********************************************/
    
    public static boolean isPrime(int number){
	//  Returns true if n is a prime, false otherwise
	if (number==1){
	    // 1 is not a prime
	    return false;
	}
	else{
      	// if n is not a prime, it will have a factor <= the square root of n
	    for (int j=2; j<=(int) Math.sqrt(number); j++){
		// check whether j is the factor of n
		if(number%j == 0){
		    return false;
		}
		
	    } 
	}
	// if n does not have a factor that is larger than 1 and smaller than sqrt(n), it is a prime
	return true;
    }//end of isPrime()

    public static void main (String [] args){
	// Print out all prime numbers between 1 and 100
	for (int i=1; i<=100; i++){
	    if (isPrime(i)){
		System.out.println(i);
	    }
	}
    }// end of main()
}// end of class Primes()
