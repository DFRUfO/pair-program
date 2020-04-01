package control;

import java.util.List;
import java.io.IOException;

public class CompareAnswer {
	public int Comparable(double[] result,List list) throws IOException {
		
		int i,j=0,k=0;
		int correct = 0,wrong = 0;
		String c_message,w_message,c_number = "",w_number="";
		int[] correctNumber = new int[list.size()];
		int[] wrongNumber = new int[list.size()];
		for(i = 0 ; i < list.size() ; i++) {
			if(new Double(list.get(i).toString()) == result[i]){
				correct++;
				correctNumber[j] = i+1;
				j++;
			}
			else {
				wrong++;
				wrongNumber[k] = i+1;
				k++;
			}
		}
		
		Grade(correctNumber,wrongNumber,correct,list.size(),j,k);
		return correct;
	}
	
	public void Grade(int[] correctNumber,int[] wrongNumber,int correct,int list,int j,int k) throws IOException {
		WriteTxt wt = new WriteTxt();
		int i;
		String c_message,w_message,c_number = "",w_number="";
		for(i = 0; i < j; i++) {
			c_number += correctNumber[i] + "";
			if(i < j-1)
				c_number += ",";
			
		}
		c_message = "Correct:" + correct + "(" + c_number + ")";
		
		for(i = 0; i < k; i++) {
			w_number += wrongNumber[i] + "";
			if(i < k-1)
				w_number += ",";
			
		}
		w_message = "Wrong:" + (list-correct) + "(" + w_number + ")";
		wt.WriteTxt(c_message, "Grade.txt");
		wt.WriteTxt(w_message, "Grade.txt");
	}


}
