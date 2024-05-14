package com.filipearn.itauauthentication.infra.utils;

public class MessageConstants {

    public static final String INIT_VALIDATION = "==== Iniciando validacao de token: {}";
    public static final String CLAIM_VALIDATION = "Claim {} validation: result={}, reason={}";
    public static final String RESULT =  "==== Result: {}";

    public static final String SIGNING_KEY_ERROR = "Erro na leitura do arquivo de signing-key jwt: path=%s";
    public static final String SIGNING_KEY_ERROR_AWS = "Erro ao obter secret singing-key jwt na AWS Secrets Manager!";
}
