/**
 * Array based storage for Resumes
 */

import java.util.Arrays;

public class ArrayStorage {
    private static final int MAX_CAPACITY = 10000;
    private final Resume[] storage = new Resume[MAX_CAPACITY];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        if (size != storage.length) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Internal array is full");
        }
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        return index >= 0 ? storage[index] : null;
    }

    void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Element with uuid = " + uuid + " not found");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - index);
            storage[size] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {
        return size;
    }

    //Added by AlexeyPavlov methods

    /**
     * Method searches the specified array "storage" of Resume for the specified value of field "uuid"
     *
     * @param uuid - search string
     * @return integer - -1: not found, >=0 - position in the array
     */
    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        return -1;
    }

}