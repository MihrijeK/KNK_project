package Helpers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class SecurityHelper {

    public SecurityHelper(){

    }
     public String hashPassword(String password) throws Exception{
        MessageDigest messageDigest=MessageDigest.getInstance("SHA-512");
        byte[] bytes=messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encodedHash= Base64.getEncoder().encodeToString(bytes);

        return encodedHash;
     }
    public boolean checkPassword(String userinput,String hashedpassword) throws Exception{
      return hashPassword(userinput).equals(hashedpassword)?true:false;

    }

}
