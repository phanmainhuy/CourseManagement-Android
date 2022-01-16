package com.example.onlearn.activity.change_avatar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.onlearn.GLOBAL;
import com.example.onlearn.R;
import com.example.onlearn.activity.home.HomeActivity;
import com.example.onlearn.models.AVATAR;

import java.util.ArrayList;

public class ChangeAvatarActivity extends AppCompatActivity {
    GridView gridView;
    String titleActionBar = "Đổi ảnh đại diện";
    ArrayList<AVATAR> data = new ArrayList<>();
//    ChangeAvatarAdapter avatarAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avatar);
        DecorateActionBar();

        gridView = findViewById(R.id.GridviewAvatar);

        gridView.setAdapter(new ChangeAvatarAdapter(this, data));

//        data.add(new AVATAR("admin1.jpg"));

        data.add(new AVATAR("Mặc định","userdefault.png"));
        //girl
        data.add(new AVATAR("Cô bé dễ thương","avatar.png"));
        data.add(new AVATAR("Cô bé trưởng thành","girl.png"));
        data.add(new AVATAR("Cô bé phù thủy tí hon","magician.png"));
        data.add(new AVATAR("Chị nữ thư ký","user2.png"));


        //boy
        data.add(new AVATAR("Cậu bé dễ thương 1","boy.png"));
        data.add(new AVATAR("Cậu bé dễ thương 2","delivery-boy.png"));
        data.add(new AVATAR("Anh chàng điển trai","young-man.png"));
        data.add(new AVATAR("Anh chàng cool ngầu","gamer.png"));
        data.add(new AVATAR("Anh chàng hacker","hacker.png"));
        data.add(new AVATAR("Anh chàng IT dễ thương","programmer.png"));

        //bussiness
        data.add(new AVATAR("Người kinh doanh 1","businessman.png"));
        data.add(new AVATAR("Người kinh doanh 2","profile.png"));
        data.add(new AVATAR("Người kinh doanh 3","business-man.png"));
        data.add(new AVATAR("Người kinh doanh 4","bussiness-man.png"));

        //man
        data.add(new AVATAR("Bác sĩ nhiệt tình","doctor.png"));
        data.add(new AVATAR("Quý ngài giáo viên","male-teacher.png"));
        data.add(new AVATAR("Luật sư xuất sắc","lawyer.png"));
        data.add(new AVATAR("Người đàn ông lạc nơi hoang đảo","man.png"));
        data.add(new AVATAR("Ông phù thủy đáng yêu","wizard.png"));




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);


        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_home:
                this.finish();
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    //action bar
    void DecorateActionBar(){
        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(Html.fromHtml("<font color=\"white\">" + titleActionBar + "</font>"));
        //doi mau thanh action bars
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(GLOBAL.colorActionBar));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
    }


}