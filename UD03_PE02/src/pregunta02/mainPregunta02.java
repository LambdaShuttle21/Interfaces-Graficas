/**
 * 
 */
package pregunta02;

import java.awt.EventQueue;

/**
 * 
 */
public class mainPregunta02 {

	/**
	 * 
	 */
	public mainPregunta02() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaMetodosPago frame = new ventanaMetodosPago();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
