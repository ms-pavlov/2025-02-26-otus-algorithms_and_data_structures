package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class OpenAddressMap<K, V> implements Map<K, V>, Iterable<Map.Entry<K, V>> {

    private OpenAddressMap.Entry<?, ?>[] table;
    private int size;
    private int basketCount;
    private int probing;

    public OpenAddressMap(int initialCapacity, int basketCount) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);

        if (initialCapacity == 0)
            initialCapacity = 1;
        this.table = new OpenAddressMap.Entry<?, ?>[initialCapacity];
        this.size = 0;
        this.basketCount = basketCount;
    }

    public abstract int getNextProbing();

    public void beginProbing() {
        probing = 1;
    }

    public int getAndIncProbing() {
        return this.probing ++;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return Arrays.asList(table).stream()
                .filter(kvEntry -> null != kvEntry || !kvEntry.isDeleted)
                .map(entry -> (Map.Entry<K, V>) entry)
                .iterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size;
    }

    @Override
    public boolean containsKey(Object key) {
        beginProbing();
        int index = getIndex(key.hashCode());
        while (table.length > index) {
            if (table[index].key == key && !table[index].isDeleted) {
                return true;
            }
            index += getNextProbing();
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return Arrays.stream(table)
                .anyMatch(entry -> entry.getValue() == value && !entry.isDeleted);
    }

    @Override
    public V get(Object key) {
        beginProbing();
        int index = getIndex(key.hashCode());
        while (table.length > index && null != table[index]) {
            if (table[index].key.equals(key) && !table[index].isDeleted) {
                return (V) table[index].value;
            }
            index += getNextProbing();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        beginProbing();
        int index = getIndex(key.hashCode());
        while (true) {
            if (table.length <= index) {
                resize();
            }
            if (table[index] == null) {
                this.table[index] = new Entry<>(key, value);
                size++;
                return null;
            }
            if (table[index].key == key || table[index].isDeleted) {
                OpenAddressMap.Entry<K, V> pointer = (OpenAddressMap.Entry<K, V>) table[index];
                V old = pointer.value;
                if (pointer.isDeleted) {
                    old = null;
                }
                pointer.isDeleted = false;
                pointer.value = value;
                pointer.key = key;
                size++;
                return old;
            }
            index += getNextProbing();
        }
    }

    @Override
    public V remove(Object key) {
        beginProbing();
        int index = getIndex(key.hashCode());
        while (table.length > index && null != table[index]) {
            if (table[index].key == key && !table[index].isDeleted) {
                table[index].isDeleted = true;
                size--;
                return (V) table[index].value;
            }
            index += getNextProbing();
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
    }

    @Override
    public Set<K> keySet() {
        return StreamSupport.stream(this::spliterator, Spliterator.ORDERED, false)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return StreamSupport.stream(this::spliterator, Spliterator.ORDERED, false)
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return StreamSupport.stream(this::spliterator, Spliterator.ORDERED, false)
                .collect(Collectors.toSet());
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        boolean isDeleted = false;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return this.value;
        }
    }

    private int getIndex(int hash) {
        return Integer.remainderUnsigned(hash, basketCount);
    }

    private void resize() {
        this.table = Arrays.copyOf(this.table, (table.length << 1) + 1);
    }
}
