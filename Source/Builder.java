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
    protected static boolean debug=false;
    
    int i=0;int j=0; int l=0;
    private String filename; 
    private BarCode2DReader reader = new BarCode2DReader();
    public int setConfig() {
        boolean next=true;
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
                System.out.println("Vous n'avez pas entrer un choix valide\n");
            }
        }
        switch (i){
            case 1 : System.out.println("Vous avez choisi 32x32\n");
            configuration.append ("000");
            choicea=32;
            break;
            case 2 : System.out.println("Vous avez choisi 64x64\n");
            configuration.append ("001");
            choicea=64;
            break;
            case 3 : System.out.println("Vous avez choisi 128x128\n");
            configuration.append ("010");
            choicea=128;
            break;
            case 4 : System.out.println("Vous avez choisi 256x256\n");    
            configuration.append ("011");
            choicec=256;
            break;

            default: System.out.println("Vous n'avez pas entrer un choix valide\n");
        }
        return choicea;
    }

    public int setCompressionMode()
    {
        //configuration.delete(0, configuration.length());
        System.out.println("Voulez-vous utilisez le mode de compression ?");
        System.out.printf("\n Oui (1) \n Non (2)\n");    
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
            case 1 : System.out.println("Mode de compression activé\n");
            configuration.append ("001");
            choicec=1;
            break;
            case 2 : System.out.println("Mode de compression désactivé\n");
            configuration.append ("000");
            choicec=0;
            break;
            default: System.out.println("Vous n'avez pas entrer un choix valide\n");
        }
        configuration.append("000000");// 6 bits fixés à 0
        return choicec;
        //System.out.println("Code binaire de configuraton :"+configuration);
        //cfg = configuration.toString();
        //c = Integer.parseInt(cfg);

    }

    public int setDataType(){  
        //configuration.delete(0, configuration.length());
        //System.out.println(configuration);
        boolean next=true;
        System.out.println("Choissisez un type de données parmi celles disponibles :");
        System.out.printf( "\n ASCII 7 bits   (1) \n ASCII étendu   (2) \n URL            (3) \n Japanese Kanji (4)\n");
        while (next){
            Scanner choice = new Scanner (System.in);
            try{
                j=choice.nextInt() ;
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
        switch (j){
            case 1 : System.out.println("Vous avez choisi  ASCII 7 bits\n");
            configuration.append ("0000");
            choiceb=0;
            break;
            case 2 : System.out.println("Vous avez choisi  ASCII étendu\n");
            configuration.append ("0001");
            choiceb=1;
            break;
            case 3 : System.out.println("Vous avez choisi URL\n");
            configuration.append ("0010");
            choiceb=2;
            break;
            case 4 : System.out.println("Vous avez choisi Japanese Kanji\n"); 
            configuration.append ("0011");
            choiceb=3;
            break;

            default: System.out.println("Vous n'avez pas entrer un choix valide\n");
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

    /* Source: http://stackoverflow.com/questions/14498804/binary-string-to-text-string-java-code
     * Méthode réarrangée par Justin Michaux
     * 
     */
    public static String int2str( String s ) { 
        String[]ss=new String[(s.length()/8)];
        for (int i=0; i<(s.length()/8);i++){
            ss[i]=s.substring(0+(i*8),8+(i*8));
            if(debug){System.out.println(i+": "+ss[i]);}
        }
        //String[] ss = s.split( " " );
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < ss.length; i++ ) { 
            sb.append( (char)Integer.parseInt( ss[i], 2 ) );                                                                                                                                                        
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

    public int[][] getData (String filename, int width, int height){
        try
        {
            reader.loadBarCode2D(filename, width,height);
            int[][] data = new int[width][height];
            for (int l=0; l<width; l++){
                for (int c=0; c<height; c++){
                    if( reader.getBarCodeData().getValue(l,c)){
                        data[l][c]=1;
                    }
                    else {
                        data[l][c]=0;
                    }
                }
            }
            return data;
        }
        catch (IOException exception)
        {
            System.err.println ("Une erreur s'est produite lors de la lecture du fichier");
        }
        return null;
    }

    public static String getConfig(int [][]data){
        int temp;
        String code="";
        String input="";
        StringBuilder decode= new StringBuilder();
        for (int c=1; c<data.length-1;c++){
            for (int l=1; l<data[0].length-1;l++){
                temp=data[c][l];
                decode.append(temp); 
            }
        }
        try{code=decode.substring(0,16);}
        catch(Exception e){System.out.println("Erreur de décryptage dans getConfig");}
        return code.toString();
    }

    public static void checkConfig(String data){
        assert data!=null : "Erreur dans checkConfig";
        String taille;String datatype;String compression;
        taille=data.substring(0,3);
        datatype=data.substring(3,7);
        compression=data.substring(7,10);
        if(debug){
            System.out.println("String data:"+data+"\ntaille:"+taille+"\ndatatype:"+datatype+"\ncompression:"+compression);}
        switch (taille){
            case "000" : //System.out.println("Vous avez choisi 32x32");
            choicea=32;
            break;
            case "001" : //System.out.println("Vous avez choisi 64x64");
            choicea=64;
            break;
            case "010" : //System.out.println("Vous avez choisi 128x128");
            choicea=128;
            break;
            case "011" : //System.out.println("Vous avez choisi lire");
            choicea=264;
            break;
            default: System.out.println("Erreur dans checkConfig");
        }
        switch (datatype){
            case "0000" : //System.out.println("Vous avez choisi ASCII");
            choiceb=0;
            break;
            case "0001" : //System.out.println("Vous avez choisi ASCII étendu");
            choiceb=1;
            break;
            case "0010" : //System.out.println("Vous avez choisi URL");
            choiceb=2;
            break;
            case "0011" : //System.out.println("Vous avez choisi Kanji");
            choiceb=3;
            break;
            default: System.out.println("Erreur dans checkConfig");
        }
        switch (compression){
            case "001" : //System.out.println("Vous avez choisi on");
            choicec=001;
            break;
            case "000" : //System.out.println("Vous avez choisi off");
            choicec=000;
            break;

            default: System.out.println("Erreur dans checkConfig");
        }
        Configurator Config= new Configurator(choicea,choiceb,choicec);
    }

    public static void affiche(int [][]data)throws IOException
    {
        Data dataf= new Data(data);
        BarCode2DWriter writer = new BarCode2DWriter((BarCode2DData) dataf);
        new BarCode2DFrame ((BarCode2DData)dataf, msg); 
        try 
        {
            writer.drawBarCode2D("test.png", choicea, choicea);
        }
        catch (IOException e)
        {
            System.out.println("Erreur d'écriture");
        }
    }

    public static void affiche2(String filename, int height, int width, String msg)throws IOException{
        try
        {
            BarCode2DReader reader = new BarCode2DReader();
            reader.loadBarCode2D (filename, height, width);
            new BarCode2DFrame (reader.getBarCodeData(), msg);
        }
        catch (IOException exception)
        {
            System.err.println ("Une erreur s'est produite lors de la lecture du fichier");
        }
    }
}