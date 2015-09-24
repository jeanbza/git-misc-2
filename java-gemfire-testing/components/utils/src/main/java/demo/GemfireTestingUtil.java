package demo;

import com.gemstone.gemfire.cache.Region;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class GemfireTestingUtil {
    private static Map<String, String> map = getEnv();

    private static Map<String, String> getEnv() {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get("gemfire-test.yml"));
            String manifest = new String(encoded, "UTF-8");

            System.out.println("Manifest file read:");
            System.out.println(manifest);

            Yaml yaml = new Yaml();
            Map map = (Map) yaml.load(manifest);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);

            // no-op
            return null;
        }
    }

    public static String gemfireLocatorHost() {
        return map.get("GEMFIRE_LOCATOR_HOST");
    }

    public static Integer gemfireLocatorPort() {
        return Integer.valueOf(map.get("GEMFIRE_LOCATOR_PORT"));
    }

    public static void emptyRegion(Region region) {
        region.keySetOnServer().stream().forEach(region::remove);
    }
}
