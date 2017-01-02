package britionary.prototypes;

public class SearcherPrototype {

    private SearcherPrototype() {
    }

    public static SearcherPrototype getInstance() {
        return SearcherPrototypeHolder.INSTANCE;
    }

    private static class SearcherPrototypeHolder {

        private static final SearcherPrototype INSTANCE = new SearcherPrototype();
    }
}
