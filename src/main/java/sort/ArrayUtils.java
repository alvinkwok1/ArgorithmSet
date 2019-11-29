package sort;

import java.util.List;

public class ArrayUtils {
  public static <T> void swap(T[] data, int a, int b){
    T temp = data[a];
    data[a]=data[b];
    data[b]=temp;
  }
}
