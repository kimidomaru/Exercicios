import java.util.Scanner;

public class mainTeste {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Digite o peso do aluno: ");
		float peso = input.nextFloat();
		System.out.println("Digite a altura do aluno: ");
		float altura = input.nextFloat();
		AlunoAcademia aluno = new AlunoAcademia(peso,altura);
		System.out.println("IMC: "+aluno.imc());
		System.out.println("Peso M�nimo: "+aluno.pesoMinimo());
		System.out.println("Peso M�ximo: "+ aluno.pesoMaximo());
		System.out.println("Peso M�dio: "+aluno.pesoMedio());
		input.close();
	}
}
