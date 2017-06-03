package competition;

import java.util.Random;

import javax.swing.JFrame;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

/**
 * 题目要求： 实现核心逻辑 用户界面展示结果 控制速度 单元测试
 */
public class Competition {
	private static Terminal terminal;

	public static int[][] Init(int row, int col) {
		int[][] array = new int[row][col];
		return array;
	}

	public static int[][] PutDot(int[][] array, int i, int j) {
		array[i][j] = 1;
		return array;
	}

	// 初始化矩阵
	public static int[][] RandomInit(int[][] array, int row, int col, int number) {
		for (int n = 0; n < number; n++) {
			Random random_row = new Random();
			Random random_col = new Random();
			random_row.setSeed(System.currentTimeMillis());
			random_col.setSeed(System.currentTimeMillis() / 2);
			// System.out.println(n);
			int i = random_row.nextInt(row);
			int j = random_col.nextInt(col);
			if (array[i][j] == 0)
				array = PutDot(array, i, j);
			else
				n--;
		}
		return array;
	}

	// 更新当前矩阵
	public static int[][] Update(int[][] array, int row, int col) {
		int[][] current = new int[row][col];// 当前状态
		int[][] temp = new int[row + 2][col + 2];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				temp[i + 1][j + 1] = array[i][j];
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				current[i][j] = Judge(temp, row, col, i + 1, j + 1);
			}
		}
		return current;
	}

	// 判断每个格子生命
	public static int Judge(int[][] array, int row, int col, int i, int j) {
		int status = 0;
		status = array[i - 1][j - 1] + array[i - 1][j] + array[i - 1][j + 1] + array[i][j - 1] + array[i][j + 1]
				+ array[i + 1][j - 1] + array[i + 1][j] + array[i + 1][j + 1];
		if (array[i][j] == 1) {
			if (status == 2 || status == 3) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if (status == 3)
				return 1;
			else
				return 0;
		}
	}

	// 打印
	public static void Disp(int[][] matrix, int row, int col) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 0) {
					terminal.applyForegroundColor(Terminal.Color.RED);
					terminal.moveCursor(i, j);
					terminal.putCharacter('△');
				} else {
					terminal.applyForegroundColor(Terminal.Color.GREEN);
					terminal.moveCursor(i, j);
					terminal.putCharacter('▲');
				}
			}
		}
	}

	// 显示到屏幕
	public static void Print(int[][] matrix, int row, int col) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int rows = 10, cols = 10, number = 50;
		int delay = 1000;
		if (args.length == 3) {
			rows = Integer.valueOf(args[0]);
			cols = Integer.valueOf(args[1]);
			number = Integer.valueOf(args[2]);
		} else if (args.length == 4) {
			rows = Integer.valueOf(args[0]);
			cols = Integer.valueOf(args[1]);
			number = Integer.valueOf(args[2]);
			delay = Integer.valueOf(args[3]);
		} else {
			System.out.println("Usage:\n" + "Competition [rows] [cols] [number]:\n"
					+ "\t\tDefine the row and col of the matrix\n" + "Competition [rows] [cols] [number] [delay]\n"
					+ "\t\tDefine the row and col of the matrix, and the delay time of each reflesh\n");
		}

		// System.out.println(rows+","+cols+","+speed);
		// create the terminal
		terminal = TerminalFacade.createSwingTerminal(cols, rows);

		// required by Lanterna framework to initialise
		terminal.enterPrivateMode();
		terminal.setCursorVisible(false);

		terminal.clearScreen();

		// set close operation so program with exit if "X" clicked
		if (terminal instanceof SwingTerminal) {
			((SwingTerminal) terminal).getJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		int[][] matrix = Init(rows, cols);
		matrix = RandomInit(matrix, rows, cols, number);
		// System.out.println(matrix[0][0]);
		while (true) {
			try {
				// Print(matrix, rows, cols);
				// clear();
				Disp(matrix, rows, cols);
				Thread.sleep(delay);
				terminal.clearScreen();
				matrix = Update(matrix, rows, cols);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
