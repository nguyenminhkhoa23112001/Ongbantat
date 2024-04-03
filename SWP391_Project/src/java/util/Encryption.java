/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.MessageDigest;
import org.apache.tomcat.util.codec.binary.Base64;


/**
 *
 * @author ADMIN
 */
public class Encryption {

    //sha-1
    public static String toSHA1(String password) {
        String salt = "uysdfqoasbcasr;fgsdfqwrucsnbfmasoqs;";
        String result = null;
        
        password += salt;
        
        try {
            byte [] dataBytes = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        
        return result;
    }
    public static void main(String[] args) {
        System.out.println(toSHA1("123"));
    }
}
