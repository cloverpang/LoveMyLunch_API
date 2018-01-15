package com.lovemylunch.common.http;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public class InternalServiceCallTrustManager implements X509TrustManager {
    private static final InternalServiceCallTrustManager INSTANCE = new InternalServiceCallTrustManager();

    private InternalServiceCallTrustManager() {
    }

    public static final InternalServiceCallTrustManager getInstance() {
        return INSTANCE;
    }

    public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
