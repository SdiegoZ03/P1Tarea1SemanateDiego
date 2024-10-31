package semanate.p1tarea1semanatediego;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.JSONObject;

public class P1Tarea1SemanateDiego {

    public static void main(String[] args) {
        P1Tarea1SemanateDiego mainProgram = new P1Tarea1SemanateDiego();
        mainProgram.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        char opcion;

        do {
            int nota1 = obtenerNota(1, scanner);
            int nota2 = obtenerNota(2, scanner);
            int nota3 = obtenerNota(3, scanner);

            float promedio = calcularPromedio(nota1, nota2, nota3);

            System.out.println("El promedio de las notas es: " + promedio);

            // Mostrar si el estudiante aprobó o reprobó según el promedio
            if (promedio >= 14) {
                System.out.println("El estudiante aprobó.");
            } else {
                System.out.println("El estudiante reprobó.");
            }

            guardarEnJson(nota1, nota2, nota3, promedio);
            guardarEnCsv(nota1, nota2, nota3, promedio);

            System.out.print("¿Desea ingresar otra vez las notas? (s/n): ");
            opcion = scanner.next().charAt(0);
        } while (opcion == 's' || opcion == 'S');
        
        scanner.close();
    }

    // Función para obtener una nota válida utilizando recursividad
    public int obtenerNota(int numeroNota, Scanner scanner) {
        System.out.print("Ingrese la nota " + numeroNota + " (entre 0 y 20): ");
        int nota = scanner.nextInt();

        if (nota < 0 || nota > 20) {
            System.out.println("Nota inválida. Debe estar entre 0 y 20.");
            return obtenerNota(numeroNota, scanner); // Llamada recursiva hasta que la nota sea válida
        }
        return nota;
    }

    // Función para calcular el promedio de las tres notas
    public float calcularPromedio(int nota1, int nota2, int nota3) {
        return (nota1 + nota2 + nota3) / 3.0f;
    }

    // Función para guardar las notas y el promedio en un archivo JSON
    public void guardarEnJson(int nota1, int nota2, int nota3, float promedio) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Nota 1", nota1);
        jsonObject.put("Nota 2", nota2);
        jsonObject.put("Nota 3", nota3);
        jsonObject.put("Promedio", promedio);
        jsonObject.put("Resultado", promedio >= 14 ? "Aprobado" : "Reprobado");

        try (FileWriter file = new FileWriter("resultados.json", true)) {
            file.write(jsonObject.toString() + "\n");
            System.out.println("Los resultados han sido guardados en 'resultados.json'");
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo JSON.");
            e.printStackTrace();
        }
    }

    // Función para guardar las notas y el promedio en un archivo CSV
    public void guardarEnCsv(int nota1, int nota2, int nota3, float promedio) {
        try (FileWriter file = new FileWriter("resultados.csv", true)) {
            file.write(nota1 + "," + nota2 + "," + nota3 + "," + promedio + "," + (promedio >= 14 ? "Aprobado" : "Reprobado") + "\n");
            System.out.println("Los resultados han sido guardados en 'resultados.csv'");
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo CSV.");
            e.printStackTrace();
        }
    }
}
