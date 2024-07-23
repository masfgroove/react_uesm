package br.com.api.produtos.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Post")
@Getter
@Setter
public class PostModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime dataPublicacao;
    private String imagem;
    private String autor;

}
