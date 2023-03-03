package reading_with_exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadingWithExceptions {
	
	public static void process(String inputFilename) {
		
		Scanner inputFile = null;
		PrintWriter outputFile = null;
		String outputFilename = null;
		
		try {
			inputFile = new Scanner(new FileInputStream(inputFilename));
			outputFilename = inputFile.next();
			//System.out.println(outputFilename);
			outputFile = new PrintWriter(outputFilename);
			
			int numberToRead = 0;
			
			try {
				numberToRead = inputFile.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid Number To Read");
			}
			if (numberToRead == 0) {
				inputFile.next();
				int i = 0;
				while (inputFile.hasNext()) {
					if(inputFile.hasNextInt()) {
						if(i == 9 || i == 19) {
							outputFile.println(inputFile.nextInt());
						} else {
							outputFile.print(inputFile.nextInt() + " ");
						}
					} else {
						if(inputFile.hasNext()) {
							String x = inputFile.next();
							System.out.println(x + " Is Not An Integer");
						} else {
							break;
						}
					}
					i++;
				}
			} else {
				for(int i = 0; i < numberToRead; i++) {
					if(inputFile.hasNextInt()) {
						if(i == 9 || i == 19) {
							outputFile.println(inputFile.nextInt());
						} else {
							outputFile.print(inputFile.nextInt() + " ");
						}
					} else {
						if(inputFile.hasNext()) {
							String x = inputFile.next();
							System.out.println(x + " Is Not An Integer");
						} else {
							break;
						}
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(inputFilename + " Not Found");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				inputFile.close();
				try {
					outputFile.close();
				} catch (NullPointerException e) {
					System.out.println(outputFilename + " Is Null");
				}
			} catch (NullPointerException e) {
				System.out.println(inputFilename + " Is Null");
			}
		}
		
		try {
			Scanner readFile = new Scanner(new FileInputStream(outputFilename));
			int i = 0;
			System.out.println();
			System.out.println(outputFilename + ":");
			while (readFile.hasNext()) {
				int x = readFile.nextInt();
				if(i == 9 || i == 19) {
					System.out.println(x);
				} else {
					System.out.print(x + " ");
				}
				i++;
			}
			System.out.println();
			System.out.println();
			readFile.close();
		} catch (FileNotFoundException e) {
			System.out.println(outputFilename + " File Not Found");
		} catch (NullPointerException e) {
			System.out.println("Output File Is Null");
		}
	}

	public static void main(String[] args) {
		//ReadingWithExceptions.process("inputFile.txt");
		ReadingWithExceptions.process(args[0]);
		ReadingWithExceptions.process(args[1]);
		ReadingWithExceptions.process(args[2]);
		ReadingWithExceptions.process(args[3]);

	}

}
