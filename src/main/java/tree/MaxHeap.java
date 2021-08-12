package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> {
    // 堆底层存储容器
    List<T> A;

    // 堆大小
    private int heapSize;

    public MaxHeap() {
        A = new ArrayList<>();
    }

    public MaxHeap(int cap) {
        A = new ArrayList<>(cap);
    }

    public MaxHeap(List<T> arr) {
        if (arr == null) {
            throw new IllegalArgumentException("array is null");
        }
        A = new ArrayList<>(arr.size());
        A.addAll(arr);

        // 建堆
        maxHeapBuild();
    }

    /**
     * 假设A[i]的左子树和右子树都是满足最大堆性质，调用该方法维护以A[i]为根的子树的最大堆性质
     *
     * @param i 结点i的下标
     */
    private void maxHeapify(int i) {
        while (i <= lastNodeIndex()) {
            int l = left(i);
            int r = right(i);
            int largest = i;
            if (l <= lastNodeIndex() && A.get(l).compareTo(A.get(i)) > 0) {
                largest = l;
            }
            if (r <= lastNodeIndex() && A.get(r).compareTo(A.get(largest)) > 0) {
                largest = r;
            }
            if (largest != i) {
                swap(A, i, largest);
                i = largest;
            } else {
                break;
            }
        }
    }

    /**
     * 建堆
     */
    private void maxHeapBuild() {
        heapSize = A.size();
        for (int i = parent(lastNodeIndex()); i >= 0; i--) {
            maxHeapify(i);
        }
    }

    /**
     * 像堆中插入元素
     *
     * @param x
     */
    public void maxHeapInsert(T x) {
        if (x == null) {
            throw new IllegalArgumentException("element can't be null");
        }
        A.add(x);
        heapSize++;
        int i = lastNodeIndex();
        while (i > 0 && A.get(i).compareTo(A.get(parent(i))) > 0) {
            swap(A, i, parent(i));
            i = parent(i);
        }
    }

    /**
     * 移除堆顶元素
     *
     * @return 移除元素
     */
    public T maxHeapRemove() {
        T max = A.get(0);
        int last = lastNodeIndex();
        swap(A, 0, last);
        A.remove(last);
        heapSize--;
        maxHeapify(0);
        return max;
    }

    /**
     * 移除指定元素
     *
     * @param i 元素下标
     * @return 返回移除元素
     */
    public T maxHeapRemove(int i) {
        if (i >= heapSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T target = A.get(i);
        // 先替换元素，并维护子树最大堆性质
        int last = lastNodeIndex();
        swap(A, i, last);
        A.remove(last);
        heapSize--;
        if (i == last) {
            return target;
        }

        maxHeapify(i);
        //上升维护
        while (i > 0 && A.get(i).compareTo(A.get(parent(i))) > 0) {
            swap(A, i, parent(i));
            i = parent(i);
        }
        return target;
    }

    private void swap(List<T> A, int a, int b) {
        T t = A.get(a);
        A.set(a, A.get(b));
        A.set(b, t);
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

    private int lastNodeIndex() {
        return heapSize - 1;
    }

    public int heapSize() {
        return heapSize;
    }


    public static void main(String[] args) {
        List<Integer> t = Arrays.asList(1, 2, 3, 10, 20, 9, 8);
        // 20 10 9 1 2 3 8
        MaxHeap<Integer> maxHeap = new MaxHeap<>(t);
        System.out.println(maxHeap);
        // 30 20 9 10 2 3 8 1
        maxHeap.maxHeapInsert(30);
        // 20 10 9 1 2 3 8
        Integer x = maxHeap.maxHeapRemove();
        System.out.println(x);
        // 20 8 9 1 2 3
        Integer y = maxHeap.maxHeapRemove(1);
        System.out.println(y);
    }
}
