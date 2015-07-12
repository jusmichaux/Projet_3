

public class Encodor implements Encoder {
    // pour importer la taille, on rajoute un paramètre nommé choicea venant de Starter
    public int[][] encode(String Aftercast, int choicea)  {
        int j=choicea;
        int[][] tab= new int[j][j]; 
        int count=0;

        for(int l=1; l<tab.length-1 ; l++)
        {
            for (int c=1; c<tab[0].length-1; c++)
            {
                if (count<Aftercast.length())
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
        int[][] tab= new int[j][j]; 
        int count=0;
        if (affiche){System.out.println("Remarque : la matrice affichée ne comprends pas la zone de correction");}
        for(int l=1; l<tab.length-1 ; l++)
        {
            for (int c=1; c<tab[0].length-1; c++)
            {
                if (count<Aftercast.length())
                {
                    tab[l][c]= Character.getNumericValue(Aftercast.charAt(count));
                    count++;
                }
                if (affiche){System.out.print(tab[l][c]+" ");}
            }
            if(affiche){System.out.println("");     }
        }
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

}
class EncodingException extends Exception{
    EncodingException (String mes)
    {
        super(mes);
        System.out.println("Encoding Exception lancée.");
    }
}