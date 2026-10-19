import org.java_websocket.*;
import org.java_websocket.server.*;
import org.java_websocket.handshake.ClientHandshake;
import java.net.InetSocketAddress;
import java.util.HashMap;

//192.168.250.221 RevPi
//172.21.96.1 Unity

public class SimpleServer extends WebSocketServer {

    //Real Conveyor 1 Sensors' State
    private int r1RightSensor = -1;
    private int r1LeftSensor = -1;

    //Real Conveyor 2 Sensors' State
    private int r2RightSensor = -1;
    private int r2LeftSensor = -1;

    //Virtual Conveyor Sensors' State
    private int vRightSensor = -1;
    private int vLeftSensor = -1;

    HashMap<String, WebSocket> agentWebSockets = new HashMap<String, WebSocket>();

    private static SimpleServer instance;

    // Private constructor to prevent external instantiation
    private SimpleServer(InetSocketAddress address) {
        super(address);
    }

    public static SimpleServer getInstance(InetSocketAddress address) {
        if (instance == null) {
            instance = new SimpleServer(address);
            instance.start();
        }
        return instance;
    }

    @Override
    public void onStart() {
        System.out.println("Server Started!");
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // This method is called when a new WebSocket connection is established
        System.out.println("Opened the connection with " + conn.getResourceDescriptor() + "!");

        switch (conn.getResourceDescriptor()) {
            case Constants.REAL1_TOPIC:
                if (!agentWebSockets.containsKey(Constants.REAL1)) {
                    agentWebSockets.put(Constants.REAL1, conn);
                }
                else{
                    agentWebSockets.replace(Constants.REAL1, conn);
                }
                break;
            case Constants.REAL2_TOPIC:
                if (!agentWebSockets.containsKey(Constants.REAL2)) {
                    agentWebSockets.put(Constants.REAL2, conn);
                }
                else{
                    agentWebSockets.replace(Constants.REAL2, conn);
                }
                break;
            case Constants.VIRTUAL_TOPIC:
                if (!agentWebSockets.containsKey(Constants.VIRTUAL)) {
                    agentWebSockets.put(Constants.VIRTUAL, conn);
                }
                else{
                    agentWebSockets.replace(Constants.VIRTUAL, conn);
                }
                break;
        }
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        // This method is called when a WebSocket message is received
        System.out.println("Received " + message);
        switch (conn.getResourceDescriptor()){
            case Constants.REAL1_TOPIC:
                switch (message){
                    case Constants.RIGHT_SENSOR_ON_MSG:
                        r1RightSensor = 1;
                        break;
                    case Constants.RIGHT_SENSOR_OFF_MSG:
                        r1RightSensor = 0;
                        break;
                    case Constants.LEFT_SENSOR_ON_MSG:
                        r1LeftSensor = 1;
                        break;
                    case Constants.LEFT_SENSOR_OFF_MSG:
                        r1LeftSensor = 0;
                        break;
                }
                break;
            case Constants.REAL2_TOPIC:
                switch (message){
                    case Constants.RIGHT_SENSOR_ON_MSG:
                        r2RightSensor = 1;
                        break;
                    case Constants.RIGHT_SENSOR_OFF_MSG:
                        r2RightSensor = 0;
                        break;
                    case Constants.LEFT_SENSOR_ON_MSG:
                        r2LeftSensor = 1;
                        break;
                    case Constants.LEFT_SENSOR_OFF_MSG:
                        r2LeftSensor = 0;
                        break;
                }
                break;
            case Constants.VIRTUAL_TOPIC:
                switch (message){
                    case Constants.RIGHT_SENSOR_ON_MSG:
                        vRightSensor = 1;
                        break;
                    case Constants.RIGHT_SENSOR_OFF_MSG:
                        vRightSensor = 0;
                        break;
                    case Constants.LEFT_SENSOR_ON_MSG:
                        vLeftSensor = 1;
                        System.out.println("Received left sensor on");
                        break;
                    case Constants.LEFT_SENSOR_OFF_MSG:
                        vLeftSensor = 0;
                        System.out.println("Received left sensor off");
                        break;
                }
                break;
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        // This method is called when a WebSocket connection is closed
        System.out.println("Closed the connection with " + conn.getResourceDescriptor() + "!");
        switch (conn.getResourceDescriptor()) {
            case Constants.REAL1_TOPIC:
                agentWebSockets.remove(Constants.REAL1);
                r1RightSensor = -1;
                r1LeftSensor = -1;
                break;
            case Constants.REAL2_TOPIC:
                agentWebSockets.remove(Constants.REAL2);
                r2LeftSensor = -1;
                r2RightSensor = -1;
                break;
            case Constants.VIRTUAL_TOPIC:
                agentWebSockets.remove(Constants.VIRTUAL);
                vLeftSensor = -1;
                vRightSensor = -1;
                break;
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        // This method is called when an error occurs
        System.out.println("Error " + ex + "!");
    }

    public int getRightSensor(String conveyorName){
        switch(conveyorName){
            case Constants.REAL1:
                return r1RightSensor;
            case Constants.REAL2:
                return r2RightSensor;
            case Constants.VIRTUAL:
                return vRightSensor;
        }
        return -1;
    }

    public int getLeftSensor(String conveyorName){
        switch(conveyorName){
            case Constants.REAL1:
                return r1LeftSensor;
            case Constants.REAL2:
                return r2LeftSensor;
            case Constants.VIRTUAL:
                return vLeftSensor;
        }
        return -1;
    }

    public void moveRight(String conveyorName){
        WebSocket ws = agentWebSockets.get(conveyorName);
        ws.send(Constants.MOVE_RIGHT_MSG);
    }

    public void moveLeft(String conveyorName){
        WebSocket ws = agentWebSockets.get(conveyorName);
        ws.send(Constants.MOVE_LEFT_MSG);
    }

    public void stopConveyor(String conveyorName){
        WebSocket ws = agentWebSockets.get(conveyorName);
        ws.send(Constants.STOP_MSG);
    }

    public void createBoxLeft(String conveyorName){
        WebSocket ws = agentWebSockets.get(conveyorName);
        ws.send(Constants.CREATE_BOX_LEFT_MSG);
    }

    public void createBoxRight(String conveyorName){
        WebSocket ws = agentWebSockets.get(conveyorName);
        ws.send(Constants.CREATE_BOX_RIGHT_MSG);
    }

    public void deleteBox(String conveyorName){
        WebSocket ws = agentWebSockets.get(conveyorName);
        ws.send(Constants.DELETE_BOX_MSG);
    }
}