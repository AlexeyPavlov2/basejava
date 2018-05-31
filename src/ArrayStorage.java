/**
 * Array based storage for Resumes
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayStorage {
    private static final int MAX_CAPACITY = 10000;
    private final Resume[] storage = new Resume[MAX_CAPACITY];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume resume) {
        if (size != storage.length) {
            storage[size] = resume;
            size++;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    Resume get(String uuid) {
        int idx = search(uuid);
        return idx >= 0 ? storage[idx] : null;
    }

    void delete(String uuid) {
        int idx = search(uuid);
        if (idx < 0) {
            throw new NoSuchElementException();
        } else {
            for (int i = idx; i < size; i++)
                storage[i] = storage[i + 1];
            storage[size] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = {};
        if (size != 0)
            return Arrays.copyOfRange(storage, 0, size);
        else
            return result;
    }

    int size() {
        return size;
    }

    //Added by AlexeyPavlov methods

    /**
     * Method searches the specified array "storage" of Resume for the specified value of field "uuid"
     *
     * @param key - search string
     * @return integer - -1: not found, >=0 - position in the array
     */
    private int search(String key) {
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (key.compareTo(storage[i].uuid) == 0) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * The method prints all elements of the array "storage"
     */
    public void printAll() {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                System.out.println("[" + i + "] = " + storage[i].toString());
            }
        } else {
            System.out.println("Storage is empty");
        }
    }

}