/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.akinmukomi.junk.cryptography;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author akinmukomi
 */
public class AsymmetricEncryptionUtils {
    
    public static KeyPair generatekeyPair() throws NoSuchAlgorithmException{
        
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(4096, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
        
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
        KeyPair keyPair = AsymmetricEncryptionUtils.generatekeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();
        
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, aPublic);
        byte[] doFinal = cipher.doFinal("Oluwaseun".getBytes());
        
        
        Cipher cipher1 = Cipher.getInstance("RSA");
        cipher1.init(Cipher.DECRYPT_MODE, aPrivate);
        byte[] doFinal1 = cipher1.doFinal(doFinal);
        
        System.out.println("value is "+new String(doFinal1));
//        byte[] encoded = aPrivate.getEncoded();
//        System.out.println("Encoded Hex Binary is ===> "+DatatypeConverter.printHexBinary(encoded));
//        
//        File file = new File("privatekey");
//        file.createNewFile();
//        String absolutePath = file.getAbsolutePath();
//        System.out.println("file path ==> "+absolutePath);
//        
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        fileOutputStream.write(encoded);
    }
    
}
