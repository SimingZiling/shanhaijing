package org.shanhaijing.example.service.impl;

import org.shanhaijing.beans.framework.annotation.Service;
import org.shanhaijing.example.service.ExampleService;


@Service
public class ExampleServiceImpl implements ExampleService {
    @Override
    public String example(String parameter) {
        return parameter;
    }
}
