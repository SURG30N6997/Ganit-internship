import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class VESIT_3040301_103_Assign4_Sairam {
    public static void main(String args[]) throws IOException, FileNotFoundException {
        String filename = "C:/Users/saira/Documents/Ganit Internship/Assignments/VESIT_3040301_103_Assign4_Sairam.xlsx";     //Location where excel file is getting generated
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Instruction");      //Generating first sheet as Instruction

        XSSFSheet sheet1 = workbook.createSheet("Questions");       //Generating second sheet as Questions

        //Adding first row in second sheet(Questions)
        String[] header = {"Sr. No", "Question Type", "Answer Type", "Topic Number", "Question (Text Only)", "Correct Answer 1", "Correct Answer 2",
                "Correct Answer 3", "Correct Answer 4", "Wrong Answer 1", "Wrong Answer 2", "Wrong Answer 3", "Time in seconds", "Difficulty Level",
                "Question (Image/ Audio/ Video)", "Contributor's Registered mailId", "Solution (Text Only)", "Solution (Image/ Audio/ Video)", "Variation Number"};
        XSSFRow rowhead = sheet1.createRow((short) 0);

        //Set height and width to the column and row
        sheet1.setColumnWidth(4, 35 * 250);
        sheet1.setColumnWidth(16, 45 * 250);

        //Adding header to the second sheet
        for (int head = 0; head < header.length; head++) {
            rowhead.createCell(head).setCellValue(header[head]);

        }

        //Taking input for number of question you want to generate
        System.out.println("How many question you want to enter:-");
        Scanner sc = new Scanner(System.in);
        int mapsize, mapsizeafter;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int q = sc.nextInt();
        for (int k = 1; k < q + 1; k++) {
            // Create row
            XSSFRow row = sheet1.createRow(k);
            row.createCell(0).setCellValue(k);
            row.createCell(1).setCellValue("Text");
            row.createCell(2).setCellValue(1);
            row.createCell(3).setCellValue("3040301");

            // Generate random number to perform the operation
            int pmin = 1; //minimum power
            int pmax = 8; //maximum power
            int cmin = 1; //minimum coefficient
            int cmax = 8; //maximum coefficient

            int power = (int) (Math.random() * (pmax - pmin + 1) + pmin);
            int coeff1 = (int) (Math.random() * (cmax - cmin + 1) + cmin);
            int coeff2 = (int) (Math.random() * (cmax - cmin + 1) + cmin);
            int coeffAnswer = coeff1 + coeff2;
            int[] coeffW = new int[5];
            coeffW[0] = coeff1 + coeff2 + 1;
            coeffW[1] = coeff1 + coeff2 - 1;
            coeffW[2] = coeff1 + coeff2 - 2;
            coeffW[3] = coeff1 + coeff2 + 2;
            coeffW[4] = coeff1 + coeff2 + 3;

            String[] variable = new String[26];

            // Fill the array with lowercase English alphabets
            for (int i = 0; i < 26; i++) {
                variable[i] = String.valueOf((char) ('a' + i));
            }
            int alphaIndex = (int)(Math.random()*(25+1));
            String[] wrongAnswer = new String[6];
            for (int j = 0; j < 5; j++) {
                wrongAnswer[j] = constructPolynomialTerm(coeffW[j], power, variable[alphaIndex]);
            }
            wrongAnswer[5] = "None of the above<br># वरील पैकी कोणतेच नाही";

            int[] wrongIndex = new int[3];
            for(int j = 0; j < 3; j++){
                wrongIndex[j] = (int)(Math.random()*(5 + 1));
                if (j > 1) {
                    for (int check = 0; check < j; check++) {
                        while (wrongIndex[check] == wrongIndex[j]) {
                            wrongIndex[j] = (int) (Math.random() * (5 + 1));
                        }
                    }
                }
            }


            String term1 = constructPolynomialTerm(coeff1, power, variable[alphaIndex]);
            String term2 = constructPolynomialTerm(coeff2, power, variable[alphaIndex]);
            String correctAnswer = constructPolynomialTerm(coeffAnswer, power, variable[alphaIndex]);
            String answerNeed = new String();
            if(power == 1){
                answerNeed = "$"+variable[alphaIndex]+"$";
            } else {
                answerNeed = "$"+variable[alphaIndex]+"^"+(power)+"$";
            }

            //Generate question english
            String Que = "Addition of "+term1+" and "+term2+" is. . . . <br>#";

            //Generate question marathi
            String Que1 = term1+" आणि "+term2+" यांची बेरीज . . . .<br>";
            String Question = "" + Que + " " + Que1 + "";

            row.createCell(4).setCellValue(Question);
            row.createCell(5).setCellValue(correctAnswer + "<br>");
            row.createCell(6).setCellValue(" ");
            row.createCell(7).setCellValue(" ");
            row.createCell(8).setCellValue(" ");
            row.createCell(9).setCellValue(wrongAnswer[wrongIndex[0]] + "<br>");
            row.createCell(10).setCellValue(wrongAnswer[wrongIndex[1]] + "<br>");
            row.createCell(11).setCellValue(wrongAnswer[wrongIndex[2]] + "<br>");
            row.createCell(12).setCellValue(60);
            row.createCell(13).setCellValue(1);
            row.createCell(14).setCellValue(" ");
            row.createCell(15).setCellValue("2022.sairam.konar@ves.ac.in");

            //Generate Solution
            String Solu = "Ans :"+correctAnswer+" <br>In case of addition or subtraction of algebraic expressions we need to add or subtract coefficients of like terms, to get the result and is written with the variable to get actual addition or subtraction. Here coefficient of "+term1+" is $"+coeff1+"$ and "+term2+" is $"+coeff2+"$, therefore $"+coeff1+"+"+coeff2+ "= "+(coeff1 + coeff2)+"$, and addition is written as "+correctAnswer+"<br>It is same as "+term1+" $+$ "+term2+ " $=$ $("+coeff1 + "+" + coeff2+")$"+answerNeed+" $=$ "+correctAnswer+"<br>#";
            String Sol1 = "उत्तर : "+correctAnswer+" <br>बैजिक राशींची बेरीज किंवा वजाबाकी करताना आपण सजातीय राशींच्या सहगुणकाची बेरीज अथवा वजाबाकी करून येणाऱ्या उत्तरासह चल लिहितो. "+term1+" चा सहगुणक $"+coeff1+"$ आहे आणि "+term2+" चा सहगुणक $"+coeff2+"$ आहे, म्हणून $"+coeff1+"+"+coeff2+ "= "+(coeff1 + coeff2)+"$, आणि ही बेरीज चलासह लिहिली असता "+correctAnswer+" असे मिळते <br>म्हणून "+term1+" $+$ "+term2+ " $=$ $("+coeff1 + "+" + coeff2+")$"+answerNeed+" $=$ "+correctAnswer+"<br>";
            String Solution = " " + Solu + " " + Sol1 + " ";
            row.createCell(16).setCellValue(Solution);
            row.createCell(17).setCellValue(" ");
            row.createCell(18).setCellValue(103);

            mapsize = map.size();
            map.put(Question, k);
            mapsizeafter = map.size();

            //In Java, a map can consist of virtually any number of key-value pairs, but the keys must always be unique — non-repeating.
            if (mapsize == mapsizeafter) {
                System.out.println("duplicate Question" + k + ". " + Question);
                k--;
            }

            if (correctAnswer.equals(wrongAnswer[wrongIndex[0]]) || correctAnswer.equals(wrongAnswer[wrongIndex[1]]) || correctAnswer.equals(wrongAnswer[wrongIndex[2]]) || wrongAnswer[wrongIndex[0]].equals(wrongAnswer[wrongIndex[1]]) || wrongAnswer[wrongIndex[0]].equals(wrongAnswer[wrongIndex[2]]) || wrongAnswer[wrongIndex[1]].equals(wrongAnswer[wrongIndex[2]])) {
                System.out.println("duplicate" + k);
                k--;
            }


        }


        int rowTotal = sheet1.getLastRowNum();
//			  System.out.println(rowTotal);
        XSSFRow row = sheet1.createRow( rowTotal + 1);
        row.createCell(0).setCellValue("****");

        //Writing data to the file
        FileOutputStream fileout = new FileOutputStream(filename);
        workbook.write(fileout);
        fileout.close();

        System.out.println("file created");

    }

    static String constructPolynomialTerm(int coefficients, int powers, String variable) {
        StringBuilder polynomialBuilder = new StringBuilder("$");
        String coefficientSign = (coefficients < 0) ? "-" : "";
        polynomialBuilder.append(coefficientSign);
        if (powers == 0) {
            polynomialBuilder.append(Math.abs(coefficients));
        } else if (Math.abs(coefficients) != 1) {
            polynomialBuilder.append(Math.abs(coefficients));
        }
        String power = new String();
        power = (powers != 0) ? (powers != 1) ? variable+"^" + powers : variable : "";
        polynomialBuilder.append(power);
        polynomialBuilder.append("$");
        return polynomialBuilder.toString();
    }
}










