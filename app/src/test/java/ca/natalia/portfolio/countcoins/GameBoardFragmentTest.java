package cc.testme.countcoins;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by natalia on 16/05/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameBoardFragmentTest {
    private GameBoardFragment gameBoardFragment;
    private static final int NOMINAL = 5;

    @Before
    public void setUp() throws Exception {;
        gameBoardFragment = spy(new GameBoardFragment());
    }

    @Test
    public void getScoreInMoneyFormat() throws Exception {
        Assert.assertEquals("0.05", GameBoardFragment.getScoreInMoneyFormat(NOMINAL));
    }

    @Test
    public void initGameData() throws Exception {
        when(gameBoardFragment.generateRandomPrice()).thenReturn(NOMINAL);

        gameBoardFragment.initGameData();

        verify(gameBoardFragment).setPrice(eq(NOMINAL));
    }

}