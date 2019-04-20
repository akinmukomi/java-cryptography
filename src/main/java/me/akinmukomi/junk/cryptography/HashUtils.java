/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.akinmukomi.junk.cryptography;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author akinmukomi
 * 
 * Sample implementation of Hashing algorithm
 * Bcrypt , SCript , PBKDF2, SHA-1, SHA-2, SHA-3, SHA-4, MD5
 */

public class HashUtils {
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        
        String name = "Akinmukomi";
        MessageDigest messageDigest = MessageDigest.getInstance("PBKDF2");
        byte[] digest = messageDigest.digest(name.getBytes());
        
        byte[] digest2 = messageDigest.digest(name.getBytes());
       
        System.out.println(DatatypeConverter.printHexBinary(digest).equals(DatatypeConverter.printHexBinary(digest2)));
    }
    
}
