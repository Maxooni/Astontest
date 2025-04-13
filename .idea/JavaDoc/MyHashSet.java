package JavaDoc;

public class MyHashSet<E> {
    private static final Object DUMMY = new Object();
    private MyHashMap<E, Object> map = new MyHashMap<>();

    /**
     * Добавляет элемент в множество.
     * @param key элемент
     */
    public void add(E key) {
        map.put(key, DUMMY);
    }

    /**
     * Удаляет элемент из множества.
     * @param key элемент
     */
    public void remove(E key) {
        map.remove(key);
    }

    /**
     * Проверяет наличие элемента в множестве.
     * @param key элемент
     * @return true, если найден
     */
    public boolean contains(E key) {
        return map.get(key) != null;
    }

    /**
     * Возвращает размер множества.
     * @return размер
     */
    public int size() {
        return map.size();
    }

    /**
     * Возвращает подмножество в виде массива элементов.
     * @param from начальный индекс (включительно)
     * @param to конечный индекс (не включительно)
     * @return массив элементов
     */
    public Object[] subList(int from, int to) {
        return map.subList(from, to);
    }
}