package top.soft.classoa.utils;

import java.util.Date;

/**
 * @author lenovo
 * @description: TODO
 * @date 2024/12/7 16:51
 */
public class DateUtils {

    /**
     * 计算结束时间和开始时间的时差
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 相差的小时数
     */
    public static long getDiffHours(Date startTime, Date endTime) {
        long diff = endTime.getTime() - startTime.getTime();
        return diff / (1000 * 60 * 60);
    }
}