package com.github.reneranzinger.pad.miru.persist;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.github.reneranzinger.pad.miru.om.AwokenSkill;
import com.github.reneranzinger.pad.miru.om.Image;
import com.github.reneranzinger.pad.miru.om.ImageType;
import com.github.reneranzinger.pad.miru.om.LeaderSkill;
import com.github.reneranzinger.pad.miru.om.MonsterEntry;
import com.github.reneranzinger.pad.miru.om.MonsterType;
import com.github.reneranzinger.pad.miru.om.Skill;

/**
 * Class that serves as persistent layer for the SQLite database files.
 *
 */
public class DBInterface
{
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

    /**
     * Get all Skills from the Database
     *
     * @return list of skills in the database
     * @throws SQLException
     *             thrown if there was a problem with the SQL query
     */
    public List<Skill> getSkills() throws SQLException
    {
        List<Skill> t_skillList = new ArrayList<>();
        // create statement and get all skills
        PreparedStatement t_statement = this.m_connection.prepareStatement("SELECT * FROM skill;");
        ResultSet t_resultSet = t_statement.executeQuery();
        // create a list with all skills
        while (t_resultSet.next())
        {
            // create skill and add to result list
            Skill t_skill = new Skill();
            t_skill.setId(t_resultSet.getInt("skill_id"));
            t_skill.setDescription(t_resultSet.getString("description"));
            t_skill.setMinTurns(t_resultSet.getInt("min_turn"));
            t_skill.setMaxTurns(t_resultSet.getInt("max_turn"));
            t_skill.setName(t_resultSet.getString("name"));
            t_skillList.add(t_skill);
        }
        // close result set and statement
        t_resultSet.close();
        t_statement.close();
        return t_skillList;
    }

    public void updateSkill(Skill a_skill) throws SQLException
    {
        PreparedStatement t_statement = this.m_connection.prepareStatement(
                "UPDATE skill SET name=?, description=?, min_turn=?, max_turn=? WHERE skill_id=?;");
        t_statement.setString(1, a_skill.getName());
        t_statement.setString(2, a_skill.getDescription());
        t_statement.setInt(3, a_skill.getMinTurns());
        t_statement.setInt(4, a_skill.getMaxTurns());
        t_statement.setInt(5, a_skill.getId());
        t_statement.execute();
        t_statement.close();
    }

    public void insertSkill(Skill a_skill) throws SQLException
    {
        PreparedStatement t_statement = this.m_connection.prepareStatement(
                "INSERT INTO skill (skill_id, name, description, min_turn, max_turn, max_level) VALUES (?,?,?,?,?,?);");
        t_statement.setInt(1, a_skill.getId());
        t_statement.setString(2, a_skill.getName());
        t_statement.setString(3, a_skill.getDescription());
        t_statement.setInt(4, a_skill.getMinTurns());
        t_statement.setInt(5, a_skill.getMaxTurns());
        t_statement.setInt(6, a_skill.getMaxLevel());
        t_statement.execute();
        t_statement.close();
    }

    public List<MonsterType> getMonsterTypes() throws SQLException
    {
        List<MonsterType> t_typeList = new ArrayList<>();
        // create statement and get all skills
        PreparedStatement t_statement = this.m_connection
                .prepareStatement("SELECT * FROM monster_type;");
        ResultSet t_resultSet = t_statement.executeQuery();
        // create a list with all skills
        while (t_resultSet.next())
        {
            // create skill and add to result list
            MonsterType t_type = new MonsterType();
            t_type.setId(t_resultSet.getInt("monster_type_id"));
            t_type.setName(t_resultSet.getString("name"));
            t_typeList.add(t_type);
        }
        // close result set and statement
        t_resultSet.close();
        t_statement.close();
        return t_typeList;
    }

    public Integer insertMonsterType(MonsterType a_type) throws SQLException
    {
        Integer t_resultID = null;
        PreparedStatement t_statement = this.m_connection
                .prepareStatement("INSERT INTO monster_type (name) VALUES (?);");
        t_statement.setString(1, a_type.getName());
        t_statement.execute();
        t_statement.close();
        // get the last ID
        t_statement = this.m_connection
                .prepareStatement("SELECT * FROM monster_type WHERE name=?;");
        t_statement.setString(1, a_type.getName());
        ResultSet t_resultSet = t_statement.executeQuery();
        while (t_resultSet.next())
        {
            t_resultID = t_resultSet.getInt("monster_type_id");
        }
        return t_resultID;
    }

