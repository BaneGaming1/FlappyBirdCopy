import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void updateWithoutUp() {
        Player player = new Player(300, 300, 60, 58, 0, 2);
        assertEquals(300,player.y);
        player.update(false);
        assertEquals(302, player.y);
    }

    @Test
    void updateWithUp() {
        Player player = new Player(300, 300, 60, 58, 0, 2);
        assertEquals(300,player.y);
        player.update(true);
        assertEquals(288, player.y);
    }


}