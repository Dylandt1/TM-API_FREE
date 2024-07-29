package fr.tmmods.tmapi.data.manager.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * This file is part of TM-API, a Spigot/BungeeCord API.
 *
 * TM-API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TM-API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

public class SqlManager
{
    /* Create tables in database with this method or use directly update() method

    To use this method, implement this code in yours main classes or adapt to your uses :

    Map<String, List<String>> tables = new HashMap<>();

    String prefixTables = "fbg_"
    String exampleTable1 = "table1";
    String exampleTable2 = "table2";

    List<String> exampleEntries1 = Arrays.asList(
            "id "+SqlType.INT.sql()+" NOT NULL AUTO_INCREMENT PRIMARY KEY",
            "name "+SqlType.VARCHAR.sql(),
            "displayName "+SqlType.VARCHAR.sql(),
            "isOP "+SqlType.BOOLEAN.sql(),
            "money "+SqlType.DOUBLE.sql()
    );

    List<String> exampleEntries2 = Arrays.asList(
            "id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY",
            "name VARCHAR(255)",
            "displayName VARCHAR(255)",
            "isOP TINYINT(1)",
            "money DOUBLE"
    );

    tables.put(exampleTable1, exampleEntries1);
    tables.put(exampleTable2, exampleEntries2);
    createTables(prefixTables, tables);

     */

    private final SqlManager INSTANCE;

    public SqlManager() {INSTANCE = this;}

    public void createTables(String prefixTables, Map<String, List<String>> tables, Connection connection)
    {
        for(String tableName : tables.keySet())
        {
            List<String> list = tables.get(tableName);

            StringBuilder sb = new StringBuilder();

            for(int i = 0; i != list.size(); i++)
            {
                sb.append(list.get(i)).append(", ");
            }

            update(connection, "CREATE TABLE IF NOT EXISTS "+prefixTables+tableName+" ("+sb.substring(0, sb.length() -2)+")");
        }

        try {
            connection.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* Create one table in database with this method.

    To use this method, implement this code in yours main classes or adapt to your uses :

    String prefixTables = "prefixTables";
    String serversTable = "tableName";

    List<String> entries = Arrays.asList(
            "id "+ SqlType.INT.sql()+" NOT NULL AUTO_INCREMENT PRIMARY KEY",
            "name "+SqlType.VARCHAR.sql(),
            "displayName "+SqlType.VARCHAR.sql(),
            "isOP "+SqlType.BOOLEAN.sql(),
            "money "+SqlType.DOUBLE.sql()
    );

    SqlManager.createTable(prefixTables, tableName, entries);

     */
    public void createTable(String prefixTables, String tableName, List<String> entries, Connection connection)
    {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i != entries.size(); i++)
        {
            sb.append(entries.get(i)).append(", ");
        }

        update(connection, "CREATE TABLE IF NOT EXISTS "+prefixTables+tableName+" ("+sb.substring(0, sb.length() -2)+")");

        try {
            connection.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Send query type executeUpdate
    public void update(Connection connection, String query)
    {
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    // Send query type executeQuery
    public void query(Connection connection, String query)
    {
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeQuery();
            ps.close();
        }
        catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    // Send query type execute
    public void execute(Connection connection, String query)
    {
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
