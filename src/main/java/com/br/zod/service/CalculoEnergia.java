package com.br.zod.service;

import org.springframework.stereotype.Service;

@Service
public class CalculoEnergia {

    public ResultadoCalculoEnergia calcularGastoEnergia(double watts, double horasPorDia, Integer diasNoMes, Double precoKWh) {

        if (watts <= 0 || horasPorDia <= 0) {
            throw new IllegalArgumentException("Os valores de watts e horasPorDia são obrigatórios e devem ser maiores que zero!");
        }

        // Define valores padrão, se necessário
        if (diasNoMes == null || diasNoMes <= 0) diasNoMes = 30; // Valor padrão
        if (precoKWh == null || precoKWh <= 0) precoKWh = 0.6;   // Valor padrão em reais

        // Cálculo do consumo
        double consumoDiarioKWh = (watts / 1000) * horasPorDia; // Convertendo watts para kWh
        double consumoMensalKWh = consumoDiarioKWh * diasNoMes; // Consumo mensal em kWh
        double custoMensal = consumoMensalKWh * precoKWh;       // Cálculo do custo em reais

        // Retorna os valores formatados no DTO
        return new ResultadoCalculoEnergia(
                String.format("%.2f", consumoMensalKWh),
                String.format("%.2f", custoMensal)
        );
    }

    // DTO para encapsular os resultados do cálculo
    public static class ResultadoCalculoEnergia {
        private final String consumoMensalKWh;
        private final String custoMensal;

        public ResultadoCalculoEnergia(String consumoMensalKWh, String custoMensal) {
            this.consumoMensalKWh = consumoMensalKWh;
            this.custoMensal = custoMensal;
        }

        public String getConsumoMensalKWh() {
            return consumoMensalKWh;
        }

        public String getCustoMensal() {
            return custoMensal;
        }
    }



}
