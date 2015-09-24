package testing;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.*;

public class GemfireTestingUtil {
    public static <K, V> Region getRegionConnection(String regionName) {
        // Honestly, we could probably just use this to get a connection in our main app (after adding pdx serialization
        // to the cache)
        ClientCacheFactory ccf = new ClientCacheFactory();
        ccf.addPoolLocator("localhost", 10334);
        ClientCache cc = ccf.create();
        ClientRegionFactory<K, V> rf = cc.createClientRegionFactory(ClientRegionShortcut.PROXY);

        return rf.create(regionName);
    }

    public static void emptyRegion(Region region) {
        region.keySetOnServer().stream()
            .forEach(region::remove);
    }
}
