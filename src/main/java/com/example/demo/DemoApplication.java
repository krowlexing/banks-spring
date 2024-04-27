package com.example.demo;

import com.example.demo.parser.BankXmlParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.parsers.SAXParserFactory;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(DemoApplication.class, args);
		} catch (Throwable t) {
			System.out.println(t.getMessage());
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
