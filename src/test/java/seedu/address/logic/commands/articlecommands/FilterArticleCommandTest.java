package seedu.address.logic.commands.articlecommands;


import static seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalArticles.getTypicalArticleBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.article.ArticleMatchesStatusPredicate;
import seedu.address.model.article.ArticleMatchesTagPredicate;
import seedu.address.model.article.ArticleMatchesTimePeriodPredicate;
import seedu.address.model.article.PublicationDate;
import seedu.address.model.tag.Tag;

public class FilterArticleCommandTest {
    private static final Tag EMPTY_TAG = null;
    private static final PublicationDate EMPTY_START = new PublicationDate(LocalDateTime.MIN);
    private static final PublicationDate EMPTY_END = new PublicationDate(LocalDateTime.MAX);
    private Model model;
    private Model expectedModel;
    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), getTypicalArticleBook(), new UserPrefs());
        expectedModel = new ModelManager(new AddressBook(), model.getArticleBook(), new UserPrefs());
    }
    @Test
    public void execute_zeroParameters_showUnfilteredList() throws ParseException {
        assertCommandSuccess(new FilterArticleCommand("", EMPTY_TAG, EMPTY_START, EMPTY_END),
                model, FilterArticleCommand.MESSAGE_SUCCESS, expectedModel);
    }
    //EP Only Status
    @Test
    public void execute_onlyStatus_showFilteredList() throws ParseException {
        ArticleMatchesStatusPredicate predicate = new ArticleMatchesStatusPredicate("DRAFT");
        expectedModel.updateFilteredArticleList(predicate);
        assertCommandSuccess(new FilterArticleCommand("DRAFT", EMPTY_TAG, EMPTY_START, EMPTY_END),
                model, FilterArticleCommand.MESSAGE_SUCCESS, expectedModel);
    }
    //ep Only tag
    @Test
    public void execute_onlyTag_showFilteredList() throws ParseException {
        Tag tag = new Tag("Science");
        ArticleMatchesTagPredicate predicate = new ArticleMatchesTagPredicate(tag);
        expectedModel.updateFilteredArticleList(predicate);
        assertCommandSuccess(new FilterArticleCommand("", tag, EMPTY_START, EMPTY_END),
                model, FilterArticleCommand.MESSAGE_SUCCESS, expectedModel);
    }
    //ep Only date
    @Test
    public void execute_onlyDates_showFilteredList() throws ParseException {
        PublicationDate start = ParserUtil.parsePublicationDate("01-01-2021");
        PublicationDate end = ParserUtil.parsePublicationDate("08-07-2021");
        ArticleMatchesTimePeriodPredicate predicate = new ArticleMatchesTimePeriodPredicate(start, end);
        expectedModel.updateFilteredArticleList(predicate);
        assertCommandSuccess(new FilterArticleCommand("", EMPTY_TAG, start, end),
                model, FilterArticleCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
