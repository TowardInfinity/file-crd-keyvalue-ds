import com.fasterxml.jackson.databind.ObjectMapper;
import in.toin.DB;
import org.mapdb.HTreeMap;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class Example {
    public static void main(String[] args) {
        
        // DB with standard project directory
        DB customDb = new DB("persons");
        
        // DB with custom location
        // DB customDb1 = new DB("E:\\", "hello");
        
        /**
         * ObjectMapper provides functionality for reading and writing JSON,
         * either to and from basic POJOs (Plain Old Java Objects)
         */
        ObjectMapper mapper = new ObjectMapper();
    
        // Map of our DS
        ConcurrentMap<String, String> map = null;
        
        try {
            // Here Value is sample object
            Value value = new Value("Apple", "Fruit as well as trillion dollar company.");
            
            // Java objects to JSON string - compact-print
            String jsonString = mapper.writeValueAsString(value);
            
            // Get Map
            map  = customDb.getMap();
            
            map.put("brand", jsonString);
            map.remove("start1");
            
            for (Map.Entry<String, String> entry: map.entrySet())
                System.out.println(entry.getKey() + " " + entry.getValue());
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        customDb.close();
    }
}
