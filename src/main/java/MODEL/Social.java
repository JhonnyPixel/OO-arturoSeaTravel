package MODEL;

import javax.swing.text.IconView;

public class Social {
    private String Nome_Social;
    private String Indirizzo_Social;
    public Social(String nome,String ind){
        this.Nome_Social=nome;
        this.Indirizzo_Social=ind;
    }

    public String getNome() {
        return Nome_Social;
    }

    public String getIndirizzo() {
        return Indirizzo_Social;
    }
}
