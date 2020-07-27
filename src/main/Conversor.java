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
    
    public static String transforma(String numero, int baseOrigem, int baseAlvo) 
    {        
        String[] num = numero.split("[.]");
        
        int tam = num[0].length() - 1; // num[0]: parte inteira
        long inteira = 0;
        for (int i = 0; i <= tam; i++) {
            int x = obtemAlgarismo(num[0].charAt(tam - i));
            if (x >= baseOrigem)
                throw new IllegalArgumentException(String.format("%s é um valor inválido na base %d", numero, baseOrigem));
            inteira += x * Math.pow(baseOrigem, i);
        }
        
        tam = num[1].length() - 1; // num[1]: parte fracionária
        double fracionario = 0.0;
        for (int i = 0; i <= tam; i++) {
            int x = obtemAlgarismo(num[1].charAt(i));
            if (x >= baseOrigem)
                throw new IllegalArgumentException(String.format("%s é um valor inválido na base %d", numero, baseOrigem));
            fracionario += x * Math.pow(baseOrigem, -(i+1));
        }
        Locale.setDefault(Locale.US);
        return baseDecParaBaseAlvo(String.format("%f", inteira + fracionario), baseAlvo);
    }
    
    private static String baseDecParaBaseAlvo(String numero, int baseAlvo) {
        double valor = Double.parseDouble(numero);
        System.out.println(valor);
        
        List<Character> list = new ArrayList<>();
        
        long inteira = (int)valor;
        System.out.println(inteira);
        
        while (inteira / baseAlvo != 0) {
            int dig = (int)inteira % baseAlvo;
            list.add(0, configuraAlgarismo(dig));
            inteira /= baseAlvo;
        }
        list.add(0, configuraAlgarismo((int)inteira));
        
        list.add(list.size(), '.');
        
        double fracionaria = valor - (int)valor;
        System.out.println(fracionaria);
        int i = 0;
        while ((fracionaria != 0) && (i < 23)) {
            int dig = (int)(fracionaria * baseAlvo);
            System.out.println(dig);
            list.add(list.size(), configuraAlgarismo(dig));
            fracionaria = (fracionaria * baseAlvo) - dig;
            i++;
        }        
        return formata(list);
    }

    private static String formata(List<Character> list) {
        System.out.println("cheguei");
        StringBuilder builder = new StringBuilder();
        list.forEach((a) -> { builder.append(a); });
        return builder.toString();
    }
}
