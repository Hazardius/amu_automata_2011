package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Testy klasy AutomatonSpecification.
 */
public class TestAutomatonSpecification extends TestCase {

    /**
     * Test metody fromString() tworzacy pusty automat.
     */
    public final void testFromString0EmptyLanguage() {
        AutomatonSpecification pustyOjciec = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0\n-Transitions:\n"
                + "-Initial state: q0\n-Final states:";

        try {
            pustyOjciec.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla poprawnego Stringa!");
        }

        AutomatonByRecursion pusteDziecko = new AutomatonByRecursion(pustyOjciec);

        assertFalse(pusteDziecko.accepts("aaa"));
        assertFalse(pusteDziecko.accepts("baba"));
        assertFalse(pusteDziecko.accepts(""));
    }

    /**
     * Test metody fromString() tworzący automat akceptujacy slowo puste.
     */
    public final void testFromString1EmptyStringAutomaton() {
        AutomatonSpecification dziwny = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0\n-Transitions:\n"
                + "-Initial state: q0\n-Final states: q0";

        try {
            dziwny.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla poprawnego Stringa!");
        }

        AutomatonByRecursion wynik = new AutomatonByRecursion(dziwny);

        assertFalse(wynik.accepts("aaa"));
        assertFalse(wynik.accepts("baba"));
        assertTrue(wynik.accepts(""));
    }

    /**
     * Test metody fromString() tworzący automat akceptujacy nieparzyste
     * potegi 'a'.
     */
    public final void testFromString2OddPowersOfA() {
        AutomatonSpecification masakra = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q1\nq1 -a-> q0\n-Initial state: q0\n-Final states: q1";

        try {
            masakra.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla poprawnego Stringa!");
        }

        AutomatonByRecursion angle = new AutomatonByRecursion(masakra);

        assertFalse(angle.accepts("aab"));
        assertFalse(angle.accepts(""));
        assertTrue(angle.accepts("aaa"));
    }

    /**
     * Test metody fromString() tworzący automat z epsilonem.
     */
//    public final void testFromString3AutomatWithEpsilon() {
//        AutomatonSpecification epsilon = new NaiveAutomatonSpecification();
//
//        String slowo = "Automaton:\n-States: q0 q1 q2 q3\n-Transitions:\n"
//                + "q0 -ε-> q1\nq0 -ε-> q2\nq1 -a-> q3\nq2 -b-> q3\n-Initial state: q0\n"
//                + "-Final states: q3";
//
//        try {
//            epsilon.fromString(slowo);
//        } catch (Exception e) {
//            fail("fromString() zwrocil wyjatek dla automatu z Epsilonem!");
//        }
//
//        AutomatonByRecursion ep = new AutomatonByRecursion(epsilon);
//
//        assertTrue(ep.accepts("a"));
//        assertTrue(ep.accepts("b"));
//        assertFalse(ep.accepts(""));
//        assertFalse(ep.accepts("ε"));
//        assertFalse(ep.accepts("aa"));
//        assertFalse(ep.accepts("ab"));
//        assertFalse(ep.accepts("ba"));
//        assertFalse(ep.accepts("bb"));
//    }

