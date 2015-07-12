import java.util.*;
import java.io.*;
import barcode2d.*;
/**
 * Write a description of class Starter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Starter
{
    String configuration;
    static boolean create;
    static boolean write;
    static boolean affiche;
    static int choiceBuilder1;static int choiceBuilder2;static int choiceBuilder3;
    /**
     * 
     * 
     */
    public static void main(String []args) throws IOException
    {
        start(); // Codage ou Lecture
        if (create){
            Builder startBuilder = new Builder();
            choiceBuilder1=startBuilder.setConfig();
            choiceBuilder2=startBuilder.setDataType();
            choiceBuilder3=startBuilder.setCompressionMode();
            System.out.println(startBuilder.getconfigurationCode());
            Configurator config= new Configurator(choiceBuilder1,choiceBuilder2,choiceBuilder3);
            String msgBuilder=startBuilder.text();
            StringBuilder binaryBuilder2=startBuilder.toBinaryStringTwice(msgBuilder);
            int [][]data=new int[choiceBuilder1][choiceBuilder1];
            Encodor startEncodor= new Encodor();
            affiche();
            data= startEncodor.encode((startBuilder.getconfigurationCode())+(binaryBuilder2.toString()), choiceBuilder1, affiche);

            
            try 
            {startBuilder.affiche(data); 
            }
            catch (IOException e)
            {System.out.println("Erreur d'écriture");
            }

        }
        if (write){
            Builder startBuilder= new Builder();
            BarCode2DReader test = new BarCode2DReader();
            int [][]data=new int[32][32];
            String decoder=startBuilder.getConfig(data);
            System.out.println("Décodage: ["+decoder+"]");
            
            
            //BarCode2DReader reader = new BarCode2DReader();
            //Data test3 = new Data(reader.getBarCodeData();
            //reader.loadBarCode2D ("test.png", 32, 32);
            //reader.getBarCodeData().getValue();

            Decodor test1 = new Decodor();
            if (test1.check(data)==true){System.out.println("check true");}
            else System.out.println("check false");
            
            
            try 
            {startBuilder.affiche2(); 
            }
            catch (IOException e)
            {System.out.println("Erreur d'écriture");
            }
            return;
        }
    }   

    public static void affiche(){
        System.out.println("Voulez-vous afficher ou non la matrice de bits ?");
        System.out.println("Oui (1)\nNon (2)");
        Scanner choice = new Scanner (System.in); 
        try{
            int i = choice.nextInt();
            switch (i){
                case 1 : System.out.println("Vous avez choisi oui");
                affiche=true;
                break;
                case 2 : System.out.println("Vous avez choisi non");
                affiche=false;
                break;

                default: System.out.println("Vous n'avez pas entrer un choix valide");
            }
        }
        catch (java.util.InputMismatchException e) {
            choice.nextLine();

        }
    }

    public static void start (){
        create= false; write=false;
        System.out.println("Que voulez-vous faire ?\nCréer ou lire un code-barres 2D ?");
        System.out.println("Créer (1)\nLire (2)");
        Scanner choice = new Scanner (System.in); 

        try{
            int i = choice.nextInt();
            switch (i){
                case 1 : System.out.println("Vous avez choisi créer");
                create=true;write=false;
                break;
                case 2 : System.out.println("Vous avez choisi lire");
                write=true;create=false;
                break;

                default: System.out.println("Vous n'avez pas entrer un choix valide");
            }
        }
        catch (java.util.InputMismatchException e) {
            choice.nextLine();

        }
    }
}
