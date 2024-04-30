package com.example.demo.services.implementations;

import com.example.demo.data.ED807;
import com.example.demo.parser.BankXmlParser;
import com.example.demo.services.ParsingService;
import org.springframework.stereotype.Service;

import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

@Service
public class SAXParsingService implements ParsingService {

    @Override
    public ED807 parse(InputStream inputStream) {
        var handler = new BankXmlParser();

        try {
            var parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(inputStream, handler);
            return handler.getResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ED807 parsing exception");
        }
    }
}
