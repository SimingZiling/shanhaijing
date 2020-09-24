package org.shanhaijing.example.service.impl;

import org.shanhaijing.example.service.ExampleService;
import org.shanhaijing.framework.beans.annotation.Service;


@Service
public class ExampleServiceImpl implements ExampleService {
    @Override
    public String example(String parameter) {
        return parameter;
    }
}
