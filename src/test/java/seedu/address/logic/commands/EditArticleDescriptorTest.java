package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil.DESC_INTEL;
import static seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil.DESC_NVIDIA;
import static seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil.FIRST_VALID_AUTHOR_INTEL;
import static seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil.FIRST_VALID_OUTLET_INTEL;
import static seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil.FIRST_VALID_SOURCE_INTEL;
import static seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil.FIRST_VALID_TAG_INTEL;
import static seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil.VALID_LINK_INTEL;
import static seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil.VALID_PUBLICATION_DATE_INTEL;
import static seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil.VALID_STATUS_INTEL;
import static seedu.address.logic.commands.articlecommands.ArticleCommandTestUtil.VALID_TITLE_INTEL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.articlecommands.EditArticleCommand.EditArticleDescriptor;
import seedu.address.testutil.EditArticleDescriptorBuilder;

public class EditArticleDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditArticleDescriptor descriptorWithSameValues = new EditArticleDescriptor(DESC_NVIDIA);
        assertTrue(DESC_NVIDIA.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_NVIDIA.equals(DESC_NVIDIA));

        // null -> returns false
        assertFalse(DESC_NVIDIA.equals(null));

        // different types -> returns false
        assertFalse(DESC_NVIDIA.equals(5));

        // different values -> returns false
        assertFalse(DESC_NVIDIA.equals(DESC_INTEL));

        // different headline -> returns false
        EditArticleDescriptor editedArticle = new EditArticleDescriptorBuilder(DESC_NVIDIA).withTitle(VALID_TITLE_INTEL)
                .build();
        assertFalse(DESC_NVIDIA.equals(editedArticle));

        // different date -> returns false
        editedArticle = new EditArticleDescriptorBuilder(DESC_NVIDIA).withPublicationDate(VALID_PUBLICATION_DATE_INTEL)
                .build();
        assertFalse(DESC_NVIDIA.equals(editedArticle));

        // different status -> returns false
        editedArticle = new EditArticleDescriptorBuilder(DESC_NVIDIA).withStatus(VALID_STATUS_INTEL).build();
        assertFalse(DESC_NVIDIA.equals(editedArticle));

        // different contributors -> returns false
        editedArticle = new EditArticleDescriptorBuilder(DESC_NVIDIA).withAuthors(FIRST_VALID_AUTHOR_INTEL).build();
        assertFalse(DESC_NVIDIA.equals(editedArticle));

        // different interviewees -> returns false
        editedArticle = new EditArticleDescriptorBuilder(DESC_NVIDIA).withSources(FIRST_VALID_SOURCE_INTEL).build();
        assertFalse(DESC_NVIDIA.equals(editedArticle));

        // different outlet -> returns false
        editedArticle = new EditArticleDescriptorBuilder(DESC_NVIDIA).withOutlets(FIRST_VALID_OUTLET_INTEL).build();
        assertFalse(DESC_NVIDIA.equals(editedArticle));

        // different tags -> returns false
        editedArticle = new EditArticleDescriptorBuilder(DESC_NVIDIA).withTags(FIRST_VALID_TAG_INTEL).build();
        assertFalse(DESC_NVIDIA.equals(editedArticle));

        // different link -> returns false
        editedArticle = new EditArticleDescriptorBuilder(DESC_NVIDIA).withLink(VALID_LINK_INTEL).build();
        assertFalse(DESC_NVIDIA.equals(editedArticle));
    }
}
