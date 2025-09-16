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

    /**
     * This method returns an object give the index if object exists
     * @param index the integer index you want to get the object by
     * @return the item of type Object based on the index
     * @throws IndexOutOfBounds if the index is less or greater/equal to length
     */
    public Object get(int index) {
        if (index < 0 || index >= length) {
            // index = index % length;
            throw new IndexOutOfBoundsException("Wrong index " + index + " for length " + length);
        }
        Node curr = head;
        int searchingIndex = 0;
        while (searchingIndex != index) {
            curr = curr.next;
            searchingIndex++;
        }
        return curr.item;
        // lets go
    }

    /** 
    * Removes the first matched object and
    * returns the position of the removed object
    * from the LinkedList.
    * 
    * @param   obj  represents the object the user wants to remove
    * @return       position of the removed object
    */
    public int remove(Object obj) {

    }

    /**
    * Removes s the  removed object (not Node) from the LinkedList.
    * 
    * @param   position  represents the position of the object
    *                    to be removed
    * @return            the item that was removed
    */
    public Object remove(int position) {

    }

    /**
    * Adds the object given its position in the LinkedList.
    * 
    * @param   obj       represents the object to be added
    * @param   position  represents the position of the object
    *                    to be added in the LinkedList
    */
    public void add(Object obj, int position) {

    }

    public int size() {
        return length;
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add(78);
        ll.add(32);
        ll.add(-43);
        ll.add(11);

        try {
            System.out.println(ll.get(4874685));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
