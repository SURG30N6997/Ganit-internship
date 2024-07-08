
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class VESIT_3040302_121_Assign8_Sairam {
    public static void main(String args[]) throws IOException, FileNotFoundException {
        String filename = "C:/Users/saira/Documents/Ganit-internship/Assignment Excel/VLab_3040302_121_3_Assign8_Sairam.xlsx";     //Location where excel file is getting generated
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
            System.out.println(k);
            // Create row
            XSSFRow row = sheet1.createRow(k);
            row.createCell(0).setCellValue(k);
            row.createCell(1).setCellValue("Text");
            row.createCell(2).setCellValue(4);
            row.createCell(3).setCellValue("3040302");

            // Generate random number to perform the operation
            int ncmin = 1;
            int ncmax = 8;
            int dcmin = 1;
            int dcmax = 8;
            int pmin = 1;
            int pmax = 5;
            //Question

            ArrayList<Integer> var1Numerators = new ArrayList<>();
            ArrayList<Integer> var2Numerators = new ArrayList<>();
            ArrayList<Integer> var1Powers = new ArrayList<>();
            ArrayList<Integer> var2Powers = new ArrayList<>();
            ArrayList<Integer> var1Denominators = new ArrayList<>();
            ArrayList<Integer> var2Denominators = new ArrayList<>();
            ArrayList<String> sign = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                if (((int) (Math.random() * (2))) == 0) {
                    sign.add("+");
                } else {
                    sign.add("-");
                }
            }

            for (int i = 0; i < 3; i++) {
                var1Powers.add((int) (Math.random() * (pmax - pmin + 1) + pmin));
                var2Powers.add((int) (Math.random() * (pmax - pmin + 1) + pmin));
                var1Numerators.add((int) (Math.random() * (ncmax - ncmin + 1) + ncmin));
                var2Numerators.add((int) (Math.random() * (ncmax - ncmin + 1) + ncmin));
                var1Denominators.add((int) (Math.random() * (dcmax - dcmin + 1) + dcmin));
                var2Denominators.add((int) (Math.random() * (dcmax - dcmin + 1) + dcmin));

                while (var1Numerators.get(i) == 0) {
                    var1Numerators.set(i, (int) (Math.random() * (ncmax - ncmin + 1) + ncmin));
                }
                while (var2Numerators.get(i) == 0) {
                    var2Numerators.set(i, (int) (Math.random() * (ncmax - ncmin + 1) + ncmin));
                }
            }

            for (int i = 0; i < 3; i++) {
                int gcd1 = gcd2nos(var1Numerators.get(i), var1Denominators.get(i));
                var1Numerators.set(i, var1Numerators.get(i) / gcd1);
                var1Denominators.set(i, var1Denominators.get(i) / gcd1);
                int gcd2 = gcd2nos(var2Numerators.get(i), var2Denominators.get(i));
                var2Numerators.set(i, var2Numerators.get(i) / gcd2);
                var2Denominators.set(i, var2Denominators.get(i) / gcd2);
            }

            if ((var1Numerators.get(0) == var1Numerators.get(1)) && var1Denominators.get(0) == var1Denominators.get(1)) {
                if(var1Denominators.get(1) != 8) {
                    var1Denominators.set(1, (var1Denominators.get(1) + 1));
                } else {var1Denominators.set(1, (var1Denominators.get(1) - 1));}
            }
            if ((var1Numerators.get(1) == var1Numerators.get(2)) && var1Denominators.get(1) == var1Denominators.get(2)) {
                if(var1Denominators.get(2) != 8) {
                    var1Denominators.set(2, (var1Denominators.get(2) + 1));
                } else {var1Denominators.set(2, (var1Denominators.get(2) - 1));}
            }
            if ((var1Numerators.get(0) == var1Numerators.get(2)) && var1Denominators.get(0) == var1Denominators.get(2)) {
                if(var1Denominators.get(2) != 8) {
                    var1Denominators.set(2, (var1Denominators.get(2) + 1));
                } else {var1Denominators.set(2, (var1Denominators.get(2) - 2));}
            }

            if ((var2Numerators.get(0) == var2Numerators.get(1)) && var2Denominators.get(0) == var2Denominators.get(1)) {
                if(var2Denominators.get(1) != 8) {
                    var2Denominators.set(1, (var2Denominators.get(1) + 1));
                } else {var2Denominators.set(1, (var2Denominators.get(1) - 1));}
            }
            if ((var2Numerators.get(1) == var2Numerators.get(2)) && var2Denominators.get(1) == var2Denominators.get(2)) {
                if(var2Denominators.get(2) != 8) {
                    var2Denominators.set(2, (var2Denominators.get(2) + 1));
                } else {var2Denominators.set(2, (var2Denominators.get(2) - 1));}
            }
            if ((var2Numerators.get(0) == var2Numerators.get(2)) && var2Denominators.get(0) == var2Denominators.get(2)) {
                if(var2Denominators.get(2) != 8) {
                    var2Denominators.set(2, (var2Denominators.get(2) + 1));
                } else {var2Denominators.set(2, (var2Denominators.get(2) - 2));}
            }

            for(int i = 0; i < 3; i++){
                int gcd1 = gcd2nos(var1Numerators.get(i), var1Denominators.get(i));
                var1Numerators.set(i, var1Numerators.get(i)/gcd1);
                var1Denominators.set(i, var1Denominators.get(i)/gcd1);
                int gcd2 = gcd2nos(var2Numerators.get(i), var2Denominators.get(i));
                var2Numerators.set(i, var2Numerators.get(i)/gcd2);
                var2Denominators.set(i, var2Denominators.get(i)/gcd2);
            }

            ArrayList<String> variables = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "f", "g", "h", "m", "n", "p", "s", "t", "u", "v", "w", "x", "y", "z"));
            ArrayList<Integer> alphaIndex = new ArrayList<>();
            alphaIndex.add((int) (Math.random() * 17));
            alphaIndex.add(alphaIndex.get(0) + 1);

            ArrayList<StringBuilder> terms = new ArrayList<>();
            for (int i = 0, j = 0; i < 6 && j < 3; i += 2, j++) {
                terms.add(constructFraction1(var1Numerators.get(j), var1Denominators.get(j), variables.get(alphaIndex.get(0)), var1Powers.get(j)));
                terms.add(constructFraction1(var2Numerators.get(j), var2Denominators.get(j), variables.get(alphaIndex.get(1)), var2Powers.get(j)));
            }

            ArrayList<StringBuilder> binomials = new ArrayList<>();
            for (int i = 0; i < 6; i += 2) {
                binomials.add(constructBino(terms.get(i).toString(), terms.get(i + 1).toString()));
            }

            String questionEq = "$\\left\\{" + binomials.get(0) + "\\right\\}" + sign.get(0) + "\\left\\{" + binomials.get(1) + "\\right\\}" + sign.get(1) + "\\left\\{" + binomials.get(2) + "\\right\\}$";
            String questionE = "Find the value using horizontal arrangement :<br>" + questionEq;
            String questionM = "<br>#आडवी मांडणी पद्धतीने किंमत काढा :<br>" + questionEq;
            StringBuilder Question = new StringBuilder(questionE+questionM);

            ArrayList<Integer> ansVar1Numerators = new ArrayList<>(var1Numerators);
            ArrayList<Integer> ansVar2Numerators = new ArrayList<>(var2Numerators);
            ArrayList<Integer> ansVar1Denominators = new ArrayList<>(var1Denominators);
            ArrayList<Integer> ansVar2Denominators = new ArrayList<>(var2Denominators);
            ArrayList<Integer> ansVar1powers = new ArrayList<>(var1Powers);
            ArrayList<Integer> ansVar2powers = new ArrayList<>(var2Powers);
            ArrayList<Integer> ansVar1Numerators1 = new ArrayList<>(var1Numerators);
            ArrayList<Integer> ansVar2Numerators1 = new ArrayList<>(var2Numerators);
            ArrayList<Integer> ansVar1Denominators1 = new ArrayList<>(var1Denominators);
            ArrayList<Integer> ansVar2Denominators1 = new ArrayList<>(var2Denominators);
            ArrayList<Integer> ansVar1powers1 = new ArrayList<>(var1Powers);
            ArrayList<Integer> ansVar2powers1 = new ArrayList<>(var2Powers);
            int[] powers2equalNum = new int[2];
            int[] powers2equalDen = new int[2];
            int[] powers3equalNum1 = new int[3];
            int[] powers3equalDen1 = new int[3];
            int[] powers3equalNum2 = new int[3];
            int[] powers3equalDen2 = new int[3];
            int[] finalFractionVar1 = new int[2];
            int[] finalFractionVar2 = new int[2];
            int[] finalFraction1Var1 = new int[2];
            int[] finalFraction1Var2 = new int[2];
            int commonPowerVar1 = 0;
            int commonPowerVar2 = 0;

            if (sign.get(0) == "-") {
                ansVar1Numerators.set(1, (-1) * ansVar1Numerators.get(1));
                ansVar2Numerators.set(1, (-1) * ansVar2Numerators.get(1));
                ansVar1Numerators1.set(1, (-1) * ansVar1Numerators1.get(1));
                ansVar2Numerators1.set(1, (-1) * ansVar2Numerators1.get(1));
            }
            if (sign.get(1) == "-") {
                ansVar1Numerators.set(2, (-1) * ansVar1Numerators.get(2));
                ansVar2Numerators.set(2, (-1) * ansVar2Numerators.get(2));
                ansVar1Numerators1.set(2, (-1) * ansVar1Numerators1.get(2));
                ansVar2Numerators1.set(2, (-1) * ansVar2Numerators1.get(2));
            }

            ArrayList<Integer> useVar1Numerators = new ArrayList<>(ansVar1Numerators);
            ArrayList<Integer> useVar2Numerators = new ArrayList<>(ansVar2Numerators);
            ArrayList<Integer> useVar1Denominators = new ArrayList<>(ansVar1Denominators);
            ArrayList<Integer> useVar2Denominators = new ArrayList<>(ansVar2Denominators);
            ArrayList<Integer> useVar1Numerators1 = new ArrayList<>(ansVar1Numerators1);
            ArrayList<Integer> useVar2Numerators1 = new ArrayList<>(ansVar2Numerators1);
            ArrayList<Integer> useVar1Denominators1 = new ArrayList<>(ansVar1Denominators1);
            ArrayList<Integer> useVar2Denominators1 = new ArrayList<>(ansVar2Denominators1);
            ArrayList<Integer> useVar1Powers = new ArrayList<>(ansVar1powers);
            ArrayList<Integer> useVar2Powers = new ArrayList<>(ansVar2powers);

            int[] var1NumFactor = ansVar1Numerators.stream().mapToInt(i -> i).toArray();
            int[] var1DenFactor = ansVar1Denominators.stream().mapToInt(i -> i).toArray();
            int[] var2NumFactor = ansVar2Numerators.stream().mapToInt(i -> i).toArray();
            int[] var2DenFactor = ansVar2Denominators.stream().mapToInt(i -> i).toArray();

            ArrayList<StringBuilder> fTerms = new ArrayList<>();
            for (int i = 0, j = 0; i < 6 && j < 3; i += 2, j++) {
                fTerms.add(constructNumFraction(var1NumFactor[j], var1DenFactor[j]));
                fTerms.add(constructNumFraction(var2NumFactor[j], var2DenFactor[j]));
            }


            ArrayList<Integer> var1NumLcm = new ArrayList<>();
            ArrayList<Integer> var1DenLcm = new ArrayList<>();
            ArrayList<Integer> var2NumLcm = new ArrayList<>();
            ArrayList<Integer> var2DenLcm = new ArrayList<>();

            ArrayList<StringBuilder> brTerms = new ArrayList<>();
            for(int i = 0, j = 0; i < 6 && j < 3; i+=2, j++){
                brTerms.add(constructFraction1(ansVar1Numerators.get(j), ansVar1Denominators.get(j), variables.get(alphaIndex.get(0)), var1Powers.get(j)));
                brTerms.add(constructFraction1(ansVar2Numerators.get(j), ansVar2Denominators.get(j), variables.get(alphaIndex.get(1)), var2Powers.get(j)));
            }

            StringBuilder brAnswer = constructAnswer(brTerms);

            if (ansVar1powers.get(0) == ansVar1powers.get(1) && ansVar1powers.get(1) == ansVar1powers.get(2)) {
                for (int i = 0; i < 3; i++) {
                    powers3equalNum1[i] = ansVar1Numerators.get(i);
                    powers3equalDen1[i] = ansVar1Denominators.get(i);
                    var1NumLcm.add(ansVar1Numerators.get(i));
                    var1DenLcm.add(ansVar1Denominators.get(i));
                }
                finalFractionVar1 = equal(powers3equalNum1, powers3equalDen1);
                finalFraction1Var1 = equal1(powers3equalNum1, powers3equalDen1);
                for (int i = 0; i < 3; i++) {
                    ansVar1Numerators.removeLast();
                    ansVar1Denominators.removeLast();
                    ansVar1Numerators1.removeLast();
                    ansVar1Denominators1.removeLast();
                    useVar1Numerators.set(i, 0);
                    useVar1Denominators.set(i, 0);
                    useVar1Numerators1.set(i, 0);
                    useVar1Denominators1.set(i, 0);
                }
                ansVar1Numerators.add(finalFractionVar1[0]);
                ansVar1Denominators.add(finalFractionVar1[1]);
                ansVar1Numerators1.add(finalFraction1Var1[0]);
                ansVar1Denominators1.add(finalFraction1Var1[1]);
                useVar1Numerators.set(0, finalFractionVar1[0]);
                useVar1Denominators.set(0, finalFractionVar1[1]);
                useVar1Numerators1.set(0, finalFraction1Var1[0]);
                useVar1Denominators1.set(0, finalFraction1Var1[1]);
                commonPowerVar1 = ansVar1powers.get(0);
                ansVar1powers.remove(2);
                ansVar1powers.remove(1);
                ansVar1powers1.remove(2);
                ansVar1powers1.remove(1);
                useVar1Powers.set(1,0);
                useVar1Powers.set(2,0);
            } else if (ansVar1powers.get(0) == ansVar1powers.get(1)) {
                for (int i = 0; i < 2; i++) {
                    powers2equalNum[i] = ansVar1Numerators.get(i);
                    powers2equalDen[i] = ansVar1Denominators.get(i);
                    var1NumLcm.add(ansVar1Numerators.get(i));
                    var1DenLcm.add(ansVar1Denominators.get(i));
                }
                finalFractionVar1 = equal(powers2equalNum, powers2equalDen);
                finalFraction1Var1 = equal1(powers2equalNum, powers2equalDen);
                for (int i = 0; i < 2; i++) {
                    ansVar1Numerators.removeFirst();
                    ansVar1Denominators.removeFirst();
                    ansVar1Numerators1.removeFirst();
                    ansVar1Denominators1.removeFirst();
                }
                useVar1Numerators.set(1, 0);
                useVar1Denominators.set(1, 0);
                useVar1Numerators1.set(1, 0);
                useVar1Denominators1.set(1, 0);
                ansVar1Numerators.add(0, finalFractionVar1[0]);
                ansVar1Denominators.add(0, finalFractionVar1[1]);
                ansVar1Numerators1.add(0, finalFraction1Var1[0]);
                ansVar1Denominators1.add(0, finalFraction1Var1[1]);
                useVar1Numerators.set(0, finalFractionVar1[0]);
                useVar1Denominators.set(0, finalFractionVar1[1]);
                useVar1Numerators1.set(0, finalFraction1Var1[0]);
                useVar1Denominators1.set(0, finalFraction1Var1[1]);
                commonPowerVar1 = ansVar1powers.get(0);
                ansVar1powers.remove(1);
                ansVar1powers1.remove(1);
                useVar1Powers.set(1, 0);
            } else if (ansVar1powers.get(1) == ansVar1powers.get(2)) {
                for (int i = 1; i < 3; i++) {
                    powers2equalNum[i - 1] = ansVar1Numerators.get(i);
                    powers2equalDen[i - 1] = ansVar1Denominators.get(i);
                    var1NumLcm.add(ansVar1Numerators.get(i));
                    var1DenLcm.add(ansVar1Denominators.get(i));
                }
                finalFractionVar1 = equal(powers2equalNum, powers2equalDen);
                finalFraction1Var1 = equal1(powers2equalNum, powers2equalDen);
                for (int i = 1; i < 3; i++) {
                    ansVar1Numerators.removeLast();
                    ansVar1Denominators.removeLast();
                    ansVar1Numerators1.removeLast();
                    ansVar1Denominators1.removeLast();
                }
                useVar1Numerators.set(2, 0);
                useVar1Denominators.set(2, 0);
                useVar1Numerators1.set(2, 0);
                useVar1Denominators1.set(2, 0);
                ansVar1Numerators.add(1, finalFractionVar1[0]);
                ansVar1Denominators.add(1, finalFractionVar1[1]);
                ansVar1Numerators1.add(1, finalFraction1Var1[0]);
                ansVar1Denominators1.add(1, finalFraction1Var1[1]);
                useVar1Numerators.set(1, finalFractionVar1[0]);
                useVar1Denominators.set(1, finalFractionVar1[1]);
                useVar1Numerators1.set(1, finalFraction1Var1[0]);
                useVar1Denominators1.set(1, finalFraction1Var1[1]);
                commonPowerVar1 = ansVar1powers.get(1);
                ansVar1powers.remove(2);
                ansVar1powers1.remove(2);
                useVar1Powers.set(2, 0);
            } else if (ansVar1powers.get(0) == ansVar1powers.get(2)) {
                for (int i = 0, j = 0; i < 3 && j < 2; i += 2, j++) {
                    powers2equalNum[j] = ansVar1Numerators.get(i);
                    powers2equalDen[j] = ansVar1Denominators.get(i);
                    var1NumLcm.add(ansVar1Numerators.get(i));
                    var1DenLcm.add(ansVar1Denominators.get(i));
                }
                finalFractionVar1 = equal(powers2equalNum, powers2equalDen);
                finalFraction1Var1 = equal1(powers2equalNum, powers2equalDen);
                ansVar1Numerators.removeFirst();
                ansVar1Denominators.removeFirst();
                ansVar1Numerators.removeLast();
                ansVar1Denominators.removeLast();
                ansVar1Numerators1.removeFirst();
                ansVar1Denominators1.removeFirst();
                ansVar1Numerators1.removeLast();
                ansVar1Denominators1.removeLast();
                useVar1Numerators.set(2, 0);
                useVar1Denominators.set(2, 0);
                useVar1Numerators1.set(2,0);
                useVar1Denominators1.set(2,0);
                commonPowerVar1 = ansVar1powers.get(0);
                ansVar1Numerators.add(0, finalFractionVar1[0]);
                ansVar1Denominators.add(0, finalFractionVar1[1]);
                ansVar1Numerators1.add(0, finalFraction1Var1[0]);
                ansVar1Denominators1.add(0, finalFraction1Var1[1]);
                useVar1Numerators.set(0, finalFractionVar1[0]);
                useVar1Denominators.set(0, finalFractionVar1[1]);
                useVar1Numerators1.set(0, finalFraction1Var1[0]);
                useVar1Denominators1.set(0, finalFraction1Var1[1]);
                ansVar1powers.remove(2);
                ansVar1powers1.remove(2);
                useVar1Powers.set(2, 0);
            }
            if (ansVar2powers.get(0) == ansVar2powers.get(1) && ansVar2powers.get(1) == ansVar2powers.get(2)) {
                for (int i = 0; i < 3; i++) {
                    powers3equalNum2[i] = ansVar2Numerators.get(i);
                    powers3equalDen2[i] = ansVar2Denominators.get(i);
                    var2NumLcm.add(ansVar2Numerators.get(i));
                    var2DenLcm.add(ansVar2Denominators.get(i));
                }
                finalFractionVar2 = equal(powers3equalNum2, powers3equalDen2);
                finalFraction1Var2 = equal1(powers3equalNum2, powers3equalDen2);
                for (int i = 0; i < 3; i++) {
                    ansVar2Numerators.removeLast();
                    ansVar2Denominators.removeLast();
                    ansVar2Numerators1.removeLast();
                    ansVar2Denominators1.removeLast();
                    useVar2Numerators.set(i, 0);
                    useVar2Denominators.set(i, 0);
                    useVar2Numerators1.set(i, 0);
                    useVar2Denominators1.set(i, 0);
                }
                ansVar2Numerators.add(finalFractionVar2[0]);
                ansVar2Denominators.add(finalFractionVar2[1]);
                ansVar2Numerators1.add(finalFraction1Var2[0]);
                ansVar2Denominators1.add(finalFraction1Var2[1]);
                useVar2Numerators.set(0, finalFractionVar2[0]);
                useVar2Denominators.set(0, finalFractionVar2[1]);
                useVar2Numerators1.set(0, finalFraction1Var2[0]);
                useVar2Denominators1.set(0, finalFraction1Var2[1]);
                commonPowerVar2 = ansVar2powers.get(0);
                ansVar2powers.remove(2);
                ansVar2powers.remove(1);
                ansVar2powers1.remove(2);
                ansVar2powers1.remove(1);
                useVar2Powers.set(2,0);
                useVar2Powers.set(1,0);
            } else if (ansVar2powers.get(0) == ansVar2powers.get(1)) {
                for (int i = 0; i < 2; i++) {
                    powers2equalNum[i] = ansVar2Numerators.get(i);
                    powers2equalDen[i] = ansVar2Denominators.get(i);
                    var2NumLcm.add(ansVar2Numerators.get(i));
                    var2DenLcm.add(ansVar2Denominators.get(i));
                }
                finalFractionVar2 = equal(powers2equalNum, powers2equalDen);
                finalFraction1Var2 = equal1(powers2equalNum, powers2equalDen);
                useVar2Numerators.set(1, 0);
                useVar2Denominators.set(1, 0);
                useVar2Numerators1.set(1, 0);
                useVar2Denominators1.set(1, 0);
                for (int i = 0; i < 2; i++) {
                    ansVar2Numerators.removeFirst();
                    ansVar2Denominators.removeFirst();
                    ansVar2Numerators1.removeFirst();
                    ansVar2Denominators1.removeFirst();
                }
                ansVar2Numerators.add(0, finalFractionVar2[0]);
                ansVar2Denominators.add(0, finalFractionVar2[1]);
                ansVar2Numerators1.add(0, finalFraction1Var2[0]);
                ansVar2Denominators1.add(0, finalFraction1Var2[1]);
                useVar2Numerators.set(0, finalFractionVar2[0]);
                useVar2Denominators.set(0, finalFractionVar2[1]);
                useVar2Numerators1.set(0, finalFraction1Var2[0]);
                useVar2Denominators1.set(0, finalFraction1Var2[1]);
                commonPowerVar2 = ansVar2powers.get(0);
                ansVar2powers.remove(1);
                ansVar2powers1.remove(1);
                useVar2Powers.set(1,0);
            } else if (ansVar2powers.get(1) == ansVar2powers.get(2)) {
                for (int i = 1; i < 3; i++) {
                    powers2equalNum[i - 1] = ansVar2Numerators.get(i);
                    powers2equalDen[i - 1] = ansVar2Denominators.get(i);
                    var2NumLcm.add(ansVar2Numerators.get(i));
                    var2DenLcm.add(ansVar2Denominators.get(i));
                }
                finalFractionVar2 = equal(powers2equalNum, powers2equalDen);
                finalFraction1Var2 = equal1(powers2equalNum, powers2equalDen);
                for (int i = 1; i < 3; i++) {
                    ansVar2Numerators.removeLast();
                    ansVar2Denominators.removeLast();
                    ansVar2Numerators1.removeLast();
                    ansVar2Denominators1.removeLast();
                }
                useVar2Numerators.set(2,0);
                useVar2Denominators.set(2,0);
                useVar2Numerators1.set(2,0);
                useVar2Denominators1.set(2,0);
                ansVar2Numerators.add(1, finalFractionVar2[0]);
                ansVar2Denominators.add(1, finalFractionVar2[1]);
                ansVar2Numerators1.add(1, finalFraction1Var2[0]);
                ansVar2Denominators1.add(1, finalFraction1Var2[1]);
                useVar2Numerators.set(1, finalFractionVar2[0]);
                useVar2Denominators.set(1, finalFractionVar2[1]);
                useVar2Numerators1.set(1, finalFraction1Var2[0]);
                useVar2Denominators1.set(1, finalFraction1Var2[1]);
                commonPowerVar2 = ansVar2powers.get(1);
                ansVar2powers.remove(2);
                ansVar2powers1.remove(2);
                useVar2Powers.set(2, 0);
            } else if (ansVar2powers.get(0) == ansVar2powers.get(2)) {
                for (int i = 0, j = 0; i < 3 && j < 2; i += 2, j++) {
                    powers2equalNum[j] = ansVar2Numerators.get(i);
                    powers2equalDen[j] = ansVar2Denominators.get(i);
                    var2NumLcm.add(ansVar2Numerators.get(i));
                    var2DenLcm.add(ansVar2Denominators.get(i));
                }
                finalFractionVar2 = equal(powers2equalNum, powers2equalDen);
                finalFraction1Var2 = equal1(powers2equalNum, powers2equalDen);
                ansVar2Numerators.removeFirst();
                ansVar2Denominators.removeFirst();
                ansVar2Numerators.removeLast();
                ansVar2Denominators.removeLast();
                ansVar2Numerators1.removeFirst();
                ansVar2Denominators1.removeFirst();
                ansVar2Numerators1.removeLast();
                ansVar2Denominators1.removeLast();
                ansVar2Numerators.add(0, finalFractionVar2[0]);
                ansVar2Denominators.add(0, finalFractionVar2[1]);
                ansVar2Numerators1.add(0, finalFraction1Var2[0]);
                ansVar2Denominators1.add(0, finalFraction1Var2[1]);
                useVar2Numerators.set(2,0);
                useVar2Denominators.set(2,0);
                useVar2Numerators1.set(2,0);
                useVar2Denominators1.set(2,0);
                useVar2Numerators.set(0, finalFractionVar2[0]);
                useVar2Denominators.set(0, finalFractionVar2[1]);
                useVar2Numerators1.set(0, finalFraction1Var2[0]);
                useVar2Denominators1.set(0, finalFraction1Var2[1]);
                commonPowerVar2 = ansVar2powers.get(0);
                ansVar2powers.remove(2);
                ansVar2powers1.remove(2);
                useVar2Powers.set(2,0);
            }

            if (!var1DenLcm.isEmpty()) {
                if (var1DenLcm.size() == 2) {
                    int lcmVar1 = lcm2nos(var1DenLcm.get(0), var1DenLcm.get(1));
                    for (int i = 0; i < 2; i++) {
                        var1NumLcm.set(i, var1NumLcm.get(i) * lcmVar1 / var1DenLcm.get(i));
                    }
                    var1DenLcm.clear();
                    var1DenLcm.add(lcmVar1);
                } else if (var1DenLcm.size() == 3) {
                    int lcmVar1 = lcm3nos(var1DenLcm.get(0), var1DenLcm.get(1), var1DenLcm.get(2));
                    for (int i = 0; i < 3; i++) {
                        var1NumLcm.set(i, var1NumLcm.get(i) * lcmVar1 / var1DenLcm.get(i));
                    }
                    var1DenLcm.clear();
                    var1DenLcm.add(lcmVar1);
                }
            }
            if (!var2DenLcm.isEmpty()) {
                if (var2DenLcm.size() == 2) {
                    int lcmVar2 = lcm2nos(var2DenLcm.get(0), var2DenLcm.get(1));
                    for (int i = 0; i < 2; i++) {
                        var2NumLcm.set(i, var2NumLcm.get(i) * lcmVar2 / var2DenLcm.get(i));
                    }
                    var2DenLcm.clear();
                    var2DenLcm.add(lcmVar2);
                } else if (var2DenLcm.size() == 3) {
                    int lcmVar2 = lcm3nos(var2DenLcm.get(0), var2DenLcm.get(1), var2DenLcm.get(2));
                    for (int i = 0; i < 3; i++) {
                        var2NumLcm.set(i, var2NumLcm.get(i) * lcmVar2 / var2DenLcm.get(i));
                    }
                    var2DenLcm.clear();
                    var2DenLcm.add(lcmVar2);
                }
            }

            ArrayList<StringBuilder> lcmTerms = new ArrayList<>();
            if (!var1NumLcm.isEmpty()) {
                lcmTerms.add(constructLcmFraction(var1NumLcm, var1DenLcm, variables.get(alphaIndex.get(0)), commonPowerVar1));
            }
            if (!var2NumLcm.isEmpty()) {
                lcmTerms.add(constructLcmFraction(var2NumLcm, var2DenLcm, variables.get(alphaIndex.get(1)), commonPowerVar2));
            }

            ArrayList<StringBuilder> correctTerms = new ArrayList<>();
            ArrayList<StringBuilder> correctTerms1 = new ArrayList<>();

            for (int i = 0; i < ((ansVar1powers.size() + ansVar2powers.size())); i++) {
                if (i < useVar1Powers.size() && useVar1Denominators.get(i)!=0) {
                    correctTerms.add(constructFraction(useVar1Numerators.get(i), useVar1Denominators.get(i), variables.get(alphaIndex.get(0)), useVar1Powers.get(i)));
                }
                if (i < useVar2Powers.size() && useVar2Denominators.get(i)!=0) {
                    correctTerms.add(constructFraction(useVar2Numerators.get(i), useVar2Denominators.get(i), variables.get(alphaIndex.get(1)), useVar2Powers.get(i)));
                }
            }

            for (int i = 0; i < ((ansVar1powers1.size() + ansVar2powers1.size())); i++) {
                if (i < useVar1Powers.size() && useVar1Denominators1.get(i)!=0) {
                    correctTerms1.add(constructFraction1(useVar1Numerators1.get(i), useVar1Denominators1.get(i), variables.get(alphaIndex.get(0)), useVar1Powers.get(i)));
                }
                if (i < useVar2Powers.size() && useVar2Denominators1.get(i)!=0) {
                    correctTerms1.add(constructFraction1(useVar2Numerators1.get(i), useVar2Denominators1.get(i), variables.get(alphaIndex.get(1)), useVar2Powers.get(i)));
                }
            }

            StringBuilder correctAnswer = constructAnswer(correctTerms);
            StringBuilder correctAnswerNC = constructAnswer(correctTerms1);
            StringBuilder correctAnswerS = constructAnswer(correctTerms);
            String numerator11 = (Math.abs(finalFraction1Var1[0]) == 1) ? "" : String.valueOf(Math.abs(finalFraction1Var1[0]));
            String numerator12 = (Math.abs(finalFraction1Var2[0]) == 1) ? "" : String.valueOf(Math.abs(finalFraction1Var2[0]));
            String numerator1 = (Math.abs(finalFractionVar1[0]) == 1) ? "" : String.valueOf(Math.abs(finalFractionVar1[0]));
            String numerator2 = (Math.abs(finalFractionVar2[0]) == 1) ? "" : String.valueOf(Math.abs(finalFractionVar2[0]));
