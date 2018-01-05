import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.BufferPoolMXBean;
import java.util.List;
import java.util.Map;

/**
 * Created by yzd on 2018/1/4.
 */
public class Client {

    /**
     * 发送GET请求
     * @param url
     *          目的地址
     * @param parameters
     *          请求参数 Map类型
     * @return
     */
    public static String sendGet(String url, Map<String,String> parameters) {
        String result = "";//返回的结果
        BufferedReader in = null;//读取响应输入流
        StringBuffer sb = new StringBuffer();//存储参数
        String params = "";//编码之后的参数
        try {
            //编码请求参数
            if(parameters.size() == 1) {
                for(String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                            "UTF-8"));
                }
                params = sb.toString();
            } else {
                for(String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),"UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0,temp_params.length() - 1);

            }
            String full_url = url + "?" + params;
            System.out.println(full_url);
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(full_url);
            //打开URL链接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
            //设置通用属性
            httpConn.setRequestProperty("Accept","*/*");
            httpConn.setRequestProperty("Connection","Keep-Alive");
            httpConn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            //建立实际的链接
            httpConn.connect();
            //响应头部获取
            Map<String, List<String>> headers = httpConn.getHeaderField();
            //便利所有的响应头字段
            for(String key : headers.keySet()){
                System.out.println(key + "\t: \t" + headers.get(key));
            }
            //定义BufferedReader输入流来读取URL的响应，并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn
                            .getInputStream(),"UTF-8"));
            String line;

        } catch (Exception e) {

        } finally {

        }
    }

    /**
     * 发送POST请求
     * @param url
     * @param parameters
     * @return
     */
    public static String sendPost(String url,Map<String, String> parameters) {

    }

    /**
     * 主函数 测试请求
     * @param args
     */
    public static void main(String[] args) {

    }
}
