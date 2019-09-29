package dagger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Dagger by default provides one Database object when LoginCommand requests it and another when DepositCommand requests it.
 * 当LoginCommand 和 DepositCommand请求一个Database的对象的时候， Dagger默认会提供一个新的Database的实例。
 * In order to tell Dagger that they both need to share the same instance of Database, we annotate the Database class with @Singleton.
 * 为了告诉Dagger它们是需要共享同一个Database对象，我们在Database的类上加上@Singleton
 * We also annotate our @Component type with @Singleton to declare that instances of classes annotated with @Singleton should be shared among other objects that depend on them in this component.
 * 我们还用@Singleton注释了@Component类型，以声明用@Singleton注释的类的实例应在依赖于此组件的其他对象之间共享。
 *
 * @see CommandRouterFactory
 */
@Singleton
public class Database {

    private final Map<String, Account> accounts = new HashMap<>();

    @Inject
    public Database() {
        System.out.println("Creating a new " + this);
    }

    Account getAccount(String username) {
        return accounts.computeIfAbsent(username, Account::new);
    }

    static final class Account {
        private final String username;
        private BigDecimal balance = BigDecimal.ZERO;

        Account(String username) {
            this.username = username;
        }

        void deposit(BigDecimal deposit) {
            balance = balance.add(deposit);
        }

        String username() {
            return username;
        }

        BigDecimal balance() {
            return balance;
        }
    }
}
