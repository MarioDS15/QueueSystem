
public class QueueSystem {

    private static int clock = 0;
    private static int totalWaitingTime = 0;
    private static Client[] clientsWorld = new Client[10];
    private static int totalClientsInSystem;
    private static int waitingLineSize;
    private static Client[] waitingLine;
    private static boolean tvInWaitingArea;
    private static boolean coffeeInWaitingArea;
    private static Queue[] queues = new Queue[5];
    private static int waitingLineCount = 0;
    private static Request[] emptyRequest = {};

    /**
     * Creates a new QueueSystem object.
     *
     * @param waitingLineSizeInput The size of the waiting line.
     * @param tvInWaitingAreaInput Whether there is a TV in the waiting area.
     * @param coffeeInWaitingAreaInput Whether there is coffee in the waiting area.
     */
    public QueueSystem(int waitingLineSizeInput, boolean tvInWaitingAreaInput, boolean coffeeInWaitingAreaInput) {
        clock = 0;
        totalClientsInSystem = 0;
        totalWaitingTime = 0;
        waitingLineCount = 0;
        waitingLineSize = waitingLineSizeInput;
        clientsWorld = new Client[10];
        waitingLine = new Client[waitingLineSize];
        tvInWaitingArea = tvInWaitingAreaInput;
        coffeeInWaitingArea = coffeeInWaitingAreaInput;
        queues = new Queue[10];
    }

    /**
     * Gets the current clock time.
     *
     * @return The current clock time.
     */
    public static int getClock() {
        return clock;
    }

    /**
     * Sets the clock time.
     *
     * @param clockInput The new clock time to set.
     */
    public static void setClock(int clockInput) {
        clock = clockInput;
    }

    /**
     * Gets the total waiting time of clients in the system.
     *
     * @return The total waiting time.
     */
    public static int getTotalWaitingTime() {
        return totalWaitingTime;
    }

    /**
     * Sets the total waiting time of clients in the system.
     *
     * @param totalWaitingTimeInput The new total waiting time to set.
     */
    public static void setTotalWaitingTime(int totalWaitingTimeInput) {
        totalWaitingTime = totalWaitingTimeInput;
    }

    /**
     * Gets the array of clients in the world.
     *
     * @return The array of clients in the world.
     */
    public static Client[] getClientsWorld() {
        return clientsWorld;
    }

    /**
     * Sets the array of clients in the world.
     *
     * @param clientsWorldInput The new array of clients in the world to set.
     */
    public static  void setClientsWorld(Client[] clientsWorldInput) {
        clientsWorld = clientsWorldInput;
    }

    /**
     * Gets the total number of clients in the system.
     *
     * @return The total number of clients in the system.
     */
    public static  int getTotalClientsInSystem() {
        return totalClientsInSystem;
    }

    /**
     * Sets the size of the waiting line.
     *
     * @param waitingLineSizeInput The new size of the waiting line to set.
     */
    public static void setTotalClientsInSystem(int totalClientsInSystemInput) {
        totalClientsInSystem = totalClientsInSystemInput;
    }

    /**
     * Gets the array of clients in the waiting line.
     *
     * @return The array of clients in the waiting line.
     */
    public static int getWaitingLineSize() {
        return waitingLineSize;
    }

    /**
     * Sets the clock time.
     *
     * @param clockInput The new clock time to set.
     */
    public static void setWaitingLineSize(int waitingLineSizeInput) {
        waitingLineSize = waitingLineSizeInput;
    }

    /**
     * Gets the total waiting time of clients in the system.
     *
     * @return The total waiting time.
     */
    public static Client[] getWaitingLine() {
        return waitingLine;
    }

    /**
     * Sets the total waiting time of clients in the system.
     *
     * @param totalWaitingTimeInput The new total waiting time to set.
     */
    public static void setWaitingLine(Client[] waitingLineInput) {
        waitingLine = waitingLineInput;
    }

