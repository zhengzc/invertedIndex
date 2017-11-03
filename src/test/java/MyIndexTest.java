import com.zzc.invertedIndex.MyIndex;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @auther Created by johnson.zheng
 * @pray Code is far away from bug with the animal alpaca protecting. amen!
 * @date 2017/11/3
 */
@Slf4j
public class MyIndexTest {

    @Test
    public void test() {
        MyIndex myIndex = new MyIndex();
        myIndex.add("my name is zhichao.zheng");
        myIndex.add("he is johnson.zheng");
        myIndex.add("I like programing");
        myIndex.add("he like eat");

        log.debug("===>{}", myIndex.search("zheng"));
        log.debug("===>{}", myIndex.search("l"));
        log.debug("===>{}", myIndex.search("i"));
    }
}
