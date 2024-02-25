package com.example.dataanalyze.data.service;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataAnalysisServiceImpl implements DataAnalysisService{

    private final String pythonServerUrl = "http://localhost:5000/analyze";
    public String analyzeData(String csvData) {
        // 여기서 파이썬 백엔드와 통신하고 결과를 받아오는 로직을 구현
        // HTTP 요청을 통해 파이썬 백엔드에 CSV 데이터 전송

        // 실제로는 RestTemplate 대신 WebClient 또는 Feign 등을 사용할 수 있음
        String result = communicateWithPythonBackend(csvData);

        // 결과를 가공하거나 추가 처리
        String finalResult = processResult(result);

        return finalResult;
    }

    private String communicateWithPythonBackend(String csvData) {
        // HTTP 요청을 위한 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HTTP 요청을 위한 엔터티 설정
        HttpEntity<String> requestEntity = new HttpEntity<>(csvData, headers);

        // RestTemplate을 사용하여 HTTP POST 요청 보내기
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(pythonServerUrl, requestEntity, String.class);

        return result;
    }

    private String processResult(String result) {
        // 결과 처리 로직 구현
        // 가상의 코드로 대체
        return "Processed Result: " + result;
    }
}
