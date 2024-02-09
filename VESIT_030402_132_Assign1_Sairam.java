import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class VESIT_030402_132_Assign2_Sairam{
    public static void main(String args[]) throws IOException, FileNotFoundException {
        String filename = "C:/Users/saira/Documents/Ganit Internship/Assignments/VESIT_030402_132_Assign2_Sairam.xlsx";     //Location where excel file is getting generated
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
            row.createCell(3).setCellValue("030402");

            // Generate random number to perform the operation
            int pmin = 1; //min value for power
            int pmax = 4; //max value for power
            int cmin = 1; //min value for coefficients
            int cmax = 20; //max value for coefficients
            int[] powersQuestion = new int[3];
            int coefficientQuestion;
            int coefficientC_Answer;
            int coefficientW_Answer1;
            int coefficientW_Answer2;
            int coefficientW_Answer3;

            for (int i = 0; i < 3; i++) {
                powersQuestion[i] = (int) (Math.random() * (pmax - pmin + 1) + pmin);
            }
            for (int i = 0; i < 2; i++) {
                while (powersQuestion[i] == powersQuestion[i + 1]) {
                    int newRandomValue = (int) (int) (Math.random() * (pmax - pmin + 1) + pmin);

                    // Check against all values in the array
                    boolean isDuplicate = false;
                    for (int check = 0; check <= i; check++) {
                        if (newRandomValue == powersQuestion[check]) {
                            isDuplicate = true;
                            break;
                        }
                    }

                    if (!isDuplicate) {
                        powersQuestion[i] = newRandomValue;
                    }
                }
            }
            Arrays.sort(powersQuestion);
            int sum = powersQuestion[0] + powersQuestion[1] + powersQuestion[2];
            while (sum > 8){
                powersQuestion[2] -= 1;
                sum -= 1;
            }
            Arrays.sort(powersQuestion);


            String[] alphabet = new String[3];
            alphabet[0] = "a";
            alphabet[1] = "b";
            alphabet[2] = "c";
            //Generate Correct answer
            int[] correctPower = new int[3];
            correctPower[0] = (powersQuestion[0] + powersQuestion[1]);
            correctPower[1] = (powersQuestion[1] - 1);
            correctPower[2] = (powersQuestion[2] - powersQuestion[1] + 1);

            coefficientQuestion = (int)(Math.random()*(cmax-cmin+1)+cmin);
            coefficientC_Answer = (int)(Math.random()*(cmax-cmin+1)+cmin);
            coefficientW_Answer1 = (int)(Math.random()*(cmax-cmin+1)+cmin);
            coefficientW_Answer2 = (int)(Math.random()*(cmax-cmin+1)+cmin);
            coefficientW_Answer3 = (int)(Math.random()*(cmax-cmin+1)+cmin);


            String correctAnswer = constructPolynomial(coefficientC_Answer, correctPower, alphabet);

            int[] wrongPower1 = new int[3];
            wrongPower1[0] =  (powersQuestion[0] + powersQuestion[1]);
            wrongPower1[1] =  (powersQuestion[1]);
            wrongPower1[2] =  (powersQuestion[2] - powersQuestion[1] + 1);

            int[] wrongPower2 = new int[3];
            wrongPower2[0] =  (powersQuestion[0] + powersQuestion[1] - 1);
            wrongPower2[1] =  (powersQuestion[1] + 1);
            wrongPower2[2] =  (powersQuestion[2] - powersQuestion[1] + 2);

            int[] wrongPower3 = new int[3];
            wrongPower3[0] =  (powersQuestion[0] + powersQuestion[1] - 1);
            wrongPower3[1] =  (powersQuestion[1] - 1);
            wrongPower3[2] =  (powersQuestion[2] - powersQuestion[1] + 1);

            String wrongAnswer1 = constructPolynomial(coefficientW_Answer1, wrongPower1, alphabet  );
            String wrongAnswer2 = constructPolynomial(coefficientW_Answer2, wrongPower2, alphabet  );
            String wrongAnswer3 = constructPolynomial(coefficientW_Answer3, wrongPower3, alphabet );

            //question term generation
            String[] alphabet1 = new String[3];
            alphabet1[0] = "x";
            alphabet1[1] = "y";
            alphabet1[2] = "z";
            String polyQ = constructPolynomial(coefficientQuestion, powersQuestion, alphabet1 );
//


            String questionE = "Which of the following terms has degree same as degree of the term " + polyQ + "<br>#";
            String questionM = "खाली दिलेल्या कोणत्या पदाची कोटी, " + polyQ + " या पदाच्या कोटी इतकी आहे.<br>";
            String Question = "" + questionE + " " + questionM + "";

            row.createCell(4).setCellValue(Question);
            row.createCell(5).setCellValue(correctAnswer+"<br>");
            row.createCell(6).setCellValue(" ");
            row.createCell(7).setCellValue(" ");
            row.createCell(8).setCellValue(" ");
            row.createCell(9).setCellValue(wrongAnswer1+"<br>");
            row.createCell(10).setCellValue(wrongAnswer2+"<br>");
            row.createCell(11).setCellValue(wrongAnswer3+"<br>");
            row.createCell(12).setCellValue(60);
            row.createCell(13).setCellValue(2);
            row.createCell(14).setCellValue(" ");
            row.createCell(15).setCellValue("2022.sairam.konar@ves.ac.in");

            //Generate Solution
            String solutionE = "Ans : " + correctAnswer + "<br>Degree of the given term " + polyQ + "<br>$=$ power of $x$ + power of $y$ + power of $z= " + powersQuestion[0] + "+" + powersQuestion[1] + "+" + powersQuestion[2] + " =" + (powersQuestion[0] + powersQuestion[1] + powersQuestion[2]) + "$ .<br> From the given options, the term " + correctAnswer + " has the power<br> $=$ power of $a$ + power of $b$ + power of $c = " + (powersQuestion[0] + powersQuestion[1]) + "+" + (powersQuestion[1] - 1) + "+" + (powersQuestion[2] - powersQuestion[1] + 1) + " = " + (powersQuestion[0] + powersQuestion[1] + powersQuestion[1] - 1 + powersQuestion[2] - powersQuestion[1] + 1) + "$<br>All other terms have degree different than $" + (powersQuestion[0] + powersQuestion[1] + powersQuestion[1] - 1 + powersQuestion[2] - powersQuestion[1] + 1) + "$.<br> $\\therefore$ " + correctAnswer + " is the right option is the answer.<br>#";
            String solutionM = "उत्तर : " + correctAnswer + "<br>दिलेल्या पदाची कोटी " + polyQ + "<br>$ =x$ चा घात + $y$ चा घात + $z$ चा घात $=" + powersQuestion[0] + "+" + powersQuestion[1] + "+" + powersQuestion[2] + " =" + (powersQuestion[0] + powersQuestion[1] + powersQuestion[2]) + "$ आहे.<br> दिलेल्या पर्यायां पैकी " + correctAnswer + " या पदातील $a$, $b$ आणि $c$ या चलांचे घातांक अनुक्रमे $" + (powersQuestion[0] + powersQuestion[1]) + "$, $" + (powersQuestion[1] - 1) + "$ आणि $" + (powersQuestion[2] - powersQuestion[1] + 1) + "$ आहेत.<br>म्हणून त्या पदाची कोटी $= " + (powersQuestion[0] + powersQuestion[1]) + "+" + (powersQuestion[1] - 1) + "+" + (powersQuestion[2] - powersQuestion[1] + 1) + " = " + (powersQuestion[0] + powersQuestion[1] + powersQuestion[1] - 1 + powersQuestion[2] - powersQuestion[1] + 1) + "$.<br>तसेच इतर सर्व पदांची कोटी $" + (powersQuestion[0] + powersQuestion[1] + powersQuestion[1] - 1 + powersQuestion[2] - powersQuestion[1] + 1) + "$ पेक्षा वेगळी आहे. <br> $\\therefore$ " + correctAnswer + " हाच योग्य पर्याय आहे हे उत्तर . <br> ";
            String Solution = " " + solutionE + " " + solutionM + " ";

            row.createCell(16).setCellValue(Solution);
            row.createCell(17).setCellValue(" ");
            row.createCell(18).setCellValue(132);

            mapsize = map.size();
            map.put(Question, k);
            mapsizeafter = map.size();

            //In Java, a map can consist of virtually any number of key-value pairs, but the keys must always be unique — non-repeating.
            if (mapsize == mapsizeafter) {
                System.out.println("duplicate Question" + k + ". " + Question);
                k--;
            }

            if (correctAnswer.equals(wrongAnswer1) || correctAnswer.equals(wrongAnswer2) || correctAnswer.equals(wrongAnswer3) || wrongAnswer1.equals(wrongAnswer2) || wrongAnswer1.equals(wrongAnswer3) || wrongAnswer2.equals(wrongAnswer3)) {
                System.out.println("duplicate" + k);
                k--;
            }


        }


        int rowTotal = sheet1.getLastRowNum();
//			  System.out.println(rowTotal);
        XSSFRow row = sheet1.createRow((short) rowTotal + 1);
        row.createCell(0).setCellValue("****");

        //Writing data to the file
        FileOutputStream fileout = new FileOutputStream(filename);
        workbook.write(fileout);
        fileout.close();

        System.out.println("file created");

    }
    private static String constructPolynomial(int coefficient, int[] power, String[] alphabet  ){
        StringBuilder polynomialBuilder = new StringBuilder("$");
        for(int i = 0; i<3 ; i++){
            if(i == 0) {
                if (coefficient != 1) {
                    polynomialBuilder.append("{" + coefficient + "}");
                }
            }
            if(power[i] == 0){
                continue;
            }
            polynomialBuilder.append(alphabet[i]);
            if(power[i] == 1){
                polynomialBuilder.append("");
            } else{
                polynomialBuilder.append("^{"+power[i]+"}");
            }
        }
        polynomialBuilder.append("$");
        return polynomialBuilder.toString();
    }
}
