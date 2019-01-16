package zhouxu.site.statistics;

/**
 * Created with IntelliJ IDEA.
 * Description:业务警告
 * User: zhouxu
 * Date: 2019-01-16 10:09
 */
public class BizException extends RuntimeException {
    public BizException(String exception){
        super(exception);
    }
}
