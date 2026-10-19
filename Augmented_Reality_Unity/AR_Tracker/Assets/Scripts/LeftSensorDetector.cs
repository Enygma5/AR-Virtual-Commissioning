using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Events;

public class LeftSensorDetector : MonoBehaviour
{
    ConveyorManager conveyor_manager;

    private void OnTriggerEnter()
    {
        conveyor_manager.OnLeftSensorEnter();
    }

    private void OnTriggerExit()
    {
        conveyor_manager.OnLeftSensorExit();
    }

    private void Start()
    {
        conveyor_manager = GameObject.FindGameObjectWithTag("Manager").GetComponent<ConveyorManager>();
    }
}
