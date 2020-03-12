import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;

public class MesaInterface extends JFrame {

	private JPanel contentPane;
	private ArrayList<JLabel> labelsFilosofosArray = new ArrayList<JLabel>();
	Image circuloFome = new ImageIcon(getClass().getResource("\\Imagens\\circuloFome.png")).getImage();
	Image circuloPensando = new ImageIcon(getClass().getResource("\\Imagens\\circuloPensando.png")).getImage();
	Image circuloComendo = new ImageIcon(getClass().getResource("\\Imagens\\circuloComendo.png")).getImage();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MesaInterface frame = new MesaInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MesaInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 875, 889);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Image mesa = new ImageIcon(getClass().getResource("\\Imagens\\mesaF.png")).getImage();
		
		JLabel labelFilosofo1 = new JLabel("");
		labelFilosofo1.setBounds(111, 91, 201, 186);
		contentPane.add(labelFilosofo1);
		
		JLabel labelFilosofo2 = new JLabel("");
		labelFilosofo2.setBounds(27, 416, 201, 186);
		contentPane.add(labelFilosofo2);
		
		JLabel labelFilosofo3 = new JLabel("");
		labelFilosofo3.setBounds(358, 657, 233, 172);
		contentPane.add(labelFilosofo3);
		
		JLabel labelFilosofo4 = new JLabel("");
		labelFilosofo4.setBounds(670, 458, 201, 172);
		contentPane.add(labelFilosofo4);
		
		JLabel labelFilosofo5 = new JLabel("");
		labelFilosofo5.setBounds(581, 91, 201, 173);
		contentPane.add(labelFilosofo5);
		
		labelsFilosofosArray.add(labelFilosofo1);
		labelsFilosofosArray.add(labelFilosofo2);
		labelsFilosofosArray.add(labelFilosofo3);
		labelsFilosofosArray.add(labelFilosofo4);
		labelsFilosofosArray.add(labelFilosofo5);
		
		JLabel labelMesa = new JLabel("");
		labelMesa.setIcon(new ImageIcon(mesa));
		labelMesa.setBounds(0, 0, 831, 842);
		contentPane.add(labelMesa);
	}
	
	public void setEstadoFilosofoComendo(int numeroFilosofo) {
		labelsFilosofosArray.get(numeroFilosofo).setIcon(new ImageIcon(circuloComendo));
	}
	
	public void setEstadoFilosofoPensando(int numeroFilosofo) {
		labelsFilosofosArray.get(numeroFilosofo).setIcon(new ImageIcon(circuloPensando));
	}
	
	public void setEstadoFilosofoFome(int numeroFilosofo) {
		labelsFilosofosArray.get(numeroFilosofo).setIcon(new ImageIcon(circuloFome));
	}
}
