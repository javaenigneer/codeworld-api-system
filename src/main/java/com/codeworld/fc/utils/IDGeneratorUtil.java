package com.codeworld.fc.utils;

import java.util.Random;

/**
 * @author Lenovo
 */
public class IDGeneratorUtil {

    /**
     * 对象实例
     */
    private static IDGeneratorUtil generateIdUtil = new IDGeneratorUtil();

    /**
     * 随机数实例
     */
    private static Random random = new Random(9);

    /**
     * @return GenerateIdUtil
     * @throws
     * @author chenly
     * getInstance 获得对象实例
     * @since 1.0.0
     */
    public static IDGeneratorUtil getInstance() {
        return generateIdUtil;
    }

    /**
     * @return long
     * @throws
     * @author chenly
     * getNextId  返回一个当前时间的long类型数字
     * @since 1.0.0
     */
    public static synchronized long getNextId() {


        //单纯时间同时保存多条记录时会造成主键冲突  modifed by liusp at 20130312
        Long id = System.currentTimeMillis() + random.nextInt();

        String categoryId = String.valueOf(id);

        // 截取后面的6位数字
        categoryId = categoryId.substring(7);

        return Long.valueOf(categoryId);
    }

    /**
     * 生成用户Id主键
     * @return
     */
    public static synchronized long getUserId(){

        //单纯时间同时保存多条记录时会造成主键冲突  modifed by liusp at 20130312
        Long id = System.currentTimeMillis() + random.nextInt();

        String userId = String.valueOf(id);

        // 截取后面的5位数字
        userId = userId.substring(6);

        return Long.valueOf(userId);
    }


}
