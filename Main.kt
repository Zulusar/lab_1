//@file:Suppress("UNUSED_EXPRESSION")

import java.io.PrintStream


fun main() {
    val outputConsole: PrintStream =
        PrintStream(System.out, true, "UTF-8")
    println(
        """Приветствую в игре "Крестики-Нолики"!
        |Перед Вами находится поле 3х3. Правила игры, думаю, Вы знаете.""".trimMargin()
    )//приветствие игрока
    println("По правилам крестиком ходить первым, так что вперед, дерзайте!")


    val Field: Array<Array<Char>> = arrayOf(arrayOf(' ', ' ', ' '), arrayOf(' ', ' ', ' '), arrayOf(' ', ' ', ' '))
    var count = 0
    var iter = 0
    var result = 1
    val boardSize = 3
    var Coordinates: String

    fun printBoard(board: Array<Array<Char>>) {
        for (row in board) println(row.contentToString())
    }

    fun isFill(board: Array<Array<Char>>): Boolean {
        var a = 9
        for (row in board) {
            for (cell in row) {
                if (cell != ' ') a--
            }
        }
        println("Мест на достке $a")

        return if (a == 0) false
        else true
    }

    fun pointFromString(string: String): Array<Int> {
        val Coor = string.split(" ").map(String::toInt)
        val Coor1 = arrayOf(Coor[0], Coor[1])
        return Coor1
    }

    fun isRightMove(board: Array<Array<Char>>, point: Array<Int>): Boolean {
        var i = 0
        if (point[0] >= boardSize || point[1] >= boardSize || board[point[0]][point[1]] != ' ') i++
        else i = 0
        return if (i == 0) true
        else false
    }

    fun checkWin(board: Array<Array<Char>>): Char {
        val winLines = arrayOf(
            arrayOf(arrayOf(0, 0), arrayOf(0, 1), arrayOf(0, 2)),
            arrayOf(arrayOf(1, 0), arrayOf(1, 1), arrayOf(1, 2)),
            arrayOf(arrayOf(2, 0), arrayOf(2, 1), arrayOf(2, 2)),
            arrayOf(arrayOf(0, 0), arrayOf(1, 0), arrayOf(2, 0)),
            arrayOf(arrayOf(0, 1), arrayOf(1, 1), arrayOf(2, 1)),
            arrayOf(arrayOf(0, 2), arrayOf(1, 2), arrayOf(2, 2)),
            arrayOf(arrayOf(0, 0), arrayOf(1, 1), arrayOf(2, 2)),
            arrayOf(arrayOf(0, 2), arrayOf(1, 1), arrayOf(2, 0))
        )

        for (lines in winLines) {
            val coord1 = lines[0]
            val coord2 = lines[1]
            val coord3 = lines[2]
            if (board[coord1[0]][coord1[1]] == board[coord2[0]][coord2[1]] && board[coord1[0]][coord1[1]] == board[coord3[0]][coord3[1]] && board[coord1[0]][coord1[1]] == 'O') {
                iter += 1
            }
            if (board[coord1[0]][coord1[1]] == board[coord2[0]][coord2[1]] && board[coord1[0]][coord1[1]] == board[coord3[0]][coord3[1]] && board[coord1[0]][coord1[1]] == 'X') {
                iter += 2
            }
        }

        return if (iter == 1) 'O'
        else 'X'
    }

    do {
        do {
            println("\nВведите координаты или команду")
            Coordinates = readLine().toString()
            pointFromString(Coordinates)
            isRightMove(Field, pointFromString(Coordinates))
        } while (isRightMove(Field, pointFromString(Coordinates)) == false)
        count++
        if (count % 2 == 1) Field[pointFromString(Coordinates)[0]][pointFromString(Coordinates)[1]] = 'X'
        else Field[pointFromString(Coordinates)[0]][pointFromString(Coordinates)[1]] = 'O'
        printBoard(Field)
        checkWin(Field)
//        isFill(Field)
        if(isFill(Field) == false) {
            result--
            break
        }
    } while (iter == 0)
    if (result==0) {
        println("Места больше нет, добро пожаловать отсюда!")
        return
    }
    println("Поздравляю, выиграли ${checkWin(Field)}")

}