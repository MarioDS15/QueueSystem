public class Client {

    private static int currID = 1; // Static field to autoincrement client ID

    private int id;
    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private Gender gender;
    private Request[] requests;
    private int arrivalTime;
    private int waitingTime;
    private int timeInQueue;
    private int serviceTime;
    private int departureTime;
    private int patience;
    private int startTime;
    private Queue queueJoined;

    
    /**
     * Creates a new Client object with the given parameters.
     * @param firstName
     * @param lastName
     * @param yearOfBirth
     * @param gender
     * @param arrivalTime
     * @param patience
     * @param requests
     */
    public Client(String firstName, String lastName, int yearOfBirth, String gender, int arrivalTime, int patience, Request[] requests) 
    {
        this.id = currID++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.gender = Gender.valueOf(gender);
        this.requests = requests;
        if(arrivalTime > 0){this.arrivalTime = arrivalTime;}
        else {this.arrivalTime = 1;}
        this.waitingTime = 0;
        this.timeInQueue = 0;
        this.serviceTime = 0;
        this.departureTime = 0; // Default value
        this.patience = patience;
    }

    /**
     * Creates a new Client object with the given parameters.
     * @param firstName
     * @param lastName
     * @param yearOfBirth
     * @param gender
     * @param patience
     * @param requests
     */
    public Client(String firstName, String lastName, int yearOfBirth, String gender, int patience, Request[] requests) {
        this(firstName, lastName, yearOfBirth, gender, 0, patience, requests); // Use default arrivalTime
        int time = 0;
        for(Request request : requests)
        {
            time += request.calculateProcessingTime();
        }
        this.serviceTime = time;
    }

    /**
     * Creates a new Client object with the given parameters.
     * @param firstName
     * @param lastName
     * @param yearOfBirth
     * @param gender
     * @param patience
     */
    public Client(String firstName, String lastName, int yearOfBirth, String gender, int patience) {
        this(firstName, lastName, yearOfBirth, gender, 0, patience, new Request[0]); // Use default arrivalTime and empty requests
    }

    /**
     * Creates a new Client object with the given parameters.
     * @return the client ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the start time of the client.
     * @param startTime
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the start time of the client.
     * @return the start time
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Returns the first name of the client.
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the client.
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the client.
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the client.
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the year of birth of the client.
     * @return
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * Sets the year of birth of the client.
     * @param yearOfBirth
     */
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * Get the Gender of the client.
     * @return the Gender of the client.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the clients Gender.
     * @param gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Returns the requests of the client.
     * @return
     */
    public Request[] getRequests() {
        return requests;
    }

    /**
     * Sets the requests of the client.
     * @param requests
     */
    public void setRequests(Request[] requests) {
        this.requests = requests;
    }

    /**
     * Returns the request at the given index.
     * @param index
     * @return
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets the arrival time of the client.
     * @param arrivalTime
     */
    public void setArrivalTime(int arrivalTime) {
        if (arrivalTime >= 1) {
            this.arrivalTime = arrivalTime;
        } else {
            this.arrivalTime = 1;
        }
    }

    /**
     * Returns the waiting time of the client.
     * @return the waiting time.
     */
    public int getWaitingTime() {
        return waitingTime;
    }

    /**
     * Sets the waiting time of the client.
     * @param waitingTime
     */
    public void setWaitingTime(int waitingTime) {
        if (waitingTime >= 0) {
            this.waitingTime = waitingTime;
        }
    }

    /**
     * Returns the time the client spent in the queue.
     * @return the time in queue.
     */
    public int getTimeInQueue() {
        return timeInQueue;
    }

    /**
     * Sets the time the client spent in the queue.
     * @param timeInQueue
     */
    public void setTimeInQueue(int timeInQueue) {
        if (timeInQueue >= 0) {
            this.timeInQueue = timeInQueue;
        }
    }

    /**
     * Returns the service time of the client.
     * @return the service time.
     */
    public int getServiceTime() {
        int tempService = 0;
        if(requests == null){return 0;}
        for(Request request : requests)
        {
            if(request != null){tempService += request.calculateProcessingTime();}
            else{return 0;}
            //System.out.println("Client " + this.firstName + " has service Time: " + tempService);
        }
        this.serviceTime = tempService;
        return tempService;
    }

    /**
     * Sets the service time of the client.
     * @param serviceTime
     */
    public void setServiceTime(int serviceTime) {
        if (serviceTime >= 0) {
            this.serviceTime = serviceTime;
        }
    }

    /**
     * Returns the departure time of the client.
     * @return the departure time.
     */
    public int getDepartureTime() {
        return departureTime;
    }

    /**
     * Sets the departure time of the client.
     * @param departureTime
     */
    public void setDepartureTime(int departureTime) {
        if (departureTime == 0 || departureTime >= arrivalTime + waitingTime + timeInQueue)
        {
            this.departureTime = departureTime+1;
        }
        else{
            this.departureTime = departureTime;}
    }

    /**
     * Returns the patience of the client.
     * @return the patience.
     */
    public int getPatience() {
        return patience;
    }

    /**
     * Sets the patience of the client.
     * @param patience
     */
    public void setPatience(int patience) {
        this.patience = patience;
    }

    /**
     * Returns the queue the client is in.
     * @return the queue.
     */
    public int estimateServiceLevel() {
        int serviceLevel = 10;
        if (departureTime == 0) {
            return -1; // Client is still in the system
        }
    
        if (waitingTime > 4) {
            serviceLevel--;
        }
        if (waitingTime > 6) {
            serviceLevel--;
        }
        if (waitingTime > 8) {
            serviceLevel--;
        }
        if (waitingTime > 10) {
            serviceLevel--;
        }
        if (waitingTime > 12) {
            serviceLevel--;
        }
        if (timeInQueue > 4) {
            serviceLevel--;
        }
        if (timeInQueue > 6) {
            serviceLevel--;
        }
        if (timeInQueue > 8) {
            serviceLevel--;
        }
        if (timeInQueue > 10) {
            serviceLevel--;
        }
        if (timeInQueue > 12) {
            serviceLevel--;
        }

        return serviceLevel;
    }

    /**
     * Returns the queue the client is in.
     * @return the queue.
     */
    public void setQueueJoined(Queue queueJoined) {
        this.queueJoined = queueJoined;
    }
    
    /**
     * Resets the clients ID
     */
    public void resetID()
    {
        currID = 1;
    }

    /**
     * Returns the string representation of the client.
     * @return the string representation.
     */
    @Override
    public String toString() {
        String serverName = "None";
        if(queueJoined != null)
        {
            serverName = queueJoined.getServerName();
        }
        String clientInfo = 
        "Client: " + lastName + ", " + firstName + "\n" +
        "** Arrival Time : " + arrivalTime + "\n" +
        "** Waiting Time : " + waitingTime + "\n" +
        "** Time In Queue : " + timeInQueue + "\n" +
        "** Service Time : " + serviceTime + "\n" +
        "** Departure Time : " + departureTime + "\n" +
        "** Served By Server: " + serverName + "\n" +
        "** Service Level : " + estimateServiceLevel();
        return clientInfo;
    }
}
