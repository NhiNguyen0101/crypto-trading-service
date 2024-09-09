package com.crypto.entity;

import com.crypto.common.exeptions.ResourceNotFoundException;
import com.crypto.dto.CryptoPriceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crypto_price")
public class CryptoPrice extends AbstractEntity {

    @Column(name = "bid_price", nullable = false)
    private Double bidPrice;

    @Column(name = "bid_quantity", nullable = false)
    private Double bidQuantity;

    @Column(name = "ask_price", nullable = false)
    private Double askPrice;

    @Column(name = "ask_quantity", nullable = false)
    private Double askQuantity;

    @Column(name = "crypto_pair_id", nullable = false)
    private Long cryptoPairId;

    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "tenant_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "crypto_pair_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private CryptoPair cryptoPair;

    public CryptoPriceDto toDto() {
        return CryptoPriceDto.builder()
                .tenant(tenant.getName())
                .symbol(cryptoPair.getCryptoPairName())
                .askPrice(askPrice)
                .askQty(askQuantity)
                .bidPrice(bidPrice)
                .bidQty(bidQuantity)
                .build();
    }
}
