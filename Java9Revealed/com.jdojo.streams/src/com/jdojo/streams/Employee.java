// Employee.java
package com.jdojo.streams;

import java.util.List;

public class Employee {
    private String name;
    private String department;
    private double salary;
    private List<String> spokenLanguages;

    public Employee(String name, String department, double salary, 
                    List<String> spokenLanguages) {

        this.name = name;
        this.department = department;
        this.salary = salary;
        this.spokenLanguages = spokenLanguages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public List<String> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<String> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }
    
    @Override
    public String toString() {
        return "[" + name + ", " + department + ", " + salary + ", " + spokenLanguages + "]";
    }
    
    public static List<Employee> employees() {
        return List.of(
                new Employee("John", "Sales", 1000.89, List.of("English", "French")),
                new Employee("Wally", "Sales", 900.89, List.of("Spanish", "Wu")),
                new Employee("Ken", "Sales", 1900.00, List.of("English", "French")),
                new Employee("Li", "HR", 1950.89, List.of("Wu", "Lao")),
                new Employee("Manuel", "IT", 2001.99, List.of("English", "German")),
                new Employee("Tony", "IT", 1700.89, List.of("English"))
        );
    } 
}
