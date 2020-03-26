package pub.avalonframework.web.utils;

import pub.avalonframework.web.entity.CopyResourceFile;
import pub.avalonframework.web.entity.DownloadResult;
import pub.avalonframework.web.entity.ResourceFile;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The resource utils.
 *
 * @author baichao
 */
public final class ResourceUtils {

    private ResourceUtils() {
    }

    /**
     * 项目路径
     */
//    public static final String PROJECT_PATH = getProjectPath();

    /**
     * 项目所在文件夹路径
     */
//    public static final String PROJECT_FOLDER_PATH = getProjectFolderPath();

    private static final Pattern PROJECT_PATH_PATTERN = Pattern.compile("^[/\\\\](.*?)[^/\\\\]+[/\\\\][^/\\\\]+[/\\\\][^/\\\\]+[/\\\\]$");

    /**
     * 获取项目路径
     *
     * @return
     */
    public static String getProjectPath() {
        String projectPath = ResourceUtils.class.getClassLoader().getResource("").getPath();
        try {
            projectPath = URLDecoder.decode(projectPath, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int end = projectPath.length() - "WEB-INF/classes/".length();
        return projectPath.substring(1, end);
    }

    /**
     * 获取项目所在文件夹路径
     *
     * @return
     */
    public static String getProjectFolderPath() {
        String projectPath = ResourceUtils.class.getClassLoader().getResource("").getPath();
        try {
            projectPath = URLDecoder.decode(projectPath, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Matcher matcher = PROJECT_PATH_PATTERN.matcher(projectPath);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /**
     * 获取资源真实路径
     *
     * @param path 资源相对项目路径
     * @return 资源全路径
     */
/*    public static String getRealPath(String path) {
        return PROJECT_PATH + File.separator + path;
    }*/

    /**
     * 获取资源的真实项目所在文件夹路径
     *
     * @param path 资源项目于项目所在文件夹路径
     * @return 资源全路径
     */
/*    public static String getRealProjectFolderPath(String path) {
        return PROJECT_FOLDER_PATH + File.separator + path;
    }*/

    /**
     * 下载
     *
     * @param fileName    文件名称
     * @param suffix      文件后缀
     * @param inputStream 文件流
     * @param request     请求
     * @param response    响应
     * @return
     * @throws UnsupportedEncodingException
     * @throws DownLoadException
     */
    public static DownloadResult download(String fileName, String suffix, String filePath, InputStream inputStream,
                                          HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, DownLoadException {
        //声明本次下载状态的记录对象
        DownloadResult downloadResult = new DownloadResult(fileName, filePath, request);
        //设置响应头和客户端保存文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("utf-8"), "ISO-8859-1") + "." + suffix);
        //用于记录以完成的下载的数据量，单位是byte
        long downloadedLength = 0L;
        OutputStream os = null;
        try {
            //激活下载操作
            os = response.getOutputStream();
            //循环写入输出流
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
                downloadedLength += b.length;
            }

        } catch (Exception e) {
            downloadResult.setStatus(DownloadResult.STATUS_ERROR);
            throw new DownLoadException(e, downloadResult);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        downloadResult.setStatus(DownloadResult.STATUS_SUCCESS);
        downloadResult.setEndTime(new Timestamp(System.currentTimeMillis()));
        downloadResult.setLength(downloadedLength);
        return downloadResult;
    }

    /**
     * 下载
     *
     * @param fileName 文件名称
     * @param suffix   文件后缀
     * @param filePath 文件路径
     * @param request  请求
     * @param response 响应
     * @return
     * @throws UnsupportedEncodingException
     * @throws DownLoadException
     * @throws FileNotFoundException
     */
    public static DownloadResult download(String fileName, String suffix, String filePath,
                                          HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, DownLoadException, FileNotFoundException {
        return download(fileName, suffix, filePath, new FileInputStream(filePath), request, response);
    }

    /**
     * 将base64保存为图片
     *
     * @param base64       图片base64数据
     * @param savePath     文件保存路径(包含项目路径,不包含文件名和后缀名,尾部不用加/)
     * @param saveFileName 保存文件名(不包含后缀名)
     * @param suffix       文件后缀
     * @throws IOException
     */
    public static void base64ToImage(String base64, String savePath, String saveFileName, String suffix) throws IOException {
        if (base64 == null || base64.isEmpty()) {
            throw new ResourceException("there is no base64 data.");
        }
        base64 = base64.replace("data:image/" + suffix + ";base64,", "");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(base64);
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String saveFile = savePath + File.separator + saveFileName + FilenameUtils.EXTENSION_SEPARATOR + suffix;
        OutputStream out = null;
        try {
            out = new FileOutputStream(saveFile);
            out.write(b);
            out.flush();
        } catch (IOException e) {
            throw new ResourceException(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean createFile(String parent, String child, MultipartFile targetFile) throws Exception {
        File parentFile = new File(parent);
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        File file = new File(parent, child);
        if (!file.exists()) {
            targetFile.transferTo(file);
            return true;
        }
        return false;
    }

    /**
     * 文件上传之前操作
     * 可以在此校验文件,抛出异常终止上传
     */
    @FunctionalInterface
    public interface UploadBefore {

        /**
         * 接收上传结果
         *
         * @param resourceFile 上传文件
         * @throws Exception
         */
        void accept(ResourceFile resourceFile) throws Exception;
    }

    /**
     * 获取文件后缀名
     *
     * @param file 文件
     * @return 后缀名
     */
    private static String getFileSuffix(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return null;
        }
        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }

    /**
     * 上传资源文件
     *
     * @param file             文件
     * @param absoluteSavePath 文件保存绝对路径
     * @param relativeSavePath 文件保存相对路径
     * @param fileSaveName     文件保存名
     * @return
     * @throws Exception
     */
    public static ResourceFile uploadFile(MultipartFile file, String absoluteSavePath, String relativeSavePath, String fileSaveName) throws Exception {
        return uploadFile(file, absoluteSavePath, relativeSavePath, fileSaveName, getFileSuffix(file), uploadResult -> {
        });
    }

    /**
     * 上传资源文件
     *
     * @param file             文件
     * @param absoluteSavePath 文件保存绝对路径
     * @param relativeSavePath 文件保存相对路径
     * @param fileSaveName     文件保存名
     * @param saveSuffix       文件保存后缀名
     * @return
     * @throws Exception
     */
    public static ResourceFile uploadFile(MultipartFile file, String absoluteSavePath, String relativeSavePath, String fileSaveName, String saveSuffix) throws Exception {
        return uploadFile(file, absoluteSavePath, relativeSavePath, fileSaveName, saveSuffix, uploadResult -> {
        });
    }

    /**
     * 上传资源文件
     *
     * @param file             文件
     * @param absoluteSavePath 文件保存绝对路径
     * @param relativeSavePath 文件保存相对路径
     * @param fileSaveName     文件保存名
     * @param uploadBefore     文件上传前的回调
     * @return
     * @throws Exception
     */
    public static ResourceFile uploadFile(MultipartFile file, String absoluteSavePath, String relativeSavePath, String fileSaveName, UploadBefore uploadBefore) throws Exception {
        return uploadFile(file, absoluteSavePath, relativeSavePath, fileSaveName, getFileSuffix(file), uploadBefore);
    }

    /**
     * 上传资源文件
     *
     * @param file             文件
     * @param absoluteSavePath 文件保存绝对路径
     * @param relativeSavePath 文件保存相对路径
     * @param fileSaveName     文件保存名
     * @param saveSuffix       文件保存后缀名
     * @param uploadBefore     文件上传前的回调
     * @return
     * @throws Exception
     */
    public static ResourceFile uploadFile(MultipartFile file, String absoluteSavePath, String relativeSavePath, String fileSaveName, String saveSuffix, UploadBefore uploadBefore) throws Exception {
        ResourceFile result = new ResourceFile(absoluteSavePath, fileSaveName, saveSuffix);
        result.setRelativeSavePath(relativeSavePath);
        return uploadFile(file, result, uploadBefore);
    }

    /**
     * 上传资源文件
     *
     * @param file         文件
     * @param uploadFile   上传信息
     * @param uploadBefore 文件上传前的回调
     * @return
     * @throws Exception
     */
    public static <T extends ResourceFile> T uploadFile(MultipartFile file, T uploadFile, UploadBefore uploadBefore) throws Exception {
        uploadFile.setRealName(file.getOriginalFilename());
        uploadBefore.accept(uploadFile);
        createFile(uploadFile.getAbsoluteSavePath(), uploadFile.getSaveFullName(), file);
        return uploadFile;
    }

    /**
     * 复制资源文件
     *
     * @param sourceFullPath   源路径
     * @param absoluteSavePath 文件保存绝对路径
     * @param relativeSavePath 文件保存相对路径
     * @param saveFileName     文件保存名
     * @param suffix           文件后缀名
     * @return
     * @throws Exception
     */
    public static CopyResourceFile copyResourceFile(String sourceFullPath, String absoluteSavePath, String relativeSavePath, String saveFileName, String suffix) throws Exception {
        File source = new File(sourceFullPath);
        if (!source.exists()) {
            throw new ResourceException("the source is not exist, copy fail.");
        }
        CopyResourceFile copyResourceFile = new CopyResourceFile(absoluteSavePath, saveFileName, suffix);
        copyResourceFile.setSourcePath(sourceFullPath);
        copyResourceFile.setRealName(source.getName());
        copyResourceFile.setRelativeSavePath(relativeSavePath);
        File dic = new File(copyResourceFile.getAbsoluteSavePath());
        if (!dic.exists()) {
            boolean b = dic.mkdirs();
            if (!b) {
                throw new ResourceException("create dirs fail.");
            }
        } else if (!dic.isDirectory()) {
            throw new ResourceException("savePath is not a folder.");
        }
        File dest = new File(copyResourceFile.getAbsoluteSaveFullPath());
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (Exception e) {
            throw new ResourceException("copy fail.", e);
        } finally {
            try {
                if (inputChannel != null) {
                    inputChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputChannel != null) {
                    outputChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return copyResourceFile;
    }

    public static MultipartFile buildMultipartFile(String fieldName, File file) {
        final DiskFileItem item = new DiskFileItem(fieldName, MediaType.MULTIPART_FORM_DATA_VALUE, true, file.getName(), 100000000, file.getParentFile());
        try {
            OutputStream os = item.getOutputStream();
            os.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace(); // do nothing!
        }
        return new CommonsMultipartFile(item);
    }
}