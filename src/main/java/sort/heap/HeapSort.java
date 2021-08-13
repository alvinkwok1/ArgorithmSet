package sort.heap;

import sort.ISort;

import java.util.Arrays;

public class HeapSort<T extends Comparable<T>> implements ISort<T> {

    /**
     * 排序
     *
     * @param data 待排数据
     * @return 排序后列表
     */
    @Override
    public T[] sort(T[] data) {
        // 堆初始化
        maxHeapBuild(data);
        // 执行堆排序
        for (int i = lastNodeIndex(data.length); i > 0; i--) {
            swap(data, 0, i);
            maxHeapify(data, i, 0);
        }
        return data;
    }


    private void maxHeapBuild(T[] data) {
        for (int i = parent(lastNodeIndex(data.length)); i >= 0; i--) {
            maxHeapify(data, data.length, i);
        }
    }

    private void maxHeapify(T[] data, int heapSize, int i) {
        while (i <= lastNodeIndex(heapSize)) {
            int l = left(i);
            int r = right(i);
            int largest = i;
            int last = lastNodeIndex(heapSize);
            if (l <= last && data[l].compareTo(data[i]) > 0) {
                largest = l;
            }
            if (r <= last && data[r].compareTo(data[largest]) > 0) {
                largest = r;
            }
            if (largest != i) {
                swap(data, i, largest);
                i = largest;
            } else {
                break;
            }
        }
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * (i + 1);
    }

    private int lastNodeIndex(int len) {
        return len - 1;
    }

    private void swap(T[] data, int a, int b) {
        T t = data[a];
        data[a] = data[b];
        data[b] = t;
    }


    public static void main(String[] args) {
        Integer[] arr = new Integer[10];

        arr[0] = 10;
        arr[1] = 9;
        arr[2] = 8;
        arr[3] = 7;
        arr[4] = 6;
        arr[5] = 5;
        arr[6] = 4;
        arr[7] = 3;
        arr[8] = 2;
        arr[9] = 1;


        HeapSort<Integer> sort = new HeapSort<>();
        sort.sort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}
