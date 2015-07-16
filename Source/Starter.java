import java.util.*;
import java.io.*;
import barcode2d.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
/**
 * Write a description of class Starter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Starter
{
    String configuration;
    protected static boolean debug=true;
    static boolean create;
    static boolean read;
    static boolean affiche=false;
    static int choiceBuilder1;static int choiceBuilder2;static int choiceBuilder3;
    /**
     * 
     * 
     */
    public static void main(String []args) throws Exception
    {
        //System.out.println(a.length());
        start(); // Codage ou Lecture
        if (create){
            // DEBUT --------------------------------- CHOIX DES OPTIONS -------------------------
            Builder startBuilder = new Builder();
            choiceBuilder1=startBuilder.setConfig();
            choiceBuilder2=startBuilder.setDataType();
            choiceBuilder3=startBuilder.setCompressionMode();
            if(debug){System.out.println(startBuilder.getconfigurationCode());}
            // FIN   --------------------------------- CHOIX DES OPTIONS -------------------------
            Configurator config= new Configurator(choiceBuilder1,choiceBuilder2,choiceBuilder3);
            //System.out.println("test: "+test.length());
            // DEBUT ----------------------------- ENCODAGE DU TEXTE A DECODER -------------------
            String msgBuilder=startBuilder.text();   
            if (choiceBuilder1 == 32){
                if (msgBuilder.length()>118){choiceBuilder1=suggest(msgBuilder, msgBuilder.length());}
                else {;}
            }
            else if (choiceBuilder1 == 64 ){
                if (msgBuilder.length()>494) {choiceBuilder1=suggest(msgBuilder, msgBuilder.length());}
                else {;}
            }
            else if (choiceBuilder1 == 128) {
                if (msgBuilder.length()>2014) {choiceBuilder1=suggest(msgBuilder, msgBuilder.length());}
                else {;}
            }
            else if (choiceBuilder1 == 254) {
                if (msgBuilder.length()>7999) {choiceBuilder1=suggest(msgBuilder, msgBuilder.length());}
                else {;}
            }
            StringBuilder binaryBuilder2=startBuilder.toBinaryStringTwice(msgBuilder);
            if(debug){System.out.println(binaryBuilder2.toString());}
            // FIN   ----------------------------- ENCODAGE DU TEXTE A DECODER -------------------
            // DEBUT -------------------------- ENCODAGE DES DONNEES EN TABLEAU -------------------
            int [][]data=new int[choiceBuilder1][choiceBuilder1];
            Encodor startEncodor= new Encodor();
            if(debug){affiche();}
            data= startEncodor.encode((startBuilder.getconfigurationCode())+(binaryBuilder2.toString()), choiceBuilder1, affiche);
            startEncodor.encodeParity(data,choiceBuilder1);
            // FIN --------------------------- ENCODAGE DES DONNEES EN TABLEAU -------------------
            try {
                startBuilder.affiche(data); 
            }
            catch (Exception e){
                System.out.println("Problème d'affichage du tableau data");
                EncodingException exception= new EncodingException("Erreur", e.getCause());
                //System.exit(0);
            }

        }
        if (read){
            try {
                BufferedImage img = ImageIO.read(new File("test.png"));
                int width = img.getWidth();
                int height = img.getHeight();
                if(debug){System.out.println("width: "+width+ "height: "+height);}
                Builder decodage = new Builder();
                int [][]data=new int[width][height];
                data=decodage.getData("test.png", width, height);
                if(debug){
                    System.out.println("Tentative d'écriture du tableau data");
                    for(int i=0; i<data.length-1;i++){
                        for (int j=0; j<data[0].length-1;j++){
                            System.out.print(data[i][j]);
                        }
                        System.out.println("");
                    }
                } 
                String config;
                config= decodage.getConfig(data);
                Configurator Config= new Configurator(decodage.choicea,decodage.choiceb,decodage.choicec);
                if(debug){System.out.println("Code de configuration: "+config);}
                Decodor decod = new Decodor();
                if (decod.check(data)==true){
                    System.out.println("Aucune erreur détecté");
                }
                else {
                    System.out.println("Erreur détectée grâce à check");
                }
                if(debug){System.out.println("["+decodage.int2str(decod.getData(data))+"]");}
                try 
                {decodage.affiche2("test.png", width, height,decodage.int2str(decod.getData(data)) ); 
                }
                catch (IOException e)
                {System.out.println("Erreur d'écriture");
                }
            } catch (IOException e) {
                System.err.println("Une erreur s'est produite lors de la premiere partie de lecture du Code Barre");
            }
            return;
        }
    }   

    public static void affiche(){
        int i =0;
        boolean next = true;
        System.out.println("Voulez-vous afficher ou non la matrice de bits ?");
        System.out.println("Oui (1)\nNon (2)");

        while (next){
            Scanner choice = new Scanner (System.in);
            try{
                i=choice.nextInt() ;
                next = false;
            } 
            catch(java.util.NoSuchElementException e){
                next = true;
            }
            if (i > 2 || i < 1){
                next = true;
            }
            if (next) {
                System.out.println("Vous n'avez pas entrer un choix valide");
            }
        }

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

    public static int suggest(String msgBuilder,int choix){
        int temp;
        int choice =1;
        boolean next = true;
        System.out.println("");System.out.println("");
        if(msgBuilder.length()>300){
            System.out.println("La longueur de vos données est trop importante pour le format que vous utilisez");
            System.out.println("Voulez-vous changer la taille précédemment choisie ?");
            System.out.println("Oui (1)\nNon (2)");
            while (next){
                Scanner twicechoice= new Scanner(System.in);
                try{
                    choice = twicechoice.nextInt();
                    next = false;
                } 
                catch(java.util.NoSuchElementException e){
                    next = true;
                }
                if (choice > 2 || choice < 1){
                    next = true;
                }
                if (next) {
                    System.out.println("Vous n'avez pas entrer un choix valide");
                }
            }
            if (choice == 1){
                System.out.println("Taille actuelle: "+choiceBuilder1);
                System.out.println("Merci de choisir une taille plus grande que la taille actuelle");     
                temp=choiceBuilder1;
                Builder startBuilder2 = new Builder();
                choiceBuilder1=startBuilder2.setConfig();
                if (debug){System.out.println("choiceBuilder1 :"+choiceBuilder1);}
                if (choiceBuilder1==(temp)){
                    choice=2;
                }
            } 
            if ( choice==2){
                System.out.println("Vous avez forcé la taille :"+choiceBuilder1);
                System.out.println("\nCelle-ci va être utilisé malgré la perte de données évidente\n");
            }
        }
        return choiceBuilder1;

    }

    public static void start (){
        create= false; read=false;
        int i = 1;
        boolean next = true;
        System.out.println("Que voulez-vous faire ?\nCréer ou lire un code-barres 2D ?");
        System.out.println("Créer (1)\nLire (2)");
        while (next){
            Scanner choice = new Scanner (System.in);
            try{
                i=choice.nextInt() ;
                next = false;
            } 
            catch(java.util.NoSuchElementException e){
                next = true;
            }
            if (i > 2 || i < 1){
                next = true;
            }
            if (next) {
                System.out.println("Vous n'avez pas entrer un choix valide");
            }
        }
        switch (i){
            case 1 : System.out.println("Vous avez choisi créer");
            create=true;read=false;
            break;
            case 2 : System.out.println("Vous avez choisi lire");
            read=true;create=false;
            break;

            default: System.out.println("Vous n'avez pas entrer un choix valide");
        }

    }
}
