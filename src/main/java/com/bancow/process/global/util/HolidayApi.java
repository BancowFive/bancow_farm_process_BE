package com.bancow.process.global.util;

import com.bancow.process.domain.model.DateType;
import com.bancow.process.global.response.RequestDateResponseDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HolidayApi {

    public static List<RequestDateResponseDto> getHoliday(LocalDate startDate, LocalDate endDate) throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=UaOu9X%2B3prfBISxCzEp3TAd0Q7rtqIHPAGC253MBw3AmJBqJfQuwU%2F%2BpZZyHR%2BaW%2FAQ%2BHvPXeAjs5W4ZSVVffA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("62", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("solYear", "UTF-8") + "=" + URLEncoder.encode("2022", "UTF-8")); /*연*/
//        urlBuilder.append("&" + URLEncoder.encode("solMonth", "UTF-8") + "=" + URLEncoder.encode("02", "UTF-8")); /*월*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8")+ "=" + URLEncoder.encode("json"));

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

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
        JSONObject parseResponse = (JSONObject) jsonObject.get("response");
        JSONObject parseBody = (JSONObject) parseResponse.get("body");
        JSONObject parseItems = (JSONObject) parseBody.get("items");
        JSONArray parseItem = (JSONArray) parseItems.get("item");

        JSONObject item;
        List holidayList = new ArrayList<>();
        for (int i = 0; i < parseItem.size() ; i++) {
            item = (JSONObject)parseItem.get(i);
            Long locdate = (Long) item.get("locdate");
            String dateName =(String) item.get("dateName");
            LocalDate date = LocalDate.parse(String.valueOf(locdate), DateTimeFormatter.ofPattern("yyyyMMdd"));

            if (date.isAfter(startDate) && date.isBefore(endDate.plusDays(1))) {
                RequestDateResponseDto requestDateResponseDto
                        = new RequestDateResponseDto(dateName, date, DateType.HOLIDAY);
                holidayList.add(requestDateResponseDto);
            }
        }

        return holidayList;
    }
}
