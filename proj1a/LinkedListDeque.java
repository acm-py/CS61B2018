public class LinkedListDeque<T>{
    /* inner class Node. */
    private class Node {
        // item 存在node里
        private T item;
        // pre 指向当前节点的前一个
        private Node pre;
        // next 指向当前节点的后一个
        private Node next;

        // 构造函数
        public Node(T n, Node ppre, Node nnext) {
            item = n;
            pre = ppre;
            next = nnext;
        }

        // 构造函数
        public Node(Node ppre, Node nnext) {
            pre = ppre;
            next = nnext;
        }
    }
    // 哨兵节点 sentinel node
    // 此外哨兵是指向自身的。
    // sentinel.pre 指向sentinel
    // sentinel.next 指向sentinel
    // 循环
    // 参考设计 https://docs.google.com/presentation/d/1suIeJ1SIGxoNDT8enLwsSrMxcw4JTvJBsMcdARpqQCk/pub?start=false&loop=false&delayms=3000&slide=id.g829fe3f43_0_376
    // 也可以设计为 两个哨兵，一个在头，一个在尾
    // 可参考设计 https://docs.google.com/presentation/d/1suIeJ1SIGxoNDT8enLwsSrMxcw4JTvJBsMcdARpqQCk/pub?start=false&loop=false&delayms=3000&slide=id.g829fe3f43_0_291
    private final Node sentinel;
    // 元素个数
    private int size;

    // LinkedListDeque 的构造函数
    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node newList = new Node(item, sentinel, sentinel.next);
        sentinel.next.pre = newList;
        sentinel.next = newList;
        size ++;
    }

    public void addLast(T item) {
        Node newList = new Node(item, sentinel.pre, sentinel);
        sentinel.pre.next = newList;
        sentinel.pre = newList;
        size ++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node cur = sentinel.next;
        while (cur != null) {
            System.out.print(cur.item + "->");
            cur = cur.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T result = sentinel.next.item;
        // 头节点.next.pre = sentinel -> 使头节点的后一个节点的pre 指向sentinel
        sentinel.next.next.pre = sentinel;
        // 使头节点的前一个节点的next 指向头节点的后一个节点
        sentinel.next = sentinel.next.next;
        size --;
        return result;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        // 取出最后一个节点
        T result = sentinel.pre.item;
        // 使尾节点的前一个节点的next 指向sentinel
        sentinel.pre.pre.next = sentinel;
        // 使sentinel.pre 指向尾节点的前一个节点
        sentinel.pre = sentinel.pre.pre;
        size --;
        return result;
    }

    // 迭代get
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node pre = sentinel;
        for (int i = 0; i <= index; i++) {
            pre = pre.next;
        }
        return pre.item;
    }

    // get的递归版本
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        } else {
            return getRecursiveHelp(sentinel.next, index);
        }
    }

    private T getRecursiveHelp(Node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelp(start.next, index -1 );
        }
    }
}
