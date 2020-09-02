package io.starskyoio.dbstore.server.ds;

import java.util.LinkedHashSet;
import java.util.Set;

public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();
    private static Set<String> keyCache = new LinkedHashSet<>();

    public static String getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }

    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }

    public static boolean containsDataSourceKey(String key) {
        return keyCache.contains(key);
    }

    public static void addDataSourceKey(String key) {
        if (!containsDataSourceKey(key)) {
            keyCache.add(key);
        }
    }

    public static void addDataSourceKeys(Set<String> keys) {
        keyCache.addAll(keys);
    }


}
