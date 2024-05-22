
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class VESIT_3040302_117_Assign7_Sairam {
    public static void main(String args[]) throws IOException, FileNotFoundException {
        String filename = "C:/Users/saira/Documents/Ganit-internship/Assignment Excel/VESIT_3040302_117_Assign7_Sairam.xlsx";     //Location where excel file is getting generated
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
            row.createCell(2).setCellValue(4);
            row.createCell(3).setCellValue("3040302");

            // Generate random number to perform the operation
            int cmin = -8;
            int cmax = 8;
            int pmin = 0;
            int pmax = 3;
            int[] coefficients = new int[6];
            for (int i = 0; i < 6; i++) {
                do{
                    coefficients[i] = (int)(Math.random()*(cmax - cmin + 1) + cmin);
                } while(coefficients[i] == 0);
            }
            int[] coeffCorrect = new int[6];
            for(int i = 0; i < 2; i++){
                coeffCorrect[i] = coefficients[i];
            }
            for(int i = 2; i < 6; i++){
                coeffCorrect[i] = (-1)*(coefficients[i]);
            }
            int[][] powers = new int[6][2];
            for(int i=0; i<6; i++){
                for(int j=0; j<2; j++) {
                    powers[i][j] = (int) (Math.random() * (pmax - pmin + 1) + pmin);
                }

            }
            for (int i = 0; i < 6; i++) {
                if (powers[i][0] + powers[i][1] > 5) {
                    powers[i][1] -= 1;
                }
            }
            boolean allDistinct = false;
            while (!allDistinct) {
                allDistinct = true; // Assuming all arrays are distinct until proven otherwise
                for (int i = 0; i < 6; i++) {
                    for (int j = i + 1; j < 6; j++) {
                        if (Arrays.equals(powers[i], powers[j])) {
                            // If two arrays are equal, generate new random values for the second array
                            for (int l = 0; l < 2; l++) {
                                powers[j][l] = (int) (Math.random() * (pmax - pmin + 1) + pmin);
                            }
                            allDistinct = false; // At least one pair of arrays is not distinct
                        }
                    }
                }
            }
            String variable[] = {"a", "b", "c", "d", "f", "g", "h", "m", "n", "p" ,"s", "t", "u", "v", "w", "x", "y", "z"};
            int alphaIndex[] = new int[2];
            alphaIndex[0] = (int)(Math.random()*(17));
            alphaIndex[1] = alphaIndex[0] + 1;

            int coeffWrong1[] = new int[6];
            for(int i = 0; i < 6; i++){
                coeffWrong1[i] = (-1) * coefficients[i];
            }

            int coeffWrong2[] = new int[6];
            for(int i = 0; i < 4; i++){
                coeffWrong2[i] = coefficients[i];
            }
            for(int i = 4; i < 6; i++){
                coeffWrong2[i] = (-1) * coefficients[i];
            }

            int coeffWrong3[] = new int[6];
            for(int i = 0; i < 6; i++){
                if(i==2 || i==3){
                    coeffWrong3[i] = (-1) * coefficients[i];
                } else {
                    coeffWrong3[i] = coefficients[i];
                }
            }
            String wrongTerms[][] = new String[4][6];
            for(int i = 0; i < 6; i++){
                wrongTerms[0][i] = constructPolynomialTerm(coeffWrong1[i], powers[i], alphaIndex, variable);
                wrongTerms[1][i] = constructPolynomialTerm(coeffWrong2[i], powers[i], alphaIndex, variable);
                wrongTerms[2][i] = constructPolynomialTerm(coeffWrong3[i], powers[i], alphaIndex, variable);
                wrongTerms[3][i] = constructPolynomialTerm(coefficients[i], powers[i], alphaIndex, variable);
            }


            String wrongBino[][] = new String[4][3];
            for(int i = 0; i < 4; i++){
                wrongBino[i][0] = constructBinomial(wrongTerms[i][0], wrongTerms[i][1]);
                wrongBino[i][1] = constructBinomial(wrongTerms[i][2], wrongTerms[i][3]);
                wrongBino[i][2] = constructBinomial(wrongTerms[i][4], wrongTerms[i][5]);
            }

            String wrongAnswers[] = new String[4];
            for(int i = 0; i < 4; i++){
                wrongAnswers[i] = constructAnswer(wrongBino[i][0], wrongBino[i][1], wrongBino[i][2]);
            }

            int[] wrongIndex = new int[3];
            for (int j = 0; j < 3; j++) {
                wrongIndex[j] = (int) (Math.random() * (4));
                if (j >= 1) {
                    for (int check = 0; check < j; check++) {
                        while (wrongIndex[check] == wrongIndex[j]) {
                            wrongIndex[j] = (int) (Math.random() * (4));
                        }
                    }
                }
            }

            //Generate Correct Answer
            String termC1 = constructPolynomialTerm(coeffCorrect[0], powers[0], alphaIndex, variable);
            String termC2 = constructPolynomialTerm(coeffCorrect[1], powers[1], alphaIndex, variable);
            String termC3 = constructPolynomialTerm(coeffCorrect[2], powers[2], alphaIndex, variable);
            String termC4 = constructPolynomialTerm(coeffCorrect[3], powers[3], alphaIndex, variable);
            String termC5 = constructPolynomialTerm(coeffCorrect[4], powers[4], alphaIndex, variable);
            String termC6 = constructPolynomialTerm(coeffCorrect[5], powers[5], alphaIndex, variable);

            String binoC1 = constructBinomial(termC1, termC2);
            String binoC2 = constructBinomial(termC3, termC4);
            String binoC3 = constructBinomial(termC5, termC6);

            String correctAnswer = constructAnswer(binoC1, binoC2, binoC3);

            //Generate Question
            String term1 = constructPolynomialTerm(coefficients[0], powers[0], alphaIndex, variable);
            String term2 = constructPolynomialTerm(coefficients[1], powers[1], alphaIndex, variable);
            String term3 = constructPolynomialTerm(coefficients[2], powers[2], alphaIndex, variable);
            String term4 = constructPolynomialTerm(coefficients[3], powers[3], alphaIndex, variable);
            String term5 = constructPolynomialTerm(coefficients[4], powers[4], alphaIndex, variable);
            String term6 = constructPolynomialTerm(coefficients[5], powers[5], alphaIndex, variable);

            String bino1 = constructBinomial(term1, term2);
            String bino2 = constructBinomial(term3, term4);
            String bino3 = constructBinomial(term5, term6);

            String Question = "Find by horizontal subtraction / आडवी मांडणी पद्धतीने वजाबाकी करा : $("+bino1+") - ("+bino2+") - ("+bino3+")$<br>\n";

            row.createCell(4).setCellValue(Question);
            row.createCell(5).setCellValue(correctAnswer + "<br>");
            row.createCell(6).setCellValue(" ");
            row.createCell(7).setCellValue(" ");
            row.createCell(8).setCellValue(" ");
            row.createCell(9).setCellValue(wrongAnswers[wrongIndex[0]] + "<br>");
            row.createCell(10).setCellValue(wrongAnswers[wrongIndex[1]] + "<br>");
            row.createCell(11).setCellValue(wrongAnswers[wrongIndex[2]] + "<br>");
            row.createCell(12).setCellValue(60);
            row.createCell(13).setCellValue(3);
            row.createCell(14).setCellValue(" ");
            row.createCell(15).setCellValue("2022.sairam.konar@ves.ac.in");

            //Generate Solution
            String Solu = "Ans : "+correctAnswer+"<br> \nGiven  $("+bino1+")-("+bino2+") -("+bino3+")$<br>\n In case of horizontal addition or subtraction of algebraic expressions we need to add or subtract (as the case may be) coefficients of like terms, to get the result and is written with the variable to get actual addition or subtraction.<br>\n$\\therefore$ $("+bino1+")-("+bino2+")-("+bino3+")$<br>\n$=$"+correctAnswer+". . . . By opening the brackets<br>\n$=$"+correctAnswer+"   . . . . . as there are no like terms, is answer.<br>";
            String Sol1 = "# उत्तर : "+correctAnswer+"<br> दिल्या नुसार  $("+bino1+")-("+bino2+") -("+bino3+")$<br> बैजिक राशींची बेरीज किंवा वजाबाकी करताना आपण सजातीय राशींच्या सहगुणकांची बेरीज अथवा वजाबाकी करून (आवश्यकते नुसार) येणाऱ्या उत्तरासह चल लिहितो .<br> $\\therefore$ $("+bino1+")-("+bino2+")-("+bino3+")$<br> $=$"+correctAnswer+". . . . कंस सोडवून<br> $=$"+correctAnswer+"  . . . . . सजातीय पदे नाहीत म्हणून हे उत्तर.<br>";
            String Solution = " " + Solu + " " + Sol1 + " ";
            row.createCell(16).setCellValue(Solution);
            row.createCell(17).setCellValue(" ");
            row.createCell(18).setCellValue(117);

            mapsize = map.size();
            map.put(Question, k);
            mapsizeafter = map.size();

            //In Java, a map can consist of virtually any number of key-value pairs, but the keys must always be unique — non-repeating.
            if (mapsize == mapsizeafter) {
                System.out.println("duplicate Question" + k + ". " + Question);
                k--;
            }

            if (correctAnswer.equals(wrongAnswers[wrongIndex[0]]) || correctAnswer.equals(wrongAnswers[wrongIndex[1]]) || correctAnswer.equals(wrongAnswers[wrongIndex[2]]) || wrongAnswers[wrongIndex[0]].equals(wrongAnswers[wrongIndex[1]]) || wrongAnswers[wrongIndex[0]].equals(wrongAnswers[wrongIndex[2]]) || wrongAnswers[wrongIndex[1]].equals(wrongAnswers[wrongIndex[2]])) {
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

    static String constructPolynomialTerm(int coefficient, int[] powers, int[] alphaIndex, String[] variables) {
        StringBuilder polynomialBuilder = new StringBuilder();
        String coefficientSign = (coefficient > 0) ? "" : "-";
        polynomialBuilder.append(coefficientSign);
        if (powers[0] == 0 && powers[1] == 0){
            polynomialBuilder.append(Math.abs(coefficient));
        } else if (Math.abs(coefficient) != 1){
            polynomialBuilder.append(Math.abs(coefficient));
        }
        String variable = (powers[0] == 0 && powers[1] == 0) ? "" : (powers[0] == 1 && powers[1] == 1) ? variables[alphaIndex[0]] + "" + variables[alphaIndex[1]] : (powers[0] == 0) ? (powers[1] == 1) ? variables[alphaIndex[1]] : variables[alphaIndex[1]] +"^"+ powers[1] : (powers[1] == 0) ? (powers[0] == 1) ? variables[alphaIndex[0]] : variables[alphaIndex[0]] +"^"+ powers[0] : (powers[0] == 1) ? variables[alphaIndex[0]] +""+ variables[alphaIndex[1]] + "^" +powers[1] : (powers[1] == 1) ? variables[alphaIndex[0]] +"^"+ powers[0] + "" + variables[alphaIndex[1]] : variables[alphaIndex[0]] +"^"+ powers[0] + "" + variables[alphaIndex[1]] +"^"+ powers[1];
        polynomialBuilder.append(variable);
//        polynomialBuilder.append("$");
        return polynomialBuilder.toString();
    }

    static String constructBinomial(String term1, String term2){
        StringBuilder binomialBuilder = new StringBuilder();
        binomialBuilder.append(term1);
        if(!term2.contains("-")){
            binomialBuilder.append("+");
        }
        binomialBuilder.append(term2);
        return binomialBuilder.toString();
    }

    static String constructAnswer(String bino1, String bino2, String bino3){
        StringBuilder correctAnswer = new StringBuilder("$");
        correctAnswer.append(bino1);
        char character = bino2.charAt(0);
        if(character != '-'){
            correctAnswer.append("+");
        }
        correctAnswer.append(bino2);
        char character1 = bino3.charAt(0);
        if(character1 != '-'){
            correctAnswer.append("+");
        }
        correctAnswer.append(bino3);
        correctAnswer.append("$");
        return correctAnswer.toString();
    }
}
