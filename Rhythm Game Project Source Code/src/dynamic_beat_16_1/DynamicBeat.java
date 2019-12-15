// cont + shift + f : �ڵ� �� ����
package dynamic_beat_16_1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
//import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
//import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// cont + shift + o : import
public class DynamicBeat extends JFrame {

	// �׳� �̹����� ���� ���۸� ���� -> ���� ���۸� �̿�(�ż��� �̹��� ����)
	private Image screenImage;
	private Graphics screenGraphic;

	// �̹��� �������� (�ʱ�ȭ)
	// exit�� ���콺 �ø��� �� �ٲ��
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/startButtonEnteredImage1.png"));
	private ImageIcon endButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/endButtonEnteredImage1.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(
			Main.class.getResource("../images/startButtonBasicImage1.png"));
	private ImageIcon endButtonBasicImage = new ImageIcon(Main.class.getResource("../images/endButtonBasicImage1.png"));

	
	 // �ӵ����� private ImageIcon plusButtonImage = new
	 private ImageIcon plusButtonImage =  new ImageIcon(Main.class.getResource("../images/plusButton.png")); 
	 private ImageIcon minusButtonImage = new ImageIcon(Main.class.getResource("../images/minusButton.png"));


	// mode ��ư
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/Easy.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/Hard.png"));

	// ������ ���� �̵� ��ư
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButton.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButton.png"));

	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground(title).jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	// back��ư
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButton.png"));

	// ��ư �߰��� ���⵵ �߰�
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton endButton = new JButton(endButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);

	// �ӵ�����
	private JButton plusButton = new JButton(plusButtonImage);
	private JButton minusButton = new JButton(minusButtonImage);

	private int mouseX, mouseY;

	private boolean isMainScreen = false;
	private boolean isGameScreen = false;

	///// ���� ��ü�� �ֱ� //////
	ArrayList<Track> trackList = new ArrayList<Track>();

	private Image titleImage;
	private Music introMusic = new Music("intro.mp3", true);
	// ���õ� ���� �̹���
	private Image selectedImage;
	private Music selectedMusic;
	// ���� ���õ� ���� �ε���
	private int nowSelected = 0;

	// Game class - ������Ʈ ��ü���� �ϳ�
	public static Game game;

	public DynamicBeat() {
		// �ε��� ������� ������ �߻��� �� �־ �� ��������
		// ���� list
		trackList.add(new Track("Happy Life title image.png", "Happy Life start image.jpg", "Happy Life game image.jpg",
				"Fredji - Happy Life selected.mp3", "Fredji - Happy Life (Game).mp3", "Fredji - Happy Life"));
		trackList.add(new Track("Chilling title image.png", "Chilling start image.jpg", "Chilling game Image.png",
				"Music_cut.mp3", "Music_cut.mp3", "osh-va - Chilling"));
		trackList.add(new Track("Harmony title image.png", "Harmony start image.jpg", "Harmony game image.jpg",
				"Harmony2.mp3", "Harmony3.mp3", "Harmony - Ikson"));
		trackList.add(
				new Track("Good For You title image.png", "Good For You start image.jpg", "Good For You Game Image.png",
						"Lost Stars by Jung Kook.mp3", "Lost Stars by Jung Kook.mp3", "Good For You -THBD"));

		// �������� �� �⺻������ �����ϴ� �޴��ٰ� ������ ����
		setUndecorated(true);
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		backButton.setVisible(false);
		minusButton.setVisible(false);
		plusButton.setVisible(false);
		setTitle("Rhythm Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// �� �߾ӿ� �߰�
		setResizable(false);
		setLocationRelativeTo(null);
		// ����â�� �������� �� ���α׷� ��ü�� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���� ����â�� ���̰�
		setVisible(true);
		// paintComponent�� ������ ����� ���� ������� �ٲ�
		setBackground(new Color(0, 0, 0, 0));
		// ��ư�̳� JLabel������ �־��� �� ���� �� ��ġ �״��
		setLayout(null);

		addKeyListener(new KeyListener());

		// intro ����
		introMusic.start();

////////////////// exit��ư //////////////////
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				// ���콺�� �ö��� �� �հ��� ������� �ٲ��
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ��ư�� ���콺 �ø��� �Ҹ�����
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});

