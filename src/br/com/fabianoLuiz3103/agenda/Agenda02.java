package br.com.fabianoLuiz3103.agenda;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author FabianoLuiz3103
 * --> Matriz que simula uma agenda de compromissos
 * --> [mes][dia][hora] = tarefa
 */
public class Agenda02 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[][][] agenda = new String[12][31][8];
        int opcao;

        do{

            opcao = receberInteiro(scanner, "\n--------------------------------------" +
                    "\nDIGITE:" +
                    "\n(1) - PARA INSERIR COMPROMISSOS NA AGENDA; " +
                    "\n(2) - PARA CONSULTAR UM COMPROMISSO NA AGENDA; " +
                    "\n(3) - PARA CONSULTAR TODOS OS COMPROMISSOS DA AGENDA." +
                    "\n(4) - PARA SAIR \n", "opcao");

            switch (opcao){

                case 1: {
                    inserindoOUconsultarCompromisso(scanner, agenda, true);
                }
                break;
                case 2:{
                    inserindoOUconsultarCompromisso(scanner, agenda, false);
                }
                break;

                case 3:{
                    for (int i = 0; i < agenda.length; i++) {
                        StringBuilder linha = new StringBuilder();
                        for (int j = 0; j < agenda[i].length; j++) {
                            for(int k = 0; k < agenda[i][j].length; k++){
                                if (agenda[i][j][k] != null) {
                                    linha.append("DIA: ").append(j+1).append("/").append(i+1).append(" - HORA: ").append(k+1).append(" COMPROMISSO: ").append(agenda[i][j][k]).append(", ");
                                }
                            }
                        }
                        if (!linha.isEmpty()) {
                            System.out.println(linha);
                        }
                    }
                }
                break;
                case 4: {
                    System.out.println("\n\tENCERRANDO...");
                }
                break;
                default:{
                    System.out.println("\n\tOpção inválida!...");
                }
            }

        }while (opcao != 4);

        scanner.close();
    }

    private static void inserindoOUconsultarCompromisso(Scanner scanner, String[][][] agenda, boolean isInserindo){

        int opcao, mes, dia, hora;
        boolean temCompromisso=false;
        do{
            opcao = receberInteiro(scanner, "\n--------------------------------------" +
                    "\nDESEJA " + ((isInserindo) ? "INSERIR " : "CONSULTAR ") +  "UM COMPROMISSO?:" +
                    "\n(1) - SIM; " +
                    "\n(0) - NÃO; \n" , "opcao");
            switch (opcao){
                case 1:{

                    do{
                        mes = receberInteiro(scanner, "\n\tInforme o mês que você deseja " + ((isInserindo) ? "INSERIR " : "CONSULTAR ") +" um compromisso: ", "mes");
                        dia = receberInteiro(scanner, "\n\tInforme o dia do mes " + mes + " que você deseja " + ((isInserindo) ? "INSERIR " : "CONSULTAR ") +" um compromisso: ", "dia");
                        hora = receberInteiro(scanner, "\n\tInforme a hora do dia " + dia + " que você deseja " + ((isInserindo) ? "INSERIR " : "CONSULTAR ") + " um compromisso: ", "hora");
                        scanner.nextLine();
                        mes--;
                        dia--;
                        hora--;
                        if(isInserindo){
                            if(agenda[mes][dia][hora]!=null){
                                temCompromisso=true;
                                System.out.println("\n\tERRO! O dia: " + (dia+1) + " às: " + (hora+1) + " horas já tem compromisso marcado! ");
                            }else{
                                temCompromisso=false;
                                System.out.print("\n\tInforme o compromisso do dia " + (dia+1)+ "/" + (mes+1) + " às " + (hora+1) + " horas: ");
                                agenda[mes][dia][hora] = scanner.nextLine();
                            }
                        }else{
                            System.out.print("\n\t\t"+((agenda[mes][dia][hora]==null)?" SEM COMPROMISSO PARA O DIA " + (dia+1) + " ÀS " +(hora+1) + " HORAS":
                                    "COMPROMISSO: " + agenda[mes][dia][hora]));
                        }
                    }while (temCompromisso);

                }
                break;
                case 0:{
                    System.out.println("\n\tAté logo!");
                }
                break;
                default: {
                    System.out.println("\n\tOpção inválida!");
                }
            }
        }while (opcao != 0);


    }

    private static int receberInteiro(Scanner scanner, String texto, String tipo){
        int valor;
        while (true){
            try{
                System.out.print(texto);
                valor = scanner.nextInt();
                if(tipo.equals("mes") && valor > 0 && valor <= 12 ||
                        tipo.equals("dia") && valor > 0 && valor <= 30 ||
                        tipo.equals("hora") && valor > 0 && valor <= 8 ||
                tipo.equals("opcao")){break;}

                System.out.print("\n\t " +
                        (tipo.equals("mes") ? "ERRO! O valor deve ser maior que 0 e menor ou igual a 12!" :
                                (tipo.equals("dia") ? "ERRO! O valor deve ser maior que zero e menor ou igual a 30!" :
                                        (tipo.equals("hora") ? "ERRO! O valor deve ser maior que 0 e menor ou igual a 8!" : ""))));

            }catch (InputMismatchException e){
                System.out.println("\n\tERRO! O valor informado deve ser um número inteiro! ");
                scanner.nextLine();
            }
        }
        return valor;
    }
}
