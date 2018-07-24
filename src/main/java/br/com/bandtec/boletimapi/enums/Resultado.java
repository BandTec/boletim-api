package br.com.bandtec.boletimapi.enums;

/**
 * @author José Yoshiriro
 */
public enum Resultado {

    APROVADO("Aprovado"),
    REPROVADO_NOTA("Reprovado por nota"),
    REPROVADO_FREQUENCIA("Reprovado por frequência"),
    REPROVADO_GERAL("Reprovado Geral");

    private final String descricao;

    Resultado(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
