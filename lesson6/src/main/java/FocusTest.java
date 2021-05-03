import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FocusTest {
    private ArrayFocus fsc;
    private Object RuntimeException;

    @BeforeEach
    public  void init(){
        fsc = new ArrayFocus();
    }


    @ParameterizedTest
    @MethodSource("dataForCut")
    public  void testCut(int[] array, int[] result){
        Assertions.assertArrayEquals(result, ArrayFocus.fourCut(array));

    }

    @Test
    public void testExcept(){
        Assertions.assertThrows(RuntimeException.class, ()->{
            ArrayFocus.fourCut(new int[] {1, 2, 44, 24, 3, 7});
        });
    }

    public Stream<Arguments> dataForCut(){
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{1, 7}));
        out.add(Arguments.arguments(new int[] {1, 2, 4, 3, 4}, new int[]{}));
//        out.add(Arguments.arguments(new int[] {1, 2, 44, 24, 3, 7}, new RuntimeException()));

        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("dataForOneAndFour")
    public void testOneAndFour(int[] array, boolean result){
        Assertions.assertEquals(result, ArrayFocus.oneAndFour(array));
    }

    public Stream<Arguments> dataForOneAndFour(){
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[] { 1, 1, 4, 4, 1, 4, 4}, true));
        out.add(Arguments.arguments(new int[] {  1, 1, 1, 1, 1, 1}, false));
        out.add(Arguments.arguments(new int[] { 4, 4, 4, 4}, false));
        out.add(Arguments.arguments(new int[] { 1, 4, 4, 1, 1, 4, 3 }, false));

        return out.stream();
    }
}
