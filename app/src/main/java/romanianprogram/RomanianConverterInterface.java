package romanianprogram;

import java.util.HashMap;

public interface RomanianConverterInterface {
    public String cyrillicToLatin(String input, HashMap<String, String> tokensHashMap);
    public String latinToCyrillic(String input, HashMap<String, String> tokensHashMap);
}
