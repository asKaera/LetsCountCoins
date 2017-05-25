package ca.natalia.portfolio.countcoins;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.*;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by natalia on 11/04/17.
 */
public class GameBoardFragment extends Fragment implements ScoreChangeListener {

    public static final String TAG = "GameBoardFragment";

    private MainActivity activity;
    private Integer[] nominal = {5, 10, 25, 100};
    private int price;

    enum UserStatus {WON, LOSE}

    private TextView textViewPrice;
    private View view;
    private UserSelectedCoinsAdaptor userSelectedCoinsAdaptor;


    @Override
    public View onCreateView(LayoutInflater inflater, final @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fgmt_game, container, false);
        setRetainInstance(true);

        final List<Coin> userCoinsList = new ArrayList<>();

        initGameData();

        textViewPrice = (TextView) view.findViewById(R.id.price);
        textViewPrice.setText("$" + getScoreInMoneyFormat(price));

        Button cents5 = (Button) view.findViewById(R.id.cents5);
        cents5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userCoinsList.add(new Coin(nominal[0]));
                userSelectedCoinsAdaptor.notifyDataSetChanged();
            }
        });

        Button cents10 = (Button) view.findViewById(R.id.cents10);
        cents10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userCoinsList.add(new Coin(nominal[1]));
                userSelectedCoinsAdaptor.notifyDataSetChanged();
            }
        });

        Button cents25 = (Button) view.findViewById(R.id.cents25);
        cents25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userCoinsList.add(new Coin(nominal[2]));
                userSelectedCoinsAdaptor.notifyDataSetChanged();

            }
        });

        Button cents100 = (Button) view.findViewById(R.id.cents100);
        cents100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userCoinsList.add(new Coin(nominal[3]));
                userSelectedCoinsAdaptor.notifyDataSetChanged();

            }
        });

        Button endGame = (Button) view.findViewById(R.id.button_done);
        endGame.setOnClickListener(new View.OnClickListener() {

            // alert dialog shows user status: won or lose
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog);

                TextView status = (TextView) dialog.findViewById(R.id.status);


                if (price == userSelectedCoinsAdaptor.getScore()) {
                    status.setText("You " + UserStatus.WON.toString() + "!");
                } else {
                    status.setText("You " + UserStatus.LOSE.toString() + "!");
                }

                Button newGame = (Button) dialog.findViewById(R.id.new_game);
                newGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                        initGameData();

                        textViewPrice.setText("$" + getScoreInMoneyFormat(price));
                        userSelectedCoinsAdaptor.clear();
                    }
                });

                Button endGame = (Button) dialog.findViewById(R.id.end_game);
                endGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });
                dialog.show();
            }
        });

        GridView grid = (GridView) view.findViewById(R.id.grid);

        userSelectedCoinsAdaptor = new UserSelectedCoinsAdaptor(activity,
                ContextCompat.getDrawable(activity, R.drawable.button_coin), userCoinsList, this);

        grid.setAdapter(userSelectedCoinsAdaptor);
        userSelectedCoinsAdaptor.clear();

        return view;
    }

    public int generateRandomPrice() {
        Random random = new Random();
        return ((random.nextInt(40) + 1) * 5);
    }

    public static String getScoreInMoneyFormat(int moneyInCents) {
        String format;
        Number value;

        if (moneyInCents % 100 == 0) {
            format = "%d";
            value = moneyInCents / 100;
        } else {
            format = "%.2f";
            value = moneyInCents / 100.0;
        }
        return String.format(format, value);
    }

    public void initGameData() {
        setPrice(generateRandomPrice());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public void onCoinRemoved(int newScore, int removedCoinNominal) {

    }

    public void setPrice(int price) {
        this.price = price;
    }
}
