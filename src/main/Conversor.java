package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Conversor {

    private static int obtemAlgarismo(char ch) {
        ch = Character.toUpperCase(ch);
        switch (ch) {
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
            default:
                return Integer.parseInt(String.valueOf(ch));
        }
    }

    private static char configuraAlgarismo(int dig) {
        switch (dig) {
            case 10:
                return 'A';
            case 11:
                return 'B';
            case 12:
                return 'C';
            case 13:
                return 'D';
            case 14:
                return 'E';
            case 15:
                return 'F';
            default:
                return String.valueOf(dig).charAt(0);
        }
    }

    public static String baseOrigemParaBaseDec(String numero, int baseOrigem, int baseAlvo) {
        
        String[] num = numero.split("[.]"); // separação das partes inteiras e fracionárias do número

        int tam = num[0].length() - 1; // num[0]: parte inteira - sempre haverá, ainda que seja 0.
        long inteira = 0;
        for (int i = 0; i <= tam; i++) {
            int x = obtemAlgarismo(num[0].charAt(tam - i));
            if (x >= baseOrigem) {
                throw new IllegalArgumentException(String.format("%s é um valor inválido na base %d", numero, baseOrigem));
            }
            inteira += x * Math.pow(baseOrigem, i);
        }

        if (num.length == 1) { // número é inteiro, isto é, não possui parte decimal
            return baseDecParaBaseAlvo(String.format("%d", inteira), baseAlvo);
        }

        tam = num[1].length() - 1; // num[1]: parte fracionária
        double fracionario = 0.0;
        for (int i = 0; i <= tam; i++) {
            int x = obtemAlgarismo(num[1].charAt(i));
            if (x >= baseOrigem) {
                throw new IllegalArgumentException(String.format("%s é um valor inválido na base %d", numero, baseOrigem));
            }
            fracionario += x * Math.pow(baseOrigem, -(i + 1));
        }
        Locale.setDefault(Locale.US);
        return baseDecParaBaseAlvo(String.format("%f", inteira + fracionario), baseAlvo);
    }

    private static String baseDecParaBaseAlvo(String numero, int baseAlvo) {
        
        double valor = Double.parseDouble(numero);

        List<Character> list = new ArrayList<>(); // lista para armazenar os algarismo correspondentes a cada base

        long inteira = (int) valor; // parte inteira do número

        /* Transformação da parte inteira */
        while (inteira / baseAlvo != 0) {
            int dig = (int) inteira % baseAlvo;
            list.add(0, configuraAlgarismo(dig));
            inteira /= baseAlvo;
        }
        list.add(0, configuraAlgarismo((int) inteira));

        /* Verificando se há parte fracionária */
        double fracionaria = valor - (int) valor;
        if (fracionaria == 0) { // não há parte fracionário, o número é inteiro
            return formata(list);
        }
        
        /* Transformação da parte decimal*/
        list.add(list.size(), '.');
        int i = 0;
        while ((fracionaria != 0) && (i < 23)) {
            int dig = (int) (fracionaria * baseAlvo);
            list.add(list.size(), configuraAlgarismo(dig));
            fracionaria = (fracionaria * baseAlvo) - dig;
            i++;
        }
        return formata(list);
    }

    private static String formata(List<Character> list) {
        StringBuilder builder = new StringBuilder();
        list.forEach((a) -> {
            builder.append(a);
        });
        return builder.toString().replace(".", ",");
    }
}
