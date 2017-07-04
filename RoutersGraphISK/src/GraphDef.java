import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class GraphDef {

    public static int[] lengths = { 9, 5, 9, 7, 2, 10, 9, 11, 15, 10, 8, 6, 5,
	    15, 3, 4, 8 };

    public static void clear(Graph g) {
	g.clear();
	setStyleAndBackround(g);
    }

    public static void getExampleGraphById(int id, Graph g) {
	clear(g);
	if (id == 0) {
	    exampleGraph(g);
	} else if (id == 1) {
	    exampleGraph2(g);
	}
	lengths = getEdgeLenghtsArray(g);
    }

    public static void setStyleAndBackround(Graph g) {
	g.addAttribute("ui.stylesheet", Utils.style);
	System.setProperty("org.graphstream.ui.renderer",
		"org.graphstream.ui.j2dviewer.J2DGraphRenderer");
    }

    public static void exampleGraph(Graph g) {
	Utils.START = "A";
	Utils.END = "H";
	g.addNode("A").addAttribute("xy", 0, 1);
	g.addNode("B").addAttribute("xy", 1, 3);
	g.addNode("C").addAttribute("xy", 1, 1);
	g.addNode("D").addAttribute("xy", 1, 0);
	g.addNode("E").addAttribute("xy", 2, 3);
	g.addNode("F").addAttribute("xy", 2, 1);
	g.addNode("G").addAttribute("xy", 2, 0);
	g.addNode("H").addAttribute("xy", 3, 2);
	g.addNode("I").addAttribute("xy", 3, 1);
	g.addNode("J").addAttribute("xy", 0, 2);
	g.addEdge("AJ", "A", "J").addAttribute("length", 9);
	g.addEdge("JB", "J", "B").addAttribute("length", 5);
	g.addEdge("AC", "A", "C").addAttribute("length", 9);
	g.addEdge("AD", "A", "D").addAttribute("length", 7);
	g.addEdge("BC", "B", "C").addAttribute("length", 2);
	g.addEdge("CD", "C", "D").addAttribute("length", 10);
	g.addEdge("BE", "B", "E").addAttribute("length", 9);
	g.addEdge("CF", "C", "F").addAttribute("length", 11);
	g.addEdge("DF", "D", "F").addAttribute("length", 15);
	g.addEdge("DG", "D", "G").addAttribute("length", 10);
	g.addEdge("GF", "G", "F").addAttribute("length", 8);
	g.addEdge("EF", "E", "F").addAttribute("length", 6);
	g.addEdge("EH", "E", "H").addAttribute("length", 5);
	g.addEdge("HF", "H", "F").addAttribute("length", 15);
	g.addEdge("GI", "G", "I").addAttribute("length", 3);
	g.addEdge("IH", "I", "H").addAttribute("length", 4);
	g.addEdge("FI", "F", "I").addAttribute("length", 8);
	for (Node n : g)
	    n.addAttribute("label", n.getId());
	for (Edge e : g.getEachEdge())
	    e.addAttribute("label", "" + (int) e.getNumber("length"));
    }

    public static void exampleGraph2(Graph g) {
	g.addNode("A").addAttribute("xy", 4, 1);
	g.addNode("B").addAttribute("xy", 3, 2);
	g.addNode("C").addAttribute("xy", 5, 2);
	g.addNode("D").addAttribute("xy", 4, 3);
	g.addNode("E").addAttribute("xy", 4, 4);
	g.addNode("F").addAttribute("xy", 2, 3);
	g.addNode("G").addAttribute("xy", 3, 5);
	g.addNode("H").addAttribute("xy", 2, 6);
	g.addNode("I").addAttribute("xy", 1, 5);
	g.addNode("J").addAttribute("xy", 5, 5);
	g.addNode("K").addAttribute("xy", 6, 3);
	g.addNode("L").addAttribute("xy", 6, 6);
	g.addNode("M").addAttribute("xy", 7, 5);
	g.addNode("N").addAttribute("xy", 4, 6);
	g.addNode("O").addAttribute("xy", 3, 7);
	g.addNode("P").addAttribute("xy", 5, 7);
	g.addNode("R").addAttribute("xy", 4, 8);
	g.addNode("S").addAttribute("xy", 1, 1);

	g.addEdge("AB", "A", "B").addAttribute("length", 1);
	g.addEdge("AC", "A", "C").addAttribute("length", 1);
	g.addEdge("BD", "B", "D").addAttribute("length", 1);
	g.addEdge("CD", "C", "D").addAttribute("length", 1);
	g.addEdge("DE", "D", "E").addAttribute("length", 3);
	g.addEdge("IF", "I", "F").addAttribute("length", 1);
	g.addEdge("IH", "I", "H").addAttribute("length", 1);
	g.addEdge("FG", "F", "G").addAttribute("length", 1);
	g.addEdge("HG", "H", "G").addAttribute("length", 1);
	g.addEdge("GE", "G", "E").addAttribute("length", 3);
	g.addEdge("MK", "M", "K").addAttribute("length", 1);
	g.addEdge("ML", "M", "L").addAttribute("length", 1);
	g.addEdge("KJ", "K", "J").addAttribute("length", 1);
	g.addEdge("LJ", "L", "J").addAttribute("length", 1);
	g.addEdge("JE", "J", "E").addAttribute("length", 3);
	g.addEdge("RO", "R", "O").addAttribute("length", 1);
	g.addEdge("RP", "R", "P").addAttribute("length", 1);
	g.addEdge("ON", "O", "N").addAttribute("length", 1);
	g.addEdge("PN", "P", "N").addAttribute("length", 1);
	g.addEdge("NE", "N", "E").addAttribute("length", 3);
	g.addEdge("RM", "R", "M").addAttribute("length", 1);
	g.addEdge("KA", "K", "A").addAttribute("length", 1);
	g.addEdge("AS", "A", "S").addAttribute("length", 13);
	g.addEdge("SI", "S", "I").addAttribute("length", 16);
	for (Node n : g)
	    n.addAttribute("label", n.getId());
	for (Edge e : g.getEachEdge())
	    e.addAttribute("label", "" + (int) e.getNumber("length"));
    }

    public static int[] getEdgeLenghtsArray(Graph g) {
	int[] lengths = new int[g.getEdgeCount()];
	int i = 0;
	for (Edge e : g.getEachEdge())
	    lengths[i++] = e.getAttribute("length");
	return lengths;
    }

}