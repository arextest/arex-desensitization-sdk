package com.arextest.diff.service.impl;

import com.arextest.diff.service.DecompressService;
import com.github.luben.zstd.ZstdInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZstdDecompressImpl implements DecompressService {

    private static Logger log = LoggerFactory.getLogger(ZstdDecompressImpl.class.getName());

    public ZstdDecompressImpl() {
    }

    public String getAliasName() {
        return "ZSTD";
    }

    public String decompress(String str, String args) {
        if (str == null || str.isEmpty()) {
            return null;
        }

        try {
            return this.uncompressString(str);
        } catch (Exception e) {
            log.warn("ZstdDeCompress decompress error, message: {}", str, e);
            return str;
        }
    }

    public String uncompressString(String value) throws Exception {
        return this.uncompressString(Base64.getDecoder().decode(value));
    }

    public String uncompressString(byte[] bytes) throws Exception {
        if (bytes != null && bytes.length != 0) {
            byte[] buf = this.uncompress(bytes);
            return new String(buf);
        } else {
            return null;
        }
    }

    public byte[] uncompress(byte[] bytes) throws Exception {
        if (bytes == null) {
            return new byte[0];
        } else {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            InputStream inputStream = new ZstdInputStream(byteArrayInputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(bytes.length * 2);

            while (inputStream.available() > 0) {
                outputStream.write(inputStream.read());
            }

            byte[] buf = outputStream.toByteArray();
            if (buf.length == 0) {
                return buf;
            }

            return buf;
        }
    }

}