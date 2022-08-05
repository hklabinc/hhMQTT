package hhjson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public class StringToJson {
 
    public static void main(String[] args) {
        String jsonString1 = "{\"식물\":\"무궁화\",\"곤충\":[{\"이름\":\"잠자리\",\"다리갯수\":\"6\"},{\"이름\":\"사슴벌레\",\"다리갯수\":\"6\"}],\"동물\":\"코끼리\"}";
        JSONParser jsonParser1 = new JSONParser();
        JSONObject jsonObject1;
 
        try {
            
            jsonObject1 = (JSONObject) jsonParser1.parse(jsonString1);
            JSONArray jsonArray1 = (JSONArray) jsonObject1.get("곤충");
      
            for(int i=0; i<jsonArray1.size(); i++){
                System.out.println("곤충"+ i +" : " +jsonArray1.get(i));            
                JSONObject objectInArray = (JSONObject) jsonArray1.get(i);
                System.out.println("Key값은 "+objectInArray.get("이름"));
                System.out.println("Value값은 "+objectInArray.get("다리갯수"));
            }
            /*
             
                곤충0 : {"이름":"잠자리","다리갯수":"6"}
                Key값은 잠자리
                Value값은 6
                
                곤충1 : {"이름":"사슴벌레","다리갯수":"6"}
                Key값은 사슴벌레
                Value값은 6
             
             */
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

