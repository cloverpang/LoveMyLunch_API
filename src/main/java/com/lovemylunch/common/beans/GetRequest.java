package com.lovemylunch.common.beans;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GetRequest implements Serializable {
    private static final long serialVersionUID = -5476427419473997242L;
    private String url;
    private Map<String, String> headers;
    private Map<String, String> params;

    public GetRequest() {
    }

    public GetRequest(String url, Map<String, String> headers, Map<String, String> params) {
        this.url = url;
        this.headers = headers;
        this.params = params;
    }

    public static GetRequest newInstance() {
        return new GetRequest();
    }

    public String getUrl() {
        return this.url;
    }

    public GetRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public GetRequest setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public GetRequest setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public GetRequest setParam(String key, String value) {
        if(this.params == null) {
            this.params = new HashMap();
        }

        this.params.put(key, value);
        return this;
    }

    public GetRequest setHeader(String key, String value) {
        if(this.headers == null) {
            this.headers = new HashMap();
        }

        this.headers.put(key, value);
        return this;
    }

    public String toString() {
        return "GetRequest [url=" + this.url + ", headers=" + this.headers + ", params=" + this.params + "]";
    }
}
