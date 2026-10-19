using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ConveyorManager : MonoBehaviour
{
    public GameObject box_prefab;

    public float belt_speed = 0f;

    public GameObject left_sensor_image;
    public GameObject right_sensor_image;

    private GameObject conveyor;
    private GameObject box_instance;

    private Transform left_spawn_transform;
    private Transform right_spawn_transform;

    private Rigidbody belt_rigidbody;

    private Color32 grey = new Color32(150,150,150,150);
    private Color32 green = new Color32(0,255,0,150);

    private WebSocketClient client;

    public void SpawnBoxLeft()
    {
        if (box_instance == null)
        {
            Debug.Log("Spawning box on the left");
            box_instance = Instantiate(box_prefab, left_spawn_transform.position, left_spawn_transform.rotation, conveyor.transform);
            Debug.Log("Spawned box on the left");
            
        }
    }

    public void SpawnBoxRight()
    {
        if (box_instance == null){
            box_instance = Instantiate(box_prefab, right_spawn_transform.position, right_spawn_transform.rotation, conveyor.transform);
        }
    }

    public void DeleteBox()
    {
        Destroy(box_instance);
        OnRightSensorExit();
        OnLeftSensorExit();
    }

    public void MoveConveyorLeft()
    {
        belt_speed = -0.002f;
    }

    public void MoveConveyorRight()
    {
        belt_speed = 0.002f;
    }

    public void StopConveyor()
    {
        belt_speed = 0f;
        Debug.Log("Stopped the conveyor");
    }

    public void OnLeftSensorEnter()
    {
        left_sensor_image.GetComponent<Image>().color = green;
        Debug.Log("will send left sens on");
        client.websocket.Send("left_sensor_on");
        Debug.Log("sent left sens on");
    }

    public void OnLeftSensorExit()
    {
        left_sensor_image.GetComponent<Image>().color = grey;
        Debug.Log("will send left sens off");
        client.websocket.Send("left_sensor_off");
        Debug.Log("sent left sens off");
    }

    public void OnRightSensorEnter()
    {
        right_sensor_image.GetComponent<Image>().color = green;
        client.websocket.Send("right_sensor_on");
    }

    public void OnRightSensorExit()
    {
        right_sensor_image.GetComponent<Image>().color = grey;
        client.websocket.Send("right_sensor_off");
    }

    private void Awake()
    {
        client = WebSocketClient.Instance;
    }

    private void Start()
    {
        left_sensor_image.GetComponent<Image>().color = grey;
        right_sensor_image.GetComponent<Image>().color = grey;
        client.websocket.Send("left_sensor_off");
        client.websocket.Send("right_sensor_off");
    }

    void FixedUpdate()
    {
        //Controls the belt's movement
        if (belt_speed != 0f)
        {
            Vector3 belt_position = belt_rigidbody.position;
            belt_rigidbody.position += belt_rigidbody.transform.forward * belt_speed;
            belt_rigidbody.MovePosition(belt_position);
        }
    }

    // Update is called once per frame
    void Update()
    {
        if (conveyor == null) {
            conveyor = GameObject.FindWithTag("Conveyor");
            if (conveyor != null)
            {
                left_spawn_transform = conveyor.transform.Find("SpawnPointLeft");
                right_spawn_transform = conveyor.transform.Find("SpawnPointRight");
                belt_rigidbody = conveyor.transform.Find("Belt").gameObject.GetComponent<Rigidbody>();
            }
        }
    }
}
