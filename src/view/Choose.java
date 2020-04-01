package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.CalculateImp;
import control.Create_title;
import control.ReadTxt;
import control.ServerImp;
import control.WriteTxt;

public class Choose extends JFrame{
	public Choose() {
		super("选择题目数量范围");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		this.setLocation(700, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		JLabel titleNumber = new JLabel("生成题目的数量：");
		JLabel titleRange = new JLabel("题目的范围：");
		Font font = new Font("微软雅黑", Font.BOLD, 15);
		titleNumber.setFont(font);
		titleRange.setFont(font);
		
		JTextField number = new JTextField();
		JTextField range = new JTextField();
		JButton create = new JButton("生成");
		
		titleNumber.setBounds(50, 50, 160, 50);
		number.setBounds(200, 50, 100, 40);
		titleRange.setBounds(50, 120, 150, 50);
		range.setBounds(200, 120, 100, 40);
		create.setBounds(150, 190, 100, 50);
		this.add(titleNumber);
		this.add(number);
		this.add(create);
		this.add(titleRange);
		this.add(range);
		this.setVisible(true);
		
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		        ReadTxt rt = new ReadTxt();
		        int Number = Integer.parseInt(number.getText());
		        int Range = Integer.parseInt(range.getText());
		        try {
		        	String[] title = title(Number,Range);
					double[] rs = rt.readTxt("Answers.txt");
					JOptionPane.showMessageDialog(null, "成功生成题目！");
					dispose();
			        Jtable jTable_01 = new Jtable(title,rs);
			        jTable_01.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "生成失败！");
				}   //答案文件
				
			}
		});
	}
	
	//生成题目、答案，并写入文件
	public String[] title(int number,int range) throws IOException {
		CalculateImp c = new CalculateImp();
        Create_title c2 = new  Create_title();
        WriteTxt wt = new WriteTxt();
        Vector title1 = new Vector();
        
        String title_path = "Exercises.txt";
        String result_path = "Answers.txt";
        
        HashSet set = new HashSet();
        ServerImp serverImp = new ServerImp();
        for(int i = 0 ; i < number ; i++) {
            String title =  c2.create_title(range,3);
            double result = Double.parseDouble(c.calculate(title.toCharArray()));
            
            if(result < 0.0 && i != 0) {
            		i--;
            		System.out.println(result);
	            	System.out.println("出现负数");
	                continue;
             }
            
            if(!serverImp.removeRepeat(title,set)){ //去除重复的函数，并把结果存储在set中
            	System.out.println("重复了");
            	i--;
            }
            
        }

        Iterator iterator = set.iterator();
        int j = 0;
        while (iterator.hasNext()){
            String title3 = (String)iterator.next();
           
            title1.add(title3);
            System.out.println("第" + String.valueOf(j+1) + "道题目:" + title3 + " =");
            wt.WriteTxt("第" + String.valueOf(j+1) + "道题目：" + title3 + " =", title_path);
            wt.WriteTxt("第" + String.valueOf(j+1) + "道题的答案：" + c.calculate(title3.toCharArray()),result_path);
            j++;
        }
        
        String[] title3 = new String[title1.size()];
        for(int i = 0;i<title1.size();i++){
            title3[i] = (String)title1.get(i);
        }
        return title3;
	}
}
