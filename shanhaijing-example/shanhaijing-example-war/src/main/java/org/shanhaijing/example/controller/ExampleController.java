package org.shanhaijing.example.controller;

import org.shanhaijing.example.service.ExampleService;
import org.shanhaijing.framework.beans.annotation.Autowirted;
import org.shanhaijing.framework.beans.annotation.Controller;
import org.shanhaijing.framework.beans.annotation.RequestParam;
import org.shanhaijing.framework.request.annotation.RequestMapping;
import org.shanhaijing.framework.request.annotation.RequestMethod;

@Controller
@RequestMapping
public class ExampleController {

    @Autowirted
    private ExampleService exampleService;

    @RequestMapping(method = RequestMethod.GET)
    public String example(@RequestParam("name") String name){
        return exampleService.example(name);
    }

}
