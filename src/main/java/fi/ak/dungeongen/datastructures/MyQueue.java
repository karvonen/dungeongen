package fi.ak.dungeongen.datastructures;

import java.util.NoSuchElementException;

/**
 * Class provides functionality for a queue.
 */
public class MyQueue<T> {

    private T[] queue;
    private int head;
    private int tail;
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_SIZE = 9999999;

    public MyQueue() {
        head = 0;
        tail = 0;
        queue = (T[]) new Object[DEFAULT_SIZE];
    }

    private void increaseSize() {
        int newSize = (int) Math.pow(queue.length, 2);
        T temp[] = (T[]) new Object[Math.min(newSize, MAX_SIZE)];
        for (int i = head; i < tail; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param e element to be added.
     */
    public void addLast(T e) {
        queue[tail] = e;
        tail++;
        if (tail >= queue.length) {
            increaseSize();
        }
    }

    /**
     * Removes and returns the first element of the queue.
     *
     * @return first element of the queue.
     *
     * @throws NoSuchElementException if list is empty.
     */
    public T removeFirst() throws NoSuchElementException {
        if (head == tail) {
            throw new NoSuchElementException();
        }
        T first = queue[head];
        head++;
        return first;
    }

    /**
     * Removes and returns the last element of the queue.
     *
     * @return last element of the queue.
     *
     * @throws NoSuchElementException if list is empty.
     */
    public T removeLast() throws NoSuchElementException {
        if (head == tail) {
            throw new NoSuchElementException();
        }
        T last = queue[tail];
        queue[tail] = null;
        tail--;
        return last;
    }

    /**
     * Clears the queue and sets up a new one with default size.
     */
    public void clear() {
        head = 0;
        tail = 0;
        queue = (T[]) new Object[DEFAULT_SIZE];
    }

    /**
     * Size of the queue.
     *
     * @return int for size of the queue.
     */
    public int size() {
        return tail - head;
    }

    /**
     * Checks whether the queue is empty or not.
     *
     * @return boolean for queue being empty.
     */
    public boolean isEmpty() {
        return head == tail;
    }

}
