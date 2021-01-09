package Practicas5y6;

import java.awt.*;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.table.*;
import javax.swing.tree.*;

@SuppressWarnings("serial")
public class VentanaGeneral extends JFrame implements ActionListener{
private static VentanaGeneral v;
	
	//Ficheros
	private static String path;
	private static File f;
	private static JFileChooser fc;
	private static FileNameExtensionFilter filter;
	private static File selectedFile;
	
	// Paneles
	private JPanel pNorte; // Etiqueta
	private JPanel pCentral;
	private JPanel pCIzq;
	private JPanel pCIzqNorte; // JTextArea
	private JPanel pcIzqSur; // JTree
	private JPanel pCIDrch; // JTable
	private JPanel pSur; // JProgressBar
	
	
	private JLabel lEtiqueta;
	private JTextField tf;
	private JTextArea ta;
	private JScrollPane sp;
	private JProgressBar pb;

	// Tabla
	private Object[][] data;
	private String[] columnNames = {
			"ID","SCREENNAME","TAGS", "AVATAR",
			"FOLLOWERSCOUNT","FRIENDSCOUNT", 
			"LANG","LASTSEEN","TWEETID", "FRIENDS"};
	private DefaultTableModel tableModel;
	private JTable table;
	private SimpleUT myList;
	
	// Arbol
	private JTree tree;
	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;

	public static void main(String[] args) {
		v = new VentanaGeneral("PRÁCTICAS 5 + 6");
		v.setVisible( true );
	}

	public VentanaGeneral(String titulo) {
		
		super(titulo);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 500);
		this.setLocationRelativeTo( null );
		getContentPane().revalidate();
		
		path = "/Users/ainhoaegia/git/Practicas-III/Practicas-ProgIII/src/Practicas5y6/data.csv";
		f = new File (path);
		fc = new JFileChooser(FileSystemView.getFileSystemView().getParentDirectory( f ));
		filter = new FileNameExtensionFilter("CSV Files", "csv");
		fc.setFileFilter(filter);
		
		int returnValue = fc.showOpenDialog(null);
		selectedFile = fc.getSelectedFile();
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			System.out.println("Path: " + selectedFile.getAbsolutePath());
		}
		
		pNorte = new JPanel();
		pCentral = new JPanel( new GridLayout(1, 2));
		pCIzq = new JPanel( new GridLayout(2, 1));
		pCIzqNorte = new JPanel();
		pcIzqSur = new JPanel();
		pCIDrch = new JPanel();
		pSur = new JPanel();
		
		pCIzq.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		pCIDrch.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		
		lEtiqueta = new JLabel("Etiqueta: ");
		tf = new JTextField( 30 );
		ta = new JTextArea( 20, 70 );
		sp = new JScrollPane( ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		
		int lenght = Math.toIntExact( selectedFile.length() );
		System.out.println( "La longitud del fichero es de " + lenght + " bytes.");
		
		pb = new JProgressBar( 0, lenght );
		pb.setStringPainted( true );
		pb.setValue( 0 );
		
		try (BufferedReader br = new BufferedReader(new FileReader( f ))) { // Cargar el fichero seleccionado al JTextArea
			ArrayList<String> lines = new ArrayList<>();
			String line;
			int bytesLeidos = 0;
			while ((line = br.readLine()) != null) {
				lines.add( line );
				int bytesLinea = line.getBytes().length;
				bytesLeidos+=bytesLinea;
				pb.setValue( bytesLeidos );
			}
			for (int i = 0; i < 20; i++) { // Para que no tarde en rellenar
				ta.append( lines.get( i ) + "\n" );
			}
		} catch (IOException e) {
			System.err.println( "Error leyendo el fichero seleccionado" );
		}
		
		// Tabla
		
		myList = new SimpleUT();
		myList.readFromCSV( path );
		data = myList.convert2Data();
		tableModel = new DefaultTableModel(data, columnNames){ public boolean isCellEditable(int row, int column) { return false; } };
		table = new JTable(tableModel);
		table.setAutoCreateRowSorter(true);
		table.setSize( pCIDrch.getWidth(), pCIDrch.getHeight() );
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setSize( pCIDrch.getWidth(), 70);

//		CustomTableCellRenderer renderer = new CustomTableCellRenderer();
//		renderer.setRowColor(0, Color.RED);
//		table.getColumnModel().getColumn(0).setCellRenderer(renderer);
		
		// JTree
		
		root = new DefaultMutableTreeNode("Root");
		tree = new JTree( root );
		model = (DefaultTreeModel)tree.getModel();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				String friends = table.getValueAt(row, 9).toString();
				DefaultMutableTreeNode friendsNode = new DefaultMutableTreeNode( friends );
				root.add( friendsNode );
				model.reload( root );
			}
		});

        
        // Paneles
		pNorte.add( lEtiqueta );
		pNorte.add( tf );
		pCIzq.add( pCIzqNorte );
		pCIzq.add( pcIzqSur );
		pCIzqNorte.add( sp );
		pcIzqSur.add( tree );
		pCIDrch.add(scrollPane);
		pCentral.add( pCIzq );
		pCentral.add( pCIDrch );
		pSur.add( pb );
		this.add( pNorte, BorderLayout.NORTH );
		this.add( pCentral, BorderLayout.CENTER );
		this.add( pSur, BorderLayout.SOUTH );
	}

	@Override
	public void actionPerformed(ActionEvent e) {}
}

//@SuppressWarnings("serial")
//class CustomTableCellRenderer extends DefaultTableCellRenderer {
//
//    private Map<Integer, Color> mapColors;
//
//    public CustomTableCellRenderer() {
//        mapColors = new HashMap<Integer, Color>();
//    }
//
//    public void setRowColor(int row, Color color) {
//        mapColors.put(row, color);
//    }
//
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
//
//        Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, 1);
//        Color color = mapColors.get(row);
//        if (color != null) {
//            cell.setBackground(color);
//        } else {
//            cell.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
//        }
//        return cell;
//    }
//}