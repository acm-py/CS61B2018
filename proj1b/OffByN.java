public class OffByN implements CharacterComparator {

    private int n;
    public OffByN(int N) {
        this.n = N;
    }

    @Override
    // 这个函数的作用是如果 x 与 y的acsii码值之差为N，返回true，否则返回false
    public boolean equalChars(char x, char y) {
//        if ((int)x - (int)y == 1 || ((int)x - (int)y) == -1){
//            return true;
//        }
//        return false;
        // TODO 这种写法更美
        if (x - y == n || x - y == -n) {
            return true;
        }
        return false;
    }
}
