package pub.avalonframework.web.entity;

/**
 * The copy resource file.
 *
 * @author baichao
 */
public class CopyResourceFile extends ResourceFile {

    /**
     * 源路径
     */
    private String sourcePath;

    public CopyResourceFile(String fileAbsoluteSavePath, String fileSaveName, String fileSuffix) {
        super(fileAbsoluteSavePath, fileSaveName, fileSuffix);
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }
}
