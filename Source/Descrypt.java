import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.Key; 
import javax.crypto.Cipher; 
import javax.crypto.spec.SecretKeySpec;
public class Descrypt{
    private String algo = "Blowfish";
    public Descrypt(){
    }

    public void crypter(String password, String entree, String sortie) {
        try {
            byte[] passwordInBytes = password.getBytes("ISO-8859-2"); 
            Key clef = new SecretKeySpec(passwordInBytes, algo); 
            Cipher cipher = Cipher.getInstance(algo);
            cipher.init(Cipher.ENCRYPT_MODE, clef);

            byte[] texteClaire = entree.getBytes();
            byte[] texteCrypte = cipher.doFinal(texteClaire);
        }
        catch (Exception e) {
            System.out.println("Erreur lors de l'encryptage des donnees");
        }

    }

    public void decrypter(String password, String entree, String sortie) {
        try {
            byte[] passwordInBytes = password.getBytes("ISO-8859-2"); 
            Key clef = new SecretKeySpec(passwordInBytes, algo); 
            Cipher cipher = Cipher.getInstance(algo);
            cipher.init(Cipher.DECRYPT_MODE, clef);

            byte[] texteCrypte = entree.getBytes();
            byte[] texteClaire = cipher.doFinal(texteCrypte);
        }
        catch (Exception e) {
            System.out.println("Erreur lors du décryptage des donnees");
        }
    }
    public void main (String[] args){
        
    
        
    }

}