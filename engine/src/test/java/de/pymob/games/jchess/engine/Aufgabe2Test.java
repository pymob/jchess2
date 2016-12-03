package de.pymob.games.jchess.engine;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Labor 'Programmieren' WS2016
 * 
 * Aufgabe 2: Schachspiel mit allen Figuren.
 * 
 * @author M.Gründel, M.Krone
 * 
 * SS 2012: Erste Version
 * WS 2016: Einige weitere Überprüfungen in den Testfällen eingefügt
*/
public class Aufgabe2Test {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetFigur() {
        ISpiel spielfeld = new Spiel();
        //System.out.println(spielfeld);
        // Weisse Figuren ---------------------------------------
        assertEquals("Figur auf A1 ungleich TW",spielfeld.getFigur("A1"), "TW");
        assertEquals("Figur auf B1 ungleich SW",spielfeld.getFigur("B1"), "SW");
        assertEquals("Figur auf C1 ungleich LW",spielfeld.getFigur("C1"), "LW");
        assertEquals("Figur auf D1 ungleich DW",spielfeld.getFigur("D1"), "DW");
        assertEquals("Figur auf E1 ungleich KW",spielfeld.getFigur("E1"), "KW");
        assertEquals("Figur auf F1 ungleich LW",spielfeld.getFigur("F1"), "LW");
        assertEquals("Figur auf G1 ungleich SW",spielfeld.getFigur("G1"), "SW");
        assertEquals("Figur auf H1 ungleich TW",spielfeld.getFigur("H1"), "TW");
        for (char spalte = 'A'; spalte <= 'H'; spalte++) {
            String ort = String.valueOf(spalte) + "2";
            assertEquals("Figur auf " + ort + " ungleich BW",
            spielfeld.getFigur(ort), "BW");
        }

        //Schwarze Figuren --------------------------------------
        assertEquals("Figur auf A8 ungleich TS",spielfeld.getFigur("A8"), "TS");
        assertEquals("Figur auf B8 ungleich SS",spielfeld.getFigur("B8"), "SS");
        assertEquals("Figur auf C8 ungleich LS",spielfeld.getFigur("C8"), "LS");
        assertEquals("Figur auf D8 ungleich DS",spielfeld.getFigur("D8"), "DS");
        assertEquals("Figur auf E8 ungleich KS",spielfeld.getFigur("E8"), "KS");
        assertEquals("Figur auf F8 ungleich LS",spielfeld.getFigur("F8"), "LS");
        assertEquals("Figur auf G8 ungleich SS",spielfeld.getFigur("G8"), "SS");
        assertEquals("Figur auf H8 ungleich TS",spielfeld.getFigur("H8"), "TS");

        for (char spalte = 'A'; spalte <= 'H'; spalte++) {
            String ort = String.valueOf(spalte) + "7";
            assertEquals("Figur auf " + ort + " ungleich BS",
            spielfeld.getFigur(ort), "BS");
        }
        //-------------------------------------------------------
        for (char zeile = '3'; zeile <= '6'; zeile++) {
            for (char spalte = 'A'; spalte <= 'H'; spalte++) {
                String ort = String.valueOf(spalte) +
                             String.valueOf(zeile);
                assertTrue("Falsch plazierte Figur auf " + ort,
                        spielfeld.getFigur(ort).length() == 0);
            }
        }

    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Weisse Figuren
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Test
     /* -----------------------------------------
      3 |    |    | ** | ?? | ** |    |    |    | 3
        -----------------------------------------
      2 |    |    |    | BW |    |    |    |    | 2
        -----------------------------------------
      1 |    |    |    |    |    |    |    |    | 1
        -----------------------------------------
           a    b    c    d    e    f    g    h
    */
	public void testBauerWeissZieht() {
		kannZiehen("D2", "D3,D4", "D2BW", "W");
		//-------------------------------------------------------
		kannZiehen("D2", "E3,C3,D3,D4", "D2BW,E3BS,C3BS", "W");
		kannZiehen("D3", "E4,C4,D4", "D3BW,E4BS,C4BS", "W");
		kannZiehen("A2", "B3,A3,A4", "A2BW,B3BS", "W");
		kannZiehen("D2", "D3", "D2BW,D4BW", "W");
		kannZiehen("D2", "D3", "D2BW,D4BS", "W");
		//-------------------------------------------------------
		kannNichtZiehen("D2", "D3,D4", "D2BW", "S");
		//-------------------------------------------------------
		kannNichtZiehen("D2", "D2,E4,E3,E2,E1,D1,C1,C2,C3,C4", "D2BW", "W");
		kannNichtZiehen("D3", "D3,E5,E4,E3,E2,D2,C2,C3,C4,C5", "D3BW", "W");
		//-------------------------------------------------------		
		kannNichtZiehen("D2", "D4", "D2BW,D3BW", "W");
		//-------------------------------------------------------
		kannNichtZiehen("D2", "D4", "D2BW,D4BW", "W");
		//-------------------------------------------------------		
		kannNichtZiehen("D2", "D3,D4", "D2BW,D3BS", "W");
		//-------------------------------------------------------											
		kannNichtZiehen("D2", "D2,D3,D4,E3,E2,E1,D1,C1,C2,C3", 
		                "D2BW,D3BW,E3BW,E2BW,E1BW," +
		                "D1BW,C1BW,C2BW,C3BW", "W");		
	}


