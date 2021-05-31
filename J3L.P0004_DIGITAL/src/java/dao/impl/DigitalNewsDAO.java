/*
 * Copyright(C) 2005,  FPT University.
 * J3.L.P0004:
 * Digital News
 *
 * Record of change:
 * DATE          Version             AUTHOR             DESCRIPTION
 * 2021-05-16     1.0              TungCTHE141487      First Implement
 */
package dao.impl;

import context.DBContext;
import dao.IDigitalDAO;
import entity.Digital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * The class implement from IDigitalDAO contains method select information from
 * Digital table in database.
 *
 * @author TungCTHE141487
 */
public class DigitalNewsDAO extends DBContext implements IDigitalDAO {

    /**
     * Find the top 1 of digital news by id. The object will appear with the
     * same id it have.
     *
     * @param id is the id of Digital object, it is an int
     * @return a <code>Digital</code>, it is an object
     * @throws java.lang.Exception
     */
    @Override
    public Digital getNews(int id) throws Exception {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String s = "select * from digital where id = ?";
        // check connection of db if cannot connect, it will throw an object of Exception
        try {
            con = getConnection();
            ps = con.prepareCall(s);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("images"),
                        rs.getString("author"),
                        rs.getTimestamp("timePost"),
                        rs.getString("shortDes"));
                return d;
            }
        } catch (Exception e) {

        } finally {
            // close connection, ResultSet and PrepareStatement
            closeConnection(con);
            closePrepareStateMent(ps);
            closeResultSet(rs);
        }
        return null;
    }

    /**
     * Find list of digital news. All the digital news will card listed returned
     * the result contain a list of <code> entity.Digital
     * </code> objects with id, title, description, images, author,
     * timePost,shortDes
     *
     * @param txt is the name of title of Digital object, it is a
     * <code>java.lang.String</code>
     * @param pageIndex the name of a page index, it is an int
     * @param pageSize the name of a size of page, it is an int
     * @return a list of Digital object, it is a <code>java.util.List</code>
     * @throws java.lang.Exception
     */
    @Override
    public List<Digital> getSearch(String txt, int pageIndex, int pageSize) throws Exception {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Digital> list = new ArrayList<>();
        String query = "select *from("
                + "select ROW_NUMBER() over (order by ID ASC) as rn, *\n"
                + "from digital where title \n"
                + "like ?"
                + ")as x\n"
                + "where rn between ?*?-2"
                + "and ?*?";
        // check connection of db if cannot connect, it will throw an object of Exception
        try {
            con = getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, pageIndex);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageIndex);
            ps.setInt(5, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("images"),
                        rs.getString("author"),
                        rs.getTimestamp("timePost"),
                        rs.getString("shortDes"));
                list.add(d);
            }
        } catch (Exception e) {
        } finally {
            // close connection, ResultSet and PrepareStatement
            closeConnection(con);
            closePrepareStateMent(ps);
            closeResultSet(rs);
        }
        return list;
    }

    /**
     * Find number page of digital news.
     *
     * @param txt the name of title of Digital object, it is a
     * <code>java.lang.String</code>
     * @return a number of page
     * @throws java.lang.Exception
     */
    @Override
    public int count(String txt) throws Exception {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        // check connection of db if cannot connect, it will throw an object of Exception
        try {
            String query = "select count(*) from digital \n"
                    + "where title like ?";
            con = getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + txt + "%");
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            throw e;
        } finally {
            // close connection, ResultSet and PrepareStatement

            closeConnection(con);
            closePrepareStateMent(ps);
            closeResultSet(rs);
        }
    }

    /**
     * Find top of digital news. All the digital news will card listed returned
     * the result contain a list of <code> entity.Digital
     * </code> objects with id, title, description, images, author,
     * timePost,shortDes
     *
     * @return a list of Digital object, it is a <code>java.util.List</code>
     * @throws java.lang.Exception
     */
    @Override
    public List<Digital> getTop(int top) throws Exception {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Digital> list = new ArrayList<>();
        // check connection of db if cannot connect, it will throw an object of Exception
        try {

            String query = "select top " + top + " * from digital order by timePost desc";
            con = getConnection();
            ps = con.prepareCall(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("images"),
                        rs.getString("author"),
                        rs.getTimestamp("timePost"),
                        rs.getString("shortDes"));
                list.add(d);
            }

        } catch (Exception e) {
        } finally {
            // close connection, ResultSet and PrepareStatement
            closeConnection(con);
            closePrepareStateMent(ps);
            closeResultSet(rs);
        }
        return list;
    }

    /**
     * Check the exist id of Digital
     *
     * @param id is an id of Digital object, it is an int
     * @return true/ false of an id exist
     * @throws Exception
     */
    @Override
    public boolean checkExistId(int id) throws Exception {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String query = "select id from digital where id like ?";
            con = getConnection();
            ps = con.prepareCall(query);
            ps.setString(1, "%" + id + "%");
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count != 0;

        } catch (Exception e) {
        } finally {
            // close connection, ResultSet and PrepareStatement
            closeConnection(con);
            closePrepareStateMent(ps);
            closeResultSet(rs);
        }
        return false;
    }
}
