package com.cn.hnust.dao;

import java.lang.annotation.*;

/**
 *  DAO层所对应的表描述
 * User: wuqingming
 * Date: 14-10-23
 * Time: 上午9:28
 * To change this template use File | Settings | File Templates.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface TableDesc {
   public String nameSpace() default "";
   public String tableName() default "";
}