    public List<LeaderSkill> getLeaderSkills() throws SQLException
    {
        List<LeaderSkill> t_skillList = new ArrayList<>();
        // create statement and get all skills
        PreparedStatement t_statement = this.m_connection
                .prepareStatement("SELECT * FROM leader_skill;");
        ResultSet t_resultSet = t_statement.executeQuery();
        // create a list with all skills
        while (t_resultSet.next())
        {
            // create skill and add to result list
            LeaderSkill t_skill = new LeaderSkill();
            t_skill.setId(t_resultSet.getInt("leader_skill_id"));
            t_skill.setDescription(t_resultSet.getString("description"));
            t_skill.setName(t_resultSet.getString("name"));
            t_skillList.add(t_skill);
        }
        // close result set and statement
        t_resultSet.close();
        t_statement.close();
        return t_skillList;
    }

    public void updateLeaderSkill(LeaderSkill a_skill) throws SQLException
    {
        PreparedStatement t_statement = this.m_connection.prepareStatement(
                "UPDATE leader_skill SET name=?, description=? WHERE Leader_skill_id=?;");
        t_statement.setString(1, a_skill.getName());
        t_statement.setString(2, a_skill.getDescription());
        t_statement.setInt(3, a_skill.getId());
        t_statement.execute();
        t_statement.close();
    }

    public void insertLeaderSkill(LeaderSkill a_skill) throws SQLException
    {
        PreparedStatement t_statement = this.m_connection.prepareStatement(
                "INSERT INTO leader_skill (leader_skill_id, name, description) VALUES (?,?,?);");
        t_statement.setInt(1, a_skill.getId());
        t_statement.setString(2, a_skill.getName());
        t_statement.setString(3, a_skill.getDescription());
        t_statement.execute();
        t_statement.close();
    }

    public List<AwokenSkill> getAwokenSkills() throws SQLException
    {
        List<AwokenSkill> t_skillList = new ArrayList<>();
        // create statement and get all skills
        PreparedStatement t_statement = this.m_connection
                .prepareStatement("SELECT * FROM awoken_skill;");
        ResultSet t_resultSet = t_statement.executeQuery();
        // create a list with all skills
        while (t_resultSet.next())
        {
            // create skill and add to result list
            AwokenSkill t_skill = new AwokenSkill();
            t_skill.setId(t_resultSet.getInt("awoken_skill_id"));
            t_skill.setDescription(t_resultSet.getString("description"));
            t_skill.setName(t_resultSet.getString("name"));
            t_skillList.add(t_skill);
        }
        // close result set and statement
        t_resultSet.close();
        t_statement.close();
        return t_skillList;
    }

    public void updateAwokenSkill(AwokenSkill a_skill) throws SQLException
    {
        PreparedStatement t_statement = this.m_connection.prepareStatement(
                "UPDATE awoken_skill SET name=?, description=? WHERE awoken_skill_id=?;");
        t_statement.setString(1, a_skill.getName());
        t_statement.setString(2, a_skill.getDescription());
        t_statement.setInt(3, a_skill.getId());
        t_statement.execute();
        t_statement.close();
    }

    public void insertAwokenSkill(AwokenSkill a_skill) throws SQLException
    {
        PreparedStatement t_statement = this.m_connection.prepareStatement(
                "INSERT INTO awoken_skill (awoken_skill_id, name, description) VALUES (?,?,?);");
        t_statement.setInt(1, a_skill.getId());
        t_statement.setString(2, a_skill.getName());
        t_statement.setString(3, a_skill.getDescription());
        t_statement.execute();
        t_statement.close();
    }

