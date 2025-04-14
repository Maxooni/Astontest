package JavaDoc;

import java.util.Arrays;

public class MyHashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        boolean isDeleted;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.isDeleted = false;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private Entry<K, V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Добавляет или обновляет значение по ключу.
     * @param key Ключ
     * @param value Значение
     */
    public void put(K key, V value) {
        int index = getIndex(key);
        for (int i = 0; i < table.length; i++) {
            int probe = (index + i) % table.length;
            Entry<K, V> entry = table[probe];
            if (entry == null || entry.isDeleted || entry.key.equals(key)) {
                table[probe] = new Entry<>(key, value);
                size++;
                return;
            }
        }
    }

    /**
     * Получает значение по ключу.
     * @param key Ключ
     * @return Значение или null, если не найдено
     */
    public V get(K key) {
        int index = getIndex(key);
        for (int i = 0; i < table.length; i++) {
            int probe = (index + i) % table.length;
            Entry<K, V> entry = table[probe];
            if (entry == null) return null;
            if (!entry.isDeleted && entry.key.equals(key)) return entry.value;
        }
        return null;
    }

    /**
     * Удаляет значение по ключу.
     * @param key Ключ
     */
    public void remove(K key) {
        int index = getIndex(key);
        for (int i = 0; i < table.length; i++) {
            int probe = (index + i) % table.length;
            Entry<K, V> entry = table[probe];
            if (entry == null) return;
            if (!entry.isDeleted && entry.key.equals(key)) {
                entry.isDeleted = true;
                size--;
                return;
            }
        }
    }

    /**
     * Устанавливает новое значение по ключу, если ключ существует.
     * @param key Ключ
     * @param value Новое значение
     */
    public void set(K key, V value) {
        int index = getIndex(key);
        for (int i = 0; i < table.length; i++) {
            int probe = (index + i) % table.length;
            Entry<K, V> entry = table[probe];
            if (entry != null && !entry.isDeleted && entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
    }

    /**
     * Возвращает количество элементов.
     * @return размер
     */
    public int size() {
        return size;
    }

    /**
     * Возвращает массив ключей в заданном диапазоне.
     * @param from индекс начала (включительно)
     * @param to индекс конца (не включительно)
     * @return массив ключей
     */
    public Object[] subList(int from, int to) {
        Object[] keys = new Object[to - from];
        int idx = 0, count = 0;
        for (Entry<K, V> entry : table) {
            if (entry != null && !entry.isDeleted) {
                if (count >= from && count < to) {
                    keys[idx++] = entry.key;
                }
                count++;
            }
        }
        return keys;
    }

    /**
     *  Вычисляет индекс ячейки в хеш-таблице (table), где должен находиться элемент с заданным ключом.
     * @param key Ключ
     */
    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    /**
     * Выводит все элементы карты (ключ => значение), которые не удалены.
     */
    public void printAll() {
        System.out.println("Содержимое MyHashMap:");
        for (Entry<K, V> entry : table) {
            if (entry != null && !entry.isDeleted) {
                System.out.println(entry.key + " => " + entry.value);
            }
        }
    }




}