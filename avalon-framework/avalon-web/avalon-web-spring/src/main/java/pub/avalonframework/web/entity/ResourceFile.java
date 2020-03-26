package pub.avalonframework.web.entity;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * 资源文件
 *
 * @author baichao
 */
public class ResourceFile {

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 保存名称
     */
    private String saveName;

    /**
     * 相对保存路径
     */
    private String relativeSavePath;

    /**
     * 绝对保存路径
     */
    private String absoluteSavePath;

    public ResourceFile(String absoluteSavePath, String saveName, String suffix) {
        this.absoluteSavePath = absoluteSavePath;
        this.saveName = saveName;
        this.suffix = suffix;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String fileRealName) {
        this.realName = realName;
    }

    public String getSuffix() {
        return suffix == null ? FilenameUtils.getExtension(this.getRealName()) : suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getRelativeSavePath() {
        return relativeSavePath;
    }

    public void setRelativeSavePath(String relativeSavePath) {
        this.relativeSavePath = relativeSavePath;
    }

    public String getAbsoluteSavePath() {
        return absoluteSavePath;
    }

    public void setAbsoluteSavePath(String absoluteSavePath) {
        this.absoluteSavePath = absoluteSavePath;
    }

    /**
     * 获取保存全名
     *
     * @return
     */
    public String getSaveFullName() {
        return this.getSaveName() + FilenameUtils.EXTENSION_SEPARATOR + this.getSuffix();
    }

    /**
     * 获取绝对保存全路径
     *
     * @return
     */
    public String getAbsoluteSaveFullPath() {
        return this.getAbsoluteSavePath() + File.separator + this.getSaveFullName();
    }

    /**
     * 获取相对保存全路径
     *
     * @return
     */
    public String getRelativeSaveFullPath() {
        return this.getRelativeSavePath() + File.separator + this.getSaveFullName();
    }
}