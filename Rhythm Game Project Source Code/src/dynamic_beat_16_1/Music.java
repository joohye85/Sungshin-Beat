package dynamic_beat_16_1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

// Thread : �ϳ��� ���� ���α׷�
public class Music extends Thread{
	
	// �ٿ���� �ڹ����� ���̺귯��
	private Player player;
	private Boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	// ������
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			
			// �ش� ���� �о�� �� �ֵ���
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			
			// �ش� ���� ���� �� �ֵ���
			player = new Player(bis);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// ���� �������� ������ ���� ������ġ
	public int getTime() {
		if(player == null)
			return 0;
		return player.getPosition();
	}
	
	// �׻� ������ �� �ֵ���
	public void close() {
		isLoop = false;
		player.close();
		// ���� �������ִ� ���α׷��� ������ �װ� �������ִ°� interrupt
		this.interrupt();
	}
	
	// thread ��ӹ����� ������ ���
	@Override
	public void run() {
		try {
			// �����
			do {
				player.play();
				// �ش� ���� �о�� �� �ֵ���
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				
				// �ش� ���� ���� �� �ֵ���
				player = new Player(bis);
			// isLoop�� true �� ���ѹݺ�
			} while(isLoop);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
