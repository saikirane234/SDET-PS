package dictionary;

import java.util.List;

public interface DictionaryService {
	
   
    public boolean isEnglishWord(String word);
	public List<String> getDictionary();
	
}