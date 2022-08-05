package hhjson;

import java.util.ArrayList;
import java.util.HashMap;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
public class JsonTest {
 
    public static void main(String[] args) {
        
        JSONObject jsonObject1 = new JSONObject(); // �߰�ȣ�� �� �Ӽ� ���� { "a" : "1", "b" : "2" }
        JSONArray jsonArray1 = new JSONArray(); // ���ȣ ���� [{ "a" : "1", "b" : "2" }]
        JSONObject finalJsonObject1 = new JSONObject(); // �߰�ȣ�� ���� ���ȣ�� �̸��� ������ { "c" : [{  "a" : "1", "b" : "2" }] }
 
        jsonObject1.put("�̸�", "���ڸ�");
        jsonObject1.put("�ٸ�����", "6");
        jsonArray1.add(jsonObject1);
 
        jsonObject1 = new JSONObject();
        jsonObject1.put("�̸�", "�罿����");
        jsonObject1.put("�ٸ�����", "6");
        jsonArray1.add(jsonObject1);
        
        finalJsonObject1.put("����", jsonArray1);
 
        finalJsonObject1.put("����", "�ڳ���");
        finalJsonObject1.put("�Ĺ�", "����ȭ");
        
        System.out.println(finalJsonObject1);
 
        /* finalJsonObject1 ��°�          
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
