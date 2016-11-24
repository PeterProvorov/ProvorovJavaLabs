import Sortings.SortingInterface;
import org.junit.Test;

import java.util.Comparator;
import java.util.Random;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by ThinkPad on 05.09.2016.
 */
public class SortTest {

    SortTest(SortingInterface s) {
        sorter = s;
    }

    private <T extends Comparable<T>> void test(T[] arr) {
        test(arr, Comparable::compareTo);
    }

    private <T> void test(T[] arr, Comparator<? super T> c) {
        T[] originArr = arr.clone();
        T[] sortedArr = new T[arr.length];

        sorter.sort(sortedArr, c);

        assertTrue(checkCorrectSorting(originArr, sortedArr, c));

    }

    private <T> boolean checkCorrectSorting(T[] originArr, T[] sortedArr, Comparator<? super T> c) {
        int length = sortedArr.length;
        if(originArr.length != sortedArr.length)
            return false;

        //check order
        for(int i = 1; i < length; i++) {
            if(c.compare(sortedArr[i - 1], sortedArr[i]) > 0)
                return false;
        }

        //check correct array content
        for(T arrElem : sortedArr) {
            int sameElemCount = 0;
            for(T arrElem1 : sortedArr) {
                if(arrElem.equals(arrElem1))
                    sameElemCount++;
            }

            for(T arrElem1 : originArr) {
                if(arrElem.equals(arrElem1))
                    sameElemCount--;
            }

            if(sameElemCount != 0)
                return false;
        }

        return true;
    }

    private Integer[] getRandomArray(int arrLength) {
        Integer[] arr = new Integer[arrLength];

        Date date = new Date();
        final Random random = new Random(date.getTime());

        for(int i = 0; i < arrLength; i++)
            arr[i] = random.nextInt();

        return arr;
    }

    @Test
    public void oddIntArray() {
        for(int i = 0; i < 10; i++)
            test(getRandomArray(2 << i + 1));
    }

    @Test
    public void oddCharArray() {
        test(new Character[]{'z', 'a', 'c', 'w', 'f'});
    }

    @Test
    public void evenArray() {
        for(int i = 0; i < 10; i++)
            test(getRandomArray(2 << i));
    }

    @Test
    public void empty() {
        test(new Integer[]{});
    }

    @Test
    public void monoArray() {
        for(int i = -5; i < 5; i++) {
            final int size = 100;
            Integer[] arr = new Integer[size];
            for(int g = 0; g < size; g++)
                arr[g] = i;

            test(arr);
        }
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