import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;

/** Ejercicio de creación de ventanas complejas
 * En este mismo paquete verás un fichero 
 *   <a href="file:./ej-sprites-1.jpg">ej-sprites-1.jpg</a>
 * con una propuesta de ventana. Diseña con las variantes que veas oportuna esa ventana (en principio, sin programarla).<br/>
 * Hay otro fichero con marcas:
 *   <a href="file:./ej-sprites-2.jpg">ej-sprites-2.jpg</a>
 * sobre el que puedes observar los comentarios de cada parte de la ventana. Lo que se pretende es:<br/>
 *  - Objetivo de la ventana: editar y visualizar animaciones de sprites partiendo de gráficos ya existentes<br/>
 *  - Funcionamiento: al pulsar el botón (2) aparecerá una selección de ficheros (JFileChooser) que permitirá
 *    seleccionar una CARPETA cualquiera del disco <br/>
 *  - Los ficheros png/jpg/gif [recomendado pngs transparentes] que estén en esa carpeta aparecerán en (1) <br/>
 *  - (1) es un JList con desplazamiento (dentro de un JScrollPane) donde aparecerán los nombres de ficheros existentes en la carpeta <br/>
 *  - Entre el botón (2) y la lista (1) aparece el nombre de la carpeta seleccionada (JLabel)<br/>
 *  - Al cambiar la carpeta se crea una selección nueva vacía (lista (5)) <br/>
 *  - Al hacer doble click en un fichero de (1) se añade al final de la lista (5) <br/>
 *  - Al seleccionar un fichero en (5) se ve en el panel de preview (3), centrado con respecto al panel <br/>
 *  - Los componentes que hay a la derecha de la lista (5) tienen los siguientes significados: <br/>
 *  &nbsp;&nbsp;- Un deslizador (JSlider) de nivel de zoom, desde 10% hasta 200% <br/>
 *  &nbsp;&nbsp;- Un deslizador (JSlider) de rotación, desde 0º hasta 360º <br/>
 *  &nbsp;&nbsp;- Un JTextField de desplazamiento a derecha de pixels (0 por defecto) <br/>
 *  &nbsp;&nbsp;- Un JTextField de desplazamiento abajo de pixels (0 por defecto) <br/>
 *  &nbsp;&nbsp;- Una indicación de milisegundos de cada imagen dentro de la secuencia del sprite (100 por defecto) <br/>
 *  &nbsp;&nbsp;Estos valores indicados configuran cada imagen dentro de la secuencia (y por tanto deben almacenarse y restaurarse por cada imagen de la secuencia). Lo demás es para toda la secuencia:<br/>
 *  &nbsp;&nbsp;- Checkbox de ciclo (al visualizar la secuencia, tras la última imagen vuelve a empezar la primera) <br/>
 *  &nbsp;&nbsp;- Botones de arriba/abajo que suben o bajan una posición la imagen seleccionada con respecto al resto de la lista (5) <br/>
 *  &nbsp;&nbsp;- Debajo de (5) dos JTextField de ancho y alto en píxels de toda la secuencia <br/>
 *  &nbsp;&nbsp;- (6) Botones de nuevo/save/load para reiniciar, guardar o cargar una secuencia configurada (en save y load aparecerá un cuadro de diálogo que pide el nombre y localización) <br/>
 *  - (7) es un panel donde se define la animación de movimiento del sprite en la "arena" (4). Su contenido es: <br/>
 *  &nbsp;&nbsp;- Píxels de origen x e y de la animación dentro de la arena<br/>
 *  &nbsp;&nbsp;- Velocidad inicial de la animación en píxels por segundo, con un cuadro de texto y un JSlider entre 0 y 200<br/>
 *  &nbsp;&nbsp;- Ángulo inicial de la animación en grados, con un cuadro de texto y un JSlider entre 0 y 90<br/>
 *  &nbsp;&nbsp;- Gravedad de la animación, con un JSlider entre 0.0 y 10.0  (9.8 por defecto)<br/>
 *  - (8) es un panel donde se complementa la animación de movimiento del sprite. Contenido: <br/>
 *  &nbsp;&nbsp;- Rotación de la animación en grados por segundo, con un cuadro de texto y un JSlider entre 0 y 360 (0 por defecto)<br/>
 *  &nbsp;&nbsp;- Zoom de la animación en % por segundo, con un cuadro de texto y un JSlider entre 50 y 200 (100 por defecto)<br/>
 *  &nbsp;&nbsp;- Checkbox de si la animación se hace cíclica (infinita) <br/>
 *  &nbsp;&nbsp;- Checkbox de si la animación se hace con retorno (va y vuelve) <br/>
 *  - (9) son los tres botones de animación:<br/>
 *  &nbsp;&nbsp;- Anima solo la secuencia sin mover el sprite en la arena<br/>
 *  &nbsp;&nbsp;- Hace el movimiento sin animar la secuencia (toma el sprite seleccionado actualmente)<br/>
 *  &nbsp;&nbsp;- Realiza a la vez las dos animaciones, la de secuencia y la de movimiento<br/>
 *  Otras anotaciones:<br/>
 *  - Valora los layouts más adecuados para cada panel. Intenta utilizar los más sencillos.<br/>
 *  - Los cuadros de texto que van con sliders asociados deben alimentarse mutuamente (si se cambia el slider cambia el texto y viceversa). Intenta hacerlo de una forma metódica en lugar de repitiendo código.<br/>
 *  - Haz una ventana interna (JInternalFrame) en lugar de un JFrame normal y así podría integrarse con otras ventanas de la misma aplicación<br/>
 *  - La Arena debería ocupar el máximo espacio posible de la ventana. El resto de los paneles el mínimo necesario.<br/>
 *  - En la zona (9) se podría añadir una JProgressBar que vaya mostrando la progresión de la animación en curso<br/>
 *  - Añade tooltips a las partes que consideres interesante<br/>
 *  - Si quieres probar los SplitPane puedes hacer uno entre (3) y (4)<br/>
 *  
 * <br/>
 * Programación posterior:<br/>
 *  Hay que definir modelos de datos para las listas (¿de qué tipo cada JList y cada modelo?)<br/>
 *  Hilos para los plays (¿cuántos? ¿cuándo?)<br/>
 *  Hay que usar alguna estructura (ArrayList por ejemplo) para guardar toda la configuración de la secuencia (¿varios arraylists o un arraylist de una clase nueva?)<br/>
 */
