package sort;

public class InitData {

  /**
   * 排序相关的测试数据
   *
   * @return
   */
  public static Integer[] getTestData() {
    return new Integer[]{1, 4, 2, 7, 9, 8, 3, 6};
  }

  /**
   * 对测试数据准备的预期数据
   * 目标是做升序排序
   *
   * @return
   */
  public static Integer[] getExpectData() {
    return new Integer[]{1, 2, 3, 4, 6, 7, 8, 9};
  }
}
