package fr.tmmods.tmapi.data.manager.sql;

import com.google.common.annotations.Beta;

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

@Beta
public enum SqlType
{
    CHAR("CHAR", "255"),
    VARCHAR("VARCHAR", "255"),
    TINYTEXT("TINYTEXT", "255"),
    TEXT("TEXT", "65535"),
    MEDIUMTEXT("MEDIUMTEXT", "16777215"),
    LONGTEXT("LONGTEXT", "4294967295"),

    TINYINT("TINYINT", "127"),
    SMALLINT("SMALLINT", "32767"),
    INT("INT", "11"),
    MEDIUMINT("MEDIUMINT", "8388607"),
    INTEGER("INTEGER", "2147483647"),
    BIGINT("BIGINT", "9223372036854775807"),

    FLOAT("FLOAT", null),
    DOUBLE("DOUBLE", null),
    DECIMAL("DECIMAL", null),
    NUMERIC("NUMERIC", null),

    REAL("REAL", null),

    DATE("DATE", null),
    DATETIME("DATETIME", null),
    TIME("TIME", null),
    TIMESTAMP("TIMESTAMP", null),
    YEAR("YEAR", null),

    VARBINARY("VARBINARY", "-999999999"),
    BINARY("BINARY", null),

    TINYBLOB("TINYBLOB", "255"),
    BLOB("BLOB", "65535"),
    MEDIUMBLOB("MEDIUMBLOB", "16777215"),
    LONGBLOB("LONGBLOB", "4294967295"),

    SET("SET", null),
    ENUM("ENUM", null),
    BOOLEAN("TINYINT", "1");

    private final String type;
    private final String spec;

    SqlType(String type, String spec)
    {
        this.type = type;
        this.spec = spec;
    }

    public String getType() {return type;}
    public String getSpec() {return spec;}

    public String sql()
    {
        if(spec == null) return type;
        return type+"("+spec+")";
    }

    public String sql(String customSpec)
    {
        if(customSpec == null) return type;
        return type+"("+customSpec+")";
    }
}
