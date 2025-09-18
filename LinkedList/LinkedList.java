package LinkedList;

class Pokemon {
    String name;
    int age;

    public Pokemon(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pokemon)) {
            return false;
        }
        Pokemon pObj = (Pokemon)obj;

        return name.equals(pObj.name) && age == pObj.age;
    }

    public String toString() {
        return name + " " + age + " y.o.";
    }
}

class Node<F1> {
    F1 item;
    Node<F1> next;
    Node<F1> prev;

    public Node() {
        item = null;
        next = prev = null;
    }

    public Node(F1 item) {
        this.item = item;
        next = prev = null;
    }
}

public class LinkedList<F1> {
    private Node<F1> head = null;
    private Node<F1> tail = null;
    private int length = 0;

    public LinkedList() {

    }

    public void add(F1 item) {
        Node<F1> n = new Node<>(item);
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
    public F1 get(int index) {
        if (index < 0 || index >= length) {
            // index = index % length;
            throw new IndexOutOfBoundsException("Wrong index " + index + " for length " + length);
        }
        Node<F1> curr = head;
        int searchingIndex = 0;
        while (searchingIndex != index) {
            curr = curr.next;
            searchingIndex++;
        }
        return curr.item;
        // lets go
    }

    public boolean contains(F1 item) {
        for (Node<F1> curr = head; curr != null; curr = curr.next) {
            if (curr.item.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /** 
    * Removes the first matched object and
    * returns the position of the removed object
    * from the LinkedList.
    * 
    * @param   obj  represents the object the user wants to remove
    * @return       position of the removed object
    */
    public int remove(F1 obj) {
        return 0;
    }

    /**
    * Removes s the  removed object (not Node) from the LinkedList.
    * 
    * @param   position  represents the position of the object
    *                    to be removed
    * @return            the item that was removed
    */
    public F1 remove(int position) {
        return null;
    }

    /**
    * Adds the object given its position in the LinkedList.
    * 
    * @param   obj       represents the object to be added
    * @param   position  represents the position of the object
    *                    to be added in the LinkedList
    */
    public void add(F1 obj, int position) {

    }

    public int size() {
        return length;
    }

    public static void main(String[] args) {
        LinkedList<Pokemon> ll = new LinkedList<>();

        Pokemon p1 = new Pokemon("char", 45);
        Pokemon p2 = new Pokemon("pika", 8993443);

        Pokemon p3 = new Pokemon("char", 45);
        
        ll.add(p1);
        ll.add(p2);
        // ll.add(-43);
        // ll.add(11);

        int sum = 0;
        for (int i = 0; i < ll.size(); i++) {
            sum += ll.get(i).age;
        }

        System.out.println("Sum is " + sum);
        System.out.println("Do we have " + p3 + " in the list? " + ll.contains(p3));

        try {
            System.out.println(ll.get(4874685));
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
