
public class InformationRequest extends Request {
    private String[] questions;

    /**
     * Creates a new QueueSystem object.
     *
     * @param description descreption of the request.
     * @param priority    priority of the request.
     * @param difficulty  difficulty of the request.
     */
    public InformationRequest(String description, int priority, int difficulty, String[] questions) {
        this.setDescription(description);
        this.setPriority(priority);
        this.setDifficulty(difficulty);
        this.setFactor(1);
        this.setStatus(Status.NEW);
        this.questions = questions;
    }

    /**
     * Returns a string representation of the information request.
     * @return a string representation of the information request.
     */
    public String[] getQuestions() {return questions;}

    /**
     * Sets the questions.
     * @param questions the questions.
     */
    public void setQuestions(String[] questions) {this.questions = questions;}

    /**
     * Calculates the amount of time required to process the information request.
     * @return the amount of time required to process the information request.
     */
    @Override
    public int calculateProcessingTime()
    {
        return this.getDifficulty() * this.getFactor() * this.getQuestions().length;
    }
}
