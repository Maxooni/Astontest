package JavaDoc_ver_2;

/**
 * Реализация множества (MyHashSet), основанная на MyHashMap.
 * Элементы хранятся как ключи, значение не используется.
 *
 * @param <T> тип элементов множества
 */
public class MyHashSet_ver_2<T> {

    private final MyHashMap_ver_2<T, Object> map;
    private static final Object PRESENT = new Object();

    /**
     * Создаёт пустое множество.
     */
    public MyHashSet_ver_2() {
        map = new MyHashMap_ver_2<>();
    }

    /**
     * Добавляет элемент во множество.
     *
     * @param value элемент для добавления
     */
    public void add(T value) {
        map.put(value, PRESENT); //PRESENT - это заглушка так как значения ключей везде одинакого
    }

    /**
     * Удаляет элемент из множества.
     *
     * @param value элемент для удаления
     */
    public void remove(T value) {
        map.remove(value);
    }

    /**
     * Проверяет, содержится ли элемент во множестве.
     *
     * @param value элемент для проверки
     * @return {@code true}, если элемент присутствует; иначе {@code false}
     */
    public boolean contains(T value) {
        return map.get(value) != null;
    }

    /**
     * Возвращает количество элементов во множестве.
     *
     * @return размер множества
     */
    public int size() {
        return map.size();
    }

     /**
     * Возвращает подмножество элементов от {@code fromIndex} (включительно) до {@code toIndex} (не включительно).
     * Элементы выбираются в порядке обхода хеш-таблицы и связанных списков в бакетах.
     *
     * @param fromIndex начальный индекс включительно (0)
     * @param toIndex конечный индекс не включительно
     * @return новое множество, содержащее указанный диапазон элементов
     * @throws IndexOutOfBoundsException если индексы выходят за пределы размера
     */
     public MyHashSet_ver_2<T> subList(int fromIndex, int toIndex) {
         // Проверка валидности границ
         if (fromIndex < 0 || toIndex > map.size() || fromIndex > toIndex) {
             throw new IndexOutOfBoundsException("Неверные fromIndex или toIndex");
         }

         // Создаем новое множество для результата
         MyHashSet_ver_2<T> result = new MyHashSet_ver_2<>();

         // Получаем подтаблицу из подкарты
         MyHashMap_ver_2<T, Object> subMap = map.subList(fromIndex, toIndex);

         // Получаем доступ к внутренней таблице subMap
         MyHashMap_ver_2.Entry<T, Object>[] table = subMap.getTable();

         // Проходим по таблице и добавляем все ключи в множество
         for (int i = 0; i < table.length; i++) {
             MyHashMap_ver_2.Entry<T, Object> current = table[i];
             while (current != null) {
                 result.add(current.key);
                 current = current.next;
             }
         }

         return result;
     }

    /**
     * Выводит все элементы множества в консоль.
     */
    public void printAll() {
        MyHashMap_ver_2.Entry<T, Object>[] table = map.getTable();
        for (int i = 0; i < table.length; i++) {
            MyHashMap_ver_2.Entry<T, Object> current = table[i];
            while (current != null) {
                System.out.println(current.key); // выводим ключ (элемент множества)
                current = current.next;
            }
        }
    }

}
