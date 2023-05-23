package es.thom.thomkit.tools.file_system;

import es.thom.thomkit.enums.LogType;
import es.thom.thomkit.tools.ConsoleLogger;
import lombok.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

@Data

public class FileReader {

    private String fileContent = "";

    public static String readFile(String file) {

        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String newLine;
        StringBuilder fileContent = new StringBuilder();
        while (true) {

            try {
                if ((newLine = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fileContent.append("\n").append(newLine);
        }
        fileContent = new StringBuilder(fileContent.toString().replaceFirst("^\\s*", ""));
        ConsoleLogger.log("FileReader: Read " + file.substring(0, file.lastIndexOf("\\")) + file.substring(file.lastIndexOf("\\") + 1), LogType.Info);
        return fileContent.toString();
    }

    public static String readFileLine(String file, int lineIndex) {

        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line = "";

        for (int i = 1; i <= lineIndex; i++) {
            try {
                if ((line = bufferedReader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ConsoleLogger.log("FileReader: Read " + file.substring(file.lastIndexOf("\\")) + "at line" + lineIndex + " from " + file, LogType.Info);
        assert line != null;
        return line.replaceFirst("^\\s*", "");
    }

    public static String readCodeLine(String code, int lineIndex) {

        String line = "";
        for (int i = 1; i <= lineIndex; i++) {
            line = code.substring(0, code.indexOf("\n"));
            code = code.substring(code.indexOf("\n"));
        }
        ConsoleLogger.log("FileReader:\u001B[0m Read code line: " + line, LogType.Info);
        return line.replaceFirst("^\\s*", "");
    }
}
