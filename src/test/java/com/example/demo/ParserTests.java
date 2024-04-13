package com.example.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.codec.json.Jackson2JsonEncoder;

import javax.xml.parsers.SAXParserFactory;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ParserTests {

    File findXmlFile() {
        var systemProperty = System.getProperty("user.dir");
        return new File(systemProperty + "/xml/2023.xml");
    }

    @Test
    public void testOpenFile() {
        assertTrue(findXmlFile().exists());
    }

    @Test
    public void parserParsesSuccessfully() {
        assertDoesNotThrow(() -> {
            var xml = findXmlFile();
            var sax = SAXParserFactory.newInstance().newSAXParser();
            var handler = new BankXmlParser();
            AtomicInteger count = new AtomicInteger();
            var ref = new Object() {
                int c = 0;
            };
            AtomicBoolean first = new AtomicBoolean(true);
            handler.onEntryParsed = entry -> {
                if (first.get()) {
                    assertDoesNotThrow(() -> {
                        var json = new ObjectMapper().writeValueAsString(entry);
                        assertEquals("", json);
                    });

                    first.set(false);
                }
                count.getAndIncrement();
                ref.c += 1;
            };
            sax.parse(xml, handler);
            assertEquals(ref.c, 1627);
            assertNotEquals(0, ref.c);
        });
    }
}