    public List<AwokenSkill> getSuperAwokenSkills() throws SQLException
    {
        List<AwokenSkill> t_skillList = new ArrayList<>();
        // create statement and get all skills
        PreparedStatement t_statement = this.m_connection
                .prepareStatement("SELECT * FROM super_awoken_skill;");
        ResultSet t_resultSet = t_statement.executeQuery();
        // create a list with all skills
        while (t_resultSet.next())
        {
            // create skill and add to result list
            AwokenSkill t_skill = new AwokenSkill();
            t_skill.setId(t_resultSet.getInt("super_awoken_skill_id"));
            t_skill.setDescription(t_resultSet.getString("description"));
            t_skill.setName(t_resultSet.getString("name"));
            t_skillList.add(t_skill);
        }
        // close result set and statement
        t_resultSet.close();
        t_statement.close();
        return t_skillList;
    }

    public void updateSuperAwokenSkill(AwokenSkill a_skill) throws SQLException
    {
        PreparedStatement t_statement = this.m_connection.prepareStatement(
                "UPDATE super_awoken_skill SET name=?, description=? WHERE super_awoken_skill_id=?;");
        t_statement.setString(1, a_skill.getName());
        t_statement.setString(2, a_skill.getDescription());
        t_statement.setInt(3, a_skill.getId());
        t_statement.execute();
        t_statement.close();
    }

    public void insertSuperAwokenSkill(AwokenSkill a_skill) throws SQLException
    {
        PreparedStatement t_statement = this.m_connection.prepareStatement(
                "INSERT INTO super_awoken_skill (super_awoken_skill_id, name, description) VALUES (?,?,?);");
        t_statement.setInt(1, a_skill.getId());
        t_statement.setString(2, a_skill.getName());
        t_statement.setString(3, a_skill.getDescription());
        t_statement.execute();
        t_statement.close();
    }

    public List<Image> getImages(ImageType a_type) throws SQLException
    {
        List<Image> t_imageList = new ArrayList<>();
        // create statement and get all skills
        PreparedStatement t_statement = this.m_connection
                .prepareStatement("SELECT * FROM image WHERE type=?;");
        t_statement.setString(1, a_type.getType());
        ResultSet t_resultSet = t_statement.executeQuery();
        // create a list with all skills
        while (t_resultSet.next())
        {
            // create skill and add to result list
            Image t_image = new Image();
            t_image.setId(t_resultSet.getInt("image_id"));
            t_image.setImage(t_resultSet.getBytes("image"));
            t_image.setFileExtension(t_resultSet.getString("image_type"));
            t_imageList.add(t_image);
        }
        // close result set and statement
        t_resultSet.close();
        t_statement.close();
        return t_imageList;
    }

    public void updateImage(Image a_image, ImageType a_type, Integer a_id) throws SQLException
    {
        PreparedStatement t_statement = this.m_connection.prepareStatement(
                "UPDATE image SET image=?, image_type=? WHERE type_id=? AND type=?;");
        t_statement.setBytes(1, a_image.getImage());
        t_statement.setString(2, a_image.getFileExtension());
        t_statement.setString(3, a_type.getType());
        t_statement.setInt(4, a_id);
        t_statement.execute();
        t_statement.close();
    }

    public void insertImage(Image a_image, ImageType a_type, Integer a_id) throws SQLException
    {
        PreparedStatement t_statement = this.m_connection.prepareStatement(
                "INSERT INTO image (type_id, type, image, image_type) VALUES (?,?,?,?);");
        t_statement.setInt(1, a_id);
        t_statement.setString(2, a_type.getType());
        t_statement.setBytes(3, a_image.getImage());
        t_statement.setString(4, a_image.getFileExtension());
        t_statement.execute();
        t_statement.close();
    }

    public List<Integer> getMonsters() throws SQLException
    {
        List<Integer> t_monsterList = new ArrayList<>();
        // create statement and get all skills
        PreparedStatement t_statement = this.m_connection
                .prepareStatement("SELECT monster_id FROM monster;");
        ResultSet t_resultSet = t_statement.executeQuery();
        // create a list with all skills
        while (t_resultSet.next())
        {
            // create skill and add to result list
            t_monsterList.add(t_resultSet.getInt("monster_id"));
        }
        // close result set and statement
        t_resultSet.close();
        t_statement.close();
        return t_monsterList;
    }

