import java.util.*;
import java.util.stream.*;

class Employee {
    String name;
    int age;
    double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " | Age: " + age + " | Salary: " + salary;
    }
}

class Student {
    String name;
    double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
}

class Product {
    String name;
    double price;
    String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return name + " | " + category + " | " + price;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n========= JAVA LAMBDA & STREAM DEMO =========");
            System.out.println("1. Sort Employees using Lambda Expressions");
            System.out.println("2. Filter and Sort Students using Streams");
            System.out.println("3. Stream Operations on Product Dataset");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sortEmployees();
                    break;
                case 2:
                    filterAndSortStudents();
                    break;
                case 3:
                    productStreamOperations();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);
        sc.close();
    }

    // ===== Part (a) Sorting Employee Objects =====
    public static void sortEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ravi", 28, 40000));
        employees.add(new Employee("Anita", 25, 50000));
        employees.add(new Employee("Karan", 32, 35000));

        System.out.println("\nOriginal Employee List:");
        employees.forEach(System.out::println);

        // Sort by Name
        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));
        System.out.println("\nSorted by Name:");
        employees.forEach(System.out::println);

        // Sort by Age
        employees.sort((e1, e2) -> Integer.compare(e1.age, e2.age));
        System.out.println("\nSorted by Age:");
        employees.forEach(System.out::println);

        // Sort by Salary Descending
        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        System.out.println("\nSorted by Salary (Descending):");
        employees.forEach(System.out::println);
    }

    // ===== Part (b) Filtering and Sorting Students =====
    public static void filterAndSortStudents() {
        List<Student> students = Arrays.asList(
                new Student("Rohit", 82),
                new Student("Sneha", 65),
                new Student("Kavya", 91),
                new Student("Amit", 74),
                new Student("Divya", 88));

        System.out.println("\nStudents scoring above 75% (sorted by marks):");
        students.stream()
                .filter(s -> s.marks > 75)
                .sorted(Comparator.comparingDouble(s -> s.marks))
                .map(s -> s.name + " (" + s.marks + "%)")
                .forEach(System.out::println);
    }

    // ===== Part (c) Stream Operations on Products =====
    public static void productStreamOperations() {
        List<Product> products = Arrays.asList(
                new Product("Laptop", 75000, "Electronics"),
                new Product("Headphones", 2500, "Electronics"),
                new Product("Refrigerator", 50000, "Appliances"),
                new Product("Mixer", 3500, "Appliances"),
                new Product("T-shirt", 1200, "Clothing"),
                new Product("Jeans", 2200, "Clothing"));

        // Grouping by Category
        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));

        System.out.println("\nProducts Grouped by Category:");
        grouped.forEach((cat, list) -> {
            System.out.println("\n" + cat + ":");
            list.forEach(System.out::println);
        });

        // Most Expensive Product in Each Category
        Map<String, Optional<Product>> maxPriceByCategory = products.stream()
                .collect(Collectors.groupingBy(p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.price))));

        System.out.println("\nMost Expensive Product in Each Category:");
        maxPriceByCategory.forEach((cat, prod) ->
                System.out.println(cat + ": " + prod.get()));

        // Average Price of All Products
        double avgPrice = products.stream()
                .collect(Collectors.averagingDouble(p -> p.price));

        System.out.println("\nAverage Price of All Products: " + avgPrice);
    }
}
