import org.junit.Test;

public class Palindrome {
    // 这个方法的作用就是讲string word 顺序遍历每个字符，存储到list中
    public Deque<Character> wordToDeque(String word){
        // 创建存储的容器
        Deque<Character> deque = new ArrayDeque<>();
        // 遍历每个字符, 在字符串中获得每个字符使用charAt
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        // tip：使用递归会让代码更漂亮
        // 考虑base case
        return helprecurion(deque);
    }

    private boolean helprecurion(Deque word) {
        if (word.size()<=1) {
            return true;
        }
        if(word.removeFirst()==word.removeLast()){
            return helprecurion(word);
        }
        return false;
    }

    // 迭代版本
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        if ( deque.size() <= 1) {
            return true;
        }
        int len = deque.size();
        for (int i = 0; i < len / 2; i++) {
            if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                return false;
            }
        }
        return true;
    }

}
