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
     // 根据比较获取目标索引
     int targetIndex = i;
     T target = data[i];
     for (int j=i;j<arrLen;j++) {
       if (comparator.compare(target,data[j])>0) {
         target=data[j];
         targetIndex = j;
       }
     }
     // 放置到已排序的末尾
     ArrayUtils.swap(data,i,targetIndex);
   }
    return data;
  }
}
