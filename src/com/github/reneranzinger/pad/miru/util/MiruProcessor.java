package com.github.reneranzinger.pad.miru.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.github.reneranzinger.pad.miru.om.Bonus;
import com.github.reneranzinger.pad.miru.om.Dungeon;
import com.github.reneranzinger.pad.miru.om.Floor;
import com.github.reneranzinger.pad.miru.om.MonsterEntry;
import com.github.reneranzinger.pad.miru.om.Skill;
import com.github.reneranzinger.pad.miru.persist.DBInterface;

public class MiruProcessor
{
    public static final String SKILL_FILE = "na_skills.json";
    public static final String CARD_FILE = "na_raw_cards.json";
    public static final String BONUS_FILE = "na_bonuses.json";
    public static final String DUNGEON_FILE = "na_dungeons.json";

    private DBInterface m_db = null;
    private HashMap<Integer, Boolean> m_mapSkills = new HashMap<>();
    private HashMap<Integer, Boolean> m_mapAwakening = new HashMap<>();
    private HashMap<Integer, Boolean> m_mapType = new HashMap<>();
    private HashMap<Integer, Boolean> m_cardIds = new HashMap<>();

    public void process(String a_targetFolder, DBInterface a_db)
            throws IOException, ParseException, MiruFileException, SQLException
    {
        this.m_db = a_db;
        this.initMaps();
        System.out.println("Importing Skills");
        this.loadSkillFile(a_targetFolder + MiruProcessor.SKILL_FILE);
        System.out.println("Importing Cards");
        this.loadCardFile(a_targetFolder + MiruProcessor.CARD_FILE);
        System.out.println("Importing Dungeons");
        this.loadDungeonsFile(a_targetFolder + MiruProcessor.DUNGEON_FILE);
        System.out.println("Importing Bonus");
        this.loadBonusFile(a_targetFolder + MiruProcessor.BONUS_FILE);
    }

    private void loadBonusFile(String a_file) throws IOException, ParseException, MiruFileException
    {
        JSONArray t_arrayRoot = (JSONArray) this.parseJsonFile(a_file);
        Integer t_counter = -1;
        for (Object t_object : t_arrayRoot)
        {
            t_counter++;
            try
            {
                JSONObject t_jsonObject = (JSONObject) t_object;
                Bonus t_bonus = new Bonus();
                t_object = t_jsonObject.get("bonus");
                if (!(t_object instanceof JSONObject))
                {
                    throw new MiruFileException("Bonus is not of type JsonObject.");
                }
                this.fillBonusInformation(t_bonus, (JSONObject) t_object, t_counter);
                // start time
                Date t_dateValue = this.getDateValue(t_jsonObject, "start_timestamp", true,
                        t_counter);
                t_bonus.setStartTime(t_dateValue);
                // end time
                t_dateValue = this.getDateValue(t_jsonObject, "end_timestamp", true, t_counter);
                t_bonus.setEndTime(t_dateValue);
                // is starter
                Boolean t_booleanValue = this.getBooleanValue(t_jsonObject, "is_starter", true,
                        t_counter);
                t_bonus.setStarter(t_booleanValue);
                // group
                String t_stringValue = this.getStringValue(t_jsonObject, "group", false, t_counter);
                t_bonus.setGroup(t_stringValue);
                // insert into database
                this.m_db.insertBonus(t_bonus);
            }
            catch (Exception e)
            {
                System.out.println("\t" + t_counter.toString() + ":" + e.getMessage() + "("
                        + e.getClass().getName() + ")");
            }
        }
    }

