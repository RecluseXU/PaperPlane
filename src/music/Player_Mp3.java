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
    //构造方法
    public Player_Mp3(){}
    
    public void Load_Music(String fileName) {
        this.music = new File(fileName);
    }
    //重写run方法
    @Override
    public void run() {
        super.run();
        play_Music();
    }
    //播放方法
    public void play_Music(){

            BufferedInputStream buffer;
			try {
				buffer = new BufferedInputStream(new FileInputStream(music));

            player = new Player(buffer);
            player.play();			
            } catch (FileNotFoundException | JavaLayerException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
    }
}
