package fi.ak.dungeongen.datastructures;

/**
 * Class provides functionality for an ordered list.
 */
public class MyList<T> {

    private T[] list;
    private int current;
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_SIZE = 9999999;

    public MyList() {
        current = 0;
        list = (T[]) new Object[DEFAULT_SIZE];
    }

    /**
     * Size of the list.
     *
     * @return int for size of the list.
     */
    public int size() {
        return current;
    }

    /**
     * Checks whether the list is empty or not.
     *
     * @return boolean for list being empty.
     */
    public boolean isEmpty() {
        return current == 0;
    }

    /**
     * Clears the list and sets up a new one with default size.
     */
    public void clear() {
        current = 0;
        list = (T[]) new Object[DEFAULT_SIZE];
    }

    /**
     * Get element from list at index.
     *
     * @param index index of the list.
     *
     * @return T element at requested index.
     */
    public T get(int index) {
        if (index > current || index < 0 || current == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return list[index];
    }

    /**
     * Adds an element to the list.
     * 
     * @param element to be added.
     * 
     * @return boolean whether adding was successful or not.
     */
    public boolean add(T element) {
        if (current >= MAX_SIZE) {
            return false;
        }
        list[current] = element;
        current++;
        if (current >= list.length) {
            increaseSize();
        }
        return true;

    }

    private void increaseSize() {
        int newSize = (int) Math.pow(list.length, 2);
        T temp[] = (T[]) new Object[Math.min(newSize, MAX_SIZE)];
        for (int i = 0; i < list.length; i++) {
            temp[i] = list[i];
        }
        list = temp;
    }

}
