import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class VESIT_3040302_121_Assign8_Sairam {
    public static void main(String args[]) throws IOException, FileNotFoundException {
        System.out.println("How many questions you want to enter:-");
        Scanner sc = new Scanner(System.in);
        int mapsize, mapsizeafter;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int q = sc.nextInt();
        for (int k = 1; k < q + 1; k++) {
            // Generate random number to perform the operation
            int ncmin = 1;
            int ncmax = 10;
            int dcmin = 1;
            int dcmax = 10;
            int pmin = 1;
            int pmax = 5;
            System.out.println("Iteration no: "+ k);
            //Question

            ArrayList<Integer> var1Numerators = new ArrayList<>();
            ArrayList<Integer> var2Numerators = new ArrayList<>();
            ArrayList<Integer> var1Powers = new ArrayList<>();
            ArrayList<Integer> var2Powers = new ArrayList<>();
            ArrayList<Integer> var1Denominators = new ArrayList<>();
            ArrayList<Integer> var2Denominators = new ArrayList<>();
            ArrayList<String> sign = new ArrayList<>();
            for(int i = 0; i<2; i++){
                if(((int)(Math.random()*(2)))==0){
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

                while(var1Numerators.get(i) == 0) {
                    var1Numerators.set(i, (int) (Math.random() * (ncmax - ncmin + 1) + ncmin));
                }
                while(var2Numerators.get(i) == 0) {
                    var2Numerators.set(i, (int) (Math.random() * (ncmax - ncmin + 1) + ncmin));
                }
            }

            ArrayList<String> variables = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "f", "g", "h", "m", "n", "p", "s", "t", "u", "v", "w", "x", "y", "z"));
            ArrayList<Integer> alphaIndex = new ArrayList<>();
            alphaIndex.add((int)(Math.random() * 17));
            alphaIndex.add(alphaIndex.get(0) + 1);

            ArrayList<StringBuilder> terms = new ArrayList<>();
            for(int i = 0, j = 0; i < 6 && j < 3; i+=2, j++){
                terms.add(constructFraction(var1Numerators.get(j), var1Denominators.get(j), variables.get(alphaIndex.get(0)), var1Powers.get(j)));
                terms.add(constructFraction(var2Numerators.get(j), var2Denominators.get(j), variables.get(alphaIndex.get(1)), var2Powers.get(j)));
            }

            ArrayList<StringBuilder> binomials = new ArrayList<>();
            for(int i = 0; i < 6; i+=2){
                binomials.add(constructBino(terms.get(i).toString(), terms.get(i+1).toString()));
            }



//            System.out.println(var1Numerators);
//            System.out.println(var2Numerators);
//            System.out.println(var1Denominators);
//            System.out.println(var2Denominators);
            System.out.println(terms);
            System.out.println(binomials);

            String Question = "Find by horizontal arrangement / आडवी मांडणी पद्धतीने किंमत काढा :<br> $("+binomials.get(0)+")"+sign.get(0)+"("+binomials.get(1)+")"+sign.get(1)+"("+binomials.get(2)+")$";
            System.out.println(Question);

            //Answers
            ArrayList<Integer> ansVar1Numerators = new ArrayList<>(var1Numerators);
            ArrayList<Integer> ansVar2Numerators = new ArrayList<>(var2Numerators);
            ArrayList<Integer> ansVar1Denominators = new ArrayList<>(var1Denominators);
            ArrayList<Integer> ansVar2Denominators = new ArrayList<>(var2Denominators);
            ArrayList<Integer> ansVar1powers = new ArrayList<>(var1Powers);
            ArrayList<Integer> ansVar2powers = new ArrayList<>(var2Powers);
            int[] powers2equalNum = new int[2];
            int[] powers2equalDen = new int[2];
            int[] powers3equalNum = new int[3];
            int[] powers3equalDen = new int[3];
            int[] finalFraction = new int[2];

            if(sign.get(0)=="-"){
                ansVar1Numerators.set(1, (-1)*ansVar1Numerators.get(1));
                ansVar2Numerators.set(1, (-1)*ansVar2Numerators.get(1));
            }
            if(sign.get(1)=="-"){
                ansVar1Numerators.set(2, (-1)*ansVar1Numerators.get(2));
                ansVar2Numerators.set(2, (-1)*ansVar2Numerators.get(2));
            }

            if(ansVar1powers.get(0) == ansVar1powers.get(1) && ansVar1powers.get(1) == ansVar1powers.get(2)){
                for(int i = 0; i<3; i++) {
                    powers3equalNum[i] = ansVar1Numerators.get(i);
                    powers3equalDen[i] = ansVar1Denominators.get(i);
                }
                    finalFraction = equal(powers3equalNum, powers3equalDen);
                for(int i = 0; i < 3; i++){
                    ansVar1Numerators.removeLast();
                    ansVar1Denominators.removeLast();
                }
                ansVar1Numerators.add(finalFraction[0]);
                ansVar1Denominators.add(finalFraction[1]);
                ansVar1powers.remove(2);
                ansVar1powers.remove(1);
            } else if(ansVar1powers.get(0) == ansVar1powers.get(1)){
                for(int i = 0; i < 2; i++){
                    powers2equalNum[i] = ansVar1Numerators.get(i);
                    powers2equalDen[i] = ansVar1Denominators.get(i);
                }
                finalFraction = equal(powers2equalNum, powers2equalDen);
                for(int i = 0; i < 2; i++) {
                    ansVar1Numerators.removeFirst();
                    ansVar1Denominators.removeFirst();
                }
                ansVar1Numerators.add(0, finalFraction[0]);
                ansVar1Denominators.add(0, finalFraction[1]);
                ansVar1powers.remove(1);
            } else if(ansVar1powers.get(1) == ansVar1powers.get(2)){
                for(int i = 1; i < 3; i++){
                    powers2equalNum[i-1] = ansVar1Numerators.get(i);
                    powers2equalDen[i-1] = ansVar1Denominators.get(i);
                }
                finalFraction = equal(powers2equalNum, powers2equalDen);
                for(int i = 1; i < 3; i++){
                    ansVar1Numerators.removeLast();
                    ansVar1Denominators.removeLast();
                }
                ansVar1Numerators.add(1, finalFraction[0]);
                ansVar1Denominators.add(1, finalFraction[1]);
                ansVar1powers.remove(2);
            } else if(ansVar1powers.get(0) == ansVar1powers.get(2)){
                for(int i = 0, j = 0; i < 3 && j < 2; i+=2, j++){
                    powers2equalNum[j] = ansVar1Numerators.get(i);
                    powers2equalDen[j] = ansVar1Denominators.get(i);
                }
                finalFraction = equal(powers2equalNum, powers2equalDen);
                ansVar1Numerators.removeFirst();
                ansVar1Denominators.removeFirst();
                ansVar1Numerators.removeLast();
                ansVar1Denominators.removeLast();
                ansVar1Numerators.add(0, finalFraction[0]);
                ansVar1Denominators.add(0, finalFraction[1]);
                ansVar1powers.remove(2);
            }
            if(ansVar2powers.get(0) == ansVar2powers.get(1) && ansVar2powers.get(1) == ansVar2powers.get(2)){
                for(int i = 0; i<3; i++) {
                    powers3equalNum[i] = ansVar2Numerators.get(i);
                    powers3equalDen[i] = ansVar2Denominators.get(i);
                }
                finalFraction = equal(powers3equalNum, powers3equalDen);
                for(int i = 0; i < 3; i++){
                    ansVar2Numerators.removeLast();
                    ansVar2Denominators.removeLast();
                }
                ansVar2Numerators.add(finalFraction[0]);
                ansVar2Denominators.add(finalFraction[1]);
                ansVar2powers.remove(2);
                ansVar2powers.remove(1);
            } else if(ansVar2powers.get(0) == ansVar2powers.get(1)){
                for(int i = 0; i < 2; i++){
                    powers2equalNum[i] = ansVar2Numerators.get(i);
                    powers2equalDen[i] = ansVar2Denominators.get(i);
                }
                finalFraction = equal(powers2equalNum, powers2equalDen);
                for(int i = 0; i < 2; i++) {
                    ansVar2Numerators.removeFirst();
                    ansVar2Denominators.removeFirst();
                }
                ansVar2Numerators.add(0, finalFraction[0]);
                ansVar2Denominators.add(0, finalFraction[1]);
                ansVar2powers.remove(1);
            } else if(ansVar2powers.get(1) == ansVar2powers.get(2)){
                for(int i = 1; i < 3; i++){
                    powers2equalNum[i-1] = ansVar2Numerators.get(i);
                    powers2equalDen[i-1] = ansVar2Denominators.get(i);
                }
                finalFraction = equal(powers2equalNum, powers2equalDen);
                for(int i = 1; i < 3; i++){
                    ansVar2Numerators.removeLast();
                    ansVar2Denominators.removeLast();
                }
                ansVar2Numerators.add(1, finalFraction[0]);
                ansVar2Denominators.add(1, finalFraction[1]);
                ansVar2powers.remove(2);
            } else if(ansVar2powers.get(0) == ansVar2powers.get(2)){
                for(int i = 0, j = 0; i < 3 && j < 2; i+=2, j++){
                    powers2equalNum[j] = ansVar2Numerators.get(i);
                    powers2equalDen[j] = ansVar2Denominators.get(i);
                }
                finalFraction = equal(powers2equalNum, powers2equalDen);
                ansVar2Numerators.removeFirst();
                ansVar2Denominators.removeFirst();
                ansVar2Numerators.removeLast();
                ansVar2Denominators.removeLast();
                ansVar2Numerators.add(0, finalFraction[0]);
                ansVar2Denominators.add(0, finalFraction[1]);
                ansVar2powers.remove(2);
            }
            System.out.println("Numerator Var1: " + ansVar1Numerators);
            System.out.println("Numerator Var2: "+ansVar2Numerators);
            System.out.println("Denominator Var1: "+ansVar1Denominators);
            System.out.println("Denominator Var2: "+ansVar2Denominators);
            System.out.println("Power Var1: "+ansVar1powers);
            System.out.println("Power Var2: "+ansVar2powers);
            ArrayList<StringBuilder> correctTerms = new ArrayList<>();
//            StringBuilder test = new StringBuilder("0");
//            for(int i = 0; i < ((ansVar1powers.size())+ansVar2powers.size()); i++){
//                correctTerms.add(test);
//            }
//            for(int i=0, j = 0; i < ((ansVar1powers.size())+ansVar2powers.size()) && j < ansVar1Numerators.size(); i+=2, j++){
//                correctTerms.set(i, constructFraction( ansVar1Numerators.get(j), ansVar1Denominators.get(j), variables.get(alphaIndex.get(0)), ansVar1powers.get(j)));
//            }
//            System.out.println(correctTerms);
//            int j = 0;
//            for(int i = 0; i < ((ansVar1powers.size())+ansVar2powers.size()); i++){
//                if(correctTerms.get(i).equals(test)){
//                    correctTerms.set(i, constructFraction(ansVar2Numerators.get(j), ansVar2Denominators.get(j), variables.get(alphaIndex.get(1)), ansVar2powers.get(j)));
//                    j++;
//                }
//            }
            for(int i = 0; i < ansVar1powers.size(); i++){
                correctTerms.add(constructFraction( ansVar1Numerators.get(i), ansVar1Denominators.get(i), variables.get(alphaIndex.get(0)), ansVar1powers.get(i)));
            }
            for(int i = 0; i < ansVar2powers.size(); i++){
                correctTerms.add(constructFraction( ansVar2Numerators.get(i), ansVar2Denominators.get(i), variables.get(alphaIndex.get(1)), ansVar2powers.get(i)));
            }
            System.out.println(correctTerms);
            String correctAnswer = constructAnswer(correctTerms);
            System.out.println(correctAnswer);
            System.out.println("file created");

        }
    }

    static int[] equal(int[] numerator, int[] denominator) {
        int[] fraction = new int[2];
        int len = denominator.length;
        if(len == 3) {
            if (denominator[0] == denominator[1] && denominator[1] == denominator[2]) {
                fraction = equalAdd(Arrays.copyOf(numerator, numerator.length), Arrays.copyOf(denominator, denominator.length));
            } else {
                fraction = equate(Arrays.copyOf(numerator, numerator.length), Arrays.copyOf(denominator, denominator.length));
            }
        }
        if(len == 2){
            if (denominator[0] == denominator[1]) {
                fraction = equalAdd(Arrays.copyOf(numerator, numerator.length), Arrays.copyOf(denominator, denominator.length));
            } else {
                fraction = equate(Arrays.copyOf(numerator, numerator.length), Arrays.copyOf(denominator, denominator.length));
            }
        }
        return fraction;
    }

    static int[] equate(int[] numerator, int[] denominator) {
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

    static StringBuilder constructFraction(int numerator, int denominator, String variable, int power){
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

    static StringBuilder constructBino(String term1, String term2){
        StringBuilder binomial = new StringBuilder();
        binomial.append(term1);
        if(!term2.contains("-")){
            binomial.append("+");
        }
        binomial.append(term2);
        return binomial;
    }

    static String constructAnswer(ArrayList terms1){
        StringBuilder equation = new StringBuilder("$");
        equation.append(terms1.get(0));
        for(int i = 1; i < terms1.size(); i++){
            if(!terms1.get(i).toString().contains("-")){
                equation.append("+");
            }
            equation.append(terms1.get(i));
        }
        equation.append("$");
        return equation.toString();
    }
}
