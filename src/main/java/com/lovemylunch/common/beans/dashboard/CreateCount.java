package com.lovemylunch.common.beans.dashboard;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class CreateCount implements Serializable {
    private String createdDate;
    private String createdCount;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedCount() {
        return createdCount;
    }

    public void setCreatedCount(String createdCount) {
        this.createdCount = createdCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