    @Test
    /*  -----------------------------------------
      5 |    |    |    |    |    |    |    |    | 5
        -----------------------------------------
      4 |    |    | ** | ** | ** |    |    |    | 4
        -----------------------------------------
      3 |    |    | ** | KW | ** |    |    |    | 3
        -----------------------------------------
      2 |    |    | ** | ** | ** |    |    |    | 2   
        -----------------------------------------
      1 |    |    |    |    |    |    |    |    | 1      
        -----------------------------------------
           a    b    c    d    e    f    g    h
    */
    public void testKoenigWeissZieht() {
        kannZiehen("D3", "C4,D4,E4,C3,E3,C2,E2,D2", "D3KW", "W");
        //-------------------------------------------------------
        kannZiehen("D3", "C4,D4,E4,C2,E2",
                   "C4BS,D4BS,E4BS,C3BS,D3KW,E3BS,C2BS,D2BS,E2BS", "W");
        kannNichtZiehen("D3", "C3,E3,D2",
                        "C4BS,D4BS,E4BS,C3BS,D3KW,E3BS,C2BS,D2BS,E2BS", "W");
        //-------------------------------------------------------
        kannNichtZiehen("D3", "C4,D4,E4,C3,D3,E3,C2,E2,D2",
                        "D3KW,D4BW,E4BW,E3BW,E2BW,D2BW,C2BW,C3BW,C4BW", "W");
        kannNichtZiehen("D3", "B1,C1,D1,E1,F1,B5,C5,D5,E5,F5," +
                        "B4,F4,B3,F3,B5,F5", "D3KW", "W");

        // MK
        kannZiehen("D3", "C3,E3,C2,D2,E2", "D3KW,D5KS", "W");
        //König kann nicht in Schach zeihen
        kannNichtZiehen("D3", "C4,D4,E4", "D3KW,D5KS", "W");
    }


    @Test
    /*  -----------------------------------------
      5 |    | ** |    | ** |    | ** |    |    | 5
        -----------------------------------------
      4 |    |    | ** | ** | ** |    |    |    | 4
        -----------------------------------------
      3 | ** | ** | ** | DW | ** | ** | ** | ** | 3
        -----------------------------------------
      2 |    |    | ** | ** | ** |    |    |    | 2
        -----------------------------------------
      1 |    | ** |    | ** |    | ** |    |    | 1
        -----------------------------------------
           a    b    c    d    e    f    g    h
    */
    public void testDameWeissZieht() {
        kannZiehen("D3", "B5,D5,F5,C4,D4,E4," +
                          "A3,B3,C3,E3,F3,G3,H3," +
                          "C2,D2,E2,B1,D1,F1", "D3DW", "W");
        kannZiehen("D3", "C4,D4,E4,C3,E3,C2,D2,E2",
                         "D3DW,C4BS,D4BS,E4BS,C3BS,E3BS,C2BS,D2BS,E2BS", "W");
        kannZiehen("D3", "B5,D5,F5,B3,F3,B1,D1,F1",
                          "D3DW,B5BS,D5BS,F5BS,B3BS,F3BS," +
                          "B1BS,D1BS,F1BS", "W");
        //-------------------------------------------------------
        kannNichtZiehen("D3", "D3,C5,E5,B4,F4,B2,F2,C1,E1",
                        "D3DW", "W");
        kannNichtZiehen("D3", "D3,C4,C5,D4,D5,E4,F5,C3,B3,E3,F3,C2,B1,D2,D1,E2,F1",
                        "D3DW,C4BW,D4BW,E4BW,C3BW,E3BW,C2BW,D2BW,E2BW", "W");

        kannNichtZiehen("D3", "D3,B5,D5,F5,B3,F3,B1,D1,F1", "D3DW,C4BS,D4BS,E4BS,C3BS,E3BS,C2BS,D2BS,E2BS", "W");
    }


