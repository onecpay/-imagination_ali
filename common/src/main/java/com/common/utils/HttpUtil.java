package com.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * http工具类
 * @author yangzhongying
 */
public class HttpUtil {

    private static ConnectionKeepAliveStrategy myStrategy = null;

    static {
        myStrategy =
                (response, context) -> {
                    HeaderElementIterator it =
                            new BasicHeaderElementIterator(response.headerIterator("Keep-Alive"));
                    while(it.hasNext()) {
                        HeaderElement he = it.nextElement();
                        String param = he.getName();
                        String value = he.getValue();
                        if((value != null) && ("timeout".equalsIgnoreCase(param))) {
                            return 10 * 1000L;
                        }
                    }
                    return 5000L;
                };
    }

    private static CloseableHttpClient httpclient = createSSLClientDefault();

    public static JSONObject post(Map<String, String> params, String url) throws Exception {

        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig =
                RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000).build();
        post.setConfig(requestConfig);

        List<NameValuePair> list = new ArrayList<>();

        Iterator<String> it = params.keySet().iterator();
        while(it.hasNext()) {
            String key = it.next();
            list.add(new BasicNameValuePair(key, params.get(key)));
        }
        post.setEntity(new UrlEncodedFormEntity(list, Consts.UTF_8));
        CloseableHttpResponse response = httpclient.execute(post);
        JSONObject jo = null;
        if(response.getStatusLine().getStatusCode() == HttpServletResponse.SC_OK) {
            HttpEntity entity = response.getEntity();
            String str = EntityUtils.toString(entity);
            if(entity != null) {
                jo = JSONObject.parseObject(str);
            }
        } else {
            EntityUtils.consume(response.getEntity());
        }

