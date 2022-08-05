package hhmqtt;

import java.util.HashMap;
import java.util.function.Consumer;

public class TestMqttMainMethod {
    
    
    public static void main(String [] args){
        
        final Consumer<HashMap<Object, Object>> pdk = (arg)->{  //�޽����� �޴� �ݹ� ����
            arg.forEach((key, value)->{
                System.out.println( String.format("�޽��� ���� : Ű -> %s, �� -> %s", key, value) );
            });            
        };

        MyMqttClient client = new MyMqttClient(pdk);  //�ش� �Լ��� �����ڷ� �־��ش�.

        /*client.init("���Ӿ��̵�", "��й�ȣ", "tcp://�ּ�:��Ʈ��ȣ", "���̵�")
              .subscribe(new String[]{"����1","����2"}); */ //subscribe �޼ҵ�� ������ ���
        /*client.init("hhmqtt", "1234", "tcp://sean715.iptime.org:1883", "hhchoi")
        	.subscribe(new String[]{"AiCMS/Camera_1","AiCMS/test"});  */ //subscribe �޼ҵ�� ������ ���
        client.init("xxxx", "yyyy", "tcp://hklab.hknu.ac.kr:8085", "zzzz")
    		.subscribe(new String[]{"hhtest", "aaa/bbb"});  // subscribe �޼ҵ�� ������ ��� (������ ������ ���� �� ����)

        client.initConnectionLost( (arg)->{  //�ݹ�����1, �������� ������ ����� ����
            arg.forEach((key, value)->{
                System.out.println( String.format("Ŀ�ؼ� ����~! Ű -> %s, �� -> %s", key, value) );
            });
        });

        client.initDeliveryComplete((arg)-> {  //�ݹ�����2, �޽����� ������ ���� ����
            arg.forEach((key, value)->{
                System.out.println( String.format("�޽��� ���� �Ϸ�~! Ű -> %s, �� -> %s", key, value) );
            });
        });


        new Thread( ()->{
            try {
                Thread.sleep(9000);		// 9�� �ڿ� �ѹ� ���� 
                client.sender("hhtest", "Hello world! �ѱ��ѱ�!");  // (����, �����޽���) �̷������� ������.
                client.close();  //����� �̷���!
            } catch (Exception e) {
                e.printStackTrace();
            }
        } ).start();        
        
    }
}