    @Test
    /*  -----------------------------------------
      5 |    |    | ** |    | ** |    |    |    | 5
        -----------------------------------------
      4 |    | ** |    |    |    | ** |    |    | 4
        -----------------------------------------
      3 |    |    |    | SW |    |    |    |    | 3
        -----------------------------------------
      2 |    | ** |    |    |    | ** |    |    | 2
        -----------------------------------------
      1 |    |    | ** |    | ** |    |    |    | 1
        -----------------------------------------
           a    b    c    d    e    f    g    h
    */
    public void testSpringerWeissZieht() {
        kannZiehen("D3", "C5,E5,B4,F4,B2,F2,C1,E1", "D3SW", "W");
        kannZiehen("D3", "C5,E5,B4,F4,B2,F2,C1,E1",
                   "D3SW,C5BS,E5BS,B4BS,F4BS,B2BS,F2BS,C1BS,E1BS", "W");
        //-------------------------------------------------------
        kannNichtZiehen("D3", "D3,B5,D5,F5,C4,D4,E4,B3,C3,D3,E3,F3,C2,D2,E2," +
                              "B1,D1,F1", "D3SW", "W");
        kannNichtZiehen("D3", "D3,C5,E5,B4,F4,B2,F2,C1,E1",
                        "D3SW,C5BW,E5BW,B4BW,F4BW,B2BW,F2BW,C1BW,E1BW", "W");

        // MK (testen, ob der Springer auch über Figuren (Feind und Freund) springt)
        kannZiehen("D3", "C5,E5,B4,F4,B2,F2,C1,E1",
                   "D3SW,C4BS,D4BS,E4BS,C3BW,C2BW,D2BW,E2BW,E3BS", "W");
    }

    @Test
    /*  -----------------------------------------
      8 |    | ** |    |    |    | ** |    |    | 8
        -----------------------------------------
      7 |    |    | ** |    | ** |    |    |    | 7
        -----------------------------------------
      6 |    |    |    | LW |    |    |    |    | 6
        -----------------------------------------
      5 |    |    | ** |    | ** |    |    |    | 5
        -----------------------------------------
      4 |    | ** |    |    |    | ** |    |    | 4
        -----------------------------------------
           a    b    c    d    e    f    g    h
    */
    public void testLaeuferWeissZieht() {
        kannZiehen("D6", "C7,E7,B8,F8,C5,E5,B4,F4",
                   "D6LW", "W");
        //-------------------------------------------------------

        kannNichtZiehen("D6", "D6,C7,E7,B8,F8,C5,E5,B4,F4",
                        "D6LW", "S");
        kannNichtZiehen("D6", "D6,C8,D8,E8,B7,D7,F7,B6,C6,E6," +
                              "F6,B5,D5,F5,C4,D4,E4", "D6LW", "W");
        kannNichtZiehen("D6", "D6,C8,F8,B4,F4",
                              "D6LW,C7BS,E7BW,C5BS,E5BW", "W");
    }

