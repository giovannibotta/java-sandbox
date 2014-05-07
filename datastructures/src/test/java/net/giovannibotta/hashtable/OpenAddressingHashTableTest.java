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

        basicTest(OpenAddressingHashTable.createDoubleHashing(String::hashCode, String::hashCode, String::equals));
    }

    private void basicTest(OpenAddressingHashTable<String, String> table) {
        assertTrue("Table initially empty", table.isEmpty());
        assertEquals("Table size initially 0", 0, table.size());
        assertTrue("Table does not contain some key", !table.containsKey("x"));

        assertTrue("Put operation modifies table", table.put("x", "y"));
        assertEquals("Can find a given key and retrieve the correct value", "y", table.get("x"));
        assertTrue("Put operation modifies table", table.put("y", "y"));
        assertEquals("Can find a given key and retrieve the correct value", "y", table.get("x"));
        assertTrue("Put operation modifies table", table.put("z", "y"));
        assertEquals("Can find a given key and retrieve the correct value", "y", table.get("y"));
        assertEquals("Can find a given key and retrieve the correct value", "y", table.get("z"));
        assertEquals("Table size is correct", 3, table.size());

        assertTrue("Can find a given key", table.containsKey("x"));
        assertEquals("Can find a given key and retrieve the correct value", "y", table.get("x"));

        assertTrue("Put operation modifies table if value is different", table.put("x", "z"));
        assertTrue("Put operation does not modify table if value is the same", !table.put("x", "z"));
        assertEquals("Updates to the same key does not change the size", 3, table.size());

        assertTrue("Can find a given key", table.containsKey("x"));
        assertEquals("Can find a given key and retrieve the correct value", "z", table.get("x"));

        System.out.println(table);
    }
}
