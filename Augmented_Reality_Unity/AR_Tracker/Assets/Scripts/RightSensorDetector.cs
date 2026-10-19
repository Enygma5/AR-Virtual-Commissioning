using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RightSensorDetector : MonoBehaviour
{
    ConveyorManager conveyor_manager;

    private void OnTriggerEnter()
    {
        conveyor_manager.OnRightSensorEnter();
    }

    private void OnTriggerExit()
    {
        conveyor_manager.OnRightSensorExit();
    }

    private void Start()
    {
        conveyor_manager = GameObject.FindGameObjectWithTag("Manager").GetComponent<ConveyorManager>();
    }
}
