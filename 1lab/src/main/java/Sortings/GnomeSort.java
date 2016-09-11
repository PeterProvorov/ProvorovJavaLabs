package Sortings;

import java.util.Comparator;

import static Sortings.SortUtils.checkInAscendingOrder;
import static Sortings.SortUtils.swap;

/**
 * Created by ThinkPad on 10.09.2016.
 */
public class GnomeSort implements SortingInterface {
    public static <T extends Comparable<T>> void sort(T[] arr) {
        new GnomeSort().sort(arr, Comparable::compareTo);
    }

    @Override
    public <T> void sort(T[] arr, Comparator<? super T> c) {
        boolean isOrdered = checkInAscendingOrder(arr, c);
        if(true == isOrdered)
            return;

        int length = arr.length;
        for(int i = 0; i < length;)
        {
            if(i == 0 ||  c.compare(arr[i - 1], arr[i]) <= 0)
                i++;
            else {
                swap(arr, i-1, i);
                i--;
            }
        }
    }
}
