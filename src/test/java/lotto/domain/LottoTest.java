package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {

    @Test
    void create() {
        assertThat(new Lotto(RandomNumber.makeLottoNumbers()).value().size()).isEqualTo(6);
        assertThat(new Lotto(RandomNumber.makeLottoNumbers()).value().get(0)).isLessThan(46);
    }

    @DisplayName("로또 생성, 특정 값이 포함되어 있는지")
    @Test
    void contain() {
        assertThat(new Lotto(Arrays.asList(8, 21, 23, 41, 42, 43)).contains(8)).isTrue();
        assertThat(new Lotto(Arrays.asList(3, 5, 11, 16, 32, 38)).contains(8)).isFalse();
    }
}