        /**
     * Checks if there is a TV in the waiting area.
     *
     * @return true if there is a TV in the waiting area, false otherwise.
     */
    public static boolean isTvInWaitingArea() {
        return tvInWaitingArea;
    }

    /**
     * Sets whether there is a TV in the waiting area.
     *
     * @param tvInWaitingAreaInput true if there is a TV in the waiting area, false otherwise.
     */
    public static void setTvInWaitingArea(boolean tvInWaitingAreaInput) {
        tvInWaitingArea = tvInWaitingAreaInput;
    }


    /**
     * Checks if there is coffee in the waiting area.
     *
     * @return true if there is coffee in the waiting area, false otherwise.
     */
    public static  boolean isCoffeeInWaitingArea() {
        return coffeeInWaitingArea;
    }

    /**
     * Sets whether there is coffee in the waiting area.
     *
     * @param coffeeInWaitingAreaInput true if there is coffee in the waiting area, false otherwise.
     */
    public static void setCoffeeInWaitingArea(boolean coffeeInWaitingAreaInput) {
        coffeeInWaitingArea = coffeeInWaitingAreaInput;
    }

    /**
     * Gets the array of queues in the system.
     *
     * @return The array of queues in the system.
     */
    public static Queue[] getQueues() { 
        return queues;
    }

    /**
     * Sets the array of queues in the system.
     *
     * @param queuesInput The new array of queues to set.
     */
    public static void setQueues(Queue[] queuesInput) {
        queues = queuesInput;
    }
    
    /**
     * Gets the clients currently being served in each queue.
     *
     * @return An array of clients being served in each queue.
     */
    public static Client[] getClientsBeingServed()
    {
        Client[] clientsBeingServed = new Client[queues.length];
        for(int i = 0; i < queues.length; i++)
        {
            clientsBeingServed[i] = queues[i].getClientBeingServed();
        }
        return clientsBeingServed;
    }

    /**
     * Increases the clock time by 1 unit and performs various queue management tasks.
     */
    public void increaseTime()
    {
        setClock(getClock() + 1);
       // System.out.println("\nClock: " + clock);

        patienceCheck(); // Check if anyone left due to patience
        //System.out.println("Check service starting: " + queues[0].toString() + "\n");
        checkService(); // Check if service is done and updates requests
        //System.out.println("Check service ended: " + queues[0].toString() + "\n");
        while(availableQueue() && waitingLineCount() != 0) // While there are open spots add priority clients to queue
        {
            //System.out.println("Wait Line size: " + waitingLineCount());
            Client client = priorityClient();
            if(client == null){break;}
            addClientToQueue(client);
        }
        //System.out.println("VIP Queue available: " + availableVIPQueue());
        while(waitingLineCount() != 0)
        {
            if(availableVIPQueue())
            {
                VIPClient client = priorityVIPClient();
                if(client == null){break;}
                addClientToQueue(client);
                continue;
            }
            else if (availableQueue())
            {
                
                Client client = priorityVIPClient();
                if(client == null){break;}
                //System.out.println("People in waiting line: " + waitingLineCount());
                addVIPToQueue(client);
            }
            else
            {
                break;
            }
        }
        newClients(); // Move clients from Client world to waiting line
        //System.out.println(this.toString(false));
    }

    /**
     * Increases the clock time by a specified number of units and performs various queue management tasks.
     *
     * @param time The number of time units to increase the clock by.
     */
    public void increaseTime(int time)
    {
        for(int i = 0; i < time; i++){increaseTime();}
    }

