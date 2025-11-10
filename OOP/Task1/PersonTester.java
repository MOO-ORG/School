package OOP.Task1;

import OOP.Helper;

public class PersonTester {
    public static void main(String[] args) {
        Person person = new Person("Alice", 30, "USA");

        Helper.organize("Previous information");

        // Test getters
        System.out.println("Name: " + person.getName()); // Expected: Alice
        System.out.println("Age: " + person.getAge());   // Expected: 30
        System.out.println("Country: " + person.getCountry()); // Expected: USA

        
        Helper.organize("Updated information");
        
        // Test setters
        person.setName("Bob");
        person.setAge(25);
        person.setCountry("Canada");
        
        System.out.println("Updated Name: " + person.getName()); // Expected: Bob
        System.out.println("Updated Age: " + person.getAge());   // Expected: 25
        System.out.println("Updated Country: " + person.getCountry()); // Expected: Canada
    }
}
