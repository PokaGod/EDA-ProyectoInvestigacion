/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mauricio
 */
// Java program for Fibonacci Search
import java.util.*;

public class Fibonacci {
	// Utility function to find minimum
	// of two elements
	public static int min(int x, int y)
	{
		return (x <= y) ? x : y;
	}

	/* Returns index of x if present, else returns -1 */
	public static int fibMonaccianSearch(int arr[], int x, int n)
	{
		/* Initialize fibonacci numbers */
		int fibMMm2 = 0; // (m-2)'th Fibonacci No.
		int fibMMm1 = 1; // (m-1)'th Fibonacci No.
		int fibM = fibMMm2 + fibMMm1; // m'th Fibonacci

		/* fibM is going to store the smallest
		Fibonacci Number greater than or equal to n */
		while (fibM < n) {
			fibMMm2 = fibMMm1;
			fibMMm1 = fibM;
			fibM = fibMMm2 + fibMMm1;
		}

		// Marks the eliminated range from front
		int offset = -1;

		/* while there are elements to be inspected.
		Note that we compare arr[fibMm2] with x.
		When fibM becomes 1, fibMm2 becomes 0 */
		while (fibM > 1) {
			// Check if fibMm2 is a valid location
			int i = min(offset + fibMMm2, n - 1);

			/* If x is greater than the value at
			index fibMm2, cut the subarray array
			from offset to i */
			if (arr[i] < x) {
                            fibM = fibMMm1;
                            fibMMm1 = fibMMm2;
                            fibMMm2 = fibM - fibMMm1;
                            offset = i;
			}

			/* If x is less than the value at index
			fibMm2, cut the subarray after i+1 */
			else if (arr[i] > x) {
                            fibM = fibMMm2;
                            fibMMm1 = fibMMm1 - fibMMm2;
                            fibMMm2 = fibM - fibMMm1;
			}

			/* element found. return index */
			else{
                            return i;
                        }
		}

		/* comparing the last element with x */
		if (fibMMm1 == 1 && arr[n-1] == x){
			return n-1;
                }

		/*element not found. return -1 */
		return -1;
	}

	// driver code
	public static void main(String[] args)
	{       
                
                long inicio = System.nanoTime();//Inicio del medidor de tiempo
		int arr[] = new int [9999];//Definir arreglo
                Random ran = new Random();//Crear clase Randon
                for (int i = 0; i < arr.length; i++) { //Llenar valores aleatorios
                arr[i]= ran.nextInt((9999-1)+1)+1;
                }
                Arrays.sort(arr); //Ordenar el arreglo
		int n = arr.length; 
                int alt= ran.nextInt(((arr.length-1)-0)+1); //Generar posicion
		int x = arr[alt]; 
                int ind = fibMonaccianSearch(arr, x, n);
                //Escenarios posibles 
		if(ind>=0){
		System.out.println("Encontrado en la posición: "+ind);
                }
                else{
		System.out.println(x+" no esta presente en el arreglo");
                }
                long fin = System.nanoTime(); //Operaciones para medir el tiempo
                long tTotal = fin - inicio;
                System.out.println("Tiempo total de ejecución: "+tTotal+" nanosegundos");	
        }
    }




