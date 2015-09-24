package demo;

import com.gemstone.gemfire.cache.Region;

public class GemfireTestingUtil {
    public static void emptyRegion(Region region) {
        region.keySetOnServer().stream()
            .forEach(region::remove);
    }
}
