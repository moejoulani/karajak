package com.example.myapplication;

import android.app.DownloadManager;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firestore.v1.TargetOrBuilder;

import java.security.PrivateKey;

public class sorting extends AppCompatActivity {
        private TextView close,returnSearch;
        private Spinner sp_location,sp_price,sp_rating;
        private String selectLocation,selectPrice,selectRating;
        private int THE_FUCKING_CODE;
        private static int PRICE_CODE=0;

       // public Query qqquery;
    private static int ONLY_LOCATION=45;
    private String Category,Selec2,Selec3,Selec4,Selec5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);
        returnSearch =(TextView)findViewById(R.id.update_sorting);
        close =(TextView)findViewById(R.id.close_sorting);
    //    close.setOnClickListener(new View.OnClickListener() {
      //      @Override
        //  public void onClick(View v) {
          //    finish();
           //}
     // });

        THE_FUCKING_CODE=0;

        Category =getIntent().getExtras().get("Category").toString();
        Selec2   =getIntent().getExtras().get("Selec2").toString();
        Selec3   =getIntent().getExtras().get("Selec3").toString();
        Selec4   =getIntent().getExtras().get("Selec4").toString();
        Selec5   =getIntent().getExtras().get("Selec5").toString();









        String Location [] ={"","عمان","البيادر","ماركا","وادي الرمم","القويسمة","سحاب","الزرقاء","أربد","العقبة","طبربور"};
        String Price []   ={"ـــــــــــ","من الأقل سعر ألى الأعلى","من الأعلى سعر ألى الأقل"};
        String Rating []  ={"ـــــــــــ","اعلى تقيم","اقل تقيم"};

        sp_location = (Spinner) findViewById(R.id.sp_location);
        sp_price    = (Spinner) findViewById(R.id.sp_price);
        sp_rating   = (Spinner) findViewById(R.id.sp_rating);
        ArrayAdapter adapter_location =new ArrayAdapter(this,android.R.layout.simple_list_item_1,Location);
        ArrayAdapter adapter_price    =new ArrayAdapter(this,android.R.layout.simple_list_item_1,Price);
        ArrayAdapter adapter_rating   =new ArrayAdapter(this,android.R.layout.simple_list_item_1,Rating);
        sp_location.setAdapter(adapter_location);
        sp_price.setAdapter(adapter_price);
        sp_rating.setAdapter(adapter_rating);

      returnSearch.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

                  searchFilter(THE_FUCKING_CODE);


              Toast.makeText(sorting.this,String.valueOf(THE_FUCKING_CODE),Toast.LENGTH_SHORT).show();
          }
      });


             sp_price.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                 public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                     if(position==0)
                     {
                         THE_FUCKING_CODE=45;
                     }
                     else if(position==1)
                     {
                     //    THE_FUCKING_CODE=97;
                            THE_FUCKING_CODE=1;
                     }
                     else if(position==2)
                     {
                       //  THE_FUCKING_CODE=102;
                         THE_FUCKING_CODE=2;
                     }
                 }

                 @Override
                 public void onNothingSelected(AdapterView<?> parent) {
                        THE_FUCKING_CODE=101;
                 }
             });
        sp_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectLocation=sp_location.getSelectedItem().toString();
                if(position==0)
                {
                    Toast.makeText(sorting.this,"pos -0",Toast.LENGTH_SHORT).show();
                  /*  if(THE_FUCKING_CODE==97)
                    {
                        Toast.makeText(sorting.this,"97 hohohohoh",Toast.LENGTH_SHORT).show();
                        THE_FUCKING_CODE=1;
                    }
                    else if(THE_FUCKING_CODE==102)
                    {
                        THE_FUCKING_CODE=2;
                    }
                    */
                    Toast.makeText(sorting.this,String.valueOf(THE_FUCKING_CODE),Toast.LENGTH_SHORT).show();
                }
                if(position>0)
                {

                    if(THE_FUCKING_CODE==1)
                    {
                        THE_FUCKING_CODE=97;
                    }
                    else if(THE_FUCKING_CODE==2)
                    {
                        THE_FUCKING_CODE=102;
                    }
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(sorting.this," noth pos -0",Toast.LENGTH_SHORT).show();
                sp_location.setSelection(0);

            }
        });
             close.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Toast.makeText(sorting.this,selectLocation + " ______ "+String.valueOf(THE_FUCKING_CODE),Toast.LENGTH_SHORT).show();
                 }
             });

  /*      returnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPriceFilter(PRICE_CODE);
            }
        });


*/













    }

    public void searchFilter(int code)
    {
        if(code == 45)
        {
            Intent intent = new Intent(sorting.this, final_brows.class);
            intent.putExtra("Category", Category);
            intent.putExtra("Selec2", Selec2);
            intent.putExtra("Selec3", Selec3);
            intent.putExtra("Selec4", Selec4);
            intent.putExtra("Selec5", Selec5);
            intent.putExtra("code", "45");
            intent.putExtra("field", "");
            intent.putExtra("location", selectLocation);
        Toast.makeText(this,"45COCO",Toast.LENGTH_SHORT).show();

            finish();
            startActivity(intent);
         }
        else if(code == 97)
        {
            Intent intent =new Intent(sorting.this,final_brows.class);
            intent.putExtra("Category", Category);
            intent.putExtra("Selec2", Selec2);
            intent.putExtra("Selec3", Selec3);
            intent.putExtra("Selec4", Selec4);
            intent.putExtra("Selec5", Selec5);
            intent.putExtra("code","97");
            intent.putExtra("field","");
            intent.putExtra("location",selectLocation);
            Toast.makeText(this,"97COCO",Toast.LENGTH_SHORT).show();


            finish();
            startActivity(intent);
        }
        else if(code == 102)
        {
            Intent intent =new Intent(sorting.this,final_brows.class);
            intent.putExtra("Category", Category);
            intent.putExtra("Selec2", Selec2);
            intent.putExtra("Selec3", Selec3);
            intent.putExtra("Selec4", Selec4);
            intent.putExtra("Selec5", Selec5);
            intent.putExtra("code","102");
            intent.putExtra("field","");
            intent.putExtra("location",selectLocation);
            Toast.makeText(this,"102COCO",Toast.LENGTH_SHORT).show();


            finish();
            startActivity(intent);

        }
        else if(code == 1)
        {
            Intent intent =new Intent(sorting.this,final_brows.class);
            intent.putExtra("Category", Category);
            intent.putExtra("Selec2", Selec2);
            intent.putExtra("Selec3", Selec3);
            intent.putExtra("Selec4", Selec4);
            intent.putExtra("Selec5", Selec5);
            intent.putExtra("code","1");
            intent.putExtra("field","");
            intent.putExtra("location",selectLocation);
            Toast.makeText(this,"102COCO",Toast.LENGTH_SHORT).show();


            finish();
            startActivity(intent);

        }
        else if(code == 2)
        {
            Intent intent =new Intent(sorting.this,final_brows.class);
            intent.putExtra("Category", Category);
            intent.putExtra("Selec2", Selec2);
            intent.putExtra("Selec3", Selec3);
            intent.putExtra("Selec4", Selec4);
            intent.putExtra("Selec5", Selec5);
            intent.putExtra("code","2");
            intent.putExtra("field","");
            intent.putExtra("location",selectLocation);
            Toast.makeText(this,"102COCO",Toast.LENGTH_SHORT).show();


            finish();
            startActivity(intent);

        }


    }







    public void searchPriceFilter(int code)
    {
        if(code == 0)
        {
         //   qqquery = FirebaseDatabase.getInstance().getReference("Parts").orderByChild("Category_Section_CarType_CarSeries_CarModel").equalTo(Category + "_" + Selec2 + "_" + Selec3 + "_" + Selec4 + "_" + Selec5);
            Intent intent =new Intent(sorting.this,final_brows.class);
            intent.putExtra("Category", Category);
            intent.putExtra("Selec2", Selec2);
            intent.putExtra("Selec3", Selec3);
            intent.putExtra("Selec4", Selec4);
            intent.putExtra("Selec5", Selec5);
            intent.putExtra("code","0");
            intent.putExtra("field","");


            finish();
            startActivity(intent);



        }
        else if(code==1)
        {
            Intent intent =new Intent(sorting.this,final_brows.class);
            intent.putExtra("Category", Category);
            intent.putExtra("Selec2", Selec2);
            intent.putExtra("Selec3", Selec3);
            intent.putExtra("Selec4", Selec4);
            intent.putExtra("Selec5", Selec5);
            intent.putExtra("code","1");
            intent.putExtra("field","");
            finish();
            startActivity(intent);

        }
        else if(code==2)
        {
            Intent intent =new Intent(sorting.this,final_brows.class);
            intent.putExtra("Category", Category);
            intent.putExtra("Selec2", Selec2);
            intent.putExtra("Selec3", Selec3);
            intent.putExtra("Selec4", Selec4);
            intent.putExtra("Selec5", Selec5);
            intent.putExtra("code","2");
            intent.putExtra("field","");
            finish();
            startActivity(intent);
        }




    }
}
