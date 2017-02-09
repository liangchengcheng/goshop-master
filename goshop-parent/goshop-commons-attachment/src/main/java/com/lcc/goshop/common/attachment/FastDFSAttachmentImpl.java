package com.lcc.goshop.common.attachment;

/**
 * Created by lcc on 2017/2/9.
 */
public class FastDFSAttachmentImpl {//implements AttachmentService {


    /**
     * FastNFS服务器地址
     */
    private String hostName;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 编码
     */
    private String charset;

    /**
     * 超时时间
     */
    private Integer networkTimeout;


    private String http;

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Integer getNetworkTimeout() {
        return networkTimeout;
    }

    public void setNetworkTimeout(Integer networkTimeout) {
        this.networkTimeout = networkTimeout;
    }

}
