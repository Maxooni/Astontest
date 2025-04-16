package JavaDoc_ver_2;

    /**
     * Использует (связанные списки) для разрешения коллизий.
     *
     * @param <K> тип ключей
     * @param <V> тип значений
     */
    public class MyHashMap_ver_2<K, V> {

        /**
         * Внутренний класс, представляющий элемент (пару ключ-значение) карты.
         */
        protected static class Entry<K, V> {
            K key;
            V value;
            Entry<K, V> next;

            Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private Entry<K, V>[] table;
        private static final int DEFAULT_CAPACITY = 16;
        private int size;

        /**
         * Создаёт новую хэш-карту с начальной вместимостью по умолчанию.
         */
        @SuppressWarnings("unchecked")
        public MyHashMap_ver_2() {
            table = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
            size = 0;
        }

        /**
         * Вычисляет индекс массива на основе хэш-кода ключа.
         *
         * @param key ключ
         * @return индекс в таблице
         */
        private int getIndex(K key) {
            return Math.abs(key.hashCode()) % table.length;
        }

        /**
         * Добавляет новую пару ключ-значение или обновляет значение, если такой ключ уже существует.
         *
         * @param key ключ
         * @param value значение
         */
        public void put(K key, V value) {
            int index = getIndex(key);
            Entry<K, V> current = table[index];

            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; // обновляем значение
                    return;
                }
                current = current.next;
            }

            Entry<K, V> newEntry = new Entry<>(key, value);
            newEntry.next = table[index]; // вставка в начало цепочки
            table[index] = newEntry;
            size++;
        }

        /**
         * Получает значение, связанное с указанным ключом.
         *
         * @param key ключ
         * @return значение или {@code null}, если ключ не найден
         */
        public V get(K key) {
            int index = getIndex(key);
            Entry<K, V> current = table[index];

            while (current != null) {
                if (current.key.equals(key)) {
                    return current.value;
                }
                current = current.next;
            }

            return null;
        }

        /**
         * Удаляет пару ключ-значение по ключу.
         *
         * @param key ключ, который нужно удалить
         */
        public void remove(K key) {
            int index = getIndex(key);
            Entry<K, V> current = table[index];
            Entry<K, V> prev = null;

            while (current != null) {
                if (current.key.equals(key)) {
                    if (prev == null) {
                        table[index] = current.next;
                    } else {
                        prev.next = current.next;
                    }
                    size--;
                    return;
                }
                prev = current;
                current = current.next;
            }
        }

        /**
         * Возвращает количество элементов в карте.
         *
         * @return количество элементов
         */
        public int size() {
            return size;
        }


        /**
         * Возвращает подмножество этой карты в виде нового MyHashMap с элементами от fromIndex (включительно) до toIndex (не включительно).
         *
         * @param fromIndex начальный индекс (включительно)
         * @param toIndex   конечный индекс (не включительно)
         * @return новая MyHashMap с подмножеством элементов
         * @throws IndexOutOfBoundsException если индексы некорректны
         */
        public MyHashMap_ver_2<K, V> subList(int fromIndex, int toIndex) {
            // Проверка на корректность границ
            if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
                throw new IndexOutOfBoundsException("Invalid fromIndex or toIndex");
            }

            // Создаем новую карту, в которую запишем подмножество
            MyHashMap_ver_2<K, V> result = new MyHashMap_ver_2<>();

            int index = 0; // Счетчик позиции элемента в обходе

            // Обходим таблицу с бакетами
            for (int i = 0; i < table.length; i++) {
                Entry<K, V> current = table[i];

                // Проходим по цепочке в каждом бакете
                while (current != null) {
                    // Добавляем в результат только если индекс входит в указанный диапазон
                    if (index >= fromIndex && index < toIndex) {
                        result.put(current.key, current.value);
                    }

                    index++; // Переходим к следующей позиции
                    current = current.next; // Переход по цепочке
                }
            }

            return result;
        }

        /**
         * Обновляет значение, связанное с указанным ключом.
         *
         * @param key ключ, значение которого нужно изменить
         * @param newValue новое значение
         * @return true, если значение было обновлено; false, если ключ не найден
         */
        public boolean set(K key, V newValue) {
            int index = getIndex(key);
            Entry<K, V> current = table[index];

            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = newValue;
                    return true;
                }
                current = current.next;
            }

            return false;
        }


        /**
         * Возвращает массив бакетов (используется во внутренней логике MyHashSet).
         */
        Entry<K, V>[] getTable() {
            return table;
        }


        /**
         * Выводит все пары ключ-значение в консоль.
         */
        public void printAll() {
            for (int i = 0; i < table.length; i++) {
                Entry<K, V> current = table[i];
                while (current != null) {
                    System.out.println(current.key + " = " + current.value);
                    current = current.next;
                }
            }
        }
    }