package com.exercicio.notacao_pos_fixa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Calculadora {
     // Verifica se e um operador
    boolean operador(String op) {
        return op.equals("+") || op.equals("-")
            || op.equals("*") || op.equals("/");
    }

    // Define a prioridade dos operadores
    int prioridade(String op) {
        if (op.equals("*") || op.equals("/")) {
            return 2;
        }
        return 1;
    }

    // Converte infixa para pos-fixa
    List<String> infixaParaPos(String[] exp) {
        List<String> resultado = new ArrayList<>();
        Stack<String> pilha = new Stack<>();

        for (String op : exp) {
            if (!operador(op) && !op.equals("(") && !op.equals(")")) {
                resultado.add(op);
            } else if (op.equals("(")) {
                pilha.push(op);
            } else if (op.equals(")")) {
                while (!pilha.peek().equals("(")) {
                    resultado.add(pilha.pop());
                }
                pilha.pop();
            } else {
                while (!pilha.isEmpty()
                    && !pilha.peek().equals("(")
                    && prioridade(pilha.peek()) >= prioridade(op)) {
                    resultado.add(pilha.pop());
                }
                pilha.push(op);
            }
        }

        while (!pilha.isEmpty()) {
            resultado.add(pilha.pop());
        }

        return resultado;
    }

    // Converte pre-fixa para pos-fixa
    List<String> preParaPos(String[] exp) {
        Stack<List<String>> pilha = new Stack<>();

        for (int i = exp.length - 1; i >= 0; i--) {
            String op = exp[i];

            if (!operador(op)) {
                pilha.push(new ArrayList<>(Arrays.asList(op)));
            } else {
                List<String> a = pilha.pop();
                List<String> b = pilha.pop();

                a.addAll(b);
                a.add(op);
                pilha.push(a);
            }
        }

        return pilha.pop();
    }

    // Converte pos-fixa para pre-fixa
    List<String> posParaPre(List<String> exp) {
        Stack<String> pilha = new Stack<>();

        for (String op : exp) {
            if (!operador(op)) {
                pilha.push(op);
            } else {
                String b = pilha.pop();
                String a = pilha.pop();
                pilha.push(op + " " + a + " " + b);
            }
        }

        return Arrays.asList(pilha.pop().split(" "));
    }

    // Converte pos-fixa para infixa
    String posParaIn(List<String> exp) {
        Stack<String> pilha = new Stack<>();

        for (String op : exp) {
            if (!operador(op)) {
                pilha.push(op);
            } else {
                String b = pilha.pop();
                String a = pilha.pop();
                pilha.push("(" + a + " " + op + " " + b + ")");
            }
        }

        return pilha.pop();
    }

    // Calcula o resultado usando a pos-fixa
    double calcular(List<String> exp) {
        Stack<Double> pilha = new Stack<>();

        for (String op : exp) {
            if (!operador(op)) {
                pilha.push(Double.parseDouble(op));
            } else {
                double b = pilha.pop();
                double a = pilha.pop();

                switch (op) {
                    case "+":
                        pilha.push(a + b);
                        break;
                    case "-":
                        pilha.push(a - b);
                        break;
                    case "*":
                        pilha.push(a * b);
                        break;
                    case "/":
                        if (b == 0) {
                            System.out.println("Divisao por zero");
                        }
                        pilha.push(a / b);
                        break;
                }
            }
        }

        return pilha.pop();
}}