public class VentanaEdicionSprites extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public VentanaEdicionSprites() {
		
		this.setTitle("Ventana Sprites");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1200, 1000);
		this.setLocationRelativeTo(null);
		
		
		
		JList<String> lFicheros = new JList<>();
		JScrollPane spListaFicheros = new JScrollPane(lFicheros);
		JLabel lFichero = new JLabel("holaa");
		JButton bBuscar = new JButton("Buscar");
		
		JPanel pIzquerda = new JPanel();
		JPanel pCentral = new JPanel();
		pCentral.setLayout(new GridLayout(2, 1));
		JPanel pCentralArriba = new JPanel();
		JPanel pCentralAbajo = new JPanel();
		
		pCentral.add(pCentralArriba);
		pCentral.add(pCentralAbajo);
		
		pCentralAbajo.setLayout(new BorderLayout());
		
		pIzquerda.setLayout(new BoxLayout(pIzquerda, BoxLayout.Y_AXIS));
		pIzquerda.add(spListaFicheros);
		pIzquerda.add(lFichero);
		pIzquerda.add(bBuscar);
		
		//Panel Central Arriba
		///////////////////////////////////
		JPanel pPreview = new JPanel();
		JPanel pArena = new JPanel();
		
		pCentralArriba.add(pPreview);
		pCentralArriba.add(pArena);
		
		
		///////////////////////////////////
		
		
		JPanel pCentralIzquierdo = new JPanel();
		//Panel Central Izquierdo
		////////////////////////////////////
		JList<String> lSprites = new JList<>();
		JScrollPane spListaSprites = new JScrollPane(lSprites);
		JLabel lSecuencia = new JLabel("Secuencia");
		JTextField tfAncho = new JTextField();
		JTextField tfAlto = new JTextField();
		JSlider sZoom = new JSlider(10, 200);
		JSlider sRotacion = new JSlider(0, 360);
		JTextField tfDesDcha = new JTextField("0");
		JTextField tfDesAbajo = new JTextField("0");
		JTextField tfMilis = new JTextField("100");
		JCheckBox cbCiclo = new JCheckBox("Ciclo");
		JButton bArriba = new JButton("Arriba");
		JButton bAbajo = new JButton("Abajo");
		JButton bNuevo = new JButton("Nuevo");
		JButton bSave = new JButton("Save");
		JButton bLoad = new JButton("Load");
		
		JPanel pCentralIzda = new JPanel();
		JPanel pCentralDcha = new JPanel();
		JPanel pCentralIzdaAbajo = new JPanel();
		JPanel pCentralDchaPosicionamiento = new JPanel();
		JPanel pCentralDchaGuardaCarga = new JPanel();
		
		pCentralIzda.setLayout(new BoxLayout(pCentralIzda, BoxLayout.Y_AXIS));
		pCentralIzda.add(lSecuencia);
		pCentralIzda.add(spListaSprites);
		pCentralIzda.add(pCentralIzdaAbajo);
		pCentralIzdaAbajo.add(tfAncho); pCentralIzdaAbajo.add(tfAlto);
		
		pCentralDcha.setLayout(new BoxLayout(pCentralDcha, BoxLayout.Y_AXIS));
		pCentralDcha.add(sZoom); pCentralDcha.add(sRotacion); pCentralDcha.add(tfAncho); pCentralDcha.add(tfAlto);
		pCentralDcha.add(tfDesDcha); pCentralDcha.add(tfDesAbajo); pCentralDcha.add(tfMilis); pCentralDcha.add(cbCiclo);
		pCentralDcha.add(pCentralDchaPosicionamiento); pCentralDcha.add(pCentralDchaGuardaCarga);
		
		pCentralDchaPosicionamiento.add(bArriba);  pCentralDchaPosicionamiento.add(bAbajo);
		pCentralDchaGuardaCarga.add(bNuevo); pCentralDchaGuardaCarga.add(bLoad); pCentralDchaGuardaCarga.add(bSave);
		
		pCentralIzquierdo.setLayout(new GridLayout(1, 2));
		pCentralIzquierdo.add(pCentralIzda);
		pCentralIzquierdo.add(pCentralDcha);
		
		pCentralAbajo.add(pCentralIzquierdo, BorderLayout.WEST);
		///////////////////////////////////
		
		JPanel pCentralDerecha = new JPanel();
		//PanelCentral Derecha
		///////////////////////////////////
		
		JPanel pCentralDchaDcha = new JPanel();
		JPanel pCentralDchaIzda = new JPanel();
		JPanel pCentralDchaAbajo = new JPanel();
		
		JLabel lAnim = new JLabel("Animación");
		
		JPanel pOrigen = new JPanel();
		JPanel pVini = new JPanel();
		JPanel pAngulo = new JPanel();
		JPanel pGrav = new JPanel();
		
		pOrigen.add(new JLabel("Origen"));
		pOrigen.add(new JTextField("0"));
		pOrigen.add(new JTextField("0"));
		
		pVini.add(new JLabel("V.Ini."));
		pVini.add(new JTextField("0"));
		pVini.add(new JLabel("px/sg"));
		pVini.add(new JSlider(0, 200));
		
		pAngulo.add(new JLabel("Angulo"));
		pAngulo.add(new JTextField("0"));
		pAngulo.add(new JLabel("º"));
		pAngulo.add(new JSlider(0, 90));
		
		pGrav.add(new JLabel("Gravedad"));
		pGrav.add(new JTextField("0"));
		pGrav.add(new JLabel("px/sg^2"));
		pGrav.add(new JSlider(0, 10));
		
		pCentralDchaIzda.setLayout(new BoxLayout(pCentralDchaIzda, BoxLayout.Y_AXIS));
		pCentralDchaIzda.add(lAnim);
		pCentralDchaIzda.add(pOrigen);
		pCentralDchaIzda.add(pVini);
		pCentralDchaIzda.add(pAngulo);
		pCentralDchaIzda.add(pGrav);
		
		JPanel pRot = new JPanel();
		JPanel pZoom = new JPanel();
		
		pRot.add(new JLabel("Rotacion"));
		pRot.add(new JTextField("0"));
		pRot.add(new JLabel("º/sg"));
		pRot.add(new JSlider(0, 360));
		
		pZoom.add(new JLabel("Zoom"));
		pZoom.add(new JTextField("100"));
		pZoom.add(new JLabel("%"));
		pZoom.add(new JSlider(50, 200));
		
		pCentralDchaDcha.setLayout(new BoxLayout(pCentralDchaDcha, BoxLayout.Y_AXIS));
		pCentralDchaDcha.add(pRot);
		pCentralDchaDcha.add(pZoom);
		pCentralDchaDcha.add(new JCheckBox("Ciclo"));
		pCentralDchaDcha.add(new JCheckBox("Retorno"));
		
		JButton bSecuencia = new JButton("Secuencia");
		JButton bAnimacion = new JButton("Animacion");
		JButton bSecAnim = new JButton("Sec + Anim");
		
		pCentralDchaAbajo.add(bSecuencia);
		pCentralDchaAbajo.add(bAnimacion);
		pCentralDchaAbajo.add(bSecAnim);
		
		pCentralDerecha.setLayout(new BorderLayout());
		JPanel pAux = new JPanel();
		pCentralDerecha.add(pAux, BorderLayout.CENTER);
		pAux.setLayout(new GridLayout(1, 2));
		pAux.add(pCentralDchaIzda); pAux.add(pCentralDchaDcha);
		pCentralDerecha.add(pCentralDchaAbajo, BorderLayout.SOUTH);
		
		pCentralAbajo.add(pCentralDerecha, BorderLayout.CENTER);
		
		///////////////////////////////////
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pIzquerda, BorderLayout.WEST);
		getContentPane().add(pCentral, BorderLayout.CENTER);
		
		pCentral.setBorder(BorderFactory.createLineBorder(Color.black));
		
	}
	
	public static void main(String[] args) {
		VentanaEdicionSprites vES = new VentanaEdicionSprites();
		vES.setVisible(true);
		
	}
	
	

}
