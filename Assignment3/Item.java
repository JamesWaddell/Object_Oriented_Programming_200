/**
 * An abstract class representing a library item
 * DO NOT SUBMIT THIS FILE
 */
public abstract class Item {
    /**
     * call number
     */
    private String callNumber;

    /**
     * title of the item
     */
    private String title;

    /**
     * Getter of callNumber
     *
     * @return call number
     */
    public String getCallNumber() {
        return callNumber;
    }

    /**
     * Getter of title
     *
     * @return title of the item
     */
    public String getTitle() {
        return title;
    }

    /**
     * To be implemented by derived classes
     */
    @Override
    public abstract String toString();

    /**
     * Constructor - to be called by derived classes
     *
     * @param callNumber the call number
     * @param title      the title
     */
    public Item(String callNumber, String title) {
        this.callNumber = callNumber;
        this.title = title;
    }

}
