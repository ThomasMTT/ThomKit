package es.thom.thomkit.tools;

import es.thom.thomkit.enums.LogType;

public class ConsoleLogger {

    public static void log(String log, LogType logType) {

        if (LogType.Info.equals(logType)) {
            System.out.println("ThomKit [" + "\u001B[34m" + "INFO" + "\u001B[0m" + "] " + log);
        } else if (LogType.LogicInfo.equals(logType)) {
            System.out.println("ThomKit [" + "\u001B[34m" + "INFO" + "\u001B[0m" + "] " + "\u001B[33m" + log + "\u001B[0m");
        } else if (LogType.Error.equals(logType)) {
            System.out.println("ThomKit [" + "\u001B[31m" + "ERROR" + "\u001B[0m" + "] " + "\u001B[31m" + log + "\u001B[0m");
        }

    }
}