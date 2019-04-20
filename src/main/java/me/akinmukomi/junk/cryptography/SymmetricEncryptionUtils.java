/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.akinmukomi.junk.cryptography;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author akinmukomi
 */
public class SymmetricEncryptionUtils {
    
    
    public static SecretKey createAESKey() throws NoSuchAlgorithmException{
        
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256, new SecureRandom());
        return keyGenerator.generateKey();
    }
    
    public static byte[] generateInitializationVector(){
        byte[] initializationVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        return initializationVector;
    }
    
    public static byte[] encryptText(String plainText, SecretKey secretKey, byte[] initializationVector) throws NoSuchAlgorithmException, 
            NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, BadPaddingException{
        
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey , ivParameterSpec);
        byte[] encrytedValue = cipher.doFinal(plainText.getBytes());
        
        System.out.println("encrypted value "+new String(encrytedValue));
        
        return encrytedValue;
    }
    
    public static String decryptText(byte[] cipherText, SecretKey secretKey, byte[] initializationVector) throws NoSuchAlgorithmException, 
            NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, 
            IllegalBlockSizeException, BadPaddingException{
        
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey , ivParameterSpec);
        byte[] encrytedValue = cipher.doFinal(cipherText);
        
        System.out.println("decrypted value "+new String(encrytedValue));
        
        return new String(encrytedValue);
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
       
        SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
        
        String plainText = "Oluwaseun Akinmukomi is the best!";
        byte[] initializationVector = SymmetricEncryptionUtils.generateInitializationVector();
        byte[] encryptText = SymmetricEncryptionUtils.encryptText(plainText, secretKey, initializationVector);
        
        String decryptText = SymmetricEncryptionUtils.decryptText(encryptText, secretKey, initializationVector);
        
    }   
}
