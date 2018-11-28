package com.lovemylunch.common.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public MD5() {
    }

    public static String toMD5(String val) throws Exception {
        try {
            byte[] nsae = val.getBytes();
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(nsae);
            byte[] messageDigest = algorithm.digest();
            return convertToHex(messageDigest);
        } catch (NoSuchAlgorithmException var4) {
            throw new Exception("MD5 not implemented.");
        }
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < data.length; ++i) {
            int halfbyte = data[i] >>> 4 & 15;
            int two_halfs = 0;

            do {
                if (0 <= halfbyte && halfbyte <= 9) {
                    buf.append((char) (48 + halfbyte));
                } else {
                    buf.append((char) (97 + (halfbyte - 10)));
                }

                halfbyte = data[i] & 15;
            } while (two_halfs++ < 1);
        }

        return buf.toString();
    }
}