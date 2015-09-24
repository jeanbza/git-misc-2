package testing;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.*;

public class GemfireTestingUtil {
    public static Region getRegionConnection(String regionName, Class keyClass, Class valueClass) {
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
