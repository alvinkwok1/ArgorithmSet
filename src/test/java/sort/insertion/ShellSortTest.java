package sort.insertion;

import org.junit.Assert;
import org.junit.Test;
import sort.ISort;
import sort.InitData;

import java.util.Comparator;

import static org.junit.Assert.*;

public class ShellSortTest {
  @Test
  public void testShellSort() {
    ISort<Integer> sortor = new ShellSort<>();
    Integer[] result = sortor.sort(InitData.getTestData(), new DefaultComparator());
    System.out.println("使用希尔增量");
    assertResult(result);
  }

  @Test
  public void testHibbortIncrementShellSort() {
    ShellSort<Integer> sortor = new ShellSort<>();
    Integer[] testData = InitData.getTestData();
    Integer[] result = sortor.sort(testData, new DefaultComparator(), new ShellSort.HibbardIncrement(testData.length));
    System.out.println("使用hibbort增量");
    assertResult(result);
  }

  @Test
  public void testKnuthIncrementShellSort() {
    ShellSort<Integer> sortor = new ShellSort<>();
    Integer[] testData = InitData.getTestData();
    Integer[] result = sortor.sort(testData, new DefaultComparator(), new ShellSort.KnuthIncrement(testData.length));
    System.out.println("使用knuth增量");
    assertResult(result);
  }

  private class DefaultComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
      return o1 - o2;
    }
  }

  private void assertResult(Integer[] result) {
    for (int i = 0; i < InitData.getExpectData().length; i++) {
      Assert.assertEquals(InitData.getExpectData()[i].intValue(), result[i].intValue());
    }

    System.out.println("希尔排序测试结束\n结果：");
    for (int i = 0; i < result.length; i++) {
      System.out.print(result[i]);
    }
    System.out.println();
  }
}