package sort.bubble;

import sort.ArrayUtils;
import sort.ISort;

import java.util.Comparator;
import java.util.List;

public class BubbleSort<T extends Comparable<T>> implements ISort<T> {

  @Override
  public T[] sort(T[] data) {
    int arrLen = data.length;
    if (arrLen == 0) {
      return data;
    }
    for (int i = 0; i < arrLen; i++) {
      for (int j = 0; j < arrLen - i - 1; j++) {
        if (data[j].compareTo(data[j+1]) > 0) {
          ArrayUtils.swap(data, j, j+1);
        }
      }
    }
    return data;
  }
}
