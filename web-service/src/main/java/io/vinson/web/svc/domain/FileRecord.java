package io.vinson.web.svc.domain;

import java.util.Date;

/**
 * 文件记录
 * @author: jiangweixin
 * @date: 2019/4/27
 */
public class FileRecord {

    private int id;

    private String md5;

    private long size;

    private int fileSystemId;

    private String filePath;

    private Date createTime;

    private Date lastUpdateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getFileSystemId() {
        return fileSystemId;
    }

    public void setFileSystemId(int fileSystemId) {
        this.fileSystemId = fileSystemId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
