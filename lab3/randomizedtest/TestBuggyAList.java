package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
//    @Test
//    public void testThreeNumber() {
//        AListNoResizing<Integer> listA = new AListNoResizing<Integer>();
//        BuggyAList<Integer> listB = new BuggyAList<Integer>();
//
//        // 向listA添加4,5,6
//        listA.addLast(4);
//        listA.addLast(5);
//        listA.addLast(6);
//
//        // 向listB添加4,5,6
//        listB.addLast(4);
//        listB.addLast(5);
//        listB.addLast(6);
//
//        // 比较两个数组的大小
//        assertEquals(listA, listB);
//
//        // remove
//        assertEquals(listA.removeLast(), listB.removeLast());
//        assertEquals(listA.removeLast(), listB.removeLast());
//        assertEquals(listA.removeLast(), listB.removeLast());
//    }

    @Test
    public void randomizedTest() {
            AListNoResizing<Integer> correctList = new AListNoResizing<>();
            BuggyAList<Integer> brokenList = new BuggyAList<Integer>();

            int N = 5000;
            for (int i = 0; i < N; i += 1) {
                int operationNumber = StdRandom.uniform(0, 4);
                if (operationNumber == 0) {
                    // addLast the same value to both lists
                    int randVal = StdRandom.uniform(0, 100);
                    correctList.addLast(randVal);
                    brokenList.addLast(randVal);

                } else if (operationNumber == 1) {
                    // assert if size are equal
                    assertEquals(correctList.size(), brokenList.size());

                } else if (operationNumber == 2) {
                    // assert if getLast is equal
                    if (correctList.size() > 0 && brokenList.size() > 0){
                        assertEquals(correctList.getLast(), brokenList.getLast());
                    }

                } else if (operationNumber == 3) {
                    // assert if removeLast is equal
                    if (correctList.size() > 0 && brokenList.size() > 0){
                        assertEquals(correctList.removeLast(), brokenList.removeLast());
                    }
                }
            }
    }
}