package JavaDoc;

public class MyHashSet<T> {

    /**
     * Хранит элементы множества (Set) вместе с меткой "удалён".
     */
    private static class Entry<T> {
        T key;
        boolean isDeleted;

        Entry(T key) {
            this.key = key;
            this.isDeleted = false;
        }
    }

    private Entry<T>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;



   
    /**
       Создать обобщённый массив вручную, с осознанным подавлением предупреждения.
    */
    @SuppressWarnings("unchecked")
    public MyHashSet() {
        table = (Entry<T>[]) new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     *  Вычисляет индекс ячейки в хеш-таблице (table), где должен находиться элемент с заданным ключом.
     * @param key Ключ
     */
    private int getIndex(T key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    /**
     * Добавляет элемент во множество.
     */
    public void add(T key) {
        int index = getIndex(key);
        for (int i = 0; i < table.length; i++) {
            int probe = (index + i) % table.length;
            Entry<T> entry = table[probe];
            if (entry == null || entry.isDeleted) {
                table[probe] = new Entry<>(key);
                size++;
                return;
            }
            if (!entry.isDeleted && entry.key.equals(key)) {
                return; // уже есть
            }
        }
    }

    /**
     * Удаляет элемент из множества.
     */
    public void remove(T key) {
        int index = getIndex(key);
        for (int i = 0; i < table.length; i++) {
            int probe = (index + i) % table.length;
            Entry<T> entry = table[probe];
            if (entry == null) return;
            if (!entry.isDeleted && entry.key.equals(key)) {
                entry.isDeleted = true;
                size--;
                return;
            }
        }
    }

    /**
     * Проверяет, содержится ли элемент в множестве.
     */
    public boolean contains(T key) {
        int index = getIndex(key);
        for (int i = 0; i < table.length; i++) {
            int probe = (index + i) % table.length;
            Entry<T> entry = table[probe];
            if (entry == null) return false;
            if (!entry.isDeleted && entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Возвращает количество элементов во множестве.
     */
    public int size() {
        return size;
    }

    /**
     * Возвращает подмножество элементов в виде массива от индекса `from` до `to` (не включая).
     */
    public Object[] subList(int from, int to) {
        Object[] result = new Object[to - from];
        int count = 0;
        int index = 0;
        for (Entry<T> entry : table) {
            if (entry != null && !entry.isDeleted) {
                if (index >= from && index < to) {
                    result[count++] = entry.key;
                }
                index++;
            }
        }
        Object[] trimmed = new Object[count];
        System.arraycopy(result, 0, trimmed, 0, count);
        return trimmed;
    }

    /**
     * Выводит все элементы множества.
     */
    public void printAll() {
        System.out.println("Содержимое MyHashSet:");
        for (Entry<T> entry : table) {
            if (entry != null && !entry.isDeleted) {
                System.out.println(entry.key);
            }
        }
    }
}
