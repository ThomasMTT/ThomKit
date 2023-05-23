package es.thom.thomkit.tools;

import es.thom.thomkit.objects.Template;
import es.thom.thomkit.tools.file_system.FileReader;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

public class TemplateExecutor {

    public static String[] execute(Template template) {

        Map<String, String> values = new HashMap<>();
        String[][] variables = template.getVariables();
        String code = template.getCode();

        int arrayLength = InputCheck.checkArrayLength(variables[0]);

        int k = 0;
        String[] codeOutput = new String[arrayLength];
        for (int i = 1; i < arrayLength; i++) {

            for (int j = 0; variables[j][i] != null; j++) {
                values.put(variables[j][0], variables[j][i]);
            }
            StringSubstitutor sub = new StringSubstitutor(values);
            codeOutput[k] = sub.replace(code).replaceFirst("^\\s*", "");
            k++;

        }
        return codeOutput;
    }

    public static String[] templateToClassFromFiles(String codeFile, String variablesFile) {

        String code = FileReader.readFile(codeFile);
        String variables = FileReader.readFile(variablesFile);
        String[][] variablesList = InputConvert.variablesToMultiDimensionalArray(variables);
        Template template = new Template(code, variablesList);
        return execute(template);
    }

}

