package com.example.demo.services;

import com.example.demo.data.ED807;

import java.io.InputStream;

public interface ParsingService {
    ED807 parse(InputStream inputStream);
}
