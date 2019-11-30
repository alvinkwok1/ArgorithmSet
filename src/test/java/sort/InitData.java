package sort;

public class InitData {

  /**
   * 排序相关的测试数据
   * @return
   */
  public static Integer[] getTestData() {
    return new Integer[]{6, 2, -1, 1};
  }

  /**
   * 对测试数据准备的预期数据
   * 目标是做升序排序
   * @return
   */
  public static Integer[] getExpectData() {
    return new Integer[]{-1, 1, 2, 6};
  }
}
