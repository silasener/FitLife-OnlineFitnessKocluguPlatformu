package com.example.fitlifeonlinefitnesskocluguplatformu;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class MultipartFileDeserializer extends JsonDeserializer<MultipartFile> {





    @Override
    public MultipartFile deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String dosyaAdi = p.readValueAs(String.class);
       // InputStream is = p.readBinaryValue(InputStream.class);
        // MultipartFile'ı oluşturacak özel işlemleri burada gerçekleştirin
        return null; // Burayı gerçek uygulamanıza uygun bir şekilde doldurun
    }


}
