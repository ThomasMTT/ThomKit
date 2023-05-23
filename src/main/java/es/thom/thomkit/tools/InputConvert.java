package es.thom.thomkit.tools;

import es.thom.thomkit.objects.Properties;
import es.thom.thomkit.tools.file_system.FileReader;
import org.apache.commons.lang3.SystemUtils;

import java.util.Arrays;

public class InputConvert {

    public static String packageToDirectory(String packageRoot) {

        return packageToDirectory(packageRoot, false);
    }

    public static String packageToDirectory(String packageRoot, boolean isTest) {

        Properties properties = new Properties();

        packageRoot = InputCheck.checkPackage(packageRoot);

        if (SystemUtils.IS_OS_WINDOWS) {
            packageRoot = packageRoot.replace(".", "\\");
            if (isTest) {
                packageRoot = properties.get("SourceDirectory") + "test\\java\\" + packageRoot + "\\";
            } else {
                packageRoot = properties.get("SourceDirectory") + "main\\java\\" + packageRoot + "\\";
            }

        } else if (SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_UNIX) {
            packageRoot = packageRoot.replace(".", "/");
            if (isTest) {
                packageRoot = properties.get("SourceDirectory") + "test/java/" + packageRoot + "/";
            } else {
                packageRoot = properties.get("SourceDirectory") + "main/java/" + packageRoot + "/";
            }
        }

        return packageRoot;
    }

    public static String[] packagesMatching(String[] packageList, String filter) {

        String[] packagesMatching = new String[packageList.length];
        int j = 0;
        for (int i = 0; packageList[i] != null; i++) {
            if (packageList[i].endsWith(filter)) {
                packagesMatching[j] = packageList[i];
                j++;
            }
        }
        return packagesMatching;
    }

    public static String[] directoryToPackage(String[] directories) {

        String[] packagesList = new String[directories.length];

        for (int i = 0; i < directories.length; i++) {
            packagesList[i] = FileReader.readFileLine(directories[i], 1);
            packagesList[i] = packagesList[i].substring(8, packagesList[i].length() - 1);
        }
        return packagesList;
    }

    public static String variablesToCsvFormat(String[][] variablesInMultiDimensionalArray) {

        StringBuilder csvFormatedVariables = new StringBuilder();

        int i = 0, j = 0;
        while (variablesInMultiDimensionalArray[0][i] != null) {
            i++;
        }

        String[] codeOutput = new String[i - 1];
        for (i = 0; variablesInMultiDimensionalArray[i][j] != null; i++) {

            for (j = 0; variablesInMultiDimensionalArray[i][j] != null; j++) {
                csvFormatedVariables.append(variablesInMultiDimensionalArray[i][j]).append(" , ");
            }
            csvFormatedVariables = new StringBuilder(csvFormatedVariables.substring(0, csvFormatedVariables.length() - 3));
            csvFormatedVariables.append("\n");
            j = 0;
        }
        return csvFormatedVariables.toString();
    }

    public static String[][] variablesToMultiDimensionalArray(String variablesInCsvFormat) {

        String[] lines = variablesInCsvFormat.split("\n");
        int linesCount = InputCheck.checkArrayLength(lines);
        int variablesInLineCount = 0;
        for (int i = 0; i < linesCount; i++) {
            String[] variablesinLine = lines[i].split(",");
            if (InputCheck.checkArrayLength(variablesinLine) > variablesInLineCount) {
                variablesInLineCount = InputCheck.checkArrayLength(variablesinLine);
            }
        }
        String[][] variables = new String[linesCount + 1][variablesInLineCount];

        lines = Arrays.copyOf(lines, lines.length + 1);
        for (int j = 0; lines[j] != null; j++) {
            String[] variablesInLine = lines[j].split(" , ");
            variablesInLine = Arrays.copyOf(variablesInLine, variablesInLine.length + 1);
            for (int i = 0; variablesInLine[i] != null; i++) {
                variables[j][i] = variablesInLine[i];
            }
        }

        return variables;
    }

    public static String getNameFromClass(String code) {

        String className;
        int classNameIndexStart = code.indexOf("public class ") + 13;
        className = code.substring(classNameIndexStart);
        int classNameIndexEnd = className.indexOf(" ");
        className = className.substring(0, classNameIndexEnd);
        return className;
    }

    public static String directoryToPackage(String directory) {

        return FileReader.readFileLine(directory, 1);
    }
}