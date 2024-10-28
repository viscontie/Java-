import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

/** 
 * WordCounter.java
 * @authors Ella Visconti and Finley Sebert
 * Implements static methods that aid and abet in turning a text file into a 
 * ArrayList that stores each word and the number of time it occurs.
 */
public class WordCounter {

    // stores stopwords for subsequent use
    public static ArrayList<String> stopwordList;

    /**
     * A method that takes a text file and stores every word (all lowercase, no punctuation)
     * @param file file to be read from 
     * @return an ArrayList containing every word in the text file
     */
    public static ArrayList<String> readText(File file) {
        
        ArrayList<String> array = new ArrayList<>();

        // attempts to take input from file if it exists
        try {
            
            // reads each word from file and adds it to the list
            Scanner text = new Scanner(new FileReader(file)); // utilizes FileReader because Scanner was misbehaving
            while (text.hasNext()) {
                String next = text.next();
                // splits whenever there is a non-alphabetic character besides [']
                String[] split = next.split("[^a-zA-Z\']");
                for (String word : split) {
                    // removes punctuation from word
                    word = word.replaceAll("[^a-zA-Z]","");
                    // makes word lowercase
                    word = word.toLowerCase();
                        // if the word is not an empty string, then add it
                        if (!word.equals("")) {
                            array.add(word);
                        
                    }
                }
            }
            text.close();
        }

        // if file cannot be found, catches FileNotFoundException
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return array;
    }
    
    /**
     * Reads from "stopwords.txt" a list of stop words
     * @return an ArrayList of words not to be included
     */
    public static ArrayList<String> stopwords() {
        
        // if this method has not been run before, calculate stopwordsList for the first time
        if (stopwordList == null) {
            // opens "stopwords.txt"
            File file = new File("stopwords.txt");
            
            ArrayList<String> stopwords = readText(file);
            stopwordList = stopwords;
            return readText(file);
        }
        // else returns stored stopwordList
        return stopwordList;
    }

    /**
     * Takes a text file as input and sorts its words and corresponding counts alphabetically
     * @param file the file whose words are to be sorted
     * @return an ArrayList that contains the words and counts of the text file sorted alphabetically
     */
    public static ArrayList<WordCount> alphabetical(File file) {
        
        // initializes an empty ArrayList
        ArrayList<String> alphabetical = new ArrayList<String>();

        // iterates through every word in the text file and only keeps words that are not stop words
        for (String word : readText(file)) {
            if (!stopwords().contains(word)) {
                alphabetical.add(word);
            }
        }
        
        // creates a binary search tree from the ArrayList of words, keeping count of repeat entries
        WordCountMap BST = new WordCountMap();
        for (String word : alphabetical) {
            BST.incrementCount(word);
        }

        // Sorts the BST into an ArrayList organized alphabetically, returns it
        ArrayList<WordCount> alphabeticalArray = BST.getWordCountsByWord();
        return alphabeticalArray;
    }
    
    /**
     * Takes a text file as input and sorts its words by number of times that they occur
     * @param file the file whose words are to be sorted
     * @return an ArrayList that contains the words and counts of the text file sorted by count in descending order
     */
    public static ArrayList<WordCount> frequency(File file) {
        
        // initializes an empty ArrayList
        ArrayList<String> frequency = new ArrayList<String>();

        // interates through every word in the text file and only keeps words that are not stop words
        
        for (String word : readText(file)) {
            if (!stopwords().contains(word)) {
                frequency.add(word);
            }
        }

        // creates a binary search tree from the ArrayList of words, keeping count of repeat entries
        WordCountMap BST = new WordCountMap();
        for (String word : frequency) {
            BST.incrementCount(word);
        }
        
        // Sorts the BST into an ArrayList organized by count, returns it
        ArrayList<WordCount> frequencyArray = BST.getWordCountsByCount();
        return frequencyArray;

    }
    
    /**
     * Uses the WordCloudMaker class to create a word cloud from a text file
     * @param file the file to make a word cloud out of
     * @param n the number of words to be included
     */
    public static void cloud(File file, int n) {
        
        // creates a title with proper format
        String name = file.getName();
        name = name.replace(".txt", "");
        String title = name + "-cloud";
        
        // initializes list of words to include in cloud
        ArrayList<WordCount> cloudWords = new ArrayList<WordCount>();

        // gets words of text file organized by frequency
        ArrayList<WordCount> frequencyArray = frequency(file);
        
        // if there are less words in array than n, set n equal to size of array
        if (n > frequencyArray.size()) {
            n = frequencyArray.size();
        }
        
        // gets the first n most frequent words in the text file
        for (int i = 0; i < n; i++) {
            cloudWords.add(frequencyArray.get(i));
        }

        // creates HTML file based on provided words and count and prints it
        String document = WordCloudMaker.getWordCloudHTML(title, cloudWords);
        System.out.print(document);
    }
    
    
    public static void main(String[] args) {

        if (args[0].equals("alphabetical")) {
            
            try {
                File f = new File(args[1]);
                for (WordCount wordCount : alphabetical(f)) {
                    System.out.println(wordCount.getWord() + ":" + wordCount.getCount());
                }
            }
            catch (NullPointerException e) {
                System.out.println("command must be of form: WordCounter alphabetical [textFileName]");
            }
        }
        if (args[0].equals("frequency")) {
            
            try {
                File f = new File(args[1]);
                for (WordCount wordCount : frequency(f)) {
                    System.out.println(wordCount.getWord() + ":" + wordCount.getCount());
                }
            }
            catch (NullPointerException e) {
                System.out.println("command must be of form: WordCounter frequency [textFileName]");
            }
        }
        if (args[0].equals("cloud")) {

            try {
            cloud(new File(args[1]), Integer.valueOf(args[2]));
            }
            catch (NumberFormatException e) {
                System.out.println("numberOfWordsToInclude must be an integer!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("command must be of form: WordCounter cloud [textFileName] [numberOfWordsToInclude]");
            } catch (NullPointerException e) {
                System.out.println("command must be of form: WordCounter cloud [textFileName] [numberOfWordsToInclude]");
            }
        }
    
    }
}
