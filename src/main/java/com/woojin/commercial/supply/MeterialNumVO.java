package com.woojin.commercial.supply;

import lombok.Data;
import org.springframework.cache.annotation.Cacheable;

@Data
@Cacheable(value = "metrials")
public class MeterialNumVO {
    private int unit_price;
    private String  knumh;
    private String  material_num;
}
