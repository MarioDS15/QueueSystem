public abstract class Request implements Prioritizable{
    private String description;
    private int priority;
    private int difficulty;
    private int factor;
    private int startTime;
    private int endTime;
    private Status status;


    /**
     * Gets the descreption of the request.
     * @return the descreption of the request.
     */
    public String getDescription() {return description;}

    /**
     * Sets the descreption of the request.
     * @param description the descreption of the request.
     */
    public void setDescription(String description) {this.description = description;}

    /**
     * Gets the priority of the request.
     * @return the priority of the request.
     */
    public int getPriority() {return priority;}

    /**
     * Sets the priority of the request.
     * @param priority the priority of the request.
     */
    public void setPriority(int priority) {this.priority = priority;}

    /**
     * Gets the difficulty of the request.
     * @return the difficulty of the request.
     */
    public int getDifficulty() {return difficulty;}

    /**
     * Sets the difficulty of the request.
     * @param difficulty the difficulty of the request.
     */
    public void setDifficulty(int difficulty) {this.difficulty = difficulty;}

    /**
     * Gets the factor of the request.
     * @return the factor of the request.
     */
    public int getFactor() {return factor;}

    /**
     * Sets the factor of the request.
     * @param factor the factor of the request.
     */
    public void setFactor(int factor) {this.factor = factor;}

    /**
     * Gets the start time of the request.
     * @return the start time of the request.
     */
    public int getStartTime() {return startTime;}

    /**
     * Sets the start time of the request.
     * @param startTime the start time of the request.
     */
    public void setStartTime(int startTime) {this.startTime = startTime;}

    /**
     * Gets the end time of the request.
     * @return the end time of the request.
     */
    public int getEndTime() {return endTime;}

    /**
     * Sets the end time of the request.
     * @param endTime the end time of the request.
     */
    public void setEndTime(int endTime) {this.endTime = endTime;}

    /**
     * Gets the status of the request.
     * @return the status of the request.
     */
    public Status getStatus() {return status;}

    /**
     * Sets the status of the request.
     * @param status the status of the request.
     */
    public void setStatus(Status status) {this.status = status;}

    /**
     * Calculates the amount of time required to process the request.
     * @return the amount of time required to process the request.
     */
    public abstract int calculateProcessingTime(); // Not done
}
