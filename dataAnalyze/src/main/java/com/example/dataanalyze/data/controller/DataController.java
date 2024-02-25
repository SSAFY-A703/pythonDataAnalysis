package com.example.dataanalyze.data.controller;

import com.example.dataanalyze.data.service.DataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataAnalysisService dataAnalysisService;

    @PostMapping("/visualization")
    public ResponseEntity<String> requestVisualization(@RequestBody String csvData) {
        // 파이썬 백엔드에 CSV 데이터 전송
        String result = dataAnalysisService.analyzeData(csvData);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}