    /**
     * Checks the service status of clients in the queues and updates their requests.
     */
    private static void checkService()
    {
        int estEnd = 0;
        for(Queue queue : queues)
        {
            if(queue == null){continue;}

            if(queue.getClientBeingServed() != null && queue.getRequestInProgress() != null)
            {
                    estEnd = queue.getRequestInProgress().getEndTime();
                    int time = getClock();

                    if(estEnd <= time)
                    {

                        // Remove the client from the queue
                        if(queue.getClientBeingServed().getRequests().length <= 1)
                        {   
                            
                            // Archive the client
                            queue.addToHistory(queue.getClientBeingServed());
                            queue.getClientBeingServed().setDepartureTime(getClock());

                            //Remove the client
                            queue.getClientBeingServed().setRequests(emptyRequest);
                            queue.setClientBeingServed(null);   
                            queue.setRequestInProgress(null);
                            
                            

                            if(queue.getClientsInQueue()[0] != null)
                            {
                                queue.setClientBeingServed(queue.getClientsInQueue()[0]);
                                //System.out.println("Next client " + queue.getClientBeingServed().getId() + " has requests:  " + queue.getClientBeingServed().getRequests().length);
                                //queue.setPeopleInQueue(queue.getClientCount() - 1);
                                queue.setRequestInProgress(queue.getClientsInQueue()[0].getRequests()[0]);
                                queue.getClientsInQueue()[0].getRequests()[0].setStartTime(getClock());
                                int depart = getClock() + queue.getClientsInQueue()[0].getServiceTime();
                                queue.getClientBeingServed().setDepartureTime(depart);
                                //queue.getClientsInQueue()[0].setDepartureTime(depart);
                                queue.getRequestInProgress().setStatus(Status.IN_PROGRESS);
                                queue.getClientsInQueue()[0].getRequests()[0].setEndTime(getClock() + queue.getClientsInQueue()[0].getRequests()[0].calculateProcessingTime()) ;
                                queue.removeClient(queue.getClientBeingServed());
                                queue.shiftClients();
                            }
                        }
                        else
                        {
                            // Remove the request from the client
                            Request[] currRequests = queue.getClientBeingServed().getRequests();
                            queue.getRequestInProgress().setStatus(Status.PROCESSED);
                            if(currRequests != null && currRequests.length > 1)
                            {
                                Request[] newRequests = new Request[currRequests.length - 1];
                                for(int i = 1; i < currRequests.length; i++)
                                {
                                    newRequests[i - 1] = currRequests[i];
                                }
                                queue.getClientBeingServed().setRequests(newRequests);
                                queue.getClientBeingServed().getRequests()[0].setStartTime(getClock());
                                queue.getClientBeingServed().getRequests()[0].setEndTime(getClock() + queue.getClientBeingServed().getRequests()[0].calculateProcessingTime());
                            }
                            else{queue.getClientBeingServed().setRequests(new Request[0]);}
                            
                        }
                    }
                    //System.out.println("Queue:" + queue.toString());
            }
            else // If no one is being served, find the next client to serve
            {
                if(queue.getClientCount() != 0)
                {
                    if(queue.getClientsHistory()[0] == null){queue.setProcessingStartTime(getClock());}
                    queue.setClientBeingServed(queue.getClientsInQueue()[0]);
                    //queue.setClientCount(queue.getClientCount() - 1);
                    queue.setRequestInProgress(queue.getClientBeingServed().getRequests()[0]);
                    queue.getClientBeingServed().getRequests()[0].setStartTime(getClock());
                    queue.getClientBeingServed().setDepartureTime(getClock() + queue.getClientBeingServed().getServiceTime()); //revisit
                    queue.getRequestInProgress().setStatus(Status.IN_PROGRESS);
                    queue.getClientBeingServed().getRequests()[0].setEndTime(getClock() + queue.getClientBeingServed().getRequests()[0].calculateProcessingTime());
                    queue.removeClient(queue.getClientBeingServed());

                    //System.out.println("About to shift : " + queues[0].toString() + "\n");
                    queue.shiftClients();
                    //System.out.println("Shifted : " + queues[0].toString() + "\n");
                }
            }
        }
    }

