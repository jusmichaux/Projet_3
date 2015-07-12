/**
 * Write a description of interface Decoder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 

public interface Decoder { 
	
	
	/**
	 * @pre data != null 
	 * 		data est une matrice carrée, de taille 32, 64, 128 ou 256 
	 * 		data ne contient que des 0 et des 1 
	 * @post La valeur renvoyée contient le décodage de la matrice de bits data 
	 * 		 décodée en respectant la configuration de ce décodeur
	 * 		 Si la matrice contient une erreur, celle-ci est corrigée 
	 * @throw DecodingException au cas où la matrice data ne peut pas être décodée 
	 */ 
	public String decode (int[][] data);
	
	
	
	/** 
	 * @pre data != null 
	 * 		data est une matrice carrée, de taille 32, 64, 128 ou 256 
	 * 		data ne contient que des 0 et des 1 * @post La valeur renvoyée contient true si data ne contient pas 
	 * 		d’erreurs (la parité de la matrice de bits est valide), 
	 * 		et false sinon 
	 */ 
	public boolean check (int[][] data);
	
	
	/** 
	 * @pre 
	 * @post La valeur renvoyée contient la configuration de ce décodeur 
	 */ 
	public Configuration getConfiguration();
}

