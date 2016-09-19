package Sortings;

import java.util.Comparator;

import static Sortings.SortUtils.checkInAscendingOrder;
import static Sortings.SortUtils.swap;

/**
 * Created by ThinkPad on 10.09.2016.
 */
public class HeapSort implements SortingInterface{

    @Override
    public <T> void sort(T[] arr, Comparator<? super T> c) {
        boolean isOrdered = checkInAscendingOrder(arr, c);
        if(isOrdered)
            return;

        int length = arr.length;

        //make binary heap
        for(int i = length / 2; i > 0; i--)
            sieve(arr, i, length, c);

        while (length > 1) {
            //Swap max and last elem
            swap(arr, 0, length - 1);

            //rebuild binary heap without last element of array
            length = length - 1;
            sieve(arr, 1, length, c);

        }
    }

    private static <T> void sieve(T[] arr, int i, int length, Comparator<? super T> c) {
        while (i <= length / 2) {
            T parent = arr[i - 1];

            int maxChild = i + i - 1; //2*(i - 1) + 1 //left

            if ((maxChild + 1 < length) && c.compare(arr[maxChild + 1], arr[maxChild]) > 0)
                maxChild++;

            if (c.compare(parent, arr[maxChild]) >= 0) {
                break;
            } else {
                swap(arr, i - 1, maxChild);
                i = maxChild + 1;
            }
        }
    }
}