//            System.out.println(correctAnswerNC);
            if(finalFraction1Var1[0] != 0 && finalFraction1Var1[1] != 0) {
                if (commonPowerVar1 != 1 && finalFraction1Var1[1] != 1) {
                    int pos1 = correctAnswerNC.toString().indexOf("\\dfrac{" + (numerator11) + variables.get(alphaIndex.get(0)) + "^" + commonPowerVar1 + "}{" + finalFraction1Var1[1]);
                    String str = "\\dfrac{" + (numerator11) + variables.get(alphaIndex.get(0)) + "^" + commonPowerVar1 + "}{" + finalFraction1Var1[1] + "}";
                    int len = str.length();
                    correctAnswerNC.insert(pos1 + len, "\\right\\}");
                    correctAnswerNC.insert(pos1, "\\left\\{");
                } else if (commonPowerVar1 == 1 && finalFraction1Var1[1] != 1) {
                    int pos1 = correctAnswerNC.toString().indexOf("\\dfrac{" + (numerator11) + variables.get(alphaIndex.get(0)) + "}{" + finalFraction1Var1[1]);
                    String str = "\\dfrac{" + (numerator11) + variables.get(alphaIndex.get(0)) + "}{" + finalFraction1Var1[1] + "}";
                    int len = str.length();
                    correctAnswerNC.insert(pos1 + len, "\\right\\}");
                    correctAnswerNC.insert(pos1, "\\left\\{");
                } else if (commonPowerVar1 != 1 && finalFraction1Var1[1] == 1) {
                    int pos1 = correctAnswerNC.toString().indexOf((numerator11) + variables.get(alphaIndex.get(0)) + "^" + commonPowerVar1);
                    String str = (numerator11) + variables.get(alphaIndex.get(0)) + "^" + commonPowerVar1;
                    int len = str.length();
                    correctAnswerNC.insert(pos1 + len, "\\right\\}");
                    correctAnswerNC.insert(pos1, "\\left\\{");
                } else if (commonPowerVar1 == 1 && finalFraction1Var1[1] == 1) {
                    int pos1 = correctAnswerNC.toString().indexOf((numerator11) + variables.get(alphaIndex.get(0)));
                    String str = (numerator11) + variables.get(alphaIndex.get(0));
                    int len = str.length();
                    correctAnswerNC.insert(pos1 + len, "\\right\\}");
                    correctAnswerNC.insert(pos1, "\\left\\{");
                }
            }
            if(finalFraction1Var2[0] != 0 && finalFraction1Var2[1] != 0) {
                if (commonPowerVar2 != 1 && finalFraction1Var2[1] != 1) {
                    int pos1 = correctAnswerNC.toString().indexOf("\\dfrac{" + (numerator12) + variables.get(alphaIndex.get(1)) + "^" + commonPowerVar2 + "}{" + finalFraction1Var2[1]+"}");
                    String str = "\\dfrac{" + (numerator12) + variables.get(alphaIndex.get(1)) + "^" + commonPowerVar2 + "}{" + finalFraction1Var2[1] + "}";
                    int len = str.length();
                    correctAnswerNC.insert(pos1 + len, "\\right\\}");
                    correctAnswerNC.insert(pos1, "\\left\\{");
                } else if (commonPowerVar2 == 1 && finalFraction1Var2[1] != 1) {
                    int pos1 = correctAnswerNC.toString().indexOf("\\dfrac{" + (numerator12) + variables.get(alphaIndex.get(1)) + "}{" + finalFraction1Var2[1]);
                    String str = "\\dfrac{" + (numerator12) + variables.get(alphaIndex.get(1)) + "}{" + finalFraction1Var2[1] + "}";
                    int len = str.length();
                    correctAnswerNC.insert(pos1 + len, "\\right\\}");
                    correctAnswerNC.insert(pos1, "\\left\\{");
                } else if (commonPowerVar2 != 1 && finalFraction1Var2[1] == 1) {
                    int pos1 = correctAnswerNC.toString().indexOf((numerator12) + variables.get(alphaIndex.get(1)) + "^" + commonPowerVar2);
                    String str = (numerator12) + variables.get(alphaIndex.get(1)) + "^" + commonPowerVar2;
                    int len = str.length();
                    correctAnswerNC.insert(pos1 + len, "\\right\\}");
                    correctAnswerNC.insert(pos1, "\\left\\{");
                } else if (commonPowerVar2 == 1 && finalFraction1Var2[1] == 1) {
                    int pos1 = correctAnswerNC.toString().indexOf((numerator12) + variables.get(alphaIndex.get(1)));
                    String str = (numerator12) + variables.get(alphaIndex.get(1));
                    int len = str.length();
                    correctAnswerNC.insert(pos1 + len, "\\right\\}");
                    correctAnswerNC.insert(pos1, "\\left\\{");
                }
            }
            if(finalFractionVar1[0] != 0 && finalFractionVar1[1] != 0) {
                if (commonPowerVar1 != 1 && finalFractionVar1[1] != 1) {
                    int pos1 = correctAnswerS.toString().indexOf("\\dfrac{" + (numerator1) + variables.get(alphaIndex.get(0)) + "^" + commonPowerVar1 + "}{" + finalFractionVar1[1] + "}");
                    String str = "\\dfrac{" + (numerator1) + variables.get(alphaIndex.get(0)) + "^" + commonPowerVar1 + "}{" + finalFractionVar1[1] + "}";
                    int len = str.length();
                    correctAnswerS.insert(pos1 + len, "\\right\\}");
                    correctAnswerS.insert(pos1, "\\left\\{");
                } else if (commonPowerVar1 == 1 && finalFractionVar1[1] != 1) {
                    int pos1 = correctAnswerS.toString().indexOf("\\dfrac{" + (numerator1) + variables.get(alphaIndex.get(0)) + "}{" + finalFractionVar1[1] + "}");
                    String str = "\\dfrac{" + (numerator1) + variables.get(alphaIndex.get(0)) + "}{" + finalFractionVar1[1] + "}";
                    int len = str.length();
                    correctAnswerS.insert(pos1 + len, "\\right\\}");
                    correctAnswerS.insert(pos1, "\\left\\{");
                } else if (commonPowerVar1 != 1 && finalFractionVar1[1] == 1) {
                    int pos1 = correctAnswerS.toString().indexOf((numerator1) + variables.get(alphaIndex.get(0)) + "^" + commonPowerVar1);
                    String str = (numerator1) + variables.get(alphaIndex.get(0)) + "^" + commonPowerVar1;
                    int len = str.length();
                    correctAnswerS.insert(pos1 + len, "\\right\\}");
                    correctAnswerS.insert(pos1, "\\left\\{");
                } else if (commonPowerVar1 == 1 && finalFractionVar1[1] == 1) {
                    int pos1 = correctAnswerS.toString().indexOf((numerator1) + variables.get(alphaIndex.get(0)));
                    String str = (numerator1) + variables.get(alphaIndex.get(0));
                    int len = str.length();
                    correctAnswerS.insert(pos1 + len, "\\right\\}");
                    correctAnswerS.insert(pos1, "\\left\\{");
                }
            }
            if(finalFractionVar2[0] != 0 && finalFractionVar2[1] != 0) {
                if (commonPowerVar2 != 1 && finalFractionVar2[1] != 1) {
                    int pos1 = correctAnswerS.toString().indexOf("\\dfrac{" + (numerator2) + variables.get(alphaIndex.get(1)) + "^" + commonPowerVar2 + "}{" + finalFractionVar2[1]);
                    String str = "\\dfrac{" + (numerator2) + variables.get(alphaIndex.get(1)) + "^" + commonPowerVar2 + "}{" + finalFractionVar2[1] + "}";
                    int len = str.length();
                    correctAnswerS.insert(pos1 + len, "\\right\\}");
                    correctAnswerS.insert(pos1, "\\left\\{");
                } else if (commonPowerVar2 == 1 && finalFractionVar2[1] != 1) {
                    int pos1 = correctAnswerS.toString().indexOf("\\dfrac{" + (numerator2) + variables.get(alphaIndex.get(1)) + "}{" + finalFractionVar2[1]);
                    String str = "\\dfrac{" + (numerator2) + variables.get(alphaIndex.get(1)) + "}{" + finalFractionVar2[1] + "}";
                    int len = str.length();
                    correctAnswerS.insert(pos1 + len, "\\right\\}");
                    correctAnswerS.insert(pos1, "\\left\\{");
                } else if (commonPowerVar2 != 1 && finalFractionVar2[1] == 1) {
                    int pos1 = correctAnswerS.toString().indexOf((numerator2) + variables.get(alphaIndex.get(1)) + "^" + commonPowerVar2);
                    String str = (numerator2) + variables.get(alphaIndex.get(1)) + "^" + commonPowerVar2;
                    int len = str.length();
                    correctAnswerS.insert(pos1 + len, "\\right\\}");
                    correctAnswerS.insert(pos1, "\\left\\{");
                } else if (commonPowerVar2 == 1 && finalFractionVar2[1] == 1) {
                    int pos1 = correctAnswerS.toString().indexOf((numerator2) + variables.get(alphaIndex.get(1)));
                    String str = (numerator2) + variables.get(alphaIndex.get(1));
                    int len = str.length();
                    correctAnswerS.insert(pos1 + len, "\\right\\}");
                    correctAnswerS.insert(pos1, "\\left\\{");
                }
            }
            //wrong answers

            ArrayList<Integer> wAnsVar1Numerators = new ArrayList<>(ansVar1Numerators);
            ArrayList<Integer> wAnsVar2Numerators = new ArrayList<>(ansVar2Numerators);
            ArrayList<Integer> wAnsVar1Denominators = new ArrayList<>(ansVar1Denominators);
            ArrayList<Integer> wAnsVar2Denominators = new ArrayList<>(ansVar2Denominators);
            ArrayList<Integer> wAnsVar1powers = new ArrayList<>(ansVar1powers);
            ArrayList<Integer> wAnsVar2powers = new ArrayList<>(ansVar2powers);
            ArrayList<StringBuilder> wrongTerms = new ArrayList<>();
            ArrayList<String> wrongAnswers = new ArrayList<>();

            for (int i = 0; i < wAnsVar1Numerators.size(); i++) {
                wAnsVar1Numerators.set(i, (-1) * wAnsVar1Numerators.get(i));
            }

            for (int i = 0; i < (wAnsVar1powers.size() + wAnsVar2powers.size()); i++) {
                if (i < wAnsVar1powers.size()) {
                    wrongTerms.add(constructFraction(wAnsVar1Numerators.get(i), wAnsVar1Denominators.get(i), variables.get(alphaIndex.get(0)), wAnsVar1powers.get(i)));
                }
                if (i < wAnsVar2powers.size()) {
                    wrongTerms.add(constructFraction(wAnsVar2Numerators.get(i), wAnsVar2Denominators.get(i), variables.get(alphaIndex.get(1)), wAnsVar2powers.get(i)));
                }
            }

            StringBuilder wrongAnswer1 = constructAnswer(wrongTerms);

            for (int i = 0; i < wAnsVar1Numerators.size(); i++) {
                wAnsVar1Numerators.set(i, (-1) * wAnsVar1Numerators.get(i));
            }

            for (int i = 0; i < wAnsVar2Numerators.size(); i++) {
                wAnsVar2Numerators.set(i, (-1) * wAnsVar2Numerators.get(i));
            }

            wrongTerms.clear();

            for (int i = 0; i < (wAnsVar1powers.size() + wAnsVar2powers.size()); i++) {
                if (i < wAnsVar1powers.size()) {
                    wrongTerms.add(constructFraction(wAnsVar1Numerators.get(i), wAnsVar1Denominators.get(i), variables.get(alphaIndex.get(0)), wAnsVar1powers.get(i)));
                }
                if (i < wAnsVar2powers.size()) {
                    wrongTerms.add(constructFraction(wAnsVar2Numerators.get(i), wAnsVar2Denominators.get(i), variables.get(alphaIndex.get(1)), wAnsVar2powers.get(i)));
                }
            }

            StringBuilder wrongAnswer2 = constructAnswer(wrongTerms);

            for (int i = 0; i < wAnsVar1Numerators.size(); i++) {
                wAnsVar1Numerators.set(i, (-1) * wAnsVar1Numerators.get(i));
            }

            wrongTerms.clear();

            for (int i = 0; i < (wAnsVar1powers.size() + wAnsVar2powers.size()); i++) {
                if (i < wAnsVar1powers.size()) {
                    wrongTerms.add(constructFraction(wAnsVar1Numerators.get(i), wAnsVar1Denominators.get(i), variables.get(alphaIndex.get(0)), wAnsVar1powers.get(i)));
                }
                if (i < wAnsVar2powers.size()) {
                    wrongTerms.add(constructFraction(wAnsVar2Numerators.get(i), wAnsVar2Denominators.get(i), variables.get(alphaIndex.get(1)), wAnsVar2powers.get(i)));
                }
            }

            StringBuilder wrongAnswer3 = constructAnswer(wrongTerms);

            wrongAnswers.add(wrongAnswer1.toString());
            wrongAnswers.add(wrongAnswer2.toString());
            wrongAnswers.add(wrongAnswer3.toString());

            ArrayList<Integer> cAnsVar1Numerators = new ArrayList<>(var1Numerators);
            ArrayList<Integer> cAnsVar2Numerators = new ArrayList<>(var2Numerators);
            ArrayList<Integer> cAnsVar1Denominators = new ArrayList<>(var1Denominators);
            ArrayList<Integer> cAnsVar2Denominators = new ArrayList<>(var2Denominators);

            if(sign.get(0)=="-"){
                cAnsVar1Numerators.set(1, (-1)*cAnsVar1Numerators.get(1));
                cAnsVar2Numerators.set(1, (-1)*cAnsVar2Numerators.get(1));
            }
            if(sign.get(1)=="-"){
                cAnsVar1Numerators.set(2, (-1)*cAnsVar1Numerators.get(2));
                cAnsVar2Numerators.set(2, (-1)*cAnsVar2Numerators.get(2));
            }

            ArrayList<StringBuilder> cTerms = new ArrayList<>();
            for (int i = 0, j = 0; i < 6 && j < 3; i += 2, j++) {
                cTerms.add(constructFraction1(cAnsVar1Numerators.get(j), cAnsVar1Denominators.get(j), variables.get(alphaIndex.get(0)), var1Powers.get(j)));
                cTerms.add(constructFraction1(cAnsVar2Numerators.get(j), cAnsVar2Denominators.get(j), variables.get(alphaIndex.get(1)), var2Powers.get(j)));
            }

            ArrayList<String> solTerms = new ArrayList<>();
            ArrayList<String> solFTerms = new ArrayList<>();
            ArrayList<String> lcmEqTerms = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                String temp = cTerms.get(i).toString();
                StringBuilder temp2 = fTerms.get(i);
                boolean bool2 = false;
                for(int n = 0; n < solTerms.size(); n++){
                    String temp3 = solTerms.get(n);
                    String temp5 = temp;
                    if(temp5.contains("\\dfrac")){
                        temp5 = temp5.replace("\\dfrac", "");
                    }
                    if(temp5.contains("\\left\\{")){
                        temp5 = temp5.replace("\\left\\{", "");
                    }
                    if(temp5.contains("\\right\\}")){
                        temp5 = temp5.replace("\\right\\}", "");
                    }
                    if(temp5.contains("-")){
                        temp5 = temp5.replace("-", "");
                    }
                    if(temp3.contains("\\dfrac")){
                        temp3 = temp3.replace("\\dfrac", "");
                    }
                    if(temp3.contains("\\left\\{")){
                        temp3 = temp3.replace("\\left\\{", "");
                    }
                    if(temp3.contains("\\right\\}")){
                        temp3 = temp3.replace("\\right\\}", "");
                    }
                    if(temp3.contains("-")){
                        temp3 = temp3.replace("-", "");
                    }
                    if(temp3.length()==1){
                        StringBuilder temp4 = new StringBuilder(temp3);
                        temp4.append("^1");
                        temp3 = temp4.toString();
                    }
                    String variable ="";
                    if(i%2 == 0){
                        variable = variables.get(alphaIndex.get(0));
                    } else {
                        variable = variables.get(alphaIndex.get(1));
                    }
                    if(temp5.length() == 1){
                        StringBuilder temp4 = new StringBuilder(temp5);
                        temp4.append("^1");
                        temp5 = temp4.toString();
                    }
                    if(temp5.contains(temp3)){
                        if(temp3.contains("^1")){
                            temp3.replace("^1", "");
                        }
                        if(temp.contains("^1")){
                            temp.replace("^1", "");
                        }
                        bool2 = true;
                        break;
                    }
                }
                if (bool2 == false) {
                    solTerms.add(temp);
                    if (i % 2 == 0) {
                        if (var1Powers.get(i / 2) == 1) {
                            temp2.append(variables.get(alphaIndex.get(0)));
                            String tempStr = temp2.toString();
                            if (tempStr.equals("1" + variables.get(alphaIndex.get(0))) || tempStr.equals("-1" + variables.get(alphaIndex.get(0)))) {
                                tempStr = tempStr.replace("1", "");
                            }
                            solFTerms.add(tempStr);
                            lcmEqTerms.add(tempStr);
                        } else {
                            temp2.append(variables.get(alphaIndex.get(0)) + "^" + var1Powers.get(i / 2));
                            String tempStr = temp2.toString();
                            if (tempStr.equals("1" + variables.get(alphaIndex.get(0)) + "^" + var1Powers.get(i / 2)) || tempStr.equals("-1" + variables.get(alphaIndex.get(0)) + "^" + var1Powers.get(i / 2))) {
                                tempStr = tempStr.replace("1", "");
                            }
                            solFTerms.add(tempStr);
                            lcmEqTerms.add(tempStr);
                        }
                    } else {
                        if (var2Powers.get(i / 2) == 1) {
                            temp2.append(variables.get(alphaIndex.get(1)));
                            String tempStr = temp2.toString();
                            if (tempStr.equals("1" + variables.get(alphaIndex.get(1))) || tempStr.equals("-1" + variables.get(alphaIndex.get(1)))) {
                                tempStr = tempStr.replace("1", "");
                            }
                            solFTerms.add(tempStr);
                            lcmEqTerms.add(tempStr);
                        } else {
                            temp2.append(variables.get(alphaIndex.get(1)) + "^" + var2Powers.get(i / 2));
                            String tempStr = temp2.toString();
                            if (tempStr.equals("1" + variables.get(alphaIndex.get(1)) + "^" + var2Powers.get(i / 2)) || tempStr.equals("-1" + variables.get(alphaIndex.get(1)) + "^" + var2Powers.get(i / 2))) {
                                tempStr = tempStr.replace("1", "");
                            }
                            solFTerms.add(tempStr);
                            lcmEqTerms.add(tempStr);
                        }
                    }

                    int bool = 0;
                    int bool1 = 0;
                    for (int j = i + 2; j < 6; j += 2) {
                        if (j % 2 == 0) {
                            if (var1Powers.get(i / 2) == 1) {
                                if (cTerms.get(j).toString().contains(variables.get(alphaIndex.get(0))) && !(cTerms.get(j).toString().contains("^"))) {
                                    if (i == 0 || !(cTerms.get(j - 2).toString().contains("\\left\\{"))) {
                                        if (powers3equalDen1[0] != 0 && powers3equalDen1[1] != 0 && powers3equalDen1[2] != 0) {
                                            if (bool == 0) {
                                                cTerms.set((i), cTerms.get(i).insert(0, "\\left\\{"));
                                                StringBuilder tempo = fTerms.get(i);
                                                if (tempo.toString().contains("\\dfrac")) {
                                                    int loc = tempo.toString().indexOf("\\dfrac");
                                                    tempo = new StringBuilder(tempo.toString().replace("\\dfrac", ""));
                                                    tempo = new StringBuilder(tempo.toString().replace(variables.get(alphaIndex.get(0)), ""));
                                                    tempo.insert(loc, "\\dfrac");
                                                } else {
                                                    tempo = new StringBuilder(tempo.toString().replace(variables.get(alphaIndex.get(0)), ""));
                                                }
                                                fTerms.set(i, new StringBuilder(tempo));
                                                fTerms.set(i, fTerms.get(i).insert(0, "\\left\\{"));
                                                lcmEqTerms.removeLast();
                                                solTerms.removeLast();
                                                solFTerms.removeLast();
                                                solTerms.add(cTerms.get(i).toString());
                                                solFTerms.add(fTerms.get(i).toString());
                                                bool++;
                                            }
                                        } else if (!(cTerms.get(j - 2).toString().contains("\\left\\{"))) {
                                            cTerms.set((i), cTerms.get(i).insert(0, "\\left\\{"));
                                            StringBuilder tempo = fTerms.get(i);
                                            if (tempo.toString().contains("\\dfrac")) {
                                                int loc = tempo.toString().indexOf("\\dfrac");
                                                tempo = new StringBuilder(tempo.toString().replace("\\dfrac", ""));
                                                tempo = new StringBuilder(tempo.toString().replace(variables.get(alphaIndex.get(0)), ""));
                                                tempo.insert(loc, "\\dfrac");
                                            } else {
                                                tempo = new StringBuilder(tempo.toString().replace(variables.get(alphaIndex.get(0)), ""));
                                            }
                                            fTerms.set(i, new StringBuilder(tempo));
                                            fTerms.set(i, fTerms.get(i).insert(0, "\\left\\{"));
                                            lcmEqTerms.removeLast();
                                            solTerms.removeLast();
                                            solFTerms.removeLast();
                                            solTerms.add(cTerms.get(i).toString());
                                            solFTerms.add(fTerms.get(i).toString());
                                        }
                                    }
                                    if ((((cTerms.get(j - 2).toString().contains("\\left\\{")) || (cTerms.get(j - 4).toString().contains("\\left\\{")) && cTerms.get(j + 1).toString().contains(variables.get(alphaIndex.get(1)))))) {
                                        if (powers3equalDen1[0] != 0 && powers3equalDen1[1] != 0 && powers3equalDen1[2] != 0) {
                                            if (bool1 == 1) {
                                                cTerms.set((j), cTerms.get(j).append("\\right\\}"));
                                                fTerms.set((j), fTerms.get(j).append("\\right\\}"));
                                                fTerms.set(j, fTerms.get(j).append(variables.get(alphaIndex.get(0))));
                                            } else {
                                                bool1++;

                                            }
                                        } else {
                                            cTerms.set((j), cTerms.get(j).append("\\right\\}"));
                                            fTerms.set((j), fTerms.get(j).append("\\right\\}"));
                                            fTerms.set(j, fTerms.get(j).append(variables.get(alphaIndex.get(0))));
                                        }
                                    }
                                    solTerms.add(cTerms.get(j).toString());
                                    for (int m = 0; m < lcmTerms.size(); m++) {
                                        String yes = lcmTerms.get(m).toString();
                                        if(yes.contains("\\left\\{")){
                                            yes = yes.replace("\\left\\{", "");
                                        }
                                        if(yes.contains("\\right\\}")){
                                            yes = yes.replace("\\right\\}", "");
                                        }
                                        if(yes.contains("\\dfrac")){
                                            yes = yes.replace("\\dfrac", "");
                                        }
                                        if ((yes.contains(variables.get(alphaIndex.get(0)))) && !(yes.contains("^"))) {
                                            if (!lcmEqTerms.contains(lcmTerms.get(m).toString())) {
                                                lcmEqTerms.add(lcmTerms.get(m).toString());
                                            }
                                        }
                                    }
                                    solFTerms.add(fTerms.get(j).toString());
                                }
                            } else if (cTerms.get(j).toString().contains(variables.get(alphaIndex.get(0))) && (cTerms.get(j).toString().contains("^"))) {
                                if (cTerms.get(j).toString().contains(variables.get(alphaIndex.get(0)) + "^" + var1Powers.get(i / 2))) {
                                    if (i == 0 || !(cTerms.get(j - 2).toString().contains("\\left\\{"))) {
                                        if (powers3equalDen1[0] != 0 && powers3equalDen1[1] != 0 && powers3equalDen1[2] != 0) {
                                            if (bool == 0) {
                                                cTerms.set((i), cTerms.get(i).insert(0, "\\left\\{"));
                                                StringBuilder tempo = fTerms.get(i);
                                                if (tempo.toString().contains("\\dfrac")) {
                                                    int loc = tempo.toString().indexOf("\\dfrac");
                                                    tempo = new StringBuilder(tempo.toString().replace("\\dfrac", ""));
                                                    tempo = new StringBuilder(tempo.toString().replace(variables.get(alphaIndex.get(0)) + "^" + var1Powers.get(i / 2), ""));
                                                    tempo.insert(loc, "\\dfrac");
                                                } else {
                                                    tempo = new StringBuilder(tempo.toString().replace(variables.get(alphaIndex.get(0)) + "^" + var1Powers.get(i / 2), ""));
                                                }
                                                fTerms.set(i, tempo);
                                                fTerms.set((i), fTerms.get(i).insert(0, "\\left\\{"));
                                                lcmEqTerms.removeLast();
                                                solTerms.removeLast();
                                                solFTerms.removeLast();
                                                solTerms.add(cTerms.get(i).toString());
                                                solFTerms.add(fTerms.get(i).toString());
                                                bool++;
                                            }
                                        } else if (!(cTerms.get(j - 2).toString().contains("\\left\\{"))){
                                            cTerms.set((i), cTerms.get(i).insert(0, "\\left\\{"));
                                            StringBuilder tempo = fTerms.get(i);
                                            if (tempo.toString().contains("\\dfrac")) {
                                                int loc = tempo.toString().indexOf("\\dfrac");
                                                tempo = new StringBuilder(tempo.toString().replace("\\dfrac", ""));
                                                tempo = new StringBuilder(tempo.toString().replace(variables.get(alphaIndex.get(0)) + "^" + var1Powers.get(i / 2), ""));
                                                tempo.insert(loc, "\\dfrac");
                                            } else {
                                                tempo = new StringBuilder(tempo.toString().replace(variables.get(alphaIndex.get(0)) + "^" + var1Powers.get(i / 2), ""));
                                            }
                                            fTerms.set(i, tempo);
                                            fTerms.set((i), fTerms.get(i).insert(0, "\\left\\{"));
                                            lcmEqTerms.removeLast();
                                            solTerms.removeLast();
                                            solFTerms.removeLast();
                                            solTerms.add(cTerms.get(i).toString());
                                            solFTerms.add(fTerms.get(i).toString());
                                        }
                                    }
                                    if (((cTerms.get(j - 2).toString().contains("\\left\\{") || (cTerms.get(j - 4).toString().contains("\\left\\{")) && (cTerms.get(j + 1).toString().contains(variables.get(alphaIndex.get(1)))) || cTerms.get(j + 1) == null))) {
                                        if (powers3equalDen1[0] != 0 && powers3equalDen1[1] != 0 && powers3equalDen1[2] != 0) {
                                            if (bool1 == 1) {
                                                cTerms.set((j), cTerms.get(j).append("\\right\\}"));
                                                fTerms.set((j), fTerms.get(j).append("\\right\\}"));
                                                fTerms.set(j, fTerms.get(j).append(variables.get(alphaIndex.get(0)) + "^" + var1Powers.get(i / 2)));
                                            } else {
                                                bool1++;
                                            }
                                        } else {
                                            cTerms.set((j), cTerms.get(j).append("\\right\\}"));
                                            fTerms.set((j), fTerms.get(j).append("\\right\\}"));
                                            fTerms.set(j, fTerms.get(j).append(variables.get(alphaIndex.get(0)) + "^" + var1Powers.get(i / 2)));
                                        }
                                    }
                                    solTerms.add(cTerms.get(j).toString());
                                    solFTerms.add(fTerms.get(j).toString());
                                    for (int m = 0; m < lcmTerms.size(); m++) {
                                        String yes = lcmTerms.get(m).toString();
                                        if(yes.contains("\\left\\{")){
                                            yes = yes.replace("\\left\\{", "");
                                        }
                                        if(yes.contains("\\right\\}")){
                                            yes = yes.replace("\\right\\}", "");
                                        }
                                        if(yes.contains("\\dfrac")){
                                            yes = yes.replace("\\dfrac", "");
                                        }
                                        if (yes.contains(variables.get(alphaIndex.get(0)) + "^" + var1Powers.get(i/2))){
                                            if (!lcmEqTerms.contains(lcmTerms.get(m).toString())) {
                                                lcmEqTerms.add(lcmTerms.get(m).toString());
                                            }
                                        }
                                    }

                                }
                            }
                        } else {
                            if (var2Powers.get(i / 2) == 1) {
                                if (cTerms.get(j).toString().contains(variables.get(alphaIndex.get(1))) && !(cTerms.get(j).toString().contains("^"))) {
                                    if (!(cTerms.get(j - 2).toString().contains("\\left\\{"))) {
                                        if (bool == 0) {
                                            cTerms.set((i), cTerms.get(i).insert(0, "\\left\\{"));
                                            StringBuilder tempo = fTerms.get(i);
                                            if (tempo.toString().contains("\\dfrac")) {
                                                int loc = tempo.toString().indexOf("\\dfrac");
                                                tempo = new StringBuilder(tempo.toString().replace("\\dfrac", ""));
                                                tempo = new StringBuilder(tempo.toString().replace(variables.get(alphaIndex.get(1)), ""));
                                                tempo.insert(loc, "\\dfrac");
                                            } else {
                                                tempo = new StringBuilder(tempo.toString().replace(variables.get(alphaIndex.get(1)), ""));
                                            }
                                            fTerms.set(i, tempo);
                                            fTerms.set((i), fTerms.get(i).insert(0, "\\left\\{"));
                                            lcmEqTerms.removeLast();
                                            solTerms.removeLast();
                                            solFTerms.removeLast();
                                            solTerms.add(cTerms.get(i).toString());
                                            solFTerms.add(fTerms.get(i).toString());
                                            bool++;
                                        }
                                    }
                                    if (j == 5) {
                                        cTerms.set((j), cTerms.get(j).append("\\right\\}"));
                                        fTerms.set((j), fTerms.get(j).append("\\right\\}"));
                                        fTerms.set((j), fTerms.get(j).append(variables.get(alphaIndex.get(1))));
                                    } else if (((cTerms.get(j - 2).toString().contains("\\left\\{") || (cTerms.get(j - 4).toString().contains("\\left\\{")) && cTerms.get(j + 1).toString().contains(variables.get(alphaIndex.get(1)))))) {
                                        if (powers3equalDen2[0] != 0 && powers3equalDen2[1] != 0 && powers3equalDen2[2] != 0) {
                                            if (bool1 == 1) {
                                                cTerms.set((j), cTerms.get(j).append("\\right\\}"));
                                                fTerms.set((j), fTerms.get(j).append("\\right\\}"));
                                                fTerms.set((j), fTerms.get(j).append(variables.get(alphaIndex.get(1))));
                                            } else {
                                                bool1++;
                                            }
                                        } else {
                                            cTerms.set((j), cTerms.get(j).append("\\right\\}"));
                                            fTerms.set((j), fTerms.get(j).append("\\right\\}"));
                                            fTerms.set((j), fTerms.get(j).append(variables.get(alphaIndex.get(1))));
                                        }
                                    }
                                    solTerms.add(cTerms.get(j).toString());
                                    solFTerms.add(fTerms.get(j).toString());
                                    for (int m = 0; m < lcmTerms.size(); m++) {
                                        String yes = lcmTerms.get(m).toString();
                                        if(yes.contains("\\left\\{")){
                                            yes = yes.replace("\\left\\{", "");
                                        }
                                        if(yes.contains("\\right\\}")){
                                            yes = yes.replace("\\right\\}", "");
                                        }
                                        if(yes.contains("\\dfrac")){
                                            yes = yes.replace("\\dfrac", "");
                                        }
                                        System.out.println((yes.contains(variables.get(alphaIndex.get(1)))) && !(yes.contains("^")));
                                        if ((yes.contains(variables.get(alphaIndex.get(1)))) && !(yes.contains("^"))) {
                                            if (!(lcmEqTerms.contains(lcmTerms.get(m).toString()))) {
                                                lcmEqTerms.add(lcmTerms.get(m).toString());
                                            }
                                        }
                                    }
                                }
                            } else if (cTerms.get(j).toString().contains(variables.get(alphaIndex.get(1))) && (cTerms.get(j).toString().contains("^"))) {
                                if (cTerms.get(j).toString().contains(variables.get(alphaIndex.get(1)) + "^" + var2Powers.get(i / 2))) {
                                    if (!(cTerms.get(j - 1).toString().contains("\\left\\{"))) {
                                        if (bool == 0) {
                                            cTerms.set((i), cTerms.get(i).insert(0, "\\left\\{"));
                                            StringBuilder tempo = fTerms.get(i);
                                            if (tempo.toString().contains("\\dfrac")) {
                                                int loc = tempo.toString().indexOf("\\dfrac");
                                                tempo = new StringBuilder(tempo.toString().replace("\\dfrac", ""));
                                                tempo = new StringBuilder(fTerms.get(i).toString().replace(variables.get(alphaIndex.get(1)) + "^" + var2Powers.get(i / 2), ""));
                                                tempo.insert(loc, "\\dfrac");
                                            } else {
                                                tempo = new StringBuilder(fTerms.get(i).toString().replace(variables.get(alphaIndex.get(1)) + "^" + var2Powers.get(i / 2), ""));
                                            }
                                            fTerms.set(i, tempo);
                                            fTerms.set((i), fTerms.get(i).insert(0, "\\left\\{"));
                                            lcmEqTerms.removeLast();
                                            solTerms.removeLast();
                                            solFTerms.removeLast();
                                            solTerms.add(cTerms.get(i).toString());
                                            solFTerms.add(fTerms.get(i).toString());
                                            bool++;
                                        }
                                    }
                                    if (j == 5) {
                                        cTerms.set((j), cTerms.get(j).append("\\right\\}"));
                                        fTerms.set((j), fTerms.get(j).append("\\right\\}"));
                                        fTerms.set(j, fTerms.get(j).append(variables.get(alphaIndex.get(1)) + "^" + var2Powers.get(i / 2)));
                                    } else if (((cTerms.get(j - 2).toString().contains("\\left\\{") || (cTerms.get(j - 4).toString().contains("\\left\\{")) && cTerms.get(j + 1).toString().contains(variables.get(alphaIndex.get(1)))))) {
                                        if (powers3equalDen2[0] != 0 && powers3equalDen2[1] != 0 && powers3equalDen2[2] != 0) {
                                            if (bool1 == 1) {
                                                cTerms.set((j), cTerms.get(j).append("\\right\\}"));
                                                fTerms.set((j), fTerms.get(j).append("\\right\\}"));
                                                fTerms.set(j, fTerms.get(j).append(variables.get(alphaIndex.get(1)) + "^" + var2Powers.get(i / 2)));
                                            } else {
                                                bool1++;
                                            }
                                        } else {
                                            cTerms.set((j), cTerms.get(j).append("\\right\\}"));
                                            fTerms.set((j), fTerms.get(j).append("\\right\\}"));
                                            fTerms.set(j, fTerms.get(j).append(variables.get(alphaIndex.get(1)) + "^" + var2Powers.get(i / 2)));
                                        }
                                    }
                                    solTerms.add(cTerms.get(j).toString());
                                    solFTerms.add(fTerms.get(j).toString());

                                    for (int m = 0; m < lcmTerms.size(); m++) {
                                        String yes = lcmTerms.get(m).toString();
                                        if(yes.contains("\\left\\{")){
                                            yes = yes.replace("\\left\\{", "");
                                        }
                                        if(yes.contains("\\right\\}")){
                                            yes = yes.replace("\\right\\}", "");
                                        }
                                        if(yes.contains("\\dfrac")){
                                            yes = yes.replace("\\dfrac", "");
                                        }
                                        if (yes.contains(variables.get(alphaIndex.get(1)) + "^" + var2Powers.get(i/2))){
                                            if (!(lcmEqTerms.contains(lcmTerms.get(m).toString()))) {
                                                lcmEqTerms.add(lcmTerms.get(m).toString());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for(int i = 0; i < solFTerms.size(); i++) {
                if (solFTerms.get(i).contains("\\dfrac")) {
                    int loc = solFTerms.get(i).indexOf("\\dfrac");
                    if(solFTerms.get(i).indexOf("\\dfrac", loc+1) != -1){
                        solFTerms.set(i, solFTerms.get(i).replaceFirst("\\\\dfrac", ""));
                    }
                }
            }
            for(int i = 0; i < solFTerms.size(); i++) {
                String test = solFTerms.get(i);
                if (((test.contains(variables.get(alphaIndex.get(0)))) || (test.contains(variables.get(alphaIndex.get(1))))) && !test.contains("\\right\\}") && !test.contains("\\left\\{")){
                    if(test.contains("\\dfrac")) {
                        int tempLoc = test.indexOf("\\dfrac");
                        test = test.replace("\\dfrac", "");
                        int loc = test.indexOf("}");
                        int loc1 = test.indexOf("}", loc+1);
                        String test1 = test.substring(loc1+1, test.length());
                        test = test.replace(test1, "");
                        StringBuilder test2 = new StringBuilder(test);
                        test2.insert((loc), test1);
                        test2.insert(tempLoc, "\\dfrac");
                        test = test2.toString();
                        if((test.charAt(loc-1) == '1' && test.charAt(loc-2) == '{') || (test.charAt(loc1-1) == '1' && test.charAt(loc1-2) == '{')){
                            test = test.replace("1", "");
                        }
                        solFTerms.set(i, test);
                    }
                }
            }
            for(int i = 0; i < lcmEqTerms.size(); i++){
                String test = lcmEqTerms.get(i);
                if(!(test.contains("\\left\\{")) && !(test.contains("\\right\\}"))) {
                    if (test.contains("\\dfrac")) {
                        int tempLoc = test.indexOf("\\dfrac");
                        test = test.replace("\\dfrac", "");
                        int loc = test.indexOf("}");
                        int loc1 = test.indexOf("}", loc + 1);
                        String test1 = test.substring(loc1 + 1, test.length());
                        test = test.replace(test1, "");
                        StringBuilder test2 = new StringBuilder(test);
                        test2.insert(loc, test1);
                        test2.insert(tempLoc, "\\dfrac");
                        test = test2.toString();
                        if (test.charAt(loc - 1) == '1' && test.charAt(loc - 2) == '{') {
                            test = test.replace("1", "");
                        }
                        lcmEqTerms.set(i, test);
                    }
                }
            }

            String likeTerms = "$" + likeTerms(solTerms) + "$";
            String lcmEq = "$"+likeTerms(lcmEqTerms)+"$";
            String factor = "$" + likeTerms(solFTerms) + "$";

            int qNo = 1;
            int startIndex = 0;
            while(lcmEq.indexOf("\\left\\{", startIndex) != -1){
                qNo++;
                System.out.println("Index: "+startIndex);
                startIndex = lcmEq.indexOf("\\left\\{", startIndex) + 1;
            }

            Question.insert(0, qNo+") ");
            System.out.println(Question);
            String QuestionS = Question.toString();

            int gcdVar1 = gcd2nos(finalFraction1Var1[0], finalFraction1Var1[1]);
            int gcdVar2 = gcd2nos(finalFraction1Var2[0], finalFraction1Var2[1]);

            String solutionE = "";
            String solutionM = "";
            if((finalFractionVar1[0] != 0 && finalFractionVar1[1] != 0) || (finalFractionVar2[0]!=0 && finalFractionVar2[1]!=0)){
                if(gcdVar1 > 1 || gcdVar2 > 1){
                    solutionE = "Ans : "+correctAnswer+"<br>" +
                            "Given<br>\n" +
                            ""+questionEq+"<br> \n" +
                            "$=$"+brAnswer+" . . . . by opening the brackets<br>\n" +
                            "$=$"+likeTerms+" . . . . by bringing the like terms together<br>\n" +
                            "$=$"+factor+" . . . . taking out the common factor from like terms<br>\n"+
                            "$=$"+lcmEq+" . . . . by adding the coefficients of like terms using L.C.M.<br>\n"+
                            "$=$"+correctAnswerNC+" . . . . by solving the brackets<br>\n" +
                            "$=$"+correctAnswerS+" . . . . by simplifying<br>\n" +
                            "$\\therefore$ "+correctAnswer+" is the answer.<br>#";

                    solutionM = "उत्तर  : "+correctAnswer+"<br>\n" +
                            "दिल्या नुसार <br>\n" +
                            ""+questionEq+"<br> \n" +
                            "$=$"+brAnswer+" . . . . कंस सोडवून <br>\n" +
                            "$=$"+likeTerms+" . . . . सजातीय पदे एकत्र आणून <br>\n" +
                            "$=$"+factor+" . . . . समान अटींमधून सामान्य घटक काढूण<br>\n"+
                            "$=$"+lcmEq+" . . . . L.C.M वापरून समान संज्ञांचे गुणांक जोडून <br>\n"+
                            "$=$"+correctAnswerNC+" . . . . कंस सोडवून <br>\n" +
                            "$=$"+correctAnswerS+" . . . . सरळरूप देऊन <br>\n" +
                            "$\\therefore$ "+correctAnswer+" हे उत्तर <br>";
                } else {
                    solutionE = "Ans : "+correctAnswer+"<br>" +
                            "Given<br>\n" +
                            ""+questionEq+"<br> \n" +
                            "$=$"+brAnswer+" . . . . by opening the brackets<br>\n" +
                            "$=$"+likeTerms+" . . . . by bringing the like terms together<br>\n" +
                            "$=$"+factor+" . . . . taking out the common factor from like terms<br>\n"+
                            "$=$"+lcmEq+" . . . . by adding the coefficients of like terms using L.C.M.<br>\n"+
                            "$=$"+correctAnswerS+" . . . . by solving the brackets<br>\n" +
                            "$\\therefore$ "+correctAnswer+" is the answer.<br>#";

                    solutionM = "उत्तर  : "+correctAnswer+"<br>\n" +
                            "दिल्या नुसार <br>\n" +
                            ""+questionEq+"<br> \n" +
                            "$=$"+brAnswer+" . . . . कंस सोडवून <br>\n" +
                            "$=$"+likeTerms+" . . . . सजातीय पदे एकत्र आणून <br>\n" +
                            "$=$"+factor+" . . . . समान अटींमधून सामान्य घटक काढूण<br>\n"+
                            "$=$"+lcmEq+" . . . . L.C.M वापरून समान संज्ञांचे गुणांक जोडून <br>\n"+
                            "$=$"+correctAnswerS+" . . . . कंस सोडवून <br>\n" +
                            "$\\therefore$ "+correctAnswer+" हे उत्तर <br>";
                }
            } else {
                solutionE = "Ans : "+correctAnswer+"<br>\n" +
                        "Given<br>\n" +
                        ""+questionEq+"<br> \n" +
                        "$=$"+brAnswer+" . . . . by opening the brackets<br>\n" +
                        "Since there are no like terms<br>\n" +
                        "$\\therefore$ "+correctAnswer+ " is the answer.<br>#";
                solutionM = "उत्तर  : "+correctAnswer+"<br>\n" +
                        "दिल्या नुसार<br>\n" +
                        ""+questionEq+"<br> \n" +
                        "$=$"+brAnswer+" . . . . कंस सोडवून <br>\n" +
                        "यात कोणतीही सजातीय पदे नाहीत. <br>\n" +
                        "$\\therefore$ "+correctAnswer+" हे उत्तर .<br>\n" +
                        "सोपे रूप देऊन हे उत्तर<br>";
            }
//
            String solution = solutionE + solutionM;

            row.createCell(4).setCellValue(QuestionS);
            row.createCell(5).setCellValue(correctAnswer + "<br>");
            row.createCell(6).setCellValue(" ");
            row.createCell(7).setCellValue(" ");
            row.createCell(8).setCellValue(" ");
            row.createCell(9).setCellValue(wrongAnswers.get(0) + "<br>");
            row.createCell(10).setCellValue(wrongAnswers.get(1) + "<br>");
            row.createCell(11).setCellValue(wrongAnswers.get(2) + "<br>");
            row.createCell(12).setCellValue(150);
            row.createCell(13).setCellValue(4);
            row.createCell(14).setCellValue(" ");
            row.createCell(15).setCellValue("2022.sairam.konar@ves.ac.in");
            row.createCell(16).setCellValue(solution);
            row.createCell(17).setCellValue(" ");
            row.createCell(18).setCellValue(121);

            mapsize = map.size();
            map.put(QuestionS, k);
            mapsizeafter = map.size();

            //In Java, a map can consist of virtually any number of key-value pairs, but the keys must always be unique — non-repeating.
            if (mapsize == mapsizeafter) {
                System.out.println("duplicate Question" + k + ". " + QuestionS);
                k--;
            }

            if (correctAnswer.equals(wrongAnswers.get(0)) || correctAnswer.equals(wrongAnswers.get(1)) || correctAnswer.equals(wrongAnswers.get(2)) || wrongAnswers.get(0).equals(wrongAnswers.get(1)) || wrongAnswers.get(0).equals(wrongAnswers.get(2)) || wrongAnswers.get(1).equals(wrongAnswers.get(2))) {
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

    static int[] equal(int[] numerator, int[] denominator) {
        int[] fraction = new int[2];
        int len = denominator.length;
        if (len == 3) {
            if (denominator[0] == denominator[1] && denominator[1] == denominator[2]) {
                fraction = equalAdd(Arrays.copyOf(numerator, numerator.length), Arrays.copyOf(denominator, denominator.length));
            } else {
                fraction = equate(Arrays.copyOf(numerator, numerator.length), Arrays.copyOf(denominator, denominator.length));
            }
        }
        if (len == 2) {
            if (denominator[0] == denominator[1]) {
                fraction = equalAdd(Arrays.copyOf(numerator, numerator.length), Arrays.copyOf(denominator, denominator.length));
            } else {
                fraction = equate(Arrays.copyOf(numerator, numerator.length), Arrays.copyOf(denominator, denominator.length));
            }
        }
        return fraction;
    }

    static int[] equate(int[] numerator, int[] denominator) {
        if (denominator.length == 3) {
            int lcm = lcm3nos(Math.abs(denominator[0]), Math.abs(denominator[1]), Math.abs(denominator[2]));
            for (int i = 0; i < numerator.length; i++) {
                int factor = lcm / denominator[i];
                numerator[i] *= factor;
                denominator[i] = lcm;
            }
        } else if (denominator.length == 2) {
            int lcm = lcm2nos(Math.abs(denominator[0]), Math.abs(denominator[1]));
            for (int i = 0; i < numerator.length; i++) {
                int factor = lcm / denominator[i];
                numerator[i] *= factor;
                denominator[i] = lcm;
            }
        }
        return equalAdd(numerator, denominator);
    }

    static int[] equalAdd(int[] numerator, int[] denominator) {
        int sumNumerator = 0;
        int sumDenominator = denominator[0]; // all denominators are the same after equate
        for (int num : numerator) {
            sumNumerator += num;
        }
        int gcd = gcd2nos(sumNumerator, sumDenominator);
        return new int[]{sumNumerator / gcd, sumDenominator / gcd};
    }

    static int gcd2nos(int num1, int num2) {
        num1 = Math.abs(num1);
        num2 = Math.abs(num2);
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    static int lcm2nos(int num1, int num2) {
        return (Math.abs(num1) * Math.abs(num2)) / gcd2nos(Math.abs(num1), Math.abs(num2));
    }

    static int lcm3nos(int num1, int num2, int num3) {
        return lcm2nos(lcm2nos(num1, num2), num3);
    }

    static StringBuilder constructFraction(int numerator, int denominator, String variable, int power) {
        StringBuilder fraction = new StringBuilder();
        String sign = (numerator < 0) ? "-" : "";
        int gcd = gcd2nos(Math.abs(numerator), denominator);
        numerator /= gcd;
        denominator /= gcd;
        fraction.append(sign);
        String pVariable = (power == 1) ? variable : variable + "^" + power;
        if (denominator == 1) {
            fraction.append((Math.abs(numerator) == 1) ? pVariable : Math.abs(numerator) + pVariable);
        } else {
            fraction.append("\\dfrac{" + ((Math.abs(numerator) == 1) ? pVariable : Math.abs(numerator) + pVariable) + "}{" + denominator + "}");
        }
        return fraction;
    }

    static StringBuilder constructBino(String term1, String term2) {
        StringBuilder binomial = new StringBuilder();
        binomial.append(term1);
        if (!term2.contains("-")) {
            binomial.append("+");
        }
        binomial.append(term2);
        return binomial;
    }

    static StringBuilder constructAnswer(ArrayList terms1) {
        StringBuilder equation = new StringBuilder("$");
        equation.append(terms1.get(0));
        for (int i = 1; i < terms1.size(); i++) {
            if (!terms1.get(i).toString().contains("-")) {
                equation.append("+");
            }
            equation.append(terms1.get(i));
        }
        equation.append("$");
        return equation;
    }

    static String likeTerms(ArrayList<String> terms) {
        StringBuilder equation = new StringBuilder();

        if (terms.get(0).contains("-") && !terms.get(0).contains("\\left\\")) {
            equation.append("-");
        }
        for (int i = 0; i < terms.size(); i++) {
            if (i != 0) {
                if (terms.get(i).contains("\\left\\")) {
                    equation.append("+");
                } else if (!(terms.get(i).contains("-"))) {
                    equation.append("+");
                }
            }
            equation.append(terms.get(i));
        }
        return equation.toString();
    }

    static int[] equalAdd1(int[] numerator, int[] denominator) {
        int sumNumerator = 0;
        int sumDenominator = denominator[0]; // all denominators are the same after equate
        for (int num : numerator) {
            sumNumerator += num;
        }
        return new int[]{sumNumerator, sumDenominator};
    }

    static int[] equal1(int[] numerator, int[] denominator) {
        int[] fraction = new int[2];
        int len = denominator.length;
        if (len == 3) {
            if (denominator[0] == denominator[1] && denominator[1] == denominator[2]) {
                fraction = equalAdd1(Arrays.copyOf(numerator, numerator.length), Arrays.copyOf(denominator, denominator.length));
            } else {
                fraction = equate1(Arrays.copyOf(numerator, numerator.length), Arrays.copyOf(denominator, denominator.length));
            }
        }
        if (len == 2) {
            if (denominator[0] == denominator[1]) {
                fraction = equalAdd1(Arrays.copyOf(numerator, numerator.length), Arrays.copyOf(denominator, denominator.length));
            } else {
                fraction = equate1(Arrays.copyOf(numerator, numerator.length), Arrays.copyOf(denominator, denominator.length));
            }
        }
        return fraction;
    }

    static int[] equate1(int[] numerator, int[] denominator) {
        if(denominator.length == 3) {
            int lcm = lcm3nos(Math.abs(denominator[0]), Math.abs(denominator[1]), Math.abs(denominator[2]));

            for (int i = 0; i < numerator.length; i++) {
                int factor = lcm / denominator[i];
                numerator[i] *= factor;
                denominator[i] = lcm;
            }
        } else if (denominator.length == 2) {
            int lcm = lcm2nos(Math.abs(denominator[0]), Math.abs(denominator[1]));
            for (int i = 0; i < numerator.length; i++) {
                int factor = lcm / denominator[i];
                numerator[i] *= factor;
                denominator[i] = lcm;
            }
        }
        return equalAdd1(numerator, denominator);
    }

    static StringBuilder constructFraction1(int numerator, int denominator, String variable, int power) {
        StringBuilder fraction = new StringBuilder();
        String sign = (numerator < 0) ? "-" : "";
        fraction.append(sign);
        String pVariable = (power == 1) ? variable : variable + "^" + power;
        if (denominator == 1) {
            fraction.append((Math.abs(numerator) == 1) ? pVariable : Math.abs(numerator) + pVariable);
        } else {
            fraction.append("\\dfrac{" + ((Math.abs(numerator) == 1) ? pVariable : Math.abs(numerator) + pVariable) + "}{" + denominator + "}");
        }
        return fraction;
    }

    static StringBuilder constructNumFraction(int numerator, int denominator) {
        StringBuilder fraction = new StringBuilder();
        String sign = (numerator < 0) ? "-" : "";
        fraction.append(sign);
        if (denominator == 1) {
            fraction.append(Math.abs(numerator));
        } else {
            fraction.append("\\dfrac{" + Math.abs(numerator) + "}{" + denominator + "}");
        }
        return fraction;
    }

    static StringBuilder constructLcmFraction(ArrayList<Integer> numerator, ArrayList<Integer> denominator, String variable, int power) {
        StringBuilder fraction = new StringBuilder();
        fraction.append("\\left\\{");
        if (denominator.get(0) != 1) {
            fraction.append("\\dfrac{");
            for (int i = 0; i < numerator.size(); i++) {
                if (i == 0) {
                    if (numerator.get(0) < 0) {
                        fraction.append("-");
                    }
                    fraction.append(Math.abs(numerator.get(i)));
                } else {
                    if (numerator.get(i) < 0) {
                        fraction.append("-");
                    } else {
                        fraction.append("+");
                    }
                    fraction.append(Math.abs(numerator.get(i)));
                }

            }
            fraction.append("}{" + denominator.get(0) + "}\\right\\}");
        } else {
            for (int i = 0; i < numerator.size(); i++) {
                if (i == 0) {
                    if (numerator.get(0) < 0) {
                        fraction.append("-");
                    }
                    fraction.append(Math.abs(numerator.get(i)));
                } else {
                    if (numerator.get(i) < 0) {
                        fraction.append("-");
                    } else {
                        fraction.append("+");
                    }
                    fraction.append(Math.abs(numerator.get(i)));
                }
            }
            fraction.append("\\right\\}");
        }
        if (power == 1) {
            fraction.append(variable);
        } else {
            fraction.append(variable + "^" + power);
        }
        return fraction;
    }
}