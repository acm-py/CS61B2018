public interface Deque <T>{
    boolean isEmpty();
    int size();
    void addFirst(T item);
    void addLast(T item);
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
}
