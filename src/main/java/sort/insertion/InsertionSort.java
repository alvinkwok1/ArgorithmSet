package sort.insertion;

import sort.ArrayUtils;
import sort.ISort;

import java.util.Comparator;
import java.util.List;

public class InsertionSort<T extends Comparable<T>> implements ISort<T> {
  @Override
  public T[] sort(T[] data) {
    int arrLen = data.length;
    if (arrLen ==0) {
      return data;
    }
    for (int i=1;i<arrLen;i++) {
      // 每次都从头找
      int j=0;
      // 用临时变量存储，后续移动数据时原位置会被覆盖占用
      T temp = data[i];
      // 寻找可插入位置
      while (j<i && temp.compareTo(data[j])>0) {
        j++;
      }
      // 将比temp大的数值都往后移动
      for (int k=i;k>j;k--) {
        data[k]=data[k-1];
      }
      // 插入到j位置上
      data[j]=temp;
    }
    return data;
  }
}
