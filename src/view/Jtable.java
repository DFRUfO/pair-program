package view;

import javax.swing.*;

import control.CompareAnswer;
import control.WriteTxt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Jtable extends JFrame {
	
	CompareAnswer response = new CompareAnswer();
	WriteTxt wt = new WriteTxt();
	Grade g = new Grade();
	
    List list = new ArrayList();//储存的答案

    public Jtable(String[] title, double[] rs) {

        super();
        setTitle("答题窗口");
        setBounds(800, 200, 400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] columnName = {"题目", "答案"};
        String[][] tableVlues = new String[title.length][2];
        for (int i = 0; i < title.length; i++) {
            tableVlues[i][0] = i+1 + "、" + title[i] + " =";
            tableVlues[i][1] = "";

        }
        JTable table = new JTable(tableVlues, columnName);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);//设置列不可拖动
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton commit = new JButton("提交");
        commit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = table.getRowCount();
                for (int i = 0; i < row; i++) {
                    list.add(table.getValueAt(i,1));
                }

//                System.out.println(response.Comparable(rs, list));
                try {
                	int correct = response.Comparable(rs, list);
					g.grade(correct, list.size()-correct);
					dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        getContentPane().add(commit, BorderLayout.SOUTH);


    }  
}
