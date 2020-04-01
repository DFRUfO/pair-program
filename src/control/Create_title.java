package control;

import java.util.Random;


public class Create_title {


    public String create_title(int range, int num2) {//num:提目数 range:范围 num2:题目组成数量

        Random random = new Random(range);
        String[] char_arrays = null;
        int num3 = (int) (Math.random() * num2);//分数的个数
        int num4 = num2 - num3;//正数个数
        int charNum = num2 - 1;//符号个数

        int bracketL = 0;//(位置

        int[] int_arrays = new int[num4 + num3 * 2];
        boolean flag = isBracket();
        if (flag) {
            char_arrays = returnChar(num3, charNum + 2, flag);

        } else {
            char_arrays = returnChar(num3, charNum, false);
        }


        for (int w = 0; w < num2; w++) {
            int_arrays[w] = random.nextInt(range);
        }
        String title = "";
        if (char_arrays[0].equals("(")) {
            for (int i = 0; i < num2; i++) {
                title += char_arrays[i] + ((int) (Math.random() * range) + "");
            }
            if (char_arrays[num2].equals(")")) {
                title += ")";
            }

        } else {
            for (int i = 0; i <= num2; i++) {
                title += ((int) (Math.random() * range) + "") + char_arrays[i];
            }
        }
        if (title.charAt(title.length() - 1) == '+' || title.charAt(title.length() - 1) == '-' || title.charAt(title.length() - 1) == '/' || title.charAt(title.length() - 1) == '*') {
            title = title.substring(0, title.length() - 1);
        }


        return title;
    }

    boolean isBracket() {
        return true;
    }




    String[] returnChar(int mark, int charNum, boolean flag) {
        Random random = new Random();
        String[] char_array = new String[charNum];

        if (flag) {
            int bracketL = random.nextInt(charNum - 2);//(位置,最后一位和倒数第二位不能是（

            int j = charNum - bracketL - 2;

            int bracketR = bracketL + random.nextInt(j) + 2;//)位置这个随机数可以生成0

            if (bracketL != 0) {
                char_array[bracketL] = "+(";
            } else {
                char_array[bracketL] = "(";

            }

            if (bracketR == charNum - 1) {
                char_array[bracketR] = ")";
            } else {
                char_array[bracketR] = ")-";
            }
        }
        int mark1 = 0;
        while (true) {
            if (mark1 == mark) {
                break;
            }
            int i = (int) (Math.random() * charNum);
            if (char_array[i] == null) {
                char_array[i] = "/";
                mark1++;
            }

        }
        int num = 0;
        while (true) {

            if (num == charNum) break;
            if (char_array[num] == null) {
                int i = (int) (Math.random()*4);
                switch (i) {
                    case 0:
                        char_array[num] = "*";
                        break;
                    case 1:
                        char_array[num] = "+";
                        break;
                    case 2:
                        char_array[num] = "-";
                        break;

                    case 3:
                        char_array[num] = "÷";
                        break;


                }
            }

            num++;

        }
        return char_array;
    }



}
