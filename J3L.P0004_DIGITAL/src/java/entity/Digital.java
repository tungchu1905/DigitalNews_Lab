/*
 * Copyright(C) 2005,  FPT University.
 * J3.L.P0004:
 * Digital News
 *
 * Record of change:
 * DATE          Version             AUTHOR             DESCRIPTION
 * 2021-05-16     1.0              TungCTHE141487      First Implement
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class object of Digital News, all properties have <code>get</code>
 * <code>set</code> method
 *
 * @author TungCTHE141487
 */
public class Digital {

    /**
     * Information of id
     */
    private int id;
    /**
     * Information of title
     */
    private String title;
    /**
     * Information of description
     */
    private String description;
    /**
     * Information of image
     */
    private String image;
    /**
     * Information of author
     */
    private String author;
    /**
     * Information of time post
     */
    private Date timePost;
    /**
     * Information of short description
     */
    private String shortDes;

    /**
     * Constructor of Digital
     */
    public Digital() {
    }

    /**
     * Class constructor specifying id, title, description, image,author,
     * timePost and shortDes of Digital objects
     *
     *
     * @param id the id of a Digital object, it is an int
     * @param title the title of Digital object, it is a string
     * @param description the description of Digital object, it is a string
     * @param image the image of Digital object, it is a string
     * @param author the author of Digital object, it is a string
     * @param timePost the time of Digital object, it is a
     * <code>java.util.Date</code>
     * @param shortDes the short description of Digital object, it is a string
     */
    public Digital(int id, String title, String description, String image, String author, Date timePost, String shortDes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.author = author;
        this.timePost = timePost;
        this.shortDes = shortDes;
    }

    /**
     * Get value of id attribute of Digital object
     *
     * @return id of Digital object it is an int
     */
    public int getId() {
        return id;
    }

    /**
     * Set value of id attribute of Digital object
     *
     * @param id is the id of Digital, it is an int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get value of title attribute of Digital object
     *
     * @return title of Digital object, it is a string
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set value of tittle attribute of Digital object
     *
     * @param title is the title of Digital, it is string
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get value of description attribute of Digital object
     *
     * @return description of Digital object it is a string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set value of description attribute of Digital object
     *
     * @param description is the description of Digital, it is string
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get value of image attribute of Digital object
     *
     * @return image of Digital object it is a string
     */
    public String getImage() {
        return image;
    }

    /**
     * Set value of image attribute of Digital object
     *
     * @param image is the image of Digital, it is string
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Get value of author attribute of Digital object
     *
     * @return author of Digital object it is a string
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set value of author attribute of Digital object
     *
     * @param author is the author of Digital, it is string
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Get value of timePost attribute of Digital object
     *
     * @return timePost of Digital object it is a date object
     */
    public Date getTimePost() {
        return timePost;
    }

    /**
     * Set value of timePost attribute of Digital object
     *
     * @param timePost is the timePost of Digital, it is Date
     */
    public void setTimePost(Date timePost) {
        this.timePost = timePost;
    }

    /**
     * Get value of shortDes attribute of Digital object
     *
     * @return shortDes of Digital object it is a string
     */
    public String getShortDes() {
        return shortDes;
    }

    /**
     * Set value of shortDes attribute of Digital object
     *
     * @param shortDes is the shortDes of Digital, it is string
     */
    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    /**
     * This method change date of timePost to right format in a
     * <code>java.text.SimpleDateFormat</code>
     *
     * @return date formated
     */
    public String getDateConvert() {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMM dd yyyy - hh:mm");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("a");
        String date = dateFormat1.format(this.timePost) + dateFormat2.format(this.timePost).toLowerCase();
        return date;
    }
}
