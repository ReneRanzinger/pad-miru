package com.github.reneranzinger.pad.miru.persist;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.github.reneranzinger.pad.miru.om.MonsterEntry;
import com.github.reneranzinger.pad.miru.om.Skill;

/**
 * Class that serves as persistent layer for the SQLite database files.
 *
 */
public class DBInterface
{
    public static final String DEFAULT_DATABASE = "monster_book.sqlite3";
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

    public void insertSkill(Skill a_skill) throws SQLException
    {
        PreparedStatement t_statement = this.m_connection.prepareStatement(
                "INSERT INTO skill (skill_id, name, description, min_turn, max_turn, max_level, skill_part_one, skill_part_two, skill_part_three, skill_type) VALUES (?,?,?,?,?,?,?,?,?,?);");
        t_statement.setInt(1, a_skill.getId());
        t_statement.setString(2, a_skill.getName());
        if (a_skill.getDescription() == null)
        {
            t_statement.setNull(3, Types.VARCHAR);
        }
        else
        {
            t_statement.setString(3, a_skill.getDescription());
        }
        if (a_skill.getMinTurns() == null)
        {
            t_statement.setNull(4, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(4, a_skill.getMinTurns());
        }
        if (a_skill.getMaxTurns() == null)
        {
            t_statement.setNull(5, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(5, a_skill.getMaxTurns());
        }
        if (a_skill.getMaxLevel() == null)
        {
            t_statement.setNull(6, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(6, a_skill.getMaxLevel());
        }
        if (a_skill.getPart1() == null)
        {
            t_statement.setNull(7, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(7, a_skill.getPart1());
        }
        if (a_skill.getPart2() == null)
        {
            t_statement.setNull(8, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(8, a_skill.getPart2());
        }
        if (a_skill.getPart3() == null)
        {
            t_statement.setNull(9, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(9, a_skill.getPart3());
        }
        t_statement.setInt(10, a_skill.getType());
        t_statement.execute();
        t_statement.close();
    }

    public void insertMonster(MonsterEntry a_entry) throws SQLException
    {
        PreparedStatement t_statement = this.m_connection.prepareStatement(
                "INSERT INTO monster ( monster_id, name, rarity, mpoints, limitbreak, hp, atk, rcv, leader_skill_id, "
                        + "skill_id, primary_attribute, secondary_attribute, ancestor, max_level, "
                        + "evo_mat_1, evo_mat_2, evo_mat_3, evo_mat_4, evo_mat_5 ) "
                        + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );");
        t_statement.setInt(1, a_entry.getId());
        t_statement.setString(2, a_entry.getName());
        t_statement.setInt(3, a_entry.getRarity());
        t_statement.setInt(4, a_entry.getmPoints());
        if (a_entry.getLimitBreaks() == null)
        {
            t_statement.setNull(5, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(5, a_entry.getLimitBreaks());
        }
        t_statement.setInt(6, a_entry.getHp());
        t_statement.setInt(7, a_entry.getAtk());
        t_statement.setInt(8, a_entry.getRcv());
        if (a_entry.getLeaderSkill() == null)
        {
            t_statement.setNull(9, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(9, a_entry.getLeaderSkill());
        }
        if (a_entry.getSkill() == null)
        {
            t_statement.setNull(10, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(10, a_entry.getSkill());
        }
        t_statement.setInt(11, a_entry.getPrimaryAttribute());
        if (a_entry.getSecondaryAttribute() == null)
        {
            t_statement.setInt(12, -1);
        }
        else
        {
            t_statement.setInt(12, a_entry.getSecondaryAttribute());
        }
        if (a_entry.getAncestor() == null)
        {
            t_statement.setNull(13, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(13, a_entry.getAncestor());
        }
        t_statement.setInt(14, a_entry.getMaxLevel());
        if (a_entry.getEvoMaterial1() == null)
        {
            t_statement.setNull(15, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(15, a_entry.getEvoMaterial1());
        }
        if (a_entry.getEvoMaterial2() == null)
        {
            t_statement.setNull(16, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(16, a_entry.getEvoMaterial1());
        }
        if (a_entry.getEvoMaterial3() == null)
        {
            t_statement.setNull(17, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(17, a_entry.getEvoMaterial1());
        }
        if (a_entry.getEvoMaterial4() == null)
        {
            t_statement.setNull(18, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(18, a_entry.getEvoMaterial1());
        }
        if (a_entry.getEvoMaterial5() == null)
        {
            t_statement.setNull(19, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(19, a_entry.getEvoMaterial1());
        }
        t_statement.execute();
        t_statement.close();
        this.insertMonsterTypes(a_entry);
        this.insertMonsterAwokenSkill(a_entry);
        this.insertMonsterSuperAwokenSkill(a_entry);
    }

    private void insertMonsterTypes(MonsterEntry a_entry) throws SQLException
    {
        for (Integer t_type : a_entry.getType())
        {
            PreparedStatement t_statement = this.m_connection.prepareStatement(
                    "INSERT INTO monster_has_type  (monster_id, monster_type_id) VALUES (?,?);");
            t_statement.setInt(1, a_entry.getId());
            t_statement.setInt(2, t_type);
            t_statement.execute();
            t_statement.close();
        }
    }

    private void insertMonsterAwokenSkill(MonsterEntry a_entry) throws SQLException
    {
        Integer t_position = 0;
        for (Integer t_skill : a_entry.getAwokenSkills())
        {
            t_position++;
            PreparedStatement t_statement = this.m_connection.prepareStatement(
                    "INSERT INTO monster_has_awoken_skill (monster_id, awoken_skill_id, position) VALUES (?,?,?);");
            t_statement.setInt(1, a_entry.getId());
            t_statement.setInt(2, t_skill);
            t_statement.setInt(3, t_position);
            t_statement.execute();
            t_statement.close();
        }
    }

    private void insertMonsterSuperAwokenSkill(MonsterEntry a_entry) throws SQLException
    {
        Integer t_position = 0;
        for (Integer t_skill : a_entry.getSuperAwokenSkill())
        {
            t_position++;
            PreparedStatement t_statement = this.m_connection.prepareStatement(
                    "INSERT INTO monster_has_super_awoken_skill (monster_id, awoken_skill_id, position) VALUES (?,?,?);");
            t_statement.setInt(1, a_entry.getId());
            t_statement.setInt(2, t_skill);
            t_statement.setInt(3, t_position);
            t_statement.execute();
            t_statement.close();
        }
    }

    public static String copyDatabase(String a_sourceFile, String a_targetFolder,
            Boolean a_timeStampDatabase) throws IOException
    {
        File t_databaseTemplate = new File(a_sourceFile);
        // create the new file with a timestamp
        String t_newFile = DBInterface.DEFAULT_DATABASE;
        if (a_timeStampDatabase)
        {
            Date t_date = Calendar.getInstance().getTime();
            DateFormat t_dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String t_strDate = t_dateFormat.format(t_date);
            t_newFile = "monster_book." + t_strDate + ".sqlite3";
        }
        File t_database = new File(a_targetFolder + t_newFile);
        // delete the file if its already exists
        if (t_database.exists())
        {
            t_database.delete();
        }
        // copy the template
        Files.copy(t_databaseTemplate.toPath(), t_database.toPath());
        // return new file name
        return t_newFile;
    }

    public HashMap<Integer, Boolean> getAwakenings() throws SQLException
    {
        HashMap<Integer, Boolean> t_result = new HashMap<>();
        PreparedStatement t_statement = this.m_connection
                .prepareStatement("SELECT awoken_skill_id FROM awoken_skill");
        ResultSet t_set = t_statement.executeQuery();
        while (t_set.next())
        {
            t_result.put(t_set.getInt("awoken_skill_id"), Boolean.TRUE);
        }
        return t_result;
    }

    public HashMap<Integer, Boolean> getTypes() throws SQLException
    {
        HashMap<Integer, Boolean> t_result = new HashMap<>();
        PreparedStatement t_statement = this.m_connection
                .prepareStatement("SELECT monster_type_id FROM monster_type");
        ResultSet t_set = t_statement.executeQuery();
        while (t_set.next())
        {
            t_result.put(t_set.getInt("monster_type_id"), Boolean.TRUE);
        }
        return t_result;
    }

    public List<Integer> getCardIds() throws SQLException
    {
        List<Integer> t_result = new ArrayList<Integer>();
        PreparedStatement t_statement = this.m_connection
                .prepareStatement("SELECT monster_id FROM monster");
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
                .prepareStatement("SELECT awoken_skill_id FROM awoken_skill");
        ResultSet t_set = t_statement.executeQuery();
        while (t_set.next())
        {
            t_result.add(t_set.getInt("awoken_skill_id"));
        }
        return t_result;
    }
}
