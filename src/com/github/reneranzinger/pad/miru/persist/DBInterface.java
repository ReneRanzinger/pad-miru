package com.github.reneranzinger.pad.miru.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that serves as persistent layer for the SQLite database files.
 *
 */
public class DBInterface
{
    public static final String DEFAULT_DATABASE = "dadguide.sqlite";

    // connection object that can be used to generate statements
    private Connection m_connection = null;

    /**
     * Constructor of the interface. Needs the filename/path of the database
     * file.
     *
     * @param a_databaseFilePath
     *            Filename and path of the database file to be opened
     * @throws ClassNotFoundException
     *             thrown if the database class can not be found
     * @throws SQLException
     *             thrown if the database connection can not be created
     */
    public DBInterface(String a_databaseFilePath) throws ClassNotFoundException, SQLException
    {
        // load driver into memory so it can be found
        Class.forName("org.sqlite.JDBC");
        // database URL
        String t_url = "jdbc:sqlite:" + a_databaseFilePath;
        // create a connection to the database
        this.m_connection = DriverManager.getConnection(t_url);
        // set the connection to support cascade foreign keys
        Statement t_statement = this.m_connection.createStatement();
        t_statement.execute("PRAGMA foreign_keys = ON");
    }

    /**
     * Close the database connection.
     *
     * @throws SQLException
     *             thrown if the database connection can not be closed
     */
    public void close() throws SQLException
    {
        // check if a connection object exists
        if (this.m_connection != null)
        {
            // close the connection
            this.m_connection.close();
        }
    }

    public List<Integer> getMonsterIds() throws SQLException
    {
        List<Integer> t_result = new ArrayList<Integer>();
        PreparedStatement t_statement = this.m_connection
                .prepareStatement("SELECT monster_id FROM monsters");
        ResultSet t_set = t_statement.executeQuery();
        while (t_set.next())
        {
            t_result.add(t_set.getInt("monster_id"));
        }
        return t_result;
    }

    public List<Integer> getAwokenSkills() throws SQLException
    {
        List<Integer> t_result = new ArrayList<Integer>();
        PreparedStatement t_statement = this.m_connection
                .prepareStatement("SELECT awoken_skill_id FROM awoken_skills");
        ResultSet t_set = t_statement.executeQuery();
        while (t_set.next())
        {
            t_result.add(t_set.getInt("awoken_skill_id"));
        }
        return t_result;
    }
}
