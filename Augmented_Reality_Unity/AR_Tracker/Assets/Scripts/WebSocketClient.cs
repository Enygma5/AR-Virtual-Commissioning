using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using WebSocketSharp;
//using NativeWebSocket;
using System.Threading.Tasks;

public class WebSocketClient : MonoBehaviour
{
    private static WebSocketClient instance;

    private ConveyorManager conveyor;

    public WebSocket websocket;

    public static WebSocketClient Instance
    {
        get
        {
            if (instance == null)
            {
                instance = FindObjectOfType<WebSocketClient>();
            }
            return instance;
        }
    }

    private void Awake()
    {
        conveyor = FindObjectOfType<ConveyorManager>();
    }

    private void Start()
    {
        websocket = new WebSocket("ws://192.168.1.13:8080/virtual"); //Home
        //websocket = new WebSocket("ws://192.168.250.204:8080/virtual"); //Lab
        //websocket = new WebSocket("ws://192.168.250.221:8080/virtual"); //Laptop

        websocket.OnOpen += (sender, e) =>
        {
            Debug.Log("Connection open!");
            websocket.Send("Hello server!");
        };

        websocket.OnMessage += (sender, e) =>
        {
            string message = e.Data;
            Debug.Log("OnMessage! " + message);

            switch (message)
            {
                case "move_left":
                    conveyor.MoveConveyorLeft();
                    break;
                case "move_right":
                    conveyor.MoveConveyorRight();
                    break;
                case "stop":
                    Debug.Log("Received command to stop");
                    conveyor.StopConveyor();
                    break;
                case "create_box_left":
                    Debug.Log("Received command to spawn box at left");
                    conveyor.SpawnBoxLeft();
                    break;
                case "create_box_right":
                    conveyor.SpawnBoxRight();
                    break;
                case "delete_box":
                    conveyor.DeleteBox();
                    break;
            }
        };

        websocket.OnError += (sender, e) => {
            Debug.Log("Error! " + e.Message);
        };

        websocket.OnClose += (sender, e) =>
        {
            Debug.Log("Connection closed: " + e.Reason);
            websocket.Connect();
        };

        websocket.ConnectAsync();
    }

    private void OnApplicationQuit()
    {
        websocket.Close();
    }
}