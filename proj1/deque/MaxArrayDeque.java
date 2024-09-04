package deque;
import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        // ArrayDeque初始化
        super();
        // 传入比较器
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        return findMax(c);
    }

    private T findMax(Comparator<T> c) {
        Iterator <T> iterator = iterator();
        T maxElemnet = iterator.next();
        while(iterator.hasNext()) {
            T currentElement = iterator.next();
            if (c.compare(currentElement, maxElemnet) > 0) {
                maxElemnet = currentElement;
            }
        }
        return maxElemnet;
    }
}