    @Test
    /*  -----------------------------------------
      8 |    |    |    | ** |    |    |    |    | 8
        -----------------------------------------
      7 |    |    |    | ** |    |    |    |    | 7
        -----------------------------------------
      6 | ** | ** | ** | TW | ** | ** | ** | ** | 6
        -----------------------------------------
      5 |    |    |    | ** |    |    |    |    | 5
        -----------------------------------------
      4 |    |    |    | ** |    |    |    |    | 4
        -----------------------------------------
           a    b    c    d    e    f    g    h
    */
    public void testTurmWeissZieht() {    	
        kannZiehen("D6", "D8,D7,D5,D4,D3,D2,D1,A6,B6,C6,E6,F6,G6,H6",
                   "D6TW", "W");
        //-------------------------------------------------------
        kannNichtZiehen("D6", "D6,C7,E7,C5,E5", "D6TW", "W");
        kannNichtZiehen("D6", "D6,D8,D4,B6,F6", "D6TW,D7BS,D5BS,C6BS,E6BS", "W");

        // MK (auch testen, ob Turm nicht über eigene Figuren "springt")
        kannNichtZiehen("D6", "D8,D4,B6,F6", "D6TW,D7BW,D5BW,C6BW,E6BW", "W");
    }

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Schwarze Figuren
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Test
     /* -----------------------------------------
      8 |    |    | ** | ?? | ** |    |    |    | 8
        -----------------------------------------
      7 |    |    |    | BS |    |    |    |    | 7
        -----------------------------------------
      6 |    |    |    |    |    |    |    |    | 6
        -----------------------------------------
           a    b    c    d    e    f    g    h
    */
	public void testBauerSchwarzZieht() {		
		kannZiehen("D7", "D6,D5", "D7BS", "S");
		//-------------------------------------------------------
		kannZiehen("D7", "E6,C6,D6,D5", "D7BS,E6BW,C6BW", "S");
		kannZiehen("D6", "E5,C5,D5", "D6BS,E5BW,C5BW", "S");
		kannZiehen("A7", "B6,A6,A5", "A7BS,B6BW","S");
		kannZiehen("D7", "D6", "D7BS,D5BS", "S");
		kannZiehen("D7", "D6", "D7BS,D5BW", "S");
		//-------------------------------------------------------
		kannNichtZiehen("D7", "D6,D5", "D7BS", "W");
		//-------------------------------------------------------
		kannNichtZiehen("D7", "D7,E5,E6,E7,E8,D8,C8,C7,C6,C5", "D7BS", "S");
		kannNichtZiehen("D6", "D6,E4,E5,E6,E7,D7,C7,C6,C5,C4", "D6BS", "S");
		//-------------------------------------------------------		
		kannNichtZiehen("D7", "D5", "D7BS,D6BS", "S");
		//-------------------------------------------------------
		kannNichtZiehen("D7", "D5", "D7BS,D5BS", "S");	
		//-------------------------------------------------------
		kannNichtZiehen("D7", "D6,D5", "D7BS,D6BW", "S");
		//-------------------------------------------------------											
		kannNichtZiehen("D7", "D7,D6,D5,E6,E7,E8,D8,C8,C7,C6", 
		                "D7BS,D6BS,E6BS,E7BS,E8BS," +
		                "D8BS,C8BS,C7BS,C6BS", "S");			
	}


    @Test
    /*  -----------------------------------------
      8 |    |    |    |    |    |    |    |    | 8
        -----------------------------------------
      7 |    |    | ** | ** | ** |    |    |    | 7
        -----------------------------------------
      6 |    |    | ** | KS | ** |    |    |    | 6
        -----------------------------------------
      5 |    |    | ** | ** | ** |    |    |    | 5
        -----------------------------------------
      4 |    |    |    |    |    |    |    |    | 4
        -----------------------------------------
           a    b    c    d    e    f    g    h
    */
    public void testKoenigSchwarzZieht() {
        kannZiehen("D6", "C7,D7,E7,C6,E6,C5,D5,E5", "D6KS", "S");
        //-------------------------------------------------------
        kannZiehen("D6", "C7,E7,C5,D5,E5",
                   "D6KS,C7BW,D7BW,E7BW,C6BW,E6BW,C5BW,D5BW,E5BW", "S");
        kannNichtZiehen("D6", "D7,C6,E6",
                        "D6KS,C7BW,D7BW,E7BW,C6BW,E6BW,C5BW,D5BW,E5BW", "S");
        //-------------------------------------------------------
        kannNichtZiehen("D6", "D6,C7,D7,E7,C6,E6,C5,D5,E5",
                        "D6KS,C7BS,D7BS,E7BS,C6BS,E6BS,C5BS,D5BS,E5BS", "S");
        kannNichtZiehen("D6", "B8,C8,D8,E8,F8,B4,C4,D4,E4,F4," +
                              "B5,B6,B7,F5,F6,F7", "D6KS", "S");

        // MK
        kannZiehen("D6", "C6,E6,C5,D5,E5","D6KS,D8KW","S");
        //König kann nicht in Schach zeihen
        kannNichtZiehen("D6", "C7,D7,E7","D6KS,D8KW","S");
    }


