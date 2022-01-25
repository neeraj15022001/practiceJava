package Arrays;

public class ArrayListSelf {
    private int[] arr;
    private int size;
    private int capacity;

    public ArrayListSelf() {
        this.arr = new int[2];
        this.size = 0;
        this.capacity = 2;
    }

    public ArrayListSelf(int capacity) {
        this.arr = new int[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public void insert(int element) {
        if (size == capacity) {
            capacity *= 2;
            int[] newArr = new int[capacity];
            size = 0;
            for (int i = 0; i < arr.length; i++) {
                newArr[size] = arr[i];
                size++;
            }
            arr = newArr;
        }
        arr[size] = element;
        size++;
    }

    public int get(int index) throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return arr[index];
    }

    public void set(int index, int value) throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        arr[index] = value;
    }

    public void remove() {
        if (size == 0) {
            System.out.println("Cannot Remove Element as Array is Already Empty");
            return;
        }
        size--;
        arr[size] = 0;
    }

    public void print() {
        if(size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public int size() {
        return this.size;
    }
}