    private void loadDungeonsFile(String a_file) throws IOException, ParseException
    {
        JSONArray t_arrayRoot = (JSONArray) this.parseJsonFile(a_file);
        Integer t_counter = -1;
        for (Object t_object : t_arrayRoot)
        {
            t_counter++;
            try
            {
                JSONObject t_jsonObject = (JSONObject) t_object;
                Dungeon t_dungeon = new Dungeon();
                // alt type
                String t_stringValue = this.getStringValue(t_jsonObject, "alt_dungeon_type", true,
                        t_counter);
                t_dungeon.setAltType(t_stringValue);
                // comment
                t_stringValue = this.getStringValue(t_jsonObject, "dungeon_comment", false,
                        t_counter);
                t_dungeon.setComment(t_stringValue);
                // comment value
                Integer t_intValue = this.getIntValue(t_jsonObject, "dungeon_comment_value", false,
                        t_counter);
                t_dungeon.setCommentValue(t_intValue);
                // id
                t_intValue = this.getIntValue(t_jsonObject, "dungeon_id", true, t_counter);
                t_dungeon.setId(t_intValue);
                // name
                t_stringValue = this.getStringValue(t_jsonObject, "clean_name", true, t_counter);
                t_dungeon.setName(t_stringValue);
                // one time
                Boolean t_booleanValue = this.getBooleanValue(t_jsonObject, "one_time", true,
                        t_counter);
                t_dungeon.setOneTime(t_booleanValue);
                // repeat day
                t_stringValue = this.getStringValue(t_jsonObject, "repeat_day", false, t_counter);
                t_dungeon.setRepeatDay(t_stringValue);
                // type
                t_stringValue = this.getStringValue(t_jsonObject, "dungeon_type", false, t_counter);
                t_dungeon.setType(t_stringValue);
                // floors
                List<Floor> t_floors = this.extractfloors(t_jsonObject.get("floors"), t_counter);
                t_dungeon.setFloors(t_floors);
                // write to database
                this.m_db.insertDungeon(t_dungeon);
            }
            catch (Exception e)
            {
                System.out.println("\t" + t_counter.toString() + ":" + e.getMessage() + "("
                        + e.getClass().getName() + ")");
            }
        }
    }

    private List<Floor> extractfloors(Object a_floors, Integer a_counter) throws MiruFileException
    {
        if (a_floors == null)
        {
            throw new MiruFileException(
                    "floors is missing in object number " + a_counter.toString());
        }
        if (!(a_floors instanceof JSONArray))
        {
            throw new MiruFileException(
                    "floors is not an Array in object number " + a_counter.toString());
        }
        JSONArray t_arrayFloors = (JSONArray) a_floors;
        List<Floor> t_floorList = new ArrayList<>();
        for (Object t_objectFloors : t_arrayFloors)
        {
            if (!(t_objectFloors instanceof JSONObject))
            {
                throw new MiruFileException(
                        "element in floors is not an object: " + a_counter.toString());
            }
            Floor t_floor = this.getFloor((JSONObject) t_objectFloors, a_counter);
            t_floorList.add(t_floor);
        }
        return t_floorList;
    }

    private Floor getFloor(JSONObject a_objectFloors, Integer a_counter) throws MiruFileException
    {
        Floor t_floor = new Floor();
        // number
        Integer t_intValue = this.getIntValue(a_objectFloors, "floor_number", true, a_counter);
        t_floor.setNumber(t_intValue);
        // name
        String t_stringValue = this.getStringValue(a_objectFloors, "clean_name", true, a_counter);
        t_floor.setName(t_stringValue);
        // stamina
        t_stringValue = this.getStringValue(a_objectFloors, "stamina", true, a_counter);
        t_intValue = Integer.parseInt(t_stringValue);
        t_floor.setStamina(t_intValue);
        // waves
        t_intValue = this.getIntValue(a_objectFloors, "waves", true, a_counter);
        t_floor.setWaves(t_intValue);
        // drops
        List<Integer> t_intList = this.getIntList(a_objectFloors, "drops", a_counter);
        t_floor.setDrops(t_intList);
        return t_floor;
    }

    private void fillBonusInformation(Bonus a_bonus, JSONObject a_jsonObject, Integer a_counter)
            throws MiruFileException
    {
        // bonus id
        Integer t_intValue = this.getIntValue(a_jsonObject, "bonus_id", true, a_counter);
        a_bonus.setBonusTypeId(t_intValue);
        // bonus name
        String t_stringValue = this.getStringValue(a_jsonObject, "bonus_name", true, a_counter);
        a_bonus.setName(t_stringValue);
        // bonus value
        Object t_value = a_jsonObject.get("bonus_value");
        if (t_value != null)
        {
            if (t_value instanceof Long)
            {
                t_stringValue = ((Long) t_value).toString();
            }
            else if (t_value instanceof String)
            {
                t_stringValue = (String) t_value;
            }
            else
            {
                throw new MiruFileException(
                        "bonus_value is not an integer or a string in object number "
                                + a_counter.toString());
            }
            a_bonus.setValue(t_stringValue);
        }
        // message
        t_stringValue = this.getStringValue(a_jsonObject, "clean_message", false, a_counter);
        a_bonus.setMessage(t_stringValue);
        // dungeon id
        t_intValue = this.getIntValue(a_jsonObject, "dungeon_id", false, a_counter);
        a_bonus.setDungeonId(t_intValue);
        // dungeon floor
        t_intValue = this.getIntValue(a_jsonObject, "dungeon_floor_id", false, a_counter);
        a_bonus.setFloorNumber(t_intValue);
        // egg machine id
        t_intValue = this.getIntValue(a_jsonObject, "egg_machine_id", false, a_counter);
        a_bonus.setEggMachineId(t_intValue);
    }