    @Test
    /*  -----------------------------------------
      5 |    | ** |    | ** |    | ** |    |    | 5
        -----------------------------------------
      4 |    |    | ** | ** | ** |    |    |    | 4
        -----------------------------------------
      3 | ** | ** | ** | DS | ** | ** | ** | ** | 3
        -----------------------------------------
      2 |    |    | ** | ** | ** |    |    |    | 2
        -----------------------------------------
      1 |    | ** |    | ** |    | ** |    |    | 1
        -----------------------------------------
           a    b    c    d    e    f    g    h
    */
    public void testDameSchwarzZieht() {
        kannZiehen("D3", "B5,D5,F5,C4,D4,E4," +
                          "A3,B3,C3,E3,F3,G3,H3," +
                          "C2,D2,E2,B1,D1,F1", "D3DS", "S");
        kannZiehen("D3", "C4,D4,E4,C3,E3,C2,D2,E2",
                         "D3DS,C4BW,D4BW,E4BW,C3BW,E3BW,C2BW,D2BW,E2BW", "S");
        kannZiehen("D3", "B5,D5,F5,B3,F3,B1,D1,F1",
                          "D3DS,B5BW,D5BW,F5BW,B3BW,F3BW," +
                          "B1BW,D1BW,F1BW", "S");
        //-------------------------------------------------------
        kannNichtZiehen("D3", "D3,C5,E5,B4,F4,B2,F2,C1,E1",
                        "D3DS", "S");
        kannNichtZiehen("D3", "D3,C4,C5,D4,D5,E4,F5,C3,B3,E3,F3,C2,B1,D2,D1,E2,F1",
                        "D3DS,C4BS,D4BS,E4BS,C3BS,E3BS,C2BS,D2BS,E2BS", "S");

        kannNichtZiehen("D3", "D3,B5,D5,F5,B3,F3,B1,D1,F1", "D3DS,C4BW,D4BW,E4BW,C3BW,E3BW,C2BW,D2BW,E2BW", "S");
    }

    @Test
    /*  -----------------------------------------
      8 |    |    |    | ** |    |    |    |    | 8
        -----------------------------------------
      7 |    |    |    | ** |    |    |    |    | 7
        -----------------------------------------
      6 | ** | ** | ** | TS | ** | ** | ** | ** | 6
        -----------------------------------------
      5 |    |    |    | ** |    |    |    |    | 5
        -----------------------------------------
      4 |    |    |    | ** |    |    |    |    | 4
        -----------------------------------------
           a    b    c    d    e    f    g    h
    */
    public void testTurmSchwarzZieht() {
        kannZiehen("D6", "D8,D7,D5,D4,D3,D2,D1,A6,B6,C6,E6,F6,G6,H6",
                   "D6TS", "S");
        //-------------------------------------------------------
        kannNichtZiehen("D6", "D6,C7,E7,C5,E5", "D6TS", "S");
        kannNichtZiehen("D6", "D6,D8,D4,B6,F6", "D6TS,D7BW,D5BW,C6BW,E6BW", "S");

        // MK (auch testen, ob Turm nicht über eigene Figuren "springt")
        kannNichtZiehen("D6", "D8,D4,B6,F6", "D6TS,D7BS,D5BS,C6BS,E6BS", "S");
    }


    @Test
    /*  -----------------------------------------
      8 |    | ** |    |    |    | ** |    |    | 8
        -----------------------------------------
      7 |    |    | ** |    | ** |    |    |    | 7
        -----------------------------------------
      6 |    |    |    | LS |    |    |    |    | 6
        -----------------------------------------
      5 |    |    | ** |    | ** |    |    |    | 5
        -----------------------------------------
      4 |    | ** |    |    |    | ** |    |    | 4
        -----------------------------------------
           a    b    c    d    e    f    g    h
    */
    public void testLaeuferSchwarzZieht() {
        kannZiehen("D6", "C7,E7,B8,F8,C5,E5,B4,F4",
                   "D6LS", "S");
        //-------------------------------------------------------

        kannZiehen("D6", "C7,E7,B8,F8,C5,E5,B4,F4",
                   "D6LS", "S");

        kannNichtZiehen("D6", "D6,C8,D8,E8,B7,D7,F7,B6,C6,E6," +
                              "F6,B5,D5,F5,C4,D4,E4", "D6LS", "S");
        kannNichtZiehen("D6", "D6,C8,F8,B4,F4",
                              "D6LS,C7BW,E7BS,C5BW,E5BS", "S");
    }
    
    // ##########################################################
    
