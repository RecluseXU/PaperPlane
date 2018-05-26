package music;

 //继承自线程类Thread
public final class Music_MP3 implements Music{
	private Player_Mp3 player;
	//无参构造
		public Music_MP3()
		{
			this.player = new Player_Mp3();
		}
		public void Load_Music(String fileName)
		{
			player.Load_Music(fileName);
		}
		public void play()
		{
			player.start();
		}
		public void stop()
		{
			player.stop();
		}
		public void reset()
		{
			this.player = new Player_Mp3();
		}
}