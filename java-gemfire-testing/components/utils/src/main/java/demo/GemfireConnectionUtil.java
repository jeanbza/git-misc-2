package demo;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GemfireConnectionUtil {
    // Because when we run acceptance tests, getRegionConnection gets called twice in the same context (once by
    // app booting, and once by the integration test) - and there can only be one proxy per context
    public static Map<String, Region> regionCache = new ConcurrentHashMap<>();

    public static <K, V> Region getRegionConnection(String regionName, String host, int port) {
        if (regionCache.containsKey(regionName)) {
            return regionCache.get(regionName);
        } else {
            ClientCacheFactory clientCacheFactory = new ClientCacheFactory();
            clientCacheFactory.addPoolLocator(host, port);
            ClientCache clientCache = clientCacheFactory.create();
            ClientRegionFactory<K, V> regionFactory = clientCache.createClientRegionFactory(ClientRegionShortcut.PROXY);

            Region<K, V> newRegion = regionFactory.create(regionName);
            regionCache.put(regionName, newRegion);

            return newRegion;
        }
    }
}
