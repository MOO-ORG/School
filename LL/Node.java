// A simple Node class for a singly linked list
public class Node {
    Object data;
    Node next;

    // Constructor to create a new node
    public Node(Object data) {
        this.data = data;
        this.next = null;
    }

    // Initialize this node with the provided data and attach it after head
    public Node(Node head, Object data) {
        this.data = data;
        this.next = null;
        if (head != null) {
            head.next = this;
        }
    }

    // Method to display the linked list by Iterating through nodes
    public static void displayNode(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null) {
                System.out.print(" -> ");
            }
            temp = temp.next;
        }
        System.out.println();
    }

    // Method to display the linked list using Recursion
    public static void displayNodeRecursive(Node head) {
        if (head == null) {
            System.out.println();
            return;
        }
        System.out.print(head.data);
        if (head.next != null) {
            System.out.print(" -> ");
        }
        displayNodeRecursive(head.next);
    }

    // Method to insert a new node at the beginning
    public static Node insertNodeAtBeginning(Node head, Object something) {
        Node newNode = new Node(something);
        newNode.next = head;
        return newNode;
    }

    // Method to insert a new node at the end
    public static Node insertNodeAtEnd(Node head, Object something) {
        Node newNode = new Node(something);
        if (head == null) {
            return newNode;
        }
        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        return head;
    }

    // Method to insert a node at a specific position
    public static Node insertNodeAtPosition(Node head, Object something, int position) {

        if (position < 1) {
            return head; // Invalid position
        }

        Node temp = head;

        if (position == 1) {
            Node newNode = new Node(something);
            newNode.next = temp;
            return newNode;
        }

        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            return head; // Position is greater than the length of the list
        }

        Node newNode = new Node(something);
        newNode.next = temp.next;
        temp.next = newNode;
        return head;
    }

    //Method to delete a node at the beginning
    public static Node deleteNodeAtBeginning(Node head) {
        if (head == null) {
            return null; // List is empty
        }
        return head.next; // New head is the next node
    }

    //Method to delete a node at the end
    public static Node deleteNodeAtEnd(Node head) {
        if (head == null) {
            return null; // List is empty
        }
        if (head.next == null) {
            return null; // List has only one node
        }
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null; // Remove the last node
        return head;
    }

    //Method to delete a node at a specific position
    public static Node deleteNodeAtPosition(Node head, int position) {
        if (head == null || position < 1) {
            return head; // List is empty or invalid position
        }
        if (position == 1) {
            return head.next; // New head is the next node
        }
        Node temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            return head; // Position is greater than the length of the list
        }
        temp.next = temp.next.next; // Bypass the node to be deleted
        return head;
    }

    //Search for a node with specific data
    public static boolean searchNode(Node head, Object something) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.equals(something)) {
                return true; // Node found
            }
            temp = temp.next;
        }
        return false; // Node not found
    }

    //search for a node with specific data recursively
    public static boolean searchNodeRecursive(Node head, Object something) {
        if (head == null) {
            return false; // Base case: reached end of list
        }
        if (head.data.equals(something)) {
            return true; // Node found
        }
        return searchNodeRecursive(head.next, something); // Recur for the next node
    }

    //reverse the linked list\\
    public static Node reverseLinkedList(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.next; // Store next node
            current.next = prev; // Reverse current node's pointer
            prev = current; // Move pointers one position ahead
            current = next;
        }
        return prev; // New head of the reversed list
    }

    //reverse the linked list recursively
    public static Node reverseLinkedListRecursive(Node head) {
        if (head == null || head.next == null) {
            return head; // Base case: empty list or single node
        }
        Node newHead = reverseLinkedListRecursive(head.next); // Recur for the rest of the list
        head.next.next = head; // Make the next node point to the current node
        head.next = null; // Set the current node's next to null
        return newHead; // New head of the reversed list
    }


    public static void main(String[] args) {

        // Create a node
        Node head = new Node(10);
        Node newNode = new Node(head, 20);
        Node anotherNode = new Node(newNode, 30);

        System.out.println("===== Display Node =====");
        // Display the linked list
        displayNode(head);

        System.out.println("===== Display Node at Beginning =====");
        // Insert a new node at the beginning
        head = insertNodeAtBeginning(head, 5);
        displayNode(head);

        System.out.println("===== Display Node at End =====");
        // Insert a new node at the end
        head = insertNodeAtEnd(head, 42);
        displayNode(head);

        System.out.println("===== Display Node at Position 2 =====");
        head = insertNodeAtPosition(head, 25, 2);
        displayNode(head);

        System.out.println("===== Display Node after Deleting at Beginning =====");
        head = deleteNodeAtBeginning(head);
        displayNode(head);

        System.out.println("===== Display Node after Deleting at End =====");
        head = deleteNodeAtEnd(head);
        displayNode(head);

        System.out.println("===== Display Node after Deleting at Position 2 =====");
        head = deleteNodeAtPosition(head, 2);
        displayNode(head);

        System.out.println("===== Search Node for 25 =====");
        boolean found = searchNode(head, 25);
        System.out.println("Node found: " + found);
    }
}
