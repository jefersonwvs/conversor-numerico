package main;

import java.util.ArrayList;
import java.util.List;

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
    
    public static String transforma(String numero, int baseOrigem, int baseAlvo) {
        long res = 0;
        int tam = numero.length() - 1;
        for (int i = 0; i <= tam; i++) {
            int x = obtemAlgarismo(numero.charAt(tam - i)); 
            if (x >= baseOrigem)
                throw new IllegalArgumentException(String.format("%s é um valor inválido na base %d", numero, baseOrigem));
            res += x * (int)Math.pow(baseOrigem, i);
        }
        return baseDecParaBaseAlvo(String.valueOf(res), baseAlvo);
        
    }
    
    private static String baseDecParaBaseAlvo(String numero, int baseAlvo) {
        List<Character> list = new ArrayList<>();
        long valor = Integer.valueOf(numero);
        while (valor / baseAlvo != 0) {
            int dig = (int)valor % baseAlvo;
            list.add(0, configuraAlgarismo(dig));
            valor /= baseAlvo;
        }
        list.add(0, configuraAlgarismo((int)valor));
        
        return formata(list);
    }

    private static String formata(List<Character> list) {
        StringBuilder builder = new StringBuilder();
        list.forEach((a) -> { builder.append(a); });
        return builder.toString();
    }
}
