package romanianprogram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class RomanianReader implements RomanianReaderInterface {

    private static RomanianReader Instance;
    private RomanianReader() {}

    public static RomanianReader getInstance() {
        if (Instance == null) {
            Instance = new RomanianReader();
        }
        return Instance;
    }
    
    @Override
    public HashMap<String, String> tokensFromFileReader(String path) {
        var tokensHashMap = new HashMap<String, String>(); 
        try (var reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitted = line.split(":");
                tokensHashMap.put(splitted[0], splitted[1]);
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return tokensHashMap;
    }

    @Override
    public String textFromFileReader(String path) {
        var output = new String();      
        try (var reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output += line;
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return output;
    }

}
