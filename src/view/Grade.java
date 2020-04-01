package view;


import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Grade {
	public void grade(int correct,int wrong) {
		// TODO Auto-generated method stub
				DefaultPieDataset dataset = new DefaultPieDataset();
				//添加数据
				dataset.setValue("错误",wrong);
				dataset.setValue("正确",correct);
				JFreeChart chart = ChartFactory.createPieChart3D("您的答题情况", //主标题的名称
						                      dataset, //图标显示的数据集合
						                      true,//是否显示子标题 
						                      true,//是否生成提示的标签 
						                      true); //是否生成URL链接
				//处理图形上的乱码
				//处理主标题的乱码
				chart.getTitle().setFont(new Font("宋体",Font.BOLD,18));
				//处理子标题乱码
				chart.getLegend().setItemFont(new Font("宋体",Font.BOLD,15));
				//获取图表区域对象
				PiePlot3D categoryPlot = (PiePlot3D)chart.getPlot();
				//处理图像上的乱码
				categoryPlot.setLabelFont(new Font("宋体",Font.BOLD,15));
				//设置图形的生成格式为（上海 2 （10%））
				String format = "{0} {1} ({2})";
				categoryPlot.setLabelGenerator(new StandardPieSectionLabelGenerator(format));
				
				ChartFrame frame = new ChartFrame("Grade",chart);
				frame.setVisible(true);
				frame.pack();
	}


}