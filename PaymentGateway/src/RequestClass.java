import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class RequestClass {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		String bank_ifsc_code="ICIC0000001";
		String bank_account_number="11111111";
		String amount="10000.00";
		String merchant_transaction_ref="txn001";
		String transaction_date="2014-11-14";
		String payment_gateway_merchant_reference="merc001";
		
		String combined= "bank_ifsc_code=ICIC0000001|bank_account_number=11111111|amount=10000.00|merchant_transaction_ref=txn001|transaction_date=2014-11-14|payment_gateway_merchant_reference=merc001";
		String hash= HashingWithSHA1(combined);
		System.out.println(hash);
		String payload_with_sha= combined+"|hash="+hash;
		System.out.println(payload_with_sha);
		AESEncryption aes= new AESEncryption(payload_with_sha);
		
		
	}

	public static String HashingWithSHA1(String combined) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		String sha1="";
		MessageDigest dig= MessageDigest.getInstance("SHA-1");
		dig.reset();
		try {
			dig.update(combined.getBytes("UTF-8"));
			sha1= byteToHex(dig.digest());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sha1;
	}
	
	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}

}
