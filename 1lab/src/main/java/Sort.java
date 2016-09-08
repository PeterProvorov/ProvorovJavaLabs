/**
 * Created by ThinkPad on 05.09.2016.
 * @author Peter Provorov
 * @version 1.0
 */

public class Sort {
    public static void gnomeSort(Object[] arr){
        boolean isOrdered = checkInAscendingOrder(arr);
        if(true == isOrdered)
            return;

        int length = arr.length;
        for(int i = 0; i < length;)
        {
            if(i == 0 || ((Comparable) arr[i - 1]).compareTo(arr[i]) <= 0)
                i++;
            else{
                swap(arr, i-1, i);
                i--;
            }
        }
    }

    public static void heapSort(Object[] arr){
        boolean isOrdered = checkInAscendingOrder(arr);
        if(true == isOrdered)
            return;

        int length = arr.length;

        //make binary heap
        for(int i = length / 2; i > 0; i--)
            sieve(arr, i, length);

        while (length > 1) {
            //Меняем максимум с последним элементом...
            swap(arr, 0, length - 1);

            //... и перестравиваем сортирующее дерево
            //для неотсортированной части массива
            length = length - 1;
            sieve(arr, 1, length);

        }
    }

    private static void sieve(Object[] arr, int i, int length){
        //Смотрим потомков в пределах ветки
        while (i <= length / 2) {
            Object parent = arr[i - 1];

            int maxChild = i + i - 1; //2*(i - 1) + 1 //left

            if ((maxChild + 1 < length) && (((Comparable) arr[maxChild + 1]).compareTo(arr[maxChild]) > 0))
                maxChild++;

            if (((Comparable) parent).compareTo(arr[maxChild]) >= 0){
                break;
            } else {
                swap(arr, i - 1, maxChild);
                i = maxChild + 1;
            }
        }

        //Родитель в итоге меняется местами с наибольшим из потомков
        //(или остаётся на своём месте, если все потомки меньше его)
        //arr[i - 1] = parent;
    }

    private static void swap(Object[] arr, int a, int b) {
        Object t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    public static boolean checkInAscendingOrder(Object[] arr) {
        int length = arr.length;

        for(int i = 1; i < length; i++)
        {
            if(((Comparable) arr[i - 1]).compareTo(arr[i]) > 0)
                return false;
        }

        return true;
    }
}
