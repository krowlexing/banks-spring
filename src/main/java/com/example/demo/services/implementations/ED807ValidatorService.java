package com.example.demo.services.implementations;

import com.example.demo.services.ValidatorService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ED807ValidatorService implements ValidatorService {

    @Override
    public boolean validate(InputStream inputStream) {
        // TODO: file is valid when
        // - its xml file
        // - no unknown attributes
        // - parsing finishes successfully
        return true;
    }
}
