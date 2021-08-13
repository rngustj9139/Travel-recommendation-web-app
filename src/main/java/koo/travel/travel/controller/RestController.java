package koo.travel.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * https://www.data.go.kr/iim/api/selectAPIAcountView.do (openApi를 이용할 restApi 구현, 반환값이 json 형태임)
 * **/
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("/api")
    public String callApi() throws IOException {
        StringBuilder result = new StringBuilder();

        String urlStr = "http://api.visitkorea.or.kr/openapi/service/rest/DataLabService?" +
                "serviceKey = KQAJl0G39I5j1EBQo5PG71RH4tfEOJNqpL%2BIYXBsUd8muZGu%2BAw4C9vDXPGRM54z0pjUNvxn9sKUWaTyPJPDSA%3D%3D" +
                "pageNo = 0" +
                "&numOfRows = 20" +
                "&type = json";
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
    }

}
