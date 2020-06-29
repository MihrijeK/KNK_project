package sample;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class SecurityHelper {

    public SecurityHelper(){

    }
     public String hashPassword(String password) throws Exception{
        MessageDigest messageDigest=MessageDigest.getInstance("SHA-512");
     }
