
/**
 * Write a description of interface Encoder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface Encoder { 
	
	
	
	
	/** 
	 * @pre msg != null 
	 * @post La valeur renvoyée contient une matrice de bits correspondant 
	 * au message msg, encodé en respectant la configuration de cet encodeur 
	 * @throw EncodingException au cas où le message msg ne peut pas être encodé 
	 */ 
	public int[][] encode (String msg);
	 
	 
	 
	 /**
	  * @pre 
	  * @post La valeur renvoyée contient la configuration de cet encodeur 
	  */
	 public Configuration getConfiguration();
	 
	 
}


