package hhmqtt;

import java.util.HashMap;
import java.util.function.Consumer;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class MyMqttClient implements MqttCallback{

    private MqttClient client;
    private MqttConnectOptions option;
    private Consumer<HashMap<Object, Object>> FNC = null;  //�޽��� ���� �� �����ϴ� �Լ�
    private Consumer<HashMap<Object, Object>> FNC2 = null; //Ŀ�ؼ��� ���� �� �����ϴ� �Լ�
    private Consumer<HashMap<Object, Object>> FNC3 = null; //������ �Ϸ�� ���� �����ϴ� �Լ�.

	
    /**
     * �⺻ �����ڷ� Predicate�� �޽��ϴ�. �ش� Predicate�� �޽����� ������ ������ ���� �ݹ��Լ��Դϴ�.<br>
     * �ش� �Լ��� �������� ������ Ŭ������ ���� �� �� �����ϴ�.
     * **/
    public MyMqttClient (Consumer<HashMap<Object, Object>> fnc){
        this.FNC = fnc;
    }
	
    /**
     * ���������� ����մϴ�.<br>
     * �Ķ���ʹ� �� 4���� �ʿ��մϴ�.<br>
     * ������̸�, ��й�ȣ, �ּ�, �����Ŀ� ����� ���̵� �Դϴ�.
     * */
    public MyMqttClient init(String userName, String password, String serverURI, String clientId){
        option = new MqttConnectOptions();
        option.setCleanSession(true);
        option.setKeepAliveInterval(30);
        option.setUserName(userName);
        option.setPassword(password.toCharArray());
        try {
            client = new MqttClient(serverURI, clientId);
            client.setCallback(this);
            client.connect(option);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
	
    /***
     * ���� �޼ҵ��Դϴ�.
     *
    **/
    public boolean sender(String topic, String msg) throws MqttPersistenceException, MqttException{
        MqttMessage message = new MqttMessage();
        message.setPayload(msg.getBytes());
        client.publish(topic, message);
        return true;
    }
	
    /***
     * ���� ����� �����մϴ�.
     *
     * **/
    public boolean subscribe(String... topics){
        try {
            if(topics != null){
                for(String topic : topics){
                    client.subscribe(topic,0);
                }
            }			
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
	
    /**
     * Ŀ�ؼ��� ������ ������ �ݹ������� ����մϴ�.<br>
     * �ؽ��� ������ ����� Ű�� result, ���� Throwable ��ü�� ��ȯ �մϴ�. 
     * **/
    public void initConnectionLost (Consumer<HashMap<Object, Object>> fnc){
        FNC2 = fnc;
    }
	

    /**
     * ������ �Ϸ�� ������ �ݹ������� ����մϴ�.<br>
     * �ؽ��� ������ ����� Ű�� result, ���� IMqttDeliveryToken ��ü�� ��ȯ �մϴ�. 
     * **/
    public void initDeliveryComplete (Consumer<HashMap<Object, Object>> fnc){
        FNC3 = fnc;
    }
	
    /**
     * ����޼ҵ��Դϴ�.<br>
     * Ŭ���̾�Ʈ�� ���� �մϴ�.
     * */
    public void close(){
        if(client != null){
            try {
                client.disconnect();
                client.close();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
	
    public void connectionLost(Throwable arg0) {
        if(FNC2 != null){
            HashMap<Object, Object> result = new HashMap<>();
            result.put("result", arg0);
            FNC2.accept(result);
            arg0.printStackTrace();
        }
    }	
	
    public void deliveryComplete(IMqttDeliveryToken arg0) {
        if(FNC3 != null){
            HashMap<Object, Object> result = new HashMap<>();
            try {
                result.put("result", arg0);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("result", "ERROR");
                result.put("error", e.getMessage());
            }
            FNC3.accept(result);
        }
    }

    //�޽��� ����
    public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
        if(FNC != null){
            HashMap<Object, Object> result = new HashMap<>();
            result.put("topic", arg0);
            result.put("message", new String(arg1.getPayload(),"UTF-8"));
            FNC.accept(result);  //�ݹ����� ����
        }
    }
}