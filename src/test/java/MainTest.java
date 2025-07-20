
import io.restassured.RestAssured;
import org.example.BoardApi;
import org.example.CardApi;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainTest {

    BoardApi boardApi = new BoardApi();
    CardApi  cardApi  = new CardApi();
    Random   rnd      = new Random();

    @Test
    void runScenario() {

        // 1) Board oluştur
        String boardId = boardApi.createBoard("Mini‑Board");
        assertNotNull(boardId, "Board ID null geldi!");
        System.out.println("Board oluşturuldu → ID = " + boardId);


        // 2) Board’daki ilk listenin ID’sini al
        String listId = given(boardApi.spec)
                .get("/boards/{id}/lists", boardId)
                .then().statusCode(200)
                .extract().path("[0].id");

        System.out.println("İlk liste ID = " + listId);

        // 3) İki kart oluştur
        String cardA = cardApi.createCard(listId, "Kart A");
        String cardB = cardApi.createCard(listId, "Kart B");
        List<String> cards = List.of(cardA, cardB);
        assertNotNull(cardA);
        assertNotNull(cardB);
        System.out.println("Kart A oluşturuldu → ID = " + cardA);
        System.out.println("Kart B oluşturuldu → ID = " + cardB);


        // 4) Rastgele birini güncelle
        String toUpdate = cards.get(rnd.nextInt(cards.size()));
        cardApi.updateCard(toUpdate, "Güncellendi!");
        System.out.println("Güncellenen kart ID = " + toUpdate);

        // 5) Kartları sil
        cardApi.deleteCard(cardA);   // Kart A silindi
        cardApi.deleteCard(cardB);   // Kart B silindi
        System.out.println("Kartlar silindi.");



        // 6) Board’u sil
        boardApi.deleteBoard(boardId);
        System.out.println("Board silindi.");

    }
}
