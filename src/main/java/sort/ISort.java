package sort;

import java.util.Comparator;
import java.util.List;

/**
 * 基本排序接口
 *
 */
public interface ISort<T> {
  /**
   * 排序接口
   * @param data
   * @return
   */
  List<? extends Comparable> sort(List<? extends Comparable> data);

  List<T> sort(List<T> data, Comparator<T> comparator);
}
