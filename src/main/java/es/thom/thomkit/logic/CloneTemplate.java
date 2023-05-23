package es.thom.thomkit.logic;

import es.thom.thomkit.enums.LogType;
import es.thom.thomkit.objects.Template;
import es.thom.thomkit.tools.ConsoleLogger;
import es.thom.thomkit.tools.InputCheck;
import es.thom.thomkit.tools.InputConvert;
import es.thom.thomkit.tools.TemplateExecutor;
import es.thom.thomkit.tools.file_system.FileReader;
import es.thom.thomkit.tools.file_system.FileWriter;

public class CloneTemplate {

    public static void cloneToDirectory(Template template, String toDirectory, String extension) {

        ConsoleLogger.log("CloneTemplate: Cloning Process Starting...", LogType.LogicInfo);
        String[] classes = TemplateExecutor.execute(template);
        int arrayLength = InputCheck.checkArrayLength(classes);
        ConsoleLogger.log("CloneTemplate: Cloning Template Into " + arrayLength + " New Classes To " + toDirectory, LogType.LogicInfo);

        for (int i = 0; i < arrayLength; i++) {
            String className = InputConvert.getNameFromClass(classes[i]);
            FileWriter.write(className + extension, toDirectory, classes[i]);
        }
        ConsoleLogger.log("CloneTemplate: Cloned " + arrayLength + " Classes Into " + toDirectory, LogType.LogicInfo);
    }

    public static void cloneToPackages(Template template) {

        ConsoleLogger.log("CloneTemplate: Cloning Process Starting...", LogType.LogicInfo);
        String[] classes = TemplateExecutor.execute(template);
        int arrayLength = InputCheck.checkArrayLength(classes);
        ConsoleLogger.log("Cloning Template Into " + arrayLength + " New Classes To Their Packages", LogType.LogicInfo);
        for (int i = 0; i < arrayLength; i++) {
            String className = InputConvert.getNameFromClass(classes[i]);
            String packageName = FileReader.readCodeLine(template.getCode(), 1);
            String packageDir = InputConvert.packageToDirectory(packageName, template.isTest());
            FileWriter.write(className + ".java", packageDir, classes[i]);
        }
        ConsoleLogger.log("CloneTemplate: Cloned " + arrayLength + " Classes Into Their Packages", LogType.LogicInfo);
    }
}