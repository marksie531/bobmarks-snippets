package org.bobmarks.caching;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 * Cache Test unit test class.
 * 
 * @author Bob Marks
 */
public class CacheTest {

    private final JCSCacheFacade jcsCache = new JCSCacheFacade();
    private final EHCacheFacade ehCache = new EHCacheFacade();

    // Performance methods
    private long time;

    @Rule
    public TestName name = new TestName();

    @Before
    public void before() {
        time = System.currentTimeMillis();
    }

    @After
    public void after() {
        System.out.println(name.getMethodName() + "\t"
                + (System.currentTimeMillis() - time));
    }

    @Test
    public void testJCSCacheSimple() throws Exception {

        String key = "test_key";
        String value = "test_value";
        jcsCache.put(key, value);

        String cacheValue = (String) jcsCache.get(key);

        Assert.assertEquals(value, cacheValue);
    }

    @Test
    public void testJCSCacheInserts100k() throws Exception {

        String key = "test_key";
        String value = "test_value";
        for (long l = 0; l < 100000; l++) {
            jcsCache.put(key + l, value);
        }
    }

    @Test
    @SuppressWarnings("unused")
    public void testJCSCacheRetrieves10k() throws Exception {
        String key = "test_key";
        Object value = null;
        for (long l = 0; l < 10000; l++) {
            value = jcsCache.get(key + l);
        }
    }

    @Test
    public void testEHCacheSimple() throws Exception {

        String key = "test_key";
        String value = "test_value";
        ehCache.put(key, value);

        String cacheValue = (String) ehCache.get(key);

        Assert.assertEquals(value, cacheValue);
    }

    @Test
    public void testEHCacheInserts100k() throws Exception {

        String key = "test_key";
        String value = "test_value";
        for (long l = 0; l < 100000; l++) {
            ehCache.put(key + l, value);
        }
    }

    @Test
    @SuppressWarnings("unused")
    public void testEHCacheRetrieves10k() throws Exception {
        String key = "test_key";
        Object value = null;
        for (long l = 0; l < 100; l++) {
            value = ehCache.get(key + l);
        }
    }
}