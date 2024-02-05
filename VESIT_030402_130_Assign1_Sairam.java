import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class VESIT_030402_130_Assign1_Sairam{
    public static void main(String args[]) throws IOException,FileNotFoundException{
        String filename = "C:/Users/saira/Documents/Ganit Internship/Assignments/VESIT_030402_130_Assign1_Sairam.xlsx";     //Location where excel file is getting generated
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Instruction");      //Generating first sheet as Instruction

        XSSFSheet sheet1 = workbook.createSheet("Questions");       //Generating second sheet as Questions

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
            int pmin = 1; // min value of power
            int pmax = 7; // max value of power
            int cmin = 1; //min for coefficient
            int cmax = 8; //max for coefficient
            int min = 5; // min value for noOfTerms
            int max = 8; // max value for noOfTerms

            int noOfTerms = (int) (Math.random() * (max - min + 1) + min);
            int noOfPowers = noOfTerms - 1;
            int[] power = new int[noOfPowers];

            for (int j = 0; j < noOfPowers; j++) {
                power[j] = (int) (Math.random() * (pmax - pmin + 1) + pmin);
            }

            for (int i = 0; i < noOfPowers - 1; i++) {
                for (int j = i + 1; j < noOfPowers; j++) {
                    // Check for duplicates
                    while (power[i] == power[j]) {
                        // Generate a new random value
                        int newRandomValue = (int) (Math.random() * (pmax - pmin + 1) + pmin);

                        // Check against all values in the array
                        boolean isDuplicate = false;
                        for (int check = 0; check <= j; check++) {
                            if (newRandomValue == power[check]) {
                                isDuplicate = true;
                                break;
                            }
                        }

                        if (!isDuplicate) {
                            power[j] = newRandomValue;
                        }
                    }
                }
            }


            Arrays.sort(power);

            //coefficients
            int[] coefficients = new int[noOfTerms];
            for (int i = 0; i < noOfTerms; i++) {
                coefficients[i] = (int) (Math.random() * (cmax - cmin + 1) + cmin);
            }

            //Generate Correct answer
            String correctAnswer = "$"+coefficients[noOfTerms-1]+"x^"+power[noOfPowers-1]+"$<br>";

            //Generate wrong options
            int[] wrongIndex = new int[noOfPowers-1];

            for(int i=0; i < noOfPowers-1; i++){
                wrongIndex[i] = (int)(Math.random() * (noOfPowers-1));
            }

            for(int i=0; i < noOfPowers-2; i++){
                while (wrongIndex[i] == wrongIndex[i + 1]) {
                    int newRandomValue = (int) (Math.random() * (noOfPowers-1));

                    // Check against all values in the array
                    boolean isDuplicate = false;
                    for (int check = 0; check <= i; check++) {
                        if (newRandomValue == wrongIndex[check]) {
                            isDuplicate = true;
                            break;
                        }
                    }

                    if (!isDuplicate) {
                        wrongIndex[i] = newRandomValue;
                    }
                }
            }



            String wrongAnswer1 = "$"+coefficients[wrongIndex[0]+1]+"x^"+power[wrongIndex[0]]+"$<br>";
            String wrongAnswer2 = "$"+coefficients[wrongIndex[1]+1]+"x^"+power[wrongIndex[1]]+"$<br>";
            String wrongAnswer3 = "$"+coefficients[wrongIndex[2]+1]+"x^"+power[wrongIndex[2]]+"$<br>";

            //polynomial generation
            String polynomial = "";

            if(noOfTerms==5){
                polynomial = "$"+coefficients[4]+"x^"+power[3]+"+"+coefficients[3]+"x^"+power[2]+"+"+coefficients[2]+"x^"+power[1]+"+"+coefficients[1]+"x^"+power[0]+"+"+coefficients[0]+"$";
            } else if(noOfTerms==6){
                polynomial = "$"+coefficients[5]+"x^"+power[4]+"+"+coefficients[4]+"x^"+power[3]+"+"+coefficients[3]+"x^"+power[2]+"+"+coefficients[2]+"x^"+power[1]+"+"+coefficients[1]+"x^"+power[0]+"+"+coefficients[0]+"$";
            } else if(noOfTerms==7){
                polynomial = "$"+coefficients[6]+"x^"+power[5]+"+"+coefficients[5]+"x^"+power[4]+"+"+coefficients[4]+"x^"+power[3]+"+"+coefficients[3]+"x^"+power[2]+"+"+coefficients[2]+"x^"+power[1]+"+"+coefficients[1]+"x^"+power[0]+"+"+coefficients[0]+"$";
            } else if(noOfTerms==8){
                polynomial = "$"+coefficients[7]+"x^"+power[6]+"+"+coefficients[6]+"x^"+power[5]+"+"+coefficients[5]+"x^"+power[4]+"+"+coefficients[4]+"x^"+power[3]+"+"+coefficients[3]+"x^"+power[2]+"+"+coefficients[2]+"x^"+power[1]+"+"+coefficients[1]+"x^"+power[0]+"+"+coefficients[0]+"$";
            }

            //Generate question english
            String  Que = "Identify the term with highest degree in given polynomial "+ polynomial +" <br>#";
            //Generate question marathi
            String Que1 = polynomial +" या बहुपदीमधील जास्तीत जास्त कोटी असणारे पद ओळखा<br>";
            String Question = ""+Que+" "+Que1+"";

            row.createCell(4).setCellValue(Question);
            row.createCell(5).setCellValue(correctAnswer);
            row.createCell(6).setCellValue(" ");
            row.createCell(7).setCellValue(" ");
            row.createCell(8).setCellValue(" ");
            row.createCell(9).setCellValue(wrongAnswer1);
            row.createCell(10).setCellValue(wrongAnswer2);
            row.createCell(11).setCellValue(wrongAnswer3);
            row.createCell(12).setCellValue(60);
            row.createCell(13).setCellValue(2);
            row.createCell(14).setCellValue(" ");
            row.createCell(15).setCellValue("2022.sairam.konar@ves.ac.in");

            //Generate Solution
            String Solu = "Ans :$"+coefficients[noOfTerms-1]+"x^"+power[noOfPowers-1]+"$<br>\n" +
                    "By definition degree of a term in one variable, is the power of that variable. With more than one variable, the degree is the sum of the exponents of each variable. Given expression is in one variable only. Hence the term with highest degree is $"+coefficients[noOfTerms-1]+"x^"+power[noOfPowers-1]+"$ is the answer. <br>\n" +
                    "#<br>";
            String Sol1 = "$"+coefficients[noOfTerms-1]+"x^"+power[noOfPowers-1]+"$<br> पद जर एका चलातील असेल तर त्या चलाचा घातांक हाच त्या पदाची कोटी असते. परंतु, पद जर एकापेक्षा अधिक चलातील असेल तर, सर्व चलांच्या घातांकांची बेरीज ही त्या पदाची कोटी असते. <br> ही राशी एकाच चलातील आहे. त्यामुळे जास्तीत जास्त कोटी असणारे पद $"+coefficients[noOfTerms-1]+"x^"+power[noOfPowers-1]+"$ आहे हे उत्तर.<br> ";
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

}



