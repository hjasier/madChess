package componentes;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class userInfo extends JPanel{
	
	private JLabel userNameLabel;
	private JLabel userRankLabel;
	
	public userInfo(String userName, int userRank) {
		this.setLayout(new FlowLayout());
		
		userNameLabel = new JLabel(userName);
		userRankLabel = new JLabel(userRank+"");
		
		
		this.add(userNameLabel);
		this.add(userRankLabel);
		
	}
	
	
}
