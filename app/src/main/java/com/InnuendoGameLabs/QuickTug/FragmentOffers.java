package com.InnuendoGameLabs.QuickTug;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.OfferwallListener;
import com.InnuendoGameLabs.QuickTug.adapter.ListViewOfferAdapter;
import com.InnuendoGameLabs.QuickTug.library.DatabaseHandler;
import com.InnuendoGameLabs.QuickTug.library.GlobalVariables;
import com.InnuendoGameLabs.QuickTug.model.ListViewItemOffer;

import java.util.ArrayList;
import java.util.List;

public class FragmentOffers extends Fragment {

    ListView listview;
    String[] titles;
    TypedArray icons;
    ListViewOfferAdapter listViewAdapter;
    List<ListViewItemOffer> listItem;
    DatabaseHandler db;
    Context mContext;
    Intent fyberOfferwallIntent;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_offers, container, false);
        listview = (ListView) v.findViewById(R.id.lv);
        mContext = container.getContext();
        db = new DatabaseHandler(getActivity().getApplicationContext());

        // sonic

        if(GlobalVariables.netList.contains("Sonic")){
            IronSource.setOfferwallListener(new OfferwallListener() {
                @Override
                public void onOfferwallAvailable(boolean b) {
                    Toast.makeText(getActivity().getApplicationContext(),"Sonic Ready !", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onOfferwallOpened() {

                }

                @Override
                public void onOfferwallShowFailed(IronSourceError ironSourceError) {

                }

                @Override
                public boolean onOfferwallAdCredited(int i, int i1, boolean b) {
                    return false;
                }

                @Override
                public void onGetOfferwallCreditsFailed(IronSourceError ironSourceError) {

                }

                @Override
                public void onOfferwallClosed() {

                }
            });
        }




                // lay data tu string ra mang
        titles = getActivity().getResources().getStringArray(
                R.array.listview_titles);
        icons = getActivity().getResources().obtainTypedArray(
                R.array.listview_icons);

        listItem = new ArrayList<ListViewItemOffer>();

        for (int i = 0; i < titles.length; i++) {
            ListViewItemOffer lItem = new ListViewItemOffer(titles[i],
                    icons.getResourceId(i, -1));
            listItem.add(lItem);
        }
        listViewAdapter = new ListViewOfferAdapter(getActivity()
                .getApplicationContext(), listItem);
        listview.setAdapter(listViewAdapter);
        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0: {
                        if(GlobalVariables.netList.contains("Sonic")){
                            IronSource.showOfferwall();
                        }
                        break;
                    }

                    default: {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Coming soon !!", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
            }
        });
        return v;
    }



}
