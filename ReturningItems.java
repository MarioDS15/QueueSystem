public class ReturningItems extends Request {
    private String[] itemsToReturn;

    /**
     * Creates a new ReturningItems request.
     *
     * @param description descreption of the request.
     * @param priority    priority of the request.
     * @param difficulty  difficulty of the request.
     */
    public ReturningItems(String description, int priority, int difficulty, String[] itemsToReturn) {
        this.setDescription(description);
        this.setPriority(priority);
        this.setDifficulty(difficulty);
        this.setFactor(3);
        this.setStatus(Status.NEW);
        this.itemsToReturn = itemsToReturn;
    }

    /**
     * Returns a string representation of the questions items request.
     * @return a string representation of the questions items request.
     */
    public String[] getQuestions() {return itemsToReturn;}

    /**
     * Sets the questions.
     * @param questions the questions.
     */
    public void setQuestions(String[] itemsToReturn) {this.itemsToReturn = itemsToReturn;}

    /**
     * Calculates the amount of time required to process the questions items request.
     * @return the amount of time required to process the questions items request.
     */
    @Override
    public int calculateProcessingTime()
    {
        return this.getDifficulty() * this.getFactor() * this.itemsToReturn.length;
    }
}


