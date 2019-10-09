package dagger;

@Module
interface AccountModule {

    @Provides
    static Database.Account account(Database database, @Username String username) {
        return database.getAccount(username);
    }
}
