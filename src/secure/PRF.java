package secure;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * This class is used to implement pseudo-random function.
 */
public class PRF {

    public static final String hashType = "SHA-256";

	public static final String keyedHashType = "HmacSHA256";

	public static final char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    
    public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	public static String genShortHash(String fileName, int outputLength) throws Exception {

		String hash = genHash(fileName);

		return hash.substring(0, outputLength);
	}

	public static String genHash(String fileName) throws Exception {

		InputStream fis;
		fis = new FileInputStream(fileName);
		byte[] buffer = new byte[1024];
		MessageDigest digest = MessageDigest.getInstance(hashType);
		int numRead = 0;
		while ((numRead = fis.read(buffer)) > 0) {
			digest.update(buffer, 0, numRead);
		}
		fis.close();
		return toHexString(digest.digest());
	}

    public static byte[] SHA256(byte[] msg) {

        try {

            MessageDigest md = MessageDigest.getInstance(hashType);

            md.update(msg);
            
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] HMACSHA256(byte[] msg, byte[] key) {

        byte[] digest;

        try {
            Mac sha256_HMAC = Mac.getInstance(keyedHashType);

            SecretKey secretKey = new SecretKeySpec(key, keyedHashType);

            sha256_HMAC.init(secretKey);

            sha256_HMAC.update(msg);

            digest = sha256_HMAC.doFinal();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }

        return digest;
    }
    
    public static byte[] HMACSHA256(String msg, String key) {

        byte[] digest;

        try {

            Charset asciiCs = Charset.forName("US-ASCII");

            Mac sha256_HMAC = Mac.getInstance(keyedHashType);

            SecretKey secretKey = new SecretKeySpec(asciiCs.encode(key).array(), keyedHashType);

            sha256_HMAC.init(secretKey);

            sha256_HMAC.update(msg.getBytes());

            digest = sha256_HMAC.doFinal();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }

        return digest;
    }
}
