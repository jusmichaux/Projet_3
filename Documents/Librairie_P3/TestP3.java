// TestP3.java

import barcode2d.BarCode2DReader;
import barcode2d.BarCode2DFrame;

import java.io.IOException;

public class TestP3
{
	public static void main (String[] args)
	{
		try
		{
			BarCode2DReader reader = new BarCode2DReader();
			reader.loadBarCode2D ("barcode.png", 64, 64);
			new BarCode2DFrame (reader.getBarCodeData(), "Hello A., koff koff...");
		}
		catch (IOException exception)
		{
			System.err.println ("Une erreur s'est produite lors de la lecture du fichier");
		}
	}
}