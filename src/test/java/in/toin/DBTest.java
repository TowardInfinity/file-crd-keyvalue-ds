package in.toin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentMap;

import static org.junit.jupiter.api.Assertions.*;

class DBTest {

    @Test
    void testGetMapAndCRUD(@TempDir Path tempDir) {
        String dbLocation = tempDir.toString() + "/";
        DB db = new DB(dbLocation, "testdb");
        try {
            ConcurrentMap<String, String> map = db.getMap();
            assertNotNull(map, "Map should not be null");
            map.put("key1", "value1");
            assertEquals("value1", map.get("key1"));
            assertTrue(map.containsKey("key1"));

            map.put("key1", "updatedValue");
            assertEquals("updatedValue", map.get("key1"));

            String removed = map.remove("key1");
            assertEquals("updatedValue", removed);
            assertFalse(map.containsKey("key1"));
        } finally {
            db.close();
        }
    }

    @Test
    void testClose(@TempDir Path tempDir) {
        String dbLocation = tempDir.toString() + "/";
        DB db = new DB(dbLocation, "closedb");
        ConcurrentMap<String, String> map = db.getMap();
        map.put("k", "v");
        assertDoesNotThrow(db::close);
    }
}
