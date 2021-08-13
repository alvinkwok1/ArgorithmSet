package sort.insertion;

import sort.ArrayUtils;
import sort.ISort;


/**
 * 希尔排序属于插入排序的一种，本案例的增量选用shell增量
 *
 * @param <T>
 */
public class ShellSort<T extends Comparable<T>> implements ISort<T> {

    public T[] sort(T[] data, Increment increment) {
        int arrLen = data.length;
        if (arrLen == 0) {
            return data;
        }
        int startIncrement = increment.next();
        for (int in = startIncrement; in > 0; in = increment.next()) {
            // 对每一个分组进行直接插入排序
            for (int i = in; i < arrLen; i++) {
                int j = i;
                // 从分组尾部到头部，将
                while (j - in >= 0 && data[j - in].compareTo(data[j]) > 0) {
                    ArrayUtils.swap(data, j - in, j);
                    j -= in;
                }
            }
        }

        return data;
    }

    @Override
    public T[] sort(T[] data) {
        int arrLen = data.length;
        if (arrLen == 0) {
            return data;
        }
        return sort(data, new ShellIncrement(data.length));
    }


    /**
     * 希尔增量
     */
    public static class ShellIncrement implements Increment {

        int k = 1;

        public ShellIncrement(int arrLen) {
            k = arrLen / 2;
        }

        @Override
        public int next() {
            return k /= 2;
        }
    }


    public static class HibbardIncrement implements Increment {

        int k = 1;

        public HibbardIncrement(int arrLen) {
            k = (int) (Math.log(arrLen) / Math.log(2));
        }

        /**
         * hibbard增量
         * 递推公式为:
         * H(1)=1,H(t)=2*H(t-1)+1,t为正整数
         * 该增量序列下，最坏时间复杂度为O(n^(3/2)),平均复杂度为O(n^(5/4))
         *
         * @return
         */
        @Override
        public int next() {
            int seq = 1;
            if (k > 1) {
                seq = (int) (Math.pow(2, k--) - 1);
            } else if (k == 1) {
                seq = 1;
                k--;
            } else {
                seq = 0;
            }
            return seq;
        }
    }


    public static class KnuthIncrement implements Increment {
        int k;

        public KnuthIncrement(int arrLen) {
            k = (int) (Math.log(arrLen) / Math.log(3));
        }

        @Override
        public int next() {
            int seq = 1;
            if (k > 1) {
                seq = (int) (Math.pow(3, k--) - 1);
            } else if (k == 1) {
                seq = 1;
                k--;
            } else {
                seq = 0;
            }
            return seq;
        }
    }


    /**
     * 增量序列接口
     * 用于生成指定的序列值
     */
    public interface Increment {
        int next();
    }
}
