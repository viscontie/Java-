import java.util.ArrayList;
import java.util.Collections;
/** 
 * WordCountMap.java
 * @authors Ella Visconti and Finley Sebert
 * 
 * A binary search tree that stores WordCount objects.
 * Provides methods to add/increment a word to a tree, to check
 * if a tree contains a word, and to sort a tree's WordCount into a list.
 */
public class WordCountMap {
    
    // the root node of this WordCountMap
    private Node root;
    
    /**
     * Binary search tree nodes that store WordCount object
     */
    private class Node {
        
        // a WordCount instance that stores a word and the number of times that word has been counted
        private WordCount wordCount;
        // stores the node to the left
        private Node left;
        // stores the node to the right
        private Node right;

        /**
         * WordCountMap node constructor
         * @param word the word to be stored by this Node
         */
        private Node(String word) {
            this.wordCount = new WordCount(word, 1);
            this.left = null;
            this.right = null;
        }
    }
    
    /**
     * Constructs an empty WordCountMap
     */
    public WordCountMap() {
        root = null;
    }
    
    /**
     * A recursive helper function.
     * Either adds a new Node containing a word to a subtree, or increments the
     * count of that word by 1 if it is already contained within the subtree.
     * @param root the root of the subtree
     * @param word the word to be added/incremented
     * @return the root with the word added/incremented
     */
    private Node incrementCount(Node root, String word)  {
        
        // if the root of the subtree is null, then return the word to be set as the root
        if (root == null) {
            return new Node(word);
        }

        // compare alphabetical order of the root and the word
        int comparison = root.wordCount.getWord().compareTo(word);

        // if the root comes after the word alphabetically, add/increment the word in the left subtree
        if (comparison > 0) {
            root.left = incrementCount(root.left, word);
        // if the root and the word are the same, then increment the count of the root word by 1.
        } else if (comparison == 0) {
            root.wordCount.incrementCount();
        // if the root comes before the word alphabetically, add/increment the word in the right subtree
        } else {
            root.right = incrementCount(root.right, word);
        }
        return root;

    }
    
    /**
     * Either adds a new Node containing a word to the WordCountMap, or increments the
     * count of that word by 1 if it is already contained within the WordCountMap.
     * @param word the word to be added/incremented
     */
    public void incrementCount(String word)    {
        // uses a recursive helper function
        root = incrementCount(root, word);
    }

    /**
     * A recursive helper function.
     * Checks if a subtree contains a specified word.
     * @param word the word to be searched for
     * @param root the root of the subtree
     * @return returns true if the subtree contains the word; returns false otherwise
     */
    private boolean contains(String word, Node root) {
        
        // if the root is null, then the word is not contained in the subtree
        if (root == null) {
            return false;
        }
        
        // if the root comes after the word alphabetically, then search the left subtree
        if (root.wordCount.getWord().compareTo(word) > 0) {
            return contains(word, root.left);
        // if the root is the same as the word, then the subtree contains the word; return true
        } else if (root.wordCount.getWord().compareTo(word) == 0) {
            return true;
        // if the root comes before the word alphabetically, then search the right subtree
        } else if (root.wordCount.getWord().compareTo(word) < 0) {
            return contains(word, root.right);
        }

        return false;
    }
    
    /**
     * Checks if the WordCountMap contains a word.
     * @param word the word to be searched for
     * @return returns true if the WordCountMap contains the word; returns false otherwise
     */
    public boolean contains(String word) {
        // uses a recursive helper function
        return contains(word, root);
    }

    /**
     * A recursive helper function.
     * Organizes a subtree into an ArrayList sorted alphabetically.
     * @param array the ArrayList the tree is to be sorted into
     * @param root the root of the subtree
     * @return an ArrayList of WordCounts sorted alphabetically
     */
    private ArrayList<WordCount> getWordCountsByWord(ArrayList<WordCount> array, Node root) {
            
            // does an in-order traversal of the WordCountMap, resulting in an alphabetically sorted array
            if (root != null) {
                getWordCountsByWord(array, root.left);
                array.add(root.wordCount);
                getWordCountsByWord(array, root.right);
            }
            return array;
        }

    /**
     * Organizes the WordCountMap into an ArrayList sorted alphabetically.
     * @return an ArrayList of WordCounts sorted alphabetically
     */
    public ArrayList<WordCount> getWordCountsByWord() {
        // uses a recursive helper function
        return getWordCountsByWord(new ArrayList<WordCount>(), root);
    }

    
     /**
      * Organizes the WordCountMap into an ArrayList sorted by frequency.
      * @return an ArrayList of WordCounts sorted by frequency in descending order
      */
    public ArrayList<WordCount> getWordCountsByCount() {
        
        // gets the alphabetically sorted list
        ArrayList<WordCount> byCountArray = getWordCountsByWord();
        // sorts the list by ascending frequency
        Collections.sort(byCountArray);
        // reverses the list so it is sorted by descending frequency
        Collections.reverse(byCountArray);
        return byCountArray;
    }



    public static void main(String[] args) {
        WordCountMap map = new WordCountMap();
        map.incrementCount("apple");
        map.incrementCount("aaaaa");
        map.incrementCount("banana");
        map.incrementCount("apple");
        map.incrementCount("apple");
        map.incrementCount("apple");
        map.incrementCount("apple");
        map.incrementCount("apple");
        map.incrementCount("loser");
        map.incrementCount("loser");
        
        System.out.print(map.contains("aaaaa"));
        
        
    }
}

