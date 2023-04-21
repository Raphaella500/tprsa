/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desencrypyer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


public class DesEncrypyer {

   static final byte k[] = "yassine1".getBytes();
	static SecretKeySpec myDesKey = new SecretKeySpec(k,"DES");
    static Cipher desCipher;
	
    public static String DESencryption(byte [] text){
    	try{
    		
    		  // Create the cipher
    		 desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    		 // Initialize the cipher for encryption
    		 desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
    		 byte[] textEncrypted = desCipher.doFinal(text);
    		 
             return new sun.misc.BASE64Encoder().encode(textEncrypted);
    	}catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e){
		}catch(Exception e){	
    } 
    return null;
    }
    public static String DESdecrypt(String str){
    	try {
    		
    		byte[] textencrypted = new sun.misc.BASE64Decoder().decodeBuffer(str);
    		
  		  // Create the cipher
  		 desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
  		 // Initialize the same cipher for decryption
		    desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
		    // Decrypt the text
		    byte[] textDecrypted = desCipher.doFinal(textencrypted);
		    
		    return new String(textDecrypted,"UTF-8");
    	}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}catch(NoSuchPaddingException e){
			e.printStackTrace();
    }catch(InvalidKeyException e){
		e.printStackTrace();
    }catch(Exception e){	
    } 
    	return null;
    }
	public static void main(String[] argv) {
 try{
		    byte[] text = "yassine£".getBytes("UTF-8");
 long startTime=System.currentTimeMillis();
		    
		    System.out.println("encrypted information :"+DESencryption(text));
		    System.out.println(" encrypted in "+(System.currentTimeMillis()-startTime)+"ms" );
		    long start=System.currentTimeMillis();
		    System.out.println("decyrypted information :"+DESdecrypt("WaJecOaONCQmY/n9yfmEFg=="));
		    System.out.println(" decrypted in "+(System.currentTimeMillis()-start)+"ms" );	
		   
 
		    
		    }catch(Exception e){
		    	
		    }
 
		   
		    
 
	}
 
    
}
