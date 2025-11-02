
public class Node {

    public Object data;
    public Node next;

    public Node(Object something) {
        this.data = something;
        this.next = null;
    }

    public Node(){
    
    }

    public Node createdNode(){
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = new Node(50);
        return head;
    }

    public int nodeLength() {
        if (this.next == null) {
            return 1;
        }

        int length = 0;
        Node current = this;

        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    public Object dataAtNode(int n) {
        if (n < 1) {
            return null;
        }

        Node temp = this;
        int count = 1;

        while (temp != null && count < n) {
            temp = temp.next;
            count++;
        }

        return (temp != null) ? temp.data : null;
    }

    public void printList() {
        Node temp = this;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    
    public Node reversePrintMethod(Node head){
    Node temp = head, prev = null, next;

        // Traverse all the nodes of Linked List
        while (temp != null) {

            // Store next
            next = temp.next;

            // Reverse current node's next pointer
            temp.next = prev;

            // Move pointers one position ahead
            prev = temp;
            temp = next;
        }

        return prev;
    }
}