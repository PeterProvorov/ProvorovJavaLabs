import Sortings.SortingInterface;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by ThinkPad on 05.09.2016.
 */
public class SortTest {
    SortTest() {

    }

    SortTest(SortingInterface s) {
        sorter = s;
    }

    private <T extends Comparable<T>> void test(T[] arr) {
        test(arr, Comparable::compareTo);
    }

    private <T extends Comparable<T>> void test(T[] arr, Comparator<? super T> c) {
        T[] originArr = arr.clone();
        T[] sortedArr = arr.clone();

        sorter.sort(sortedArr, c);
        assertArrayEquals(sortedArr, originArr);

    }

    @Test
    public void oddIntArray() {
        test(new Integer[]{7, 3, 9, 1, 2});
    }

    @Test
    public void oddCharArray() {
        test(new Character[]{'z', 'a', 'c', 'w', 'f'});
    }

    @Test
    public void evenArray() {
        test(new Integer[]{7, 3, 9, 1, 2, -1});
    }

    @Test
    public void empty() {
        test(new Integer[]{});
    }

    @Test
    public void monoArray() {
        test(new Integer[]{1, 1, 1, 1, 1, 1, 1, 1, 1});
    }

    @Test
    public void reverseOrderArray() {
        test(new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
    }

    @Test
    public void OrderArray() {
        test(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    private SortingInterface sorter;
}