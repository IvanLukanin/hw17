package com.example.hw17.service;

import com.example.hw17.Employee;
import com.example.hw17.exceptions.FullMapException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    private Map<String, Employee> employees = new LinkedHashMap<>(Map.of(
            "Гребенщикова Екатерина Владимировна",
            new Employee("Гребенщикова Екатерина Владимировна", 85_000, 5),
            "Луканин Иван Сергеевич",
            new Employee("Луканин Иван Сергеевич", 87_000, 2),
            "Альтергот Никита Анатольевич",
            new Employee("Альтергот Никита Анатольевич", 65_000, 1),
            "Максимов Максим Максимович",
            new Employee("Максимов Максим Максимович", 90_000, 3),
            "Попов Александр Семенович",
            new Employee("Попов Александр Семенович", 67_000, 4),
            "Обрезов Николай Петрович",
            new Employee("Обрезов Николай Петрович", 63_000, 4),
            "Молитославский Богдан Александрович",
            new Employee("Молитославский Богдан Александрович", 99_000, 2),
            "Григорьев Никита Дмитриевич",
            new Employee("Григорьев Никита Дмитриевич", 72_000, 1),
            "Юдин Анатолий Владимирович",
            new Employee("Юдин Анатолий Владимирович", 76_000, 5),
            "Долболюбов Евпатий Коловратович",
            new Employee("Долболюбов Евпатий Коловратович", 75_000, 3)
    ));


    public StringBuilder printEmployees() {
        StringBuilder rezString = new StringBuilder("");
        if (employees.isEmpty()) {
            return rezString.append("интерфейс Map не содержит ни одного сотрудника");
        }
        employees.values().stream()
                .forEach(employee -> rezString.append("- " + employee.getFullName() +
                        ", зарплата: " + employee.getSalary() +
                        ", отдел: " + employee.getDept() + "\n"));
        return rezString;
    }


    public Employee addEmployee(String fullName, int salary, int dept) {
        Employee employee = new Employee(fullName, salary, dept);
        if (employees.size() > 20) {
            throw new FullMapException();
        }
        employees.put(fullName, employee);
        return employee;
    }

    public void removeEmployee(String fullName) {
        if (employees.containsKey(fullName)) {
            employees.remove(fullName);
        } else {
            throw new RuntimeException();
        }
    }


    public Map<String, Employee> getEmployees() {
        return employees;
    }

    public void changeEmployee(String fullNameDeletingEmployee,
                               String fullNameNewEmployee,
                               Integer newSalary,
                               Integer newDept) {
        if (employees.containsKey(fullNameDeletingEmployee)) {
            removeEmployee(fullNameDeletingEmployee);
            addEmployee(fullNameNewEmployee, newSalary, newDept);
        } else {
            throw new NullPointerException();
        }
    }