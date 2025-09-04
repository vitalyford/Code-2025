package LinkedList;

// PIE

class PetPeeve {
    String name;

    public PetPeeve(String name) {
        this.name = name;
    }

    public void set(String name) {
        if (name.contains(name))
        this.name = name;
    }
}

public class App {
    public static void main(String[] args) {
        PetPeeve p1 = new PetPeeve("Driving on the left lane under speed limit");

        PetPeeve p2 = new PetPeeve("Turning without a blinker");

        p2 = p1;
        p1.name = "Smashing the door in front of you";

        System.out.println(p2.name);
    }
}