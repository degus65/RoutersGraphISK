import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = -436042485570419777L;
    private static List<Node> colourNodes = new ArrayList<Node>();
    private static List<Edge> colourEdges = new ArrayList<Edge>();
    private static int edgesCounter = 0;
    private static Viewer viewer;
    private static ViewPanel view;
    private JPanel menuPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private static Graph g = new SingleGraph("ISK");
    private int whichExample = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	GraphDef.exampleGraph(g);
	viewer = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
	GraphDef.setStyleAndBackround(g);
	view = viewer.addDefaultView(false);
	view.removeKeyListener(view.getKeyListeners()[0]);
	KeyListener keyListener = new KeyListener() {

	    @Override
	    public void keyPressed(KeyEvent arg0) {
	    }

	    @Override
	    public void keyReleased(KeyEvent evt) {
		int key = evt.getKeyCode();
		manageKey(key);
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
     * 
     * @throws IOException
     */
    public MainFrame() throws IOException {
	this.setTitle("Algorytmy routingu");
	getContentPane().setLayout(new BorderLayout());
	getContentPane().add(view, BorderLayout.CENTER);

	getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	getContentPane().add(menuPanel, BorderLayout.NORTH);

	JButton btnDistance = new JButton("Wektora odleg³oœci");
	JButton btnLinkState = new JButton("£¹cze stan");
	BufferedImage buttonIcon = ImageIO.read(new File("info-maly.png"));
	JButton infoButton = new JButton(new ImageIcon(buttonIcon));
	infoButton.setBorder(BorderFactory.createEmptyBorder());
	infoButton.setContentAreaFilled(false);

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
		view.grabFocus();
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
		    ea.printStackTrace();
		}
		view.grabFocus();
	    }
	});

	infoButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, Utils.info, "Wskazówki",
			JOptionPane.INFORMATION_MESSAGE);

	    }
	});

	buttonPanel.add(btnLinkState);
	buttonPanel.add(btnDistance);
	buttonPanel.add(infoButton);

	List<JButton> grapghDefsButtons = new ArrayList<>();
	for (int i = 0; i < Utils.numberOfExamples; i++) {
	    JButton button = new JButton("Przyk³ad " + (i + 1));
	    grapghDefsButtons.add(button);
	    button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    whichExample = grapghDefsButtons.indexOf(e.getSource());
		    GraphDef.getExampleGraphById(whichExample, g);
		}
	    });
	    menuPanel.add(button);
	}

	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void transformGraphIntoDistanceExample() {
	for (Edge e : g.getEachEdge()) {
	    e.setAttribute("length", 1);
	    e.setAttribute("ui.style", "fill-color: " + Utils.edgeNormalColour);
	}
	for (Node n : g)
	    n.setAttribute("ui.style", "fill-color: " + Utils.nodeNormalColour);

	g.getNode(Utils.START).setAttribute("ui.style",
		"fill-color: " + Utils.startColour);
	g.getNode(Utils.END).setAttribute("ui.style",
		"fill-color: " + Utils.endColour);
    }

    public static void transformIntoLaczeStan() {
	int i = 0;
	for (Edge e : g.getEachEdge()) {
	    e.setAttribute("length", GraphDef.lengths[i++]);
	    e.addAttribute("label", "" + (int) e.getNumber("length"));
	    e.setAttribute("ui.style", "fill-color: " + Utils.edgeNormalColour);
	}
	for (Node n : g) {
	    n.setAttribute("ui.style", "fill-color: " + Utils.nodeNormalColour);
	    n.addAttribute("label", n.getId());
	}
	g.getNode(Utils.START).setAttribute("ui.style",
		"fill-color: " + Utils.startColour);
	g.getNode(Utils.END).setAttribute("ui.style",
		"fill-color: " + Utils.endColour);
    }

    public static void doAlg() throws InterruptedException {
	edgesCounter = 0;
	colourNodes.clear();
	colourEdges.clear();
	Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");

	dijkstra.init(g);
	dijkstra.setSource(g.getNode(Utils.START));
	dijkstra.compute();

	for (Node node : dijkstra.getPathNodes(g.getNode(Utils.END))) {
	    colourNodes.add(0, node);
	}

	for (Edge edge : dijkstra.getPathEdges(g.getNode(Utils.END)))
	    colourEdges.add(0, edge);

    }

    public static void manageKey(int key) {
	if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_RIGHT) {
	    if (edgesCounter < colourNodes.size()) {
		colourNodes.get(edgesCounter).addAttribute("ui.style",
			"fill-color: " + Utils.nodeMarkedColour);
	    }
	    if (edgesCounter < colourEdges.size()) {
		colourEdges.get(edgesCounter).addAttribute("ui.style",
			"fill-color: " + Utils.edgeMarkedColour);
	    }
	    if (edgesCounter <= colourNodes.size() - 1)
		edgesCounter++;
	} else if (key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_LEFT) {
	    if (edgesCounter >= 0) {
		if (edgesCounter < colourNodes.size()) {
		    if (edgesCounter == colourNodes.size() - 1) {
			colourNodes.get(edgesCounter).addAttribute("ui.style",
				"fill-color: " + Utils.endColour);
		    } else if (edgesCounter == 0) {
			colourNodes.get(edgesCounter).addAttribute("ui.style",
				"fill-color: " + Utils.startColour);
		    } else {
			colourNodes.get(edgesCounter).addAttribute("ui.style",
				"fill-color: " + Utils.nodeNormalColour);
		    }
		}
		if (edgesCounter < colourEdges.size()) {
		    colourEdges.get(edgesCounter).addAttribute("ui.style",
			    "fill-color: " + Utils.edgeNormalColour);
		}
		if (edgesCounter > 0)
		    edgesCounter--;
	    }
	}
    }
}
