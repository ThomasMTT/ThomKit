package es.thom.thomkit.objects;


import es.thom.thomkit.enums.LogType;
import es.thom.thomkit.tools.ConsoleLogger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Properties {

    java.util.Properties properties = new java.util.Properties();

    public Properties() {

        loadFromXml();
    }

    public void put(String key, String value) {

        properties.put(key, value);
    }

    public void replace(String key, String value) {

        properties.replace(key, value);
    }

    public void remove(String key) {

        properties.remove(key);
    }

    public void saveToXml() {

        saveToXml(".\\properties.xml");
    }

    public void loadFromXml() {

        loadFromXml(".\\properties.xml");
    }

    public String get(String key) {

        String property = properties.getProperty(key);
        if (property == null) {
            ConsoleLogger.log("Properties: Property '" + key + "' Not Found!", LogType.Error);
        }
        return property;
    }

    public void saveToXml(String xmlFile) {

        try {
            properties.storeToXML(new FileOutputStream(xmlFile), "");
            ConsoleLogger.log("Properties: Saving properties to " + xmlFile, LogType.Info);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadFromXml(String xmlFile) {

        try {
            properties.loadFromXML(new FileInputStream(xmlFile));
            ConsoleLogger.log("Properties: Loading properties from " + xmlFile, LogType.Info);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
