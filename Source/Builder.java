import barcode2d.*;
import java.util.Scanner;
import java.io.*;
/**
 * Write a description of class Builder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Builder{
    static int choicea, choiceb, choicec;
    static String config = "";static String cfg = "";
    static StringBuffer configuration = new StringBuffer (config) ; 
    static String msg;
    boolean next=true;
    int i=0;
    private String filename; 
    private BarCode2DReader reader = new BarCode2DReader();
    public int setConfig() {
        System.out.println("Choissisez une taille parmi celles disponibles :");
        System.out.printf( "\n 32x32   (1) \n 64x64   (2) \n 128x128 (3) \n 256x256 (4)\n");
         while (next){
            Scanner choice = new Scanner (System.in);
            try{
                i=choice.nextInt() ;
                next = false;
            } 
            catch(java.util.NoSuchElementException e){
                next = true;
            }
            if (i > 4 || i < 1){
                next = true;
            }
            if (next) {
                System.out.println("Vous n'avez pas entrer un choix valide");
            }
        }
        switch (i){
            case 1 : System.out.println("Vous avez choisi 32x32");
            configuration.append ("000");
            choicea=32;
            break;
            case 2 : System.out.println("Vous avez choisi 64x64");
            configuration.append ("001");
            choicea=64;
            break;
            case 3 : System.out.println("Vous avez choisi 128x128");
            configuration.append ("010");
            choicea=128;
            break;
            case 4 : System.out.println("Vous avez choisi 256x256");    
            configuration.append ("011");
            choicec=256;
            break;

            default: System.out.println("Vous n'avez pas entrer un choix valide");
        }
        return choicea;
    }

    public int setCompressionMode()
    {
        //configuration.delete(0, configuration.length());
        System.out.println("Voulez-vous utilisez le mode de compression ?");
        System.out.printf("\n Oui (1) \n Non (2)\n");
        int l=0;
        boolean next=true;
         while (next){
            Scanner choice = new Scanner (System.in);
            try{
                l=choice.nextInt() ;
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
        switch (l)
        {
            case 1 : System.out.println("Mode de compression activé");
            configuration.append ("1");
            choicec=1;
            break;
            case 2 : System.out.println("Mode de compression désactivé");
            configuration.append ("0");
            choicec=0;
            break;
            default: System.out.println("Vous n'avez pas entrer un choix valide");
        }
        return choicec;
        //System.out.println("Code binaire de configuraton :"+configuration);
        //cfg = configuration.toString();
        //c = Integer.parseInt(cfg);

    }

    public int setDataType(){  
        //configuration.delete(0, configuration.length());
        //System.out.println(configuration);
        int i=0;
        boolean next=true;
        System.out.println("Choissisez un type de données parmi celles disponibles :");
        System.out.printf( "\n ASCII 7 bits   (1) \n ASCII étendu   (2) \n URL            (3) \n Japanese Kanji (4)\n");
         while (next){
            Scanner choice = new Scanner (System.in);
            try{
                i=choice.nextInt() ;
                next = false;
            } 
            catch(java.util.NoSuchElementException e){
                next = true;
            }
            if (i > 4 || i < 1){
                next = true;
            }
            if (next) {
                System.out.println("Vous n'avez pas entrer un choix valide");
            }
        }
        switch (i){
            case 1 : System.out.println("Vous avez choisi  ASCII 7 bits ");
            configuration.append ("0000");
            choiceb=0;
            break;
            case 2 : System.out.println("Vous avez choisi  ASCII étendu");
            configuration.append ("0001");
            choiceb=1;
            break;
            case 3 : System.out.println("Vous avez choisi URL");
            configuration.append ("0010");
            choiceb=2;
            break;
            case 4 : System.out.println("Vous avez choisi Japanese Kanji"); 
            configuration.append ("0011");
            choiceb=3;
            break;

            default: System.out.println("Vous n'avez pas entrer un choix valide");
        }
        return choiceb;

        //System.out.println("Code binaire de configuration : "+configuration);
        //cfg = configuration.toString();
        //b = Integer.parseInt(cfg);
        //sc.close();

    }

    public static String getconfigurationCode(){
        cfg= configuration.toString();
        return cfg;
    }

    public static String toBinaryString(String msg) {
        char[] cArray=msg.toCharArray();
        StringBuilder sb=new StringBuilder();
        for(char c:cArray)
        {
            String cBinaryString=Integer.toBinaryString((int)c);
            sb.append(cBinaryString);
        }

        return sb.toString();
    }

    public static StringBuilder toBinaryStringTwice(String msg){
        byte[] bytes = msg.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            //binary.append(' ');
        }
        return binary;
    }

    public static String text() {
        System.out.printf("\nQuel est le texte à encoder ?\n");
        Scanner sc = new Scanner(System.in);
        msg=sc.nextLine();
        sc.close();
        return msg;
    }

    public static String getConfig(int [][]data){
        int temp;
        String code="";
        String input="";
        StringBuilder decode= new StringBuilder();
        for (int c=0; c<data.length-1;c++){
            for (int l=0; l<data[0].length-1;l++){
                temp=data[c][l];
                decode.append(temp); 
            }
        }
        try{code=decode.substring(0,16);}
        catch(Exception e){System.out.println("Erreur de décryptage");}
        return code.toString();
    }

    public static void affiche(int [][]data)throws IOException
    {
        Data dataf= new Data(data);
        BarCode2DWriter writer = new BarCode2DWriter((BarCode2DData) dataf);
        new BarCode2DFrame ((BarCode2DData)dataf, msg); 
        try 
        {
            writer.drawBarCode2D("test.png", choicea*choicea, choicea*choicea);
        }
        catch (IOException e)
        {
            System.out.println("Erreur d'écriture");
        }
    }

    public static void affiche2()throws IOException{
        try
        {
            BarCode2DReader reader = new BarCode2DReader();
            reader.loadBarCode2D ("test.png", 32, 32);
            new BarCode2DFrame (reader.getBarCodeData(), "Hello A., koff koff...");
        }
        catch (IOException exception)
        {
            System.err.println ("Une erreur s'est produite lors de la lecture du fichier");
        }
    }
}