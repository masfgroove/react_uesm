package br.com.api.produtos.modelo;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "finance")
@Getter
@Setter
public class FinanceModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String data;
    private BigDecimal entrada;
    private BigDecimal percentual;
    private BigDecimal lucroDolar;
    private BigDecimal lucroReal;
    private BigDecimal totalDolar;
    private int dias;
    private BigDecimal dolarHoje;
    private BigDecimal totalReal;
    private String nome;
}
