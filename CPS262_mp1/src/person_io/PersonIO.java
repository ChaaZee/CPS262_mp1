package person_io;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

class Person implements Serializable{
	String name;
	int age;
	
	public Person(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public String toString() {
		return "Name: " + name + "\n" + "Age: " + age; 
	}
}

public class PersonIO {
	String fileName;
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	static Scanner kbInput = new Scanner(System.in);

	public PersonIO(String fileName){
		this.fileName = fileName;
		try {
			ois = new ObjectInputStream(new FileInputStream(fileName));
			oos = new ObjectOutputStream(new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add() throws IOException {
		System.out.println("Please enter the person's name: ");
		String name = kbInput.nextLine();
		
		System.out.println("Please enter the person's age: ");
		int age = kbInput.nextInt();
		
		Person person = new Person(name, age);
		
		oos.writeObject(person);
	
	}

	public void display() {
		boolean b = true;
		while (b) {
			try{
				System.out.println("-------------------------");
				System.out.println((Person)ois.readObject());
				System.out.println("-------------------------");
			} catch(EOFException e) {
				b = false;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws IOException {
		PersonIO person = new PersonIO("person.bin");
		try {
			int option = -1;
			while (option != 0) {
				System.out.println("Please choose an option:");
				System.out.println("0: quit");
				System.out.println("1: add");
				System.out.println("2: display");
				option = kbInput.nextInt();
				kbInput.nextLine();
				switch (option) {
				case 0:
					System.out.println("Bye");
					break;					
				case 1:
					person.add();
					break;
				case 2:
					person.display();
					break;
				}

			}
		} finally {
			try {
				person.oos.close();
				person.ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
