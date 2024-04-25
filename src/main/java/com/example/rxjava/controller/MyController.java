package com.example.rxjava.controller;

import com.example.rxjava.service.MyService;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/data")
    public Observable<String> getData() {
        return myService.fetchData();
    }

//    @GetMapping("/data")
//    public Observable<String[]> getData() {
//        return myService.fetchData();
//    }

}
