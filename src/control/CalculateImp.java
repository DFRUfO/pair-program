package control;

public class CalculateImp {

    public String calculate(char[] str) {
        int j = 0;

        //中缀表达式转后缀表达式
        double[] num = new double[100];
        char[] operator = new char[100];
        int index = 0;

        char[] stack = new char[100];
        double[] stackj = new double[100];
        int top = -1;

        int temp = 0; //正在拼写数字
        int flag = 0;//表示是否执行数字

        int flag2 = 0;//判断什么时候读完了

        while (true) {

            if (j == str.length) {
                flag2 = 1;
            }
            if (flag2 == 0 && str[j] >= '0' && str[j] <= '9') {//读到数字
                flag = 1;
                temp *= 10;
                temp += str[j] - '0';


            } else {//读到了符号，或者结束

                if (flag == 1) {//如果在拼写一个数字就将一个数字输出

                    num[index] = temp;

                    operator[index] = '?';

                    index++;
                    flag = temp = 0;
                }

                if (flag2 == 1) {//如果字符串已经结束


                    while (top >= 0) {
                        int q = top--;
                        if (stack[q] != '(') {
                            operator[index++] = stack[q];
                            
                        }

                    }
                    break;

                } else {//读到一个符号

                    if (top == -1 || str[j] == '(') {
                        stack[++top] = str[j];


                    } else if (str[j] == ')') {//如果是右括号则一直出栈道左括号

                        while (top >= 0 && stack[top] != '(') {
                            operator[index++] = stack[top--];

                        }


                    } else if (str[j] == '*' || str[j] == '/') {//如果是乘除，则乘除出栈，新符号入栈
                        while ((top > 0) && (stack[top] == '*' || stack[top] == '/')) {
                            operator[index++] = stack[top--];

                        }
                        stack[++top] = str[j];

                    } else {//如果是加减，则四则运算出栈,新符号入栈

                        while ((top >= 0) && (stack[top] !='(')) {//>=0是为了确保第0位可以出栈
                            operator[index++] = stack[top--];

                        }

                        stack[++top] = str[j];

                    }

                }

            }
            j++;
        }

        top = -1;
        double temp1 = 0;
        double temp2 = 0;

        for (int i = 0; i < index; i++) {
            if (operator[i] == '?') {
                stackj[++top] = num[i];
            } else {
                temp1 = stackj[top--];

                temp2 = stackj[top--];

                switch (operator[i]) {
                    case '+':
                        stackj[++top] = temp2 + temp1;
                        break;
                    case '-':
                        stackj[++top] = temp2 - temp1;
                        break;
                    case '*':
                        stackj[++top] = temp2 * temp1;
                        break;
                    case '÷':
                        stackj[++top] = temp2 / temp1;
                        break;
                    case '/':
                        stackj[++top] = temp2 / temp1;
                        break;
                }

            }
        }
        double result = stackj[0];
//        System.out.println("_____" + (result) + "______");
        return result + "";
    }
}
