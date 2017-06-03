package test;

import org.junit.Test;

import competition.Competition;

public class Junit4Test {

	// ≤‚ ‘
	@Test
	public void Init_Test() {
		int[][] matrix = Competition.Init(10, 10);
		int[][] answer = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				assert matrix[i][j] == answer[i][j] : "Init_Test assertion failed!";
			}
		}

	}

	// ≤‚ ‘≈◊µ„
	@Test
	public void Test_Pao() {
		int[][] answer = new int[10][10];
		int[][] matrix = Competition.Init(10, 10);
		matrix = Competition.PutDot(matrix, 1, 1);
		matrix = Competition.PutDot(matrix, 2, 5);
		matrix = Competition.PutDot(matrix, 6, 7);
		answer[1][1] = 1;
		answer[2][5] = 1;
		answer[6][7] = 1;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				assert matrix[i][j] == answer[i][j] : "Init_Test assertion failed!";
			}
		}

	}

	// ≤‚ ‘æ≤Ã¨—›±‰
	@Test
	public void TestResult() {
		int[][] answer = new int[4][4];
		answer[1][1] = 1;
		answer[1][2] = 1;
		answer[2][1] = 1;
		answer[2][2] = 1;
		int[][] matrix = Competition.Init(4, 4);
		matrix = Competition.PutDot(matrix, 1, 1);
		matrix = Competition.PutDot(matrix, 1, 2);
		matrix = Competition.PutDot(matrix, 2, 1);
		matrix = Competition.PutDot(matrix, 2, 2);

		matrix = Competition.Update(matrix, 4, 4);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assert matrix[i][j] == answer[i][j] : "Init_Test assertion failed!";
			}
		}
	}

	@Test
	public void TestDeadResult() {
		int[][] answer = new int[4][4];
		int[][] matrix = Competition.Init(4, 4);
		matrix = Competition.PutDot(matrix, 0, 0);
		matrix = Competition.PutDot(matrix, 0, 3);
		matrix = Competition.PutDot(matrix, 3, 0);
		matrix = Competition.PutDot(matrix, 3, 3);

		matrix = Competition.Update(matrix, 4, 4);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assert matrix[i][j] == answer[i][j] : "Init_Test assertion failed!";
			}
		}
	}

	// ≤‚ ‘ÀÊº¥—›±‰
	@Test
	public void Update_Test() {
		System.out.println("===≥ı ºªØ===");
		int[][] matrix = Competition.Init(3, 5);
		Competition.Print(matrix, 3, 5);
		System.out.println("======");
		matrix = Competition.Update(matrix, 3, 5);
		Competition.Print(matrix, 3, 5);
		System.out.println("======");
		matrix = Competition.Update(matrix, 3, 5);
		Competition.Print(matrix, 3, 5);
	}

}
