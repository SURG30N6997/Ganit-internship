import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class VESIT_030402_136_Assign3_Sairam{
    public static void main(String args[]) throws IOException,FileNotFoundException{
        String filename = "C:/Users/saira/Documents/Ganit Internship/Assignments/VESIT_030402_136_Assign3_Sairam.xlsx";     //Location where excel file is getting generated
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
            int pmin = 0;
            int pmax = 8;
            int cmin = -6;
            int cmax = 6;
            int min = 4; //min no of terms
            int max = 5; //max no of terms

            int noOfTerms = (int)(Math.random()*(max - min + 1) + min);
            int[] coefficients = new int[noOfTerms];
            int[] powers = new int[noOfTerms];

            for(int i=0; i<noOfTerms; i++){
                coefficients[i] = (int)(Math.random()*(cmax - cmin + 1) + cmin);
                powers[i] = (int)(Math.random()*(pmax - pmin + 1) + pmin);
                while(coefficients[i] == 0){
                    coefficients[i] = (int)(Math.random()*(cmax - cmin + 1) + cmin);
                }
            }
            while(coefficients[0]<1){
                coefficients[0] = (int)(Math.random()*(cmax - cmin + 1) + cmin);
            }
            for (int i = 0; i < noOfTerms ; i++) {
                int newPower;
                boolean isDuplicate;

                do {
                    newPower = (int)(Math.random() * (pmax - pmin + 1) + pmin);
                    isDuplicate = false;

                    // Check for duplicates in already generated powers
                    for (int check = 0; check < i; check++) {
                        if (newPower == powers[check]) {
                            isDuplicate = true;
                            break;
                        }
                    }
                } while (isDuplicate);

                powers[i] = newPower;
            }
            int randIndexQ = (int)(Math.random()*(noOfTerms));

            int powerQ = powers[randIndexQ];
            String pPowerQE = new String();
            String pPowerQM = new String();
            if(powerQ == 0){
                pPowerQE = "Constant";
                pPowerQM = "स्थिरांक";
            }
            else if(powerQ == 1){
                pPowerQE = "$x$";
                pPowerQM = "$x$";
            } else {
                pPowerQE = "$x^"+(powerQ)+"$";
                pPowerQM = "$x^"+(powerQ)+"$";
            }
            ArrayList<Integer> wrongIndices = new ArrayList<>();
            for (int i = 0; i < noOfTerms - 1; i++) {
                if (i != randIndexQ) {
                    wrongIndices.add(i);
                }
            }
            Collections.shuffle(wrongIndices);

            int[] randIndex = new int[noOfTerms - 1];
            for (int i = 0; i < noOfTerms - 2; i++) {
                randIndex[i] = wrongIndices.get(i);
            }
            String correctAnswer = constructPolynomialTerm(coefficients, powers, randIndexQ);

            String[] wrongAnswer = new String[noOfTerms - 1];
            for(int i=0; i<noOfTerms-1; i++){
                wrongAnswer[i] = constructPolynomialTerm(coefficients, powers, randIndex[i]);
            }

            String polyQ = constrctPolynomial(coefficients, powers, noOfTerms);




            //Generate question english
            String  Que = "Identify the term in polynomial "+polyQ+" which has power of $x$ as $"+powerQ+"$<br>#";

            //Generate question marathi
            String Que1 = polyQ+" या बहुपदीमधील $x$ या चलाचा घातांक $"+powerQ+"$ असणारे पद ओळखा.  <br>";
            String Question = ""+Que+" "+Que1+"";

            row.createCell(4).setCellValue(Question);
            row.createCell(5).setCellValue(correctAnswer+"<br>");
            row.createCell(6).setCellValue(" ");
            row.createCell(7).setCellValue(" ");
            row.createCell(8).setCellValue(" ");
            row.createCell(9).setCellValue(wrongAnswer[randIndex[0]]+"<br>");
            row.createCell(10).setCellValue(wrongAnswer[randIndex[1]]+"<br>");
            row.createCell(11).setCellValue(wrongAnswer[randIndex[2]]+"<br>");
            row.createCell(12).setCellValue(60);
            row.createCell(13).setCellValue(2);
            row.createCell(14).setCellValue(" ");
            row.createCell(15).setCellValue("2022.sairam.konar@ves.ac.in");

            //Generate Solution
            String Solu = "Ans : "+correctAnswer+"<br> Term corresponding to power $"+powerQ+"$ is the term with "+pPowerQE+" and the term in given polynomial is "+correctAnswer+".<br>$\\therefore$ "+correctAnswer+" is the answer.<br>#";
            String Sol1 = "उत्तर : "+correctAnswer+"<br> एखाद्या पदामध्ये $x$ या चलाचा घातांक $"+powerQ+"$ असणे  म्हणजे त्या पदामध्ये "+pPowerQM+" असले पाहिजे. दिलेल्या बहुपदीमध्ये "+correctAnswer+" असलेले पद "+pPowerQM+" आहे. या पदामध्ये $x$ चा घातांक $"+powerQ+"$ आहे.<br> $\\therefore$ "+correctAnswer+" ही उत्तर आहे";
            String Solution = " "+Solu+" "+Sol1+" ";
            row.createCell(16).setCellValue(Solution);
            row.createCell(17).setCellValue(" ");
            row.createCell(18).setCellValue(136);

            mapsize = map.size();
            map.put(Question, k);
            mapsizeafter = map.size();

            //In Java, a map can consist of virtually any number of key-value pairs, but the keys must always be unique — non-repeating.
            if(mapsize == mapsizeafter) {
                System.out.println("duplicate Question"+k+". " + Question);
                k--;
            }

            if(correctAnswer.equals(wrongAnswer[randIndex[0]]) || correctAnswer.equals(wrongAnswer[randIndex[1]]) || correctAnswer.equals(wrongAnswer[randIndex[2]]) || wrongAnswer[randIndex[0]].equals(wrongAnswer[randIndex[1]]) ||wrongAnswer[randIndex[0]].equals(wrongAnswer[randIndex[2]])||wrongAnswer[randIndex[1]].equals(wrongAnswer[randIndex[2]])) {
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
    static String constrctPolynomial(int[] coefficients, int[] powers, int noOfTerms){
        StringBuilder polynomialBuilder = new StringBuilder("$");
        for(int i = 0; i<noOfTerms; i++){
            String coefficientSign = (coefficients[i] < 0) ? "-" : (i!=0) ? "+" : "";
            polynomialBuilder.append(coefficientSign);
            if(powers[i]==0){
                polynomialBuilder.append(Math.abs(coefficients[i]));
            }
            else if(Math.abs(coefficients[i]) != 1){
                polynomialBuilder.append(Math.abs(coefficients[i]));
            }
            String power = new String();
            power = (powers[i]!=0) ? (powers[i]!=1) ? "x^"+powers[i] : "x" : "";
            polynomialBuilder.append(power);
        }
        polynomialBuilder.append("$");
        return polynomialBuilder.toString();
    }

    static String constructPolynomialTerm(int[] coefficients, int[] powers, int index){
        StringBuilder polynomialBuilder = new StringBuilder("$");
        String coefficientSign = (coefficients[index] < 0) ? "-" : "";
        polynomialBuilder.append(coefficientSign);
        if(powers[index]==0){
            polynomialBuilder.append(Math.abs(coefficients[index]));
        } else if(Math.abs(coefficients[index]) != 1){
            polynomialBuilder.append(Math.abs(coefficients[index]));
        }
        String power = new String();
        power = (powers[index]!=0) ? (powers[index]!=1) ? "x^"+powers[index] : "x" : "";
        polynomialBuilder.append(power);
        polynomialBuilder.append("$");
        return polynomialBuilder.toString();
    }
}










