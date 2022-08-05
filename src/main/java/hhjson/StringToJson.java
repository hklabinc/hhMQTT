package hhjson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public class StringToJson {
 
    public static void main(String[] args) {
        String jsonString1 = "{\"�Ĺ�\":\"����ȭ\",\"����\":[{\"�̸�\":\"���ڸ�\",\"�ٸ�����\":\"6\"},{\"�̸�\":\"�罿����\",\"�ٸ�����\":\"6\"}],\"����\":\"�ڳ���\"}";
        JSONParser jsonParser1 = new JSONParser();
        JSONObject jsonObject1;
 
        try {
            
            jsonObject1 = (JSONObject) jsonParser1.parse(jsonString1);
            JSONArray jsonArray1 = (JSONArray) jsonObject1.get("����");
      
            for(int i=0; i<jsonArray1.size(); i++){
                System.out.println("����"+ i +" : " +jsonArray1.get(i));            
                JSONObject objectInArray = (JSONObject) jsonArray1.get(i);
                System.out.println("Key���� "+objectInArray.get("�̸�"));
                System.out.println("Value���� "+objectInArray.get("�ٸ�����"));
            }
            /*
             
                ����0 : {"�̸�":"���ڸ�","�ٸ�����":"6"}
                Key���� ���ڸ�
                Value���� 6
                
                ����1 : {"�̸�":"�罿����","�ٸ�����":"6"}
                Key���� �罿����
                Value���� 6
             
             */
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

