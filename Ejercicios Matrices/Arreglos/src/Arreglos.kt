import java.util.*

val scanner = Scanner(System.`in`)

fun main(){
    println("Elije un ejercicio del 1 -20 :")
    val opcion = readLine()?.toIntOrNull()

    when(opcion){
        1 -> eje1()
        2 -> eje2()
        3 -> eje3()
        4 -> eje4()
        5 -> eje5()
        6 -> eje6()
        /*

7 -> eje7()
8 -> eje8()
9 -> eje9()
10 -> eje10()
11 -> eje11()
12 -> eje12()
13 -> eje13()
14 -> eje14()
15 -> eje15()
16 -> eje16()
17 -> eje17()
18 -> eje18()
19 -> eje19()
20 -> eje20()

 */
        else -> println("Opción no válida")
    }
}

fun eje1(){

    println("Ingrese el número de filas (M):")
    val m = scanner.nextInt()
    println("Ingrese el número de columnas (N):")
    val n = scanner.nextInt()

    println("Ingrese el número (K) para multiplicar los elementos de la matriz:")
    val k = scanner.nextInt()

    val random = Random()
    val matriz = Array(m) { IntArray(n) { random.nextInt(10) } }

    println("Matriz original:")
    for(i in 0 until m){
        for(j in 0 until n){
            print("${matriz[i][j]} ")
        }
        println()
    }

    println("Matriz multiplicada por $k:")
    for(i in 0 until m){
        for(j in 0 until n){
            print("${matriz[i][j] * k} ")
        }
        println()
    }
}

fun eje2(){

    println("Ingrese el tamaño de la matriz de orden P")
    val m = scanner.nextInt()


    val random = Random()
    val matriz = Array(m) { IntArray(m) { random.nextInt(20) } }

    println("Matriz original:")
    for(i in 0 until m){
        for(j in 0 until m){
            print(String.format("%02d",matriz[i][j])+ " ")
        }
        println()
    }

    println("Diagonal principal:")
    for(i in 0 until m){
        print(String.format("%02d",matriz[i][i])+ " ")
    }

    println("\nDiagonal secundaria:")
    for(i in 0 until m){
        print(String.format("%02d",matriz[i][m-i-1])+ " ")
    }

    println("\nMatriz triangular superior:")
    for(i in 0 until m){
        for(j in 0 until m){
            if(j >= i){
                print(String.format("%02d",matriz[i][j])+ " ")
            }else{
                print("   ")
            }
        }
        println()
    }


    println("Matriz triangular inferior:")
    for(i in 0 until m){
        for(j in 0 until m){
            if(j <= i){
                print(String.format("%02d",matriz[i][j])+ " ")
            }else{
                print("   ")
            }
        }
        println()
    }
}

fun eje3(){

    println("Ingrese el número de filas (M):")
    val m = scanner.nextInt()
    println("Ingrese el número de columnas (N):")
    val n = scanner.nextInt()

    val random = Random()
    val matriz = Array(m) { IntArray(n) { random.nextInt(10) } }

    println("Matriz original:")
    for(i in 0 until m){
        for(j in 0 until n){
            print("${matriz[i][j]} ")
        }
        println()
    }

    println("Matriz transpuesta:")
    for(i in 0 until n){
        for(j in 0 until m){
            print("${matriz[j][i]} ")
        }
        println()
    }
}

fun eje4() {

    println("Ingrese el número de filas (M):")
    val m = scanner.nextInt()
    println("Ingrese el número de columnas (N):")
    val n = scanner.nextInt()

    if(m<=0 || n<=0){
        println("El número de filas y columnas debe ser mayor a 0")
        return
    }

    val random = Random()
    val matriz = Array(m) { IntArray(n) { random.nextInt(10) } }

    if(m == n) {
        println("La matriz es cuadrada:")
    }else{
        println("la Matriz no es cuadrada:")
    }

    val sumaTotal = matriz.sumOf { fila -> fila.sum() }
    val promedio = sumaTotal.toDouble() / (m * n)
        println("\nEl promedio general es $promedio")

    println("\nPromedio por fila")
    for (i in 0 until m) {
        val sumaFila = matriz[i].sum()
        val promedioFila = sumaFila.toDouble() / n
        println("Fila $i: $promedioFila")
    }

    println("\nPromedio por columna")
    for (j in 0 until n) {
        var sumaColumna = 0
        for (i in 0 until m) {
            sumaColumna += matriz[i][j]
        }
        val promedioColumna = sumaColumna.toDouble() / m
        println("Columna $j: $promedioColumna")
    }
}

fun eje5() {

    val matriz = arrayOf(
        intArrayOf(1, 0, 0),
        intArrayOf(0, 1, 0),
        intArrayOf(0, 0, 1)
    )

    val filas = matriz.size
    val columnas = matriz[0].size


    println("Matriz original:")
    for(i in 0 until filas){
        for(j in 0 until columnas){
            print(String.format("%02d",matriz[i][j])+ " ")
        }
        println()
    }

    // Verificar por filas
    for (i in 0 until filas) {
        var contadorFila = 0
        for (j in 0 until columnas) {
            if (matriz[i][j] == 1) {
                contadorFila++
            }
        }
        if (contadorFila != 1) {
            println("La matriz no es rala.")
            return
        }
    }

    // Verificar por columnas
    for (j in 0 until columnas) {
        var contadorColumna = 0
        for (i in 0 until filas) {
            if (matriz[i][j] == 1) {
                contadorColumna++
            }
        }
        if (contadorColumna != 1) {
            println("La matriz no es rala.")
            return
        }
    }

    println("La matriz es rala.")
}

fun eje6(){

    println("Ingrese el número de filas (n):")
    val n = scanner.nextInt()
    println("Ingrese el número de columnas (m):")
    val m = scanner.nextInt()

    val random = Random()
    val matriz1 = Array(m) { IntArray(n) { random.nextInt(10) } }
    val matriz2 = Array(m) { IntArray(n) { random.nextInt(10) } }

    println("Matriz 1:")
    for(i in 0 until n){
        for(j in 0 until m){
            print("${matriz1[i][j]} ")
        }
        println()
    }

    println("Matriz 2:")
    for(i in 0 until n){
        for(j in 0 until m){
            print("${matriz2[i][j]} ")
        }
        println()
    }

    println("El resultado de la suma de las 2 matrices es:")
    for (i in 0 until n) {
        for (j in 0 until m) {
            print("${matriz1[i][j] + matriz2[i][j]} ")
        }
        println()
    }

    println("El resultado de la multiplicación de las 2 matrices es:")
    for (i in 0 until n) {
        for (j in 0 until m) {
            print("${matriz1[i][j] * matriz2[i][j]} ")
        }
        println()
    }
}


