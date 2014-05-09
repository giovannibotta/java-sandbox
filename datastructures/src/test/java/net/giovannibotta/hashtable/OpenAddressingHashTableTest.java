package net.giovannibotta.hashtable;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author giovanni
 * @since 5/7/14
 */
public class OpenAddressingHashTableTest {
    @Test
    public void basic() {
        basicTest(OpenAddressingHashTable.createLinear(String::hashCode, String::equals));

        basicTest(OpenAddressingHashTable.createQuadratic(String::hashCode, String::equals));

        // FIXME: this is broken right now
        basicTest(OpenAddressingHashTable.createDoubleHashing(String::hashCode, String::hashCode, String::equals));
    }

    private void basicTest(OpenAddressingHashTable<String, String> table) {
        assertTrue("Table initially empty", table.isEmpty());
        assertEquals("Table size initially 0", 0, table.size());
        assertTrue("Table does not contain some key", !table.containsKey("x"));

        assertEquals("Put operation modifies table", null, table.put("x", "y"));
        assertEquals("Can find a given key and retrieve the correct value", "y", table.get("x"));
        assertEquals("Put operation modifies table", null, table.put("y", "y"));
        assertEquals("Can find a given key and retrieve the correct value", "y", table.get("x"));
        assertEquals("Put operation modifies table", null, table.put("z", "y"));
        assertEquals("Can find a given key and retrieve the correct value", "y", table.get("y"));
        assertEquals("Can find a given key and retrieve the correct value", "y", table.get("z"));
        assertEquals("Table size is correct", 3, table.size());

        assertTrue("Can find a given key", table.containsKey("x"));
        assertEquals("Can find a given key and retrieve the correct value", "y", table.get("x"));

        assertEquals("Put operation modifies table if value is different", "y", table.put("x", "z"));
        assertEquals("Put operation does not modify table if value is the same", "z", table.put("x", "z"));
        assertEquals("Updates to the same key does not change the size", 3, table.size());

        assertTrue("Can find a given key", table.containsKey("x"));
        assertEquals("Can find a given key and retrieve the correct value", "z", table.get("x"));

        assertEquals("Key correctly removed", "y", table.remove("z"));
        assertEquals("Updated size after deletion", 2, table.size());

        table.put("a", "1");
        table.put("b", "2");
        table.put("c", "3");

        assertEquals("Correct size", 5, table.size());
        assertEquals("Can find a given key and retrieve the correct value", "1", table.get("a"));
        assertEquals("Can find a given key and retrieve the correct value", "2", table.get("b"));

        assertEquals("Key correctly removed", "1", table.remove("a"));
        assertEquals("Updated size after deletion", 4, table.size());

        System.out.println(table);
    }
}
