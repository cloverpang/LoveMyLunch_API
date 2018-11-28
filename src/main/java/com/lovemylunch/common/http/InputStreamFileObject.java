package com.lovemylunch.common.http;

import java.io.InputStream;
import org.apache.http.entity.mime.content.InputStreamBody;

public class InputStreamFileObject extends InputStreamBody {
    private long contentLength;

    public InputStreamFileObject(InputStream in, long contentLength, String filename) {
        super(in, filename);
        this.contentLength = contentLength;
    }

    public InputStreamFileObject(InputStream in, String mimeType, long contentLength, String filename) {
        super(in, mimeType, filename);
        this.contentLength = contentLength;
    }

    public long getContentLength() {
        return this.contentLength;
    }
}
