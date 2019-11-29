package sort;

import java.util.Comparator;
import java.util.List;

/**
 * 基本排序接口
 *
 */
public interface ISort<T> {

  /**
   * 使用外部比较器进行排序
   * @param data
   * @param comparator
   * @return
   */
  T[] sort(T[] data, Comparator<T> comparator);
}
