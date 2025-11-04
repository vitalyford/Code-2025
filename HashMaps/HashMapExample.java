package HashMaps;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Data {
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
}

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<Data, String> hm = new HashMap<>();

        // Keys must be unique
        // if they are not, the previous data based on the key 
        // will be overriden automatically
        hm.put(new Data("Andrew", 20, "sophomore"), "Andrew");
        hm.put(new Data("Parker", 21, "junior"), "Parker");
        hm.put(new Data("Devin", 22, "senior"), "Devin");

        if (hm.containsKey(new Data("Andrew", 20, "sophomore"))) {
            System.out.println(hm.get(new Data("Andrew", 20, "sophomore")));
        }

        // for(Map.Entry<String, Data> pair : hm.entrySet()) {
        //     pair.getKey();
        //     pair.getValue();
        // }
        
        // // hm.keySet();
        // for (String key : hm.keySet()) {
        //     hm.get(key); // get the value
        // }
    }
}
