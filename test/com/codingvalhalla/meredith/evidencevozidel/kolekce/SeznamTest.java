package com.codingvalhalla.meredith.evidencevozidel.kolekce;

import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Function;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tento test tridy Seznam byl vygenerovan automaticky a jen mirne upraven. Je
 * urcen jako ukazka testovaciho programu. Nejedna se tedy o uplny test tridy
 * Seznam.
 *
 * Ukolem je tento test doplnit o dalsi varianty overeni kazde metody tak, aby
 * se overilo co mozna nejvice kodu metod.
 *
 * @author kasi
 */
public class SeznamTest {

    public SeznamTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getVelikost method, of class Seznam.
     * @throws com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     */
    @Test
    public void testGetVelikost() throws KolekceException {
        System.out.println("getVelikost");
        Seznam<TestTrida> instance = new Seznam<>(1);
        int expResult = 1;
        int result = instance.getVelikost();
        assertEquals(expResult, result);

    }
    

    @Test(expected = KolekceException.class)
    public void testGetVelikost2() throws KolekceException {
        System.out.println("getVelikost2");
        Seznam<TestTrida> instance = new Seznam<>(-1);
        fail();
    }

    /**
     * Test of getPocet method, of class Seznam.
     * @throws com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     */
    @Test
    public void testGetPocet() throws KolekceException {
        System.out.println("getPocet");
        Seznam<TestTrida> instance = new Seznam<>(1);
        int expResult = 0;
        int result = instance.getPocet();
        assertEquals(expResult, result);

    }

    /**
     * Test of jePlny method, of class Seznam.
     * @throws com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     */
    @Test
    public void testJePlny() throws KolekceException {
        System.out.println("jePlny");
        Seznam<TestTrida> instance = new Seznam<>(1);
        boolean expResult = false;
        boolean result = instance.jePlny();
        assertEquals(expResult, result);

    }

    /**
     * Test of jePrazdny method, of class Seznam.
     * @throws com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     */
    @Test
    public void testJePrazdny() throws KolekceException {
        System.out.println("jePrazdny");
        Seznam<TestTrida> instance = new Seznam<>(1);
        assertTrue(instance.jePrazdny());

    }

    /**
     * Test of zrus method, of class Seznam.
     * @throws com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     */
    @Test
    public void testZrus() throws KolekceException {
        System.out.println("zrus");
        Seznam<TestTrida> instance = new Seznam<>(1);
        instance.pridej(new TestTrida(0));
        assertEquals(1, instance.getPocet());
        instance.zrus();
        assertEquals(0, instance.getPocet());
        assertTrue(instance.jePrazdny());
        assertFalse(instance.jePlny());
    }

    /**
     * Test of pridej method, of class Seznam.
     * @throws java.lang.Exception
     */
    @Test
    public void testPridej() throws Exception {
        System.out.println("pridej");
        TestTrida data = new TestTrida(1);
        Seznam<TestTrida> instance = new Seznam<>(2);
        instance.pridej(data);
        assertEquals(data, instance.najdi(data));
        instance.pridej(new TestTrida(2));
        assertEquals(2, instance.getPocet());
        assertEquals(2, instance.najdi(new TestTrida(2)).getA());
    }

    @Test(expected = KolekceException.class)
    public void testPridej2() throws Exception {
        System.out.println("pridej 2");
        Seznam<TestTrida> instance = new Seznam<>(3);
        instance.pridej(new TestTrida(0));
        instance.pridej(new TestTrida(1));
        instance.pridej(new TestTrida(2));
        assertEquals(3, instance.getPocet());
        instance.pridej(new TestTrida(3));
        fail();
    }

    /**
     * Test of najdi method, of class Seznam.
     * @throws com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     */
    @Test
    public void testNajdi() throws KolekceException {
        System.out.println("najdi");
        TestTrida data = new TestTrida(2);
        Seznam<TestTrida> instance = new Seznam<>(1);
        TestTrida expResult = null;
        TestTrida result = instance.najdi(data);
        assertEquals(expResult, result);
    }

    /**
     * Test of odeber method, of class Seznam.
     * @throws com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     */
    @Test
    public void testOdeber() throws KolekceException {
        System.out.println("odeber");
        TestTrida klic = new TestTrida(2);
        Seznam<TestTrida> instance = new Seznam<>(1);
        TestTrida expResult = new TestTrida(1);
        TestTrida result = instance.odeber(klic);
        assertEquals(null, result);
        instance.pridej(new TestTrida(2));
        result = instance.odeber(klic);
        assertEquals(klic, result);
        assertEquals(0, instance.getPocet());

    }

    /**
     * Test of toArray method, of class Seznam.
     * @throws com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     */
    @Test
    public void testToArray_0args() throws KolekceException {
        System.out.println("toArray");
        Seznam<TestTrida> instance = new Seznam<>(1);
        TestTrida[] expResult = new TestTrida[1];
        Object[] result = instance.toArray();
        assertArrayEquals(expResult, result);

    }

    /**
     * Test of toArray method, of class Seznam.
     * @throws com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     */
    @Test
    public void testToArray_GenericType() throws KolekceException {
        System.out.println("toArray_GenericType");
        TestTrida[] pole = new TestTrida[0];
        Seznam<TestTrida> instance = new Seznam<>(1);
        TestTrida[] expResult = new TestTrida[0];
        TestTrida[] result = instance.toArray(pole);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of toArray method, of class Seznam.
     * @throws com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     */
    @Test
    public void testToArray_Function() throws KolekceException {
        System.out.println("toArray_Function");
        Function<Integer, TestTrida[]> s = TestTrida[]::new; // n-> new TestTrida[n];
        Seznam<TestTrida> instance = new Seznam<>(1);
        TestTrida[] expResult = new TestTrida[0];
        TestTrida[] result = instance.toArray(s);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of iterator method, of class Seznam.
     * @throws com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     */
    @Test
    public void testIterator() throws KolekceException {
        System.out.println("iterator");
        Seznam<TestTrida> instance = new Seznam<>(1);
        Iterator result = instance.iterator();
        assertNotNull(result);

    }

    /**
     * Test of nastavPrvni method, of class Seznam.
     * @throws com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     */
    @Test(expected = KolekceException.class)
    public void testNastavPrvni() throws KolekceException {
        System.out.println("nastavPrvni");
        Seznam<TestTrida> instance = new Seznam<>(1);
        instance.nastavPrvni();

    }

    /**
     * Test of prejdiNaDalsi method, of class Seznam.
     * @throws com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     */
    @Test(expected = KolekceException.class)
    public void testPrejdiNaDalsi() throws KolekceException {
        System.out.println("prejdiNaDalsi");
        Seznam<TestTrida> instance = new Seznam<>(1);
        instance.prejdiNaDalsi();
        fail();
    }

    /**
     * Test of jeDalsi method, of class Seznam.
     * @throws com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     */
    @Test(expected = KolekceException.class)
    public void testJeDalsi() throws KolekceException {
        System.out.println("jeDalsi");
        Seznam<TestTrida> instance = new Seznam<>(1);
        boolean expResult = false;
        boolean result = instance.jeDalsi();
        fail();
        // assertEquals(expResult, result);

    }

    static class TestTrida<E> implements Serializable {

        private final int a;

        public TestTrida(int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 37 * hash + this.a;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final TestTrida other = (TestTrida) obj;
            return this.a == other.a;
        }

        @Override
        public String toString() {
            return "TestTrida{" + "a=" + a + '}';
        }

    }

}
