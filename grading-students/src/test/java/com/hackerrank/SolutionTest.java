package com.hackerrank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Grading Students - gradingStudents()")
class SolutionTest {

    // -------------------------------------------------------------------------
    // Problem examples (individual round cases)
    // -------------------------------------------------------------------------

    @ParameterizedTest(name = "round({0}) = {1}")
    @CsvSource({
        "84, 85",   // 85 - 84 = 1 < 3 → round up
        "29, 29",   // below 38 → no round
        "57, 57",   // 60 - 57 = 3, NOT < 3 → no round
        "73, 75",   // 75 - 73 = 2 < 3 → round up
        "38, 40",   // 40 - 38 = 2 < 3 → round up
        "67, 67",   // 70 - 67 = 3, NOT < 3 → no round
        "33, 33",   // below 38 → no round
        "83, 85",   // 85 - 83 = 2 < 3 → round up
        "59, 60",   // 60 - 59 = 1 < 3 → round up
        "43, 45",   // 45 - 43 = 2 < 3 → round up
        "51, 51",   // 55 - 51 = 4, NOT < 3 → no round
        "62, 62",   // 65 - 62 = 3, NOT < 3 → no round
        "47, 47",   // 50 - 47 = 3, NOT < 3 → no round
        "12, 12",   // below 38 → no round
        "37, 37"    // below 38 → no round (boundary: 37 < 38)
    })
    @DisplayName("Parameterized single-grade round cases")
    void singleGradeRoundCases(int input, int expected) {
        List<Integer> result = Solution.gradingStudents(Arrays.asList(input));
        assertEquals(expected, result.get(0));
    }

    // -------------------------------------------------------------------------
    // Sample cases from problem
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Sample Case 0: [73, 67, 38, 33] → [75, 67, 40, 33]")
    void sampleCase0() {
        List<Integer> grades  = Arrays.asList(73, 67, 38, 33);
        List<Integer> expected = Arrays.asList(75, 67, 40, 33);
        assertEquals(expected, Solution.gradingStudents(grades));
    }

    @Test
    @DisplayName("Sample Case 1: [43, 51, 62, 47, 12, 37, 83, 59] → [45, 51, 62, 47, 12, 37, 85, 60]")
    void sampleCase1() {
        List<Integer> grades   = Arrays.asList(43, 51, 62, 47, 12, 37, 83, 59);
        List<Integer> expected = Arrays.asList(45, 51, 62, 47, 12, 37, 85, 60);
        assertEquals(expected, Solution.gradingStudents(grades));
    }

    // -------------------------------------------------------------------------
    // Edge cases
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Grade exactly 38: next multiple is 40 (diff=2 < 3) → rounds to 40")
    void gradeExactly38() {
        assertEquals(40, Solution.gradingStudents(Arrays.asList(38)).get(0));
    }

    @Test
    @DisplayName("Grade 37: below 38 threshold → never rounded")
    void gradeJustBelow38() {
        assertEquals(37, Solution.gradingStudents(Arrays.asList(37)).get(0));
    }

    @Test
    @DisplayName("Grade exactly at a multiple of 5 (e.g. 85): diff=0 < 3 → stays 85")
    void gradeAlreadyMultipleOf5() {
        assertEquals(85, Solution.gradingStudents(Arrays.asList(85)).get(0));
    }

    @Test
    @DisplayName("Grade where diff == 3 exactly (e.g. 57): NOT rounded (condition is strictly < 3)")
    void gradeWithDiffExactly3() {
        assertEquals(57, Solution.gradingStudents(Arrays.asList(57)).get(0));
    }

    @Test
    @DisplayName("Max grade 100: already multiple of 5, diff=0 → stays 100")
    void gradeMaxValue() {
        assertEquals(100, Solution.gradingStudents(Arrays.asList(100)).get(0));
    }
}
