package hhjson;

import java.util.ArrayList;
import java.util.HashMap;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
public class JsonTest2 {
 
    public static void main(String[] args) {
        
        HashMap<String, Object> myHashMap1 = new HashMap<String, Object>();
        JSONObject jsonObject1 = new JSONObject(); // 중괄호에 들어갈 속성 정의 { "a" : "1", "b" : "2" }
        JSONArray jsonArray1 = new JSONArray(); // 대괄호 정의 [{ "a" : "1", "b" : "2" }]
        JSONObject finalJsonObject1 = new JSONObject(); // 중괄호로 감싸 대괄호의 이름을 정의함 { "c" : [{  "a" : "1", "b" : "2" }] }
        
        myHashMap1.put("이름", "잠자리");
        myHashMap1.put("다리갯수", "6");
        
        jsonObject1 = new JSONObject(myHashMap1); 
        jsonArray1.add(jsonObject1);
        
        myHashMap1 = new HashMap<String, Object>();
        myHashMap1.put("이름", "사슴벌레");
        myHashMap1.put("다리갯수", "6");
        
        jsonObject1 = new JSONObject(myHashMap1);
        jsonArray1.add(jsonObject1);
        
        finalJsonObject1.put("곤충", jsonArray1);
        finalJsonObject1.put("동물", "코끼리");
        finalJsonObject1.put("식물", "무궁화");
        
        System.out.println(finalJsonObject1);
        
        /*
         {    
               "곤충":[  
                          {  
                             "이름":"잠자리",
                             "다리갯수":"6"
                          },
                          {  
                             "이름":"사슴벌레",
                             "다리갯수":"6"
                          }
                       ],
               "동물":"코끼리",
               "식물":"무궁화"
          }
         */
 
    }
}
