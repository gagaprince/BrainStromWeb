package com.prince.myproj.wx.services;

import com.prince.myproj.util.AESUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
@Service
public class DecodeService {
    public String decodeByEncryIvSession(String encryptedData,String iv,String sessionKey){
        try {
            byte[] resultByte = AESUtil.instance.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                String decodeStr = new String(resultByte, "UTF-8");
                return decodeStr;
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