    private void initMaps() throws SQLException
    {
        this.m_mapAwakening = this.m_db.getAwakenings();
        this.m_mapType = this.m_db.getTypes();
    }

    private void loadCardFile(String a_file)
            throws MiruFileException, IOException, ParseException, SQLException
    {
        JSONArray t_arrayRoot = (JSONArray) this.parseJsonFile(a_file);
        Integer t_counter = -1;
        try
        {
            this.fillCardIdMap(t_arrayRoot);
        }
        catch (Exception e)
        {
            throw new MiruFileException("Unable to get IDs from Card file: " + e.getMessage(), e);
        }
        for (Object t_jsonObject : t_arrayRoot)
        {
            t_counter++;
            try
            {
                JSONObject t_jsonCard = (JSONObject) t_jsonObject;
                MonsterEntry t_entry = new MonsterEntry();
                // Id
                Integer t_intValue = this.getIntValue(t_jsonCard, "card_id", true, t_counter);
                t_entry.setId(t_intValue);
                // Name
                String t_stringValue = this.getStringValue(t_jsonCard, "name", true, t_counter);
                t_entry.setName(t_stringValue);
                // ancestor
                t_intValue = this.getIntValue(t_jsonCard, "ancestor_id", false, t_counter);
                t_entry.setAncestor(t_intValue);
                // atk
                t_intValue = this.getIntValue(t_jsonCard, "max_atk", true, t_counter);
                t_entry.setAtk(t_intValue);
                // hp
                t_intValue = this.getIntValue(t_jsonCard, "max_hp", true, t_counter);
                t_entry.setHp(t_intValue);
                // rcv
                t_intValue = this.getIntValue(t_jsonCard, "max_rcv", true, t_counter);
                t_entry.setRcv(t_intValue);
                // skill
                t_intValue = this.getIntValue(t_jsonCard, "active_skill_id", false, t_counter);
                t_entry.setSkill(t_intValue);
                // leader skill
                t_intValue = this.getIntValue(t_jsonCard, "leader_skill_id", false, t_counter);
                t_entry.setLeaderSkill(t_intValue);
                // evo material 1-5
                t_intValue = this.getIntValue(t_jsonCard, "evo_mat_id_1", false, t_counter);
                t_entry.setEvoMaterial1(t_intValue);
                t_intValue = this.getIntValue(t_jsonCard, "evo_mat_id_2", false, t_counter);
                t_entry.setEvoMaterial2(t_intValue);
                t_intValue = this.getIntValue(t_jsonCard, "evo_mat_id_3", false, t_counter);
                t_entry.setEvoMaterial3(t_intValue);
                t_intValue = this.getIntValue(t_jsonCard, "evo_mat_id_4", false, t_counter);
                t_entry.setEvoMaterial4(t_intValue);
                t_intValue = this.getIntValue(t_jsonCard, "evo_mat_id_5", false, t_counter);
                t_entry.setEvoMaterial5(t_intValue);
                // max level
                t_intValue = this.getIntValue(t_jsonCard, "max_level", true, t_counter);
                t_entry.setMaxLevel(t_intValue);
                // m points
                t_intValue = this.getIntValue(t_jsonCard, "sell_mp", true, t_counter);
                t_entry.setmPoints(t_intValue);
                // attribute
                t_intValue = this.getIntValue(t_jsonCard, "attr_id", true, t_counter);
                t_entry.setPrimaryAttribute(t_intValue);
                // secondary attribute
                t_intValue = this.getIntValue(t_jsonCard, "sub_attr_id", false, t_counter);
                if (t_intValue >= 0)
                {
                    t_entry.setSecondaryAttribute(t_intValue);
                }
                // rarity
                t_intValue = this.getIntValue(t_jsonCard, "rarity", true, t_counter);
                t_entry.setRarity(t_intValue);
                // type
                List<Integer> t_intList = new ArrayList<>();
                t_intValue = this.getIntValue(t_jsonCard, "type_1_id", true, t_counter);
                if (!t_intValue.equals(-1))
                {
                    t_intList.add(t_intValue);
                }
                t_intValue = this.getIntValue(t_jsonCard, "type_2_id", true, t_counter);
                if (!t_intValue.equals(-1))
                {
                    t_intList.add(t_intValue);
                }
                t_intValue = this.getIntValue(t_jsonCard, "type_3_id", true, t_counter);
                if (!t_intValue.equals(-1))
                {
                    t_intList.add(t_intValue);
                }
                t_entry.setType(t_intList);
                // awoken skills
                t_intList = this.getIntList(t_jsonCard, "awakenings", t_counter);
                t_entry.setAwokenSkills(t_intList);
                // super awoken skill
                t_intList = this.getIntList(t_jsonCard, "super_awakenings", t_counter);
                t_entry.setSuperAwokenSkill(t_intList);
                // limit break
                t_intValue = this.getIntValue(t_jsonCard, "limit_mult", true, t_counter);
                if (!t_intValue.equals(0))
                {
                    t_entry.setLimitBreaks(t_intValue);
                }
                this.validateEntry(t_entry, t_counter);
                // evomaterial
                this.m_db.insertMonster(t_entry);
            }
            catch (Exception e)
            {
                System.out.println("\t" + t_counter.toString() + ":" + e.getMessage() + "("
                        + e.getClass().getName() + ")");
            }
        }
    }

