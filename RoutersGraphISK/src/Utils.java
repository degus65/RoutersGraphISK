
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

}
