package mx.com.ipn.upiicsa.poo.pizarron.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

public class Encryption {
	public static String digest(String token) {
		String digest = null;
		try {
			Security.addProvider(new BouncyCastleProvider());
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			digest = Base64.toBase64String(new String(Hex.encode(md.digest(token.getBytes()))).getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return digest;
	}
}
