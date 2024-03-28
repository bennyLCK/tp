package seedu.address.ui;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.article.Article;

/**
 * A UI component that displays information of an {@code Article}.
 */
public class ArticleCard extends UiPart<Region> {

    private static final String FXML = "ArticleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Article article;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private FlowPane authors;
    @FXML
    private FlowPane sources;
    @FXML
    private FlowPane tags;
    @FXML
    private Label publicationDate;
    @FXML
    private Label status;
    @FXML
    private Hyperlink hyperlink;


    /**
     * Creates a {@code ArticleCode} with the given {@code Article} and index to display.
     */
    public ArticleCard(Article article, int displayedIndex) {
        super(FXML);
        this.article = article;
        id.setText(displayedIndex + ". ");
        title.setText(article.getTitle());

        article.getAuthors().stream()
                .sorted(Comparator.comparing(author -> author.authorName))
                .forEach(author -> authors.getChildren().add(new Label(author.authorName)));
        article.getSources().stream()
                .sorted(Comparator.comparing(source -> source.sourceName))
                .forEach(source -> sources.getChildren().add(new Label(source.sourceName)));
        article.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));


        publicationDate.setText(article.getPublicationDateAsString());
        status.setText(article.getStatus().toString());

        String link = article.getLink();
        System.out.println(link);
        hyperlink.setText("Link");
        hyperlink.setOnAction(event -> {
            openBrowser(link);
        });
    }

    private void openBrowser(String url) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Desktop not supported, cannot open browser.");
        }
    }
}
