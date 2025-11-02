public class Problem1 {

	// Find the middle node of a singly linked list
	// If odd number of nodes: return the middle node
	// If even number of nodes: return the second middle node

	public static void main(String[] args) {

		// Test case 1: Odd number of nodes (5 nodes)
		System.out.println("Test Case 1: Odd number of nodes (5 nodes)");

		Node head1 = new Node(10);
		head1.next = new Node(20);
		head1.next.next = new Node(30);
		head1.next.next.next = new Node(40);
		head1.next.next.next.next = new Node(50);

		int middle = ((head1.nodeLength() / 2) + 1);
		System.out.println("Middle node length: " + middle);

		int middleData = (Integer) head1.dataAtNode(middle);
		System.out.println("Data at middle node: " + middleData);

		// Compare values correctly: dataAtNode returns Object (Integer here), so use
		// equals on the same type
		Checker checker1 = new Checker(30, middleData);


		// Test case 2: Even number of nodes (6 nodes)
		System.out.println("Test Case 2: Even number of nodes (6 nodes)");

		Node head2 = new Node(10);
		head2.next = new Node(20);
		head2.next.next = new Node(30);
		head2.next.next.next = new Node(40);
		head2.next.next.next.next = new Node(50);
		head2.next.next.next.next.next = new Node(60);

		middle = ((head2.nodeLength() / 2) + 1);
		System.out.println("Middle node length: " + middle);

		middleData = (Integer) head2.dataAtNode(middle);
		System.out.println("Data at middle node: " + middleData);

		// Compare values correctly: dataAtNode returns Object (Integer here), so use
		// equals on the same type
		Checker checker = new Checker(40, middleData);


		// // Test case 3: Single node
		System.out.println("Test Case 3: Single node");

		Node head3 = new Node(100);

		middle = ((head3.nodeLength() / 2) + 1);
		System.out.println("Middle node length: " + middle);

		middleData = (Integer) head3.dataAtNode(middle);
		System.out.println("Data at middle node: " + middleData);

		// Compare values correctly: dataAtNode returns Object (Integer here), so use
		// equals on the same type
		Checker checker3 = new Checker(100, middleData);
	}
}