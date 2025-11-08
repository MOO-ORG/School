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
        // If the list is empty, the new node is the head. There are no existing nodes.
        if (head == null) {
            return newNode;
        }

        Node temp = head;

        // Traverse to the end of the list
        // while(temp != null) does not work because temp will become null and we cannot
        // set null.next

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
            // Insert at the beginning
            Node newNode = new Node(something);
            newNode.next = temp;
            return newNode;
        }

        // Traverse to the node just before the desired position
        // Suppose position = 3, we need to stop at position 2
        // temp != null check is necessary to avoid NullPointerException
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }

        // If temp is null here, it means position is greater than the length of the list. Didn't reach position - 1.
        if (temp == null) {
            return head;
        }

        Node newNode = new Node(something);
        newNode.next = temp.next; // Link new node to the next node
        temp.next = newNode; // Link previous node to the new node
        return head;

        // PART 1
        /* Potential problems/Understandings in it
         * Here are concrete failure examples if you remove the post-loop null checks.

insertNodeAtPosition: temp becomes null
Scenario:
List: 10 -> 20 -> 30 (length = 3)
Call: insertNodeAtPosition(head, 99, 7)
for loop advances:
i=1 temp=20
i=2 temp=30
i=3 temp=null (end reached)
Loop stops because temp == null, not because i reached position - 1.
Without the if (temp == null) guard, next lines do:
newNode.next = temp.next // temp is null -> NullPointerException.

insertNodeAtPosition: head is null
Scenario:
head = null
Call: insertNodeAtPosition(null, 50, 2)
position != 1, temp = head = null
for loop does not run (temp == null immediately)
Without the null check, temp.next triggers NullPointerException.

Additionally for deleteNodeAtPosition, its combined check covers two distinct failure modes:

A) temp == null
List: 10 -> 20 (length = 2)
Call: deleteNodeAtPosition(head, 5)
Need predecessor of position 5 (position - 1 = 4). Traversal:
i=1 temp=20
i=2 temp=null (stop)
temp == null => position beyond list. Without check, temp.next would throw.

B) temp.next == null
List: 10 -> 20 -> 30 (length = 3)
Call: deleteNodeAtPosition(head, 5)
Traversal trying to reach predecessor (position - 1 = 4):
i=1 temp=20
i=2 temp=30
i=3 temp.next == null so loop stops (cannot advance)
Here temp is last node; temp.next is null; attempting temp.next.next would throw. Guard prevents that.

Minimal test harness to reproduce one insert failure:

===================== CODE SNIPPET =====================

public class TestInsertFail {
    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);

        // Simulate removal of the post-loop if (temp == null) check in insertNodeAtPosition
        // This call targets a position far beyond length.
        head = brokenInsert(head, 99, 7); // Expect NullPointerException
    }

    // Broken version without the safety check
    static Node brokenInsert(Node head, Object data, int position) {
        if (position < 1) return head;
        if (position == 1) {
            Node n = new Node(data);
            n.next = head;
            return n;
        }
        Node temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }
        // Missing: if (temp == null) return head;
        Node n = new Node(data);
        n.next = temp.next; // Will throw when temp == null
        temp.next = n;
        return head;
    }
}

===================== END OF SNIPPET ====================

Conclusion:
Those null checks are required to prevent NullPointerException when:

Requested position exceeds current length.
Head is null and position > 1.
In delete, when predecessor is last node (temp.next == null).
         */
    
        //PART 2
        /*
         * Here’s the explanation with clear examples.

Insertion logic (successful case):
Lines:
newNode.next = temp.next;
temp.next = newNode;
Order matters: you first point the new node to what used to come after temp, then hook temp to the new node. This preserves the remainder of the list.

Example 1 (valid position inside range):
List before: 10 -> 20 -> 30 -> 40
Insert 99 at position 3 (1-based)
Goal: after 20 (position 2), before 30.
Traversal:
i=1: temp = 10
Loop ends when i == position - 1 (2). temp now points to 20.
Linking:
newNode.next = temp.next (99 -> 30)
temp.next = newNode (20 -> 99)
Result: 10 -> 20 -> 99 -> 30 -> 40

Example 2 (append at end):
List: 5 -> 7 -> 9
Insert 11 at position 4 (length + 1)
Traversal stops with temp at last node (9).
newNode.next = temp.next (null)
temp.next = newNode
Result: 5 -> 7 -> 9 -> 11

Why the null check after the loop is required:
The for loop can stop for two reasons:

Reached the predecessor node (i completed).
temp became null early (position beyond list length).
If (2) happens and you skip the if (temp == null) return head; guard, dereferencing temp.next causes NullPointerException.

Failure Example 1 (position far too large):
List: 10 -> 20 -> 30 (length 3)
Insert 99 at position 7
Traversal:
i=1 temp=10
i=2 temp=20
i=3 temp=30
i=4 temp=null (end)
Loop exits because temp == null, not because i hit position - 1 (=6).
Without the null check:
newNode.next = temp.next // temp is null -> NullPointerException.

Failure Example 2 (empty list, position > 1):
head = null
Insert 50 at position 2
temp = null initially, loop body never runs.
temp still null -> same crash without guard.

(Edge) Why position == 1 is handled separately:
You must create a new head:
newNode.next = oldHead; return newNode;
Skipping the special case would mis-handle an empty list or overwrite via temp traversal logic.

Quick broken demo (without the safety check):

===================== CODE SNIPPET =====================

static Node brokenInsert(Node head, Object data, int position) {
    if (position == 1) {
        Node n = new Node(data);
        n.next = head;
        return n;
    }
    Node temp = head;
    for (int i = 1; i < position - 1 && temp != null; i++) {
        temp = temp.next;
    }
    // Missing: if (temp == null) return head;
    Node n = new Node(data);
    n.next = temp.next;   // <-- crashes when temp == null
    temp.next = n;
    return head;
}

===================== END OF SNIPPET ====================
Summary:

The post-loop null check prevents crashes when position is out of bounds or list is too short.
The two assignment lines safely splice the new node into the list.
Omitting the check leads to NullPointerException in the demonstrated failure scenarios.

         */   
        }

    // Method to delete a node at the beginning
    public static Node deleteNodeAtBeginning(Node head) {
        if (head == null) {
            return null; // List is empty
        }
        return head.next; // New head is the next node
    }

    // Method to delete a node at the end
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

    // Method to delete a node at a specific position
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

    // Search for a node with specific data
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

    // search for a node with specific data recursively
    public static boolean searchNodeRecursive(Node head, Object something) {
        if (head == null) {
            return false; // Base case: reached end of list
        }
        if (head.data.equals(something)) {
            return true; // Node found
        }
        return searchNodeRecursive(head.next, something); // Recur for the next node
    }

    // reverse the linked list\\
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

    // reverse the linked list recursively
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
