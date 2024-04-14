package seedu.address.logic.commands.articlecommands;


import static seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalArticles.getTypicalArticleBook;

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
    private Model model;
    private Model expectedModel;
    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), getTypicalArticleBook(), new UserPrefs());
        expectedModel = new ModelManager(new AddressBook(), model.getArticleBook(), new UserPrefs());
    }
    @Test
    public void execute_zeroParameters_showUnfilteredList() throws ParseException {
        assertCommandSuccess(new FilterArticleCommand("", "", "", ""),
                model, FilterArticleCommand.MESSAGE_SUCCESS, expectedModel);
    }
    //EP Only Status
    @Test
    public void execute_onlyStatus_showFilteredList() throws ParseException {
        ArticleMatchesStatusPredicate predicate = new ArticleMatchesStatusPredicate("DRAFT");
        expectedModel.updateFilteredArticleList(predicate);
        assertCommandSuccess(new FilterArticleCommand("DRAFT", "", "", ""),
                model, FilterArticleCommand.MESSAGE_SUCCESS, expectedModel);
    }
    //ep Only tag
    @Test
    public void execute_onlyTag_showFilteredList() throws ParseException {
        Tag tag = new Tag("Science");
        ArticleMatchesTagPredicate predicate = new ArticleMatchesTagPredicate(tag);
        expectedModel.updateFilteredArticleList(predicate);
        assertCommandSuccess(new FilterArticleCommand("", "Science", "", ""),
                model, FilterArticleCommand.MESSAGE_SUCCESS, expectedModel);
    }
    //ep Only date
    @Test
    public void execute_onlyDates_showFilteredList() throws ParseException {
        PublicationDate start = ParserUtil.parsePublicationDate("01-01-2021");
        PublicationDate end = ParserUtil.parsePublicationDate("08-07-2021");
        ArticleMatchesTimePeriodPredicate predicate = new ArticleMatchesTimePeriodPredicate(start, end);
        expectedModel.updateFilteredArticleList(predicate);
        assertCommandSuccess(new FilterArticleCommand("", "", "01-01-2021", "08-07-2021"),
                model, FilterArticleCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
