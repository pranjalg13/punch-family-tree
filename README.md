**Family Tree Command Line System (Pranjal Goyal)**

This is a command line tool for managing a family tree. It allows users to add persons, define relationships between them, and query information about the family tree.

### Usage
To use the family tree command line tool, you can enter the following commands:

- `family-tree add person <name>`: Adds a person to the family tree.
- `family-tree add relationship <relationship>`: Adds a relationship type to the system.
- `family-tree connect <name 1> as <relationship> of <name 2>`: Connects two persons with a specified relationship.
- `family-tree query count <entity> of <name>`: Counts the specified entity (sons, daughters, wives) of a person.
- `family-tree query father of <name>`: Retrieves the father of a person.
- `family-tree exit`: Exits the command line tool.

### Example
```
Enter family-tree command (add person, add relationship, connect, query, exit): 
family-tree add person alok
Person added: alok
Enter family-tree command (add person, add relationship, connect, query, exit): 
family-tree add person amit
Person added: amit
Enter family-tree command (add person, add relationship, connect, query, exit): 
family-tree add person alok
Person already exists: alok
Enter family-tree command (add person, add relationship, connect, query, exit): 
family-tree add relationship father
Relationship added: father
Enter family-tree command (add person, add relationship, connect, query, exit): 
family-tree add relationship mnafwef
Invalid relationship
Enter family-tree command (add person, add relationship, connect, query, exit): 
family-tree connect amit as father of alok
Connected: amit as father of alok
Enter family-tree command (add person, add relationship, connect, query, exit): 
family-tree query count sons of amit
Sons of amit: 1
Enter family-tree command (add person, add relationship, connect, query, exit): 
family-tree query count sons of alok
Sons of alok: 0
Enter family-tree command (add person, add relationship, connect, query, exit): 
family-tree query count sons of ami33
Person not found
Enter family-tree command (add person, add relationship, connect, query, exit): 
family-tree query father of amit
Father of amit: Father not found
Enter family-tree command (add person, add relationship, connect, query, exit): 
family-tree query father of alok
Father of alok: amit
Enter family-tree command (add person, add relationship, connect, query, exit): 
exit
Invalid command
Enter family-tree command (add person, add relationship, connect, query, exit): 
family-tree exit

Process finished with exit code 0
```

In this example, we added persons named "alok" and "amit", defined a relationship "father", connected "amit" as the father of "alok", and queried for counts and father information.