package es.thom.thomkit.objects;

import es.thom.thomkit.tools.InputConvert;
import es.thom.thomkit.tools.file_system.FileReader;
import lombok.Data;

@Data

public class Template {

    boolean isTest;
    private String code;
    private String[][] variables;

    public Template(String code, String[][] variables, boolean isTest) {

        this.code = code;
        this.variables = variables;
        this.isTest = isTest;
    }

    public Template(String codeFromFilePath, String VariablesFromFilePath, boolean isTest) {

        this.code = FileReader.readFile(codeFromFilePath);
        this.variables = InputConvert.variablesToMultiDimensionalArray(FileReader.readFile(VariablesFromFilePath));
        this.isTest = isTest;
    }

    public Template(String code, String[][] variables) {

        this.code = code;
        this.variables = variables;
        this.isTest = false;
    }

    public Template(String codeFromFilePath, String VariablesFromFilePath) {

        this.code = FileReader.readFile(codeFromFilePath);
        this.variables = InputConvert.variablesToMultiDimensionalArray(FileReader.readFile(VariablesFromFilePath));
        this.isTest = false;
    }

}
