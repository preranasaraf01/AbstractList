package collection;

import java.util.AbstractList;
import java.util.Arrays;

class OrderList<E> extends AbstractList<E> {
    private Object[] orders;
    private int size;

    public OrderList() {
        orders = new Object[10];
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) orders[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {
        if (size == orders.length) {
            orders = Arrays.copyOf(orders, orders.length * 2);
        }
        orders[size++] = e;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        E removedElement = (E) orders[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(orders, index + 1, orders, index, numMoved);
        }
        orders[--size] = null;
        return removedElement;
    }
}