package text;

import java.util.Scanner;

import music.Music;
import music.Music_MP3;

public class Main
{
	
	public static void main(String arg[])
	{
		Music mu = new Music_MP3();
		mu.Load_Music("src/Data/BGM/Initial_interface_BGM.mp3");
		mu.play();
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		mu.stop();
		scan.nextLine();
	}
}