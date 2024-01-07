public class VIPQueue extends Queue {

    /**
     * Constructor for objects of class VIPQueue
     */
    public VIPQueue(String serverName, int queueSize) {
        super(serverName, queueSize);
    }

    /**
     * Returns true if the client can be accepted into the queue.
     * @param client the client to check
     * @return true if the client can be accepted into the queue
     */
    public boolean canAcceptClient(Client client) {
        return client instanceof VIPClient;
    }
}
