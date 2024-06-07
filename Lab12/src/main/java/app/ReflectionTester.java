package app;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ReflectionTester {

    private static int testCount = 0;
    private static int successCount = 0;
    private static int failureCount = 0;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -cp <classpath> app.ReflectionTester <path/to/class_or_directory_or_jar>");
            return;
        }

        String inputPath = args[0];
        File inputFile = new File(inputPath);

        try {
            if (inputFile.isFile()) {
                if (inputFile.getName().endsWith(".class")) {
                    analyzeClassFile(inputFile);
                }
                else if (inputFile.getName().endsWith(".jar")) {
                    analyzeJarFile(inputFile);
                }
                else {
                    System.err.println("Unsupported file type: " + inputFile.getName());
                }
            }
            else if (inputFile.isDirectory()) {
                analyzeDirectory(inputFile);
            }
            else {
                System.err.println("Invalid input path: " + inputPath);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Test statistics:");
        System.out.println("Total tests: " + testCount);
        System.out.println("Successful tests: " + successCount);
        System.out.println("Failed tests: " + failureCount);
    }

    private static void analyzeClassFile(File classFile) throws Exception {
        URL classFileUrl = classFile.getParentFile().toURI().toURL();
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{classFileUrl}, ReflectionTester.class.getClassLoader());
        String className = getClassName(classFile);
        Class<?> clazz = Class.forName(className, true, urlClassLoader);
        processClass(clazz);
    }

    private static void analyzeJarFile(File jarFile) throws IOException, ClassNotFoundException {
        try (JarFile jar = new JarFile(jarFile)) {
            Enumeration<JarEntry> entries = jar.entries();
            URL[] urls = {new URI("jar:file:" + jarFile.getAbsolutePath() + "!/").toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, ReflectionTester.class.getClassLoader());
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".class")) {
                    String className = entry.getName().replace("/", ".").replace(".class", "");
                    Class<?> clazz = Class.forName(className, true, urlClassLoader);
                    processClass(clazz);
                }
            }
        } catch (URISyntaxException e) {
            System.err.println("Invalid URI syntax: " + e.getMessage());
        }
    }

    private static void analyzeDirectory(File directory) throws Exception {
        Set<URI> urls = new HashSet<>();
        addDirectoryToClasspath(directory, urls);
        URL[] uriArray = urls.stream().map(uri -> {
            try {
                return uri.toURL();
            } catch (Exception e) {
                return null;
            }
        }).filter(Objects::nonNull).toArray(URL[]::new);
        URLClassLoader urlClassLoader = new URLClassLoader(uriArray, ReflectionTester.class.getClassLoader());
        for (File file : getAllClassFiles(directory)) {
            String className = getClassName(file);
            Class<?> clazz = Class.forName(className, true, urlClassLoader);
            processClass(clazz);
        }
    }

    private static void addDirectoryToClasspath(File directory, Set<URI> urls) {
        urls.add(directory.toURI());
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                addDirectoryToClasspath(file, urls);
            }
        }
    }

    private static Set<File> getAllClassFiles(File directory) {
        Set<File> classFiles = new HashSet<>();
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                classFiles.addAll(getAllClassFiles(file));
            }
            else if (file.getName().endsWith(".class")) {
                classFiles.add(file);
            }
        }
        return classFiles;
    }

    private static String getClassName(File classFile) {
        String absolutePath = classFile.getAbsolutePath();
        String classPath = absolutePath.substring(absolutePath.indexOf("classes") + 8).replace(File.separator, ".");
        return classPath.replace(".class", "");
    }

    private static void processClass(Class<?> clazz) {
        System.out.println("Class: " + clazz.getName());
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("  " + method);
            if (method.isAnnotationPresent(Test.class)) {
                invokeTestMethod(clazz, method);
            }
        }
    }

    private static void invokeTestMethod(Class<?> clazz, Method method) {
        try {
            Object instance = null;
            if (!java.lang.reflect.Modifier.isStatic(method.getModifiers())) {
                instance = clazz.getDeclaredConstructor().newInstance();
            }
            method.setAccessible(true);
            method.invoke(instance, generateMockArguments(method));
            successCount++;
            System.out.println("Test passed: " + method.getName());
        } catch (Exception e) {
            failureCount++;
            System.err.println("Test failed: " + method.getName());
            System.out.println(e.getMessage());
        } finally {
            testCount++;
        }
    }

    private static Object[] generateMockArguments(Method method) {
        Parameter[] parameters = method.getParameters();
        Object[] mockArgs = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Class<?> type = parameters[i].getType();
            if (type == int.class) {
                mockArgs[i] = 0;
            }
            else if (type == String.class) {
                mockArgs[i] = "mock";
            }
            else if (type == boolean.class) {
                mockArgs[i] = false;
            }
            else if (type == double.class) {
                mockArgs[i] = 0.0;
            }
            else {
                mockArgs[i] = null;
            }
        }
        return mockArgs;
    }
}
