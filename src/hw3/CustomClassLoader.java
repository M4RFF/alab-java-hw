package hw3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {

    private final String classPath; // stores the path to the directory

    public CustomClassLoader(String classPath) {
        this.classPath = classPath;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);

        if(classData == null) {
            throw new ClassNotFoundException("Class " + name + " not found: ");
        }
        return defineClass(name, classData, 0, classData.length); // defining the class in memory
    }


    private byte[] loadClassData(String className) {
        String path = classPath + File.separator + className.replace('.', File.separatorChar) + ".class";

        try (InputStream inputStream = new FileInputStream(path)) {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            return buffer;
        } catch (IOException e) {
            System.out.println("Failed to load class data for: " + className);
            return null;
        }
    }
}