		add(exitButton);

//////////////////start��ư //////////////////
		startButton.setBounds(320, 300, 128, 128);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				// ���콺�� �ö��� �� �հ��� ������� �ٲ��
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ��ư�� ���콺 �ø��� �Ҹ�����
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();

				introMusic.close();

				// ���ǻ��� -> ��ü�� ���� ����
//				Music selectedMusic = new Music("Fools (COVER) By BTS.mp3",true);
//				selectedMusic.start();
				enterMain();

			}

		});

		add(startButton);

//////////////////end��ư //////////////////
		endButton.setBounds(780, 300, 128, 128);
		endButton.setBorderPainted(false);
		endButton.setContentAreaFilled(false);
		endButton.setFocusPainted(false);
		endButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				endButton.setIcon(endButtonEnteredImage);
				// ���콺�� �ö��� �� �հ��� ������� �ٲ��
				endButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ��ư�� ���콺 �ø��� �Ҹ�����
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				endButton.setIcon(endButtonBasicImage);
				endButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});

		add(endButton);

//////////////////left��ư //////////////////
		leftButton.setBounds(240, 310, 64, 64);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				leftButton.setIcon(leftButtonEnteredImage);
//				// ���콺�� �ö��� �� �հ��� ������� �ٲ��
//				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//				// ��ư�� ���콺 �ø��� �Ҹ�����
//				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
//				buttonEnteredMusic.start();
//			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				selectLeft();
			}
		});

		add(leftButton);

//////////////////right��ư //////////////////
		rightButton.setBounds(990, 310, 64, 64);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				selectRight();
			}
		});

		add(rightButton);
/////////////////�ӵ����� -��ư/////////////////////
minusButton.setBounds(500, 50, 64, 64);
minusButton.setBorderPainted(false);
minusButton.setContentAreaFilled(false);
minusButton.setFocusPainted(false);
minusButton.addMouseListener(new MouseAdapter() {

	@Override
	public void mouseExited(MouseEvent e) {
//		minusButton.setIcon(rightButtonBasicImage);
		minusButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
		buttonEnteredMusic.start();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Main.NOTE_SPEED > 1)
			Main.NOTE_SPEED--;
	}
});
add(minusButton);

/////////////////�ӵ����� +��ư/////////////////////
plusButton.setBounds(700, 50, 64, 64);
plusButton.setBorderPainted(false);
plusButton.setContentAreaFilled(false);
plusButton.setFocusPainted(false);
plusButton.addMouseListener(new MouseAdapter() {

	@Override
	public void mouseExited(MouseEvent e) {
//minusButton.setIcon(rightButtonBasicImage);
		plusButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
		buttonEnteredMusic.start();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Main.NOTE_SPEED < 10)
			Main.NOTE_SPEED++;
	}
});
add(plusButton);
//////////////////easy��ư //////////////////
		easyButton.setBounds(655, 0, 250, 70);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {

//			@Override
//			public void mouseExited(MouseEvent e) {
//			
//				
//			}

			@Override
			public void mousePressed(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
//				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();
				// easy ����
				gameStart(nowSelected, "Easy");
			}
		});

		add(easyButton);

//////////////////hard��ư //////////////////
		hardButton.setBounds(20, 20, 250, 70);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {

//			@Override
//			public void mouseExited(MouseEvent e) {
//				
//
//			}

			@Override
			public void mousePressed(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();

				// hard ����
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);

//////////////////back��ư //////////////////
		backButton.setBounds(50, 70, 70, 70);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				Music buttonEnteredMusic = new Music("ButtonPress.mp3", false);
				buttonEnteredMusic.start();

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// ���� ȭ������ ���ư��� �̺�Ʈ
				game.isGameEnd = false;
				backMain();
			}
		});
		add(backButton);

