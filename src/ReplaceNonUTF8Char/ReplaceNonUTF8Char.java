package JavaReplaceNonUTF8.net.gorton.ReplaceNonUTF8Char;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ReplaceNonUTF8Char {

    public static final char REPLACEMENT_CHAR = ' ';
    public static final String REPLACEMENT_STRING = "";
    public static void main(String[] args) {
        String input = "\u00C4\u00B0nan\u00C3\u00A7 Esaslar\u00C4\u00B1"; // Sample string with non-UTF-8 character
        
        String utf8String = replaceNonUTF8Characters(input, REPLACEMENT_CHAR); // Change '?' to your desired placeholder character
        String utf8String1 = replaceNonUTF8Characters1(input, REPLACEMENT_STRING); 

        System.out.println("Original String: " + input);
        System.out.println("UTF-8 String: " + utf8String);
        System.out.println("UTF-8 String: " + utf8String1);
    }

    public static String replaceNonUTF8Characters(String input, char placeholder) {
        Charset utf8Charset = StandardCharsets.UTF_8;
        
        // Convert the input string to UTF-8 bytes and then back to a string
        byte[] utf8Bytes = input.getBytes(utf8Charset);
        String utf8String = new String(utf8Bytes, utf8Charset);
        
        // Create a StringBuilder to build the result string
        StringBuilder result = new StringBuilder(utf8String.length());
        
        // Iterate through the original string and replace non-UTF-8 characters
        for (char c : utf8String.toCharArray()) {
            if (c != placeholder) {
                result.append(c);
            }
        }
        
        return result.toString();
    }

    public static String replaceNonUTF8Characters1(String input, String replacement) {
        Charset utf8Charset = StandardCharsets.UTF_8;
        byte[] utf8Bytes = input.getBytes(utf8Charset);
        
        StringBuilder utf8String = new StringBuilder();

        for (byte b : utf8Bytes) {
            int value = b & 0xFF; // Convert to an unsigned integer
            if (value <= 0x7F) { // ASCII character
                utf8String.append((char) value);
            } else {
                // Replace non-UTF-8 characters with the custom replacement string
                utf8String.append(replacement);
            }
        }
        
        return utf8String.toString();
    }

}

