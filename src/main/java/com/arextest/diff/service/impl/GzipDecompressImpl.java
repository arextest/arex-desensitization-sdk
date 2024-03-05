package com.arextest.diff.service.impl;

import com.arextest.diff.service.DecompressService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GzipDecompressImpl implements DecompressService {

  private static Logger log = LoggerFactory.getLogger(GzipDecompressImpl.class.getName());

  public GzipDecompressImpl() {
  }

  public String getAliasName() {
    return "Gzip";
  }

  public String decompress(String str, String args) {
    if (str == null || str.isEmpty()) {
      return null;
    }

    if (!isGzip(str)) {
      return str;
    }

    try {
      return this.unCompress(str);
    } catch (Exception e) {
      log.warn("GzipDeCompress decompress error, message: {}", str, e);
      return str;
    }
  }

  public String unCompress(String value) throws Exception {
    byte[] bytes = Base64.getDecoder().decode(value);
    return this.unCompress(bytes, "UTF-8");
  }

  private String unCompress(byte[] bytes, String encoding) throws Exception {
    if (bytes != null && bytes.length != 0) {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ByteArrayInputStream in = new ByteArrayInputStream(bytes);
      GZIPInputStream ungzip = new GZIPInputStream(in);
      byte[] buffer = new byte[256];

      int n;
      while ((n = ungzip.read(buffer)) >= 0) {
        out.write(buffer, 0, n);
      }

      return out.toString(encoding);
    } else {
      return null;
    }
  }

  public static boolean isGzip(String str) {
    return str != null && str.startsWith("H4sIAAAAAAAA");
  }

}