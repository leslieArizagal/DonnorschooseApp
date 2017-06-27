package com.donorschoose;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Carlos Espinoza on 5/23/2017.
 */

public class ProposalAdapter extends BaseAdapter {


    private  List<Donor.Proposal> proposal;
    private Context context;
    private static LayoutInflater inflater = null;

    public ProposalAdapter(Context context, List<Donor.Proposal> lista) {

        this.context = context;
        this.proposal = lista;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return proposal.size();
    }

    @Override
    public Object getItem(int i) {
        return proposal.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View vi = view;
        if (vi == null)
            vi = inflater.inflate(R.layout.cell, null);


        TextView donorN = (TextView) vi.findViewById(R.id.donorN);
        TextView title = (TextView) vi.findViewById(R.id.title);
        TextView description = (TextView) vi.findViewById(R.id.shortDescription);
        TextView teacherName = (TextView) vi.findViewById(R.id.teacherName);
        TextView schoolName = (TextView) vi.findViewById(R.id.schoolName);
        TextView needN = (TextView) vi.findViewById(R.id.needN);
        ImageView photo = (ImageView) vi.findViewById(R.id.photo);
        TextView goalN=(TextView) vi.findViewById(R.id.goalN);


        title.setText(Html.fromHtml(proposal.get(i).getTitle()));
        description.setText(proposal.get(i).getShortDescription().substring(0,125)+"...");
        teacherName.setText(proposal.get(i).getTeacherName());
        schoolName.setText(proposal.get(i).getSchoolName());
        donorN.setText(proposal.get(i).getNumDonors());
        needN.setText("$"+Math.round( Double.parseDouble(proposal.get(i).getCostToComplete())));
        goalN.setText("$"+Math.round( Double.parseDouble(proposal.get(i).getTotalPrice())));
        Picasso.with(context).load(proposal.get(i).getImageURL()).into(photo);


        return vi;
    }
}
