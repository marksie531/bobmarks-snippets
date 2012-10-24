package org.bobmarks.caching;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

/**
 * Class to investigate the JSC cache system.
 */
public class JCSCacheFacade {

    private static final String cacheRegionName = "df";

    private JCS cache = null;

    /**
     * Constructor to JCS cache.
     */
    public JCSCacheFacade() {
        try {
            cache = JCS.getInstance(getCacheRegionName());
        } catch (CacheException e) {
            System.err.println("Problem initializing cache for region name ["
                    + this.getCacheRegionName() + "]." + e.getMessage());
        }
    }

    /**
     * Delete put method.
     * 
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        try {
            cache.put(key, value);
        } catch (CacheException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delegate get method.
     * 
     * @param key
     * @return
     */
    public Object get(String key) {
        return cache.get(key);
    }

    /**
     * Return the cache region name.
     * 
     * @return
     */
    public String getCacheRegionName() {
        return cacheRegionName;
    }

}