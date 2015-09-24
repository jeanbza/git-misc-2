package testing;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.*;

public class GemfireTestingUtil {
    public static Region getRegionConnection(String regionName, Class keyClass, Class valueClass) {
        // Honestly, we could probably just use this to get a connection in our main app (after adding pdx serialization
        // to the cache)
        ClientCacheFactory ccf = new ClientCacheFactory();
        ccf.addPoolLocator("localhost", 10334);
        ClientCache cc = ccf.create();
        ClientRegionFactory<String, Long> rf = cc.createClientRegionFactory(ClientRegionShortcut.PROXY);

        return rf.create(regionName);
    }

    public static void emptyRegion(Region region) {
        region.keySetOnServer().stream()
            .forEach(region::remove);
    }
}
