package seedu.address.logic.commands;

import static seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil.showArticleAtIndex;
import static seedu.address.testutil.TypicalArticles.getTypicalArticleBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ARTICLE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_ARTICLE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil;
import seedu.address.logic.commands.articlecommands.EditArticleCommand;
import seedu.address.logic.commands.articlecommands.EditArticleCommand.EditArticleDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.ArticleBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.article.Article;
import seedu.address.testutil.ArticleBuilder;
import seedu.address.testutil.EditArticleDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditArticleCommand.
 */
public class EditArticleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalArticleBook() , new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Article editedArticle = new ArticleBuilder().build();
        EditArticleDescriptor descriptor = new EditArticleDescriptorBuilder(editedArticle).build();
        EditArticleCommand editArticleCommand = new EditArticleCommand(INDEX_FIRST_ARTICLE, descriptor);

        String expectedMessage = String.format(EditArticleCommand.MESSAGE_EDIT_ARTICLE_SUCCESS,
                Messages.format(editedArticle));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new ArticleBook(model.getArticleBook()), new UserPrefs());
        expectedModel.setArticle(model.getFilteredArticleList().get(0), editedArticle);

        assertCommandSuccess(editArticleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Article lastArticle = model.getFilteredArticleList().get(model.getFilteredArticleList().size() - 1);

        ArticleBuilder articleInList = new ArticleBuilder(lastArticle);
        Article editedArticle = articleInList.withTitle("Seven upon a time").build();

        EditArticleDescriptor descriptor = new EditArticleDescriptorBuilder().withTitle("Seven upon a time").build();
        EditArticleCommand editArticleCommand = new EditArticleCommand(INDEX_SIXTH_ARTICLE, descriptor);

        String expectedMessage = String.format(EditArticleCommand.MESSAGE_EDIT_ARTICLE_SUCCESS,
                Messages.format(editedArticle));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new ArticleBook(model.getArticleBook()), new UserPrefs());
        expectedModel.setArticle(lastArticle, editedArticle);

        assertCommandSuccess(editArticleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditArticleCommand editArticleCommand = new EditArticleCommand(INDEX_FIRST_ARTICLE,
                new EditArticleDescriptor());
        Article editedArticle = model.getFilteredArticleList().get(INDEX_FIRST_ARTICLE.getZeroBased());

        String expectedMessage = String.format(EditArticleCommand.MESSAGE_EDIT_ARTICLE_SUCCESS,
                Messages.format(editedArticle));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new ArticleBook(model.getArticleBook()), new UserPrefs());

        assertCommandSuccess(editArticleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showArticleAtIndex(model, INDEX_FIRST_ARTICLE);

        Article articleInFilteredList = model.getFilteredArticleList().get(INDEX_FIRST_ARTICLE.getZeroBased());
        Article editedArticle = new ArticleBuilder(articleInFilteredList).withTitle("New Title").build();
        EditArticleCommand editArticleCommand = new EditArticleCommand(INDEX_FIRST_ARTICLE,
                new EditArticleDescriptorBuilder().withTitle("New Title").build());

        String expectedMessage = String.format(EditArticleCommand.MESSAGE_EDIT_ARTICLE_SUCCESS,
                Messages.format(editedArticle));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new ArticleBook(model.getArticleBook()), new UserPrefs());
        expectedModel.setArticle(articleInFilteredList, editedArticle);

        assertCommandSuccess(editArticleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidArticleIndexUnfilteredList_failure() {
        int outOfBoundIndex = model.getFilteredArticleList().size() + 1;
        EditArticleDescriptor descriptor = new EditArticleDescriptorBuilder().withTitle("New Title").build();
        EditArticleCommand editArticleCommand = new EditArticleCommand(Index.fromOneBased(outOfBoundIndex), descriptor);

        ArticleCommandTestUtil.assertCommandFailure(editArticleCommand, model,
                Messages.MESSAGE_INVALID_ARTICLE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidArticleIndexFilteredList_failure() {
        showArticleAtIndex(model, INDEX_FIRST_ARTICLE);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredArticleList().size() + 1);
        EditArticleDescriptor descriptor = new EditArticleDescriptorBuilder().withTitle("New Title").build();
        EditArticleCommand editArticleCommand = new EditArticleCommand(outOfBoundIndex, descriptor);

        ArticleCommandTestUtil.assertCommandFailure(editArticleCommand, model,
                Messages.MESSAGE_INVALID_ARTICLE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditArticleCommand standardCommand = new EditArticleCommand(INDEX_FIRST_ARTICLE,
                ArticleCommandTestUtil.DESC_NVIDIA);

        // same values -> returns true
        EditArticleDescriptor copyDescriptor = new EditArticleDescriptor(ArticleCommandTestUtil.DESC_NVIDIA);
        EditArticleCommand commandWithSameValues = new EditArticleCommand(INDEX_FIRST_ARTICLE, copyDescriptor);
        assert (standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assert (standardCommand.equals(standardCommand));

        // null -> returns false
        assert (!standardCommand.equals(null));

        // different types -> returns false
        assert (!standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assert (!standardCommand.equals(new EditArticleCommand(INDEX_SIXTH_ARTICLE,
                ArticleCommandTestUtil.DESC_NVIDIA)));

        // different descriptor -> returns false
        assert (!standardCommand.equals(new EditArticleCommand(INDEX_FIRST_ARTICLE,
                ArticleCommandTestUtil.DESC_INTEL)));
    }

}
