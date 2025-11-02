public class Problem2 {
    
    public static void main(String[] args) {

        Node node = new Node().createdNode();
        System.out.println("Reversed Linked List:");
        Node reversedHead = node.reversePrintMethod(node);
        reversedHead.printList();
    }
}
