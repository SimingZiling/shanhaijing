package org.shanhaijing.example.controller;

import org.shanhaijing.beans.framework.annotation.Autowirted;
import org.shanhaijing.beans.framework.annotation.Controller;
import org.shanhaijing.beans.framework.annotation.RequestMapping;
import org.shanhaijing.beans.framework.annotation.RequestParam;
import org.shanhaijing.example.service.ExampleService;

@Controller
@RequestMapping
public class ExampleController {

    @Autowirted
    private ExampleService exampleService;

    @RequestMapping
    public String example(@RequestParam("name") String name){
        return exampleService.example(name);
    }

}
