package com.lovemylunch.common.beans;

import com.lovemylunch.common.http.InternalServiceCallTrustManager;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.BasicClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.log4j.Logger;

public class RestServiceCallClient extends DefaultHttpClient {
    private static final Logger LOGGER = Logger.getLogger(RestServiceCallClient.class);
    public static final int REQUEST_TIMEOUT = 900000;
    public static final int WAIT_TIMEOUT = 900000;
    public static final int BUFFER_SIZE = 8192;
    private String hostIp;

    public RestServiceCallClient() {
        this.init();
    }

    private void init() {
        try {
            this.hostIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException var2) {
            LOGGER.error("!!! [Important] Failed to get host IP", var2);
        }

        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("*** HOST IP IS: " + this.hostIp);
        }

        BasicHttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 900000);
        HttpConnectionParams.setSoTimeout(httpParameters, 900000);
        HttpConnectionParams.setSocketBufferSize(httpParameters, 8192);
        this.setParams(httpParameters);
    }

    public String getHostIp() {
        return this.hostIp;
    }

    protected ClientConnectionManager createClientConnectionManager() {
        Scheme httpScheme = new Scheme("http", 80, PlainSocketFactory.getSocketFactory());
        SSLSocketFactory sslFactory = this.createSSLFactory();
        Scheme httpsScheme = new Scheme("https", 443, sslFactory);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(httpScheme);
        schemeRegistry.register(httpsScheme);
        return new BasicClientConnectionManager(schemeRegistry);
    }

    private final SSLSocketFactory createSSLFactory() {
        SSLContext sslContext = null;

        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init((KeyManager[])null, new TrustManager[]{InternalServiceCallTrustManager.getInstance()}, new SecureRandom());
        } catch (Exception var3) {
            throw new IllegalArgumentException("Cannot create SSL connection!", var3);
        }

        SSLSocketFactory factory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return factory;
    }
}
