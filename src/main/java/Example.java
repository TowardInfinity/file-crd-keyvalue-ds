import com.fasterxml.jackson.databind.ObjectMapper;
import in.toin.DB;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class Example {
    public static void main(String[] args) {
        
        // in.toin.DB with standard project directory
        DB customDb = new DB("persons");
        
        // in.toin.DB with custom location
//        in.toin.DB customDb1 = new in.toin.DB("E:\\", "hello");
        
        ObjectMapper mapper = new ObjectMapper();
        ConcurrentMap<String, String> map = null;
        
        try {
            // Here Value is sample object
            Value value = new Value("Apple", "Fruit as well as trillion dollar company.");
            
            // Java objects to JSON string - compact-print
            String jsonString = mapper.writeValueAsString(value);
            
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
