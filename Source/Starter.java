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
    protected static boolean debug;
    static boolean create;
    static boolean read;
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
        if (read){
            try {
                BufferedImage img = ImageIO.read(new File("test.png"));
                int width = img.getWidth();
                int height = img.getHeight();
                System.out.println("width: "+width+ "height: "+height);
                Builder decodage = new Builder();
                int [][]data=new int[width][height];
                data=decodage.getData("test.png", width, height);
                if(debug){
                    System.out.println("Tentative d'écriture du tableau data");
                    for(int i=0; i<data.length;i++){
                        for (int j=0; j<data[0].length;j++){
                            System.out.print(data[i][j]);
                        }
                        System.out.println("");
                    }
                } 
                String config;
                config= decodage.getConfig(data);
                Configurator Config= new Configurator(decodage.choicea,decodage.choiceb,decodage.choicec);
                if(debug){System.out.println("Code de configuration: "+config);}
            } catch (IOException e) {
                System.err.println("Une erreur s'est produite lors de la premiere partie de lecture du Code Barre");
            }

            //String decoder=startBuilder.getConfig(data);
            //System.out.println("Décodage: ["+decoder+"]");

            //BarCode2DReader reader = new BarCode2DReader();
            //Data test3 = new Data(reader.getBarCodeData();
            //reader.loadBarCode2D ("test.png", 32, 32);
            //reader.getBarCodeData().getValue();

            Decodor test1 = new Decodor();

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
