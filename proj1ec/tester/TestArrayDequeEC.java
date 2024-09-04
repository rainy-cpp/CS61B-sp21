package tester;
import static org.junit.Assert.*;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    // ArrayDeque 添加元素测试
    @Test
    public void addFirst() {
        StudentArrayDeque<Integer> arrayDeque = new StudentArrayDeque<>();
        // 添加100个元素
        for (int i = 0; i < 10; i++) {
            int randomNum = (int) (Math.random() * 100);
            arrayDeque.addFirst(randomNum);
        }
    }

    @Test
    public void addLast() {
        StudentArrayDeque<Integer> arrayDeque = new StudentArrayDeque<>();
        // 添加10个元素
        for (int i = 0; i < 100; i++) {
            int randomNum = (int) (Math.random() * 100);
            arrayDeque.addLast(randomNum);
        }
    }

    // ArrayDeque 删除元素测试
    @Test
    public void removeFirst() {
        StudentArrayDeque<Integer> arrayDeque = new StudentArrayDeque<>();
        // 添加100个元素
        for (int i = 0; i < 10; i++) {
            int randomNum = (int) (Math.random() * 100);
            arrayDeque.addFirst(randomNum);
        }
        // 删除30个元素
        for (int i = 0; i < 30; i++) {
            arrayDeque.removeFirst();
        }
    }

    @Test
    public void removeLast() {
        StudentArrayDeque<Integer> arrayDeque = new StudentArrayDeque<>();
        // 添加100个元素
        for (int i = 0; i < 10; i++) {
            int randomNum = (int) (Math.random() * 100);
            arrayDeque.addLast(randomNum);
        }
        // 删除30个元素
        for (int i = 0; i < 30; i++) {
            arrayDeque.removeLast();
        }
    }

    // StudentArray随机化测试
    @Test
    public void randomTest() {
        StudentArrayDeque<Integer> arrayDeque = new StudentArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            int operationNum = (int) (Math.random() * 3);
            if (operationNum == 0) {
                int randomNum = (int) (Math.random() * 100);
                arrayDeque.addFirst(randomNum);
            } else if (operationNum == 1) {
                int randomNum = (int) (Math.random() * 100);
                arrayDeque.addLast(randomNum);
            } else if (operationNum == 2) {
                arrayDeque.removeFirst();
            } else if (operationNum == 3) {
                arrayDeque.removeLast();
            }
        }
    }

    // Example Equals Test
    @Test
    public void exampleEqualsTest() {
        StudentArrayDeque<Integer> arrayDeque1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> arrayDeque2 = new ArrayDequeSolution<>();

        // 添加测试
        arrayDeque1.addFirst(1);
        arrayDeque2.addFirst(1);
        assertEquals(arrayDeque1, arrayDeque2);
    }

    // Equals测试
    @Test
    public void equalsTest() {
        StudentArrayDeque<Integer> arrayDeque1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> arrayDeque2 = new ArrayDequeSolution<>();
        for (int i = 0; i < 100; i++) {
            int operationNum = (int) (Math.random() * 3);
            if (operationNum == 0) {
                int randomNum = (int) (Math.random() * 100);
                arrayDeque1.addFirst(randomNum);
                arrayDeque2.addFirst(randomNum);
                assertEquals(arrayDeque1, arrayDeque2);
            }
            else if (operationNum == 1) {
                int randomNum = (int) (Math.random() * 100);
                arrayDeque1.addLast(randomNum);
                arrayDeque2.addLast(randomNum);
                assertEquals(arrayDeque1, arrayDeque2);
            }
            else if (operationNum == 2) {
                arrayDeque1.removeFirst();
                arrayDeque2.removeFirst();
                assertEquals(arrayDeque1, arrayDeque2);
            }
            else if (operationNum == 3) {
                arrayDeque1.removeLast();
                arrayDeque2.removeLast();
                assertEquals(arrayDeque1, arrayDeque2);
            }
        }
    }
}
