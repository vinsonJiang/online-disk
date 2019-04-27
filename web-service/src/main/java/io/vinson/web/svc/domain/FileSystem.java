package io.vinson.web.svc.domain;

/**
 * 文件系统
 * @author: jiangweixin
 * @date: 2019/4/27
 */
public class FileSystem {

    private int id;

    private String host;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
