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
@DisplayName("Active Traders - mostActive()")
class SolutionTest {

    // -------------------------------------------------------------------------
    // Problem examples
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Example from problem: Bigcorp, Acme, Zork qualify (23 total trades)")
    void problemExample() {
        List<String> customers = Arrays.asList(
            "Bigcorp","Bigcorp","Acme","Bigcorp","Zork","Zork","Abc","Bigcorp",
            "Acme","Bigcorp","Bigcorp","Zork","Bigcorp","Zork","Zork","Bigcorp",
            "Acme","Bigcorp","Acme","Bigcorp","Acme","Littlecorp","Nadircorp"
        );
        assertEquals(Arrays.asList("Acme", "Bigcorp", "Zork"), Solution.mostActive(customers));
    }

    @Test
    @DisplayName("Sample Case 0: Alpha(9), Omega(10), Beta(1) of 20 → all qualify at ≥5%")
    void sampleCase0() {
        List<String> customers = Arrays.asList(
            "Omega","Alpha","Omega","Alpha","Omega","Alpha","Omega","Alpha","Omega","Alpha",
            "Omega","Alpha","Omega","Alpha","Omega","Alpha","Omega","Alpha","Omega","Beta"
        );
        assertEquals(Arrays.asList("Alpha", "Beta", "Omega"), Solution.mostActive(customers));
    }

    @Test
    @DisplayName("Sample Case 1: only Beta and Zeta reach ≥5%")
    void sampleCase1() {
        List<String> customers = Arrays.asList(
            "Alpha","Beta","Zeta","Beta","Zeta","Zeta","Epsilon","Beta","Zeta","Beta",
            "Zeta","Beta","Delta","Zeta","Beta","Zeta","Beta","Zeta","Beta","Zeta","Beta"
        );
        assertEquals(Arrays.asList("Beta", "Zeta"), Solution.mostActive(customers));
    }

    // -------------------------------------------------------------------------
    // Edge cases
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Single customer with 1 trade = 100% → qualifies")
    void singleCustomer() {
        List<String> customers = Collections.singletonList("OnlyOne");
        assertEquals(Collections.singletonList("OnlyOne"), Solution.mostActive(customers));
    }

    @Test
    @DisplayName("Exact 5% boundary: 1 trade out of 20 (5% exactly) → qualifies")
    void exactFivePercentBoundary() {
        // 1 * 100 = 100 >= 20 * 5 = 100 → true (>=, not >)
        List<String> customers = Arrays.asList(
            "A","B","B","B","B","B","B","B","B","B",
            "B","B","B","B","B","B","B","B","B","B"
        );
        // A has 1/20 = 5.0% → qualifies; B has 19/20 = 95% → qualifies
        List<String> result = Solution.mostActive(customers);
        assertTrue(result.contains("A"));
        assertTrue(result.contains("B"));
    }

    @Test
    @DisplayName("Customer just below 5% threshold: 1 trade out of 21 → does not qualify")
    void justBelowThreshold() {
        // 1 * 100 = 100 < 21 * 5 = 105 → does not qualify
        List<String> customers = Arrays.asList(
            "Small","Big","Big","Big","Big","Big","Big","Big","Big","Big",
            "Big","Big","Big","Big","Big","Big","Big","Big","Big","Big","Big"
        );
        List<String> result = Solution.mostActive(customers);
        assertFalse(result.contains("Small"));
        assertTrue(result.contains("Big"));
    }

    @Test
    @DisplayName("Result is sorted alphabetically")
    void resultIsSorted() {
        List<String> customers = Arrays.asList(
            "Zeta","Zeta","Zeta","Zeta","Zeta",
            "Alpha","Alpha","Alpha","Alpha","Alpha",
            "Mango","Mango","Mango","Mango","Mango"
        );
        List<String> result = Solution.mostActive(customers);
        assertEquals(Arrays.asList("Alpha", "Mango", "Zeta"), result);
    }

    @Test
    @DisplayName("All customers are unique and below 5%: empty result")
    void allUniqueCustomersBelowThreshold() {
        // 20 distinct customers, each with 1 trade → 1/20 = 5% exactly, all qualify
        // Make 21 to push each below threshold
        List<String> customers = Arrays.asList(
            "A1","A2","A3","A4","A5","A6","A7","A8","A9","A10",
            "A11","A12","A13","A14","A15","A16","A17","A18","A19","A20","A21"
        );
        // 1 * 100 = 100 < 21 * 5 = 105 → no one qualifies
        assertTrue(Solution.mostActive(customers).isEmpty());
    }
}
