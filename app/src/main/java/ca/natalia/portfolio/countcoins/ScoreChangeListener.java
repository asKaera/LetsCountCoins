package ca.natalia.portfolio.countcoins;

/**
 * Created by natalia on 11/05/17.
 */
public interface ScoreChangeListener {
    void onCoinRemoved(int newScore, int removedCoinNominal);

}