    public void insertMonster(MonsterEntry a_entry) throws SQLException
    {
        PreparedStatement t_statement = this.m_connection.prepareStatement(
                "INSERT INTO monster ( monster_id, name, rarity, mpoints, limitbreak, hp, atk, rcv, assist, leader_skill_id, skill_id, primary_attribute, secondary_attribute ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );");
        t_statement.setInt(1, a_entry.getId());
        t_statement.setString(2, a_entry.getName());
        t_statement.setInt(3, a_entry.getRarity());
        t_statement.setInt(4, a_entry.getmPoints());
        t_statement.setBoolean(5, a_entry.isLimitBreaks());
        t_statement.setInt(6, a_entry.getHp());
        t_statement.setInt(7, a_entry.getAtk());
        t_statement.setInt(8, a_entry.getRcv());
        t_statement.setBoolean(9, a_entry.isAssist());
        if (a_entry.getLeaderSkill() == null)
        {
            t_statement.setNull(10, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(10, a_entry.getLeaderSkill().getId());
        }
        if (a_entry.getSkill() == null)
        {
            t_statement.setNull(11, Types.INTEGER);
        }
        else
        {
            t_statement.setInt(11, a_entry.getSkill().getId());
        }
        t_statement.setString(12, a_entry.getPrimaryAttribute().getInternalName());
        if (a_entry.getSecondaryAttribute() == null)
        {
            t_statement.setNull(13, Types.VARCHAR);
        }
        else
        {
            t_statement.setString(13, a_entry.getSecondaryAttribute().getInternalName());
        }
        t_statement.execute();
        t_statement.close();
        this.insertMonsterTypes(a_entry);
        this.insertMonsterAwokenSkill(a_entry);
        this.insertMonsterSuperAwokenSkill(a_entry);
    }

    private void insertMonsterTypes(MonsterEntry a_entry) throws SQLException
    {
        for (MonsterType t_type : a_entry.getType())
        {
            PreparedStatement t_statement = this.m_connection.prepareStatement(
                    "INSERT INTO monster_has_type  (monster_id, monster_type_id) VALUES (?,?);");
            t_statement.setInt(1, a_entry.getId());
            t_statement.setInt(2, t_type.getId());
            t_statement.execute();
            t_statement.close();
        }
    }

    private void insertMonsterAwokenSkill(MonsterEntry a_entry) throws SQLException
    {
        Integer t_position = 0;
        for (AwokenSkill t_skill : a_entry.getAwokenSkills())
        {
            t_position++;
            PreparedStatement t_statement = this.m_connection.prepareStatement(
                    "INSERT INTO monster_has_awoken_skill (monster_id, awoken_skill_id, position) VALUES (?,?,?);");
            t_statement.setInt(1, a_entry.getId());
            t_statement.setInt(2, t_skill.getId());
            t_statement.setInt(3, t_position);
            t_statement.execute();
            t_statement.close();
        }
    }

    private void insertMonsterSuperAwokenSkill(MonsterEntry a_entry) throws SQLException
    {
        Integer t_position = 0;
        for (AwokenSkill t_skill : a_entry.getSuperAwokenSkill())
        {
            t_position++;
            PreparedStatement t_statement = this.m_connection.prepareStatement(
                    "INSERT INTO monster_has_super_awoken_skill (monster_id, super_awoken_skill_id, position) VALUES (?,?,?);");
            t_statement.setInt(1, a_entry.getId());
            t_statement.setInt(2, t_skill.getId());
            t_statement.setInt(3, t_position);
            t_statement.execute();
            t_statement.close();
        }
    }

    public static String copyDatabase(String a_sourceFile, String a_targetFolder) throws IOException
    {
        File t_databaseTemplate = new File(a_sourceFile);
        // create the new file with a timestamp
        Date t_date = Calendar.getInstance().getTime();
        DateFormat t_dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String t_strDate = t_dateFormat.format(t_date);
        String t_newFile = "pad." + t_strDate + ".sqlite3";
        File t_database = new File(a_targetFolder + t_newFile);
        // copy the template
        Files.copy(t_databaseTemplate.toPath(), t_database.toPath());
        // return new file name
        return t_newFile;
    }
}
