package koo.travel.travel.controller;

import koo.travel.travel.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * https://www.data.go.kr/iim/api/selectAPIAcountView.do (openApi를 이용할 restApi 구현, 이 데이터(JSON 형식의 데이터가 한줄의 긴 문자열로 들어온다)들을 사용하려면 가공을 해야하기 때문에 JSON-parsing을 해야한다. 나는 간편한 json-simple이란 라이브러리를 사용할 예정)
 * Controller에서 데이터를 받아오고, 그 데이터를 String으로 서비스에 넘겨 서비스에서 데이터베이스에 삽입하도록 구현
 **/
@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final RestService restService;

    @Autowired
    public RestController(RestService restService) {
        this.restService = restService;
    }

    @GetMapping("/api")
    public String callApi() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/DataLabService/tmapTotalTarItsBroDDList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=KQAJl0G39I5j1EBQo5PG71RH4tfEOJNqpL%2BIYXBsUd8muZGu%2BAw4C9vDXPGRM54z0pjUNvxn9sKUWaTyPJPDSA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS (아이폰), AND (안드로이드), WIN (윈도우폰), ETC*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
        urlBuilder.append("&" + URLEncoder.encode("baseYm","UTF-8") + "=" + URLEncoder.encode("202106", "UTF-8")); /*시작연월(yyyyMM)*/
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*반환타입*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

        return sb.toString();
    }
}
        /*
        StringBuilder result = new StringBuilder();

        String urlStr = "http://api.visitkorea.or.kr/openapi/service/rest/DataLabService/tmapTotalTarItsBroDDList?" +
                URLEncoder.encode("ServiceKey", "UTF-8") + "=KQAJl0G39I5j1EBQo5PG71RH4tfEOJNqpL%2BIYXBsUd8muZGu%2BAw4C9vDXPGRM54z0pjUNvxn9sKUWaTyPJPDSA%3D%3D" +
                "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") +
                "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("20", "UTF-8") +
                "&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8") +
                "&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8") +
                "&" + URLEncoder.encode("baseYm", "UTF-8") + "=" + URLEncoder.encode("202106", "UTF-8") +
                "&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8");

        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

        String returnLine;

        while((returnLine = br.readLine()) != null) {
            result.append(returnLine+"\n\r");
        }

        urlConnection.disconnect();

        return result.toString();
    */
