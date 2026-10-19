public class Constants {

    //Real name of the agents
    public static final String REAL1 = "Real1";
    public static final String REAL2 = "Real2";
    public static final String VIRTUAL = "Virtual";

    //Displayed names of the agents
    public static final String REAL1_DISPLAY = "Real 1";
    public static final String REAL2_DISPLAY = "Real 2";
    public static final String VIRTUAL_DISPLAY = "Virtual";

    //Skills
    public static final String SK_REAL1_LEFT = "sk_r1_l";
    public static final String SK_REAL1_RIGHT = "sk_r1_r";
    public static final String SK_REAL2_LEFT = "sk_r2_l";
    public static final String SK_REAL2_RIGHT = "sk_r2_r";
    public static final String SK_VIRTUAL_LEFT = "sk_v_l";
    public static final String SK_VIRTUAL_RIGHT = "sk_v_r";

    //Websocket connection topics
    public static final String REAL1_TOPIC = "/real1";
    public static final String REAL2_TOPIC = "/real2";
    public static final String VIRTUAL_TOPIC = "/virtual";

    //Websocket messages
    public static final String MOVE_RIGHT_MSG = "move_right";
    public static final String MOVE_LEFT_MSG = "move_left";
    public static final String STOP_MSG = "stop";
    public static final String RIGHT_SENSOR_ON_MSG = "right_sensor_on";
    public static final String RIGHT_SENSOR_OFF_MSG = "right_sensor_off";
    public static final String LEFT_SENSOR_ON_MSG = "left_sensor_on";
    public static final String LEFT_SENSOR_OFF_MSG = "left_sensor_off";
    public static final String CREATE_BOX_LEFT_MSG = "create_box_left";
    public static final String CREATE_BOX_RIGHT_MSG = "create_box_right";
    public static final String DELETE_BOX_MSG = "delete_box";
}
