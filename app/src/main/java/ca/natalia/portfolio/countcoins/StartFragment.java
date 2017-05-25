package ca.natalia.portfolio.countcoins;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class StartFragment extends Fragment {
    public static final String TAG = "StartFragment";
    private MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fgmt_start, container, false);
        setRetainInstance(true);

        Button start = (Button) view.findViewById(R.id.button_start_game);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, new GameBoardFragment())
                        .addToBackStack(null).commit();
            }
        });

        return view;
    }


}

