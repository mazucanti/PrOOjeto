package login;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class VerificaSenha implements IMetodoAutenticacao {

	public VerificaSenha() throws UsuarioNaoAutenticadoException, FileNotFoundException {
		autenticar();
	}
	
	private String procuraAcesso(String arquivo, String id) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(arquivo));
        while(scan.hasNext()){
            String line = scan.nextLine().toString();
            if(line.contains(id)){
		    String[] pswd = line.split(", ");
            	return pswd[1];
	    }
        }
		return null;
	}
	
	@Override
	public UsuarioAutenticado autenticar() throws UsuarioNaoAutenticadoException, FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Id:");
	    String id = scanner.next(); 
			    
		System.out.println("Senha:");
		String senha = scanner.next(); 
		
		String retornoSenha = null;
		String StringAcesso = null;
		switch(id.charAt(0)) {
		case 1:
			retornoSenha = procuraAcesso("../../db/listaPaciente.txt", id);
			StringAcesso = "Paciente";
			break;
		case 2:
			retornoSenha = procuraAcesso("../../db/listaMedico.txt", id);
			StringAcesso = "Medico";
			break;
		case 3:
			retornoSenha = procuraAcesso("../../db/listaTexEnfermagem.txt", id);
			StringAcesso = "Tecnico de Enfermagem";
			break;
		case 4:
			retornoSenha = procuraAcesso("../../db/listaAtendente.txt", id);
			StringAcesso = "Atendente";
			break;
		case 5:
			retornoSenha = procuraAcesso("../../db/listaGerente.txt", id);
			StringAcesso = "Gerente";
			break;
		}
		
		scanner.close();
		if(senha.equals(retornoSenha)){
			return new UsuarioAutenticado(id, StringAcesso);
		}
		else {
			throw new UsuarioNaoAutenticadoException();
		}
	}
}
