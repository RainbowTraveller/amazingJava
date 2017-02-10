import java.nio.charset.Charset;
import java.lang.Character;
import javax.xml.bind.DatatypeConverter;

public class Emoji {

	public static final Charset UTF_8 = Charset.forName("UTF-8");
	public static final Charset ISO = Charset.forName("ISO-8859-1");


	public static String convertToUTF8(String str) {
		byte[] byteArray = str.getBytes(ISO);
		/*String encoded =  DatatypeConverter.printBase64Binary(byteArray);
		byte[] decoded = DatatypeConverter.parseBase64Binary(encoded);*/
		return new String(byteArray, UTF_8);
	}

	public static String convertToUTF8(int hexString) {
		char[] emojiCharArray = Character.toChars(hexString);
		return new String(emojiCharArray);
	}

	public static void printCodePoints(String str) {
		for(int i = 0; i < str.length(); ++i) {
			System.out.println("Index : " + i + " Code point : " + str.codePointAt(i) + " HEX VALUE :" + Integer.toHexString(str.charAt(i)) + " UTF8 String : "  + convertToUTF8(str.charAt(i)) + " HIGH : " + Character.isHighSurrogate(str.charAt(i)) + " LOW : " + Character.isLowSurrogate(str.charAt(i)));
			//System.out.println(Character.digit(str.codePointAt(i), 16));
			System.out.println(Long.parseLong(Integer.toHexString(str.charAt(i)), 16));
//			char [] emojiCharArray = Character.toChars(Character.digit(str.charAt(i), 16));
//			String emojiAsString = new String(emojiCharArray);
//			System.out.println("EMOJI STRING : " + emojiAsString);
		}
	}

    public static void main(String args[]) {

		String listname = "Emoji test🎉💃🏻👍🏼";
		String simpleString = "Emoji test";
		System.out.println(listname);
		System.out.println(convertToUTF8(listname));
		System.out.println("String length : " + listname.length() + " Code Points : "  + Character.codePointCount(listname, 0, listname.length()));
		printCodePoints(listname);
		System.out.println("String length : " + simpleString.length() + " Code Points : "  + Character.codePointCount(simpleString, 0, simpleString.length()));
		printCodePoints(simpleString);
    }
}
