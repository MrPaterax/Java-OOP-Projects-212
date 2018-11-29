abstract class Date212List {
	Date212Node head; //Our head and tail nodes for our Date212 Objects.
	Date212Node tail;
	int length; //How many nodes are in our list. 
	Date212 dummyNode = new Date212("0000000"); //Dummy date212 object for our dummy node. 
	
	public Date212List() {
		head = new Date212Node(dummyNode);
		tail = head; // Set our head & tail to point to the same Dummy Node object.
		length = 0;
	}

	void append(Date212 date) {
		Date212Node node = new Date212Node(date); //pass in our Date212 object to create a new Date212Node
		tail.next = node;
		tail = node; 
		length++; // When a new node is added, we increase the length of how many nodes are in our List
	}	
} 