package server.networking;

import data.Conversation;
import server.data.UserThread;

import java.util.ArrayList;

public class ThreadManager extends Thread {
    private static ThreadManager instance;

    private ArrayList<UserThread> threads = new ArrayList<>();

    //Threads that have not yet been added to the above list
    private ArrayList<UserThread> newThreads = new ArrayList<>();
    private int amountOfThreads = 0;


    private Conversation conversation = new Conversation();

    public static ThreadManager getInstance() {
        if (instance == null)
            instance = new ThreadManager();

        return instance;
    }

    private ThreadManager() {
    }

    public void run() {
        try {
            while (true) {

                //TODO Old, can be removed (all threads now send and receive data individually using the ThreadManager's Conversation object)
/*
                //Collect messages from each client
                for (UserThread thread : threads) {
                    //  if (thread.getCurrentLine() != null)
                    //      System.out.println(thread.getUserName() + ": " + thread.getCurrentLine());
                }

                //Send all messages to each client
                for (UserThread thread : threads) {
                    //    thread.getObjectToClient().writeObject(conversation);
                }
                */
/*
                //Adding new threads
                for (UserThread newThread : newThreads) {
                    threads.add(newThread);
                }
                */

                // System.out.println(conversation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void addUserThread(UserThread thread) {
        threads.add(thread);
        amountOfThreads++;
    }

    public int getAmountOfThreads() {
        return amountOfThreads;
    }

    public synchronized Conversation getConversation() {
        return conversation;
    }

 }
