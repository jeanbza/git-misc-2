package demo;

import com.gemstone.gemfire.cache.Region;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class GemfireTestingUtil {
    public static void main(String args[]) {
        System.out.println("test");
        getEnv();
        System.out.println(map);
    }

    private static Map<String, Object> map = getEnv();

    private static Map<String, Object> getEnv() {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get("../../gemfire-test.yml"));
            String manifest = new String(encoded, "UTF-8");
            Yaml yaml = new Yaml();
            Map map = (Map) yaml.load(manifest);
            return map;
        } catch (IOException e) {
            e.printStackTrace();

            // no-op
            return null;
        }
    }

    public static String gemfireLocatorHost() {
        return (String) map.get("GEMFIRE_LOCATOR_HOST");
    }

    public static Integer gemfireLocatorPort() {
        return (Integer) map.get("GEMFIRE_LOCATOR_PORT");
    }

    public static void emptyRegion(Region region) {
        region.keySetOnServer().stream().forEach(region::remove);
    }
}
