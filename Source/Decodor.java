
public class Decodor implements Decoder {
    @Override
    public String decode(int[][] data) {
        int a=0;
        assert data != null : "Data est null";
        assert data[a][a] == 0 || data[a][a] == 1 : "Le tableau ne contient pas uniquement de 0 ou de 1";
        return null;
    }

    @Override
    public boolean check(int[][] data) {
        int temp=0;
        int ligncount=0;
        assert data != null : "Data est null";
        assert temp == 0 || temp == 1 : "Le tableau ne contient pas uniquement de 0 ou de 1";
        for (int l=0; l<data.length-1;l++){
            for (int c=0; c<data[0].length-1;c++){
                temp=data[l][c];
                ligncount=ligncount+temp;

            }
            if (evenOrNot(ligncount)==false){return false;}
        }

        for (int c=0; c<data[0].length-1;c++){
            for (int l=0; l<data.length-1;l++){
                temp=data[c][l];
                ligncount=ligncount+temp;
            }
            if (evenOrNot(ligncount)==false){return false;}
        }
        return true;

    }

    public static int[] findConfiguration(int [][]data){
        int []subarray = new int[16];
        int count=0;
        if (count<16){
            for (int l=1;l<data.length;l++){
                for (int c=1;c<data[0].length;c++){
                    subarray[count]=data[l][c];
                    count++;
                }
            }
        }
        return subarray;
    }

    /**
     * @pre: number != null
     */
    public static boolean evenOrNot(int number)
    {
        assert number!=0 : "evenOrNot (int number), error : number null, 0 ";
        if (number % 2 == 0){
            return true;//even number
        }
        else return false;//odd number
    }

    @Override
    public Configuration getConfiguration() {
        // TODO Auto-generated method stub
        return null;
    }
}
class DecodingException extends Exception{
    DecodingException (String mes)
    {
        super(mes);
        System.out.println("Decoding Exception lancée.");
    }
}

