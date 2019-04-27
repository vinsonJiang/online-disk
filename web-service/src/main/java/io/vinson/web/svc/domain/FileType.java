package io.vinson.web.svc.domain;

/**
 * 文件类型
 * @author: jiangweixin
 * @date: 2019/4/27
 */
public class FileType {
    private int id;

    private String type;

    private String suffix;

    private String icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
