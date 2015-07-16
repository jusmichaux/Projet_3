
public class Encodor implements Encoder {
    // pour importer la taille, on rajoute un paramètre nommé choicea venant de Starter
    public int[][] encode(String Aftercast, int choicea)  {
        int j=choicea;
        int[][] tab= new int[j][j]; 
        int count=0;
        for(int l=1; l<tab.length ; l++)
        {
            for (int c=1; c<tab[0].length; c++)
            {
                if (count<(((choicea--)*(choicea--))-16)/*Aftercast.length()*/)
                {
                    tab[l][c]= Character.getNumericValue(Aftercast.charAt(count));
                    count++;
                }
                System.out.print(tab[l][c]+" ");
            }
            System.out.println("");     
        }
        return tab;

    }

    public int[][] encode(String Aftercast, int choicea, boolean affiche)  {
        int j=choicea;
        int[][] tab= new int[choicea][choicea]; 
        int count=0;
        if (affiche){System.out.println("Remarque : la matrice affichée ne comprends pas la zone de correction");}
        for(int l=1; l<tab.length ; l++)
        {
            for (int c=1; c<tab[0].length; c++)
            {
                if (count<Aftercast.length())
                {
                    tab[l][c]= Character.getNumericValue(Aftercast.charAt(count));
                    count++;
                }
                if (affiche){System.out.print(tab[l][c]+" ");}
            }
            if(affiche){System.out.println("");     
            }
            
        }
        if (affiche){System.out.println("count: "+count+"\n Aftercast.length: "+Aftercast.length());}
        return tab;

    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public int[][] encode(String msg) {
        // TODO Auto-generated method stub
        return null;
    }

    public void encodeParity(int [][]data, int length){

        int countcorner=0;
        for (int c=1; c<length;c++){
            int countl=0;
            int countc=0;
            for (int l=1;l<length;l++){
                if (data[c][l]==1){
                    countc++;
                }
                if (data[l][c]==1){
                    countl++;
                }
            }
            if (countc % 2 !=0){
                data[c][0] = 1;
            }
            if (countl % 2 !=0){
                data[0][c] = 1;
            }
            if (data[0][c] == 1){
                countcorner++;
            }
        }
        if (countcorner%2 !=0){
            data[0][0] = 1;
        }
    }
}
class EncodingException extends Exception{
    EncodingException (String mes, Throwable cause){
        super(mes, cause);
        System.out.println("EncodingException lancée");
    }
}