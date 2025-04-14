package JavaDoc;

import java.util.HashMap;

public class test  {
    public static void main(String[] args) {

        MyHashMap<Integer, String> titleName = new MyHashMap<>();

        titleName.put(1,"Апанас Алексей");
        titleName.put(2,"Бобич Антон");
        titleName.put(3,"Гаджиев Джамбулат");
        titleName.put(4,"Гелимянов Данил");
        titleName.put(5,"Герасимович Дмитрий");
        titleName.put(6,"Горбачев Станислав");
        System.out.println(titleName.get(2));
        titleName.printAll();
        titleName.remove(3);
        titleName.printAll();
        titleName.set(1,"Герасимович Дмитрий");
        titleName.printAll();
        System.out.println(titleName.size());

        Object[] keys = titleName.subList(1, 4);
        System.out.print("Sublist (1-4): ");
        for (Object key : keys) {
            System.out.print(key + " ");
        }

        // MyHashSet

        MyHashSet<String> titleNameSet = new MyHashSet<>();

        titleNameSet.add("Апанас Алексей");
        titleNameSet.add("Бобич Антон");
        titleNameSet.add("Гаджиев Джамбулат");
        titleNameSet.add("Гелимянов Данил");
        titleNameSet.add("Герасимович Дмитрий");
        titleNameSet.add("Горбачев Станислав");
        titleNameSet.printAll();
        titleNameSet.remove("Бобич Антон");
        titleNameSet.printAll();
        System.out.println( titleNameSet.contains("Бобич Антон"));
        System.out.println( titleNameSet.size());

        Object[] sub = titleNameSet.subList(1, 4);
        System.out.print("Sublist (1-4): ");
        for (Object name : sub) {
            System.out.print(name + " ");
        }



    }



}
