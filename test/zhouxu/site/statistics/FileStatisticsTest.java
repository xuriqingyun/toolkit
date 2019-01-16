package zhouxu.site.statistics;


import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhouxu
 * Date: 2019-01-16 14:11
 */
public class FileStatisticsTest {

    private Istatistics istatistics;

    @Before
    public void init(){
        istatistics = new FileStatistics("E:/product/java/own/hot-standby/src");
    }

    @Test
    public void countTpye() {
        System.out.println(istatistics.fileSize()+":"+istatistics.directorySize());
    }
}