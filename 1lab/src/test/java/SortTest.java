import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by ThinkPad on 05.09.2016.
 */
public class SortTest {
    @Ignore
    @Test
    public void sortOfOddArr() throws Exception{
        Integer[] arr = new Integer[]{4, 8, 3, 1, -7, 5, 2, 12, 13, -10, 9, 6, -14, 11, 15, 0, 7};
        Integer[] sortedArr = new Integer[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);

        Arrays.sort(sortedArr);

        //Sort.gnomeSort(arr);
        Sort.heapSort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void sortOfEvenArr() throws Exception{
        Integer[] arr = new Integer[]{1, -1, 12, 0};
        Integer[] sortedArr = new Integer[]{-1, 0, 1, 12};

        //Sort.gnomeSort(arr);
        Sort.heapSort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void emptyArr() throws Exception{
        Integer[] arr = new Integer[]{};
        Integer[] sortedArr = new Integer[]{};

        //Sort.gnomeSort(arr);
        Sort.heapSort(arr);
        assertArrayEquals(sortedArr, arr);
    }
}