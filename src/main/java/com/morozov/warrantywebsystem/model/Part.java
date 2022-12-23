package com.morozov.warrantywebsystem.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "parts")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Part extends AbstractPersistable<Integer> {
    @Column(name = "part_number", nullable = false, unique = true)
    @NotBlank
    @Size(min = 7, max = 16)
    private String partNumber;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(max = 25)
    private String description;

    @Column(name = "price", nullable = false)
    @NotBlank
    private Double price;

    @Override
    public String toString() {
        return partNumber;
    }
}