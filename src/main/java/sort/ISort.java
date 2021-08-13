package sort;

import java.util.Comparator;
import java.util.List;

/**
 * 基本排序接口
 *
 */
public interface ISort<T extends Comparable<T>> {

  /**
   * 排序
   * @param data 待排数据
   * @return 排序后列表
   */
  T[] sort(T[] data);
}
