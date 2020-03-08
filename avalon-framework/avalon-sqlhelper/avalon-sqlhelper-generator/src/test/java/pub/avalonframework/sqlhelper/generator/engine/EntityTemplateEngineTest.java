package pub.avalonframework.sqlhelper.generator.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.sqlhelper.generator.jdbc.JdbcTemplate;
import pub.avalonframework.sqlhelper.generator.jdbc.JdbcTemplateBuilder;
import pub.avalonframework.sqlhelper.generator.options.EntityOptions;
import pub.avalonframework.sqlhelper.generator.options.GenerateOptions;
import pub.avalonframework.sqlhelper.generator.options.OutputOptions;

import java.io.File;

/**
 * @author baichao
 */
public class EntityTemplateEngineTest {

    private final static String ROOT_PATH = System.getProperty("user.dir") + File.separator + "generator";

    private final static String PACKAGE_PATH = "pub.avalonframework.sqlhelper.generator.entity";

    private final static String PACKAGE_DIR_PATH = File.separator + PACKAGE_PATH.replaceAll("\\.", File.separator) + File.separator;

    @Test
    void Test_generateJavaFile_deleteFiles() {
        JdbcTemplate jdbcTemplate = JdbcTemplateBuilder.newJdbcTemplateBuilder()
                .setDriverClassName("com.mysql.cj.jdbc.Driver")
                .setJdbcUrl("jdbc:mysql://localhost:3306/sqlhelper?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false")
                .setUsername("root")
                .setPassword("19910405")
                .build();
        TemplateEngineBuilder.newTemplateEngineBuilder()
                .setJdbcTemplate(jdbcTemplate)
                .buildEntityTemplateEngine()
                .setDefaultGenerateOptions(new GenerateOptions().setPackagePath(PACKAGE_PATH))
                .addTable("sys_user", "SysUser")
                .addTable("role_resource", "RoleResource")
                .addTable("user_role", "UserRole")
                .generateJavaFiles(new OutputOptions(ROOT_PATH));
        File file1 = new File(ROOT_PATH + PACKAGE_DIR_PATH + "SysUser.java");
        Assertions.assertTrue(file1.exists());
        File file2 = new File(ROOT_PATH + PACKAGE_DIR_PATH + "RoleResource.java");
        Assertions.assertTrue(file2.exists());
        File file3 = new File(ROOT_PATH + PACKAGE_DIR_PATH + "UserRole.java");
        Assertions.assertTrue(file3.exists());
        TemplateEngine.deleteAllFiles(new File(ROOT_PATH));
        TemplateEngine.deleteFile(ROOT_PATH);
        Assertions.assertFalse(file1.exists());
        Assertions.assertFalse(file2.exists());
        Assertions.assertFalse(file3.exists());
    }

    @Test
    void Test_generateClassFile_deleteFiles() {
        JdbcTemplate jdbcTemplate = JdbcTemplateBuilder.newJdbcTemplateBuilder()
                .setDriverClassName("com.mysql.cj.jdbc.Driver")
                .setJdbcUrl("jdbc:mysql://localhost:3306/sqlhelper?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false")
                .setUsername("root")
                .setPassword("19910405")
                .build();
        TemplateEngineBuilder.newTemplateEngineBuilder()
                .setJdbcTemplate(jdbcTemplate)
                .buildEntityTemplateEngine()
                .setDefaultGenerateOptions(new GenerateOptions().setPackagePath(PACKAGE_PATH))
                .addTable("sys_user", "SysUser")
                .addTable("role_resource", "RoleResource")
                .addTable("user_role", "UserRole")
                .generateClassFiles(new OutputOptions(ROOT_PATH));
        File file1 = new File(ROOT_PATH + PACKAGE_DIR_PATH + "SysUser.class");
        Assertions.assertTrue(file1.exists());
        File file2 = new File(ROOT_PATH + PACKAGE_DIR_PATH + "RoleResource.class");
        Assertions.assertTrue(file2.exists());
        File file3 = new File(ROOT_PATH + PACKAGE_DIR_PATH + "UserRole.class");
        Assertions.assertTrue(file3.exists());
        TemplateEngine.deleteAllFiles(new File(ROOT_PATH));
        TemplateEngine.deleteFile(ROOT_PATH);
        Assertions.assertFalse(file1.exists());
        Assertions.assertFalse(file2.exists());
        Assertions.assertFalse(file3.exists());
    }

    @Test
    void Test_generateClassFile_customGenerateOptions_deleteFiles() {
        JdbcTemplate jdbcTemplate = JdbcTemplateBuilder.newJdbcTemplateBuilder()
                .setDriverClassName("com.mysql.cj.jdbc.Driver")
                .setJdbcUrl("jdbc:mysql://localhost:3306/sqlhelper?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false")
                .setUsername("root")
                .setPassword("19910405")
                .build();
        TemplateEngineBuilder.newTemplateEngineBuilder()
                .setJdbcTemplate(jdbcTemplate)
                .buildEntityTemplateEngine()
                .setDefaultGenerateOptions(new GenerateOptions()
                        .setPackagePath(PACKAGE_PATH)
                        .setEntityOptions(new EntityOptions().setClassNameSuffix("Model")))
                .addTable("sys_user", "SysUser")
                .addTable("role_resource", "RoleResource")
                .addTable("user_role", "UserRole")
                .generateClassFiles(new OutputOptions(ROOT_PATH));
        File file1 = new File(ROOT_PATH + PACKAGE_DIR_PATH + "SysUserModel.class");
        Assertions.assertTrue(file1.exists());
        File file2 = new File(ROOT_PATH + PACKAGE_DIR_PATH + "RoleResourceModel.class");
        Assertions.assertTrue(file2.exists());
        File file3 = new File(ROOT_PATH + PACKAGE_DIR_PATH + "UserRoleModel.class");
        Assertions.assertTrue(file3.exists());
        TemplateEngine.deleteAllFiles(new File(ROOT_PATH));
        TemplateEngine.deleteFile(ROOT_PATH);
        Assertions.assertFalse(file1.exists());
        Assertions.assertFalse(file2.exists());
        Assertions.assertFalse(file3.exists());
    }
}