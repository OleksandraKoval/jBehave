package common.helper;

import java.util.Base64;

public class EncodingManager {


    public static String decryptString(final String message, final String key) {
        try {
            if (message == null || key == null) {
                return null;
            }
            char[] keyCharSequence = key.toCharArray();
            char[] messageCharSequence = new String(Base64.getDecoder().decode(message)).toCharArray();
            char[] decryptMessageCharSequence = new char[messageCharSequence.length];
            for (int i = 0; i < messageCharSequence.length; i++) {
                decryptMessageCharSequence[i] =
                        (char) (messageCharSequence[i] ^ keyCharSequence[i % keyCharSequence.length]);
            }
            return new String(decryptMessageCharSequence);
        } catch (Exception e) {
            return null;
        }
    }
}
