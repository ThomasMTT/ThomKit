package es.thom.thomkit.tools.file_system;

import es.thom.thomkit.tools.InputCheck;
import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DirectoryScanner {

    public static String[] scanForFileDirs(String directory) {

        List<Path> pathList;
        try {
            pathList = Files.walk(Paths.get(directory))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] paths = new String[pathList.size()];
        for (int i = 0; i < pathList.size(); i++) {
            paths[i] = pathList.get(i).toString();
        }
        return paths;
    }

    public static String[] scanForFileDirs(String directory, String endingWithExtension) {

        List<Path> pathList;
        try {
            pathList = Files.walk(Paths.get(directory))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] paths = new String[pathList.size()];
        int cleanArray = 0;
        for (int i = 0; i < pathList.size(); i++) {
            paths[i] = pathList.get(i).toString();
            if (!pathList.get(i).toString().endsWith(endingWithExtension)) {
                pathList.remove(i);
                i--;
            } else {
                paths[i] = pathList.get(i).toString();
            }
            cleanArray = i + 1;
        }
        String[] cleanPaths = new String[cleanArray];
        System.arraycopy(paths, 0, cleanPaths, 0, cleanArray);
        cleanPaths = InputCheck.cleanArrayNulls(cleanPaths);
        return cleanPaths;
    }

    public static String[] scanForFileNames(String directory) {

        List<Path> pathList;
        try {
            pathList = Files.walk(Paths.get(directory))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] fileNames = new String[pathList.size()];
        for (int i = 0; i < pathList.size(); i++) {
            fileNames[i] = pathList.get(i).getFileName().toString();
        }
        return fileNames;
    }

    public static String[] scanForFileNames(String directory, String endingWithExtension) {

        List<Path> pathList;
        try {
            pathList = Files.walk(Paths.get(directory))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] fileNames = new String[pathList.size()];
        for (int i = 0; i < pathList.size(); i++) {

            if (!pathList.get(i).toString().endsWith(endingWithExtension)) {
                pathList.remove(i);
                i--;
            } else {
                fileNames[i] = pathList.get(i).getFileName().toString();
            }

        }
        fileNames = InputCheck.cleanArrayNulls(fileNames);
        return fileNames;
    }

    public static int scanForFileCount(String directory) {

        List<Path> pathList;
        try {
            pathList = Files.walk(Paths.get(directory))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pathList.size();
    }
}
