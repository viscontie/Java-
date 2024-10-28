/**
 * WordCount.java
 * @author Sherri Goings, 12 Feb 2013
 * Modified by Jeff Ondich, 14 Feb 2014
 * Modified by Layla Oesper, 31 Oct 2017
 * Modified by Kiran Tomlinson, 14 Feb 2023
 * 
 * Stores a word and its associated count.
 */

public class WordCount implements Comparable<WordCount> {
    private String word;
    private int count;
    
    /**
     * Constructor to make a new WordCount with the given
     * word and count.
     */ 
    public WordCount(String aWord, int aCount) {
        word = aWord;
        count = aCount;
    }
    
    /**
     * @return the word assiciated with this WordCount object
     */ 
    public String getWord() {
        return word;
    }
    
    /**
     * @return the count assiciated with this WordCount object
     */ 
    public int getCount() {
        return count;
    }

    /**
     * Increment the count stored in this object by 1
     */ 
    public void incrementCount() {
        count += 1;
    }
    
    /**
     * Compare the count of this WordCount with another
     * @param other the WordCount to compare to
     * @return a positive int, a negative int, or 0 if the 
     *         this word count is greater than, less than,
     *         or equal other's word count, respectively 
     */ 
    public int compareTo(WordCount other) {
        return count - other.getCount();
    }
}