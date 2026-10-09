package com.exercicio.notacao_pos_fixa;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);
        Calculadora calculadora = new Calculadora();

        System.out.println("1 - Infixa");
        System.out.println("2 - Pos-fixa");
        System.out.println("3 - Pre-fixa");
        System.out.print("Escolha: ");

        int tipo = Integer.parseInt(leia.nextLine());

        System.out.print("Expressao: ");
        String[] exp = leia.nextLine().trim().split("\\s+");

        List<String> pos;

        switch (tipo) {
            case 1:
                pos = calculadora.infixaParaPos(exp);
                break;
            case 2:
                pos = Arrays.asList(exp);
                break;
            case 3:
                pos = calculadora.preParaPos(exp);
                break;
            default:
                System.out.println("Opcao invalida!");
                leia.close();
                return;
        }

        System.out.println("Infixa: " + calculadora.posParaIn(pos));
        System.out.println("Pos-fixa: " + String.join(" ", pos));
        System.out.println("Pre-fixa: " + String.join(" ", calculadora.posParaPre(pos)));
        System.out.println("Resultado: " + calculadora.calcular(pos));

        leia.close();
    }
}