package music;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

final class Player_Mp3 extends Thread{
    Player player;
    File music;
    //���췽��
    public Player_Mp3(){}
    
    public void Load_Music(String fileName) {
        this.music = new File(fileName);
    }
    //��дrun����
    @Override
    public void run() {
        super.run();
        play_Music();
    }
    //���ŷ���
    public void play_Music(){

            BufferedInputStream buffer;
			try {
				buffer = new BufferedInputStream(new FileInputStream(music));

            player = new Player(buffer);
            player.play();			
            } catch (FileNotFoundException | JavaLayerException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
    }
}
