package collection;



import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class DeliveryRoute<E> extends AbstractSequentialList<E> {
    private class Node {
        E data;
        Node next;
        Node prev;

        Node(E data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    private class ListIter implements ListIterator<E> {
        private Node current;
        private Node lastReturned;
        private int index;

        ListIter(int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = current;
            current = current.next;
            index++;
            return lastReturned.data;
        }

        @Override
        public boolean hasPrevious() {
            return current != null && current.prev != null;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            current = current.prev;
            lastReturned = current;
            index--;
            return lastReturned.data;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            Node next = lastReturned.next;
            Node prev = lastReturned.prev;

            if (prev == null) {
                head = next;
            } else {
                prev.next = next;
                lastReturned.prev = null;
            }

            if (next != null) {
                next.prev = prev;
            }
            lastReturned.next = null;
            lastReturned = null;
            size--;
            index--;
        }

        @Override
        public void set(E e) {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            lastReturned.data = e;
        }

        @Override
        public void add(E e) {
            Node newNode = new Node(e);
            if (current == null) {
                if (head == null) {
                    head = newNode;
                } else {
                    Node tail = head;
                    while (tail.next != null) {
                        tail = tail.next;
                    }
                    tail.next = newNode;
                    newNode.prev = tail;
                }
            } else {
                Node prev = current.prev;
                newNode.next = current;
                current.prev = newNode;
                if (prev == null) {
                    head = newNode;
                } else {
                    prev.next = newNode;
                    newNode.prev = prev;
                }
            }
            size++;
            index++;
        }
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIter(index);
    }

    @Override
    public int size() {
        return size;
    }
}