package model;

public class Tweet {

    private int id;
    private String message;
    private String tweetDate;
    private int userId;

    public Tweet(){

    }

    //Method to write a tweet
    public Tweet(String message, int userId){
        this.message = message;
        this.userId = userId;

    }


    

   /**
    * 
    * @return int return the id of user
    */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the message
     * 
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return String return the tweetDate
     */
    public String getTweetDate() {
        return tweetDate;
    }

    /**
     * @param tweetDate the tweetDate to set
     */
    public void setTweetDate(String tweetDate) {
        this.tweetDate = tweetDate;
    }

    /**
     * @return int return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

}
