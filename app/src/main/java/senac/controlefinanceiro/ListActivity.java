package senac.controlefinanceiro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {

    private RapidFloatingActionLayout rfaLayout;
    private RapidFloatingActionButton rfaBtn;
    private RapidFloatingActionHelper rfabHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            rfaLayout = findViewById(R.id.activity_main_rfal);
            rfaBtn = findViewById(R.id.activity_main_rfab);

            RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(getApplicationContext());
            rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
            List<RFACLabelItem> items = new ArrayList<>();
            items.add(new RFACLabelItem<Integer>()
                    .setLabel("Adicionar Despesa")
                    .setResId(R.drawable.ic_despesa)
                    .setIconNormalColor(0xffE6E6FA)
                    .setIconPressedColor(0xffFA8072)
                    .setLabelColor(Color.RED)
                    .setWrapper(0)
            );
            items.add(new RFACLabelItem<Integer>()
                    .setLabel("Adicionar Receita")
                    .setResId(R.drawable.ic_receita)
                    .setIconNormalColor(0xffE6E6FA)
                    .setIconPressedColor(0xff006400)
                    .setLabelColor(Color.GREEN)
                    .setLabelSizeSp(14)
                    .setWrapper(1)
            );

            rfaContent
                    .setItems(items)
                    .setIconShadowColor(0xff888888);

            rfabHelper = new RapidFloatingActionHelper(
                    getApplicationContext(),
                    rfaLayout,
                    rfaBtn,
                    rfaContent
            ).build();

        } catch (Exception e){
            Log.e("main", "onCreate: " + e.getMessage());
        }
    }

    private void chamarAtividade(int position){
        switch (position){
            case 0:
                startActivity(new Intent(this, DespesaActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, ReceitaActivity.class));
                break;
        }
    }
    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        chamarAtividade(position);
        rfabHelper.toggleContent();
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        chamarAtividade(position);
        rfabHelper.toggleContent();
    }
}