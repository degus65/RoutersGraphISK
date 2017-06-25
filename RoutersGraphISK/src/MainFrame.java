import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    private static List<Node> colourNodes = new ArrayList<Node>();
    private static List<Edge> colourEdges = new ArrayList<Edge>();
    private static int edgesCounter = 0;
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
	viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

	g.addAttribute("ui.stylesheet", style);
	System.setProperty("org.graphstream.ui.renderer",
		"org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	view = viewer.addDefaultView(false);
	KeyListener keyListener = new KeyListener() {

	    @Override
	    public void keyPressed(KeyEvent arg0) {
	    }

	    @Override
	    public void keyReleased(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
		    if (edgesCounter < colourNodes.size()) {
			colourNodes.get(edgesCounter).addAttribute("ui.style",
				"fill-color: blue;");
		    }
		    if (edgesCounter < colourEdges.size()) {
			colourEdges.get(edgesCounter).addAttribute("ui.style",
				"fill-color: red;");
		    }
		    edgesCounter++;
		}

	    }

	    @Override
	    public void keyTyped(KeyEvent arg0) {
	    }

	};
	view.addKeyListener(keyListener);
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
	this.setTitle("Algorytmy routingu");
	getContentPane().setLayout(new BorderLayout());
	getContentPane().add(view, BorderLayout.CENTER);

	getContentPane().add(buttonPanel, BorderLayout.SOUTH);

	JButton btnDistance = new JButton("Wektora odleg³oœci");
	JButton btnLinkState = new JButton("£¹cze stan");

	btnDistance.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		btnDistance.setBackground(Color.GREEN);
		btnLinkState.setBackground(Color.WHITE);
		transformGraphIntoDistanceExample();
		try {
		    doAlg();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	});

	btnLinkState.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnDistance.setBackground(Color.WHITE);
		btnLinkState.setBackground(Color.GREEN);
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
	edgesCounter = 0;
	colourNodes.clear();
	colourEdges.clear();
	Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");

	dijkstra.init(g);
	dijkstra.setSource(g.getNode(START));
	dijkstra.compute();

	for (Node node : dijkstra.getPathNodes(g.getNode(END))) {
	    colourNodes.add(0, node);
	}

	for (Edge edge : dijkstra.getPathEdges(g.getNode(END)))
	    colourEdges.add(0, edge);

    }
}
