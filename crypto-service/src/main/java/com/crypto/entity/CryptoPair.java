package com.crypto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crypto_pair")
public class CryptoPair extends AbstractEntity {

    @Column(name = "crypto_pair_name", nullable = false)
    private String cryptoPairName;
}
