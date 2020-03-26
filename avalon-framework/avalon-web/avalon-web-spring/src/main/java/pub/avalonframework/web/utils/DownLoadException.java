package pub.avalonframework.web.utils;

import pub.avalonframework.web.entity.DownloadResult;
import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class DownLoadException extends AvalonException {

    private DownloadResult downloadResult;

    public DownLoadException(DownloadResult downloadResult) {
        this.downloadResult = downloadResult;
    }

    public DownLoadException(String message, DownloadResult downloadResult) {
        super(message);
        this.downloadResult = downloadResult;
    }

    public DownLoadException(String message, Throwable cause, DownloadResult downloadResult) {
        super(message, cause);
        this.downloadResult = downloadResult;
    }

    public DownLoadException(Throwable cause, DownloadResult downloadResult) {
        super(cause);
        this.downloadResult = downloadResult;
    }
}