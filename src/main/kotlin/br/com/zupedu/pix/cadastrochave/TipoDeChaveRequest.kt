package br.com.zupedu.pix.cadastrochave

import br.com.zup.edu.TipoChave
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator

enum class TipoDeChaveRequest(val atributoGrpc: TipoChave) {
    CPF(TipoChave.CPF) {
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }

            if (!chave.matches("^[0-9]{11}\$".toRegex())) {
                return false
            }

            return CPFValidator().run {
                initialize(null)

                isValid(chave, null)
            }
        }
    },

    CELULAR(TipoChave.CELULAR) {
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }

            return chave.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex())
        }

    },

    EMAIL(TipoChave.EMAIL) {
        override fun valida(chave: String?): Boolean {
            if (chave.isNullOrBlank()) {
                return false
            }

            return EmailValidator().run {
                initialize(null)

                isValid(chave, null)
            }
        }
    },

    ALEATORIA(TipoChave.ALEATORIA) {
        override fun valida(chave: String?) = chave.isNullOrBlank()
    };

    abstract fun valida(chave: String?): Boolean
}
