package Sortings;

import java.util.Comparator;
/**
 * Created by ThinkPad on 10.09.2016.
 */
public interface SortingInterface {
    default <T extends Comparable<T>> void sort(T[] arr)
    {
        sort(arr, Comparable::compareTo);
    }

    <T> void sort(T[] arr, Comparator<? super T> c);
}
