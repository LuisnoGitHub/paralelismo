package org.example.calculator;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class AloImpl implements AloInt {

    public static void main(String args[]) {
        try {
            AloImpl alo = new AloImpl();

            // Exporta o objeto remoto numa porta especificada (por exemplo, 5000)
            int port = 5000; // Porta especificada
            AloInt aloStub = (AloInt) UnicastRemoteObject.exportObject(alo, port);

            // Cria o registro RMI na porta especificada (por exemplo, 1098)
            Registry registro = LocateRegistry.createRegistry(1098);

            // Registra o objeto remoto com o nome "Alo"
            registro.rebind("Alo", aloStub);

            System.out.println("Servidor RMI pronto na porta " + port);
        } catch (Exception e) {
            System.out.println("! Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public double Somar(double a, double b) throws RemoteException {
        return a + b;
    }

    @Override
    public double Subtrair(double a, double b) throws RemoteException {
        return a - b;
    }

    @Override
    public double Dividir(double a, double b) throws RemoteException {
        return a / b;
    }

    @Override
    public double Multiplicar(double a, double b) throws RemoteException {
        return a * b;
    }
}
