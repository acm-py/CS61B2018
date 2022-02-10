public class ArrayDeque<T> implements Deque<T>{

    // 使用循环数组存储数据
    private T[] array;
    // 元素个数
    private int size;
    // 数组长度
    private int length;
    // 循环数组的头索引, 在头部增加数组元素时，头索引左移。
    private int front;
    // 循环数组的尾索引 尾部增加数组元素时，尾索引右移。
    private int last;

    // 构造函数
    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        front = 4;
        last = 4;
    }

    // 索引正确-1
    private int minusOne(int index) {
        // 当索引是0时，下次 - 1 应该回到length - 1
        // Tip ： 循环数组
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }

    // 索引正确 + 1
    private int plusOne(int index, int mod) {
        // 防止越界
        index  %= mod;
        // 当索引等于mod - 1 时，应该回到0位置;
        if (index == mod - 1) {
            return 0;
        }
        return index + 1;
    }

    // 扩容操作
    private void grow() {
        T[] newArray = (T[]) new Object[length * 2];
        // 头索引
        int ptr1 = front;
        // 原数组最大长度，同时也是新数组头索引（因为front = last = len/2，此时的len是原来的2倍)
        int ptr2 = length;
        while (ptr1 != last) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length*2);
        }
        front = length;
        last = ptr2;
        array = newArray;
        length *= 2;
    }

    // 缩容操作
    private void shrink() {
        T[] newArray = (T[]) new Object[length / 2];
        // 头索引
        int ptr1 = front;
        // 原数组最大长度，同时也是新数组头索引（因为front = last = len/2，此时的len是原来的二分之一)
        int ptr2 = length/4;
        while (ptr1 != last) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length / 2);
        }
        front = length / 4;
        last = ptr2;
        array = newArray;
        length /= 2;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T item) {
        // 即front = last + 1 时
        if (size == length - 1) {
            grow();
        }
        front = minusOne(front);
        array[front] = item;
        size ++;
    }

    @Override
    public void addLast(T item) {
        if (size == length - 1) {
            grow();
        }
        array[last] = item;
        last = plusOne(last, length);
        size++;
    }

    @Override
    public T removeFirst() {
        // 此时的利用率在25%以下，需要缩容
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        T result = array[front];
        front = plusOne(front, length);
        size --;
        return result;
    }

    @Override
    public T removeLast() {
        // 此时的利用率在25%以下，需要缩容
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        last = minusOne(last);
        size --;
        return array[last];
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int ptr = front;
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr, length);
        }
        return array[ptr];
    }

    @Override
    public void printDeque() {
        int ptr = front;
        while (ptr != last) {
            System.out.print(array[ptr] + " ");
            ptr = plusOne(ptr, length);
        }
    }
}
