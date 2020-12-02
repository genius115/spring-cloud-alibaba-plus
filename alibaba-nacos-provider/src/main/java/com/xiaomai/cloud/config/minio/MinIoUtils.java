package com.xiaomai.cloud.config.minio;

/**
 * @author wangfeng
 * @date 2020/11/30
 */
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.PutObjectOptions;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Configuration
public class MinIoUtils {

    private MinIoParamConfig minIo;

    public MinIoUtils(MinIoParamConfig minIo) {
        this.minIo = minIo;
    }

    private MinioClient instance;

    private static final int DEFAULT_EXPIRY_TIME = 7 * 24 * 3600;

    @PostConstruct
    public void init() {
        try {
            instance = new MinioClient(minIo.getUrl(),minIo.getAccessKey(),minIo.getSecretKey());
        } catch (InvalidPortException e) {
            e.printStackTrace();
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断 bucket是否存在
     * @param bucketName
     * @return
     */
    public boolean bucketExists(String bucketName){
        try {
            return instance.bucketExists(bucketName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建 bucket
     * @param bucketName
     */
    public void makeBucket(String bucketName){
        try {
            boolean isExist = instance.bucketExists(bucketName);
            if(!isExist) {
                instance.makeBucket(bucketName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取全部bucket
     */
    public List<Bucket> getAllBuckets() throws Exception{
        return instance.listBuckets();
    }

    /**
     * 文件上传
     * @param bucketName
     * @param objectName
     * @param filename
     */
    public void putObject(String bucketName, String objectName, String filename){
        try {
            instance.putObject(bucketName,objectName,filename,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 文件上传
     * @param bucketName
     * @param multipartFile
     */
    public void putObject(String bucketName, MultipartFile multipartFile,String filename){
        try {
            // PutObjectOptions，上传配置(文件大小，内存中文件分片大小)
            PutObjectOptions putObjectOptions = new PutObjectOptions(multipartFile.getSize(), PutObjectOptions.MIN_MULTIPART_SIZE);
            // 文件的ContentType
            putObjectOptions.setContentType(multipartFile.getContentType());
            instance.putObject(bucketName,filename,multipartFile.getInputStream(),putObjectOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除文件
     * @param objectName
     */
    public boolean removeObject(String objectName){
        boolean flag=true;
        try {
            instance.removeObject(minIo.getBucketName(),objectName);
        } catch (Exception e) {
            flag=false;
        }
        return flag;
    }

    /**
     * 下载
     * @param fileName
     * @param originalName
     * @param response
     */
    public void downloadFile(String fileName,String originalName, HttpServletResponse response){
        try {

            InputStream file = instance.getObject(minIo.getBucketName(),fileName);
            String filename = new String(fileName.getBytes("ISO8859-1"), StandardCharsets.UTF_8);
            if(StringUtils.isNotEmpty(originalName)){
                fileName=originalName;
            }
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            int len;
            byte[] buffer = new byte[1024];
            while((len=file.read(buffer)) > 0){
                servletOutputStream.write(buffer, 0, len);
            }
            servletOutputStream.flush();
            file.close();
            servletOutputStream.close();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }


    /**
     * 有效期下载地址
     * @param bucketName
     * @param objectName
     * @param expires
     * @return
     */
    public String presignedObjectUrl(String bucketName, String objectName, int expires){
        try {
            if(StringUtils.isEmpty(bucketName)){
                bucketName = minIo.getBucketName();
            }
            if(expires<0){
                expires=24*60*60;
            }
            Map<String,String> reqParams = new HashMap<>();
            reqParams.put("response-content-disposition", "attachment; filename=\""+objectName+"\"");
            String url = instance.getPresignedObjectUrl(Method.GET,bucketName,objectName,expires,reqParams);
            return url;
        } catch (MinioException e) {
            e.printStackTrace();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    /**
     * 删除存储桶
     *
     * @param bucketName 存储桶名称
     * @return
     * @throws IOException
     * @throws XmlParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidResponseException
     * @throws InvalidBucketNameException
     * @throws InternalException
     * @throws InsufficientDataException
     * @throws IllegalArgumentException
     * @throws ErrorResponseException
     * @throws InvalidKeyException
     */
    public boolean removeBucket(String bucketName) throws InvalidKeyException, ErrorResponseException,
            IllegalArgumentException, InsufficientDataException, InternalException, InvalidBucketNameException,
            InvalidResponseException, NoSuchAlgorithmException, XmlParserException, IOException {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                // 有对象文件，则删除失败
                if (item.size() > 0) {
                    return false;
                }
            }
            // 删除存储桶，注意，只有存储桶为空时才能删除成功。
            instance.removeBucket(bucketName);
            flag = bucketExists(bucketName);
            if (!flag) {
                return true;
            }

        }
        return false;
    }

    /**
     * 列出存储桶中的所有对象名称
     *
     * @param bucketName 存储桶名称
     * @return
     * @throws IOException
     * @throws XmlParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidResponseException
     * @throws InvalidBucketNameException
     * @throws InternalException
     * @throws InsufficientDataException
     * @throws IllegalArgumentException
     * @throws ErrorResponseException
     * @throws InvalidKeyException
     */
    public List<String> listObjectNames(String bucketName) throws InvalidKeyException, ErrorResponseException,
            IllegalArgumentException, InsufficientDataException, InternalException, InvalidBucketNameException,
            InvalidResponseException, NoSuchAlgorithmException, XmlParserException, IOException {
        List<String> listObjectNames = new ArrayList<>();
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                listObjectNames.add(item.objectName());
            }
        }
        return listObjectNames;
    }

    /**
     * 列出存储桶中的所有对象
     *
     * @param bucketName 存储桶名称
     * @return
     * @throws IOException
     * @throws XmlParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidResponseException
     * @throws InvalidBucketNameException
     * @throws InternalException
     * @throws InsufficientDataException
     * @throws IllegalArgumentException
     * @throws ErrorResponseException
     * @throws InvalidKeyException
     */
    public Iterable<Result<Item>> listObjects(String bucketName) throws InvalidKeyException, ErrorResponseException,
            IllegalArgumentException, InsufficientDataException, InternalException, InvalidBucketNameException,
            InvalidResponseException, NoSuchAlgorithmException, XmlParserException, IOException {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            return instance.listObjects(bucketName);
        }
        return null;
    }

    /**
     * 获取对象的元数据
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     * @throws IOException
     * @throws XmlParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidResponseException
     * @throws InvalidBucketNameException
     * @throws InternalException
     * @throws InsufficientDataException
     * @throws IllegalArgumentException
     * @throws ErrorResponseException
     * @throws InvalidKeyException
     */
    public ObjectStat statObject(String bucketName, String objectName)
            throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException,
            XmlParserException, IOException {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            ObjectStat statObject = instance.statObject(bucketName, objectName);
            return statObject;
        }
        return null;
    }
    /**
     * 通过文件上传到对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param fileName   File name
     * @return
     * @throws InvalidKeyException
     * @throws ErrorResponseException
     * @throws IllegalArgumentException
     * @throws InsufficientDataException
     * @throws InternalException
     * @throws InvalidBucketNameException
     * @throws InvalidResponseException
     * @throws NoSuchAlgorithmException
     * @throws XmlParserException
     * @throws IOException
     */
    public boolean putObjectByFileName(String bucketName, String objectName, String fileName)
            throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException,
            XmlParserException, IOException {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            instance.putObject(bucketName, objectName, fileName, null);
            ObjectStat statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.length() > 0) {
                return true;
            }
        }
        return false;

    }

    /**
     * 通过InputStream上传对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param stream     要上传的流
     * @return
     * @throws IOException
     * @throws XmlParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidResponseException
     * @throws InvalidBucketNameException
     * @throws InternalException
     * @throws InsufficientDataException
     * @throws IllegalArgumentException
     * @throws ErrorResponseException
     * @throws InvalidKeyException
     */
    public boolean putObject(String bucketName, String objectName, InputStream stream)
            throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException,
            XmlParserException, IOException {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            instance.putObject(bucketName, objectName, stream, new PutObjectOptions(stream.available(), -1));
            ObjectStat statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.length() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 以流的形式获取一个文件对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     * @throws IOException
     * @throws XmlParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidResponseException
     * @throws InvalidBucketNameException
     * @throws InternalException
     * @throws InsufficientDataException
     * @throws IllegalArgumentException
     * @throws ErrorResponseException
     * @throws InvalidKeyException
     */
    public InputStream getObject(String bucketName, String objectName)
            throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException,
            XmlParserException, IOException {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            ObjectStat statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.length() > 0) {
                InputStream stream = instance.getObject(bucketName, objectName);
                return stream;
            }
        }
        return null;
    }

    /**
     * 以流的形式获取一个文件对象（断点下载）
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param offset     起始字节的位置
     * @param length     要读取的长度 (可选，如果无值则代表读到文件结尾)
     * @return
     * @throws IOException
     * @throws XmlParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidResponseException
     * @throws InvalidBucketNameException
     * @throws InternalException
     * @throws InsufficientDataException
     * @throws IllegalArgumentException
     * @throws ErrorResponseException
     * @throws InvalidKeyException
     */
    public InputStream getObject(String bucketName, String objectName, long offset, Long length)
            throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException,
            XmlParserException, IOException {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            ObjectStat statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.length() > 0) {
                InputStream stream = instance.getObject(bucketName, objectName, offset, length);
                return stream;
            }
        }
        return null;
    }

    /**
     * 下载并将文件保存到本地
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param fileName   File name
     * @return
     * @throws IOException
     * @throws XmlParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidResponseException
     * @throws InvalidBucketNameException
     * @throws InternalException
     * @throws InsufficientDataException
     * @throws IllegalArgumentException
     * @throws ErrorResponseException
     * @throws InvalidKeyException
     */
    public boolean getObject(String bucketName, String objectName, String fileName)
            throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException,
            XmlParserException, IOException {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            ObjectStat statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.length() > 0) {
                instance.getObject(bucketName, objectName, fileName);
                return true;
            }
        }
        return false;
    }

    /**
     * 删除一个对象
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @throws IOException
     * @throws XmlParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidResponseException
     * @throws InvalidBucketNameException
     * @throws InternalException
     * @throws InsufficientDataException
     * @throws IllegalArgumentException
     * @throws ErrorResponseException
     * @throws InvalidKeyException
     */
    public boolean removeObject(String bucketName, String objectName)
            throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException,
            XmlParserException, IOException {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            instance.removeObject(bucketName, objectName);
            return true;
        }
        return false;

    }

    /**
     * 删除指定桶的多个文件对象,返回删除错误的对象列表，全部删除成功，返回空列表
     *
     * @param bucketName  存储桶名称
     * @param objectNames 含有要删除的多个object名称的迭代器对象
     * @return
     * @throws InvalidKeyException
     * @throws ErrorResponseException
     * @throws IllegalArgumentException
     * @throws InsufficientDataException
     * @throws InternalException
     * @throws InvalidBucketNameException
     * @throws InvalidResponseException
     * @throws NoSuchAlgorithmException
     * @throws XmlParserException
     * @throws IOException
     */
    public List<String> removeObject(String bucketName, List<String> objectNames)
            throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException,
            XmlParserException, IOException {
        List<String> deleteErrorNames = new ArrayList<>();
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<DeleteError>> results = instance.removeObjects(bucketName, objectNames);
            for (Result<DeleteError> result : results) {
                DeleteError error = result.get();
                deleteErrorNames.add(error.objectName());
            }
        }
        return deleteErrorNames;
    }
    /**
     * 生成一个给HTTP GET请求用的presigned URL。
     * 浏览器/移动端的客户端可以用这个URL进行下载，即使其所在的存储桶是私有的。这个presigned URL可以设置一个失效时间，默认值是7天。
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param expires    失效时间（以秒为单位），默认是7天，不得大于七天
     * @return
     * @throws IOException
     * @throws XmlParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidResponseException
     * @throws InvalidBucketNameException
     * @throws InternalException
     * @throws InsufficientDataException
     * @throws IllegalArgumentException
     * @throws ErrorResponseException
     * @throws InvalidKeyException
     * @throws InvalidExpiresRangeException
     */
    public String presignedGetObject(String bucketName, String objectName, Integer expires)
            throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException,
            XmlParserException, IOException, InvalidExpiresRangeException {
        boolean flag = bucketExists(bucketName);
        String url = "";
        if (flag) {
            if (expires < 1 || expires > DEFAULT_EXPIRY_TIME) {
                throw new InvalidExpiresRangeException(expires,
                        "expires must be in range of 1 to " + DEFAULT_EXPIRY_TIME);
            }
            url = instance.presignedGetObject(bucketName, objectName, expires);
        }
        return url;
    }

    /**
     * 生成一个给HTTP PUT请求用的presigned URL。
     * 浏览器/移动端的客户端可以用这个URL进行上传，即使其所在的存储桶是私有的。这个presigned URL可以设置一个失效时间，默认值是7天。
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @param expires    失效时间（以秒为单位），默认是7天，不得大于七天
     * @return
     * @throws InvalidKeyException
     * @throws ErrorResponseException
     * @throws IllegalArgumentException
     * @throws InsufficientDataException
     * @throws InternalException
     * @throws InvalidBucketNameException
     * @throws InvalidResponseException
     * @throws NoSuchAlgorithmException
     * @throws XmlParserException
     * @throws IOException
     * @throws InvalidExpiresRangeException
     */
    public String presignedPutObject(String bucketName, String objectName, Integer expires)
            throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException,
            XmlParserException, IOException, InvalidExpiresRangeException {
        boolean flag = bucketExists(bucketName);
        String url = "";
        if (flag) {
            if (expires < 1 || expires > DEFAULT_EXPIRY_TIME) {
                throw new InvalidExpiresRangeException(expires,
                        "expires must be in range of 1 to " + DEFAULT_EXPIRY_TIME);
            }
            url = instance.presignedPutObject(bucketName, objectName, expires);
        }
        return url;
    }
    /**
     * 文件访问路径
     *
     * @param bucketName 存储桶名称
     * @param objectName 存储桶里的对象名称
     * @return
     * @throws IOException
     * @throws XmlParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidResponseException
     * @throws InvalidBucketNameException
     * @throws InternalException
     * @throws InsufficientDataException
     * @throws IllegalArgumentException
     * @throws ErrorResponseException
     * @throws InvalidKeyException
     */
    public String getObjectUrl(String bucketName, String objectName) throws InvalidKeyException,ErrorResponseException,
            IllegalArgumentException, InsufficientDataException, InternalException, InvalidBucketNameException,
            InvalidResponseException, NoSuchAlgorithmException, XmlParserException, IOException {
        boolean flag = bucketExists(bucketName);
        String url = "";
        if (flag) {
            url = instance.getObjectUrl(bucketName, objectName);
        }
        return url;
    }
}