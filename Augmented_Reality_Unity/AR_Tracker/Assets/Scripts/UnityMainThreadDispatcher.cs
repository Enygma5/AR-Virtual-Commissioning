using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System;

public class UnityMainThreadDispatcher : MonoBehaviour
{
    private static readonly Queue<Action> ExecuteOnMainThread = new Queue<Action>();
    private static UnityMainThreadDispatcher instance;

    public static UnityMainThreadDispatcher Instance
    {
        get
        {
            if (instance == null)
            {
                instance = FindObjectOfType<UnityMainThreadDispatcher>();
            }
            return instance;
        }
    }

    public void Enqueue(Action action)
    {
        ExecuteOnMainThread.Enqueue(action);
    }

    private void Update()
    {
        while (ExecuteOnMainThread.Count > 0)
        {
            ExecuteOnMainThread.Dequeue().Invoke();
        }
    }
}
