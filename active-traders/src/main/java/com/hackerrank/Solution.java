package com.hackerrank;

import java.util.*;

public class Solution {

    public static List<String> mostActive(List<String> customers) {
        int total = customers.size();

        // Contar trades por cliente
        Map<String, Integer> freq = new HashMap<>();
        for (String customer : customers) {
            freq.put(customer, freq.getOrDefault(customer, 0) + 1);
        }

        // Filtrar los que tienen >= 5% del total
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            // Usamos multiplicación para evitar decimales: count/total >= 0.05 => count*100 >= total*5
            if (entry.getValue() * 100 >= total * 5) {
                result.add(entry.getKey());
            }
        }

        // Ordenar alfabéticamente
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== Example from problem ===");
        List<String> c0 = Arrays.asList(
            "Bigcorp","Bigcorp","Acme","Bigcorp","Zork","Zork","Abc","Bigcorp",
            "Acme","Bigcorp","Bigcorp","Zork","Bigcorp","Zork","Zork","Bigcorp",
            "Acme","Bigcorp","Acme","Bigcorp","Acme","Littlecorp","Nadircorp"
        );
        System.out.println("Result:   " + mostActive(c0));
        System.out.println("Expected: [Acme, Bigcorp, Zork]");

        System.out.println("\n=== Sample Case 0 ===");
        List<String> c1 = Arrays.asList(
            "Omega","Alpha","Omega","Alpha","Omega","Alpha","Omega","Alpha","Omega","Alpha",
            "Omega","Alpha","Omega","Alpha","Omega","Alpha","Omega","Alpha","Omega","Beta"
        );
        System.out.println("Result:   " + mostActive(c1));
        System.out.println("Expected: [Alpha, Beta, Omega]");

        System.out.println("\n=== Sample Case 1 ===");
        List<String> c2 = Arrays.asList(
            "Alpha","Beta","Zeta","Beta","Zeta","Zeta","Epsilon","Beta","Zeta","Beta",
            "Zeta","Beta","Delta","Zeta","Beta","Zeta","Beta","Zeta","Beta","Zeta","Beta"
        );
        System.out.println("Result:   " + mostActive(c2));
        System.out.println("Expected: [Beta, Zeta]");
    }
}
