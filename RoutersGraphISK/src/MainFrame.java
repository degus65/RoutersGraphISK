import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

public class MainFrame extends JFrame {

    public static final String START = "A";
    public static final String END = "H";

    private static final long serialVersionUID = -436042485570419777L;
    private static Viewer viewer;
    private static ViewPanel view;
    private JPanel buttonPanel = new JPanel();
    private static Graph g = new SingleGraph("ISK");
    private static Graph graphTemp = new SingleGraph("ISK");;
    public static String style = "graph { fill-color: #B0DA08; }  "
	    + "node { size: 20px, 20px; " + "shape: box; "
	    + "fill-color: green; " + "stroke-mode: plain; "
	    + "stroke-color: yellow; }  " + "node#" + START
	    + " { 	fill-color: #6CFDE0; }  " + "node#" + END
	    + " { 	fill-color: #0DCDA6; } "
	    + "node:clicked { 	fill-color: red; }  ";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	GraphDef.exampleGraph(g);
	GraphDef.exampleGraph(graphTemp);
	viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
	g.addAttribute("ui.stylesheet", style);
	System.setProperty("org.graphstream.ui.renderer",
		"org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	view = viewer.addDefaultView(false);
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    MainFrame frame = new MainFrame();
		    frame.setVisible(true);
		    frame.setSize(800, 600);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public MainFrame() {
	getContentPane().setLayout(new BorderLayout());
	getContentPane().add(view, BorderLayout.CENTER);

	getContentPane().add(buttonPanel, BorderLayout.SOUTH);

	JButton btnDistance = new JButton("Wektora odleg³oœci");
	btnDistance.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		transformGraphIntoDistanceExample();
		try {
		    doAlg();
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	});

	JButton btnLinkState = new JButton("£¹cze stan");
	btnLinkState.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		transformIntoLaczeStan();
		try {
		    doAlg();
		} catch (InterruptedException ea) {
		    // TODO Auto-generated catch block
		    ea.printStackTrace();
		}
	    }
	});
	buttonPanel.add(btnLinkState);
	buttonPanel.add(btnDistance);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void transformGraphIntoDistanceExample() {
	for (Edge e : g.getEachEdge()) {
	    e.setAttribute("length", 1);
	    e.removeAttribute("label");
	    e.setAttribute("ui.style", "fill-color: black;");
	}
	for (Node n : g)
	    n.setAttribute("ui.style", "fill-color: green;");

	g.getNode(START).setAttribute("ui.style", "fill-color: #6CFDE0;");
	g.getNode(END).setAttribute("ui.style", "fill-color: #0DCDA6;");
    }

    public static void transformIntoLaczeStan() {
	int i = 0;
	for (Edge e : g.getEachEdge()) {
	    e.setAttribute("length", GraphDef.lengths[i++]);
	    e.addAttribute("label", "" + (int) e.getNumber("length"));
	    e.setAttribute("ui.style", "fill-color: black;");
	}
	for (Node n : g) {
	    n.setAttribute("ui.style", "fill-color: green;");
	    n.addAttribute("label", n.getId());
	}
	g.getNode(START).setAttribute("ui.style", "fill-color: #6CFDE0;");
	g.getNode(END).setAttribute("ui.style", "fill-color: #0DCDA6;");
    }

    public static void doAlg() throws InterruptedException {
	Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");

	dijkstra.init(g);
	dijkstra.setSource(g.getNode(START));
	dijkstra.compute();

	List<Node> nodes = new ArrayList<Node>();
	List<Edge> edges = new ArrayList<Edge>();

	for (Node node : dijkstra.getPathNodes(g.getNode(END))) {
	    nodes.add(0, node);
	}

	for (Edge edge : dijkstra.getPathEdges(g.getNode(END)))
	    edges.add(0, edge);

	for (int i = 0; i < nodes.size(); i++) {
	    // Thread.sleep(1000);
	    nodes.get(i).addAttribute("ui.style", "fill-color: blue;");
	    // Thread.sleep(1000);
	    if (i < edges.size())
		edges.get(i).addAttribute("ui.style", "fill-color: red;");
	}
    }
}
