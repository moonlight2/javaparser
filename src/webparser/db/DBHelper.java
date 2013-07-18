
package webparser.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import webparser.config.Config;
import webparser.entity.Page;
import webparser.entity.PageCollection;


/**
 * Class contains all the methods for working with database
 */
public class DBHelper {

    private Statement st;
    private Connection con;

    public DBHelper() throws Exception {
        Class.forName(Config.JDBC_DRIVER);
        con = DriverManager.getConnection("jdbc:" + Config.DB_TYPE + ":" + Config.DB_NAME, Config.DB_USER, Config.DB_PASS);
    }

    public static void main(String[] args) throws Exception {
        DBHelper d = new DBHelper();
        System.out.println(d.getPages());
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public void deleteLinks(String url) {
        try {
            st = con.createStatement();
            st.executeUpdate("DELETE FROM page WHERE page_name = '" + url + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateLinks(String url, List links) {
        
        try {
            st = con.createStatement();
            int id = getId(url);
            st.executeUpdate("DELETE FROM page_links WHERE page_id = '" + id + "'");

            PreparedStatement prep = con.prepareStatement("INSERT INTO page_links (page_id, link_name, nesting_level, external_links) "
                    + "VALUES (?,?,?,?)");
            int size = links.size();
            for (int x = 0; x < size; x++) {
                prep.setInt(1, id);
                prep.setString(2, (String) ((ArrayList) links.get(x)).get(0));
                prep.setInt(3, (Integer) ((ArrayList) links.get(x)).get(1));
                prep.setInt(4, (Integer) ((ArrayList) links.get(x)).get(2));
                prep.addBatch();
            }
            con.setAutoCommit(false);
            prep.executeBatch();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertLinks(String url, List links) {
        try {
            st = con.createStatement();
            st.executeUpdate("INSERT INTO page (page_name) VALUES ('" + url + "') ");
            int id = getId(url);

            PreparedStatement prep = con.prepareStatement("INSERT INTO page_links (page_id, link_name, nesting_level, external_links) "
                    + "VALUES (?,?,?,?)");
            int size = links.size();
            for (int x = 0; x < size; x++) {
                prep.setInt(1, id);
                prep.setString(2, (String) ((ArrayList) links.get(x)).get(0));
                prep.setInt(3, (Integer) ((ArrayList) links.get(x)).get(1));
                prep.setInt(4, (Integer) ((ArrayList) links.get(x)).get(2));
                prep.addBatch();
            }
            con.setAutoCommit(false);
            prep.executeBatch();
            con.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PageCollection getLinksByUrl(String url) {

        PageCollection links = new PageCollection();
        try {
            PreparedStatement stat = con.prepareStatement(
                    "SELECT "
                    + "page_links.link_name, page_links.nesting_level, page_links.external_links "
                    + "FROM page, page_links "
                    + "WHERE page.page_id = page_links.page_id "
                    + "AND page.page_name = '" + url + "' "
                    + "LIMIT 4000");
            ResultSet res = stat.executeQuery();
            while (res.next()) {
                Page link = new Page(res.getString(1));
                link.setLevel(res.getInt(2));
                link.setLinks(res.getInt(3));

                links.add(link);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return links;
    }

    private int getId(String url) {
        int id = 0;
        try {
            PreparedStatement stat = con.prepareStatement(
                    "SELECT page_id from page WHERE page_name = '" + url + "' LIMIT 1");
            ResultSet res = stat.executeQuery();
            while (res.next()) {
                id = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List getPages() {
        List pages = new ArrayList();
        try {
            PreparedStatement stat = con.prepareStatement("SELECT page_name FROM page LIMIT 50");
            ResultSet res = stat.executeQuery();
            while (res.next()) {
                pages.add(res.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pages;
    }
}