package com.info.recyclerviewkullanimi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BasitRvAdapter extends RecyclerView.Adapter<BasitRvAdapter.CardViewTasarimNesneleriniTutucu> {


    private Context mContext;
    private List<String> ulkelerDisaridanGelenList;
    //bir context oluşturup veri kümesini tanımlıyoruz



    //daha sonra bunlar için constructor oluşturuyorum
    public BasitRvAdapter(Context mContext, List<String> ulkelerDisaridanGelenList) {
        this.mContext = mContext;
        this.ulkelerDisaridanGelenList = ulkelerDisaridanGelenList;
    }



    //card tasarımımın içindeki görsel nesneleri temsilen bir sınıf oluşturuyorum
    public class CardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder {

        public TextView satirYazi;
        public CardView satirCardView;
        public ImageView noktaResim;  //tasarımdaki üçnokta resmini tanımlıyorum.


        public CardViewTasarimNesneleriniTutucu(View view){
            super(view);
            satirYazi = view.findViewById(R.id.satirYazi);
            satirCardView = view.findViewById(R.id.satirCardView);  //bu tasarımı sınıfımıza bağlıyoruz
            noktaResim = view.findViewById(R.id.noktaResim);   //tasarımımız üzerindeki üçnokta resmini adapter içerisinde bağlıyorum
            //ve ucnoktaya tıklandıgı anda menu açılmasını istiyorum. tıklama işlemi OnBindByHolder içerisinde çalışacak, ORAYA GİDİYORUM:
        }
    }


    //bağlama işlemi bittikten sonra onCreateViewHolder gelecek
    @NonNull
    @Override
    public CardViewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim, parent, false);
        return new CardViewTasarimNesneleriniTutucu(itemView);
    }


    @Override //hangi görsel nesneye hangi verinin yerleşeceğini burada tanımlamış olacağım:
    public void onBindViewHolder(@NonNull final CardViewTasarimNesneleriniTutucu holder, int position) {

        String ulke = ulkelerDisaridanGelenList.get(position); //bu şekilde satır satır hangi satırdaysam o ülkeyi bana verecek.

        holder.satirYazi.setText(ulke);

        holder.satirCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //orneğin tıklandıgında toast mesaj gosterecek olayım:
                Toast.makeText(mContext, "Seçtiğiniz ülke: "+ulke, Toast.LENGTH_SHORT).show();

            }
        });

        //noktaResmin tıklanma özelliğini burada tanımlıyorum:
        holder.noktaResim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //orneğin tıklandıgında toast mesaj gosterecek olayım:
                Toast.makeText(mContext, "Seçtiğiniz ülke: "+ulke, Toast.LENGTH_SHORT).show();

                PopupMenu popup = new PopupMenu(mContext,holder.noktaResim); //pop up menüyü tanımladım.
                //şimdi şunu düşünmem lazım hangi tasarımı buna ekleyecegim:
                MenuInflater inflater = popup.getMenuInflater();
                //şimdi bu inflater nesnesiyle tasarımımı buraya tanımlayacagım.
                inflater.inflate(R.menu.card_menu, popup.getMenu()); //bu şekilde popup ile tasarım eşleşmiş oldu
                popup.show();

                //popup üzerindeki itemlara yani sil ve düzenleye tıklayabilmek için tıklanma özelliği ekliyorum
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.action_sil:
                                Toast.makeText(mContext, "sil tıklandı", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.action_duzenle:
                                Toast.makeText(mContext, "düzenle tıklandı", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;

                        }
                    }
                });
            }
        });



    }


    @Override  //veri kümesinin boyutunu istiyor bizden, kaç tane veri var, kaç satır?
    public int getItemCount() {
        return ulkelerDisaridanGelenList.size();
    }


}