    /**
     * Checks if any clients have left the system due to patience.
     */
    private static void patienceCheck()
    {
        // Adjusts patience in queue
        for(Queue queue : queues) // Removes clients from queue if patience is 0
        {
            if(queue == null){continue;}
            for( int i = 0; i < queue.getClientsInQueue().length; i++)
            {
                Client client = queue.getClientsInQueue()[i];
                if(client == null){continue;}
                client.setWaitingTime(client.getWaitingTime() + 1);
                totalWaitingTime++;
                if(client.getPatience() <= 0)
                {
                    client.setDepartureTime(getClock());
                    queue.removeClient(client);
                    totalClientsInSystem--; // Decrement the total number of clients
                    System.out.println("Client " +   client.getFirstName() + " has left Queue due to patience at time: " + clock + "\n");
                    queue.shiftClients();
                    i--;
                }
                else{client.setPatience(client.getPatience() - 1);client.setTimeInQueue(client.getTimeInQueue()+1);}
            }
            
        }

        // Adjusts patience in waitingline
        for(int i = 0; i < waitingLine.length; i++) // Removes clients from waiting line if patience is 0
        {
            Client client = waitingLine[i];
            if(client == null){continue;}
            if ((client.getPatience() <= 0))
            {
                client.setDepartureTime(getClock());
                removeClientFromWaitingLine(client);
                totalClientsInSystem--;
                //System.out.println("Client " + client.getId() + " left the waiting line");
                i--;
            }
            else{client.setPatience(client.getPatience() - 1);}
        }

        // Adjusts patience in clients world
        for(int i = 0; i < clientsWorld.length; i++) {
            Client client = clientsWorld[i];
            if(client == null){continue;}
            if(client.getArrivalTime() < getClock())
            {   
                if ((client.getPatience() <= 0))
                {
                    client.setDepartureTime(getClock());
                    removeClientFromClientsWorld(client);
                }
                else{client.setPatience(client.getPatience() - 1);}
            }
        }
    }

    
    /**
     * Returns the highest priority client in the system.
     *
     * @return Returns the highest priority client in the system.
     */
    private static Client priorityClient() {
        Client priority = null; // The client with the highest priority
        for (Client client : waitingLine) {
            if (client == null) {continue;}
            if (client instanceof VIPClient){continue;}
            if (priority == null) {
                priority = client;
            } 
            else if (client.getArrivalTime() < priority.getArrivalTime()) {
                priority = client;
            }
            else if (client.getArrivalTime() == priority.getArrivalTime()) {
                if(client.getYearOfBirth() < priority.getYearOfBirth())
                {
                    priority = client;
                }
                else if (client.getYearOfBirth() == priority.getYearOfBirth())
                {
                    if(client.getServiceTime() < priority.getServiceTime())
                    {
                        priority = client;
                    }
                    else if (client.getServiceTime() == priority.getServiceTime())
                    {
                        if(client.getId() < priority.getId())
                        {
                            priority = client;
                        }
                    }
                }
            }
        }
        if(priority == null){return null;}
        return priority;
    }

    /**
     * Returns the highest priority VIP client in the system.
     *
     * @return Returns the highest priority VIP client in the system.
     */
    private static VIPClient priorityVIPClient() {
        VIPClient priorityVIP = null; // The VIP client with the highest priority
    
        for (Client client : waitingLine) {
            if (client == null) {continue;}
            if (!(client instanceof VIPClient)) {
                continue; // Skip non-VIP clients
            }
    
            VIPClient vipClient = (VIPClient) client; // Cast the client to VIPClient
    
            if (priorityVIP == null) {
                priorityVIP = vipClient;
            } else {
                // Compare VIP clients based on priority level and memberSince fields
                if (vipClient.getPriority() > priorityVIP.getPriority()) {
                    priorityVIP = vipClient;
                } else if (vipClient.getPriority() == priorityVIP.getPriority()) {
                    if (vipClient.getMemberSince() < priorityVIP.getMemberSince()) {
                        priorityVIP = vipClient;
                    }
                    else if (vipClient.getArrivalTime() < priorityVIP.getArrivalTime()) {
                        priorityVIP = vipClient;
                    }
                }
            }
        }
        return priorityVIP;
    }

