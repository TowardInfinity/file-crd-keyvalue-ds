package in.toin;

import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.concurrent.ConcurrentMap;

public class DB {
    
    private final String fileLocation;
    private final String fileName;
    private ConcurrentMap<String, String> map;
    private org.mapdb.DB db;
    
    public DB(String fileLocation, String fileName) {
        this.fileLocation = fileLocation;
        this.fileName = fileName;
        createDB();
        createMap();
    }
    
    public DB(String fileName) {
        this.fileName = fileName;
        fileLocation = "~";
        createDB();
        createMap();
    }
    
    private void createDB() {
        db = DBMaker
                .fileDB(
                        ((fileLocation.charAt(0) == '~') ? "" : fileLocation)
                                + fileName + ".db")
                .fileMmapEnable()
                .make();
    }
    
    private ConcurrentMap<String, String> createMap() {
        map = db
                .hashMap("map", Serializer.STRING, Serializer.STRING)
                .createOrOpen();
        return map;
    }
    
    public ConcurrentMap<String, String> getMap() {
        return map;
    }
    
    public void close() {
        db.close();
    }
}
