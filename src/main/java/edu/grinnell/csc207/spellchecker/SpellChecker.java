package edu.grinnell.csc207.spellchecker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * A spellchecker maintains an efficient representation of a dictionary for
 * the purposes of checking spelling and provided suggested corrections.
 */
public class SpellChecker {
    /** The number of letters in the alphabet. */
    private static final int NUM_LETTERS = 26;

    /** The path to the dictionary file. */
    private static final String DICT_PATH = "words_alpha.txt";

    /**
     * @param filename the path to the dictionary file
     * @return a SpellChecker over the words found in the given file.
     */
    public static SpellChecker fromFile(String filename) throws IOException {
        return new SpellChecker(Files.readAllLines(Paths.get(filename)));
    }

    /** A Node of the SpellChecker structure. */
    private class Node {
        boolean isRoot;
        
        char value;
        boolean isWord;
        List<Node> nodes;
        
        public Node() {
            isRoot = true;
            isWord = false;
            nodes = new ArrayList<>();
        }
        
        public Node(char value) {
            isRoot = false;
            this.value = value;
            nodes = new ArrayList<>();
        }
    }

    /** The root of the SpellChecker */
    private Node root;

    public SpellChecker(List<String> dict) {
        // TODO: implement me!
    }

    public void add(String word) {
        Node curr = root;
        Node newNode;
        for (char ch : word.toCharArray()) {
            newNode = getNode(ch, curr);
            if (newNode == null) {
                newNode = new Node(ch);
                curr.nodes.add(newNode);
            }
            curr = newNode;
        }
        curr.isWord = true;
    }
    
    private Node getNode(char ch, Node curr) {
        if (curr.nodes.isEmpty()) {
            return null;
        }
        for (Node node : curr.nodes) {
            if (node.value == ch) {
                return node;
            }
        }
        return null;
    }

    public boolean isWord(String word) {
        // TODO: implement me!
        return false;
    }

    public List<String> getOneCharCompletions(String word) {
        // TOOD: implement me!
        return null;
    }

    public List<String> getOneCharEndCorrections(String word) {
        // TODO: implement me!
        return null;
    }

    public List<String> getOneCharCorrections(String word) {
        // TODO: implement me!
        return null;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java SpellChecker <command> <word>");
            System.exit(1);
        } else {
            String command = args[0];
            String word = args[1];
            SpellChecker checker = SpellChecker.fromFile(DICT_PATH);
            switch (command) {
                case "check": {
                    System.out.println(checker.isWord(word) ? "correct" : "incorrect");
                    System.exit(0);
                }

                case "complete": {
                    List<String> completions = checker.getOneCharCompletions(word);
                    for (String completion : completions) {
                        System.out.println(completion);
                    }
                    System.exit(0);
                }

                case "correct": {
                    List<String> corrections = checker.getOneCharEndCorrections(word);
                    for (String correction : corrections) {
                        System.out.println(correction);
                    }
                    System.exit(0);
                }

                default: {
                    System.err.println("Unknown command: " + command);
                    System.exit(1);
                }
            }
        }
    }
}
