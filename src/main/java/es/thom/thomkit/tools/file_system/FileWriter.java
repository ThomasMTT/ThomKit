package es.thom.thomkit.tools.file_system;

import es.thom.thomkit.enums.LogType;
import es.thom.thomkit.tools.ConsoleLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWriter {

    public static void write(String fileName, String writeTo, String code) {

        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(writeTo + "\\" + fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        File file = new File(writeTo);
        if (!file.exists()) {
            new File(writeTo).mkdirs();
            ConsoleLogger.log("FileWriter: Created Directory: " + writeTo, LogType.Info);
        }

        ConsoleLogger.log("FileWriter: Wrote " + writeTo + "\\" + fileName, LogType.Info);
        printWriter.println(code);
        printWriter.close();
    }

}