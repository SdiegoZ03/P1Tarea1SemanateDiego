#include <iostream>
#include <fstream>

using namespace std;

// Prototipos de funciones
int obtenerNota(int numeroNota);
float calcularPromedio(int nota1, int nota2, int nota3);
void guardarEnArchivo(int nota1, int nota2, int nota3, float promedio);

int main() {
    char opcion;
    do {
        int nota1 = obtenerNota(1);
        int nota2 = obtenerNota(2);
        int nota3 = obtenerNota(3);

        float promedio = calcularPromedio(nota1, nota2, nota3);

        cout << "El promedio de las notas es: " << promedio << endl;

        // Mostrar si el estudiante aprobó o reprobó según el promedio
        if (promedio >= 14) {
            cout << "El estudiante aprobó." << endl;
        } else {
            cout << "El estudiante reprobó." << endl;
        }

        guardarEnArchivo(nota1, nota2, nota3, promedio);

        cout << "¿Desea ingresar otra vez las notas? (s/n): ";
        cin >> opcion;
    } while (opcion == 's' || opcion == 'S');

    return 0;
}

// Función para obtener una nota valida utilizando recursividad
int obtenerNota(int numeroNota) {
    int nota;
    cout << "Ingrese la nota " << numeroNota << " (entre 0 y 20): ";
    cin >> nota;

    // Validar si la nota está en el rango permitido
    if (nota < 0 || nota > 20) {
        cout << "Nota inválida. Debe estar entre 0 y 20." << endl;
        return obtenerNota(numeroNota); // Llamada recursiva hasta que la nota sea válida
    }

    return nota; // Retorna la nota una vez validada
}

// Función para calcular el promedio de las tres notas
float calcularPromedio(int nota1, int nota2, int nota3) {
    return (nota1 + nota2 + nota3) / 3.0;
}

// Función para guardar las notas y el promedio en un archivo de texto
void guardarEnArchivo(int nota1, int nota2, int nota3, float promedio) {
    ofstream archivo("resultados.txt", ios::app);

    if (archivo.is_open()) {
        archivo << "Nota 1: " << nota1 << endl;
        archivo << "Nota 2: " << nota2 << endl;
        archivo << "Nota 3: " << nota3 << endl;
        archivo << "Promedio: " << promedio << endl;

        if (promedio >= 14) {
            archivo << "Resultado: Aprobado" << endl;
        } else {
            archivo << "Resultado: Reprobado" << endl;
        }

        archivo << "--------------------------" << endl;
        archivo.close();

        cout << "Los resultados han sido guardados en 'resultados.txt'" << endl;
    } else {
        cout << "Error al abrir el archivo." << endl;
    }
}
