package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ChainTableMap<K, V> implements Map<K, V>, Iterable<Map.Entry<K, V>> {

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private ChainTableMap.Entry<?, ?>[] table;
    private int size;

    private float loadFactor;
    private int threshold;

    public ChainTableMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal Load: " + loadFactor);

        if (initialCapacity == 0)
            initialCapacity = 1;
        this.loadFactor = loadFactor;
        table = new ChainTableMap.Entry<?, ?>[initialCapacity];
        threshold = (int) Math.min(initialCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int hash = key.hashCode();
        ChainTableMap.Entry<?, ?> pointer = table[getIndex(hash)];
        while (null != pointer) {
            if ((pointer.hash == hash) && pointer.key.equals(key)) {
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        for (int i = table.length; i-- > 0; ) {
            ChainTableMap.Entry<?, ?> pointer = table[i];
            while (null != pointer) {
                if (pointer.value.equals(value)) {
                    return true;
                }
                pointer = pointer.next;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int hash = key.hashCode();
        ChainTableMap.Entry<?, ?> pointer = table[getIndex(hash)];
        while (null != pointer) {
            if ((pointer.hash == hash) && pointer.key.equals(key)) {
                return (V) pointer.value;
            }
            pointer = pointer.next;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (value == null) {
            throw new NullPointerException();
        }

        int hash = key.hashCode();
        int index = getIndex(hash);
        ChainTableMap.Entry<K, V> pointer = (ChainTableMap.Entry<K, V>) table[index];
        while (null != pointer) {
            if ((pointer.hash == hash) && pointer.key.equals(key)) {
                V old = pointer.value;
                pointer.value = value;
                return old;
            }
            pointer = pointer.next;
        }

        addEntry(hash, key, value, index);
        return null;
    }

    @Override
    public V remove(Object key) {
        int hash = key.hashCode();
        int index = getIndex(hash);
        ChainTableMap.Entry<K, V> pointer = (ChainTableMap.Entry<K, V>) table[index];
        ChainTableMap.Entry<K,V> prev = null;
        while (null != pointer) {
            if ((pointer.hash == hash) && pointer.key.equals(key)) {
                if (prev != null) {
                    prev.next = pointer.next;
                } else {
                    table[index] = pointer.next;
                }
                size--;
                V oldValue = pointer.value;
                pointer.value = null;
                return oldValue;
            }
            pointer = pointer.next;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        ChainTableMap.Entry<?, ?> tab[] = table;
        for (int index = tab.length; --index >= 0; )
            tab[index] = null;
        size = 0;
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

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new ChainTableMapIterator<>(this);
    }

    @Getter
    @Setter
    private static class ChainTableMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        final ChainTableMap<K, V> hash;
        int index;
        ChainTableMap.Entry<K, V> current;

        private ChainTableMapIterator(ChainTableMap<K, V> hash) {
            this.hash = hash;
            index = 1;
            current = (ChainTableMap.Entry<K, V>) hash.table[0];
        }


        @Override
        public boolean hasNext() {
            skipNull();
            return null != current;
        }

        @Override
        public Map.Entry<K, V> next() {
            skipNull();
            ChainTableMap.Entry<K, V> next = current;
            current = (null != current)? current.next: null;
            return next;
        }

        private void skipNull() {
            while ((hash.table.length > index) && (null == current)) {
                current = (ChainTableMap.Entry<K, V>) hash.table[index];
                index++;
            }
        }
    }

    @AllArgsConstructor
    private static class Entry<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        ChainTableMap.Entry<K, V> next;

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
        return Integer.remainderUnsigned(hash, table.length);
    }

    private void addEntry(int hash, K key, V value, int index) {
        if (size >= threshold) {
            rehash();

            hash = key.hashCode();
            index = getIndex(hash);
        }

        ChainTableMap.Entry<K,V> pointer = (ChainTableMap.Entry<K,V>) table[index];
        table[index] = new ChainTableMap.Entry<>(hash, key, value, pointer);
        size++;
    }

    protected void rehash() {
        int oldCapacity = table.length;
        ChainTableMap.Entry<?, ?>[] oldMap = table;

        int newCapacity = (oldCapacity << 1) + 1;
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            if (oldCapacity == MAX_ARRAY_SIZE)
                return;
            newCapacity = MAX_ARRAY_SIZE;
        }

        this.threshold = (int) Math.min(newCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
        this.table = new ChainTableMap.Entry<?, ?>[newCapacity];

        for (int i = 0; i < oldCapacity; i++) {
            ChainTableMap.Entry<K, V> pointer = (ChainTableMap.Entry<K, V>) oldMap[i];
            while (null != pointer) {
                int index = Integer.remainderUnsigned(pointer.hash, this.table.length);
                ChainTableMap.Entry<K, V> e = pointer;
                pointer = pointer.next;

                e.next = (ChainTableMap.Entry<K, V>) this.table[index];
                this.table[index] = e;
            }
        }
    }
}
