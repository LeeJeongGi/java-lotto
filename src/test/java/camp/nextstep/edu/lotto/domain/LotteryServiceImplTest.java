package camp.nextstep.edu.lotto.domain;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LotteryServiceImplTest {

    private LotteryServiceImpl lotteryServiceImpl;

    @BeforeEach
    void setup() {
        lotteryServiceImpl = new LotteryServiceImpl();
    }

    @Test
    @DisplayName("0을 입력할 경우 0개의 로또를 반환합니다")
    void purchase_zero() {
        // given
        final int investment = 0;
        // when
        final Lotteries lottery = lotteryServiceImpl.purchase(investment);
        // then
        assertThat(lottery).hasSize(0);
    }

    @Test
    @DisplayName("양수를 입력할 경우 해당 숫자 / 1000 만큼의 로또를 반환합니다")
    void purchase() {
        // given
        final int investment = 14000;
        // when
        final Lotteries lottery = lotteryServiceImpl.purchase(investment);
        // then
        assertThat(lottery).hasSize(14);
    }

    @Test
    @DisplayName("투자원금이 0원 이면 IllegalArgumentException 을 발생시킵니다")
    void calculateEarningsRate1() {
        final EnumMap<RewardType, Integer> rewardMap = new EnumMap<>(RewardType.class);
        rewardMap.put(RewardType.THREE_NUMBERS_MATCHED, 1);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> lotteryServiceImpl.calculateEarningsRate(0, rewardMap));
    }

    @Test
    @DisplayName("수익금이 0원 이면 0을 반환합니다")
    void calculateEarningsRate3() {
        // given
        final int investment = 5000;
        final EnumMap<RewardType, Integer> rewardMap = new EnumMap<>(RewardType.class);
        // when
        final double actual = lotteryServiceImpl.calculateEarningsRate(investment, rewardMap);
        // then
        assertThat(actual).isCloseTo(0, Percentage.withPercentage(1));
    }

    @Test
    @DisplayName("투자원금과 수익금을 입력하면 수익금 / 투자원금의 값을 반환합니다")
    void calculateEarningsRate4() {
        // given
        final int investment = 10000;
        final EnumMap<RewardType, Integer> rewardMap = new EnumMap<>(RewardType.class);
        rewardMap.put(RewardType.FOUR_NUMBERS_MATCHED, 2);
        // when
        final double actual = lotteryServiceImpl.calculateEarningsRate(investment, rewardMap);
        // then
        assertThat(actual).isCloseTo(10, Percentage.withPercentage(1));
    }
}