import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class PipeTest {

    @Test
    void update() {
        Pipe pipe = new Pipe(510, 0, 78, 200, -1, 0, false);
        assertEquals(510,pipe.x);
        pipe.update();
        assertEquals(509,pipe.x);
    }
}