# File-CRD-KeyValue-DS

## Background
This project is build on top of [mapdb](https://github.com/jankotek/mapdb/). MapDB combines embedded database engine and Java collections. MapDB is extensively unit-tested By default.

We can directly pass **JSON String** to it. We get JSON String which string, with the help of [Jackson](https://github.com/FasterXML/jackson-core) one can make use of it.

File extension in this project is **db**.

## Features
* Create, can be easily done by put(key, value).
* Read, by get(key)
* Delete, by remove(key)
* As it is based on java collection, many more features are copied with the same.
* for more features refer [mapdb-docs](https://jankotek.gitbooks.io/mapdb/content/).

## How to use

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
DB customDb1 = new DB("E:\\", "example");
```

### Code Example

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import in.toin.DB;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class Example {
    public static void main(String[] args) {
        
        // DB with standard project directory
        DB customDb = new DB("random");
        
        // DB with custom location
        // DB customDb1 = new DB("E:\", "hello");
        
        // ObjectMapper provides functionality for reading and writing JSON,
        ObjectMapper mapper = new ObjectMapper();
        
        // Map of DS
        ConcurrentMap<String, String> map = null;
        
        try {
            // Here Value is sample object
            Value value = new Value("Apple", "Fruit as well as trillion dollar company.");
            
            // Java objects to JSON string - compact-print
            String jsonString = mapper.writeValueAsString(value);
            
            // Get the from the data base.
            map  = customDb.getMap();
    
            // Insert
            map.put("brand", jsonString);
            map.put("location", jsonString);
            map.put("animals", jsonString);
            // Remove
            map.remove("location");
            
            // Iteration through the map
            for (Map.Entry<String, String> entry: map.entrySet())
                System.out.println(entry.getKey() + " " + entry.getValue());
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        customDb.close();
    }
}
```

## Note
Always close the DB once operations have been performed, or else the DB will get corrupted.
As it is meant for single user at a time.

```java
db.close();
```

## Sources
Build on top of [mapdb](https://github.com/jankotek/mapdb/).