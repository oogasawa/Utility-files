package com.github.oogasawa.utility.files;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileUtil {


    public static boolean exists(String pathStr) {
        Path path = Paths.get(pathStr);
        return Files.exists(path);
    }


    public static Path getHomeDir() {
        Path path = Paths.get(System.getenv("HOME"));
        return path;
    }

    public static Path getCurrentDir() {
        Path path = Paths.get(System.getProperty("user.dir"));
        return path;
    }





    public static void mkdir(String dir) {
        File fObj = new File(dir);
        if (!fObj.exists()) {
            fObj.mkdir();
        }
    }

    public static void mkdirs(String dir) {
        File fObj = new File(dir);
        if (!fObj.exists()) {
            fObj.mkdirs();
        }
    }


    public static ArrayList<String> parsePathString(String path) {
        File f = new File(path);
        String name = f.getName();
        String dir = f.getParent();

        ArrayList<String> result = new ArrayList<String>();
        result.add(name);
        result.add(dir);

        return result;
    }


    public static void rmDirs(String dir) {
        File fObj = new File(dir);
        DeleteDir.deleteDirectory(fObj);
    }


}
