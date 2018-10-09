package com.nelioalves.deliveryordersapi.domain.enums;

public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private Integer codigo;
    private String descricao;

    TipoCliente(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    /*
     * Este metodo ira validar o tipo do cliente informado no momento do cadastro
     */
    public static TipoCliente toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        for (TipoCliente x : TipoCliente.values()) {
            if (codigo.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Código inválido! Id: " + codigo);
    }

}