///////////////////// �޴��� ///////////////////
		// ��ġ�� ũ�� ������
		menuBar.setBounds(0, 0, 1280, 30);
		// ���콺 ��ġ�� ���� �۵��ϰ�
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();

			}
		});
		// �޴��ٸ� ��� �̵���ų �� �ְ� ����
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		// ���� ���� ���� ����
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
		
		if (isMainScreen) {
			g.drawImage(titleImage, 350, 40, null);
			g.drawImage(selectedImage, 350, 130, null);
			// �ӵ�����
			g.setFont(new Font("Arial", Font.PLAIN, 40));
			g.setColor(Color.BLACK);
			g.drawString(Main.NOTE_SPEED + "", 620, 90);
		}
	}

	public void screenDraw(Graphics2D g) {
		// background�� 0,0�� �׷���
		// �������� �̹��� �׷��� ��
		g.drawImage(background, 0, 0, null);

		// Ư�� �̹��� �׷��� ( start ������ �ٹ���Ʈ �׷��� )
		if (isMainScreen) {
			g.drawImage(selectedImage, 350, 130, null);
			g.drawImage(titleImage, 350, 40, null);
		}

		if (isGameScreen) {
			game.screenDraw(g);
		}

		if (Game.isGameEnd) {

			result();
			game.screenDraw2(g);
			g.setColor(Color.BLACK);
			g.drawRect(310, 134, 300, 350);
			g.drawImage(selectedImage, 628, 134, 450, 450, null);
		}
		// �׻� �����ϴ�(������) �̹����� paintComponents�� �׷��� ex) JLabel
		// �߰��Ȱ� �׷��� (add�� )
		paintComponents(g);
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �ż��� �ҷ�����
		this.repaint();
	}

	// javazoom.net -> project -> JLayer

	// ���õ� ���� ��ȣ �־ �˷���
	public void selectTrack(int nowSelected) {
		// ������ ���õǾ� �ִٸ� ����
		if (selectedMusic != null)
			selectedMusic.close();

		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	// ����, ������ ��ư ������ �� �Ѿ��

	public void selectLeft() {
		if (nowSelected == 0) {
			nowSelected = trackList.size() - 1;
		} else
			nowSelected--;
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1) {
			nowSelected = 0;
		} else
			nowSelected++;
		selectTrack(nowSelected);
	}

	// hard, easy��ư ���� ���ӽ���!
	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		minusButton.setVisible(false);
		plusButton.setVisible(false);

		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		backButton.setVisible(true);
		isGameScreen = true;

		game = new Game(trackList.get(nowSelected).gettitleName(), difficulty,
				trackList.get(nowSelected).getGameMusic());
		game.start();
		// ���� �����ӿ� Ű���� ��Ŀ�� ������ �����ٿ���
		setFocusable(true);
	}

	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		minusButton.setVisible(true);
		plusButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		backButton.setVisible(false);
		isGameScreen = false;
		backButton.setVisible(false);
		game.close();
		setFocusable(true);

	}

	public void enterMain() {
		startButton.setVisible(false);
		endButton.setVisible(false);

		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();

		isMainScreen = true;

		// ��ư ���̰�
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		minusButton.setVisible(true);
		plusButton.setVisible(true);
		
		introMusic.close();

		// ó������ ù° ����
		selectTrack(0);
	}

	public void result() {
		/*isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		// minusButton.setVisible(false);
		// plusButton.setVisible(false); */
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		background = new ImageIcon(Main.class.getResource("../images/resultImage.png")).getImage();
		backButton.setVisible(true);
		isGameScreen = false;
		game.isGameEnd = true;
		game.close();

	}

}