    private void fillCardIdMap(JSONArray a_arrayRoot) throws MiruFileException
    {
        int t_counter = -1;
        for (Object t_jsonObject : a_arrayRoot)
        {
            t_counter++;
            if (!(t_jsonObject instanceof JSONObject))
            {
                throw new MiruFileException("Card file array does not contain JSON objects");
            }
            JSONObject t_jsonCard = (JSONObject) t_jsonObject;
            // Id
            Integer t_intValue = this.getIntValue(t_jsonCard, "card_id", true, t_counter);
            this.m_cardIds.put(t_intValue, Boolean.TRUE);
        }
    }

    private void validateEntry(MonsterEntry a_entry, Integer t_counter) throws MiruFileException
    {
        // skill
        Integer t_intValue = a_entry.getSkill();
        if (t_intValue != null)
        {
            if (this.m_mapSkills.get(t_intValue) == null)
            {
                throw new MiruFileException("Unknown skill (" + t_intValue.toString()
                        + ") found in object number " + t_counter.toString());
            }
        }
        // leader skill
        t_intValue = a_entry.getLeaderSkill();
        if (t_intValue != null)
        {
            if (this.m_mapSkills.get(t_intValue) == null)
            {
                throw new MiruFileException("Unknown leader skill (" + t_intValue.toString()
                        + ") found in object number " + t_counter.toString());
            }
        }
        // awkening and super awkening;
        for (Integer t_id : a_entry.getAwokenSkills())
        {
            if (this.m_mapAwakening.get(t_id) == null)
            {
                throw new MiruFileException("Unknown awoken skill (" + t_id.toString()
                        + ") found in object number " + t_counter.toString());
            }
        }
        for (Integer t_id : a_entry.getSuperAwokenSkill())
        {
            if (this.m_mapAwakening.get(t_id) == null)
            {
                throw new MiruFileException("Unknown super awoken skill (" + t_id.toString()
                        + ") found in object number " + t_counter.toString());
            }
        }
        // attribute
        t_intValue = a_entry.getPrimaryAttribute();
        if (t_intValue < 0 || t_intValue > 4)
        {
            throw new MiruFileException("Unknown attribute (" + t_intValue.toString()
                    + ") found in object number " + t_counter.toString());
        }
        t_intValue = a_entry.getSecondaryAttribute();
        if (t_intValue != null)
        {
            if (t_intValue < 0 || t_intValue > 4)
            {
                throw new MiruFileException("Unknown secondary attribute (" + t_intValue.toString()
                        + ") found in object number " + t_counter.toString());
            }
        }
        // types
        for (Integer t_id : a_entry.getType())
        {
            if (this.m_mapType.get(t_id) == null)
            {
                throw new MiruFileException("Unknown type (" + t_id.toString()
                        + ") found in object number " + t_counter.toString());
            }
        }
        // ancestor
        t_intValue = a_entry.getAncestor();
        if (t_intValue != null)
        {
            if (this.m_cardIds.get(t_intValue) == null)
            {
                throw new MiruFileException("Unknown ancestor ID (" + t_intValue.toString()
                        + ") found in object number " + t_counter.toString());
            }
        }
        // evolution material
        t_intValue = a_entry.getEvoMaterial1();
        if (t_intValue != null)
        {
            if (this.m_cardIds.get(t_intValue) == null)
            {
                throw new MiruFileException("Unknown evoluation material (" + t_intValue.toString()
                        + ") found in object number " + t_counter.toString());
            }
        }
        t_intValue = a_entry.getEvoMaterial2();
        if (t_intValue != null)
        {
            if (this.m_cardIds.get(t_intValue) == null)
            {
                throw new MiruFileException("Unknown evoluation material (" + t_intValue.toString()
                        + ") found in object number " + t_counter.toString());
            }
        }
        t_intValue = a_entry.getEvoMaterial3();
        if (t_intValue != null)
        {
            if (this.m_cardIds.get(t_intValue) == null)
            {
                throw new MiruFileException("Unknown evoluation material (" + t_intValue.toString()
                        + ") found in object number " + t_counter.toString());
            }
        }
        t_intValue = a_entry.getEvoMaterial4();
        if (t_intValue != null)
        {
            if (this.m_cardIds.get(t_intValue) == null)
            {
                throw new MiruFileException("Unknown evoluation material (" + t_intValue.toString()
                        + ") found in object number " + t_counter.toString());
            }
        }
        t_intValue = a_entry.getEvoMaterial5();
        if (t_intValue != null)
        {
            if (this.m_cardIds.get(t_intValue) == null)
            {
                throw new MiruFileException("Unknown evoluation material (" + t_intValue.toString()
                        + ") found in object number " + t_counter.toString());
            }
        }
    }

