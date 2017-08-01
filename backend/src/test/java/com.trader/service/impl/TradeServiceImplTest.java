package com.trader.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import java.math.BigDecimal;

import com.trader.dto.TradeDTO;
import com.trader.service.impl.TradeServiceImpl;
import com.trader.service.TradeService;
import com.trader.service.TradeSubmitter;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceImplTest {

    @Mock private TradeSubmitter tradeSubmitter;

    private TradeService tradeService;

    @Before
    public void setUp() {

        tradeService = new TradeServiceImpl(
                tradeSubmitter
        );
    }

    @Test
    public void test_processTrade_submits() {

        TradeDTO tradeDTO = new TradeDTO("12345", "EUR", "GBP",
                new BigDecimal(1000.01),
                new BigDecimal(747.10), 0.7471,
                "24-JAN-15 10:27:44", "FR");

        tradeService.processTrade(tradeDTO);

        verify(tradeSubmitter, times(1)).submit(tradeDTO);
    }
}