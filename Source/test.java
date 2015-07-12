import java.io.*;
import barcode2d.*;
public class test{
    public static void affiche3()throws IOException{
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