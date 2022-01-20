package dictionaryTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import dictionary.Dictionary;
import dictionary.DictionaryService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class DictionaryTester {

    private Dictionary dictionary;
    private DictionaryService dictionaryService;
    List<String> dictionaryList;
   
    /**
     * Sets the dictionary list
     * Runs before test cases
     */
    @Before
    public void setUp() {
        dictionary = new Dictionary();
        dictionaryService = mock(DictionaryService.class);
        dictionary.setDictionaryService(dictionaryService);
        // I'm adding a mocked dictionary here
        List<String> l=createDictionaryArray();
        when(dictionaryService.getDictionary()).thenReturn(l);
        dictionaryList = dictionaryService.getDictionary();
    }

    /**
     * Reads all the data from a .txt file 
     * @return String list with the dictionary content
     */
     List<String> createDictionaryArray() {
    	 List<String> listDictionary = new ArrayList<String>();
        try {
            
        	listDictionary= Files.readAllLines(Paths.get("./EnglishWords.txt"));
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listDictionary;
    }
     
     
     /**
      * .isEnglishWord function 
      * @return boolean by comparing word to dictionary content
      */
   
    public boolean isThisEnglish(String word) {
        for (String w : dictionaryList) {
            if (w.equals(word.toLowerCase())) {
                
                return true;
            }
        }
        return false;
    }
    
    /**
    When a Valid word is passed
     */
   
    @Test
    public void validWord() {
        when(dictionaryService.isEnglishWord("ABANDONED")).thenReturn(isThisEnglish("ABANDONED"));
        Assert.assertTrue(dictionary.isEnglishWord("ABANDONED"));
    }
    
    
    /**
     * valid
     *  When a Valid word is passed and the List of all english words is returned
     */
    @Test
    public void validListOfWordsWithValidWord() {
    	when(dictionaryService.isEnglishWord("WORKING")).thenReturn(isThisEnglish("WORKING"));
        Assert.assertTrue(dictionary.isEnglishWord("WORKING"));
        List<String> al=dictionary.findAllWords("WORKING");
    	
    	Assert.assertTrue(dictionaryList.containsAll(al));
    	//System.out.println(al);
    }
    
    /**
     * positive
     * When a inValid word is passed and the List of all english words is returned
     */
    @Test
    public void validListOfWordsWithInvalidWord() {
        when(dictionaryService.isEnglishWord("RUPCHIK")).thenReturn(isThisEnglish("RUPCHIK"));
        Assert.assertFalse(dictionary.isEnglishWord("RUPCHIK"));
        List<String> al=dictionary.findAllWords("RUPCHIK");
    	//System.out.println(al);
    	Assert.assertTrue(dictionaryList.containsAll(al));
    }
    
    
    /**
     * negative
     * When a inValid word is passed
     */
    @Test
    public void invalidWord() {
        when(dictionaryService.isEnglishWord("RUPCHIK")).thenReturn(isThisEnglish("RUPCHIK"));
        Assert.assertFalse(dictionary.isEnglishWord("RUPCHIK"));
    }
    
    /**
     * negative
     * When a no word is passed
     */
    @Test
    public void noWord() {
        when(dictionaryService.isEnglishWord("")).thenReturn(isThisEnglish(""));
        Assert.assertFalse(dictionary.isEnglishWord(""));
    }
    
   
}









