package net.futureorigin.architecture.sample.cola.common.util;

import com.google.gson.Gson;

/**
 * JsonUtils
 * <p></p>
 *
 * @author Leander Lee on 2021/9/2.
 */
public class JsonUtils {

    public static String toJsonString(Object o) {
        return new Gson().toJson(o);
    }
}
