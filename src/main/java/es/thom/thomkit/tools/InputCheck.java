package es.thom.thomkit.tools;

public class InputCheck {

    public static String checkPackage(String packageRoot) {

        packageRoot = packageRoot.replaceFirst("^\\s*", "");
        if (packageRoot.endsWith(";")) {
            packageRoot = packageRoot.substring(0, packageRoot.length() - 1);
        }

        if (packageRoot.startsWith("package")) {
            packageRoot = packageRoot.substring(8);
        }
        return packageRoot;
    }

    public static String checkClassName(String className) {

        return className.replaceFirst("^\\s*", "");
    }

    public static int checkArrayLength(String[] array) {

        int i = 0;
        try {
            for (i = 0; array[i] != null; i++) {
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
        return i;
    }


    public static String[] cleanArrayNulls(String[] array) {

        int arrayLength = checkArrayLength(array);
        String[] cleanArray = new String[arrayLength];
        System.arraycopy(array, 0, cleanArray, 0, arrayLength);
        return cleanArray;
    }

}
