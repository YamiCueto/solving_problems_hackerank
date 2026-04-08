package com.hackerrank;

import java.util.*;

public class Solution {

    public static List<Integer> gradingStudents(List<Integer> grades) {
        List<Integer> result = new ArrayList<>();
        for (int grade : grades) {
            result.add(round(grade));
        }
        return result;
    }

    private static int round(int grade) {
        // Grados menores a 38 no se redondean (seguirían reprobando)
        if (grade < 38) {
            return grade;
        }
        // Siguiente múltiplo de 5
        int nextMultipleOf5 = (int) (Math.ceil(grade / 5.0) * 5);
        // Si la diferencia es menor a 3, redondear hacia arriba
        if (nextMultipleOf5 - grade < 3) {
            return nextMultipleOf5;
        }
        return grade;
    }

    public static void main(String[] args) {
        System.out.println("=== Examples from problem ===");
        System.out.println(round(84) + " (expected 85)");  // 85-84=1 < 3 → round
        System.out.println(round(29) + " (expected 29)");  // < 38 → no round
        System.out.println(round(57) + " (expected 57)");  // 60-57=3, NOT < 3 → no round

        System.out.println("\n=== Sample Case 0 ===");
        List<Integer> grades0 = Arrays.asList(73, 67, 38, 33);
        System.out.println("Result:   " + gradingStudents(grades0));
        System.out.println("Expected: [75, 67, 40, 33]");

        System.out.println("\n=== Sample Case 1 ===");
        List<Integer> grades1 = Arrays.asList(43, 51, 62, 47, 12, 37, 83, 59);
        System.out.println("Result:   " + gradingStudents(grades1));
        System.out.println("Expected: [45, 51, 62, 47, 12, 37, 85, 60]");
    }
}
