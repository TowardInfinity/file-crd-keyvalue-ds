package in.toin;

import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.concurrent.ConcurrentMap;

public class DB {
    
    private final String fileLocation;
    private final String fileName;
    private ConcurrentMap<String, String> map;
    private org.mapdb.DB db;
    
    /**
     * Custom location for DB.
     * @param fileLocation file directory/location(path)
     * @param fileName file name of the db
     */
    public DB(String fileLocation, String fileName) {
        this.fileLocation = fileLocation;
        this.fileName = fileName;
        init();
    }
    
    /**
     * Project directory location of DB.
     * @param fileName file name of the db
     */
    public DB(String fileName) {
        this.fileName = fileName;
        fileLocation = "~";
        init();
    }
    
    /**
     * Initializing the values
     */
    private void init() {
        createDB();
        createMap();
    }
    
    /**
     * Create DB with the location
     */
    private void createDB() {
        db = DBMaker
                .fileDB(
                        ((fileLocation.charAt(0) == '~') ? "" : fileLocation)
                                + fileName + ".db")
                .fileMmapEnable()
                .make();
    }
    
    /**
     * Create Map for the Key-Value Pair
     */
    private void createMap() {
        map = db
                .hashMap("map", Serializer.STRING, Serializer.STRING)
                .createOrOpen();
    }
    
    /**
     * Get the map Created
     * @return ConcurrentMap<String, String> of the map DS
     */
    public ConcurrentMap<String, String> getMap() {
        return map;
    }
    
    /**
     * Close the DB once, used.
     */
    public void close() {
        db.close();
    }
}
