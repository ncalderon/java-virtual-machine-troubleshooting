import java.util.*;

public class MemoryUsage {
    Vector longLivedObjects = new Vector();    
    
    void createShortLivedObjects() {        
        HeapObject ho = new HeapObject();        
    }
    
    void createLongLivedObjects() {
        HeapObject ho = new HeapObject();
        longLivedObjects.add(ho);        
    }
    
    class HeapObject {
        byte[] b;
        public HeapObject() {
            b = new byte[1000];
        }
    }
    
    public static void main(String args[]) {
        MemoryUsage mem = new MemoryUsage();
        
       
        while(true) {
            try {
                mem.createShortLivedObjects();
                mem.createLongLivedObjects();
                Thread.sleep(1000);
            } catch (Exception e) {}
        }
        
    }
}