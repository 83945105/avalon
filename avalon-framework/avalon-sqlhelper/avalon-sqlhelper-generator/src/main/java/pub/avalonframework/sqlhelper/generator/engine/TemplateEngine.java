package pub.avalonframework.sqlhelper.generator.engine;

import pub.avalonframework.sqlhelper.generator.options.GenerateOptions;
import pub.avalonframework.sqlhelper.generator.options.OutputOptions;

import java.io.File;
import java.util.Map;

/**
 * @author baichao
 */
public interface TemplateEngine {

    default TemplateEngine addTable(String tableName, String tableAlias) {
        return addTable(tableName, tableAlias, getDefaultGenerateOptions());
    }

    TemplateEngine addTable(String tableName, String tableAlias, GenerateOptions generateOptions);

    default TemplateEngine setTables(Map<String, String> tableNameAliasMap) {
        return setTables(tableNameAliasMap, getDefaultGenerateOptions());
    }

    TemplateEngine setTables(Map<String, String> tableNameAliasMap, GenerateOptions generateOptions);

    GenerateOptions getDefaultGenerateOptions();

    TemplateEngine setDefaultGenerateOptions(GenerateOptions defaultGenerateOptions);

    OutputOptions getDefaultOutputOptions();

    TemplateEngine setDefaultOutputOptions(OutputOptions defaultOutputOptions);

    default String generateJavaCode(String tableName) {
        return generateJavaCode(tableName, getDefaultOutputOptions());
    }

    String generateJavaCode(String tableName, OutputOptions outputOptions);

    default void generateJavaFile(String tableName) {
        generateJavaFile(tableName, getDefaultOutputOptions());
    }

    void generateJavaFile(String tableName, OutputOptions outputOptions);

    default void generateJavaFiles() {
        generateJavaFiles(getDefaultOutputOptions());
    }

    void generateJavaFiles(OutputOptions outputOptions);

    default void generateClassFile(String tableName) {
        generateClassFile(tableName, getDefaultOutputOptions());
    }

    void generateClassFile(String tableName, OutputOptions outputOptions);

    default void generateClassFiles() {
        generateClassFiles(getDefaultOutputOptions());
    }

    void generateClassFiles(OutputOptions outputOptions);

    static boolean recursionDeleteFile(File directory, String fileName) {
        if (!directory.isDirectory()) {
            throw new RuntimeException("File " + fileName + " not a folder.");
        }
        File[] files = directory.listFiles();
        if (files == null) {
            return false;
        }
        for (File f : files) {
            if (f.isFile() && f.getName().equals(fileName)) {
                return directory.delete();
            }
            if (f.isDirectory()) {
                return recursionDeleteFile(f, fileName);
            }
        }
        return false;
    }

    static boolean deleteFile(String directory, String fileName) {
        return recursionDeleteFile(new File(directory), fileName);
    }

    static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if(!file.exists()) {
            return false;
        }
        return file.delete();
    }

    static boolean deleteAllFiles(File directory) {
        if (!directory.isDirectory()) {
            throw new RuntimeException("File " + directory.getName() + " not a folder.");
        }
        File[] files = directory.listFiles();
        if (files == null) {
            return true;
        }
        for (File f : files) {
            if (f.isDirectory() && f.listFiles() != null) {
                deleteAllFiles(f);
            }
            if (!f.delete()) {
                throw new RuntimeException("Delete file " + f.getName() + " fail.");
            }
        }
        return true;
    }
}