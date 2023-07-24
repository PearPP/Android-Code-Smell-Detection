/*
 * Paprika - Detection of code smells in Android application
 *     Copyright (C)  2016  Geoffrey Hecht - INRIA - UQAM - University of Lille
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.inria.sniffer.detector.neo4j;

import org.neo4j.cypher.CypherException;
import org.neo4j.graphdb.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Created by Geoffrey Hecht on 18/08/15.
 */
public class MIMQuery extends Query {

    private MIMQuery(QueryEngine queryEngine) {
        super(queryEngine, "MIM");
    }

    public static MIMQuery createMIMQuery(QueryEngine queryEngine) {
        return new MIMQuery(queryEngine);
    }

    @Override
    protected String getQuery(boolean details) {
        String query = "MATCH (a:App)-[:APP_OWNS_CLASS]->(cl:Class)-[:CLASS_OWNS_METHOD]->(m1:Method) " +
                "WHERE m1.number_of_callers>0 " +
                "AND NOT exists(m1.is_static) " +
                "AND NOT exists(m1.is_override) " +
                "AND NOT (m1)-[:USES]->(:Variable) " +
                "AND NOT (m1)-[:CALLS]->(:ExternalMethod) " +
                "AND NOT (m1)-[:CALLS]->(:Method) " +
                "AND NOT exists(m1.is_init) " +
                "AND NOT exists(cl.is_interface) " +
                "RETURN DISTINCT a.commit_number as commit_number, m1.app_key as key, cl.file_path as file_path";
        if (details) {
            query += ",m1.full_name as instance";
        } else {
            query += ",count(m1) as MIM";
        }
        return query;
    }
}
