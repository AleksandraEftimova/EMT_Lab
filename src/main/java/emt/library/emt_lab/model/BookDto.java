package emt.library.emt_lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
public class BookDto {

    private Long id;

    private String name;

    private Integer availableCopies;

    private Long categoryId;

    private Long authorId;

    public BookDto(){}

    public BookDto(Long id, String name, Integer availableCopies, Long categoryId, Long authorId) {
        this.id = id;
        this.name = name;
        this.availableCopies = availableCopies;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }
}
