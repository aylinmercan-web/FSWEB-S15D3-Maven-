package org.example;

import org.example.entity.Employee;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        List<Employee> employees = new LinkedList<>();
        employees.add(new Employee(1, "Ayse", "Yilmaz"));
        employees.add(new Employee(1, "Ayse", "Yilmaz"));
        employees.add(new Employee(2, "Mehmet", "Demir"));
        employees.add(new Employee(2, "Mehmet", "Demir"));
        employees.add(new Employee(3, "Zeynep", "Kaya"));
        employees.add(new Employee(3, "Zeynep", "Kaya"));
        employees.add(new Employee(4, "Can", "Ozturk"));

        System.out.println("Duplicates: " + findDuplicates(employees));
        System.out.println("Uniques:    " + findUniques(employees));
        System.out.println("Removed:    " + removeDuplicates(employees));
        System.out.println("Words:      " + WordCounter.calculateWord());
    }

    /**
     * Tekrar eden employee'leri döner (her tekrar eden kayıttan bir tane).
     */
    public static List<Employee> findDuplicates(List<Employee> employees) {
        Map<Integer, Employee> seen = new HashMap<>();
        Map<Integer, Employee> duplicates = new LinkedHashMap<>();

        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }
            if (seen.containsKey(employee.getId())) {
                duplicates.putIfAbsent(employee.getId(), employee);
            } else {
                seen.put(employee.getId(), employee);
            }
        }
        return new LinkedList<>(duplicates.values());
    }

    /**
     * Her id'den yalnızca bir employee içeren map döner
     * (tekrar edenlerden bir tane + hiç tekrar etmeyenler).
     */
    public static Map<Integer, Employee> findUniques(List<Employee> employees) {
        Map<Integer, Employee> uniques = new HashMap<>();

        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }
            uniques.putIfAbsent(employee.getId(), employee);
        }
        return uniques;
    }

    /**
     * Birden fazla kez geçen kayıtların tamamını siler,
     * yalnızca tek geçen kayıtları döner.
     */
    public static List<Employee> removeDuplicates(List<Employee> employees) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }
            counts.merge(employee.getId(), 1, Integer::sum);
        }

        List<Employee> result = new LinkedList<>();
        for (Employee employee : employees) {
            if (employee != null && counts.get(employee.getId()) == 1) {
                result.add(employee);
            }
        }
        return result;
    }
}
