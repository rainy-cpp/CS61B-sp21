package deque;
import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    // ArrayDeque初始化和是否为空
    public void init() {
        System.out.printf("Init Test");
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertTrue(deque.isEmpty());
    }

    @Test
    // 添加元素测试
    public void addFirst() {
        System.out.printf("Add Element Test");
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            int randomNum = (int) (Math.random() * 100);
            deque.addFirst(randomNum);
        }
    }

    @Test
    public void addLast() {
        System.out.printf("Add Element Test");
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            int randomNum = (int) (Math.random() * 100);
            deque.addLast(randomNum);
        }
    }

    @Test
    // 删除元素测试
    public void removeFirst() {
        System.out.printf("Remove Element Test");
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            int randomNum = (int) (Math.random() * 100);
            deque.addFirst(randomNum);
        }
        for (int i = 0; i < 100; i++) {
            int randomNum = (int) (Math.random() * 100);
            deque.removeFirst();
        }
    }

    @Test
    public void removeLast() {
        System.out.printf("Remove Element Test");
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            int randomNum = (int) (Math.random() * 100);
            deque.addLast(randomNum);
        }
        for (int i = 0; i < 100; i++) {
            int randomNum = (int) (Math.random() * 100);
            deque.removeLast();
        }
    }

    @Test
    // resize测试
    public void resize() {
        System.out.printf("Resize Test");
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < 100; i++) {
            int operationNum = (int) (Math.random() * 3);
            if (operationNum == 0) {
                int randomNum = (int) (Math.random() * 100);
                deque.addFirst(randomNum);
            }
            else if (operationNum == 1) {
                int randomNum = (int) (Math.random() * 100);
                deque.addLast(randomNum);
            }
            else if (operationNum == 2) {
                deque.removeFirst();
            }
            else if (operationNum == 3) {
                deque.removeLast();
            }
        }
    }
}

