
public class Utils {

    public static final int numberOfExamples = 2;

    public static final String START = "A";
    public static final String END = "H";

    public static final String startColour = "#6CFDE0;";
    public static final String endColour = "#0DCDA6;";
    public static final String edgeNormalColour = "black;";
    public static final String edgeMarkedColour = "red;";
    public static final String nodeNormalColour = "green;";
    public static final String nodeMarkedColour = "blue;";

    public static String style = "graph { fill-color: #B0DA08; }  "
	    + "node { size: 20px, 20px; " + "shape: box; "
	    + "fill-color: green; " + "stroke-mode: plain; "
	    + "stroke-color: yellow; }  " + "node#" + START
	    + " { 	fill-color: #6CFDE0; }  " + "node#" + END
	    + " { 	fill-color: #0DCDA6; } "
	    + "node:clicked { 	fill-color: red; }  ";

    public static String info = "Wskaz�wki:\n"
	    + "Za pomoc� przycisk�w w g�rnym menu mo�na prze��cza� si� pomi�dzy przyk�adami.\n"
	    + "Przyciski w dolnym menu s�u�� do wyboru algorytmu routingu.\n"
	    + "Nale�y wybra� jeden z algorytm�w, aby m�c obejrze� poszczeg�lne etapy rozwi�zania.\n"
	    + "Kroki algorytmu mo�na obejrze� naciskaj�c spacj� lub strza�k� w prawo.\n"
	    + "Aby powr�ci� do wcze�niejszego kroku nale�y wcisn�� backspace lub strza�k� w lewo.";

}
