// package timingtest;
//
///** An SLList is a list of integers, which hides the terrible truth
// * of the nakedness within. */
//public class SLList<Item> {
//	private class IntNode {
//		public Item item;
//		public IntNode next;
//
//		public IntNode(Item i, IntNode n) {
//			item = i;
//			next = n;
//		}
//	}
//
//	/* The first item (if it exists) is at sentinel.next. */
//	private IntNode sentinel;
//	private int size;
//
//	/** Creates an empty timingtest.SLList. */
//	public SLList() {
//		sentinel = new IntNode(null, null);
//		size = 0;
//	}
//
//	public SLList(Item x) {
//		sentinel = new IntNode(null, null);
//		sentinel.next = new IntNode(x, null);
//		size = 1;
//	}
//
//	/** Adds x to the front of the list. */
//	public void addFirst(Item x) {
//		sentinel.next = new IntNode(x, sentinel.next);
//		size = size + 1;
//	}
//
//	/** Returns the first item in the list. */
//	public Item getFirst() {
//		return sentinel.next.item;
//	}
//
//	/** Adds x to the end of the list. */
//	public void addLast(Item x) {
//		size = size + 1;
//
//		IntNode p = sentinel;
//
//		/* Advance p to the end of the list. */
//		while (p.next != null) {
//			p = p.next;
//		}
//
//		p.next = new IntNode(x, null);
//	}
//
//	/** returns last item in the list */
//	public Item getLast() {
//		IntNode p = sentinel;
//
//		/* Advance p to the end of the list. */
//		while (p.next != null) {
//			p = p.next;
//		}
//
//		return p.item;
//	}
//
//
//	/** Returns the size of the list. */
//	public int size() {
//		return size;
//	}
//
//	public static void main(String[] args) {
//		/* Creates a list of one integer, namely 10 */
//		SLList L = new SLList();
//		L.addLast(20);
//		System.out.println(L.size());
//	}
//}

package timingtest;

/** An SLList is a list of integers, which hides the terrible truth
 * of the nakedness within. */
public class SLList<Item> {
	private class IntNode {
		public Item item;
		public IntNode next;

		public IntNode(Item i, IntNode n) {
			item = i;
			next = n;
		}
	}

	/* The first item (if it exists) is at sentinel.next. */
	private IntNode sentinel;
	private IntNode last; // Tail pointer to track the last node
	private int size;

	/** Creates an empty SLList. */
	public SLList() {
		sentinel = new IntNode(null, null);
		last = sentinel; // Initially, last points to sentinel
		size = 0;
	}

	public SLList(Item x) {
		sentinel = new IntNode(null, null);
		IntNode newNode = new IntNode(x, null);
		sentinel.next = newNode;
		last = newNode; // Last points to the newly added node
		size = 1;
	}

	/** Adds x to the front of the list. */
	public void addFirst(Item x) {
		sentinel.next = new IntNode(x, sentinel.next);
		if (size == 0) {
			last = sentinel.next; // Update last if the list was empty
		}
		size = size + 1;
	}

	/** Returns the first item in the list. */
	public Item getFirst() {
		return sentinel.next.item;
	}

	/** Adds x to the end of the list. */
	public void addLast(Item x) {
		IntNode newNode = new IntNode(x, null);
		last.next = newNode; // Attach new node to the end
		last = newNode; // Update last to point to the new node
		size = size + 1;
	}

	/** Returns the last item in the list. */
	public Item getLast() {
		return last.item; // Directly return the item from last node
	}

	/** Returns the size of the list. */
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		/* Creates a list of one integer, namely 10 */
		SLList<Integer> L = new SLList<>();
		L.addLast(20);
		System.out.println(L.size());
		System.out.println(L.getLast()); // Should print 20
		L.addLast(30);
		System.out.println(L.getLast()); // Should print 30
	}
}
