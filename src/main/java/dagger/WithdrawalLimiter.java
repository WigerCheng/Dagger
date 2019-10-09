package dagger;

import javax.inject.Inject;
import java.math.BigDecimal;

@PerSession
final class WithdrawalLimiter {

    private BigDecimal remainingWithdrawalLimit;

    @Inject
    WithdrawalLimiter(@MaximumWithdrawal BigDecimal maximumWithdrawal) {
        this.remainingWithdrawalLimit = maximumWithdrawal;
    }

    /**
     * 记录存款
     *
     * @param amount amount
     */
    void recordDeposit(BigDecimal amount) {
        remainingWithdrawalLimit = remainingWithdrawalLimit.add(amount);
    }

    /**
     * 记录取款
     *
     * @param amount amount
     */
    void recordWithdrawal(BigDecimal amount) {
        remainingWithdrawalLimit = remainingWithdrawalLimit.subtract(amount);
    }

    BigDecimal remainingWithdrawalLimit() {
        return remainingWithdrawalLimit;
    }
}
