package org.example;

import java.util.*;


interface Relationship {
    void establish(Person person1, Person person2);
}

class FatherRelationship implements Relationship {
    @Override
    public void establish(Person person1, Person person2) {
        person1.addChild(person2);
    }
}

class ChildRelationship implements Relationship {
    @Override
    public void establish(Person person1, Person person2) {
        person2.addChild(person1);
    }
}

class SpouseRelationship implements Relationship {
    @Override
    public void establish(Person person1, Person person2) {
        person2.setSpouse(person1);
    }
}

class Person {
    private String name;
    private List<Person> children;
    private Person spouse;
    private Person parent;

    public Person(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public void setParent(Person parent) {
        this.parent = parent;
    }

    public void addChild(Person child) {
        children.add(child);
        child.setParent(this);
    }

    public int countSons() {
        int count = 0;
        for (Person child : children) {
            if (child != null && child.getParent() == this) {
                count++;
            }
        }
        return count;
    }

    public int countDaughters() {
        int count = 0;
        for (Person child : children) {
            if (child != null && child.getParent() == this) {
                count++;
            }
        }
        return count;
    }

    public int countWives() {
        if (spouse != null) {
            return 1;
        }
        return 0;
    }

    public String getFather() {
        if (parent != null) {
            return parent.getName();
        }
        return "Father not found";
    }

    public Person getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class FamilyTree {
    private static Map<String, Person> personMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter family-tree command (add person, add relationship, connect, query, exit): ");
            String command = scanner.nextLine();
            String[] tokens = command.split(" ");
            if (!Objects.equals(tokens[0], "family-tree")) {
                System.out.println("Invalid command");
                continue;
            }
            switch (tokens[1]) {
                case "add":
                    if (tokens.length < 4) {
                        System.out.println("Invalid command");
                        continue;
                    }
                    if (tokens[2].equals("person")) {
                        addPerson(tokens[3]);
                    } else if (tokens[2].equals("relationship")) {
                        addRelationship(tokens[3]);
                    } else {
                        System.out.println("Invalid command");
                    }
                    break;
                case "connect":
                    if (tokens.length != 7 || !tokens[3].equals("as") || !tokens[5].equals("of")) {
                        System.out.println("Invalid command");
                        continue;
                    }
                    connect(tokens[2], tokens[4], tokens[6]);
                    break;
                case "query":
                    query(tokens);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private static void addPerson(String name) {
        if (personMap.containsKey(name)) {
            System.out.println("Person already exists: " + name);
            return;
        }
        Person person = new Person(name);
        personMap.put(name, person);
        System.out.println("Person added: " + name);
    }

    private static void addRelationship(String relationship) {
        Set<String> validRelationships = getValidRelationships();
        if (validRelationships.contains(relationship.toLowerCase())) {
            System.out.println("Relationship added: " + relationship);
        } else {
            System.out.println("Invalid relationship");
        }
    }

    private static void connect(String name1, String relationship, String name2) {
        Person person1 = personMap.get(name1);
        Person person2 = personMap.get(name2);
        if (person1 == null || person2 == null) {
            System.out.println("Person not found");
            return;
        }

        Relationship relationshipType;
        switch (relationship.toLowerCase()) {
            case "father" -> relationshipType = new FatherRelationship();
            case "son", "daughter" -> relationshipType = new ChildRelationship();
            case "spouse" -> relationshipType = new SpouseRelationship();
            default -> {
                System.out.println("Invalid relationship");
                return;
            }
        }

        relationshipType.establish(person1, person2);
        System.out.println("Connected: " + name1 + " as " + relationship + " of " + name2);
    }

    private static void query(String[] tokens) {
        String queryType = tokens[2];
        String name = null;
        String entityType = null;
        if (queryType.equals("father")) {
            if (tokens.length < 5 && !tokens[3].equals("of")) {
                System.out.println("Invalid get query");
                return;
            }
            name = tokens[4];
            entityType = tokens[2];
        } else if (queryType.equals("count")) {
            if (tokens.length < 6 && !tokens[4].equals("of")) {
                System.out.println("Invalid count query");
                return;
            }
            name = tokens[5];
            entityType = tokens[3];
        }


        Person person = personMap.get(name);
        if (person == null) {
            System.out.println("Person not found");
            return;
        }

        switch (queryType) {
            case "count":
                switch (entityType) {
                    case "sons" -> System.out.println("Sons of " + name + ": " + person.countSons());
                    case "daughters" -> System.out.println("Daughters of " + name + ": " + person.countDaughters());
                    case "wives" -> System.out.println("Wives of " + name + ": " + person.countWives());
                    default -> System.out.println("Invalid query");
                }
                break;
            case "father":
                person = personMap.get(name);
                if (person != null) {
                    System.out.println("Father of " + name + ": " + person.getFather());
                } else {
                    System.out.println("Person not found");
                }
                break;
            default:
                System.out.println("Invalid query");
        }
    }

    private static Set<String> getValidRelationships() {
        return new HashSet<>(Arrays.asList("father", "son", "daughter", "spouse"));
    }

}