        return jo;
    }

    public static String postFumin(Map<String, String> params, String url) throws Exception {

        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig =
                RequestConfig.custom().setConnectTimeout(600000).setSocketTimeout(600000).build();
        post.setConfig(requestConfig);

        List<NameValuePair> list = new ArrayList<>();

        Iterator<String> it = params.keySet().iterator();
        while(it.hasNext()) {
            String key = it.next();
            list.add(new BasicNameValuePair(key, (String) params.get(key)));
        }
        post.setEntity(new UrlEncodedFormEntity(list, Consts.UTF_8));
        CloseableHttpResponse response = httpclient.execute(post);
        JSONObject jo = null;
        if(response.getStatusLine().getStatusCode() == HttpServletResponse.SC_OK) {
            HttpEntity entity = response.getEntity();
            String str = EntityUtils.toString(entity);
            if(entity != null) {
                return str;
            }
        } else {
            EntityUtils.consume(response.getEntity());
        }

        return null;
    }

    public static String postFileMultiPart(String url, Map<String, ContentBody> reqParam)
            throws Exception {
        HttpPost httppost = new HttpPost(url);
        RequestConfig defaultRequestConfig =
                RequestConfig.custom()
                        .setConnectTimeout(30000)
                        .setConnectionRequestTimeout(30000)
                        .setSocketTimeout(30000)
                        .build();
        httppost.setConfig(defaultRequestConfig);

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        for(Map.Entry param : reqParam.entrySet()) {
            multipartEntityBuilder.addPart((String) param.getKey(), (ContentBody) param.getValue());
        }
        HttpEntity reqEntity = multipartEntityBuilder.build();
        httppost.setEntity(reqEntity);

        CloseableHttpResponse response = httpclient.execute(httppost);

        HttpEntity entity = response.getEntity();
        if(entity != null) {
            return EntityUtils.toString(entity);
        }

        return null;
    }

    public static String postAndReturnString(Map<String, Object> params, String url)
            throws Exception {
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig =
                RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(60000).build();

        post.setConfig(requestConfig);

        List<NameValuePair> list = new ArrayList<>();

        Iterator<String> it = params.keySet().iterator();
        while(it.hasNext()) {
            String key = it.next();
            list.add(new BasicNameValuePair(key, (String) params.get(key)));
        }

        post.setEntity(new UrlEncodedFormEntity(list, Consts.UTF_8));
        CloseableHttpResponse response = httpclient.execute(post);
        String jo = null;
        if(response.getStatusLine().getStatusCode() == HttpServletResponse.SC_OK) {
            HttpEntity entity = response.getEntity();
            jo = EntityUtils.toString(entity);
        } else {
            EntityUtils.consume(response.getEntity());
        }

        return jo;
    }

    public static String testPost(Map<String, Object> params, String url) throws Exception {
        HttpPost post = new HttpPost(url);

        RequestConfig requestConfig =
                RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000).build();
        post.setConfig(requestConfig);

        post.addHeader("Charset", StandardCharsets.UTF_8.name());
        post.addHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        JSONObject req = new JSONObject();
        Iterator<String> it = params.keySet().iterator();
        while(it.hasNext()) {
            String key = it.next();
            req.put(key, params.get(key));
        }
        post.setEntity(new StringEntity(req.toJSONString(), ContentType.APPLICATION_JSON));
        CloseableHttpResponse response = httpclient.execute(post);
        String redirectUrl = null;
        if(response.getStatusLine().getStatusCode() == HttpServletResponse.SC_FOUND) {
            redirectUrl = response.getFirstHeader("Location").getValue();
        } else if(response.getStatusLine().getStatusCode() == HttpServletResponse.SC_OK) {
            HttpEntity entity = response.getEntity();
            redirectUrl = EntityUtils.toString(entity);
        }

        return redirectUrl;
    }

    public static String postString(String content, String url) throws Exception {
        HttpPost post = new HttpPost(url);

        RequestConfig requestConfig =
                RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000).build();
        post.setConfig(requestConfig);

        post.addHeader("Charset", StandardCharsets.UTF_8.name());
        post.addHeader("Content-Type", MediaType.TEXT_XML_VALUE);
        post.setEntity(new StringEntity(content, StandardCharsets.UTF_8.name()));

        CloseableHttpResponse response = httpclient.execute(post);
        String redirectUrl = null;
        if(response.getStatusLine().getStatusCode() == HttpServletResponse.SC_FOUND) {
            redirectUrl = response.getFirstHeader("Location").getValue();
        } else if(response.getStatusLine().getStatusCode() == HttpServletResponse.SC_OK) {
            HttpEntity entity = response.getEntity();
            redirectUrl = EntityUtils.toString(entity, StandardCharsets.UTF_8.name());
        }

        return redirectUrl;
    }

    public static String testGet(String url) throws Exception {
        HttpGet post = new HttpGet(url);
        RequestConfig requestConfig =
                RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000).build();
        post.setConfig(requestConfig);

        CloseableHttpResponse response = httpclient.execute(post);
        HttpEntity entity = response.getEntity();

        String str = EntityUtils.toString(entity);
        return str;
    }

    public static String write(String content, String reqUrl) {
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

            conn.connect();
            if(content != null) {
                OutputStream out = conn.getOutputStream();
                out.write(content.getBytes(StandardCharsets.UTF_8.name()));
            }
            int code = conn.getResponseCode();

            InputStream in = conn.getInputStream();
            byte[] data = new byte[1024];
            StringBuffer resp = new StringBuffer("");
            while(in.read(data) != -1) {
                resp.append(new String(data, StandardCharsets.UTF_8.name()).trim());
            }

            in.close();
            conn.disconnect();
            return resp.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream writeForm(Map<String, Object> data, String reqUrl) {
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(300000);
            conn.setReadTimeout(600000);
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.connect();
            if(data != null) {
                StringBuffer buffer = new StringBuffer("");
                Set<String> set = data.keySet();
                for(String key : set) {
                    buffer.append(key).append("=").append(data.get(key)).append("&");
                }
                String content = buffer.toString().substring(0, buffer.length() - 1);
                OutputStream out = conn.getOutputStream();
                out.write(content.getBytes(StandardCharsets.UTF_8.name()));
            }
            int code = conn.getResponseCode();

            InputStream in = conn.getInputStream();
            return in;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CloseableHttpClient createSSLClientDefault() {
        return HttpClients.custom()
                .setKeepAliveStrategy(myStrategy)
                .setMaxConnPerRoute(200)
                .setMaxConnTotal(1000)
                .build();
    }
}
