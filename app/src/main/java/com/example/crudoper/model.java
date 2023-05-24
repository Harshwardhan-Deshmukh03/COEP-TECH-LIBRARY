package com.example.crudoper;

public class model {
    String avaName,avaNo,avaTag;

    public model() {
    }

    public model(String avaName, String avaNo, String avaTag) {
        this.avaName = avaName;
        this.avaNo = avaNo;
        this.avaTag = avaTag;
    }

    public String getAvaName() {
        return avaName;
    }

    public void setAvaName(String avaName) {
        this.avaName = avaName;
    }

    public String getAvaNo() {
        return avaNo;
    }

    public void setAvaNo(String avaNo) {
        this.avaNo = avaNo;
    }

    public String getAvaTag() {
        return avaTag;
    }

    public void setAvaTag(String avaTag) {
        this.avaTag = avaTag;
    }
}
