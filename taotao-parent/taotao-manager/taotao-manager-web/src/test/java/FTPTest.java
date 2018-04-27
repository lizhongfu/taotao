import com.taotao.common.utils.FtpUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.File;

public class FTPTest {
    @Test
    public void testFtpClient() throws Exception {
        //1、连接ftp服务器
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("192.168.56.101", 21);
        //2、登录ftp服务器
        ftpClient.login("ftpuser", "ftpuser");
        //3、读取本地文件
        FileInputStream inputStream = new FileInputStream(new File("/Users/lizhongfu/Documents/image/i1.letvimg.com_vrs_201304_10_f17212d564704331a0e22bb9b99e02fa.jpg"));
        //4、上传文件
        //1）指定上传目录
        ftpClient.changeWorkingDirectory("/usr/local/nginx/html");
        //2）指定文件类型
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        //第一个参数：文件在远程服务器的名称
        //第二个参数：文件流
        ftpClient.storeFile("hello.jpg", inputStream);
        //5、退出登录
        ftpClient.logout();

    }

    @Test
    public void testFTPUtil() throws Exception {
        FileInputStream inputStream = new FileInputStream(new File("/Users/lizhongfu/Documents/image/i1.letvimg.com_vrs_201304_10_f17212d564704331a0e22bb9b99e02fa.jpg"));
        FtpUtil.uploadFile("192.168.56.101", 21, "ftpuser", "ftpuser", "/usr/local/nginx/html", "/","hello1.jpg", inputStream);
    }
}
