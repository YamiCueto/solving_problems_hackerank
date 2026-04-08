package com.hackerrank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("String Anagram - stringAnagram()")
class SolutionTest {

    // -------------------------------------------------------------------------
    // Sample cases from problem
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Sample Case 0: cold/clod/docl share key 'cdlo', heater/reheat share 'aeehrt'")
    void sampleCase0() {
        List<String> dictionary = Arrays.asList("heater", "cold", "clod", "reheat", "docl");
        List<String> query      = Arrays.asList("codl", "heater", "abcd");
        List<Integer> expected  = Arrays.asList(3, 2, 0);
        assertEquals(expected, Solution.stringAnagram(dictionary, query));
    }

    @Test
    @DisplayName("Sample Case 1: two/tow share 'otw'; abc/bca share 'abc'; no/on share 'no'; listen/silent/lisent share 'eilnst'")
    void sampleCase1() {
        List<String> dictionary = Arrays.asList("listen", "tow", "silent", "lisent", "two", "abc", "no", "on");
        List<String> query      = Arrays.asList("two", "bca", "no", "listen");
        List<Integer> expected  = Arrays.asList(2, 1, 2, 3);
        assertEquals(expected, Solution.stringAnagram(dictionary, query));
    }

    @Test
    @DisplayName("Example from problem: hack/khac/ackh share 'achk'; rank/kran share 'aknr'; 'a' appears twice")
    void problemExample() {
        List<String> dictionary = Arrays.asList(
            "hack", "a", "rank", "khac", "ackh", "kran", "rankhacker", "a", "ab", "ba", "stairs", "raits"
        );
        List<String> query     = Arrays.asList("a", "nark", "bs", "hack", "stair");
        List<Integer> expected = Arrays.asList(2, 2, 0, 3, 1);
        assertEquals(expected, Solution.stringAnagram(dictionary, query));
    }

    // -------------------------------------------------------------------------
    // Edge cases
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Query word not in dictionary → returns 0")
    void queryNotInDictionary() {
        List<String> dictionary = Arrays.asList("hello", "world");
        List<String> query      = Arrays.asList("xyz");
        assertEquals(Collections.singletonList(0), Solution.stringAnagram(dictionary, query));
    }

    @Test
    @DisplayName("Empty dictionary → all queries return 0")
    void emptyDictionary() {
        List<String> dictionary = Collections.emptyList();
        List<String> query      = Arrays.asList("listen", "silent");
        assertEquals(Arrays.asList(0, 0), Solution.stringAnagram(dictionary, query));
    }

    @Test
    @DisplayName("Empty query list → empty result")
    void emptyQueryList() {
        List<String> dictionary = Arrays.asList("hello", "world");
        List<String> query      = Collections.emptyList();
        assertTrue(Solution.stringAnagram(dictionary, query).isEmpty());
    }

    @Test
    @DisplayName("Single-character words: 'a', 'a', 'b' in dict; query 'a' → 2, query 'b' → 1, query 'c' → 0")
    void singleCharWords() {
        List<String> dictionary = Arrays.asList("a", "a", "b");
        List<String> query      = Arrays.asList("a", "b", "c");
        assertEquals(Arrays.asList(2, 1, 0), Solution.stringAnagram(dictionary, query));
    }

    @Test
    @DisplayName("Duplicate entries in query: each lookup is independent")
    void duplicateQueryEntries() {
        List<String> dictionary = Arrays.asList("cat", "act", "tac");
        List<String> query      = Arrays.asList("cat", "cat", "dog");
        assertEquals(Arrays.asList(3, 3, 0), Solution.stringAnagram(dictionary, query));
    }

    @Test
    @DisplayName("Word that is its own anagram (single letter with multiple occurrences)")
    void wordWithRepeatedLetters() {
        List<String> dictionary = Arrays.asList("aab", "aba", "baa", "aab");
        List<String> query      = Arrays.asList("aab");
        // all four share sorted key "aab"
        assertEquals(Collections.singletonList(4), Solution.stringAnagram(dictionary, query));
    }
}
