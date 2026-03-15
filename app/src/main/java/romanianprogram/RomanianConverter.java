package romanianprogram;

import java.util.HashMap;

public class RomanianConverter implements RomanianConverterInterface {
    
    private static RomanianConverter Instance;
    private RomanianConverter() {}

    public static RomanianConverter getInstance() {
        if (Instance == null) {
            Instance = new RomanianConverter();
        }
        return Instance;
    }
    
    @Override
    public String cyrillicToLatin(String input, HashMap<String, String> tokensHashMap) {
        String output = new String();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input string is empty");
        } else {
            for (var c : input.toCharArray()) {
                if (c >= 58 && c <= 64) {
                    output += c;
                } else {
                    output += tokensHashMap.get(String.valueOf(c));
                }
            }
        }
        return output;
    }

    @Override
    public String latinToCyrillic(String input, HashMap<String, String> tokensHashMap) {
        HashMap<String, String> invertedTokensHashMap = invertHashMap(tokensHashMap);
        String output = new String();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input string is empty");
        } else {
            for (int idx = 0; idx < input.length(); idx++) {
                if (input.charAt(idx) >= 58 && input.charAt(idx) <= 64) {
                    output += String.valueOf(input.charAt(idx));
                } else {
                    if (idx + 1 != input.length() && idx + 2 != input.length()) {
                        if (invertedTokensHashMap.containsKey(String.format("%s%s%s", input.charAt(idx), input.charAt(idx+1), input.charAt(idx+2)))) {
                            output += invertedTokensHashMap.get(String.format("%s%s%s", input.charAt(idx), input.charAt(idx+1), input.charAt(idx+2)));
                            idx += 2;
                            continue;
                        }
                        if (invertedTokensHashMap.containsKey(String.format("%s%s", input.charAt(idx), input.charAt(idx+1)))) {
                            output += invertedTokensHashMap.get(String.format("%s%s", input.charAt(idx), input.charAt(idx+1)));
                            idx += 1;
                            continue;
                        }
                    } if (invertedTokensHashMap.containsKey(String.format("%s", input.charAt(idx)))) {
                            output += invertedTokensHashMap.get(String.format("%s", input.charAt(idx)));
                    }
                }
            }
        }
        return output;
    }

    private HashMap<String, String> invertHashMap(HashMap<String, String> hashMap) {
        var invertedHashMap = new HashMap<String, String>();
        var keySet = hashMap.keySet();
        var valuesCollection = hashMap.values();
        var keySetIter = keySet.iterator();
        var valuesCollecitonIter = valuesCollection.iterator();
        while (keySetIter.hasNext() && valuesCollecitonIter.hasNext()) {
            invertedHashMap.put(valuesCollecitonIter.next(), keySetIter.next());
        }
        return invertedHashMap;
    }
}
