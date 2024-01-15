/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

/**
 *
 * @author Eduardo
 */
public class UmbralAutomatico {

    public static int metodoIterativo(double[] histograma) {
        int ui = calcularUmbralInicial(histograma);
        int uNuevo = 0;
        System.out.println(ui);
        do {
            uNuevo = ui;
            ui = reajustarUmbral(ui, histograma);
            System.out.println(ui);
        } while (uNuevo != ui); //Hace una actualización del umbral
        return ui;
    }

    private static int calcularUmbralInicial(double[] histograma) {
        int numPixels = 0;
        int suma = 0;
        //Se crea la suma o acumulación de las frecuencias
        for (int x = 0; x < histograma.length; x++) {
            numPixels += histograma[x];
            //Valor del histograma por tono (numero de pixeles por la frecuencias)
            suma += histograma[x] * x;
        }
        return (int) (suma / numPixels);
    }

    private static int reajustarUmbral(int ui, double[] histograma) {
        int u1, u2;
        int a1 = 0, a2 = 0, n1 = 0, n2 = 0;
        a1 += histograma[0];
        for (int x = 1; x < ui; x++) {
            a1 += histograma[x] * x;
            n1 += histograma[x];
        }

        for (int y = ui; y <= 255; y++) {
            a2 += histograma[y] * y;
            n2 += histograma[y];
        }
        if (n1 == 0 || n2 == 0) {
            return 0;
        }
        u1 = a1 / n1;
        u2 = a2 / n2;
        return (int) ((u1 + u2) / 2);
    }

    // por Pau
    public static int otsu(double[] histograma) {
        //La suma de los valores del histograma
        int total = 0;
        for (int i = 0; i < histograma.length; i++) {
            total += histograma[i];
        }
        //Valor maximo
        int top = 256;
        int sumaBB = 0;
        int wB = 0;
        //La maxima inferencia entre clases
        double maximo = 0.0;
        int wF;
        int mF;
        double valor;
        int umbral = 0;
        int suma1 = 0;
        int[] range = new int[top];
        for (int i = 0; i < top; i++) {
            range[i] = i;
        }
        //Calculamos el producto punto de los valores del histograma lo cual no 
        //da la probabilidad total de las clases
        for (int i = 0; i < histograma.length; i++) {
            suma1 += range[i] * histograma[i];
        }
        for (int i = 1; i < top; i++) {
            //Se calcula la probabilidad de que sea ForeGround, ya que son contrarias se 
            //calcula con la resta del total menos la otra probabilidad
            wF = total - wB;
            //si las dos probabilidades son mayores a 0
            if (wB > 0 && wF > 0) {
                //Se calcula la media
                mF = (suma1 - sumaBB) / wF;
                //Se calcula la varianza intra-clase
                valor = wB * wF * ((sumaBB / wB) - mF) * ((sumaBB / wB) - mF);
                //nuevo maximo y del umbral maximo
                if (valor >= maximo) {
                    umbral = i;
                    maximo = valor;
                }

            }
            //Si la probabilidas igual a 0 se cambia al siguiente umbral a verificar
            //Hciendo los calculos correspondientes
            wB = wB + (int) histograma[i];
            sumaBB = sumaBB + (i - 1) * (int) histograma[i];
        }
        return umbral;
    }

}
