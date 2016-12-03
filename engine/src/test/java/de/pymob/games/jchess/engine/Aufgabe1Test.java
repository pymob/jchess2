package de.pymob.games.jchess.engine;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Labor 'Programmieren' WS2016
 * 
 * Aufgabe 1: Schachspiel nur mit Bauern.
 * 
 * @author M.Gr�ndel, M.Krone
 * 
 * SS 2012: Erste Version
 * WS 2016: Einige weitere �berpr�fungen in den Testf�llen eingef�gt
*/
public class Aufgabe1Test {	

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
	 /* -----------------------------------------
	  3 | BS | BS | BS |    |    | BW | BW | BW | 3
	    -----------------------------------------
	  2 | BS | BW | BS |    |    | BW | BS | BW | 2
	    -----------------------------------------
	  1 | BS | BS | BS |    |    | BW | BW | BW | 1
	    -----------------------------------------
	       a    b    c    d    e    f    g    h
	*/
	public void testGetFigur() {
		String stellung = "A3BS,B3BS,C3BS,A2BS,B2BW,C2BS,A1BS,B1BS,C1BS," +
				          "F3BW,G3BW,H3BW,F2BW,G2BS,H2BW,F1BW,G1BW,H1BW";
		ISpiel spielfeld = new Spiel(stellung, "W");
		
		for (int y = 3; y >= 1; y--)
		{
			for (char x = 'A'; x <= 'C'; x++)
			{
				String pos = x + "" + y;

				if (!pos.equals("B2"))
					assertEquals("Figur auf " + pos + " ungleich BS", spielfeld.getFigur(pos), "BS");
			}
		}
		
		assertEquals("Figur auf B2 ungleich BW", spielfeld.getFigur("B2"), "BW");
		
		for (int y = 3; y >= 1; y--)
		{
			for (char x = 'D'; x <= 'E'; x++)
			{		
				String pos = x + "" + y;
				assertTrue("Es steht eine Figur auf " + pos, spielfeld.getFigur(pos).isEmpty());		
			}
		}
		
		for (int y = 3; y >= 1; y--)
		{
			for (char x = 'F'; x <= 'H'; x++)
			{
				String pos = x + "" + y;

				if (!pos.equals("G2"))
					assertEquals("Figur auf " + pos + " ungleich BS", spielfeld.getFigur(pos), "BW");
			}
		}
		
		assertEquals("Figur auf B2 ungleich BS", spielfeld.getFigur("G2"), "BS");
	}

	
	@Test
	/*a     b     c     d     e     f     g     h
 	  -------------------------------------------------
	8 |     |     |     |     |     |     |     |     | 8
	  -------------------------------------------------
	7 | [B] | [B] | [B] | [B] | [B] | [B] | [B] | [B] | 7
	  -------------------------------------------------
	6 |     |     |     |     |     |     |     |     | 6
	  -------------------------------------------------
	5 |     |     |     |     |     |     |     |     | 5
	  -------------------------------------------------
	4 |     |     |     |     |     |     |     |     | 4
	  -------------------------------------------------
	3 |     |     |     |     |     |     |     |     | 3
 	  -------------------------------------------------
	2 | <B> | <B> | <B> | <B> | <B> | <B> | <B> | <B> | 2
	  -------------------------------------------------
	1 |     |     |     |     |     |     |     |     | 1
	  -------------------------------------------------
	 a     b     c     d     e     f     g     h
*/
	public void testGrundstellung() {
		ISpiel spielfeld = new Spiel();
		for (char spalte = 'A'; spalte <= 'H'; spalte++) {
			String ort = String.valueOf(spalte) + "2";
			assertEquals("Figur auf " + ort + " ungleich BW",
					spielfeld.getFigur(ort), "BW");
		}
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
                System.out.println(spielfeld.toString());
                assertTrue("Falsch plazierte Figur auf " + ort,
                        spielfeld.getFigur(ort).isEmpty());
            }
        }
	}

	@Test
	public void test_BW() {
		kannNichtZiehen("D2", "D2,E4,E3,E2,E1,D1,C1,C2,C3,C4", "D2BW", "W");

	}
	
	@Test
	 /* -----------------------------------------
	  3 |    |    |    |    |    |    |    |    | 3
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
	 /* -----------------------------------------
	  8 |    |    |    |    |    |    |    |    | 8
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
	public void testToString() {		
		String spielfeld = new Spiel().toString();		
		assertFalse("Spiel.toString == null", spielfeld == null);
		assertFalse("Spiel.toString.length == 0", spielfeld.length() == 0);
		assertFalse("Spiel.toString nicht �berschrieben", 
				spielfeld.startsWith(Spiel.class.getName()));
	}	
	
	
	//###########################################################
	//###########################################################
	   
	   /**
	    * Pr�ft, ob eine Figur n i c h t auf eine Feld gezogen werden kann.
	    * z.B.: kannNichtZiehen("D2", "D2,E3,E2,E1,D1,C1,C2,C3", "D2BW");
	    * @param ort - Ausgangsfeld, z.B.: "D2": String.
	    * @param ziel - Folge von Zielfeldern, z.B.: "D2,E3,E2": String.
	    * @param stellung - Vorgegebene Stellung, z.B.: "D2BW,E2BW": String.
	    * @param amZug - Spieler am Zug, "W" f�r wei�, "S" f�r schwarz: String.
	    */
	    private void kannNichtZiehen(String ort, String ziel,
	                                 String stellung, String amZug) {

	        ISpiel spielfeld = new Spiel(stellung, amZug);
	        String figur = spielfeld.getFigur(ort);
	        String[] zielFelder = ziel.split(",");
	        for (String feld : zielFelder) {
	            assertFalse(figur + " " + ort + " zieht nach " + feld,
	                       spielfeld.ziehe(ort, feld));
	            assertTrue(figur + "  " + ort + " zieht nach " + feld,
	                       comp(spielfeld, stellung));
	        }
	    }


	    /**
	     * Pr�ft, ob eine Figur auf eine Feld gezogen werden kann.
	     * z.B.: kannZiehen("D2", "D3,D4", "D2BW");
	     * @param ort - Ausgangsfeld, z.B.: "D2": String.
	     * @param ziel - Folge von Zielfeldern, z.B.: "D2,E3,E2": String.
	     * @param stellung - Vorgegebene Stellung, z.B.: "D2BW,E2BW": String.
	     * @param amZug - Spieler am Zug, "W" f�r wei�, "S" f�r schwarz: String.
	     */
	    private void kannZiehen(String ort, String ziel,
	                            String stellung, String amZug) {

	        String[] zielFelder = ziel.split(",");
	        for (String feld : zielFelder) {
	            ISpiel spielfeld = new Spiel(stellung, amZug);
	            String    figur = spielfeld.getFigur(ort);
	            String erwartet =
	                setzteFigur(loescheFigur(stellung, feld), ort, feld);
	            //System.out.println("Erwartet: " + erwartet);
	            assertTrue(figur + " " + ort + " zieht nicht nach " + feld,
	                       spielfeld.ziehe(ort, feld));
	            assertTrue(figur + " " + ort + " zieht nicht nach " + feld,
	                       comp(spielfeld, erwartet));
	        }
	    }
	    
	    /**
	     * Pr�ft eine Folge von Z�gen, ausgehend von einer vorgegebenen
	     * Ausgangsstellung bis zur erwartete Endstellung. Z�ge k�nnen
	     * g�ltig sein (z.B.: A2A4T) oder ungl�ltig sein (z.B.: A4A6F).
	     * 
	     * @param zugFolge - Folge von Z�gen, z.B.: "A2A4T,A4A6F" mit 
	     * T = true (g�ltiger Zug), F = false (ung�ltiger Zug): String.
	     * @param startStellung - Ausgangsposition der Figuren, bei
	     * length = 0 wird von der Grundstellung ausgegangen: String.
	     * @param endStellung - Erwartete Endposition der Figuren: String.
	     * @param amZug - Spieler am Zug, "W" = wei�, "S" = schwarz: String.
	     */    
	    @SuppressWarnings("unused")
		private void zugFolge(String zugFolge, String startStellung, 
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
	    	//System.out.println(spielfeld.getZugfolge());
	    	//System.out.println(spielfeld);    	
	    	assertTrue("Endstellung nicht korrekt", 
	    			comp(spielfeld, endStellung));
	    }


	    /**
	     * Vergleicht die Positionen der Figuren auf einem Spielfeld
	     * mit der erwarteten Stellung.
	     * 
	     * @param spielfeld - zu pr�fendes Spielfeld: Spiel. 
	     * @param stellung - erwartete Stellung der Figuren, 
	     * z.B.: "D2BW,E3BS,C3BS": String.
	     * @return true, wenn die Stellung der Figuren auf dem Spielfeld
	     * mit der erwarteten Stellung �bereinstimmt, sonst false: boolean.
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
	     * L�scht die Figur auf dem angegebenen Feld aus der �bergebenen 
	     * Stellung.
	     * 
	     * z.B.: LoescheFigur("D2BW,E3BS,C3BS", "E3");
	     * "D2BW,E3BS,C3BS" --> "D2BW,C3BS"
	   	 *
	     * @param stellung - Stellung der Figuren, 
	     * z.B.: "D2BW,E3BS,C3BS": String.
	     * @param feld - Bezeichnung des Felds, z.B.: "E3": String.
	     * @return ge�nderte Stellung der Figuren: String.
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
	     * Ver�ndert die Position einer Figur auf dem Spielfeld.
	     * 
	     * z.B.: setzteFigur("D2BW,E3BS,C3BS", "D2", "D3");
	     *       "D2BW,E3BS,C3BS" --> "D3BW,E3BS,C3BS"     
	     * @param stellung - Stellung der Figuren, 
	     * z.B.: "D2BW,E3BS,C3BS": String.
	     * @param ort - aktueller Standort der Figur, z.B.: "D2": String.
	     * @param ziel - neuer Standort der Figur, z.B.: "D3": String.
	     * @return ge�nderte Stellung der Figuren: String.
	     */
	    private String setzteFigur(String stellung, String ort, String ziel) {
	        char[] erwartet = stellung.toCharArray();
	        int       index = stellung.indexOf(ort);
	        erwartet[index]   = ziel.charAt(0);
	        erwartet[++index] = ziel.charAt(1);
	        return String.copyValueOf(erwartet);
	    }

}

