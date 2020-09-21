package com.hive.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

@Description(value = "_FUNC_(str) - Return str with all Characters changed to uppercase!"
        , extended = "Example:\n>SELECT _FUNC_(str) from src")
/**
 * Author:BYDylan
 * Date:2020/9/21
 * Description: 传统的写法
 */
public class MyUpper extends UDF {

    //进来一条数据，输出一条数据
    public Text evaluate(Text text) {
        if (text != null) {
            return new Text(text.toString().toUpperCase());
        } else {
            return null;
        }
    }


}