    private List<Integer> getIntList(JSONObject a_jsonObject, String a_key, Integer a_counter)
            throws MiruFileException
    {
        Object t_value = a_jsonObject.get(a_key);
        if (t_value == null)
        {
            throw new MiruFileException(
                    a_key + " is missing in object number " + a_counter.toString());
        }
        if (!(t_value instanceof JSONArray))
        {
            throw new MiruFileException(
                    a_key + " is not an Array in object number " + a_counter.toString());
        }
        JSONArray t_array = (JSONArray) t_value;
        List<Integer> t_result = new ArrayList<>();
        for (Object t_object : t_array)
        {
            if (!(t_object instanceof Long))
            {
                throw new MiruFileException(a_key + " is not an integer array in object number "
                        + a_counter.toString());
            }
            Integer t_intValue = ((Long) t_object).intValue();
            t_result.add(t_intValue);
        }
        return t_result;
    }

    private Integer getIntValue(JSONObject a_jsonObject, String a_key, Boolean a_mandetory,
            Integer a_counter) throws MiruFileException
    {
        Object t_value = a_jsonObject.get(a_key);
        if (t_value == null)
        {
            if (a_mandetory)
            {
                throw new MiruFileException(
                        a_key + " is missing in object number " + a_counter.toString());
            }
            return null;
        }
        if (!(t_value instanceof Long))
        {
            throw new MiruFileException(
                    a_key + " is not an integer in object number " + a_counter.toString());
        }
        Integer t_intValue = ((Long) t_value).intValue();
        return t_intValue;
    }

    private Boolean getBooleanValue(JSONObject a_jsonObject, String a_key, boolean a_mandetory,
            Integer a_counter) throws MiruFileException
    {
        Object t_value = a_jsonObject.get(a_key);
        if (t_value == null)
        {
            if (a_mandetory)
            {
                throw new MiruFileException(
                        a_key + " is missing in object number " + a_counter.toString());
            }
            return null;
        }
        if (!(t_value instanceof Boolean))
        {
            throw new MiruFileException(
                    a_key + " is not an boolean in object number " + a_counter.toString());
        }
        Boolean t_booleanValue = (Boolean) t_value;
        return t_booleanValue;
    }

