package sort.selection;

import sort.ArrayUtils;
import sort.ISort;

import java.util.Comparator;
import java.util.List;

public class SelectionSort<T> implements ISort<T> {
  @Override
  public T[] sort(T[] data, Comparator<T> comparator) {
    int arrLen = data.length;
    if (arrLen ==0) {
      return data;
    }

   for (int i=0;i<arrLen;i++) {
     // 选最小的
     int minIndex = 0;
     T min = data[i];
     for (int j=i;j<arrLen;j++) {
       if (comparator.compare(min,data[j])>0) {
         min=data[j];
         minIndex = j;
       }
     }
     // 放置到已排序的末尾
     ArrayUtils.swap(data,i,minIndex);
   }
    return data;
  }
}
