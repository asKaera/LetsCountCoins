package ca.natalia.portfolio.countcoins;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

/**
 * Created by natalia on 20/04/17.
 */
public class UserSelectedCoinsAdaptor extends ArrayAdapter<Coin> {

    private LayoutInflater inflater;
    private Drawable userCoinButton;
    private ScoreChangeListener scoreChangeListener;
    private ViewHolder viewHolder;
    private Coin coin;


    public UserSelectedCoinsAdaptor(Context context, final Drawable userCoinButton, List<Coin> userSelectedCoins, ScoreChangeListener scoreChangeListener) {

        super(context, R.layout.activity_grid_view_cell, userSelectedCoins);

        this.inflater = LayoutInflater.from(context);
        this.userCoinButton = userCoinButton;
        this.scoreChangeListener = scoreChangeListener;
        this.userSelectedCoins = userSelectedCoins;
    }

    private List<Coin> userSelectedCoins;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_grid_view_cell, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.userCoinButton = (Button) convertView.findViewById(R.id.grid_view_button);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        coin = getItem(position);

        showSelectedCoins();

        return convertView;

    }

    private static class ViewHolder {
        private Button userCoinButton;
    }

    public int getScore() {
        int score = 0;
        for (Coin coin : userSelectedCoins) {

            score += coin.getNominal();
        }
        return score;
    }

    private void showSelectedCoins() {

        if (coin.getNominal() < 100) {
            viewHolder.userCoinButton.setText(coin.getNominal() + "¢");
            viewHolder.userCoinButton.setTextColor(ContextCompat.getColor(getContext(), R.color.app_text_color));
            viewHolder.userCoinButton.setBackgroundResource(R.drawable.button_coin);
        } else {
            viewHolder.userCoinButton.setText("$1");
            viewHolder.userCoinButton.setTextColor(ContextCompat.getColor(getContext(), R.color.text_color));
            viewHolder.userCoinButton.setBackgroundResource(R.drawable.button_dollar);
        }

        viewHolder.userCoinButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                userSelectedCoins.remove(coin);
                scoreChangeListener.onCoinRemoved(getScore(), coin.getNominal());
                notifyDataSetChanged();
            }
        });
    }
}