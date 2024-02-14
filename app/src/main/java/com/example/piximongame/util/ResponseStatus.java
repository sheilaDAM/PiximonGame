package com.example.piximongame.util;

public class ResponseStatus {
    public enum TipoCodigo {
        REGISTRADO(1), YA_EXITE(2), ERROR(3), LOGIN_CORRECTO(4), LOGIN_INCORRECTO(5);
        private final int valor;
        TipoCodigo(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }
    }

    private final TipoCodigo tipoCodigo;

    public ResponseStatus(TipoCodigo tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }

    public TipoCodigo getTipoCodigo() {
        return tipoCodigo;
    }
}