    /**
     * Adds a client to a queue.
     *
     * @param client The client to be added to a queue.
     * @return true if the client was added to a queue, false otherwise.
     */
    private static boolean addClientToQueue(Client client) {
        Queue selectedQueue = null;
        int minQueueSize = Integer.MAX_VALUE;
        //System.out.println("Client " + client.getId() + " is being added to a queue");
        for (Queue queue : queues) {
            if(queue == null){continue;}
            if ((client instanceof VIPClient && !(queue instanceof VIPQueue)) ||
                (!(client instanceof VIPClient) && queue instanceof VIPQueue))
                {
                    {continue;}
            }
            
            int currentQueueSize = queue.getClientCount();
            // If queue with smaller size is found
            if (currentQueueSize < minQueueSize && queue.getClientCount() < queue.getQueueSize()) {
                //if(selectedQueue.getClientBeingServed().getDepartureTime() > queue.getClientBeingServed().getDepartureTime()){
                selectedQueue = queue;
                minQueueSize = currentQueueSize;}
                //}
            }
    
        if (selectedQueue != null) {
            //System.out.println("Added client " + client.getId() + " to queue ");
            removeClientFromWaitingLine(client);
            selectedQueue.addClient(client);
            client.setQueueJoined(selectedQueue);
            return true;
        }
    
        return false; // No suitable queue found
    }

    /**
     * Adds a client to a VIP queue.
     *
     * @param client The client to be added to a VIP queue.
     */
    public static void addVIPToQueue(Client client)
    {
        Queue selectedQueue = null;
        int minQueueSize = Integer.MAX_VALUE;
        int i = 0;
        for (Queue queue : queues) {
            i++;
            if(queue == null){continue;}
            if(queue instanceof VIPQueue){continue;}
            if(queue.getClientCount() >= queue.getQueueSize()){continue;}
            int currentQueueSize = queue.getClientCount();
            // If queue with smaller size is found
            if (currentQueueSize < minQueueSize) {
                
                //if(selectedQueue.getClientBeingServed().getDepartureTime() > queue.getClientBeingServed().getDepartureTime()){
                selectedQueue = queue;
                minQueueSize = currentQueueSize;}
                //}
            }
    
        if (selectedQueue != null) {
            //System.out.println("Added client " + client.getId() + " to normal queue " + i);
            selectedQueue.addClient(client);
            removeClientFromWaitingLine(client);
            
            client.setQueueJoined(selectedQueue);
            //return true;
        }
    }

