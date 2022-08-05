package hhmqtt;

import java.util.HashMap;
import java.util.function.Consumer;

public class TestMqttMainMethod {
    
    
    public static void main(String [] args){
        
        final Consumer<HashMap<Object, Object>> pdk = (arg)->{  //메시지를 받는 콜백 행위
            arg.forEach((key, value)->{
                System.out.println( String.format("메시지 도착 : 키 -> %s, 값 -> %s", key, value) );
            });            
        };

        MyMqttClient client = new MyMqttClient(pdk);  //해당 함수를 생성자로 넣어준다.

        /*client.init("접속아이디", "비밀번호", "tcp://주소:포트번호", "아이디")
              .subscribe(new String[]{"주제1","주제2"}); */ //subscribe 메소드는 구독할 대상
        /*client.init("hhmqtt", "1234", "tcp://sean715.iptime.org:1883", "hhchoi")
        	.subscribe(new String[]{"AiCMS/Camera_1","AiCMS/test"});  */ //subscribe 메소드는 구독할 대상
        client.init("xxxx", "yyyy", "tcp://hklab.hknu.ac.kr:8085", "zzzz")
    		.subscribe(new String[]{"hhtest", "aaa/bbb"});  // subscribe 메소드는 구독할 대상 (토픽을 여러개 적을 수 있음)

        client.initConnectionLost( (arg)->{  //콜백행위1, 서버와의 연결이 끊기면 동작
            arg.forEach((key, value)->{
                System.out.println( String.format("커넥션 끊김~! 키 -> %s, 값 -> %s", key, value) );
            });
        });

        client.initDeliveryComplete((arg)-> {  //콜백행위2, 메시지를 전송한 이후 동작
            arg.forEach((key, value)->{
                System.out.println( String.format("메시지 전달 완료~! 키 -> %s, 값 -> %s", key, value) );
            });
        });


        new Thread( ()->{
            try {
                Thread.sleep(9000);		// 9초 뒤에 한번 전송 
                client.sender("hhtest", "Hello world! 한글한글!");  // (토픽, 보낼메시지) 이런식으로 보낸다.
                client.close();  //종료는 이렇게!
            } catch (Exception e) {
                e.printStackTrace();
            }
        } ).start();        
        
    }
}