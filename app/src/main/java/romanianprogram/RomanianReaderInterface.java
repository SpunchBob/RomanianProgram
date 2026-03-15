package romanianprogram;

import java.util.HashMap;

public interface RomanianReaderInterface {
    public HashMap<String, String> tokensFromFileReader(String path);
    public String textFromFileReader(String path);
}
