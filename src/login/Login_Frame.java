package login;

import java.awt.Image;
import setting.ImageFactory;
import setting.Single_screen_resolution;
import javax.swing.*;

  
public final class Login_Frame  extends JFrame implements Runnable{   //此登录页面采用Box布局方式；  
    /**
	 * wirten by RecluseXU
	 */
	private JLabel accountL,passwordL;
	private JTextField accountT,nameT;//两个输入框
    private JButton okB,registB,noInternetB;  //两个按钮
    private final Login_Control loginControl = new Login_Control();  
	//Thread
	Thread th = new Thread(this);
    
    public Login_Frame(){  
        init();  
    }
    void init(){
        int S_width = Single_screen_resolution.getSr_width();
        int S_height = Single_screen_resolution.getSr_height();
    	accountL = new JLabel("账号:"); 
        accountT = new JTextField(10);  
        passwordL = new JLabel("密码:"); 
        nameT = new JPasswordField(20);  
        okB = new JButton("登录");  
        registB = new JButton("注册");  
        noInternetB = new JButton("单机模式");
        JPanel ct = (JPanel)this.getContentPane();
        ct.setLayout(null);
        
        this.setBroundImage();//背景图片设置
        
        accountL.setBounds(S_width/6,S_height/28,S_width/14,S_height/28);
        ct.add(accountL);
        accountT.setBounds(S_width/6+S_width/35,S_height/28,S_width/11,S_height/32);
        ct.add(accountT);
        passwordL.setBounds(S_width/6,S_height/13,2*S_width/14,S_height/28);
        ct.add(passwordL);
        nameT.setBounds(S_width/6+S_width/35,S_height/13,S_width/11,S_height/32);
        ct.add(nameT);
        okB.setBounds(S_width/6,S_height/13+S_height/25,S_width/11+S_width/35,S_height/32);
        ct.add(okB);
        registB.setBounds(S_width/6,S_height/8+S_height/32,S_width/11+S_width/35,S_height/32);
        ct.add(registB);
        noInternetB.setBounds(S_width/6,S_height/8+S_height/15,S_width/11+S_width/35,S_height/32);
        ct.add(noInternetB);
        //添加点击事件
        okB.addActionListener(loginControl);  
        registB.addActionListener(loginControl);  
        noInternetB.addActionListener(loginControl);
        
        loginControl.setAccountT(accountT);  
        loginControl.setPasswordT(nameT);  
        loginControl.setButton(okB,registB,noInternetB);  
        
        
        setBounds(2*S_width/7,2*S_height/7,3*S_width/7,3*S_height/7);
        setTitle("用户登录界面");
        setDefaultCloseOperation(EXIT_ON_CLOSE);//按右上角的X时，关闭整个程序。
        setResizable(false);//设置禁止改动大小
        setVisible(true);
        th.start();
       
    }
    private final void setBroundImage()//背景图片
    {
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon img = ImageFactory.creatImageIcon("src/Data/background/login/login_background.jpg");  
        img.setImage(img.getImage().getScaledInstance(3*Single_screen_resolution.getSr_width()/7,3*Single_screen_resolution.getSr_height()/7, Image.SCALE_DEFAULT));
        JLabel background = new JLabel(img);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }
	@Override
	public void run() {
		try {
			while(setting.Single_State.isLogin()==false)
					Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		this.setVisible(false);
	}

      
}  