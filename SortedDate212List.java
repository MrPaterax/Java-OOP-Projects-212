class SortedDate212List extends Date212List {
	public SortedDate212List() { }; //Default constructor

	void add(Date212 date) {
		Date212Node currentNode = new Date212Node(date);
		Date212Node nodeTracker = head; //used to keep track of our nodes while looping to sort our dates		

		while(nodeTracker.next != null) { //We skip this while loop if it's the beginning of our loop
			if (Integer.parseInt(currentNode.data.date) < Integer.parseInt(nodeTracker.next.data.date)) {
				currentNode.next = nodeTracker.next; //If our current node is smaller than the next, we point our next node from currentNode to the bigger node from nodeTracker
				nodeTracker.next = currentNode; //we now point nodetrackers next node to currentnode since curerntNode is greater than nodeTracker. 
				length++;
				break; //Break our loop once we have found a sufficient smaller date
			}
			nodeTracker = nodeTracker.next;
		}
		
		if(head.next == null) append(date); //at our beginning of our list, we append
		
		/*
		 *If we go through the whole loop and have not found
		 *A sufficient smaller date 
		 *we just add it to the end.
		*/
		if (nodeTracker.next == null) append(date); 
	}
}