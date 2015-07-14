
public class Decodor implements Decoder {
    protected static boolean debug = false;
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
        int posX=0; int posY=0;int error=0;
        assert data != null : "Data est null";
        assert temp == 0 || temp == 1 : "Le tableau ne contient pas uniquement de 0 ou de 1";
        for (int l=0; l<data.length-1;l++){
            for (int c=0; c<data[0].length-1;c++){
                temp=data[l][c];
                ligncount=ligncount+temp;

            }
            if (evenOrNot(ligncount)==false){
                error++;
                posX=l;

            }
        }

        for (int c=0; c<data[0].length-1;c++){
            for (int l=0; l<data.length-1;l++){
                temp=data[c][l];
                ligncount=ligncount+temp;
            }
            if (evenOrNot(ligncount)==false){
                error++;
                posY=c;

            }
        }
        if (error==2){
            data[posX][posY]=reverseInt(data[posX][posY]);
            error=0;
        }
        if (error > 0) {
            return false;
        } else {
            return true;
        }

    }
    public static int reverseInt(int index){
        if (index==0){return 1;}
        else return 0;
    }
    public static int[] findConfiguration(int [][]data){
        int []subarray = new int[16];
        int count=0;
        if (count<16){
            for (int l=1;l<data.length-1;l++){
                for (int c=1;c<data[0].length-1;c++){
                    subarray[count]=data[l][c];
                    count++;
                }
            }
        }
        return subarray;
    }

    public static String getData(int [][]data){
        String sdata="";
        StringBuilder dat= new StringBuilder(sdata);
        int count=0;
        for (int l=1;l<data.length-1;l++){
            for (int c=1;c<data[0].length-1;c++){                
                if (count>15/*&&count<((7*8)+17)*/){
                    dat=dat.append(data[l][c]);
                }
                count++;
            }
        }
        if (debug){
            System.out.println("getData:["+dat.toString()+"]");
        }
        return dat.toString();
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

