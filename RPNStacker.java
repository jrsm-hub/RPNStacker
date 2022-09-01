import java.util.*;
import java.io.File;
import java.io.IOException;

class No {
  public int valor;
  public No abaixo;

  No(int caractere) {
    valor = caractere;
    abaixo = null;
  }
}

class Stack {
  public No topo;

  Stack() {
    topo = null;
  }

  public void push(No elemento) {
    No ultimo_topo = topo;
    topo = elemento;
    elemento.abaixo = ultimo_topo;
  }

  public int pop() {
    No novo_topo = topo.abaixo;
    No antigo_topo = topo;
    topo = novo_topo;
    return antigo_topo.valor;
  }
}

class Main {
  public static void main(String[] args) throws IOException {

    Stack stack = new Stack();
    String file = "entrada.txt";
    Scanner scanner = new Scanner(new File(file));
    scanner.useDelimiter("\n");

    while (scanner.hasNext()) {
      String entry = scanner.next();
      boolean isNumeric = entry.chars().allMatch(Character::isDigit);
      if (isNumeric) {
        No numero = new No(Integer.parseInt(entry));
        stack.push(numero);

      } else {
        if (entry.equals("+")) {
          int snd_oper = stack.pop();
          int fst_oper = stack.pop();
          int soma = fst_oper + snd_oper;
          No result = new No(soma);
          stack.push(result);

        } else if (entry.equals("*")) {
          int snd_oper = stack.pop();
          int fst_oper = stack.pop();
          int mult = fst_oper * snd_oper;
          No result = new No(mult);
          stack.push(result);

        } else if (entry.equals("-")) {
          int snd_oper = stack.pop();
          int fst_oper = stack.pop();
          int sub = fst_oper - snd_oper;
          No result = new No(sub);
          stack.push(result);

        } else {
          int snd_oper = stack.pop();
          int fst_oper = stack.pop();
          int div = fst_oper / snd_oper;
          No result = new No(div);
          stack.push(result);
        }
      }
    }
    int resultado = stack.pop();
    System.out.println(resultado);
    scanner.close();
  }
}