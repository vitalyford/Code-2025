package HashMaps;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;

class Data implements Comparable<Data> {
    String name;
    int age;
    String classLevel;

    public Data(String name, int age, String classLevel) {
        this.name = name;
        this.age = age;
        this.classLevel = classLevel;
    }

    public String toString() {
        return name + " " + age + " " + classLevel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + name.hashCode();
        hash = 19 * hash + age;
        hash = 19 * hash + classLevel.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        Data d = (Data)o;
        return d.name.equals(name) && d.age == age && d.classLevel.equals(classLevel);
    }

    public int compareTo(Data o) {
        // First, compare by name
        // If names are the same, compare by age
        // If ages are the same, compare by classLevel
        if (name.compareTo(o.name) == 0) {
            if (age == o.age) {
                return classLevel.compareTo(o.classLevel);
            }
            return age - o.age;
        }
        return name.compareTo(o.name);
    }
}

public class HashMapExample {
    public static void main(String[] args) {
        TreeMap<Data, String> hm = new TreeMap<>();

        ArrayList<Data> students = new ArrayList<>();

        students.add(new Data("Parker", 21, "junior"));
        students.add(new Data("Devin", 22, "senior"));
        students.add(new Data("Andrew", 20, "sophomore"));

        // Print before sorting
        System.out.println(students);
        // Sort the items in the collection (ArrayList)
        Collections.sort(students);
        // Print after sorting
        System.out.println(students);

        // Keys must be unique
        // if they are not, the previous data based on the key 
        // will be overriden automatically
        hm.put(new Data("Andrew", 20, "sophomore"), "Andrew");
        hm.put(new Data("Parker", 21, "junior"), "Parker");
        hm.put(new Data("Devin", 22, "senior"), "Devin");

        if (hm.containsKey(new Data("Andrew", 20, "sophomore"))) {
            System.out.println(hm.get(new Data("Andrew", 20, "sophomore")));
        }

        // for(Map.Entry<Data, String> pair : hm.entrySet()) {
        //     System.out.println(pair.getKey());
        //     System.out.println(pair.getValue());
        // }

        // hm.keySet();
        for (Data key : hm.keySet()) {
            hm.get(key); // get the value
        }
    }
}
