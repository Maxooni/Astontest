package JavaDoc_ver_2;

import JavaDoc.MyHashMap;
import JavaDoc.MyHashSet;

public class test {
    public static void main(String[] args) {

        MyHashMap_ver_2<Integer, String> titleName = new MyHashMap_ver_2<>();

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

       MyHashMap_ver_2<Integer, String> subSet = titleName.subList(0, 4);
        System.out.println("\nSubList от 0 до 4:");
       subSet.printAll();


        System.out.println("MyHashSet");



        MyHashSet_ver_2<String> titleNameSet = new MyHashSet_ver_2<>();


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



        MyHashSet_ver_2<String> subSet2 = titleNameSet.subList(1, 4);
        System.out.println("\nSubList от 1 до 4:");
        subSet2.printAll();






    }



}
