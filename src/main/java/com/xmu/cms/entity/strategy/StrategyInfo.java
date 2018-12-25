package com.xmu.cms.entity.strategy;

import java.util.List;

/**
 * @author JuboYu on 2018/12/25.
 * @version 1.0
 */
public class StrategyInfo {
    private List<Strategy> strategies;

    public StrategyInfo(List<Strategy> strategies) {
        this.strategies = strategies;
    }

    public StrategyInfo() {
    }

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<Strategy> strategies) {
        this.strategies = strategies;
    }
}
