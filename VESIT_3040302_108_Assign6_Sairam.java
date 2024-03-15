import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class VESIT_3040302_108_Assign6_Sairam {
    public static void main(String args[]) throws IOException, FileNotFoundException {
        String filename = "C:/Users/saira/Documents/Ganit-internship/Assignment Excel/VESIT_3040302_108_Assign6_Sairam.xlsx";     //Location where excel file is getting generated
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
            int[] coefficients = new int[4];
            for (int i = 0; i < 4; i++) {
                do{
                    coefficients[i] = (int)(Math.random()*(cmax - cmin + 1) + cmin);
                } while(coefficients[i] == 0);
            }
            int[][] powers = new int[4][2];
            for(int i=0; i<4; i++){
                for(int j=0; j<2; j++) {
                    powers[i][j] = (int) (Math.random() * (pmax - pmin + 1) + pmin);
                }

            }
            for (int i = 0; i < 4; i++) {
                if (powers[i][0] + powers[i][1] > 5) {
                    powers[i][1] -= 1;
                }
            }
            boolean allDistinct = false;
            while (!allDistinct) {
                allDistinct = true; // Assuming all arrays are distinct until proven otherwise
                for (int i = 0; i < 4; i++) {
                    for (int j = i + 1; j < 4; j++) {
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

            int[][][] wrongPowers = new int[4][4][2];
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    for(int l = 0; l < 2; l++){
                        wrongPowers[i][j][l] = powers[j][l];
                    }
                }
            }
            wrongPowers[0][1][1] = powers[1][1] + 1;
            wrongPowers[1][2][0] = powers[2][1] + 2;
            wrongPowers[2][1][1] = powers[1][1] + 3;
            wrongPowers[3][0][1] = powers[0][1] + 1;

            String wrongTerms[][] = new String[4][4];
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    wrongTerms[i][j] = constructPolynomialTerm(coefficients[j], wrongPowers[i][j], alphaIndex, variable);
                }
            }

            String wrongBino[][] = new String[4][2];
            for(int i = 0; i < 4; i++){
                wrongBino[i][0] = constructBinomial(wrongTerms[i][0], wrongTerms[i][1]);
                wrongBino[i][1] = constructBinomial(wrongTerms[i][2], wrongTerms[i][3]);
            }

            String wrongAnswers[] = new String[5];
            for(int i = 0; i < 4; i++){
                wrongAnswers[i] = constructAnswer(wrongBino[i][0], wrongBino[i][1]);
            }
            wrongAnswers[4] = "None of the above<br>#दिलेल्या पैकी कोणतेही नाह";
            int[] wrongIndex = new int[3];
            for (int j = 0; j < 3; j++) {
                wrongIndex[j] = (int) (Math.random() * (5));
                if (j >= 1) {
                    for (int check = 0; check < j; check++) {
                        while (wrongIndex[check] == wrongIndex[j]) {
                            wrongIndex[j] = (int) (Math.random() * (5));
                        }
                    }
                }
            }

            String term1 = constructPolynomialTerm(coefficients[0], powers[0], alphaIndex, variable);
            String term2 = constructPolynomialTerm(coefficients[1], powers[1], alphaIndex, variable);
            String term3 = constructPolynomialTerm(coefficients[2], powers[2], alphaIndex, variable);
            String term4 = constructPolynomialTerm(coefficients[3], powers[3], alphaIndex, variable);

            String bino1 = constructBinomial(term1, term2);
            String bino2 = constructBinomial(term3, term4);

            String correctAnswer = constructAnswer(bino1, bino2);

            String Question = "Add horizontally /आडवी बेरीज करा : $"+bino1+"$ and $"+bino2+"$<br>\n";

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
            String Solu = "Ans : "+correctAnswer+"<br> Given : $("+bino1+") + ("+bino2+")$<br> In case of horizontal addition or subtraction of algebraic expressions we need to add or subtract coefficients of like terms, to get the result and is written with the variable to get actual addition or subtraction.<br> $\\therefore ("+bino1+") + ("+bino2+")$<br> $=$"+correctAnswer+". . . . Opening the brackets<br>$=$"+correctAnswer+" . . . . . as there are no like terms, is answer.<br>";
            String Sol1 = "# उत्तर : "+correctAnswer+"<br> दिल्या नुसार $("+bino1+") + ("+bino2+")$<br>बैजिक राशींची बेरीज किंवा वजाबाकी करताना आपण सजातीय राशींच्या सहगुणकांची बेरीज अथवा वजाबाकी करून येणाऱ्या उत्तरासह चल लिहितो .<br>$\\therefore ("+bino1+") + ("+bino2+")$<br>$=$"+correctAnswer+". . . . कंस सोडवून<br>$=$"+correctAnswer+" . . . . .  . . . . . सजातीय पदे नाहीत म्हणून हे उत्तर.<br>";
            String Solution = " " + Solu + " " + Sol1 + " ";
            row.createCell(16).setCellValue(Solution);
            row.createCell(17).setCellValue(" ");
            row.createCell(18).setCellValue(108);

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

    static String constructAnswer(String bino1, String bino2){
        StringBuilder correctAnswer = new StringBuilder("$");
        correctAnswer.append(bino1);
        char character = bino2.charAt(0);
        if(character != '-'){
            correctAnswer.append("+");
        }
        correctAnswer.append(bino2);
        correctAnswer.append("$");
        return correctAnswer.toString();
    }
}
