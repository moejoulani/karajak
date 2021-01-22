package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
//import android.support.annotation.NonNull;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myapplication.Model.Parts;
import com.example.myapplication.Model.Users;
import com.example.myapplication.Prevalent.Prevalent;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
////mport com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class firstfun extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
        public CircleImageView profileImageView;
        TextView fere3,noo3,feaa,modeel,numOil;
        RelativeLayout hido;
        EditText partName,details ,price ;
        ProgressDialog progressDialog;
        FrameLayout fl;

        String Selec1,Selec2,Selec3,Selec4,Selec5,Pname,Describtion,saveCurrentDate,saveCurrentTime;
        int PriceS;
    /*____________________________________________________________*/
         Spinner mSpinner,mSpinner_section,carTypeSpinner,spinnerSeries,spinnerModel,spinnerOilNum;
        String downloadimageUrl;
         Button upload_newProduct;
         ImageView InputProductImage;
         private  static int GalleryPick =1;
         private Uri ImageUri;
         private  String productRandomKey;
         private StorageReference ProductImageRef;
       //  private DatabaseReference ProductsRef;

        /*____________________________________________________________*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstfun);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressDialog = new ProgressDialog(this);
     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
        CircleImageView profileImageView =headerView.findViewById(R.id.user_profile_image);
        Picasso.get().load(Prevalent.currentOnlineUsers.getImage()).placeholder(R.drawable.profile).into(profileImageView);
        userNameTextView.setText(Prevalent.currentOnlineUsers.getName());
        Picasso.get().load(Prevalent.currentOnlineUsers.getImage()).placeholder(R.drawable.profile);

        partName = (EditText) findViewById(R.id.spare_partName);
        details  = (EditText) findViewById(R.id.details);
        price    = (EditText) findViewById(R.id.price);
        InputProductImage = (ImageView) findViewById(R.id.select_part_image);
        upload_newProduct =(Button) findViewById(R.id.upload_newProduct);
          ProductImageRef = FirebaseStorage.getInstance().getReference().child("Part Name");
        //ProductsRef =FirebaseDatabase.getInstance().getReference().child("Parts");

        try {

            InputProductImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OpenGallery();
                }
            });
        }
        catch (Exception e)
        {

        throw  e;

        }





























        /*_________________Spinner_________________________________________________________________*/

            fere3 = (TextView) findViewById(R.id.fere3);
            hido  =findViewById(R.id.hido);
            feaa  =(TextView) findViewById(R.id.feaa);
            modeel=(TextView)findViewById(R.id.modeel);

        final String [] mOptions2_Mechanic={"ـــــــــ","البواجي و نظام التشغيل", "الاقشطة و البكارات", "المحرك","الكسكيت و المرابط","انابيب و برابيش","مكيف الهواء و الراديتر","نظام القير","الصنوبرصات","الستيرنج","البخاخات","الاكزوزت"};
        final String [] mOptions2_Body={ "ـــــــــ", "الابواب الامامية","الابواب الخلفية","الجناح الامامي","الجناح الخلفي","الزجاج الخلفي","الزجاج الامامي","مرشات الطمبون الامامي","المرشة الجانبية -السفلية","دعامة امامية","دعامة خلفية","فصالات ابواب","زجاج ابواب","سبويلر خلفي","طمبون خلفي","طمبون امامي","قفل ابواب","مرأة الجناح","يد ابواب خارجية"};
        final String [] mOptions2_Electronic={"ـــــــــ", "الأضوية الامامية" ,"الاضوية الخلفية","الغمازات","كشافات سفلية","ضوء البريك الخلفي","ضوء غرفة السيارة","كشافات ليد","لمبة ضوء امامي","لمبة ضوء خلفي","لمبة غماز","لمبة كشاف"};
        final String [] mOptions2_Tires={"ـــــــــ",  "سيارة ركاب", "4X4 دفع رباع", "شاحنات خفيفة"};
        final String [] mOptions2_Oiles={ "ـــــــــ",  "ADDINOL","ACDELCO","CASTROL","TOTAL","MEGUIN","PETROMIN","ROVER","QUAKER","LUCAS","KIXX","SHELL","LIQUI MOLY","JOPETROL","GULF"};
        final String [] mOptions2_Access={"ـــــــــ","جك رفع","حبل سحب","حساس وقوف","سلاسل ثلوج","شادر سيارة","طفاية حريق","قفل ستيرنج","كاميرا خلفية","كيبل اشتراك","مفاتيح سيارة","مفتاح جنط","منفاخ الاطارات"};


        String [] mOptions={"ـــــــــ","ميكانيك","بودي","كهرباء","اطارات","زيوت","اكسسوارات"};
        String [] carsType={"جميع السيارات","BMW","Mercedes","Audi","Toyota","Honda","Kia","Hyundai","Chevrolet","Dodge"};


        //_________________________________________________cars series _______________________________________________________________
        final String [] BmwSeries ={"الفئة الثالثة","الفئة الرابعة","الفئة الخامسة","الفئة السادسة","الفئة السابعة","X3","X5","X6"};
        final String [] MercedsSeries ={"الفئة A","الفئة C سيدان","الفئة E سيدان","الفئة S سيدان","CLA","الفئة C كوبيه","الفئة E كوبيه","الفئة S كوبيه","GLC","GLA","G"};
        final String [] ToyotaSeries ={"لاند كروزر","كورولا","كراون","ميراي","راف فور ","فورتشنر ","هايلكس ","يارس ","كامري","افالون ","بريوس","برادو "};
        final String [] HuyndaiSeries ={"اكسنت","ألينترا","توسان","سوناتا","جينسس"};
        final String [] AudiSeries    ={"RS5","R8","S8","KIO","Q5"};
        final String [] HondaSeries   ={"أكورد","سيفيك","سيتي","بايول","اتش ار في","كروس تور","اوديسي"};
        final String [] ChevroletSeries ={"سبارك","أفيو","أوبترا","ابيكا","لومينا","كابرس","كورفيت"};
        final String [] DodgeSeries   ={"افيجر","تشارجر","تشالنجر","داكوتا","رام","شادو","ماغنوم"};
        final String [] OilNum ={"5W-30","5W-40","10W-40","0W-30","15W-40","0W-40","5W-20","0W-20","10W-30"};
        final String [] KiaSeries ={"اوبتيما","افيلا","اسيا","بيكانتو","بنجو","بيستا","جراند بيرد","ريو","سبورتاج","سيراتو","سيفيا","كوريس"};
        String [] DefualtSeries ={"_________________"};

        final String LoLYears [] ={"1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
        // ____________________________________SPINNER MECHANIC ______________________________________________
        mSpinner_section = findViewById(R.id.spinner_section_2);
        spinnerSeries    =findViewById(R.id.spinner_carseries);
    //    spinnerOilNum =findViewById(R.id.spinner_oilnum);
        spinnerModel     =findViewById(R.id.spinner_carmodel);
        fl =(FrameLayout) findViewById(R.id.ssd);

   /*     final   ArrayAdapter mech =new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,mOptions2_Mechanic);
        mSpinner_section.setAdapter(mech);
    */
       carTypeSpinner =findViewById(R.id.spinner_car);
        noo3 =(TextView)findViewById(R.id.noo3);
        final ArrayAdapter cars =new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,carsType);












        // ____________________________________SPINNER MECHANIC ______________________________________________END
             //   numOil =(TextView) findViewById(R.id.oilnum) ;




        mSpinner =findViewById(R.id.spinner_section);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,mOptions);
        mSpinner.setAdapter(aa);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==1)                                  // mechanic section
                {
                    noo3.setText("اختر نوع السيارة ");
                    partName.setHint("اسم القطعة");
                        ArrayAdapter aa =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,mOptions2_Mechanic);
                    mSpinner_section.setAdapter(aa);
                    fere3.setVisibility(View.VISIBLE);
                    mSpinner_section.setVisibility(View.VISIBLE);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) hido.getLayoutParams();
                    params.addRule(RelativeLayout.BELOW, R.id.spinner_carmodel);
                    noo3.setVisibility(View.VISIBLE);
                    carTypeSpinner.setVisibility(View.VISIBLE);
                    carTypeSpinner.setAdapter(cars);
                    hido.setVisibility(View.VISIBLE);
                    feaa.setVisibility(View.VISIBLE);
                    spinnerSeries.setVisibility(View.VISIBLE);
                    modeel.setVisibility(View.VISIBLE);
                    spinnerModel.setVisibility(View.VISIBLE);
                    mSpinner_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                     // inside mechanic bawaji



                                        carTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                 if(position==0)
                                                 {

                                                        spinnerModel.setVisibility(View.GONE);
                                                        spinnerSeries.setVisibility(View.GONE);
                                                        feaa.setVisibility(View.GONE);
                                                        modeel.setVisibility(View.GONE);
                                                        Selec1=mSpinner.getSelectedItem().toString();
                                                        Selec2=mSpinner_section.getSelectedItem().toString();
                                                        Selec3=carTypeSpinner.getSelectedItem().toString();
                                                        Selec4="";
                                                        Selec5="";


                                                 }
                                                 else if(position==1)
                                                {
                                                    spinnerModel.setVisibility(View.VISIBLE);
                                                    spinnerSeries.setVisibility(View.VISIBLE);
                                                    feaa.setVisibility(View.VISIBLE);
                                                    modeel.setVisibility(View.VISIBLE);
                                                                ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,BmwSeries);
                                                                spinnerSeries.setAdapter(o);
                                                                spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                    @Override
                                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                        ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                                        spinnerModel.setAdapter(oe);
                                                                        spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                            @Override
                                                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                                Selec1 =mSpinner.getSelectedItem().toString();
                                                                                Selec2 =mSpinner_section.getSelectedItem().toString();
                                                                                Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                                                Selec4 =spinnerSeries.getSelectedItem().toString();
                                                                                Selec5 =spinnerModel.getSelectedItem().toString();
                                                                            }

                                                                            @Override
                                                                            public void onNothingSelected(AdapterView<?> parent) {

                                                                            }
                                                                        });


                                                                    }

                                                                    @Override
                                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                                    }
                                                                });


                                                }
                                                else if(position==2)
                                                {
                                                    spinnerModel.setVisibility(View.VISIBLE);
                                                    spinnerSeries.setVisibility(View.VISIBLE);
                                                    feaa.setVisibility(View.VISIBLE);
                                                    modeel.setVisibility(View.VISIBLE);
                                                    ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,MercedsSeries);
                                                    spinnerSeries.setAdapter(o);
                                                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                            ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                            spinnerModel.setAdapter(oe);
                                                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                @Override
                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                    Selec1 =mSpinner.getSelectedItem().toString();
                                                                    Selec2 =mSpinner_section.getSelectedItem().toString();
                                                                    Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                                    Selec4 =spinnerSeries.getSelectedItem().toString();
                                                                    Selec5 =spinnerModel.getSelectedItem().toString();
                                                                }

                                                                @Override
                                                                public void onNothingSelected(AdapterView<?> parent) {

                                                                }
                                                            });

                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> parent) {

                                                        }
                                                    });

                                                }
                                                else if(position==3)
                                                {
                                                    spinnerModel.setVisibility(View.VISIBLE);
                                                    spinnerSeries.setVisibility(View.VISIBLE);
                                                    feaa.setVisibility(View.VISIBLE);
                                                    modeel.setVisibility(View.VISIBLE);
                                                    ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,AudiSeries);
                                                    spinnerSeries.setAdapter(o);
                                                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                            ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                            spinnerModel.setAdapter(oe);
                                                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                @Override
                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                    Selec1 =mSpinner.getSelectedItem().toString();
                                                                    Selec2 =mSpinner_section.getSelectedItem().toString();
                                                                    Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                                    Selec4 =spinnerSeries.getSelectedItem().toString();
                                                                    Selec5 =spinnerModel.getSelectedItem().toString();
                                                                }

                                                                @Override
                                                                public void onNothingSelected(AdapterView<?> parent) {

                                                                }
                                                            });

                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> parent) {

                                                        }
                                                    });

                                                }
                                                else if(position==4)
                                                {
                                                    spinnerModel.setVisibility(View.VISIBLE);
                                                    spinnerSeries.setVisibility(View.VISIBLE);
                                                    feaa.setVisibility(View.VISIBLE);
                                                    modeel.setVisibility(View.VISIBLE);
                                                    ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,ToyotaSeries);
                                                    spinnerSeries.setAdapter(o);
                                                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                            ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                            spinnerModel.setAdapter(oe);
                                                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                @Override
                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                    Selec1 =mSpinner.getSelectedItem().toString();
                                                                    Selec2 =mSpinner_section.getSelectedItem().toString();
                                                                    Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                                    Selec4 =spinnerSeries.getSelectedItem().toString();
                                                                    Selec5 =spinnerModel.getSelectedItem().toString();
                                                                }

                                                                @Override
                                                                public void onNothingSelected(AdapterView<?> parent) {

                                                                }
                                                            });

                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> parent) {

                                                        }
                                                    });

                                                }
                                                else if(position==5)
                                                {
                                                    spinnerModel.setVisibility(View.VISIBLE);
                                                    spinnerSeries.setVisibility(View.VISIBLE);
                                                    feaa.setVisibility(View.VISIBLE);
                                                    modeel.setVisibility(View.VISIBLE);
                                                    ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,HondaSeries);
                                                    spinnerSeries.setAdapter(o);
                                                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                            ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                            spinnerModel.setAdapter(oe);
                                                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                @Override
                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                    Selec1 =mSpinner.getSelectedItem().toString();
                                                                    Selec2 =mSpinner_section.getSelectedItem().toString();
                                                                    Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                                    Selec4 =spinnerSeries.getSelectedItem().toString();
                                                                    Selec5 =spinnerModel.getSelectedItem().toString();
                                                                }

                                                                @Override
                                                                public void onNothingSelected(AdapterView<?> parent) {

                                                                }
                                                            });

                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> parent) {

                                                        }
                                                    });

                                                }
                                                else if(position==6)
                                                {
                                                    spinnerModel.setVisibility(View.VISIBLE);
                                                    spinnerSeries.setVisibility(View.VISIBLE);
                                                    feaa.setVisibility(View.VISIBLE);
                                                    modeel.setVisibility(View.VISIBLE);
                                                    ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,KiaSeries);
                                                    spinnerSeries.setAdapter(o);
                                                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                            ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                            spinnerModel.setAdapter(oe);
                                                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                @Override
                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                    Selec1 =mSpinner.getSelectedItem().toString();
                                                                    Selec2 =mSpinner_section.getSelectedItem().toString();
                                                                    Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                                    Selec4 =spinnerSeries.getSelectedItem().toString();
                                                                    Selec5 =spinnerModel.getSelectedItem().toString();
                                                                }

                                                                @Override
                                                                public void onNothingSelected(AdapterView<?> parent) {

                                                                }
                                                            });

                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> parent) {

                                                        }
                                                    });


                                                }
                                                else if(position==7)
                                                {
                                                    spinnerModel.setVisibility(View.VISIBLE);
                                                    spinnerSeries.setVisibility(View.VISIBLE);
                                                    feaa.setVisibility(View.VISIBLE);
                                                    modeel.setVisibility(View.VISIBLE);
                                                    ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,HuyndaiSeries);
                                                    spinnerSeries.setAdapter(o);
                                                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                            ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                            spinnerModel.setAdapter(oe);
                                                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                @Override
                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                    Selec1 =mSpinner.getSelectedItem().toString();
                                                                    Selec2 =mSpinner_section.getSelectedItem().toString();
                                                                    Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                                    Selec4 =spinnerSeries.getSelectedItem().toString();
                                                                    Selec5 =spinnerModel.getSelectedItem().toString();
                                                                }

                                                                @Override
                                                                public void onNothingSelected(AdapterView<?> parent) {

                                                                }
                                                            });

                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> parent) {

                                                        }
                                                    });


                                                }
                                                else if(position==8)
                                                {
                                                    spinnerModel.setVisibility(View.VISIBLE);
                                                    spinnerSeries.setVisibility(View.VISIBLE);
                                                    feaa.setVisibility(View.VISIBLE);
                                                    modeel.setVisibility(View.VISIBLE);
                                                    ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,ChevroletSeries);
                                                    spinnerSeries.setAdapter(o);
                                                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                            ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                            spinnerModel.setAdapter(oe);
                                                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                @Override
                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                    Selec1 =mSpinner.getSelectedItem().toString();
                                                                    Selec2 =mSpinner_section.getSelectedItem().toString();
                                                                    Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                                    Selec4 =spinnerSeries.getSelectedItem().toString();
                                                                    Selec5 =spinnerModel.getSelectedItem().toString();
                                                                }

                                                                @Override
                                                                public void onNothingSelected(AdapterView<?> parent) {

                                                                }
                                                            });

                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> parent) {

                                                        }
                                                    });


                                                }
                                                else if(position==9)
                                                {
                                                    spinnerModel.setVisibility(View.VISIBLE);
                                                    spinnerSeries.setVisibility(View.VISIBLE);
                                                    feaa.setVisibility(View.VISIBLE);
                                                    modeel.setVisibility(View.VISIBLE);
                                                    ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,DodgeSeries);
                                                    spinnerSeries.setAdapter(o);
                                                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override
                                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                            ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                            spinnerModel.setAdapter(oe);
                                                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                @Override
                                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                    Selec1 =mSpinner.getSelectedItem().toString();
                                                                    Selec2 =mSpinner_section.getSelectedItem().toString();
                                                                    Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                                    Selec4 =spinnerSeries.getSelectedItem().toString();
                                                                    Selec5 =spinnerModel.getSelectedItem().toString();
                                                                }

                                                                @Override
                                                                public void onNothingSelected(AdapterView<?> parent) {

                                                                }
                                                            });

                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> parent) {

                                                        }
                                                    });


                                                }

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });






                                        /*
                                                 Selec1 =mSpinner.getSelectedItem().toString();
                                                 Selec2 =mSpinner_section.getSelectedItem().toString();
                                                 Selec3 =carTypeSpinner.getSelectedItem().toString();

                                        */
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                           // Toast.makeText(firstfun.this,"الجراء اختيار نوع القطعة ",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else if(position==2)
                {
                    noo3.setText("اختر نوع السيارة ");
                    partName.setHint("اسم القطعة");
                    ArrayAdapter aa =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,mOptions2_Body);
                    mSpinner_section.setAdapter(aa);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) hido.getLayoutParams();
                    params.addRule(RelativeLayout.BELOW, R.id.spinner_carmodel);
                    fere3.setVisibility(View.VISIBLE);
                    mSpinner_section.setVisibility(View.VISIBLE);
                    feaa.setVisibility(View.VISIBLE);
                    spinnerSeries.setVisibility(View.VISIBLE);
                    modeel.setVisibility(View.VISIBLE);
                    spinnerModel.setVisibility(View.VISIBLE);
                    mSpinner_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                noo3.setVisibility(View.VISIBLE);
                                carTypeSpinner.setVisibility(View.VISIBLE);
                                carTypeSpinner.setAdapter(cars);
                                hido.setVisibility(View.VISIBLE);

                            carTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if(position==0)
                                    {

                                        spinnerModel.setVisibility(View.GONE);
                                        spinnerSeries.setVisibility(View.GONE);
                                        feaa.setVisibility(View.GONE);
                                        modeel.setVisibility(View.GONE);
                                        Selec1=mSpinner.getSelectedItem().toString();
                                        Selec2=mSpinner_section.getSelectedItem().toString();
                                        Selec3=carTypeSpinner.getSelectedItem().toString();
                                        Selec4="";
                                        Selec5="";


                                    }
                                    else if(position==1)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,BmwSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==2)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,MercedsSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==3)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,AudiSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==4)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,ToyotaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==5)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,HondaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==6)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,KiaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==7)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,HuyndaiSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==8)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,ChevroletSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==9)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,DodgeSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });




                 /*           Selec1 =mSpinner.getSelectedItem().toString();
                            Selec2 =mSpinner_section.getSelectedItem().toString();
                            Selec3 =carTypeSpinner.getSelectedItem().toString();
*/

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if(position==3)
                {
                    noo3.setText("اختر نوع السيارة ");
                    partName.setHint("اسم القطعة");
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) hido.getLayoutParams();
                    params.addRule(RelativeLayout.BELOW, R.id.spinner_carmodel);
                    feaa.setVisibility(View.VISIBLE);
                    spinnerSeries.setVisibility(View.VISIBLE);
                    modeel.setVisibility(View.VISIBLE);
                    spinnerModel.setVisibility(View.VISIBLE);
                    ArrayAdapter aa =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,mOptions2_Electronic);
                    mSpinner_section.setAdapter(aa);
                    fere3.setVisibility(View.VISIBLE);
                    mSpinner_section.setVisibility(View.VISIBLE);
                    mSpinner_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                noo3.setVisibility(View.VISIBLE);
                                carTypeSpinner.setVisibility(View.VISIBLE);
                                carTypeSpinner.setAdapter(cars);
                                hido.setVisibility(View.VISIBLE);




                            carTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if(position==0)
                                    {

                                        spinnerModel.setVisibility(View.GONE);
                                        spinnerSeries.setVisibility(View.GONE);
                                        feaa.setVisibility(View.GONE);
                                        modeel.setVisibility(View.GONE);
                                        Selec1=mSpinner.getSelectedItem().toString();
                                        Selec2=mSpinner_section.getSelectedItem().toString();
                                        Selec3=carTypeSpinner.getSelectedItem().toString();
                                        Selec4="";
                                        Selec5="";


                                    }
                                    else if(position==1)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,BmwSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });



                                    }
                                    else if(position==2)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,MercedsSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==3)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,AudiSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==4)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,ToyotaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==5)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,HondaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==6)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,KiaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==7)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,HuyndaiSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==8)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,ChevroletSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==9)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);

                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,DodgeSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        /*    Selec1 =mSpinner.getSelectedItem().toString();
                            Selec2 =mSpinner_section.getSelectedItem().toString();
                            Selec3 =carTypeSpinner.getSelectedItem().toString();
                        */

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if(position==4)
                {
                    noo3.setText("اختر نوع السيارة ");
                    partName.setHint("اسم القطعة");
                    ArrayAdapter aa =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,mOptions2_Tires);
                    mSpinner_section.setAdapter(aa);
                    fere3.setVisibility(View.VISIBLE);
                    feaa.setVisibility(View.INVISIBLE);
                    noo3.setVisibility(View.INVISIBLE);
                    modeel.setVisibility(View.INVISIBLE);
                    spinnerModel.setVisibility(View.INVISIBLE);
                    carTypeSpinner.setVisibility(View.INVISIBLE);
                    spinnerSeries.setVisibility(View.INVISIBLE);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) hido.getLayoutParams();
                    params.addRule(RelativeLayout.BELOW, R.id.spinner_section_2);
                    mSpinner_section.setVisibility(View.VISIBLE);
                    mSpinner_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            hido.setVisibility(View.VISIBLE);
                            Selec1 =mSpinner.getSelectedItem().toString();
                            Selec2 =mSpinner_section.getSelectedItem().toString();
                            Selec3 ="";
                            Selec4 ="";
                            Selec5 ="";
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                  /*  RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) hido.getLayoutParams();
                    params.addRule(RelativeLayout.BELOW, R.id.spinner_carmodel);
                    fere3.setVisibility(View.VISIBLE);
                    mSpinner_section.setVisibility(View.VISIBLE);
                    feaa.setVisibility(View.VISIBLE);
                    spinnerSeries.setVisibility(View.VISIBLE);
                    modeel.setVisibility(View.VISIBLE);
                    spinnerModel.setVisibility(View.VISIBLE);
                    mSpinner_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                noo3.setVisibility(View.VISIBLE);
                                carTypeSpinner.setVisibility(View.VISIBLE);
                                carTypeSpinner.setAdapter(cars);
                                hido.setVisibility(View.VISIBLE);


                            carTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                    if(position==1)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,BmwSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==2)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,MercedsSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==3)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,AudiSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==4)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,ToyotaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==5)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,HondaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==6)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,KiaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==7)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,HuyndaiSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==8)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,ChevroletSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==9)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,DodgeSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                            /*Selec1 =mSpinner.getSelectedItem().toString();
                            Selec2 =mSpinner_section.getSelectedItem().toString();
                            Selec3 =carTypeSpinner.getSelectedItem().toString();




                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });*/
                }
                else if(position==5)
                {
                    partName.setHint("يرجى كتابة اسم الزيت");
                    noo3.setText("عيار الزيت ");
                    ArrayAdapter aa =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,mOptions2_Oiles);
                    mSpinner_section.setAdapter(aa);
                    fere3.setVisibility(View.VISIBLE);
                    feaa.setVisibility(View.INVISIBLE);

                    ArrayAdapter oili =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,OilNum);
                    carTypeSpinner.setAdapter(oili);
                    noo3.setVisibility(View.VISIBLE);
                    modeel.setVisibility(View.INVISIBLE);
                    spinnerModel.setVisibility(View.INVISIBLE);
                    carTypeSpinner.setVisibility(View.VISIBLE);
                    carTypeSpinner.setAdapter(oili);
                    spinnerSeries.setVisibility(View.INVISIBLE);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) hido.getLayoutParams();
                    params.addRule(RelativeLayout.BELOW, R.id.spinner_car);
                    mSpinner_section.setVisibility(View.VISIBLE);
                    mSpinner_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            hido.setVisibility(View.VISIBLE);
                            Selec1 =mSpinner.getSelectedItem().toString();
                            Selec2 =mSpinner_section.getSelectedItem().toString();
                            Selec3 =carTypeSpinner.getSelectedItem().toString();
                            Selec4 ="";
                            Selec5 ="";
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                   /* feaa.setVisibility(View.VISIBLE);
                    spinnerSeries.setVisibility(View.VISIBLE);
                    modeel.setVisibility(View.VISIBLE);
                    spinnerModel.setVisibility(View.VISIBLE);
                    mSpinner_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                noo3.setVisibility(View.VISIBLE);
                                carTypeSpinner.setVisibility(View.VISIBLE);
                                carTypeSpinner.setAdapter(cars);
                                hido.setVisibility(View.VISIBLE);
                            carTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                    if(position==1)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,BmwSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==2)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,MercedsSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==3)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,AudiSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==4)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,ToyotaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==5)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,HondaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==6)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,KiaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==7)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,HuyndaiSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==8)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,ChevroletSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==9)
                                    {
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,DodgeSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                            /*Selec1 =mSpinner.getSelectedItem().toString();
                            Selec2 =mSpinner_section.getSelectedItem().toString();
                            Selec3 =carTypeSpinner.getSelectedItem().toString();


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });*/
                }
                else if(position==6)
                {
                    noo3.setText("اختر نوع السيارة ");
                    partName.setHint("اسم القطعة");


                    ArrayAdapter aa =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,mOptions2_Access);
                    mSpinner_section.setAdapter(aa);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) hido.getLayoutParams();
                    params.addRule(RelativeLayout.BELOW, R.id.spinner_carmodel);
                    fere3.setVisibility(View.VISIBLE);
                    mSpinner_section.setVisibility(View.VISIBLE);
                    feaa.setVisibility(View.VISIBLE);
                    spinnerSeries.setVisibility(View.VISIBLE);
                    modeel.setVisibility(View.VISIBLE);
                    spinnerModel.setVisibility(View.VISIBLE);
                    mSpinner_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                noo3.setVisibility(View.VISIBLE);
                                carTypeSpinner.setVisibility(View.VISIBLE);
                                carTypeSpinner.setAdapter(cars);
                                hido.setVisibility(View.VISIBLE);


                            carTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if(position==0)
                                    {

                                        spinnerModel.setVisibility(View.GONE);
                                        spinnerSeries.setVisibility(View.GONE);
                                        feaa.setVisibility(View.GONE);
                                        modeel.setVisibility(View.GONE);
                                        Selec1=mSpinner.getSelectedItem().toString();
                                        Selec2=mSpinner_section.getSelectedItem().toString();
                                        Selec3=carTypeSpinner.getSelectedItem().toString();
                                        Selec4="";
                                        Selec5="";


                                    }
                                  else  if(position==1)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,BmwSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });



                                    }
                                    else if(position==2)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,MercedsSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==3)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,AudiSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==4)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,ToyotaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==5)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,HondaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==6)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,KiaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==7)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,HuyndaiSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                    else if(position==8)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,ChevroletSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }
                                    else if(position==9)
                                    {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        feaa.setVisibility(View.VISIBLE);
                                        modeel.setVisibility(View.VISIBLE);
                                        ArrayAdapter o =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,DodgeSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe =new ArrayAdapter(firstfun.this,android.R.layout.simple_spinner_dropdown_item,LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Selec1 =mSpinner.getSelectedItem().toString();
                                                        Selec2 =mSpinner_section.getSelectedItem().toString();
                                                        Selec3 =carTypeSpinner.getSelectedItem().toString();
                                                        Selec4 =spinnerSeries.getSelectedItem().toString();
                                                        Selec5 =spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });


                                    }

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                            /*Selec1 =mSpinner.getSelectedItem().toString();
                            Selec2 =mSpinner_section.getSelectedItem().toString();
                            Selec3 =carTypeSpinner.getSelectedItem().toString();
*/

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                /*    Selec1 =mSpinner.getSelectedItem().toString();
                    Selec2 =mSpinner_section.getSelectedItem().toString();
                    Selec3 =carTypeSpinner.getSelectedItem().toString();  */

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    try {
        // Add Car Part Button
        upload_newProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(firstfun.this,Selec1+" ____"+Selec2+"_______ "+Selec3,Toast.LENGTH_SHORT).show();
                Toast.makeText(firstfun.this, "جار أضافة القطعة ...يرجى الأنتظار ...", Toast.LENGTH_LONG).show();
                progressDialog.setTitle("أضافة قطعة جديدة");
                progressDialog.setMessage("يرجى الأنتظار .....");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                ValidateProductData();

            }
        });
    }
    catch (Exception e)
    {
        throw e;
    }







    }



    public void OpenGallery()
    {
        Intent galleryIntent =new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GalleryPick);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GalleryPick && resultCode==RESULT_OK && data!=null)
        {
            ImageUri =data.getData();
            InputProductImage.setImageURI(ImageUri);
        }

    }

    public void ValidateProductData()
    {
            Describtion =details.getText().toString();
            PriceS      =Integer.parseInt(price.getText().toString());
            Pname       =partName.getText().toString();


            if(ImageUri == null)
            {
                Toast.makeText(this,"صورة القطعة مطلوبة...",Toast.LENGTH_SHORT).show();

            }
            else if(TextUtils.isEmpty(Describtion))
            {
                Toast.makeText(this,"ادخل التفاصيل ....",Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(String.valueOf(PriceS)))
            {
                Toast.makeText(this,"ادخل السعر....",Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(Pname))
            {
                Toast.makeText(this,"ادخل اسم القطعة....",Toast.LENGTH_SHORT).show();
            }
            else
            {
                StoreProductInformation();
            }
    }
    private void StoreProductInformation()
    {
       Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate =new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime =new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime =currentTime.format(calendar.getTime());

        productRandomKey =saveCurrentDate + saveCurrentTime;


        final StorageReference filePath =ProductImageRef.child(ImageUri.getPathSegments() + productRandomKey + ".jpg");
        final UploadTask uploadTask =filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure( Exception e) {
                     String message = e.toString();
                     Toast.makeText(firstfun.this," Error : "+message,Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               // Toast.makeText(firstfun.this," Successfully Added ",Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask =uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then( Task<UploadTask.TaskSnapshot> task) throws Exception {
                     if(!task.isSuccessful())
                     {
                         throw task.getException();
                     }
                     downloadimageUrl =filePath.getDownloadUrl().toString();
                     return filePath.getDownloadUrl();



                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete( Task<Uri> task) {
                        if(task.isSuccessful())
                        {
                            downloadimageUrl =task.getResult().toString();
                           // Toast.makeText(firstfun.this,"getting product image url successfully...",Toast.LENGTH_SHORT).show();
                            saveProductInfoToDataBase();
                        }
                    }
                });

            }
        });

    }

    public void saveProductInfoToDataBase()
    {
        final FirebaseFirestore db =FirebaseFirestore.getInstance();
    //    final DocumentReference reference =db.collection("Parts").document(productRandomKey);

        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("pid",productRandomKey);
        hashMap.put("data",saveCurrentDate);
        hashMap.put("time",saveCurrentTime);
        hashMap.put("description",Describtion);
        hashMap.put("image",downloadimageUrl);
        hashMap.put("price",PriceS);
        hashMap.put("partname",Pname);
        hashMap.put("Category_Section_CarType_CarSeries_CarModel",Selec1+"_"+Selec2+"_"+Selec3+"_"+Selec4+"_"+Selec5);
        hashMap.put("CarType_CarSeries_CarModel",Selec3+"_"+Selec4+"_"+Selec5);
        hashMap.put("Category",Selec1);
        hashMap.put("Section",Selec2);
        hashMap.put("CarType",Selec3);
        hashMap.put("CarSeries",Selec4);
        hashMap.put("CarModel",Selec5);
        hashMap.put("Vendor", Prevalent.currentOnlineUsers.getName());
        hashMap.put("VendorPhoneNumber",Prevalent.currentOnlineUsers.getPhone());
        hashMap.put("VendorAddress",Prevalent.currentOnlineUsers.getAddress());
        db.collection("Parts").document(productRandomKey).set(hashMap, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent =new Intent(firstfun.this,successfully.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message =e.getMessage().toString();
                Toast.makeText(firstfun.this,"Error : "+message,Toast.LENGTH_SHORT).show();
            }
        });

        Query q =db.collection("UCars").whereEqualTo("CarType_CarSeries_CarModel",Selec3+"_"+Selec4+"_"+Selec5);
        q.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful())
                    {
                        if(task.getResult().size()>0)
                        {
                            for(QueryDocumentSnapshot document :task.getResult())
                            {
                                Map<String,String> paNotifications =new HashMap<>();
                                paNotifications.put("from",Prevalent.currentOnlineUsers.getPhone());
                                paNotifications.put("type","request");
                                db.collection("NUsers").document(document.getId()).collection("Notifications").document().set(paNotifications,SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(firstfun.this,"Send Notification Successfully",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                        else{

                        }
                    }
            }
        });



        /*

        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("pid",productRandomKey);
        hashMap.put("data",saveCurrentDate);
        hashMap.put("time",saveCurrentTime);
        hashMap.put("description",Describtion);
        hashMap.put("image",downloadimageUrl);
        hashMap.put("price",PriceS);
        hashMap.put("partname",Pname);
        hashMap.put("Category_Section_CarType_CarSeries_CarModel",Selec1+"_"+Selec2+"_"+Selec3+"_"+Selec4+"_"+Selec5);
        hashMap.put("Category",Selec1);
        hashMap.put("Section",Selec2);
        hashMap.put("CarType",Selec3);
        hashMap.put("CarSeries",Selec4);
        hashMap.put("CarModel",Selec5);
        hashMap.put("Vendor", Prevalent.currentOnlineUsers.getName());
        hashMap.put("VendorPhoneNumber",Prevalent.currentOnlineUsers.getPhone());
        hashMap.put("VendorAddress",Prevalent.currentOnlineUsers.getAddress());
        ProductsRef.child(productRandomKey).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {

                  //  Toast.makeText(firstfun.this,"Part Successfully Added",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(firstfun.this,successfully.class);
                    startActivity(intent);

                }
                else
                {
                    String message =task.getException().toString();
                    Toast.makeText(firstfun.this,"Error : "+message,Toast.LENGTH_SHORT).show();

                }
            }
        });
*/
    }






















    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.firstfun, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // MY PARTS LIST
            Intent intent =new Intent(this,vendorpartlist.class);
            startActivity(intent);


        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

            Intent intent = new Intent(this,setting.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {

            Intent intent = new Intent(this,page2.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*____________________________LETS LEARN FIREBASE ________________________________________________*/

}