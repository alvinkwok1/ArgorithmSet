package sort.bubble;

import sort.ISort;

import java.util.Comparator;
import java.util.List;

public class BubbleSort<T extends Comparable> implements ISort<T> {


  @Override
  public List<? extends Comparable> sort(List<? extends Comparable> data) {
    int arrLen = data.size();
    if (arrLen==0) {
      return data;
    }
    for (int i=0;i<arrLen;i++) {
      for (int j=0;j<arrLen-i-1;j++) {
        Comparable a = data.get(j);
        Comparable b= data.get(j+1);
        if (a.compareTo(b)>=0) {
          swap(data,j,j+1);
        }
      }
    }
    return data;
  }

  @Override
  public List<T> sort(List<T> data, Comparator<T> comparator) {
    int arrLen = data.size();
    if (arrLen==0) {
      return data;
    }
    for (int i=0;i<arrLen;i++) {
      for (int j=0;j<arrLen-i-1;j++) {
        Comparable a = data.get(j);
        Comparable b= data.get(j+1);
        if (a.compareTo(b)>=0) {
          swap(data,j,j+1);
        }
      }
    }
    return data;
  }

  private void swap(List data,int i,int j){
    Object temp = data.get(i);
    data.set(i,data.get(j));
    data.set(j,temp);
  }

}