    @Test
    /*
	     a     b     c     d     e     f     g     h
	  -------------------------------------------------
    8 | [T] |     | [L] | [D] | [K] | [L] | [S] | [T] | 8
  	  -------------------------------------------------
	7 | [B] | [B] |     | [B] |     | [B] | [B] | [B] | 7
  	  -------------------------------------------------
	6 |     |     | [S] |     | [B] |     |     |     | 6
  	  -------------------------------------------------
	5 |     | <L> | [B] |     |     |     |     |     | 5
  	  -------------------------------------------------
	4 |     |     |     |     | <B> |     |     |     | 4
  	  -------------------------------------------------
	3 |     |     |     |     |     | <S> |     |     | 3
      -------------------------------------------------
	2 | <B> | <B> | <B> | <B> |     | <B> | <B> | <B> | 2
  	  -------------------------------------------------
	1 | <T> | <S> | <L> | <D> | <K> |     |     | <T> | 1
  	  -------------------------------------------------
     	 a     b     c     d     e     f     g     h
     */
    public void testZugfolge1() {
        zugFolge("E2E4T,C7C5T,G1F3T,B8C6T,F1B5T,E7E6T", "", 
        		 "A1TW,B1SW,C1LW,D1DW,E1KW,H1TW,A2BW,B2BW," +
        		 "C2BW,D2BW,F2BW,G2BW,H2BW,F3SW,E4BW,B5LW," +
        		 "A8TS,C8LS,D8DS,E8KS,F8LS,G8SS,H8TS,A7BS," +
        		 "B7BS,D7BS,F7BS,G7BS,H7BS,C6SS,E6BS,C5BS", "W");        
    }
    
    
    @Test
    /*
	     a     b     c     d     e     f     g     h
      -------------------------------------------------
	8 | [T] |     | [L] | [D] | [K] | [L] |     | [T] | 8
  	  -------------------------------------------------
	7 | [B] | [B] |     |     |     | [B] | [B] | [B] | 7
  	  -------------------------------------------------
	6 |     |     | [S] | [B] |     | [S] |     |     | 6
  	  -------------------------------------------------
	5 |     | <S> |     |     | [B] |     | <L> |     | 5
  	  -------------------------------------------------
	4 |     |     |     |     | <B> |     |     |     | 4
  	  -------------------------------------------------
	3 |     |     | <S> |     |     |     |     |     | 3
  	  -------------------------------------------------
	2 | <B> | <B> | <B> |     |     | <B> | <B> | <B> | 2
  	  -------------------------------------------------
	1 | <T> |     |     | <D> | <K> | <L> |     | <T> | 1
  	  -------------------------------------------------
     	 a     b     c     d     e     f     g     h
     */
    public void testZugfolge2() {
        zugFolge("E2E4T,C7C5T,B1C3T,B8C6T,G1E2T,G8F6T,D2D4T," +
        		 "C5D4T,E2D4T,E7E5T,D4B5T,D7D6T,C1G5T", "", 
        		 "A1TW,G5LW,D1DW,E1KW,F1LW,H1TW,A2BW,B2BW,C2BW," +
        		 "F2BW,G2BW,H2BW,C3SW,E4BW,B5SW," +
        		 "A8TS,C8LS,D8DS,E8KS,F8LS,H8TS,A7BS,B7BS,D6BS,F7BS," +
        		 "G7BS,H7BS,C6SS,F6SS,E5BS", "W");
    }
    
    
    
    
    @Test
    /*
	     a     b     c     d     e     f     g     h
      -------------------------------------------------
	8 |     |     |     |     | [K] |     |     |     | 8
  	  -------------------------------------------------
	7 |     |     |     |     |     |     |     |     | 7
  	  -------------------------------------------------
	6 |     |     |     |     |     |     |     |     | 6
  	  -------------------------------------------------
	5 |     |     |     |     | [T] |     | <L> |     | 5
  	  -------------------------------------------------
	4 |     |     |     |     | <T> |     |     |     | 4
  	  -------------------------------------------------
	3 |     |     |     |     |     |     |     |     | 3
  	  -------------------------------------------------
	2 |     |     |     |     |     |     |     |     | 2
  	  -------------------------------------------------
	1 |     |     |     |     |     |     |     |     | 1
  	  -------------------------------------------------
     	 a     b     c     d     e     f     g     h
     */
    public void testSchach() {
    	//Turm-------------------------------------------
    	kannZiehen("E5", "E6,E7,E4", "E8KS,E5TS,G5LW,E4TW", "S");  
    	kannNichtZiehen("E5", "D5,F5,G5", "E8KS,E5TS,G5LW,E4TW", "S");
    	//König------------------------------------------
    	kannZiehen("E8", "F8,F7,D7", "E8KS,E5TS,G5LW,E4TW", "S"); 
    	kannNichtZiehen("E8", "D8,E7", "E8KS,E5TS,G5LW,E4TW", "S");
    }    
    
