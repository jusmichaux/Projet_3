
import barcode2d.*;
/**
 * Write a description of class Data here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Data implements BarCode2DData
{
    private int [][] Matricedata;

    public  Data (int Matricedata[][])
    {
        this.Matricedata= Matricedata;
    }

    public int getWidth ()
    {
        return Matricedata.length;

    }

    public int getHeight()
    {
           return Matricedata[0].length;
    }
    public boolean getValue(int line, int column)
    {
        boolean check = true;
        if (Matricedata[line][column]== 0)
        {
            check= false ;

        }
        if (Matricedata[line][column]== 1)
        {
            check= true ;

        }
        return check;
    }
}