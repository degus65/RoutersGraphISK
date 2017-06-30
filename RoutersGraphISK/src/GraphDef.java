import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class GraphDef {

    public static int[] lengths = { 9, 5, 9, 7, 2, 10, 9, 11, 15, 10, 8, 6, 5,
	    15, 3, 4, 8 };

    public static void clear(Graph g) {
	g.clearAttributes();
	for (Node n : g)
	    g.removeNode(n);
	for (Edge e : g.getEachEdge())
	    g.removeEdge(e);
	setStyleAndBackround(g);
    }

    public static void getExampleGraphById(int id, Graph g) {
	// clear(g);
	if (id == 1) {
	    exampleGraph(g);
	}
	// } else if (id == 2) {
	// exampleGraph2(g);
	// }
    }

    public static void setStyleAndBackround(Graph g) {
	g.addAttribute("ui.stylesheet", Utils.style);
	System.setProperty("org.graphstream.ui.renderer",
		"org.graphstream.ui.j2dviewer.J2DGraphRenderer");
    }

    public static void exampleGraph(Graph g) {
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
	// g.addEdge("EF", "E", "F").addAttribute("length", 6);
	// g.addEdge("EH", "E", "H").addAttribute("length", 5);
	// g.addEdge("HF", "H", "F").addAttribute("length", 15);
	// g.addEdge("GI", "G", "I").addAttribute("length", 3);
	// g.addEdge("IH", "I", "H").addAttribute("length", 4);
	// g.addEdge("FI", "F", "I").addAttribute("length", 8);
	for (Node n : g)
	    n.addAttribute("label", n.getId());
	for (Edge e : g.getEachEdge())
	    e.addAttribute("label", "" + (int) e.getNumber("length"));
    }

}