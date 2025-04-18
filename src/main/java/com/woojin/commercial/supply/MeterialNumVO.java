package com.woojin.commercial.supply;

import lombok.Data;
import org.springframework.cache.annotation.Cacheable;

@Data
@Cacheable(value = "metrials")
public class MeterialNumVO {
    private int unit_price;
    private String  knumh;
    private String  material_num;
    
    /* 2025-04-10 손채원 추가 */
	private String datab;	/* 효력시작일 */
	private String datbi;	/* 효력종료일 */
}
