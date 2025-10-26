package ru.sbrf.edu;

import java.util.*;

public class CustomArrayImpl<T>
        implements CustomArray<T> {


    transient Object[] elementData;

    private int size;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private static final int DEFAULT_CAPACITY = 5;


    public CustomArrayImpl(){
        this.elementData = EMPTY_ELEMENT_DATA;
    }

    public CustomArrayImpl(int capacity){
        if (capacity > 0) {
            this.elementData = new Object[capacity];
        } else if (capacity == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }


    public CustomArrayImpl(Collection<? extends T>  c){
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            elementData = Arrays.copyOf(a, size, Object[].class);
        } else {
            // replace with empty array.
            elementData = EMPTY_ELEMENT_DATA;
        }
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
    public boolean add(Object item) {
        add(item, elementData, size);
        return true;
    }

    private void add(Object e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }

    private Object[] grow() {
        return grow(size + 1);
    }
    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != EMPTY_ELEMENT_DATA) {
            int newCapacity = newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

    public static int newLength(int oldLength, int minGrowth, int prefGrowth) {
        // preconditions not checked because of inlining
        // assert oldLength >= 0
        // assert minGrowth > 0

        int prefLength = oldLength + Math.max(minGrowth, prefGrowth); // might overflow
        if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) {
            return prefLength;
        } else {
            // put code cold in a separate method
            return hugeLength(oldLength, minGrowth);
        }
    }

    private static int hugeLength(int oldLength, int minGrowth) {
        int minLength = oldLength + minGrowth;
        if (minLength < 0) { // overflow
            throw new OutOfMemoryError(
                    "Required array length " + oldLength + " + " + minGrowth + " is too large");
        } else if (minLength <= SOFT_MAX_ARRAY_LENGTH) {
            return SOFT_MAX_ARRAY_LENGTH;
        } else {
            return minLength;
        }
    }


    @Override
    public boolean addAll(Object[] items) {
        int numNew = items.length;
        if (numNew == 0)
            return false;
        Object[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size))
            elementData = grow(s + numNew);
        System.arraycopy(items, 0, elementData, s, numNew);
        size = s + numNew;
        return true;
    }

    @Override
    public boolean addAll(Collection<T> items) {
        Object[] itemsArray = items.toArray();
        return addAll(itemsArray);
    }

    @Override
    public boolean addAll(int index, Object[] items) {
        Objects.checkIndex(index, size);
        int numNew = items.length;
        if (numNew == 0)
            return false;
        Object[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size))
            elementData = grow(s + numNew);

        int numMoved = s - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index,
                    elementData, index + numNew,
                    numMoved);
        System.arraycopy(items, 0, elementData, index, numNew);
        size = s + numNew;
        return true;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }

   T elementData(int index) {
        return (T) elementData[index];
    }

    @Override
    public T set(int index, Object item) {
        Objects.checkIndex(index, size);
        T oldValue = elementData(index);
        elementData[index] = item;
        return oldValue;
    }

   @Override
    public void remove(int index) {
        Objects.checkIndex(index, size);

        final Object[] es = elementData;
        fastRemove(es, index);
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }

    @Override
    public boolean remove(Object item) {
        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
        found: {
            if (item == null) {
                for (; i < size; i++)
                    if (es[i] == null)
                        break found;
            } else {
                for (; i < size; i++)
                    if (item.equals(es[i]))
                        break found;
            }
            return false;
        }
        fastRemove(es, i);
        return true;
    }

    @Override
    public boolean contains(Object item) {
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(Object item) {
        Object[] es = elementData;

        if (item == null) {
            for (int i = 0; i < size; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (item.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void ensureCapacity(int newElementsCount) {
        if (newElementsCount > elementData.length
                && !(elementData == EMPTY_ELEMENT_DATA
                && newElementsCount <= DEFAULT_CAPACITY)) {
            grow(newElementsCount);
        }
    }

    @Override
    public int getCapacity() {
        return elementData.length;
    }

    @Override
    public void reverse() {
        Object[] newElementData = new Object[elementData.length];
        for (int i = size; i > 0; i--){
            newElementData[size-i] = elementData[i-1];
        }

        elementData = newElementData;
    }
    @Override
    public String toString(){
        StringBuilder text = new StringBuilder("[");
        for (Object item : elementData){
            if (item != null)
                text.append(" ").append(item);
        }
        return text + " ]";
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }
}
