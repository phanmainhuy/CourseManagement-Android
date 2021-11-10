package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlearn.R;

import java.util.ArrayList;

import Model.NOTIFICATION;

public class NotificationAdapter_Listview extends BaseAdapter {
    Context context;
    ArrayList<NOTIFICATION> data = new ArrayList<>();

    public NotificationAdapter_Listview(Context context, ArrayList<NOTIFICATION>data)
    {
        this.context =context;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long)i;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        if(convertview== null)
            convertview = LayoutInflater.from(context).inflate(R.layout.layout_1dong_notification, null);

        ImageView imgLogo = convertview.findViewById(R.id.imgLogo_Notification);
        TextView title = convertview.findViewById(R.id.lblTitle_notification);
        TextView content = convertview.findViewById(R.id.lblContent_notification);
        TextView date = convertview.findViewById(R.id.lblDate_Notification);

        NOTIFICATION notification = data.get(position);

        imgLogo.setImageResource(notification.ImgLogo);
        title.setText(notification.Title);
        content.setText(notification.Content);
        date.setText(notification.Date);

        return convertview;
    }
}
