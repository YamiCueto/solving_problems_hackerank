package com.hackerrank;

import java.util.*;

public class Solution {

    public static List<Integer> stringAnagram(List<String> dictionary, List<String> query) {
        Map<String, Integer> freq = new HashMap<>();
        for (String word : dictionary) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();
        for (String q : query) {
            char[] chars = q.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            result.add(freq.getOrDefault(key, 0));
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== Sample Case 0 ===");
        List<String> dict0 = Arrays.asList("heater", "cold", "clod", "reheat", "docl");
        List<String> query0 = Arrays.asList("codl", "heater", "abcd");
        List<Integer> result0 = stringAnagram(dict0, query0);
        System.out.println("Result:   " + result0);
        System.out.println("Expected: [3, 2, 0]");

        System.out.println("\n=== Sample Case 1 ===");
        List<String> dict1 = Arrays.asList("listen", "tow", "silent", "lisent", "two", "abc", "no", "on");
        List<String> query1 = Arrays.asList("two", "bca", "no", "listen");
        List<Integer> result1 = stringAnagram(dict1, query1);
        System.out.println("Result:   " + result1);
        System.out.println("Expected: [2, 1, 2, 3]");

        System.out.println("\n=== Example from problem ===");
        List<String> dict2 = Arrays.asList("hack", "a", "rank", "khac", "ackh", "kran", "rankhacker", "a", "ab", "ba", "stairs", "raits");
        List<String> query2 = Arrays.asList("a", "nark", "bs", "hack", "stair");
        List<Integer> result2 = stringAnagram(dict2, query2);
        System.out.println("Result:   " + result2);
        System.out.println("Expected: [2, 2, 0, 3, 1]");
    }
}
