package hhjson;

import java.util.ArrayList;
import java.util.HashMap;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
public class JsonTest2 {
 
    public static void main(String[] args) {
        
        HashMap<String, Object> myHashMap1 = new HashMap<String, Object>();
        JSONObject jsonObject1 = new JSONObject(); // �߰�ȣ�� �� �Ӽ� ���� { "a" : "1", "b" : "2" }
        JSONArray jsonArray1 = new JSONArray(); // ���ȣ ���� [{ "a" : "1", "b" : "2" }]
        JSONObject finalJsonObject1 = new JSONObject(); // �߰�ȣ�� ���� ���ȣ�� �̸��� ������ { "c" : [{  "a" : "1", "b" : "2" }] }
        
        myHashMap1.put("�̸�", "���ڸ�");
        myHashMap1.put("�ٸ�����", "6");
        
        jsonObject1 = new JSONObject(myHashMap1); 
        jsonArray1.add(jsonObject1);
        
        myHashMap1 = new HashMap<String, Object>();
        myHashMap1.put("�̸�", "�罿����");
        myHashMap1.put("�ٸ�����", "6");
        
        jsonObject1 = new JSONObject(myHashMap1);
        jsonArray1.add(jsonObject1);
        
        finalJsonObject1.put("����", jsonArray1);
        finalJsonObject1.put("����", "�ڳ���");
        finalJsonObject1.put("�Ĺ�", "����ȭ");
        
        System.out.println(finalJsonObject1);
        
        /*
         {    
               "����":[  
                          {  
                             "�̸�":"���ڸ�",
                             "�ٸ�����":"6"
                          },
                          {  
                             "�̸�":"�罿����",
                             "�ٸ�����":"6"
                          }
                       ],
               "����":"�ڳ���",
               "�Ĺ�":"����ȭ"
          }
         */
 
    }
}
