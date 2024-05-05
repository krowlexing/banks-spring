package com.example.demo;

import com.example.demo.parser.BankXmlParser;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.parsers.SAXParserFactory;

@SpringBootApplication
@Log4j2
public class DemoApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(DemoApplication.class, args);
		} catch (Throwable t) {
			log.info(t.getMessage());
		}
	}

	public static void test() {
		try {
			var parser = SAXParserFactory.newInstance().newSAXParser();
			var saxHandler = new BankXmlParser();
			//parser.parse(File, saxHandler);
		} catch (Exception e) {

		}

	}
}
