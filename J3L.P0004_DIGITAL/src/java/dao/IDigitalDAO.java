/*
 *Copyright(C) 2005,  FPT University.
 * J3.L.P0004:
 * Digital News
 *
 * Record of change:
 * DATE          Version             AUTHOR             DESCRIPTION
 * 2021-05-16     1.0              TungCTHE141487      First Implement
 */
package dao;

import entity.Digital;
import java.util.List;

/**
 * The interface of DigitalDAO, it is the class contains method get information
 * from Digital in database.
 *
 * @author TungCTHE141487
 */
public interface IDigitalDAO {

    /**
     * Find the one of digital news by id. The object will appear with the same
     * id it have.
     *
     * @param id is the id of Digital object, it is an int
     * @return a <code>Digital</code> object, it is an object
     * @throws Exception
     */
    public Digital getNews(int id) throws Exception;

    /**
     * Find list of digital news. All the digital news will card listed returned
     * the result contain a list of <code> entity.Digital
     * </code> objects with id, title, description, images, author,
     * timePost,shortDes
     *
     * @param txt the name of a title it is a <code>java.lang.String</code>
     * object
     * @param pageIndex the name of a page index, it is an int
     * @param pageSize the name of a size of page, it is an int
     * @return a list of Digital objects it is a <code>java.util.List</code>
     * @throws Exception
     */
    public List<Digital> getSearch(String txt, int pageIndex, int pageSize) throws Exception;

    /**
     * Find number page of digital news.
     *
     * @param txt the name of title of Digital, it is a
     * <code>java.lang.String</code> object
     * @return a number of page
     * @throws Exception
     */
    public int count(String txt) throws Exception;

    /**
     * Find top of digital news. All the digital news will card listed returned
     * the result contain a list of <code> entity.Digital
     * </code> objects with id, title, description, images, author,
     * timePost,shortDes
     *
     * @param top is a number it is an int
     * @return a list of Digital objects it is a <code>java.util.List</code>
     * @throws Exception
     */
    public List<Digital> getTop(int top) throws Exception;

    /**
     * Check the exist id of Digital
     *
     * @param id the id of Digital object, it is an int
     * @return true/false of an id exist
     * @throws Exception
     */
    public boolean checkExistId(int id) throws Exception;
}