    @Test
    public void testDiagonal() {
    	/* Weisse Figuren */
    	
    	// Läufer
    	kannZiehen("A1", "B2,C3,D4,E5,F6,G7,H8", "A1LW", "W");
    	kannNichtZiehen("A1", "F6,G7,H8", "A1LW,E5BS", "W");
    	kannNichtZiehen("A1", "H8", "A1LW,H8BW", "W");
    	
    	kannZiehen("A8", "B7,C6,D5,E4,F3,G2,H1", "A8LW", "W");
    	kannNichtZiehen("A8", "F3,G2,H1", "A8LW,E4BS", "W");
    	kannNichtZiehen("A8", "H1", "A8LW,H1BW", "W");    	
    	
    	
    	/* Schwarze Figuren */
    	
    	// Läufer
    	kannZiehen("A1", "B2,C3,D4,E5,F6,G7,H8", "A1LS", "S");
    	kannNichtZiehen("A1", "F6,G7,H8", "A1LS,E5BW", "S");
    	kannNichtZiehen("A1", "H8", "A1LS,H8BS", "S");
    	
    	kannZiehen("A8", "B7,C6,D5,E4,F3,G2,H1", "A8LS", "S");
    	kannNichtZiehen("A8", "F3,G2,H1", "A8LS,E4BW", "S");
    	kannNichtZiehen("A8", "H1", "A8LS,H1BS", "S");
    }    
    
    
    //###########################################################
   
   /**
    * Prüft, ob eine Figur n i c h t auf eine Feld gezogen werden kann.
    * z.B.: kannNichtZiehen("D2", "D2,E3,E2,E1,D1,C1,C2,C3", "D2BW");
    * @param ort - Ausgangsfeld, z.B.: "D2": String.
    * @param ziel - Folge von Zielfeldern, z.B.: "D2,E3,E2": String.
    * @param stellung - Vorgegebene Stellung, z.B.: "D2BW,E2BW": String.
    * @param amZug - Spieler am Zug, "W" für weiß, "S" für schwarz: String.
    */
    private void kannNichtZiehen(String ort, String ziel,
                                 String stellung, String amZug) {

        ISpiel spielfeld = new Spiel(stellung, amZug);
        String figur = spielfeld.getFigur(ort);
        String[] zielFelder = ziel.split(",");
        for (String feld : zielFelder) {
            assertFalse(figur + " " + ort + " zieht nach " + feld,
                       spielfeld.ziehe(ort, feld));
            //System.out.println(spielfeld.feldFigur(ort).getSpur());
            assertTrue(figur + "  " + ort + " zieht nach " + feld,
                       comp(spielfeld, stellung));
        }
    }


    /**
     * Prüft, ob eine Figur auf eine Feld gezogen werden kann.
     * z.B.: kannZiehen("D2", "D3,D4", "D2BW");
     * @param ort - Ausgangsfeld, z.B.: "D2": String.
     * @param ziel - Folge von Zielfeldern, z.B.: "D2,E3,E2": String.
     * @param stellung - Vorgegebene Stellung, z.B.: "D2BW,E2BW": String.
     * @param amZug - Spieler am Zug, "W" für weiß, "S" für schwarz: String.
     */
    private void kannZiehen(String ort, String ziel,
                            String stellung, String amZug) {

        String[] zielFelder = ziel.split(",");
        for (String feld : zielFelder) {
            ISpiel spielfeld = new Spiel(stellung, amZug);
            String    figur = spielfeld.getFigur(ort);           
            String erwartet =
                setzteFigur(loescheFigur(stellung, feld), ort, feld);         
            assertTrue(figur + " " + ort + " zieht nicht nach " + feld,
                       spielfeld.ziehe(ort, feld));            
            assertTrue(figur + " " + ort + " zieht nicht nach " + feld,
                       comp(spielfeld, erwartet));
        }
    }
    
