package sort.bubble;


import org.junit.Assert;
import org.junit.Test;
import sort.ISort;
import sort.InitData;
import java.util.Comparator;

public class BubbleSortTest {

  @Test
  public void testSort() {
    ISort<Integer> sortor = new BubbleSort<>();
    Integer[] result =  sortor.sort(InitData.getTestData(), new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 - o2;
      }
    });
    for (int i=0;i<InitData.getExpectData().length;i++){
      Assert.assertEquals(InitData.getExpectData()[i].intValue(),result[i].intValue());
    }
  }
}