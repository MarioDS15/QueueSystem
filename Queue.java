/**
 * Represents a queue in a queue system.
 */
public class Queue {

    private String serverName;
    private int queueSize;
    private Client clientBeingServed;
    private Request requestInProgress;
    private int processingStartTime; // Start of when the queue started, not client
    private Client[] clientsHistory = new Client[queueSize];
    private Client[] clientsInQueue;
    private int peopleServed = 0;
    private int peopleInQueue = 0;

    private int queueNumber = 1;

    /**
     * Constructs a new Queue object with the given server name and queue size.
     * @param serverName the name of the server associated with this queue
     * @param queueSize the maximum number of clients that can be in the queue at once
     */
    public Queue(String serverName, int queueSize) {
        this.serverName = serverName;
        this.queueSize = queueSize;
        this.clientsHistory = new Client[queueSize];
        this.clientsInQueue = new Client[queueSize];
    }

    /**
     * Returns the name of the server associated with this queue.
     * @return the name of the server
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Sets the number of this queue.
     * @param queueNumber the new queue number
     */
    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }

    /**
     * Sets the name of the server associated with this queue.
     * @param serverName the new server name
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * Returns the maximum number of clients that can be in the queue at once.
     * @return the queue size
     */
    public int getQueueSize() {
        return queueSize;
    }

    /**
     * Returns the client currently being served by this queue.
     * @return the client being served
     */
    public Client getClientBeingServed() {
        return clientBeingServed;
    }

    /**
     * Sets the client currently being served by this queue.
     * @param clientBeingServed the new client being served
     */
    public void setClientBeingServed(Client clientBeingServed) {
        this.clientBeingServed = clientBeingServed;
    }

    /**
     * Returns the request currently being processed by this queue.
     * @return the request being processed
     */
    public Request getRequestInProgress() {
        return requestInProgress;
    }

    /**
     * Sets the request currently being processed by this queue.
     * @param requestInProgress the new request being processed
     */
    public void setRequestInProgress(Request requestInProgress) {
        this.requestInProgress = requestInProgress;
    }

    /**
     * Returns the time at which this queue started processing its current request.
     * @return the processing start time
     */
    public int getProcessingStartTime() {
        return processingStartTime;
    }

    /**
     * Sets the time at which this queue started processing its current request.
     * @param processingStartTime the new processing start time
     */
    public void setProcessingStartTime(int processingStartTime) {
        this.processingStartTime = processingStartTime;
    }

    /**
     * Returns an array of all clients that have been served by this queue.
     * @return an array of clients
     */
    public Client[] getClientsHistory() {
        return clientsHistory;
    }

    public void setClientsHistory(Client[] clientsHistory) 
    {
        this.clientsHistory = clientsHistory;
    }
    /**
     * Returns an array of all clients currently in the queue.
     * @return an array of clients
     */
    public Client[] getClientsInQueue() {
        return clientsInQueue;
    }

    /**
     * Returns the number of clients that have been served by this queue.
     * @return the number of clients served
     */
    public int getClientsServed()
    {
        return this.peopleServed;
    }

    /**
     * Sets the number of clients that have been served by this queue.
     * @param peopleServed the new number of clients served
     */
    public void setClientsServed(int peopleServed)
    {
        this.peopleServed = peopleServed;
    }

    /**
     * Adds the given client to the history of clients served by this queue.
     * @param client the client to add to the history
     */
    public void addToHistory(Client client) {
        if (this.peopleServed == this.queueSize - 1) 
        {
            // If the array is full, expand it by creating a new array with double the size
            Client[] newClientsHistory = new Client[(clientsHistory.length) * 2];
            for (int i = 0; i < clientsHistory.length; i++) 
            {
                newClientsHistory[i] = clientsHistory[i];
            }
            clientsHistory = newClientsHistory;
        }
        clientsHistory[peopleServed] = client;
        peopleServed++;
    }

    /**
     * Returns a string representation of this queue, including the clients currently being served and in the queue.
     * @return a string representation of this queue
     */
    public String toString() 
    {
        String returnString = "[Queue:" + this.queueNumber + "]";
        
        if (clientBeingServed != null) 
        {
            if(clientBeingServed.getId() < 10){returnString += "[0" + clientBeingServed.getId() + "]";}
            else{returnString += "[" + clientBeingServed.getId() + "]";}
        }
        else{returnString += "[ ]";}
        returnString += "-----";
        for (Client client : clientsInQueue) {
            if (client != null) {
                if(client.getId() < 10){returnString += "[0" + client.getId() + "]";}
                else{returnString += "[" + client.getId() + "]";}
            }
            else{returnString += "[ ]";}
        }
        return returnString;
    }

    /**
     * Returns a string representation of this queue, including the estimated processing times for clients in the queue.
     * @param showID whether or not to include client IDs in the string representation
     * @return a string representation of this queue
     */
    public String toString(boolean showID) {
        if (showID) {return toString();}
        
        String queueStatus = "[Queue:" + this.queueNumber + "]";
        

        // To string does not take into account if customer is currently being served
        if (requestInProgress != null) {
            int total = getClientBeingServed().getDepartureTime();
            total = total - QueueSystem.getClock() - 1;
            if (total < 10) {
                queueStatus += "[0" + String.valueOf(total) + "]";
            }
            else{queueStatus += "[" + String.valueOf(total) + "]";}
        }
        else{queueStatus += "[ ]";}
        queueStatus += "-----";
        int i = 0;
        for (Client client : clientsInQueue) {

            if (client != null) {
                int estimatedProcessingTime = client.getServiceTime();
                if (estimatedProcessingTime < 10) {
                    queueStatus += "[0" + String.valueOf(estimatedProcessingTime) + "]";
                }
                else{queueStatus += "[" + String.valueOf(estimatedProcessingTime) + "]";}
            }
            else{queueStatus += "[ ]";}
        }
        return queueStatus;
    }

    /**
     * Removes the given client from the queue.
     * @param client the client to remove
     */
    public void removeClient(Client client) {
        Client[] clientsInQueue = getClientsInQueue();
        for (int i = 0; i < clientsInQueue.length; i++) {
            if (clientsInQueue[i] == client) {
                clientsInQueue[i] = null;
            }
        }
        this.setPeopleInQueue(this.getPeopleInQueue() - 1);
    }

    /**
     * Adds the given client to the queue.
     * @param client the client to add
     */
    public void addClient(Client client) {
        for (int i = 0; i < clientsInQueue.length; i++) {
            if (clientsInQueue[i] == null) {
                clientsInQueue[i] = client;
                this.setPeopleInQueue(this.getPeopleInQueue() + 1);
                break;
            }
        }
        
    }

    /**
     * Returns the number of clients currently in the queue.
     * @return the number of clients in the queue
     */
    public void setPeopleInQueue(int number){this.peopleInQueue = number;}

    /**
     * Sets the number of clients currently in the queue.
     * @param peopleInQueue the new number of clients in the queue
     */
    public int getPeopleInQueue(){return this.peopleInQueue;}

    /**
     * Shifts all clients in the queue one position to the left.
     */
    public void shiftClients() {
        //System.out.println("Number of clients in Queue: " + this.getPeopleInQueue() + "\n");
        //Client[] clientsInQueue = getClientsInQueue();
        for (int i = 0; i < this.clientsInQueue.length - 1; i++) {
            clientsInQueue[i] = clientsInQueue[i + 1];
        }
        clientsInQueue[clientsInQueue.length - 1] = null; // Clears last element in list
    }
    

    // Duplicate functions, ignore
    /**
     * Returns the number of clients currently in the queue.
     * @return the number of clients in the queue
     */
    public int getClientCount(){return getPeopleInQueue();}


    public void setClientCount(int number){setPeopleInQueue(number);}
}

