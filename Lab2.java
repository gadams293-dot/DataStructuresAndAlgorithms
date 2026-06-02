import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Iterator;

// Lab 2 - Lists, Stacks, and Queues
// Your Name: 
// Notes: The instructions are found in comments through the code starting with "Step n"
//        Please leave the comments and add your code just after the comment.
//        Use the examples in the book to complete the lab.

class Main {
  public static void main(String[] args) {
    Lab2 lab = new Lab2();
    // Comment/Uncomment the following 3 method calls as you work on each method.
    // No code should be added to the main method. All of your code will go into the methods in Lab2.
    lab.linkedList();
    lab.queue();
    lab.stack();
  }
}

class Lab2 {

  public void linkedList() {
    // 1. Create a LinkedList<String> object called progLanguages
    LinkedList<String> progLanguages = new LinkedList<>();

    // 2. Add elements to progLanguages: "Java", "Python", "JavaScript", "C++"
    progLanguages.add("Java");
    progLanguages.add("Python");
    progLanguages.add("JavaScript");
    progLanguages.add("C++");

    // 3. Remove the element "C++" from the list using .remove()
    progLanguages.remove("C++");

    // 4. Add an element "HTML" at index 2.
    progLanguages.add(2, "HTML");

    // 5. Iterate over progLanguages using Iterator
    Iterator<String> iterator = progLanguages.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
  
  public void queue() {
    // 6. Create a Queue<String> called q
    Queue<String> q = new LinkedList<>();

    // 7. Add 5 first names to q.
    q.add("Alice");
    q.add("Bob");
    q.add("Chris");
    q.add("Diana");
    q.add("Evan");

    // 8. Uncomment the following line
    System.out.println("Elements of queue: " + q);

    // 9. Remove the head of the queue and assign it to removedElement
    String removedElement = q.remove();
    System.out.println("Removed element: " + removedElement);

    // 10. View the head of the queue using peek(). Output its value.
    System.out.println("Head of queue: " + q.peek());

    // 11. Output all values in the queue using for-each loop
    for (String element : q) {
      System.out.println(element);
    }
  }

  public void stack() {
    // 12. Create a Stack<String> called bookStack
    Stack<String> bookStack = new Stack<>();

    // 13. Push book titles
    bookStack.push("Clean Code");
    bookStack.push("Design Patterns");
    bookStack.push("Pragmatic Programmer");

    // 14. pop() 1 book off the stack. Display its value
    String popped = bookStack.pop();
    System.out.println("Popped book: " + popped);

    // 15. View the top book using peek()
    System.out.println("Top of stack: " + bookStack.peek());

    // 16. push() "Web DB Technologies"
    bookStack.push("Web DB Technologies");

    // 17. View the top book again
    System.out.println("Top of stack: " + bookStack.peek());

    // 18. Search for "Design Patterns"
    int position = bookStack.search("Design Patterns");
    System.out.println("Search result for 'Design Patterns': " + position);

    // 19. Call empty(). Output results
    System.out.println("Is stack empty? " + bookStack.empty());

    // 20. Print all book titles on the stack
    for (String book : bookStack) {
      System.out.println(book);
    }
  }
}
