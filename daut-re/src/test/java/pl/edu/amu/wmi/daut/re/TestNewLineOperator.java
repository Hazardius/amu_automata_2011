package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.AutomatonByRecursion;
import pl.edu.amu.wmi.daut.re.NewLineOperator.Factory;

/**
 * Testy klasy NewLineOperator.
 */
public class TestNewLineOperator extends TestCase {

    /**
     * Test metody CreateFixedAutomaton.
     */
    public void testCreateFixedAutomaton() {

        NewLineOperator operator = new NewLineOperator();
        AutomatonSpecification spec = operator.createFixedAutomaton();
        assertFalse(spec.isEmpty());
        assertFalse(spec.acceptEmptyWord());
        assertTrue(spec.isDeterministic());
        assertEquals(spec.countTransitions(), 1);

        AutomatonByRecursion automaton = new AutomatonByRecursion(spec);
        assertTrue(automaton.accepts("\n"));

    }

    /**
     * Test fabryki.
     */
    public void testFactory() {

        Factory factory = new Factory();
        assertEquals(factory.numberOfParams(), 0);
        RegexpOperator operator2 = factory.createOperator(new ArrayList<String>());
        int arity = operator2.arity();
        assertEquals(arity, 0);

    }
}