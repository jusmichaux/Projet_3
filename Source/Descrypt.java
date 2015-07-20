import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.Key; 
import javax.crypto.Cipher; 
import javax.crypto.spec.SecretKeySpec;

import java.lang.*;
public class Descrypt{
    static boolean debug = false;
    private String algo = "Blowfish";

    public Descrypt(){
    }

    public static void main (String[] args){
        String a = cryptSimple("Bonjour", 3);
        String b = decryptSimple(a, 3);
        Builder withBuilder2 = new Builder();

        System.out.println("bonjour: "+b);
        String afterb = withBuilder2.toBinaryString(b);
        System.out.println("binary bonjour: "+afterb);
        System.out.println("Length of bonjour: "+afterb.length());
        
        System.out.println("bonjour crypted: "+a);
        String aftera = withBuilder2.toBinaryString(a);
        System.out.println("binary bonjour crypted: "+aftera);
        System.out.println("Length of bonjour crypted: "+aftera.length());
        
        //TEST 2
        String code = "0012";
        String cle = "Blowfish";
        System.out.println("Avant Cryptage");
        System.out.println(encrypt(code, "a"));
        System.out.println("Après Cryptage");
        System.out.println(decrypt((encrypt(code, "a")), "a"));
        Builder withBuilder = new Builder();
        String binaryecodecrypted = withBuilder.toBinaryString(encrypt(code, "a"));
        String binarycode =withBuilder.toBinaryString(decrypt((encrypt(code, "a")), "a"));
        System.out.println("Binary Code:"+binarycode);
        System.out.println("Length of binary Code:"+binarycode.length());
        System.out.println("Binary Code crypted:"+binaryecodecrypted);
        System.out.println("Length of binary Code crypted:"+binaryecodecrypted.length());
        
    }
    public String getDecryptSimple(String password, int key){
        return decryptSimple(password, key);
    }
    public String getCryptSimple(String password, int key){
        return cryptSimple(password, key);
    }
    public String getEncrypt(String password, String key){
        return encrypt(password, key);
    }
    public String getDecrypt(String password, String key){
        return decrypt(password, key);
    }
    private static String decrypt(String password,String key){
        try
        {
            Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE,clef);
            return new String(cipher.doFinal(password.getBytes()));
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    private static String decryptSimple(String password, int key){
        StringBuilder Replacer = new StringBuilder(password);
        if(debug){System.out.println("Entrée decryptSimple:"+Replacer.toString());}
        try{
            for (int i=0; i<password.length();i++){
                if(debug){System.out.println("Avant :"+Replacer.charAt(i));}
                int temp =  Replacer.charAt(i)^key;
                Replacer.setCharAt(i,(char)temp);
                if(debug){System.out.println("Après :"+Replacer.charAt(i));}

            }
            if (debug){System.out.println("Sortie decryptSimple:"+Replacer.toString());}
            return Replacer.toString();
        }
        catch (Exception e){
        }
        if (debug){System.out.println("Sortie decryptSimple:"+Replacer.toString());}
        return Replacer.toString();
    }

    private static String cryptSimple(String password, int key){
        StringBuilder Replacer = new StringBuilder(password);
        if(debug){System.out.println("Entrée cryptSimple :"+Replacer.toString());}
        try{
            for (int i=0; i<password.length();i++){
                if(debug){System.out.println("Avant :"+Replacer.charAt(i));}
                int temp = Replacer.charAt(i)^key;
                Replacer.setCharAt(i,(char)temp);
                if(debug){System.out.println("Après :"+Replacer.charAt(i));}

            }
            if (debug){System.out.println("Sortie decryptSimple:"+Replacer.toString());}
            return Replacer.toString();
        }
        catch (Exception e){
        }
        if(debug){System.out.println("Sortie cryptSimple:"+Replacer.toString());}
        return Replacer.toString();

    }

    private static String encrypt(String password,String key){
        try
        {
            Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE,clef);
            return new String(cipher.doFinal(password.getBytes()));
        }
        catch (Exception e)
        {
            return null;
        }
    }
}