    /**
     * Test metody fromString() z bełkotem.
     */
    public final void testFromString4Blubbering() {
        AutomatonSpecification niepoprawny = new NaiveAutomatonSpecification();

        String slowo = "niepoprawnywogolestring";

        try {
            niepoprawny.fromString(slowo);
        } catch (Exception e) {
            assertTrue(true);
        }

        slowo = "Magia: dwa trzy cztery piec";

        try {
            niepoprawny.fromString(slowo);
        } catch (Exception e) {
            assertTrue(true);
        }

        slowo = "Automaton:\n-Niestates: q0\n-Transitions: magia";

        try {
            niepoprawny.fromString(slowo);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Test metody fromString() z błędnymi opisami automatu.
     * Błędy nazw stanów.
     */
    public final void testFromString5WrongAutomatonStrings0States() {
        AutomatonSpecification bledny = new NaiveAutomatonSpecification();

        String nonCorrectStateName0 = "Automaton:\n-States: q\n-Transitions:\n a";

        try {
            bledny.fromString(nonCorrectStateName0);
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectStateName1 = "Automaton:\n-States: a1\n-Transitions:\n a";

        try {
            bledny.fromString(nonCorrectStateName1);
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectStateName2 = "Automaton:\n-States: q1.2\n-Transitions:\n a";

        try {
            bledny.fromString(nonCorrectStateName2);
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectStateName3 = "Automaton:\n-States: qa\n-Transitions:\n a";

        try {
            bledny.fromString(nonCorrectStateName3);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Test metody fromString() z błędnymi opisami automatu.
     * Błędy nazw przejść.
     */
    public final void testFromString5WrongAutomatonStrings1Transitions() {
        AutomatonSpecification bledny = new NaiveAutomatonSpecification();

        String nonCorrectTransitionName0Label0 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 --> q0";

        try {
            bledny.fromString(nonCorrectTransitionName0Label0);
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectTransitionName0Label1 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 .q-> q0";

        try {
            bledny.fromString(nonCorrectTransitionName0Label1);
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectTransitionName0Label2 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 --q> q0";

        try {
            bledny.fromString(nonCorrectTransitionName0Label2);
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectTransitionName1 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "qa -a-> q0";

        try {
            bledny.fromString(nonCorrectTransitionName1);
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectTransitionName2 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> qa";

        try {
            bledny.fromString(nonCorrectTransitionName2);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Test metody fromString() z błędnymi opisami automatu.
     * Błędy nazw stanów specjalnych.
     */
    public final void testFromString5WrongAutomatonStrings2SpecialStates() {
        AutomatonSpecification bledny = new NaiveAutomatonSpecification();

        String nonCorrectSpecialStates0 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial error";

        try {
            bledny.fromString(nonCorrectSpecialStates0);
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectSpecialStates1 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial state: qa";

        try {
            bledny.fromString(nonCorrectSpecialStates1);
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectSpecialStates2 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial state: q0\nerror";

        try {
            bledny.fromString(nonCorrectSpecialStates2);
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectSpecialStates3 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial state: q0\n-Final error";

        try {
            bledny.fromString(nonCorrectSpecialStates3);
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectSpecialStates4 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial state: q0\n-Final states: qk";

        try {
            bledny.fromString(nonCorrectSpecialStates4);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Test metody countStates.
     */
    public final void testCountStates() {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();

        //Test 1
        assertEquals(spec.countStates(), 0);

        //Test 2
        State q0 = spec.addState();
        assertEquals(spec.countStates(), 1);

        //Test 3
        for (int i = 1; i <= 123456; i++) {
            State q = spec.addState();
        }
        assertEquals(spec.countStates(), 123456 + 1);
    }

    /**
     * Test metody countStates.
     */
    public final void testCountTransitions() {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();

        //Test 1
        assertEquals(spec.countTransitions(), 0);

        //Test 2
        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();

        spec.addTransition(q0, q1, new CharTransitionLabel('a'));
        spec.addTransition(q1, q2, new CharTransitionLabel('b'));

        spec.markAsInitial(q0);
        spec.markAsFinal(q2);

        assertEquals(spec.countTransitions(), 2);

        //Test 3
        spec.addTransition(q2, q0, new CharTransitionLabel('c'));

        assertEquals(spec.countTransitions(), 3);

        //Test 4
        spec.addTransition(q0, q2, new CharTransitionLabel('d'));

        assertEquals(spec.countTransitions(), 4);

        //Test 5
        spec.addTransition(q2, q2, new CharTransitionLabel('d'));

        assertEquals(spec.countTransitions(), 5);
    }

    /**
     * Testuje działanie metody toString().
     */
    public final void testToString() {
        /**
         * Pozwala na wygenerowanie tekstu "oszukanego" automatu
         * na podstawie stanów i krawędzi wprowadzonych jako tekst.
         * Imitacja działania metody toString() dla automatu.
         */
        class AutomatonString {
            private String states, transitions, istates, fstates;

            public AutomatonString(String states, String transitions, String istates,
                    String fstates) {
                this.states = states;
                this.transitions = transitions;
                this.istates = istates;
                this.fstates = fstates;
            }

            /**
             * Zwraca "oszukany" automat w postaci tekstowej.
             */
            @Override
            public String toString() {
                StringBuffer str = new StringBuffer();
                str.append("Automaton:");
                str.append("\n-States: " + states);
                if (states.length() > 0) {
                    str.append(" ");
                }
                str.append("\n-Transitions:\n");
                if (transitions.length() > 0) {
                    str.append("  " + transitions.replaceAll(",", "\n  "));
                    str.append("\n");
                }
                str.append("-Initial state: " + istates);
                str.append("\n-Final states: " + fstates);
                if (fstates.length() > 0) {
                    str.append(" ");
                }
                return str.toString();
            }
        }

        // Pierwszy testowy automat:
        // Jeden stan z krawędzią
        NaiveAutomatonSpecification ta1 = new NaiveAutomatonSpecification();

        State ta1s0 = ta1.addState();
        ta1.addTransition(ta1s0, ta1s0, new CharTransitionLabel('a'));
        ta1.markAsInitial(ta1s0);
        ta1.markAsFinal(ta1s0);

        AutomatonString str;

        str = new AutomatonString("q0", "q0 -a-> q0", "q0", "q0");
        assertEquals(str.toString(), ta1.toString());

        // Drugi testowy automat:
        // Dwa stany, krawędzie jak w przypadku NFA
        NaiveAutomatonSpecification ta2 = new NaiveAutomatonSpecification();

        State ta2s0 = ta2.addState();
        State ta2s1 = ta2.addState();
        ta2.addTransition(ta2s0, ta2s0, new CharTransitionLabel('a'));
        ta2.addTransition(ta2s0, ta2s1, new CharTransitionLabel('a'));
        ta2.markAsInitial(ta2s0);
        ta2.markAsFinal(ta2s0);
        ta2.markAsFinal(ta2s1);

        String transitions = "q0 -a-> q0,q0 -a-> q1";
        str = new AutomatonString("q0 q1", transitions, "q0", "q0 q1");
        assertEquals(str.toString(), ta2.toString());

        // Trzeci testowy automat:
        // Dwa stany, dwa różne rodzaje krawędzi
        NaiveAutomatonSpecification ta3 = new NaiveAutomatonSpecification();

        State ta3s0 = ta3.addState();
        State ta3s1 = ta3.addState();
        ta3.addTransition(ta3s0, ta3s0, new CharTransitionLabel('a'));
        ta3.addTransition(ta3s0, ta3s1, new CharTransitionLabel('b'));
        ta3.addTransition(ta3s1, ta3s1, new CharTransitionLabel('a'));
        ta3.addTransition(ta3s1, ta3s1, new CharTransitionLabel('b'));
        ta3.markAsInitial(ta3s0);
        ta3.markAsFinal(ta3s1);

        transitions = "q0 -a-> q0,q0 -b-> q1,q1 -a-> q1,q1 -b-> q1";
        str = new AutomatonString("q0 q1", transitions, "q0", "q1");
        assertEquals(str.toString(), ta3.toString());

        // Czwarty testowy automat:
        // sprawdzamy co jest zwracane dla "pustej" instancji klasy automatu
        NaiveAutomatonSpecification ta4 = new NaiveAutomatonSpecification();

        str = new AutomatonString("", "", "", "");
        assertEquals(str.toString(), ta4.toString());
    }
    /**
     * Testuje działanie metody testPrefixChecker().
     */
    public final void testPrefixChecker() {
        AutomatonSpecification spec = new NaiveAutomatonSpecification();
        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();
        State q3 = spec.addState();
        State q4 = spec.addState();
        State q5 = spec.addState();

        spec.markAsInitial(q0);
        spec.markAsFinal(q3);

        spec.addTransition(q0, q1, new CharTransitionLabel('a'));
        spec.addTransition(q0, q1, new CharTransitionLabel('b'));
        spec.addTransition(q0, q3, new CharTransitionLabel('c'));

        spec.addTransition(q1, q3, new CharTransitionLabel('a'));
        spec.addTransition(q1, q2, new CharTransitionLabel('b'));
        spec.addTransition(q1, q2, new CharTransitionLabel('c'));

        spec.addTransition(q2, q3, new CharTransitionLabel('a'));
        spec.addLoop(q2, new CharTransitionLabel('b'));
        spec.addTransition(q2, q5, new CharTransitionLabel('c'));

        spec.addTransition(q3, q0, new CharTransitionLabel('a'));
        spec.addTransition(q3, q0, new CharTransitionLabel('b'));
        spec.addTransition(q3, q4, new CharTransitionLabel('c'));

        // Stan 4 jest pułapką

        spec.addLoop(q4, new CharTransitionLabel('a'));
        spec.addLoop(q4, new CharTransitionLabel('b'));
        spec.addLoop(q4, new CharTransitionLabel('c'));

        // Stan 5 prowadzi tylko do stanu 4

        spec.addLoop(q5, new CharTransitionLabel('a'));
        spec.addTransition(q5, q4, new CharTransitionLabel('b'));
        spec.addLoop(q5, new CharTransitionLabel('c'));

        assertTrue(spec.prefixChecker(q0));
        assertTrue(spec.prefixChecker(q1));
        assertTrue(spec.prefixChecker(q2));
        assertTrue(spec.prefixChecker(q3));
        assertFalse(spec.prefixChecker(q4));
        assertFalse(spec.prefixChecker(q5));
    }
}
