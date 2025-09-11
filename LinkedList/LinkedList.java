package LinkedList;

class Node {
    Object item;
    Node next;
    Node prev;

    public Node() {
        item = null;
        next = prev = null;
    }

    public Node(Object item) {
        this.item = item;
        next = prev = null;
    }
}

public class LinkedList {
    private Node head = null;
    private Node tail = null;
    private int length = 0;

    public LinkedList() {

    }

    public void add(Object item) {
        Node n = new Node(item);
        if (length == 0) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            n.prev = tail;
            tail = n;
        }
        length++;
    }

    public Object get(int index) {
        return null;
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add(78);
        ll.add(32);
        ll.add(-43);
        ll.add(11);
    }
}
