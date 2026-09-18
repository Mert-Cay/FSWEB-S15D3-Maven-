package org.example;

import org.example.entity.Employee;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new LinkedList<>();
        employees.add(new Employee(1, "Joe", "Daro"));
        employees.add(new Employee(2, "Marrie", "Johnson"));
        employees.add(new Employee(3, "Tom", "Hardy"));
        employees.add(new Employee(4, "Obama", "Daro"));
        employees.add(new Employee(3, "Mert", "Çay"));

        System.out.println("--- Tekrar Edenler (findDuplicates) ---");
        System.out.println(findDuplicates(employees));

        System.out.println("\n--- Benzersiz Harita (findUniques) ---");
        System.out.println(findUniques(employees));

        System.out.println("\n--- Tekrarı Tamamen Silinmişler (removeDuplicates) ---");
        System.out.println(removeDuplicates(employees));
    }

    /**
     * Tekrar eden employeeleri bulup listeye ekler ve döner.
     */
    public static List<Employee> findDuplicates(List<Employee> list) {
        List<Employee> duplicates = new LinkedList<>();
        Set<Employee> seen = new HashSet<>();

        for (Employee emp : list) {
            // emp daha önce görülmüşse seen.add() false döner
            if (!seen.add(emp)) {
                if (!duplicates.contains(emp)) {
                    duplicates.add(emp);
                }
            }
        }
        return duplicates;
    }

    /**
     * Tekrar edenlerden sadece bir tanesini ve hiç tekrar etmeyenleri
     * id'ye karşılık Employee olacak şekilde bir Map'e ekler.
     */
    public static Map<Integer, Employee> findUniques(List<Employee> list) {
        Map<Integer, Employee> uniqueMap = new HashMap<>();
        if (list == null) return uniqueMap;

        Set<Employee> seen = new HashSet<>();
        int index = 0;

        for (Employee emp : list) {
            // Tekrar etmeyen veya ilk kez görülen elemanı index ile ekle
            if (emp != null && seen.add(emp)) {
                uniqueMap.put(index++, emp);
            }
        }
        return uniqueMap;
    }

    /**
     * Listede birden fazla kez geçen kayıtların tamamını siler.
     * Sadece listede tam olarak 1 kez bulunanları listeler.
     */
    public static List<Employee> removeDuplicates(List<Employee> list) {
        // Her çalışanın kaç kez geçtiğini say
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Employee emp : list) {
            if (emp != null) {
                countMap.put(emp.getId(), countMap.getOrDefault(emp.getId(), 0) + 1);
            }
        }

        // Sadece frekansı 1 olanları yeni listeye ekle
        List<Employee> pureUniques = new LinkedList<>();
        for (Employee emp : list) {
            if (emp != null && countMap.get(emp.getId()) == 1) {
                pureUniques.add(emp);
            }
        }
        return pureUniques;
    }
}