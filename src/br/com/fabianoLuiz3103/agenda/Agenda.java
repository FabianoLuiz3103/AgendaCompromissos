package br.com.fabianoLuiz3103.agenda;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author FabianoLuiz3103
 * --> Matriz que simula uma agenda de compromissos
 * --> [dia][hora] = tarefa
 */
public class Agenda {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[][] agenda = new String[30][24];
        int opcao;

        do{

            opcao = receberInteiro(scanner, "\n--------------------------------------" +
                    "\nDIGITE:" +
                    "\n(1) - PARA INSERIR COMPROMISSOS NA AGENDA; " +
                    "\n(2) - PARA CONSULTAR UM COMPROMISSO NA AGENDA; " +
                    "\n(3) - PARA CONSULTAR TODOS OS COMPROMISSOS DA AGENDA." +
                    "\n(4) - PARA SAIR \n", false, true);

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
                            if (agenda[i][j] != null) {
                                linha.append("DIA: " + i + " - HORA: " + j +" COMPROMISSO: " +agenda[i][j] + ", ");
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

    private static void inserindoOUconsultarCompromisso(Scanner scanner, String[][] agenda, boolean isInserindo){

        int opcao, dia, hora;
        boolean temCompromisso=false;
        do{
            opcao = receberInteiro(scanner, "\n--------------------------------------" +
                    "\nDESEJA " + ((isInserindo) ? "INSERIR " : "CONSULTAR ") +  "UM COMPROMISSO?:" +
                    "\n(1) - SIM; " +
                    "\n(0) - NÃO; \n" , false, true);
            switch (opcao){
                case 1:{

                    do{
                        dia = receberInteiro(scanner, "\n\tInforme o dia que você deseja " + ((isInserindo) ? "INSERIR " : "CONSULTAR ") +" um compromisso: ", true, false);
                        hora = receberInteiro(scanner, "\n\tInforme a hora do dia " + dia + " que você deseja " + ((isInserindo) ? "INSERIR " : "CONSULTAR ") + " um compromisso: ", false, false);
                        scanner.nextLine();
                        if(isInserindo){
                            if(agenda[dia][hora]!=null){
                                temCompromisso=true;
                                System.out.println("\n\tERRO! O dia: " + dia + " às: " + hora + " horas já tem compromisso marcado! ");
                            }else{
                                temCompromisso=false;
                                System.out.print("\n\tInforme o compromisso do dia " + dia + " às " + hora + " horas: ");
                                agenda[dia][hora] = scanner.nextLine();
                            }
                        }else{
                            System.out.print("\n\t\t"+((agenda[dia][hora]==null)?" SEM COMPROMISSO PARA O DIA " + dia + " ÀS " + hora + " HORAS":
                                    "COMPROMISSO: " + agenda[dia][hora]));
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

    private static int receberInteiro(Scanner scanner, String texto, boolean isDia, boolean isOpcao){
        int valor;
        while (true){
            try{
                System.out.print(texto);
                valor = scanner.nextInt();
                if(isDia && !isOpcao){
                    if(valor > 0 && valor <= 30){break;}
                }else if(!isDia && !isOpcao){
                    if(valor > 0 && valor <= 24){break;}
                }else{
                    break;
                }
                System.out.print("\n\t " + ((isDia)?"ERRO! O valor deve ser maior que 0 e menor ou igual a 30! ":
                        "ERRO! O valor deve ser maior que zero e menor ou igual a 25!"));
            }catch (InputMismatchException e){
                System.out.println("\n\tERRO! O valor informado deve ser um número inteiro! ");
                scanner.nextLine();
            }
        }
        return valor;
    }
}
