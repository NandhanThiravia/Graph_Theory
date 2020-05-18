package com.other.problems;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class WordLadder {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set.add("poon");
        set.add("plee");
        set.add("same");
        set.add("poie");
        set.add("plie");
        set.add("poin");
        set.add("plea");
        String start = "toon";
        String target = "plea";

        new WordLadder().shortestChain(start, target, set);
    }

    private void shortestChain(String start, String target, Set<String> set) {
        LinkedList<String> wordLadder = new LinkedList<String>();

        while ((start != target) && (set.size() >= 0)) {
            String word = null;
            boolean isOK = false;
            Iterator<String> iterator = set.iterator();
            char[] startCharArray = start.toCharArray();
            
            while (iterator.hasNext()) {
                word = iterator.next();
                char[] wordCharArray = word.toCharArray();
                int diff = 0;
                for (int index = 0; index < wordCharArray.length; ++index) {
                    if (wordCharArray[index] != startCharArray[index]) {
                        ++diff;
                    }
                }
                if (diff == 1) {
                    isOK = true;
                    break;
                }
            }
            if (isOK) {
                isOK = false;
                wordLadder.add(word);
                set.remove(word);
                start = word;
            } else {
                System.err.println("Could not find an answer");
                break;
            }
        }
        
        if (start == target) {
            for (String word : wordLadder) {
                System.out.println(word);
            }
        }
    }
}
