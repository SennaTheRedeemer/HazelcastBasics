package com.eye.EyeOfTheStorm.controllers;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/hazelcast")
public class TestCacheController {
    private final String testMapName = "testCache";

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @GetMapping("")
    public String greetings() {
        return "<h1>Welcome to Hazelcast Cache controller!</h1> to add go to /add and to get go to /get";
    }

    @GetMapping("/get")
    public Map<Long, String> retrieveAll() {
        return hazelcastInstance.getMap(testMapName);
    }

    @GetMapping("/add")
    public String addCache() {
        for (int index = 0; index < 1000; index++) {
            hazelcastInstance.getMap(testMapName).put(Long.valueOf(index), String.valueOf(index));
        }
        return "success!";
    }
}
