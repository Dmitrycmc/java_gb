package ru.geekbrains.structure;

import java.util.Iterator;

public class DequeImpl<T> implements Deque<T> {
    public class DequeIterator implements Iterator<T> {
        private Node node = start;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            T val = node.value;
            node = node.next;
            return val;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class Node {
        T value;
        Node next = null;
        Node prev = null;
    }

    private Node start = null;
    private Node end = null;

    public void push(T value) {
        Node node = new Node();
        node.value = value;

        if (start == null) {
            start = node;
            end = node;
        } else {
            end.next = node;
            node.prev = end;
            end = node;
        }
    }

    public void unshift(T value) {
        Node node = new Node();
        node.value = value;

        if (start == null) {
            start = node;
            end = node;
        } else {
            start.prev = node;
            node.next = start;
            start = node;
        }
    }

    public T pop() {
        if (end == null) {
            return null;
        }
        Node node = end;

        if (node.prev == null) {
            start = end = null;
        } else {
            end = node.prev;
            node.prev = null;
        }
        return node.value;
    }

    public T shift() {
        if (start == null) {
            return null;
        }
        Node node = start;

        if (node.next == null) {
            start = end = null;
        } else {
            start = node.next;
            node.next = null;
        }
        return node.value;
    }

    private Node find(T el) {
        Node node = start;

        while (node != null && !node.value.equals(el)) {
            node = node.next;
        }

        return node;
    }

    private void remove(Node node) {
        if (node.prev == null) {
            start = node.next;
        } else {
            node.prev.next = node.next;
        }

        if (node.next == null) {
            end = node.prev;
        } else {
            node.next.prev = node.prev;
        }

        node.prev = node.next = null;
    }

    public boolean remove(T el) {
        Node node = find(el);

        if (node != null) {
            remove(node);
            return true;
        } else {
            return false;
        }
    }

    public int removeAll(T el) {
        int counter = 0;
        while (remove(el)) {
            counter++;
        }
        return counter;
    }

    private void insertAfter(Node after, T el) {
        if (after.next == null) {
            push(el);
        } else {
            Node node = new Node();
            node.value = el;
            node.prev = after;
            node.next = after.next;
            after.next = node;
            node.next.prev = node;
        }
    }

    public boolean insertAfter(T after, T el) {
        Node node = find(after);

        if (node != null) {
            insertAfter(node, el);
            return true;
        } else {
            return false;
        }
    }

    private void insertBefore(Node before, T el) {
        if (before.prev == null) {
            unshift(el);
        } else {
            Node node = new Node();
            node.value = el;
            node.next = before;
            node.prev = before.prev;
            before.prev = node;
            node.prev.next = node;
        }
    }

    public boolean insertBefore(T before, T el) {
        Node node = find(before);

        if (node != null) {
            insertBefore(node, el);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (start == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        Iterator<T> it = iterator();

        while (true) {
            sb.append(it.next());

            if (!it.hasNext()) {
                return sb.toString();
            }

            sb.append(" <-> ");
        }
    }
}
