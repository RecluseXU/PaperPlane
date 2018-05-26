package frame.initial_interface;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import dao.DB_Business;
import setting.ImageFactory;
import setting.Single_State;
import setting.Single_screen_resolution;
import util.Exchange;
import util.ImageHelper;

final class ExPanel extends JPanel{
	private JButton sumit,reset;
	private JTextArea opinionTF;
	private String nowType;
	ExPanel(){init();}
	ExPanel(String type)
	{
		init();
		selectType(type);
	}
	void init()//��ʼ��Panel
	{
		this.setLayout(new BorderLayout());
		this.setBounds(Single_screen_resolution.getSr_width()/4, Single_screen_resolution.getSr_height()/4, Single_screen_resolution.getSr_width()/2,Single_screen_resolution.getSr_height()/2);//����λ�á���С
		this.setVisible(false);
	}
	void selectType(String type)
	{
		this.removeAll();
		if(type.equals("help"))
			this.addHelpPanel();
		if(type.equals("ranking"))
			try {
				this.addRankingPanel();
			} catch (SQLException e) {e.printStackTrace();}
		if(type.equals("opinion"))
			this.addOpinionPanel();
		this.repaint();
		this.nowType = type;
	}
	
	void addHelpPanel() {
		ImageHelper imhelper = new ImageHelper();
		JLabel help_l = new JLabel();//���һ����ʾ������JLable
		help_l.setIcon(imhelper.transform(ImageFactory.creatImageIcon("src/Data/background/help.jpg"),Single_screen_resolution.getSr_width()/2,Single_screen_resolution.getSr_height()/2));
		this.add(help_l, BorderLayout.CENTER);
	}	
	void addRankingPanel() throws SQLException {
		int S_width = Single_screen_resolution.getSr_width();
		int S_height = Single_screen_resolution.getSr_height();
		//ǰʮ����
		DB_Business db_b = new DB_Business("GetAllNote");
		JPanel con = new JPanel();
		con.setBounds(0,0, S_width/2, S_height/2);
		con.setLayout(null);
		JLabel jl;
		int n=1;
		for(String note : db_b.execute(null))
		{
			String[] a_note = note.split(" ");
			jl = new JLabel(a_note[0]);//����
			jl.setFont(new Font("����",Font.BOLD, 16));
			jl.setBounds(S_width/22, n*S_height/42, S_width/5, n*S_height/22);
			con.add(jl);
			jl = new JLabel(a_note[1]);//�÷�
			jl.setBounds(3*S_width/22, n*S_height/42, S_width/5, n*S_height/22);
			jl.setFont(new Font("����",Font.BOLD, 16));
			con.add(jl);
			n++;
		}
		//��������
		jl = new JLabel("�������");
		
		jl.setFont(new Font("����",Font.BOLD, 46));
		jl.setBounds(6*S_width/22, S_height/22,S_width/6,S_height/6);
		con.add(jl);
		
		if(Single_State.isNoInternet() == false)//�����½�ˣ���ʾ�Լ�������
		{
			db_b.business_select("GetMyRank");
			Exchange ex = new Exchange();
			jl = new JLabel(db_b.execute(ex.strs_to_strlist(Single_State.getAccount())).get(0));
			jl.setFont(new Font("����",Font.BOLD, 120));
			jl.setBounds(7*S_width/22, S_height/6,S_width/6,S_height/6);
			con.add(jl);
		}
		else
		{
			jl = new JLabel("δ��¼");
			jl.setFont(new Font("����",Font.BOLD, 35));
			jl.setBounds(6*S_width/22, S_height/6,S_width/6,S_height/6);
			con.add(jl);
		}
		//����
		ImageHelper imhelper = new ImageHelper();
		jl = new JLabel();
		jl.setIcon(imhelper.transform(ImageFactory.creatImageIcon("src/Data/background/demo.jpg"),Single_screen_resolution.getSr_width()/2,Single_screen_resolution.getSr_height()/2));
		jl.setBounds(0,0,Single_screen_resolution.getSr_width()/2,Single_screen_resolution.getSr_height()/2);
		con.add(jl);
		this.add(con, BorderLayout.CENTER);

	}
	void addOpinionPanel()
	{
		ImageHelper imhelper = new ImageHelper();
		JPanel con = new JPanel();
		int S_width = Single_screen_resolution.getSr_width();
		int S_height = Single_screen_resolution.getSr_height();
		
		//װ��panel����
		con.setLayout(null);
		con.setOpaque(false);
		//����ı���
		opinionTF = new JTextArea();
		opinionTF.setLineWrap(true);
		opinionTF.setFont(new Font("����",Font.PLAIN, 18));
		opinionTF.setBounds(30,40,S_width/2-60,S_height/2-80);
		con.add(opinionTF);
		//�ύ��ť
		sumit = new JButton("�ύ");
		sumit.setBounds(S_width/2/5+35, S_height/2-34, S_width/2/5-20, 28);
		sumit.addActionListener(new sumitBAction(this.sumit,this.opinionTF));
		con.add(sumit);
		reset = new JButton("����");
		reset.setBounds(3*S_width/2/5+35, S_height/2-34, S_width/2/5-20, 28);
		reset.addActionListener(new resetBAction(this.reset,this.opinionTF));
		con.add(reset);
		//����
		JLabel background = new JLabel();//���һ����ʾ������JLable
		background.setIcon(imhelper.transform(ImageFactory.creatImageIcon("src/Data/background/help.jpg"),Single_screen_resolution.getSr_width()/2,Single_screen_resolution.getSr_height()/2));
		background.setBounds(0,0,Single_screen_resolution.getSr_width()/2,Single_screen_resolution.getSr_height()/2);
		con.add(background);
		
		this.add(con);
	}
	public String getNowType() {
		return nowType;
	}
	class sumitBAction implements ActionListener
	{
		JButton sumit;
		JTextArea opinionTF;
		public sumitBAction(JButton b,JTextArea F) {
			super();
			this.sumit = b;
			this.opinionTF = F;
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Exchange exc = new Exchange();
			DB_Business dbb = new DB_Business("UpdateOpinion");
			if(Single_State.isNoInternet() != true)
				try {
					dbb.execute(exc.strs_to_strlist(opinionTF.getText()));
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			else
				opinionTF.setText("û������- -û�취�ϴ����Ŷ......");
		}
	}
	class resetBAction implements ActionListener
	{
		JButton sumit;
		JTextArea opinionTF;
		public resetBAction(JButton b,JTextArea F) {
			super();
			this.sumit = b;
			this.opinionTF = F;
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			opinionTF.setText("");
		}
	}

	
	
}