    private Date getDateValue(JSONObject a_jsonObject, String a_key, boolean a_mandetory,
            Integer a_counter) throws MiruFileException
    {
        Object t_value = a_jsonObject.get(a_key);
        if (t_value == null)
        {
            if (a_mandetory)
            {
                throw new MiruFileException(
                        a_key + " is missing in object number " + a_counter.toString());
            }
            return null;
        }
        if (!(t_value instanceof Long))
        {
            throw new MiruFileException(
                    a_key + " is not an long in object number " + a_counter.toString());
        }
        Long t_longValue = (Long) t_value;
        return new Date(t_longValue);
    }

    private String getStringValue(JSONObject a_jsonObject, String a_key, Boolean a_mandetory,
            Integer a_counter) throws MiruFileException
    {
        Object t_value = a_jsonObject.get(a_key);
        if (t_value == null)
        {
            if (a_mandetory)
            {
                throw new MiruFileException(
                        a_key + " is missing in object number " + a_counter.toString());
            }
            return null;
        }
        if (!(t_value instanceof String))
        {
            throw new MiruFileException(
                    a_key + " is not an String in object number " + a_counter.toString());
        }
        String t_stringValue = (String) t_value;
        return t_stringValue;
    }

    private void loadSkillFile(String a_file) throws MiruFileException, IOException, ParseException
    {
        JSONArray t_arrayRoot = (JSONArray) this.parseJsonFile(a_file);
        Integer t_counter = -1;
        for (Object t_jsonObject : t_arrayRoot)
        {
            t_counter++;
            if (!(t_jsonObject instanceof JSONObject))
            {
                throw new MiruFileException("Skill file array does not contain JSON objects");
            }
            try
            {
                JSONObject t_jsonSkill = (JSONObject) t_jsonObject;
                Skill t_skill = new Skill();
                // ID
                Integer t_intValue = this.getIntValue(t_jsonSkill, "skill_id", true, t_counter);
                t_skill.setId(t_intValue);
                // type
                t_intValue = this.getIntValue(t_jsonSkill, "skill_type", true, t_counter);
                t_skill.setType(t_intValue);
                // part 1
                t_intValue = this.getIntValue(t_jsonSkill, "skill_part_1_id", false, t_counter);
                t_skill.setPart1(t_intValue);
                // part 2
                t_intValue = this.getIntValue(t_jsonSkill, "skill_part_2_id", false, t_counter);
                t_skill.setPart2(t_intValue);
                // part 3
                t_intValue = this.getIntValue(t_jsonSkill, "skill_part_3_id", false, t_counter);
                t_skill.setPart3(t_intValue);
                // levels
                t_intValue = this.getIntValue(t_jsonSkill, "levels", false, t_counter);
                t_skill.setMaxLevel(t_intValue);
                // min turn
                t_intValue = this.getIntValue(t_jsonSkill, "turn_min", false, t_counter);
                t_skill.setMinTurns(t_intValue);
                // max turn
                t_intValue = this.getIntValue(t_jsonSkill, "turn_max", false, t_counter);
                t_skill.setMaxTurns(t_intValue);
                // name
                String t_stringValue = this.getStringValue(t_jsonSkill, "name", true, t_counter);
                t_skill.setName(t_stringValue.trim());
                // description
                t_stringValue = this.getStringValue(t_jsonSkill, "clean_description", true,
                        t_counter);
                if (t_stringValue.trim().length() != 0)
                {
                    t_skill.setDescription(t_stringValue.trim());
                }
                this.m_db.insertSkill(t_skill);
                this.m_mapSkills.put(t_skill.getId(), Boolean.TRUE);
            }
            catch (Exception e)
            {
                System.out.println("\t" + t_counter.toString() + ":" + e.getMessage() + "("
                        + e.getClass().getName() + ")");
            }
        }
    }

    private Object parseJsonFile(String a_file) throws IOException, ParseException
    {
        Reader t_reader = new FileReader(a_file);
        JSONParser t_parser = new JSONParser();
        Object t_object = t_parser.parse(t_reader);
        t_reader.close();
        return t_object;
    }

}
