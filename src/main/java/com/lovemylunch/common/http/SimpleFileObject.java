package com.lovemylunch.common.http;

import java.io.File;
import org.apache.http.entity.mime.content.FileBody;

public class SimpleFileObject extends FileBody {
    public SimpleFileObject(File file) {
        this(file.getName(), file, "application/octet-stream", "UTF-8");
    }

    public SimpleFileObject(File file, String mimeType) {
        this(file.getName(), file, mimeType, "UTF-8");
    }

    public SimpleFileObject(File file, String mimeType, String charset) {
        this(file.getName(), file, mimeType, charset);
    }

    public SimpleFileObject(String fileFieldName, File file, String mimeType, String charset) {
        super(file, fileFieldName, mimeType, charset);
    }
}