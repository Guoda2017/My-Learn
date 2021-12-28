package com.guo.stock.match.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : fengbo.guo
 * @date : 2021-12-27 11:19
 * @Description :
 */
@Slf4j
@Data
public class MatchResult {

    public final Order takerOrder;
    public final List<MatchRecord> matchRecords = new ArrayList<>();

}