    /**
     * Prüft eine Folge von Zügen, ausgehend von einer vorgegebenen
     * Ausgangsstellung bis zur erwartete Endstellung. Züge können
     * gültig sein (z.B.: A2A4T) oder unglültig sein (z.B.: A4A6F).
     * 
     * @param zugFolge - Folge von Zügen, z.B.: "A2A4T,A4A6F" mit 
     * T = true (gültiger Zug), F = false (ungültiger Zug): String.
     * @param startStellung - Ausgangsposition der Figuren, bei
     * length = 0 wird von der Grundstellung ausgegangen: String.
     * @param endStellung - Erwartete Endposition der Figuren: String.
     * @param amZug - Spieler am Zug, "W" = weiß, "S" = schwarz: String.
     */    
    private ISpiel zugFolge(String zugFolge, String startStellung, 
    		              String endStellung, String amZug) {
    	ISpiel spielfeld;
    	if (startStellung == null || startStellung.length() == 0) {
    		spielfeld = new Spiel();
    	} else {
    		spielfeld = new Spiel(startStellung, amZug);
    	}
    	String[] folge = zugFolge.split(",");
    	for (String zug : folge) {
    		String ort   = zug.substring(0, 2);
    		String ziel  = zug.substring(2, 4);
    		String figur = spielfeld.getFigur(ort);
    		switch (zug.charAt(4)) {    		
			case 'T': assertTrue (figur + " " + ort + 
						" zieht nicht nach " + 
						ziel,spielfeld.ziehe(ort, ziel)); break;
			case 'F': assertFalse(figur + " " + ort + 
						" zieht nach " + ziel, 
						spielfeld.ziehe(ort, ziel)); break;
			default:
				break;
			}
    	}    	
    	//System.out.println(spielfeld); 
    	if (endStellung != null && endStellung.length() > 0) {
    		assertTrue("Endstellung nicht korrekt", 
    				comp(spielfeld, endStellung));
    	}
    	return spielfeld;
    }


    /**
     * Vergleicht die Positionen der Figuren auf einem Spielfeld
     * mit der erwarteten Stellung.
     * 
     * @param spielfeld - zu prüfendes Spielfeld: Spiel. 
     * @param stellung - erwartete Stellung der Figuren, 
     * z.B.: "D2BW,E3BS,C3BS": String.
     * @return true, wenn die Stellung der Figuren auf dem Spielfeld
     * mit der erwarteten Stellung übereinstimmt, sonst false: boolean.
     */
    private boolean comp(ISpiel spielfeld, String stellung) {    	
        String[][] feld = new String[8][8];
        String[] elemente = stellung.split(",");
        char zeile, spalte;
        int z, s;
        //-------------------------------------------------------
        for (zeile  = '1', z = 0; zeile <= '8'; zeile++, z++) {
            for (spalte  = 'A', s = 0; spalte <= 'H'; spalte++, s++) {
                feld[z][s] = spielfeld.getFigur(String.valueOf(spalte) +
                                                String.valueOf(zeile));
            }
        }
        //-------------------------------------------------------
        for (String e : elemente) {
            z = e.charAt(1) - '0' - 1;
            s = e.charAt(0) - 'A';
            if (feld[z][s] == null || feld[z][s].length() == 0 ||
                feld[z][s].compareTo(e.substring(2, 4)) != 0) {
                return false;
            } else {
                feld[z][s] = "";
            }
        }
        //-------------------------------------------------------
        for (z = 0; z < 8; z++) {
            for (s = 0; s < 8; s++) {
                if (feld[z][s].length() != 0) {
                    return false;
                }
            }
        }
        //-------------------------------------------------------
        return true;
    }

    /**
     * Löscht die Figur auf dem angegebenen Feld aus der übergebenen 
     * Stellung.
     * 
     * z.B.: LoescheFigur("D2BW,E3BS,C3BS", "E3");
     * "D2BW,E3BS,C3BS" --> "D2BW,C3BS"
   	 *
     * @param stellung - Stellung der Figuren, 
     * z.B.: "D2BW,E3BS,C3BS": String.
     * @param feld - Bezeichnung des Felds, z.B.: "E3": String.
     * @return geänderte Stellung der Figuren: String.
     */
    private String loescheFigur(String stellung, String feld) {
        String neueStellung = "";        
        String[] part = stellung.split(",");
        for (String s : part) {           
            if (s.indexOf(feld) != 0) {
                neueStellung+= (neueStellung.length() > 0) ? "," + s : s;
            }
        }
        return neueStellung;
    }

    /**
     * Verändert die Position einer Figur auf dem Spielfeld.
     * 
     * z.B.: setzteFigur("D2BW,E3BS,C3BS", "D2", "D3");
     *       "D2BW,E3BS,C3BS" --> "D3BW,E3BS,C3BS"     
     * @param stellung - Stellung der Figuren, 
     * z.B.: "D2BW,E3BS,C3BS": String.
     * @param ort - aktueller Standort der Figur, z.B.: "D2": String.
     * @param ziel - neuer Standort der Figur, z.B.: "D3": String.
     * @return geänderte Stellung der Figuren: String.
     */
    private String setzteFigur(String stellung, String ort, String ziel) {
        char[] erwartet = stellung.toCharArray();
        int       index = stellung.indexOf(ort);
        erwartet[index]   = ziel.charAt(0);
        erwartet[++index] = ziel.charAt(1);
        return String.copyValueOf(erwartet);
    }

}


