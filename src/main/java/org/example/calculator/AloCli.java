package org.example.calculator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AloCli {
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        int port = 1098; // A mesma porta configurada no servidor RMI
        try {
            // Obtém o registro do servidor na porta correta
            Registry registry = LocateRegistry.getRegistry(host, port);

            // Faz o lookup do objeto remoto "Alo"
            AloInt stub = (AloInt) registry.lookup("Alo");

            // Chama o método remoto
            double resposta = stub.Somar(3,4);
            System.out.printf("%s,%s,%s, %s", stub.Somar(3,4),
                    stub.Subtrair(3,4),
                    stub.Dividir(3,4),
                    stub.Multiplicar(3,4));

        } catch (Exception e) {
            System.out.println("Erro: " + e.toString());
            e.printStackTrace();
        }
    }
}
