package cc.testme.countcoins;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by natalia on 16/05/17.
 */
public class CoinTest {
    private Coin coin;
    private static final int NOMINAL = 5;

    @Before
    public void setUp() throws Exception {
        coin = new Coin(NOMINAL);
    }

    @Test
    public void getNominal() throws Exception {
        Assert.assertEquals(NOMINAL, coin.getNominal());
    }

}