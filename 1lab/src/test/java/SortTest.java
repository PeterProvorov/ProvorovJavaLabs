import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by ThinkPad on 05.09.2016.
 */
public class SortTest {
    @Test
    public void heapOrderArray() throws Exception{
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5};
        Integer[] sortedArr = new Integer[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);

        Sort.heapSort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void gnomeOrderArray() throws Exception{
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5};
        Integer[] sortedArr = new Integer[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);

        Sort.gnomeSort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void heapReverseOrderArray() throws Exception{
        Integer[] arr = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] sortedArr = new Integer[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);

        Arrays.sort(sortedArr);

        Sort.heapSort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void gnomeReverseOrderArray() throws Exception{
        Integer[] arr = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] sortedArr = new Integer[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);

        Arrays.sort(sortedArr);

        Sort.gnomeSort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void heapSortOfOddArr() throws Exception{
        Integer[] arr = new Integer[]{4, 8, 3, 1, -7, 5, 2, 12, 13, -10, 9, 6, -14, 11, 15, 0, 7};
        Integer[] sortedArr = new Integer[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);

        Arrays.sort(sortedArr);

        Sort.heapSort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void gnomeSortOfOddArr() throws Exception{
        Integer[] arr = new Integer[]{4, 8, 3, 1, -7, 5, 2, 12, 13, -10, 9, 6, -14, 11, 15, 0, 7};
        Integer[] sortedArr = new Integer[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);

        Arrays.sort(sortedArr);

        Sort.gnomeSort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void heapSortOfEvenArr() throws Exception{
        Integer[] arr = new Integer[]{1, -1, 12, 0};
        Integer[] sortedArr = new Integer[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);

        Arrays.sort(sortedArr);

        Sort.heapSort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void gnomeSortOfEvenArr() throws Exception{
        Integer[] arr = new Integer[]{1, -1, 12, 0};
        Integer[] sortedArr = new Integer[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);

        Arrays.sort(sortedArr);

        Sort.gnomeSort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void heapEmptyArr() throws Exception{
        Integer[] arr = new Integer[]{};
        Integer[] sortedArr = new Integer[]{};

        Sort.heapSort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void gnomeEmptyArr() throws Exception{
        Integer[] arr = new Integer[]{};
        Integer[] sortedArr = new Integer[]{};

        Sort.gnomeSort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void heapSortOfStrings() throws Exception{
        Character[] arr = new Character[]{'c', 'z', 'a', 'b', 'u', 'o' , 'w'};
        Character[] sortedArr = new Character[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);

        Arrays.sort(sortedArr);

        Sort.heapSort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    @Test
    public void gnomeSortOfStrings() throws Exception{
        Character[] arr = new Character[]{'c', 'z', 'a', 'b', 'u', 'o' , 'w'};
        Character[] sortedArr = new Character[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);

        Arrays.sort(sortedArr);

        Sort.gnomeSort(arr);
        assertArrayEquals(sortedArr, arr);
    }
}