package Sortings;

import java.util.Comparator;

/**
 * Created by ThinkPad on 10.09.2016.
 */

final class SortUtils {

    private SortUtils() {

    }

    static <T> void swap(T[] arr, int a, int b) {
        T t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    static <T> boolean checkInAscendingOrder(T[] arr, Comparator<? super T> c) {
        int length = arr.length;

        for(int i = 1; i < length; i++)
        {
            if(c.compare(arr[i - 1], arr[i]) > 0)
                return false;
        }

        return true;
    }
}
