package com.hnsle.webaaa;

import com.hnsle.webaaa.model.AreaModel;
import com.hnsle.webaaa.model.DetailedModel;
import com.hnsle.webaaa.model.TourModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public final static String DATAURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService";
    public final static String KEY = "HXSISBGUnRn3uiRD8c1Io3lZ4DV9khvx3k1GDjWu3B%2BhtcD%2Fr4dOhMz2aNGarI1Jns%2F04PKHg8XN7Wu1fJLEiQ%3D%3D";

    public Parser() {
        try {
            apiParserSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void apiParserSearch() throws Exception {

       // System.out.println(getURLParam(null, null));

    }

    private void printList(ArrayList<String> list) {
        for(String entity: list) {
            System.out.println(entity);
        }
    }

    ////////관광정보 요청
    private String getURLParam(int areaCode, int sigunguCode) throws Exception {
        if (areaCode==0) {
            areaCode = 1;
        }
        if (sigunguCode==0) {
            sigunguCode=1;
        }
        ///////////////////////////////////////////////////////////
        StringBuilder urlBuilder = new StringBuilder(DATAURL+"/areaBasedList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("인증키 (URL- Encode)", "UTF-8")); /*공공데이터포털에서 발급받은 인증키*/ urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS (아이폰), AND (안드로이드),WIN (원도우폰), ETC*/
        urlBuilder.append("&" + URLEncoder.encode("arrange","UTF-8") + "=" + URLEncoder.encode("A", "UTF-8")); /*(A=제목순, B=조회순, C=수정일순, D=생성일순) , 대표이미지가 반드시 있는 정렬 (O=제목순, P=조회순, Q=수정일순, R=생성일순)*/
        urlBuilder.append("&" + URLEncoder.encode("cat1","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*대분류 코드*/
        urlBuilder.append("&" + URLEncoder.encode("contentTypeId","UTF-8") + "=" + URLEncoder.encode("12", "UTF-8")); /*관광타입(관광지, 숙박 등) ID*/
        urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode(Integer.toString(areaCode), "UTF-8")); /*지역코드*/
        urlBuilder.append("&" + URLEncoder.encode("sigunguCode","UTF-8") + "=" + URLEncoder.encode(Integer.toString(sigunguCode), "UTF-8")); /*시군구코드(areaCode 필수)*/
        urlBuilder.append("&" + URLEncoder.encode("listYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*목록 구분 (Y=목록, N=개수)*/
        ///////////////////////////////////////////////
        URL url = new URL(urlBuilder.toString()+"&_type=json");
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

        return sb.toString();
    }

    public List<TourModel> parseAreaBasedList(int areaCode, int sigunguCode) throws Exception {
        String str = getURLParam(areaCode, sigunguCode);
        List<TourModel> tmList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(str);
        JSONObject data1 = (JSONObject)jsonObject.get("response");
        JSONObject data2 = (JSONObject)data1.get("body");

        if(data2.getInt("totalCount") == 0) {
        }
        else {
            JSONObject data3 = (JSONObject) data2.get("items");

            //array로 넘어 올 경우
            if (data2.getInt("totalCount") > 1) {
                JSONArray data4 = (JSONArray) data3.get("item");

                for (Object data : data4) {
                    TourModel tm = new TourModel();
                    JSONObject dataTmp = (JSONObject) data;

                    if (dataTmp.has("addr1")) tm.setAddr1(dataTmp.getString("addr1"));
                    //if (dataTmp.has("addr2")) tm.setAddr2(dataTmp.getString("addr2"));
                    if (dataTmp.has("contentid")) tm.setContentId(dataTmp.getLong("contentid"));
                    if (dataTmp.has("contenttypeid")) tm.setContentTypeId(dataTmp.getInt("contenttypeid"));
                    if (dataTmp.has("firstimage")) tm.setImg1(dataTmp.getString("firstimage"));
                    if (dataTmp.has("firstimage2")) tm.setImg2(dataTmp.getString("firstimage2"));
                    if (dataTmp.has("mapx")) tm.setMapX(dataTmp.getDouble("mapx"));
                    if (dataTmp.has("mapy")) tm.setMapY(dataTmp.getDouble("mapy"));
                    if (dataTmp.has("tel")) tm.setTel(dataTmp.getString("tel"));
                    if (dataTmp.has("title")) tm.setTitle(dataTmp.getString("title"));
                    tmList.add(tm);
                }
            } else {
                JSONObject data4 = (JSONObject) data3.get("item");
                TourModel tm = new TourModel();
                JSONObject dataTmp = (JSONObject) data4;

                if (dataTmp.has("addr1")) tm.setAddr1(dataTmp.getString("addr1"));
                if (dataTmp.has("addr2")) tm.setAddr2(dataTmp.getString("addr2"));
                if (dataTmp.has("contentid")) tm.setContentId(dataTmp.getLong("contentid"));
                if (dataTmp.has("contenttypeid")) tm.setContentTypeId(dataTmp.getInt("contenttypeid"));
                if (dataTmp.has("firstimage")) tm.setImg1(dataTmp.getString("firstimage"));
                if (dataTmp.has("firstimage2")) tm.setImg2(dataTmp.getString("firstimage2"));
                if (dataTmp.has("mapx")) tm.setMapX(dataTmp.getDouble("mapx"));
                if (dataTmp.has("mapy")) tm.setMapY(dataTmp.getDouble("mapy"));
                if (dataTmp.has("tel")) tm.setTel(dataTmp.getString("tel"));
                if (dataTmp.has("title")) tm.setTitle(dataTmp.getString("title"));
                tmList.add(tm);
           }
        }
            return tmList;

    }

    ///////지역코드 요청
    public AreaModel getAreaListURL(int areaCode) throws Exception {
        ///////////////////////////////////////////////////////////
        StringBuilder urlBuilder = new StringBuilder(DATAURL+"/areaCode"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("인증키 (URL - Encode)", "UTF-8")); /*공공데이터포털에서 발급받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS (아이폰), AND (안드로이드), WIN (원도우폰), ETC*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
        urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode(Integer.toString(areaCode), "UTF-8")); /*지역코드, 시군구코드*/
        ///////////////////////////////////////////////
        //System.out.println(String.valueOf(areaCode));
        URL url = new URL(urlBuilder.toString()+"&_type=json");
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


        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONObject data1 = (JSONObject)jsonObject.get("response");
        JSONObject data2 = (JSONObject)data1.get("body");
        JSONObject data3 = (JSONObject)data2.get("items");
        JSONArray data4 = (JSONArray) data3.get("item");
        int totalCount = (int)data2.get("totalCount");
        System.out.println("totalCount: " + data2.get("totalCount"));
        List<String> result = new ArrayList<>();
        List<String> code = new ArrayList<>();
        for (Object data: data4) {
            System.out.println("name :" + ((JSONObject)data).get("name"));
            code.add(((JSONObject)data).get("code").toString());
            result.add(((JSONObject)data).get("name").toString());
        }
        rd.close();
        conn.disconnect();


        AreaModel am = new AreaModel(code,totalCount,result);
        return am;
    }


    //무장애 정보 요청
    public String getDetailedService(int contentId) throws Exception {
        String D4URL = "http://api.visitkorea.or.kr/openapi/service/rest/KorWithService/detailWithTour";
        StringBuilder urlBuilder = new StringBuilder(D4URL); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("인증키 (URL- Encode)", "UTF-8")); /*공공데이터포털에서 발급받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰),AND(안드로이드),WIN(원도우폰),ETC*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
        urlBuilder.append("&" + URLEncoder.encode("contentId","UTF-8") + "=" + URLEncoder.encode(Integer.toString(contentId), "UTF-8")); /*콘텐츠 ID*/
        //urlBuilder.append("&" + URLEncoder.encode("contentTypeId","UTF-8") + "=" + URLEncoder.encode("12", "UTF-8")); /*관광타입(관광지, 숙박 등) ID*/
        urlBuilder.append("&" + URLEncoder.encode("withYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*무장애정보 조회 N=Null*/
        /////////////////////////////////////////////
        URL url = new URL(urlBuilder.toString()+"&_type=json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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

    public DetailedModel parsedDetailedService(int id) throws Exception {
        String str = getDetailedService(id);
        DetailedModel dm = new DetailedModel();

        JSONObject jsonObject = new JSONObject(str);
        JSONObject data1 = (JSONObject)jsonObject.get("response");
        JSONObject data2 = (JSONObject)data1.get("body");

        if(data2.getInt("totalCount") == 0) {
        }
        else {
            JSONObject data3 = (JSONObject) data2.get("items");

            JSONObject data4 = (JSONObject) data3.get("item");
            JSONObject dt = (JSONObject) data4;

            if(dt.has("exit")) dm.setExit(dt.getString("exit"));
            if(dt.has("helpdog")) dm.setHelpdog(dt.getString("helpdog"));
            if(dt.has("parking")) dm.setParking(dt.getString("parking"));
            if(dt.has("publictransport")) dm.setPublictransport(dt.getString("publictransport"));
            if(dt.has("restroom")) dm.setRestroom(dt.getString("restroom"));
            if(dt.has("brailblock")) dm.setBrailblock(dt.getString("brailblock"));
            if(dt.has("elevator")) dm.setElevator(dt.getString("elevator"));
            if(dt.has("wheelchair")) dm.setWheelchair(dt.getString("wheelchair"));
/*    private String publictransport; //접근로(경사로?)
    private String restroom; //장애인 화장실
    private String wheelchair;  //휠체어
    private String exit;    //출입통로
    private String elevator;    //엘리베이터
    private String braileblok;  //점자블록
    private String helpdog; //안내견동반
    private String parking; //주차장*/
        }
        return dm;
    }



}

