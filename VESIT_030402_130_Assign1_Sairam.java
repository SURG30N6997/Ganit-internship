import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class VESIT_030402_130_Assign1_Sairam{
    public static void main(String args[]) throws IOException,FileNotFoundException{
        String filename = "C:/Users/saira/Documents/Ganit Internship/Assignments/VESIT_030402_130_Assign1_Sairam_Correction2.xlsx";     //Location where excel file is getting generated
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Instruction");      //Generating first sheet as Instruction

        XSSFSheet sheet1 =   workbook.createSheet("Questions");       //Generating second sheet as Questions

        //Adding first row in second sheet(Questions)
        String[] header = {"Sr. No","Question Type","Answer Type","Topic Number","Question (Text Only)","Correct Answer 1","Correct Answer 2",
                "Correct Answer 3","Correct Answer 4","Wrong Answer 1","Wrong Answer 2","Wrong Answer 3","Time in seconds","Difficulty Level",
                "Question (Image/ Audio/ Video)","Contributor's Registered mailId","Solution (Text Only)","Solution (Image/ Audio/ Video)","Variation Number"};
        XSSFRow rowhead = sheet1.createRow((short)0);

        //Set height and width to the column and row
        sheet1.setColumnWidth(4, 35*250);
        sheet1.setColumnWidth(16, 45*250);

        //Adding header to the second sheet
        for(int head=0; head<header.length; head++) {
            rowhead.createCell(head).setCellValue(header[head]);

        }

        //Taking input for number of question you want to generate
        System.out.println("How many question you want to enter:-");
        Scanner sc=new Scanner(System.in);
        int mapsize,mapsizeafter;
        HashMap<String, Integer> map = new HashMap<String, Integer> ();
        int q = sc.nextInt();
        for(int k =1;k<q+1;k++) {
            // Create row
            XSSFRow row = sheet1.createRow(k);
            row.createCell(0).setCellValue(k);
            row.createCell(1).setCellValue("Text");
            row.createCell(2).setCellValue(1);
            row.createCell(3).setCellValue("030402");

            // Generate random number to perform the operation
            int pmin = 0;
            int pmax = 4;
            int cmin = -6;
            int cmax = 6;
            int[] powersX = new int[5];
            int[] powersY = new int[5];
            int[] powersQuestion = new int[5];
            int[] coefficients = new int[5];

            for (int i = 0; i <= 4; i++) {
                powersX[i] = (int) (Math.random() * (pmax - pmin + 1) + pmin);
                powersY[i] = (int) (Math.random() * (pmax - pmin + 1) + pmin);
                coefficients[i] = (int) (Math.random() * (cmax - cmin + 1) + cmin);
                while (coefficients[i] == 0) {
                    coefficients[i] = (int) (Math.random() * (cmax - cmin + 1) + cmin);
                }
            }
            while (coefficients[0] <= 0) {
                coefficients[0] = (int) (Math.random() * (cmax - cmin + 1) + cmin);
            }
            for (int i = 0; i <= 4; i++) {
                while (powersX[i] + powersY[i] > 7) {
                    if (powersX[i] > powersY[i]) {
                        powersX[i] -= 1;
                    } else {
                        powersY[i] -= 1;
                    }
                }
            }
            for (int i = 0; i <= 4; i++) {
                powersQuestion[i] = powersX[i] + powersY[i];
            }
            for (int i = 0; i < 4; i++) {
                for (int j = i + 1; j < 5; j++) {
                    while (powersQuestion[i] == powersQuestion[j]) {
                        int newPowerX = (int) (Math.random() * (pmax - pmin + 1) + pmin);
                        int newPowerY = (int) (Math.random() * (pmax - pmin + 1) + pmin);
                        while (newPowerX + newPowerY > 7) {
                            if (newPowerX > newPowerY) {
                                newPowerX -= 1;
                            } else {
                                newPowerY -= 1;
                            }
                        }
                        int newPowerQuestion = newPowerX + newPowerY;
                        boolean isDuplicate = false;
                        for (int check = 0; check <= j; check++) {
                            if (newPowerQuestion == powersQuestion[check]) {
                                isDuplicate = true;
                                break;
                            }

                        }
                        if (!isDuplicate) {
                            powersX[i] = newPowerX;
                            powersY[i] = newPowerY;
                            powersQuestion[i] = newPowerQuestion;
                            break;
                        }
                    }
                }
            }
            int max = 0;
            for (int i = 1; i < 5; i++) {
                if (powersQuestion[max] < powersQuestion[i]) {
                    max = i;
                }
            }
            String correctAnswer = constructPolynomialTerm(coefficients, powersX, powersY, max);
            String polyQ = constrctPolynomial(coefficients, powersX, powersY);

            ArrayList<Integer> wrongIndices = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                if (i != max) {
                    wrongIndices.add(i);
                }
            }
            Collections.shuffle(wrongIndices);

            int[] randIndex = new int[4];
            for (int i = 0; i < 4; i++) {
                randIndex[i] = wrongIndices.get(i);
            }
            // Ensure there are no duplicate indices and none equal to max


            String wrongAnswer1 = constructPolynomialTerm(coefficients, powersX, powersY, randIndex[0]);
            String wrongAnswer2 = constructPolynomialTerm(coefficients, powersX, powersY, randIndex[1]);
            String wrongAnswer3 = constructPolynomialTerm(coefficients, powersX, powersY, randIndex[2]);
            String wrongAnswer4 = constructPolynomialTerm(coefficients, powersX, powersY, randIndex[3]);
            String wrongAnswer5 = "None of the above<br># वरील पैकी कोणतेही नाही";

            String[] wrongAnswers = new String[5];
            wrongAnswers[0] = wrongAnswer1;
            wrongAnswers[1] = wrongAnswer2;
            wrongAnswers[2] = wrongAnswer3;
            wrongAnswers[3] = wrongAnswer4;
            wrongAnswers[4] = wrongAnswer5;



            //Generate question english
            String  Que = "Identify the term with highest degree in given polynomial "+ polyQ +" <br>#";
            //Generate question marathi
            String Que1 = polyQ +" या बहुपदीमधील मोठ्यात मोठी कोटी असणारे पद ओळखा<br>";
            String Question = ""+Que+" "+Que1+"";

            row.createCell(4).setCellValue(Question);
            row.createCell(5).setCellValue(correctAnswer+"<br>");
            row.createCell(6).setCellValue(" ");
            row.createCell(7).setCellValue(" ");
            row.createCell(8).setCellValue(" ");
            row.createCell(9).setCellValue(wrongAnswers[randIndex[0]]+"<br>");
            row.createCell(10).setCellValue(wrongAnswers[randIndex[1]]+"<br>");
            row.createCell(11).setCellValue(wrongAnswers[randIndex[2]]+"<br>");
            row.createCell(12).setCellValue(60);
            row.createCell(13).setCellValue(2);
            row.createCell(14).setCellValue(" ");
            row.createCell(15).setCellValue("2022.sairam.konar@ves.ac.in");

            //Generate Solution
            String Solu ="Answer: "+correctAnswer+"<br>Given polynomial is "+polyQ+"<br>By definition degree of a term in one variable, is the power of that variable. With more than one variable, the degree is the sum of the exponents of each variable.<br>Given polynomial has 2 variables in the terms.<br>Hence the term with highest sum of exponents in this polynomial is "+correctAnswer+"<br> as power of $x =" + powersX[max] + "$ and power of $y =" + powersY[max]+"$. Thus degree of the polynomial <br>$=$ power of $x$ + power of $y$ $="+powersX[max]+"+"+powersY[max]+" = "+(powersX[max] + powersY[max])+ "$<br>$\\therefore$ "+correctAnswer+" is the correct answer.<br>" ;
            String Sol1 = "#उत्तर :"+correctAnswer+"<br>दिलेली बहुपदी "+polyQ+" आहे. <br>पद जर एका चलातील असेल तर त्या चलाचा घातांक हाच त्या पदाची कोटी असते. परंतु, पद जर एकापेक्षा अधिक चलातील असेल तर, सर्व चलांच्या घातांकांची बेरीज ही त्या पदाची कोटी असते. <br>दिलेली बहुपदी दोन चलातील आहे. त्यामुळे त्यातील जास्तीत जास्त चलांच्या घातांकांची बेरीज "+correctAnswer+ " आहे कारण $x$ चा घात $= "+powersX[max]+"$ आणि $y$ चा घात $= "+powersY[max]+"$. म्हणून दिलेली बहुपदीचे पद<br> $= x$ चा घात + $y$ चा घात $= "+powersX[max]+"+"+powersY[max]+" = "+(powersX[max] + powersY[max])+ "$<br>$\\therefore$ "+correctAnswer+" ही उत्तर आहे";
            String Solution = " "+Solu+" "+Sol1+" ";
            row.createCell(16).setCellValue(Solution);
            row.createCell(17).setCellValue(" ");
            row.createCell(18).setCellValue(130);

            mapsize = map.size();
            map.put(Question, k);
            mapsizeafter = map.size();

            //In Java, a map can consist of virtually any number of key-value pairs, but the keys must always be unique — non-repeating.
            if(mapsize == mapsizeafter) {
                System.out.println("duplicate Question"+k+". " + Question);
                k--;
            }

            if(correctAnswer.equals(wrongAnswer1) || correctAnswer.equals(wrongAnswer2) || correctAnswer.equals(wrongAnswer3) || wrongAnswer1.equals(wrongAnswer2) ||wrongAnswer1.equals(wrongAnswer3)||wrongAnswer2.equals(wrongAnswer3)) {
                System.out.println("duplicate"+ k);
                k--;
            }


        }


        int rowTotal = sheet1.getLastRowNum();
//			  System.out.println(rowTotal);
        XSSFRow row = sheet1.createRow((short)rowTotal+1);
        row.createCell(0).setCellValue("****");

        //Writing data to the file
        FileOutputStream fileout = new FileOutputStream(filename);
        workbook.write(fileout);
        fileout.close();

        System.out.println("file created");

    }
    static String constrctPolynomial(int[] coefficients, int[] powersX, int[] powersY){
        StringBuilder polynomialBuilder = new StringBuilder("$");
        for(int i = 0; i<5; i++){
            String coefficientSign = (coefficients[i] < 0) ? "-" : (i!=0) ? "+" : "";
            polynomialBuilder.append(coefficientSign);
            if(powersX[i]==0 && powersY[i]==0){
                polynomialBuilder.append(Math.abs(coefficients[i]));
            }
            else if(Math.abs(coefficients[i]) != 1){
                polynomialBuilder.append(Math.abs(coefficients[i]));
            }
            String power = new String();
            if(powersX[i] == 1 && powersY[i] == 1){
                power = "xy";
            } else if(powersX[i] == 1 && powersY[i] > 1){
                power = "xy^"+powersY[i];
            } else if(powersY[i] == 1 && powersX[i] > 1){
                power = "x^"+powersX[i]+"y";
            } else {
                power = (powersX[i] !=0) ? (powersY[i] != 0) ? ("x^"+powersX[i]+"y^"+powersY[i]) : ("x^"+powersX[i]) : (powersY[i] != 0) ? ("y^"+powersY[i]) : "";
            }
            if ((powersX[i] + powersY[i]) == 1){
                power = power.replace("^1", "");
            }
            polynomialBuilder.append(power);
        }
        polynomialBuilder.append("$");
        return polynomialBuilder.toString();
    }
    static String constructPolynomialTerm(int[] coefficients, int[] powersX, int[] powersY, int index){
        StringBuilder polynomialBuilder = new StringBuilder("$");
        String coefficientSign = (coefficients[index] < 0) ? "-" : "";
        polynomialBuilder.append(coefficientSign);
        if(powersX[index]==0 && powersY[index]==0){
            polynomialBuilder.append(Math.abs(coefficients[index]));
        }
        if(Math.abs(coefficients[index]) != 1){
            polynomialBuilder.append(Math.abs(coefficients[index]));
        }
        String power = new String();
        if(powersX[index] == 1 && powersY[index] == 1){
            power = "xy";
        } else if(powersX[index] == 1 && powersY[index] > 1){
            power = "xy^"+powersY[index];
        } else if(powersY[index] == 1 && powersX[index] > 1){
            power = "x^"+powersX[index]+"y";
        } else {
            power = (powersX[index] !=0) ? (powersY[index] != 0) ? ("x^"+powersX[index]+"y^"+powersY[index]) : ("x^"+powersX[index]) : (powersY[index] != 0) ? ("y^"+powersY[index]) : "";
        }
        if ((powersX[index] + powersY[index]) == 1){
            power = power.replace("^1", "");
        }
        polynomialBuilder.append(power);
        polynomialBuilder.append("$");
        return polynomialBuilder.toString();
    }
}






