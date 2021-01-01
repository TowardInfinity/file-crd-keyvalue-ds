# File-CRD-KeyValue-DS

### Create Database

```java
import in.toin.DB;
```
DB with standard project directory
```java
DB customDb = new DB("example");
```
DB with custom location
```java
DB customDb1 = new in.toin.DB("E:\\", "example");
```

### Example

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import in.toin.DB;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class Example {
    public static void main(String[] args) {
        
        // in.toin.DB with standard project directory
        DB customDb = new DB("random");
        
        // DB with custom location
        // DB customDb1 = new in.toin.DB("E:\\", "hello");
        
        ObjectMapper mapper = new ObjectMapper();
        ConcurrentMap<String, String> map = null;
        
        try {
            // Here Value is sample object
            Value value = new Value("Apple", "Fruit as well as trillion dollar company.");
            
            // Java objects to JSON string - compact-print
            String jsonString = mapper.writeValueAsString(value);
            
            map  = customDb.getMap();
    
            // Insert
            map.put("brand", jsonString);
            map.put("location", jsonString);
            map.put("animals", jsonString);
            // Remove
            map.remove("location");
            
            for (Map.Entry<String, String> entry: map.entrySet())
                System.out.println(entry.getKey() + " " + entry.getValue());
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        customDb.close();
    }
}
```

https://github.com/jankotek/mapdb/