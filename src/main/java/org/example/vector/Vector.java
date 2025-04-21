package org.example.vector;

import java.util.Arrays;

public class Vector<T> {
    private Object[] elementos;
    private int size;

    public Vector() {
        this.elementos = new Object[10];
        this.size = 0;
    }

    public void add(T elemento) {
        ensureCapacity();
        elementos[size++] = elemento;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return (T) elementos[index];
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size >= elementos.length) {
            int newCapacity = elementos.length * 2;
            elementos = Arrays.copyOf(elementos, newCapacity);
        }
    }
}
