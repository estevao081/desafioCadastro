package main.services.form;

public class FormUtils {

    private final String pathFormulario;
    private final int indicePrimeiraPerguntaAlteravel;

    public FormUtils(String pathFormulario) {
        this.pathFormulario = pathFormulario;
        this.indicePrimeiraPerguntaAlteravel = 7;
    }

    public String getPathFormulario() {
        return pathFormulario;
    }

    public int getIndicePrimeiraPerguntaAlteravel() {
        return indicePrimeiraPerguntaAlteravel;
    }
}
