public class VIPClient extends Client implements Prioritizable{
    private int priority;
    private int memberSince;

    /**
     * Creates a new VIPClient object.
     * @param firstName
     * @param lastName
     * @param birthYear
     * @param gender
     * @param arrivalTime
     * @param patience
     * @param requests
     * @param memberSince
     * @param priority
     */
    public VIPClient(String firstName, String lastName, int birthYear, String gender, 
    int arrivalTime, int patience, Request[] requests, int memberSince, int priority) {
        super(firstName, lastName, birthYear, gender, arrivalTime, patience, requests);
        this.priority = priority;
        this.memberSince = memberSince;
    }

    /**
     * Gets the clients priority.
     * @return the clients priority.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the clients priority.
     * @param priority the clients priority.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Gets the clients member since.
     * @return the clients member since.
     */
    public int getMemberSince() {
        return memberSince;
    }

    /**
     * Sets the clients member since.
     * @param memberSince the clients member since.
     */
    public void setMemberSince(int memberSince) {
        this.memberSince = memberSince;
    }

    /**
     * Returns a string representation of the VIP client.
     * @return a string representation of the VIP client.
     */
    @Override
    public String toString() 
    {
        String clientInfo = super.toString()+"\n** Member Since : " + memberSince +
        "\n** Priority : " + priority;
    
        return clientInfo;
    }
}
