package com.lovemylunch.common.beans;

import com.lovemylunch.common.beans.annotation.NotDbField;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public abstract class ConflictSupport implements Serializable, Cloneable {
    private static final long serialVersionUID = 8488640761608755309L;
    protected Date createTime;
    protected Date updateTime;
    protected String createBy;
    protected String updateBy;
    @NotDbField
    protected Date accessTime;
    @NotDbField
    protected String table;
    @NotDbField
    protected String keyName;
    @NotDbField
    protected String keyValue;
    @NotDbField
    protected Map<String, Object> params;
    @NotDbField
    protected long recordIndex;

    public ConflictSupport() {
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getAccessTime() {
        return this.accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public abstract String getTable();

    public abstract String getKeyName();

    public abstract String getKeyValue();

    public Map<String, Object> getParams() {
        return this.params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public long getRecordIndex() {
        return this.recordIndex;
    }

    public void setRecordIndex(long recordIndex) {
        this.recordIndex = recordIndex;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