    /**
     * Finds an available VIPQueue.
     *
     */
    private static boolean availableVIPQueue()
    {
        for(Queue queue : queues)
        {
            if(queue == null){continue;}
            if(queue.getClientCount() < queue.getQueueSize() && queue instanceof VIPQueue)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds an available normal Queue.
     *
     */
    private static boolean availableQueue()
    {
        for(Queue queue : queues)
        {
            if(queue == null){continue;}
            if(queue.getClientCount() < queue.getQueueSize() && !(queue instanceof VIPQueue))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there are any new Clients that need to be put into the system (Waiting Line)
     */
    private static void newClients()
    {
        for(int i = 0; i < clientsWorld.length ; i++)
        {
            //System.out.println("i: " + i);
            Client[] copy = getClientsWorld();
            Client client = getClientsWorld()[i];
            
            if(client == null){continue;}
            //System.out.println(client.toString() + "\n");
            //System.out.println("Client " + client.getId() + " has arrival time: " + client.getArrivalTime() + " and clock is: " + getClock());
            if(client.getArrivalTime() <= getClock())
            {
                //System.out.println("Amount of people in waiting line: " + waitingLineCount() + " When trying to add client " + client.getId() + " to waiting line");
                if(waitingLineCount() < waitingLineSize)
                {
                    
                    addClientToWaitingLine(client);
                    //System.out.println("Added client " + client.getId() + " to waiting line, amount of people in waiting line: " + waitingLineCount() + "\n");
                    i--;
                    //removeClientFromClientsWorld(client);
                }
                 // Added
            }
        }

    }

    /**
     * Adds a client to the waiting line, considering coffee and TV availability.
     *
     * @param client The client to be added to the waiting line.
     */
    private static void addClientToWaitingLine(Client client) { // Not done,  coffee / tv
        // Add the client to the waiting line
        for (int i = 0; i < waitingLine.length; i++) {
            if (waitingLine[i] == null) {
                waitingLine[i] = client;
                //System.out.println("Client " + client.getId() + " has been added to the waiting line, amount of people in waiting line: " + waitingLineCount() + "\n");
                totalClientsInSystem++; // Increment the total number of clients
                if(coffeeInWaitingArea && client.getYearOfBirth() <= 2005){client.setPatience(client.getPatience() + 15);}
                if(tvInWaitingArea){client.setPatience(client.getPatience() + 20);}
                //System.out.println("Calling remove client from clients world\n");
                removeClientFromClientsWorld(client);
                break;
            }
        }  
    }
    
    /**
     * Returns the number of clients in the waiting line.
     *
     * @return The count of clients in the waiting line.
     */
    private static int waitingLineCount()
    {
        int count = 0;
        for(Client client : waitingLine)
        {
            if(client != null){count++;}
        }
        return count;
    }
    
    /**
     * Removes a client from the "ClientsWorld" when they enter the waiting line.
     *
     * @param clientToRemove The client to be removed from the "ClientsWorld."
     */
    private static void removeClientFromClientsWorld(Client clientToRemove) {
        //System.out.println("Removing client " + clientToRemove.getId() + " from clients world");
        for (int i = 0; i < clientsWorld.length; i++) {
            if (clientsWorld[i] == clientToRemove) {
                clientsWorld[i] = null; // Set the element to null to "remove" the client
                for (int j = i; j < clientsWorld.length - 1; j++) {
                    clientsWorld[j] = clientsWorld[j + 1];
                }
                break; // Exit the loop after removing the client
            }
        }
    }

    /**
     * Removes a client from the waiting line.
     *
     * @param client The client to be removed from the waiting line.
     */
    private static void removeClientFromWaitingLine(Client client)
    {
        for(int i = 0; i < waitingLine.length; i++)
        {
            if(waitingLine[i] == client)
            {
                
                waitingLine[i] = null;
                for(int j = i; j < waitingLine.length - 1; j++)
                {
                    waitingLine[j] = waitingLine[j + 1];
                }
                waitingLine[waitingLine.length - 1] = null;
                //System.out.println("Client " + client.getId() + " has been removed from the waiting line, amount of people in waiting line: " + waitingLineCount() + "\n");
                break;
            }
        }
    }

    /**
     * Returns the string representation of the QueueSystem object.
     *
     * @return The count of clients in the system.
     */
    public String toString()
    {
        String output = "[WaitingLine]-";
        for(Client client : waitingLine)
        {
            if(client != null){output += "[" + client.getId() + "]";}
            else{output += "[ ]";}
        }
        output+= "\n---\n";
        int i = 1;
        for(Queue queue : queues)
        {
           if(queue != null)
            {
                queue.setQueueNumber(i++);
                output += queue.toString() + "\n";}
        }
        return output;
    }

    /**
     * Returns the string representation of the QueueSystem object.
     *
     * @param showID Whether to show the ID or time remaining of the clients in the system.
     * @return The count of clients in the system.
     */
    public String toString(boolean showID)
    {
        if(showID){return this.toString();}
        String output = "[WaitingLine]-";
        
        for(Client client : waitingLine)
        {
            if(client != null){
                int estimatedProcessingTime = client.getServiceTime();
                if (estimatedProcessingTime < 10) {
                    output += "[0" + String.valueOf(estimatedProcessingTime) + "]";
                }
                else{output += "[" + String.valueOf(estimatedProcessingTime) + "]";}
            }
            else{output += "[ ]";}
            
        }
        output+= "\n---\n";
        int i = 1;
        for(Queue queue : queues)
        {
            
            if(queue != null){queue.setQueueNumber(i++);output += queue.toString(false) + "\n";}
        }
        return output;
    }


}
