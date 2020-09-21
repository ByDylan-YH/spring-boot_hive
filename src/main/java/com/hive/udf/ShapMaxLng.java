package com.hive.udf;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author:BY
 * Date:2019/10/19
 * Description:取shapae字段最大的经度
 */
class ShapMaxLng extends GenericUDF {
    //输入参数
    private PrimitiveObjectInspector input;
    // 输出参数
    private PrimitiveObjectInspector output;

    public ObjectInspector initialize(ObjectInspector[] args) {
        input = (PrimitiveObjectInspector) args[0];
        assert input.getPrimitiveCategory() == PrimitiveObjectInspector.PrimitiveCategory.STRING;

        output = PrimitiveObjectInspectorFactory.writableStringObjectInspector;
        return output;
    }

    public Object evaluate(DeferredObject[] args) throws HiveException {
        List<String> list = new ArrayList<>();
        String shape = (String) input.getPrimitiveJavaObject(args[0].get());
        String[] jwd = shape.replace("POLYGON  (( ", "").replace("MULTIPOLYGON  ((( ", "").replace(")", "").replace("(", "").split(", ");
        for (String lng_lat : jwd) {
            String[] lng = lng_lat.split(" ");
            list.add(lng[0]);
        }
        return new Text(Collections.max(list));
    }

    public String getDisplayString(String[] strings) {
        return null;
    }
}
