package org.bobmarks.caching;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * Class which declares an EHCache instance.
 * 
 * @author b.marks
 */
public class EHCacheFacade {

    private static CacheManager cacheManager = null;
    private static Ehcache cache = null;

    static {
        // Create a CacheManager using a specific config file
        cacheManager = CacheManager.create(EHCacheFacade.class
                .getResource("/ehcache.xml"));
        cache = cacheManager.getCache("firstcache");
    }

    /**
     * Constructor.
     */
    public EHCacheFacade() {
    }

    /**
     * Delete put method.
     * 
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        // get an element from cache by key
        cache.put(new Element(key, value));
    }

    /**
     * Delegate get method.
     * 
     * @param key
     * @return
     */
    public Object get(String key) {
        Element e = cache.get(key);
        return e.getObjectValue();
    }
}
