import java.net.*;
import java.io.*;

public class LoadClasses {
    public void load() {
        File file  = new File("./jar/my.jar");
        String className = "Test";

        while (true) {
            try {
                // Create a new instance of ClassLoader
                URLClassLoader cl = new URLClassLoader(new URL[]{file.toURL()});
                // Load class with this new instance
                Class c = cl.loadClass(className);
                cl.close();
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // When PermGen/Metaspace threshold is reached, Full GC would get invoked
            // With the above logic, with a new instance of classloader, we are loading
            // a new copy of the class and that slowly fills up the PermGen/Metaspace.
            // And the Full GCs required to collect those classes introduce latency in
            // the process
            // If the application logic does not require duplicate copies of the classes
            // they should be loaded by the same instance of the classloader.
        }
    }

    public static void main(String args[]) {
        LoadClasses lc = new LoadClasses();
        lc.load();
    }
}