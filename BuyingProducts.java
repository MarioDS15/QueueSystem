public class BuyingProducts extends Request {
    private String[] itemsToBuy;

    /**
     * Creates a new QueueSystem object.
     *
     * @param description descreption of the request.
     * @param priority    priority of the request.
     * @param difficulty  difficulty of the request.
     */
    public BuyingProducts(String description, int priority, int difficulty, String[] itemsToBuy) {
        this.setDescription(description);
        this.setPriority(priority);
        this.setDifficulty(difficulty);
        this.setFactor(2);
        this.setStatus(Status.NEW);
        this.itemsToBuy = itemsToBuy;
    }

    /**
     * Returns a string representation of the buy request.
     * @return a string representation of the buy request.
     */
    public String[] getItemsToBuy() {return itemsToBuy;}

    /**
     * Sets the items to buy.
     * @param itemsToBuy the items to buy.
     */
    public void setItemsToBuy(String[] itemsToBuy) {this.itemsToBuy = itemsToBuy;}

    /**
     * Calculates the amount of time required to process the buy request.
     * @return the amount of time required to process the buy request.
     */
    @Override
    public int calculateProcessingTime()
    {
        return this.getDifficulty() * this.getFactor() * this.itemsToBuy.length;
    }
}
