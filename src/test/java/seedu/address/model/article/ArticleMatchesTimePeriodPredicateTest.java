package seedu.address.model.article;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalArticles.FOUR;
import static seedu.address.testutil.TypicalArticles.OLD;
import static seedu.address.testutil.TypicalArticles.ONCE;
import static seedu.address.testutil.TypicalPredicates.END;
import static seedu.address.testutil.TypicalPredicates.START;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.article.exceptions.InvalidDatesException;

public class ArticleMatchesTimePeriodPredicateTest {
    @Test
    //EP Valid Time Period
    public void newPredicate_validTimePeriod_noError() throws ParseException {
        PublicationDate start = ParserUtil.parsePublicationDate(START);
        PublicationDate end = ParserUtil.parsePublicationDate(END);
        ArticleMatchesTimePeriodPredicate predicate = new ArticleMatchesTimePeriodPredicate(start, end);
    }
    //EP Invalid time periods null
    @Test
    public void newPredicate_invalidTimePeriodNull_nullPointerError() throws ParseException {
        PublicationDate start = null;
        PublicationDate end = null;
        assertThrows(NullPointerException.class, () -> new ArticleMatchesTimePeriodPredicate(start, end));
    }
    //EP Invalid time periods start after end
    @Test
    public void newPredicate_invalidTimeStartAfterEnd_invalidDateError() throws ParseException {
        PublicationDate start = ParserUtil.parsePublicationDate(END);
        PublicationDate end = ParserUtil.parsePublicationDate(START);
        assertThrows(InvalidDatesException.class, () -> new ArticleMatchesTimePeriodPredicate(start, end));
    }
    //EP Boundary Value Start date = end date
    @Test
    public void newPredicate_startDateIsEndDate_noError() throws ParseException {
        PublicationDate start = ParserUtil.parsePublicationDate(START);
        PublicationDate end = ParserUtil.parsePublicationDate(START);
        ArticleMatchesTimePeriodPredicate predicate = new ArticleMatchesTimePeriodPredicate(start, end);
    }
    //EP Test falls within predicate
    @Test
    public void test_withinPeriod_returnTrue() throws ParseException {
        PublicationDate start = ParserUtil.parsePublicationDate(START);
        PublicationDate end = ParserUtil.parsePublicationDate(END);
        ArticleMatchesTimePeriodPredicate predicate = new ArticleMatchesTimePeriodPredicate(start, end);
        assert(predicate.test(ONCE));
    }
    //EP Test article is after predicate period
    @Test
    public void test_afterPeriod_returnFalse() throws ParseException {
        PublicationDate start = ParserUtil.parsePublicationDate(START);
        PublicationDate end = ParserUtil.parsePublicationDate(END);
        ArticleMatchesTimePeriodPredicate predicate = new ArticleMatchesTimePeriodPredicate(start, end);
        assert(!predicate.test(FOUR));
    }
    //EP Test article was published before predicate period
    @Test
    public void test_beforePeriod_returnFalse() throws ParseException {
        PublicationDate start = ParserUtil.parsePublicationDate(START);
        PublicationDate end = ParserUtil.parsePublicationDate(END);
        ArticleMatchesTimePeriodPredicate predicate = new ArticleMatchesTimePeriodPredicate(start, end);
        assert(!predicate.test(OLD));
    }
    //EP Boundary Start date = End date = Article Publish date
    @Test
    public void test_startEqualsToEndAndPublish_returnFalse() throws ParseException {
        PublicationDate start = ParserUtil.parsePublicationDate("03-01-2021");
        PublicationDate end = ParserUtil.parsePublicationDate("03-01-2021");
        ArticleMatchesTimePeriodPredicate predicate = new ArticleMatchesTimePeriodPredicate(start, end);
        assert(!predicate.test(ONCE));
    }
}
